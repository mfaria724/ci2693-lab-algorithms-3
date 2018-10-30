import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class to provied user interaction.
 */
public class ClientProgram {

  // Transformers
  private static TypeTransformer vTrans;
  private static TypeTransformer eTrans;

  /**
   * Reads graph from file.
   * @param file // File path.
   * @throws Exception // If file doesn't exist or format is incorrect.
   */
  private static void readFile(String file) throws Exception {

    // Initialice BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));
        
    // Gets types.
    String[] types =  new String[3];
    types[0] = reader.readLine();
    types[1] = reader.readLine();
    types[2] = reader.readLine();

    // Gets quantity of vertices and edges.
    int[] quantities = new int[2];
    String line;
    line = reader.readLine();
    quantities[0] = Integer.parseInt(line);
    line = reader.readLine();    
    quantities[1] = Integer.parseInt(line);

    // Starts main menu
    mainMenu(types, quantities[0], quantities[1], file);

  }

  /**
   * Initialize graphs and starts menu loop.
   * @param types // Types for graph initialization (Vertex, Edge, Graph)
   * @param numVertices // Quanttity of Vertexes.
   * @param numEdges // Quantity of Edges.
   * @param file // File path
   * @throws Exception
   */
  private static void mainMenu(String[] types, int numVertices, int numEdges, String file) throws Exception{

    // Declares graph
    DirectedGraph<?,?> dirGraph = new DirectedGraph<String, String>();
    UndirectedGraph<?,?> undGraph = new UndirectedGraph<String, String>();;

    // Graph Initialization
    try {
      if(types[2].equals("D")){ // Directed Graph
        dirGraph = dirGraphInitialization(types);
      }
      else if (types[2].equals("N")) { // Undirected Graph
        undGraph = undGraphInitialization(types);
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }
    } catch (Exception e) {
      System.out.println("mainMenu exception: " + e.getMessage());
    }

    // Transform to Boolean graph direction.
    boolean directed = false;
    if(types[2].equals("D") || types[2].equals("d")){
      directed = true;
    }
    
    // Only for graphs from files.
    boolean result = false;

    if (!file.equals("")){
      // Read the specified type of graph.
      if(directed){
        result = dirGraph.loadGraph(file, numVertices, numEdges, vTrans, eTrans);
      }else {
        result = undGraph.loadGraph(file, numVertices, numEdges, vTrans, eTrans);
      }
  
      // loadGraph result.
      if(result){
        System.out.println("El grafo fue cargado satisfactoriamente.");
      }else {
        System.out.println("El grafo no pudo ser cargado correctamente.");
      }
    }

    // Menu loop.
    int option = -1;
    while(option != 0){
      // Gets input from user.
      option = generalMenu(directed);

      // Does an action specified.
      doOption(option, directed, dirGraph, undGraph);
    }

  }

  /**
   * Initializes graph when no file was provided.
   * @throws Exception
   */
  private static void emptyGraph() throws Exception{
    
    // User information.
    welcomeMenu();
    firstMenu();

    // Get graph types. (Vertices, Edges, Graph)
    String[] types = typesMenu();

    // Initialices Main Menu
    mainMenu(types, 0, 0, "");

  };

  /**
   * Menu when no graphs was provided.
   */
  private static void firstMenu(){

    // Robust User Input.
    while(true){
      // Prints information to user.
      System.out.println("Por favor, seleccione una de las siguientes opciones: ");
      System.out.println("1 --> Crear Grafo");
      System.out.println("2 --> Cargar Grafo (El programa terminará)");

      // Gets input from user.
      Scanner scanner = new Scanner(System.in);
      String option = scanner.nextLine();

      if(option.equals("2")){ // Exit program
        System.out.println("¡Gracias por usar este programa!");
        System.exit(0);
      }else if(option.equals("1")){ // Initialices Menu
        break;
      }else { // Invalid Option
        System.out.println("Por favor, seleccione una opción correcta.");
      }
    }

  }

  /**
   * Executes common actions for both graph types.
   * @param option // Option to be executed.
   * @param directed // Graph direction.
   * @param graph // Graph.
   */
  private static void doCommonOption(int option, boolean directed, Graph graph){

    // Variables declaration
    String id;
    Object data;
    Double weight;
    String v1;
    String v2;
    Integer result;
    Integer degree;

    // Variables initialization.
    String idVertexMessage = "ingrese el identificador del vértice ";

    switch(option){
      case 1: // Number of Vertices
        result = graph.numVertices();
        System.out.println("Número de Vértices: " + result);
        break;
      case 2: // Number of Edges.
        result = graph.numEdges();
        System.out.println("Número de Lados: " + result);
        break;
      case 3: // Add Vertex
        // Reads input from user.
        id = idInput(idVertexMessage +  " que desea añadir.");
        data = dataInput("ingrese el dato que desea almacenar en el vértice: ");
        weight = weightInput("ingrese el peso del vértice: ");
        
        // Adds vertex with specified data.
        if(graph.addVertex(id, data, weight)){
          System.out.println("Vertice añadido correctamente.");
        } else {
          System.out.println("El vertice no ha sido añadido correctamente");
        }
        break;
      case 4: // Get vertex.
        // Reads input from user.
        id = idInput(idVertexMessage + " que desea obtener: ");

        // Prints vertex's id.
        try {
          System.out.println(graph.getVertex(id).toString());
        } catch (NoSuchElementException e) {
          System.out.println("No existe ningún vértice con ese identificador.");
        }
        break;
      case 5: // Verify Vertex
        // Reads input from user.
        id = idInput(idVertexMessage + " que desea verificar: ");

        // Prints if the vertex is in the graph.
        if(graph.containsVertex(id)){
          System.out.println("El vértice de identificador " + id + " pertenece al grafo.");
        }else {
          System.out.println("No existe ningún vértice con el identificador especificado.");
        }
        break;
      case 6: // Verify Edge
        // Reads input from user.
        v1 = idInput(idVertexMessage + " 1 del lado: ");
        v2 = idInput(idVertexMessage + " 2 del lado: ");

        // Prints if the edge is in the graph.
        if(graph.cotainsEdge(v1, v2)){
          System.out.println("El lado que incide sobre los vertices " + v1 + " y " + v2 + " pertenece al grafo.");
        } else {
          System.out.println("No existe ningún lado que incida sobre los vertices indicados.");
        }
        
        break;
      case 7: // Delete Vertex
        // Reads input from user.
        id = idInput(idVertexMessage + "que desea elimina: ");

        // Deletes vertex from graph.
        if(graph.deleteVertex(id)){
          System.out.println("El vértice de identificador " + id + " fue eliminado.");
        }else {
          System.out.println("No existe ningún vértice en el grafo con el identificador especificado o");
        }
        break;
      case 8: // Get vertices.

        // Get all vertices.
        ArrayList<Vertex<?>> vertices = graph.vertices();
        System.out.println("Se imprimirán los vértices en el siguiente formato: ");
        System.out.println("Id\tDato\tPeso");

        // Prints all vertices data in the specified format.
        for(int i = 0; i < vertices.size(); i++){
          Vertex<?> v = vertices.get(i);
          System.out.println(v.getId() + "\t" + v.getData() + "\t" + v.getWeight());
        }
        break;
      case 9: //Get edges

        if(directed){ //Directed
           // Get all DirectedEdges
          ArrayList<DirectedEdge<?>> edges = graph.edges();
          System.out.println("Se imprimirán los arcos en el siguiente formato: ");
          System.out.println("Id\tDato\tPeso\tIdVerticeInicial\tIdVerticeFinal");

          // Prints all edges in specified format.
          for(int i = 0; i < edges.size(); i++){
            DirectedEdge<?> e = edges.get(i);
            System.out.println(e.getId() + "\t" + e.getData() + "\t" + e.getWeight() + "\t" + e.getInitialEnd() + "\t" + e.getFinalEnd());
          }  
        }else { // Simple
          // Get all SimpleEdges
          ArrayList<SimpleEdge<?>> edges = graph.edges();
          System.out.println("Se imprimirán los arcos en el siguiente formato: ");
          System.out.println("Id Dato Peso IdPrimerVertice IdSegundoVertice");

          // Prints all edges in specified format.
          for(int i = 0; i < edges.size(); i++){
            SimpleEdge<?> e = edges.get(i);
            System.out.println(e.getId() + "\t" + e.getData() + "\t" + e.getWeight() + "\t" + e.getEnd1() + "\t" + e.getEnd2());
          }
        } 
        break;
      case 10: // Vertex Degree
        // Reads input from user.
        id = idInput(idVertexMessage + "del cual desea conocer su grado: ");

        // Prints the degree if vertex exists.
        try {
          degree = graph.degree(id);
          System.out.println("El grado del vertice de identificador " + id + " es: " + degree);
        } catch (NoSuchElementException e) {
          System.out.println("No existe ningún vértice en el grafo con el identificador especificado.");
        }
        break;
      case 11: // Adjacents

        // Reads input from user.
        id = idInput(idVertexMessage + "del cual desea conocer sus vertices adyacentes: ");

        // Get all adjacents if vertex exists.
        try{ 

          // Get all adjacents
          ArrayList<Vertex<?>> adj = graph.neighbourhood(id);
          System.out.println("Se imprimirán los vértices adyacentes en el siguiente formato: ");
          System.out.println("Id\tDato\tPeso");
          
          // Prints adjacents in specified format.
          for(int i = 0; i < adj.size(); i++){
            Vertex<?> v = adj.get(i);
            System.out.println(v.getId() + "\t" + v.getData() + "\t" + v.getWeight());
          }
        } catch (NoSuchElementException e){
          System.out.println("No existe ningún vértice en el grafo con el identificador especificado.");
        }
        break;
      case 12: // Clone Graph

        // Clones the graph.
        graph = graph.clone();
        System.out.println(graph.toString());

        System.out.println("Se ha clonando el grafo");
        break;
      case 13: // String Representation
        System.out.println(graph.toString());
        break;
    }

  }

  /**
   * Executes an option introduced by user.
   * @param option // Option number.
   * @param directed // Graph direction.
   * @param dirGraph // DirectedGraph instance
   * @param undGraph // UndirectedGraph instance
   */
  private static void doOption(int option, boolean directed, DirectedGraph dirGraph, UndirectedGraph undGraph){

    // Variables declaration.
    String id;
    Object data;
    Double weight;
    String v1;
    String v2;
    String eDir;

    // Variables initialization.
    String idVertexMessage = "ingrese el identificador del vértice ";
    String idEdgeMessage = "ingrese el identificador de ";
    String dataEdgeMessage = "ingrese el dato de ";
    String weightEdgeMessage = "ingrese el peso de ";

    // Edge name.
    if(directed){
      eDir = "arco";
    }else {
      eDir = "arista";
    }

    // Common options.
    if(option < 14){
      if(directed){
        doCommonOption(option, directed, dirGraph);
      }else {
        doCommonOption(option, directed, undGraph);
      }
    }else { // Uncommon options.
      switch (option){
        case 14: //Add Edge

          // Gets input from user.
          id = idInput(idEdgeMessage + eDir + " que desea agregar: ");
          data = dataInput(dataEdgeMessage + eDir + " que desea agregar: ");
          weight = weightInput(weightEdgeMessage + eDir + " que desea agregar: ");
          v1 = idInput(idVertexMessage + "inicial de " + eDir);
          v2 = idInput(idVertexMessage + "final de " + eDir);

          // Adds edge with provided data.
          if(directed){
            if(dirGraph.addDirectedEdge(id, data, weight, v1, v2)){
              System.out.println("El arco fue añadido.");
            }else {
              System.out.println("El arco no fue añadido, verifique que los vertices existen.");
            }
          }else {
            if(undGraph.addSimpleEdge(id, data, weight, v1, v2)){
              System.out.println("La arista fue añadida.");
            } else {
              System.out.println("La arista no fue añadida.");
            }
          }
          break;

        case 15: // Delete Edge

          // Reads input from user.
          id = idInput(idEdgeMessage + eDir + " que desea eliminar: ");

          // Deletes edge if it's found.
          if(directed){
            if(dirGraph.deleteDirectedEdge(id)){
              System.out.println("El arco fue eliminado.");
            }else {
              System.out.println("El arco no fue eliminado, verifique que el arco existe.");
            }
            
          }else {
            id = idInput();

            if(undGraph.deleteSimpleEdge(id)){
              System.out.println("La arista fue eliminada.");
            } else {
              System.out.println("La arista no fue elminada, verifique que la arista existe.");
            }
          }

          break;

        case 16: // Get edge.

          // Reads input from user.
          id = idInput(idEdgeMessage + eDir + " que desea eliminar: ");

          // Prints edge data if founded.
          if(directed){
            try {
              System.out.println(dirGraph.getDirectedEdge(id).toString());
            } catch (NoSuchElementException e) {
              System.out.println("No existe ningún arco con el identificador indicado.");
            }
          }else {
            id = idInput();
            try {
              System.out.println(undGraph.getSimpleEdge(id).toString());
            } catch (NoSuchElementException e) {
              System.out.println("No existe ningún arco con el identificador indicado.");
            }
          }
          break;

        case 17: // Inner degree

          // Reads input form user.
          id = idInput(idVertexMessage + " del cual desea conocer su grado interno: ");

          // Gets inner degree if founded.
          try {
            int degree = dirGraph.innerDegree(id);  
            System.out.println("Grado interno del vértice con identificador " + id + ": " + degree);          
          } catch (NoSuchElementException e) {
            System.out.println("No existe ningún vertice con el identificador indicado.");
          }

          break;
        case 18: // Outter degree

          //Reads input from user.
          id = idInput(idVertexMessage + " del cual desea conocer su grado externo: ");

          // Gets outter from user.
          try {
            int degree = dirGraph.outterDegree(id);  
            System.out.println("Grado externo del vértice con identificador " + id + ": " + degree);          
          } catch (NoSuchElementException e) {
            System.out.println("No existe ningún vertice con el identificador indicado.");
          }

          break;
        case 19: //Sucessors

          // Reads input from user.
          id = idInput(idVertexMessage + " del cual desea conocer sus sucesores: ");

          // Prints sucessors if founded.
          try {
            ArrayList<Vertex<?>> suc = dirGraph.sucessors(id);
            if(suc.size() > 0){
              System.out.println("Los sucesores del vertice de identificador " + id + " son: ");
              for(int i = 0; i < suc.size(); i++){
                System.out.print(suc.get(i).getId() + " ");
              }
              System.out.println("");
            }else {
              System.out.println("El vetice de identificador " + id + " no tiene sucesores.");
            }
          } catch (NoSuchElementException e) {
            System.out.println("No existe ningún vertice con el identificador especificado.");
          }

          break;
        case 20: // Predecessors

          // Reads input form user.
          id = idInput(idVertexMessage + " del cual desea conocer sus predecesores: ");
          
          // Prints predecessors if founded.
          try {
            ArrayList<Vertex<?>> pre = dirGraph.predecessors(id);

            if(pre.size() > 0){
              System.out.println("Los predecesores del vertice de identificador " + id + " son: ");
              for(int i = 0; i < pre.size(); i++){
                System.out.print(pre.get(i).getId() + " ");
              }
              System.out.println("");
            }else {
              System.out.println("El vetice de identificador " + id + " no tiene predecesores.");
            }
              
          } catch (NoSuchElementException e) {
            System.out.println("No existe ningún vertice con el identificador especificado.");
          }

          break;
      }
    }

  }

  /**
   * Gets string input from user.
   * @param message // Message to be displayed to user.
   * @return // Input from user.
   */
  private static String idInput(String message){

    // Prints user information
    System.out.print("Por favor, " + message);

    // Gets string input from user.
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    return input;
  }

  /**
   * Gets vertex or edge type input from user.
   * @param message // Message to be displayed to user. 
   * @return // Data user has introduced 
   */
  private static Object dataInput(String message){

    // Initialize variables
    Scanner scanner = new Scanner(System.in);
    Object data;

    // Loop until input is correct.
    while(true){

      // Prints user information.
      System.out.println("Por favor, " + message);
      System.out.println("Recuerde que debe tener el tipo previamente especificado");
      
      // Reads user information.
      String input = scanner.nextLine();
    
      // Try to parse to data type.
      try {
        data = vTrans.transform(input);
        break;
      } catch (NumberFormatException e) { // Invalid data type.
        System.out.println("Por favor, introduzca un dato del tipo correcto.");
      }
    }

    return data;
  }

  /**
   * Gets input for vertex or edge weight.
   * @param message // Message to be displayed to user.
   * @return // weight in Double.
   */
  private static Double weightInput(String message){
    
    // Initialize variables.
    Scanner scanner = new Scanner(System.in);
    Double weight;

    // Loops until input is correct.
    while(true){

      // Prints user information.
      System.out.print("Por favor, " + message);
      
      // Reads input from user.
      String input = scanner.nextLine();

      // Try to parse weight
      try {
        weight = Double.parseDouble(input);
        break;
      } catch (NumberFormatException e) { // Invalid type
        System.out.println("Por favor, introduzca un dato del tipo correcto.");
      }
    }

    return weight;
  }

  /**
   * DirectedGraph initialization and Transformers Initialization.
   * @param types // Graph, Vertices and Edges types.
   * @return // A new graph.
   * @throws Exception // If a type is incorrect.
   */
  private static DirectedGraph dirGraphInitialization(String[] types) throws Exception{

    // Declares graph
    DirectedGraph<?,?> graph = new DirectedGraph<String, String>();

    if(types[0].equals("B")){ // Boolean Vertex
      vTrans = new BooleanTransformer();

      if(types[1].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new DirectedGraph<Boolean, Boolean>();
      }else if(types[1].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new DirectedGraph<Boolean, Double>();
      }else if(types[1].equals("S")){ // String Edge
        eTrans = new StringTransformer();

        graph = new DirectedGraph<Boolean, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[0].equals("D")){ // Double Vertex
      vTrans = new DoubleTransformer();

      if(types[1].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new DirectedGraph<Double, Boolean>();
      }else if(types[1].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new DirectedGraph<Double, Double>();
      }else if(types[1].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new DirectedGraph<Double, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[0].equals("S")){ // String Vertex
      vTrans = new StringTransformer();
      
      if(types[1].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new DirectedGraph<String, Boolean>();
      }else if(types[1].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new DirectedGraph<String, Double>();
      }else if(types[1].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new DirectedGraph<String, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else { // Invalid Format
      throw new Exception("Invalid file format");
    }

    return graph;
  }
  
  /**
   * UndirectedGraph initialization and Transformers initialization.
   * @param types // Graph, Vertices and Edges types.
   * @return // A new UndirectedGraph.
   * @throws Exception //If a type is in an invalid format.
   */
  private static UndirectedGraph undGraphInitialization(String[] types) throws Exception{
    
    // Declares graph
    UndirectedGraph<?,?> graph = new UndirectedGraph<String, String>();

    if(types[0].equals("B")){
      vTrans = new BooleanTransformer();


      if(types[1].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new UndirectedGraph<Boolean, Boolean>();
      }else if(types[1].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new UndirectedGraph<Boolean, Double>();
      }else if(types[1].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new UndirectedGraph<Boolean, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[0].equals("D")){
      vTrans = new DoubleTransformer();


      if(types[1].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new UndirectedGraph<Double, Boolean>();
      }else if(types[1].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new UndirectedGraph<Double, Double>();
      }else if(types[1].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new UndirectedGraph<Double, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[0].equals("S")){
      vTrans = new StringTransformer();

      if(types[1].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new UndirectedGraph<String, Boolean>();
      }else if(types[1].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new UndirectedGraph<String, Double>();
      }else if(types[1].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new UndirectedGraph<String, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else { // Invalid Format
      throw new Exception("Invalid file format");
    }

    return graph;

  }

  /**
   * Prints some user information.
   */
  private static void welcomeMenu(){

    // Welcome
    System.out.println("¡BIENVENIDO! \n");

    // Information
    System.out.println("Si desea cargar un grafo desde un archivo,");
    System.out.println("ejecute este programa como java ClientProgram <nombreArchivo>\n");
    System.out.println("¡IMPORTANTE!: Recuerde asegurarse que el archivo está en la misma carpeta que el prgrama.\n");

  }

  /**
   * Gets types from user to initialize a graph.
   * @return // Array of types (Vertices, Edges, Graph)
   */
  private static String[] typesMenu(){

    // Initialices variables.
    String[] types = new String[3];
    Scanner scanner = new Scanner(System.in);

    // Gets Graph type.
    while(true){

      // Prints user info.
      System.out.println("Por favor, indique el tipo de grafo que desea crear: ");
      System.out.println("D --> Grafo Dirigido");
      System.out.println("N --> Grafo No Dirigido");

      // Read input from user.
      types[2] = scanner.nextLine().toUpperCase();

      // Checks input.
      if(types[2].equals("N") || types[2].equals("D") ){
          break;
      }

    }

    // Gets Vertices type.
    while(true){

      // Prints user info.
      System.out.println("Por favor, indique el tipo de dato que desea almacenar en los vértices: ");
      System.out.println("B --> Boolean");
      System.out.println("D --> Double");
      System.out.println("S --> String");

      // Read input from user.
      types[0] = scanner.nextLine().toUpperCase();

      // Checks input.
      if(types[0].equals("B") || types[0].equals("D") || types[0].equals("S") ){
          break;
      }

    }

    // Gets edges type.
    while(true){

      // Prints user info.
      System.out.println("Por favor, indique el tipo de dato que desea almacenar en los lados: ");
      System.out.println("B --> Boolean");
      System.out.println("D --> Double");
      System.out.println("S --> String");

      // Reads input from user.
      types[1] = scanner.nextLine().toUpperCase();

      // Checks input.
      if(types[1].equals("B") || types[1].equals("D") || types[1].equals("S") ){
          break;
      }

    }

    return types;
  }

  /**
   * Prints menu options to user.
   * @param directed // Graph type.
   * @return // Option selected by user.
   */
  private static int generalMenu(boolean directed){

    // Initialize variables.
    String option = "";
    int intOp = 0;
    Scanner scanner = new Scanner(System.in);

    while (true){
      // General Option
      System.out.println("Indique una opción a realizar: ");
      System.out.println("0  --> Salir");      
      System.out.println("1  --> Número de vértices");
      System.out.println("2  --> Número de lados");
      System.out.println("3  --> Agregar Vértice");
      System.out.println("4  --> Obtener Vértice");
      System.out.println("5  --> Verificar Vértice");
      System.out.println("6  --> Verificar Lado");
      System.out.println("7  --> Eliminar Vértice");
      System.out.println("8  --> Obtener Vértices");
      System.out.println("9  --> Obtener Lados");
      System.out.println("10 --> Grado de un Vértice");
      System.out.println("11 --> Vértices Adyacentes");
      System.out.println("12 --> Clonar Grafo");
      System.out.println("13 --> Respresentación en Caracteres");

      // Directed
      if (directed){
        System.out.println("14 --> Agregar Arco");
        System.out.println("15 --> Eliminar Arco");
        System.out.println("16 --> Obtener Arco");
        System.out.println("17 --> Grado Interior");
        System.out.println("18 --> Grado Exterior");
        System.out.println("19 --> Sucesores");
        System.out.println("20 --> Predecesores");
      }
      // Undirected
      else { 
        System.out.println("14 --> Agregar Arista");
        System.out.println("15 --> Eliminar Arista");
        System.out.println("16 --> Obtener Arista");
      }

      // Gets input from user.
      option = scanner.nextLine();

      // Parses input to int.
      try {
        intOp = Integer.parseInt(option);
        int upperLimit = 13;

        // Directed
        if(directed){
          upperLimit = 20;
        }
        // Undirected
        else {
          upperLimit = 16;
        }

        // Valid option
        if(intOp >= 0 && intOp <= upperLimit){
          break;
        } else {
          throw new Exception();
        }

      } catch (Exception e) {
        // Prints user message for invalid option.
        System.out.println("Por favor, ingrese una opción válida.");
      }

    }

    return intOp;
  }

  /**
   * Runs the application.
   * @param args // User params.
   */
  public static void main(String[] args) {

    // Checks inserted params.
    if(args.length < 1){      
      // No file provided.
      try {
        emptyGraph();
      } catch (Exception e) {
        System.out.println("Main Error: " + e.getMessage());
      } 
    } else {
      // File provided.
      try {
        readFile(args[0]);
      } catch (Exception e) {
        System.out.println("Main Error: " + e.getMessage());
      } 
    }

  }

}