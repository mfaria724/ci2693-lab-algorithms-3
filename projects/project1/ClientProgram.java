import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.NoSuchFileException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientProgram {

  private static TypeTransformer vTrans;
  private static TypeTransformer eTrans;

  private static void readFile(String file) throws Exception {

    // // Initialice BufferReader
    // BufferedReader reader = new BufferedReader(new FileReader(file));
        
    // // Gets types.
    // String[] types =  new String[3];
    // types[0] = reader.readLine();
    // types[1] = reader.readLine();
    // types[2] = reader.readLine();

    // // Gets quantity of vertices and edges.
    // int[] quantities = new int[2];
    // String line;
    // line = reader.readLine();
    // quantities[0] = Integer.parseInt(line);
    // line = reader.readLine();    
    // quantities[1] = Integer.parseInt(line);

    // System.out.println("Types: " + types.toString());
    // System.out.println("Num Vertices: " + quantities[0]);
    // System.out.println("Num Edges: " + quantities[1]);

    // // Declares graph
    // Graph graph = graphInitialization(types); 

    // graph.loadGraph(file, quantities[0], quantities[1], vTrans, eTrans);
    
    // System.out.println("Representación en String del Grafo.");
    // System.out.println(graph.toString());

  }

  private static void emptyGraph() throws Exception{
    
    mainMenu();
    firstMenu();
    String[] types = typesMenu();

    DirectedGraph<?,?> dirGraph = new DirectedGraph<String, String>();
    UndirectedGraph<?,?> undGraph = new UndirectedGraph<String, String>();;

    // Graph Initialization
    if(types[0].equals("D")){ // Directed Graph
      dirGraph = dirGraphInitialization(types);
    }
    else if (types[0].equals("N")) { // Undirected Graph
      undGraph = undGraphInitialization(types);
    }else { // Invalid Format
      throw new Exception("Invalid file format");
    }

    boolean directed = false;
    if(types[0].equals("D") || types[0].equals("d")){
      directed = true;
    }
    
    int option = -1;
    while(option != 0){
      option = generalMenu(directed);
      boolean result = doOption(option, directed, dirGraph, undGraph);
    }

    // graph.toString();

  };

  private static void firstMenu(){

    while(true){
      System.out.println("Por favor, seleccione una de las siguientes opciones: ");
      System.out.println("1 --> Crear Grafo");
      System.out.println("2 --> Cargar Grafo (El programa terminará)");

      Scanner scanner = new Scanner(System.in);
      String option = scanner.nextLine();

      if(option.equals("2")){
        System.out.println("¡Gracias por usar este programa!");
        System.exit(0);
      }else if(option.equals("1")){
        System.out.println("Wants to create a new Graph!");
        break;
      }
    }

  }

  private static boolean doCommonOption(int option, boolean directed, Graph graph){

    Scanner scanner = new Scanner(System.in);

    String[] input = new String[5];
    String id;
    Object data;
    Double weight;
    String v1;
    String v2;
    Integer result;
    Integer degree;

    System.out.println("Es dirigido: " + (graph instanceof DirectedGraph));
    System.out.println("Es NO dirigido " + (graph instanceof UndirectedGraph));

    switch(option){
      case 1:
        result = graph.numVertices();
        System.out.println("Número de Vértices: " + result);
        break;
      case 2:
        result = graph.numEdges();
        System.out.println("Número de Lados: " + result);
        break;
      case 3:
        id = idInput();
        data = dataInput();
        weight = weightInput();
        if(graph.addVertex(id, data, weight)){
          System.out.println("Vertice añadido correctamente.");
        } else {
          System.out.println("El vertice no ha sido añadido correctamente");
        }
        break;
      case 4:
        id = idInput();
        try {
          System.out.println(graph.getVertex(id).toString());
        } catch (NoSuchElementException e) {
          System.out.println("No existe ningún vértice con ese identificador.");
        }
        break;
      case 5:
        id = idInput();
        if(graph.containsVertex(id)){
          System.out.println("El vértice de identificador " + id + " pertenece al grafo.");
        }else {
          System.out.println("No existe ningún vértice con el identificador especificado.");
        }
        break;
      case 6:
        if(directed){
          System.out.println("Por favor, introduzca el identificador del vertice inicial del arco.");
          v1 = idInput();
          System.out.println("Por favor, introduzca el identificador del vertice final del arco.");
          v2 = idInput();

          if(graph.cotainsEdge(v1, v2)){
            System.out.println("El arco que incide sobre los vertices " + v1 + " y " + v2 + " pertenece al grafo.");
          } else {
            System.out.println("No existe ningún arco que incida sobre los vertices indicados.");
          }

        } else {
          System.out.println("Por favor, introduzca el identificador del primer vertice del lado.");
          v1 = idInput();
          System.out.println("Por favor, introduzca el identificador del segundo vertice del lado.");
          v2 = idInput();

          if(graph.cotainsEdge(v1, v2)){
            System.out.println("El lado que incide sobre los vertices " + v1 + " y " + v2 + " pertenece al grafo.");
          } else {
            System.out.println("No existe ningún lado que incida sobre los vertices indicados.");
          }

        }
        break;
      case 7:
        id = idInput();

        if(graph.deleteVertex(id)){
          System.out.println("El vértice de identificador " + id + " fue eliminado.");
        }else {
          System.out.println("No existe ningún vértice en el grafo con el identificador especificado o");
        }
        break;
      case 8:
        ArrayList<Vertex<?>> vertices = graph.vertices();
        System.out.println("Se imprimirán los vértices en el siguiente formato: ");
        System.out.println("Id Dato Peso");
        for(int i = 0; i < vertices.size(); i++){
          Vertex<?> v = vertices.get(i);
          System.out.println(v.getId() + " " + v.getData() + " " + v.getWeight());
        }
        break;
      case 9:
        if(directed){
          ArrayList<DirectedEdge<?>> edges = graph.edges();
          System.out.println("Se imprimirán los arcos en el siguiente formato: ");
          System.out.println("Id Dato Peso IdVerticeInicial IdVerticeFinal");
          for(int i = 0; i < edges.size(); i++){
            DirectedEdge<?> e = edges.get(i);
            System.out.println(e.getId() + " " + e.getData() + " " + e.getWeight() + " " + e.getInitialEnd() + " " + e.getFinalEnd());
          }  
        }else {
          ArrayList<SimpleEdge<?>> edges = graph.edges();
          System.out.println("Se imprimirán los arcos en el siguiente formato: ");
          System.out.println("Id Dato Peso IdPrimerVertice IdSegundoVertice");
          for(int i = 0; i < edges.size(); i++){
            SimpleEdge<?> e = edges.get(i);
            System.out.println(e.getId() + " " + e.getData() + " " + e.getWeight() + " " + e.getEnd1() + " " + e.getEnd2());
          }
        } 
        break;
      case 10:
        id = idInput();

        try {
          degree = graph.degree(id);
          System.out.println("El grado del vertice de identificador " + id + " es: " + degree);
        } catch (NoSuchElementException e) {
          System.out.println("No existe ningún vértice en el grafo con el identificador especificado.");
        }
        break;
      case 11:
        id = idInput();

        try{ 
          ArrayList<Vertex<?>> adj = graph.neighbourhood(id);
          System.out.println("Se imprimirán los vértices adyacentes en el siguiente formato: ");
          System.out.println("Id Dato Peso");
          for(int i = 0; i < adj.size(); i++){
            Vertex<?> v = adj.get(i);
            System.out.println(v.getId() + " " + v.getData() + " " + v.getWeight());
          }
        } catch (NoSuchElementException e){
          System.out.println("No existe ningún vértice en el grafo con el identificador especificado.");
        }
        break;
      case 12:
        graph = graph.clone();
        System.out.println("Se ha clonando el grafo");
        break;
      case 13:
        System.out.println(graph.toString());
        break;
    }

    return true;
  }
  // private static boolean doOptionDirected(String option, DirectedGraph graph){
  //   return false;
  // }

  // private static boolean doOptionUndirected(String option, UndirectedGraph graph){
  //   return false;
  // }

  private static boolean doOption(int option, boolean directed, DirectedGraph dirGraph, UndirectedGraph undGraph){

    boolean result = false;
    Scanner scanner = new Scanner(System.in);

    String[] input = new String[5];
    String id;
    Object data;
    Double weight;
    String v1;
    String v2;

    if(option < 14){
      if(directed){
        result = doCommonOption(option, directed, dirGraph);
      }else {
        result = doCommonOption(option, directed, undGraph);
      }
    }else {
      switch (option){
        case 14: 
          if(directed){
            id = idInput();
            data = dataInput();
            weight = weightInput();
            v1 = idInput();
            v2 = idInput();

            if(dirGraph.addDirectedEdge(id, data, weight, v1, v2)){
              System.out.println("El arco fue añadido.");
            }else {
              System.out.println("El arco no fue añadido, verifique que los vertices existen.");
            }
          }else {
            id = idInput();
            data = dataInput();
            weight = weightInput();
            v1 = idInput();
            v2 = idInput();

            if(undGraph.addSimpleEdge(id, data, weight, v1, v2)){
              System.out.println("La arista fue añadida.");
            } else {
              System.out.println("La arista no fue añadida.");
            }
          }
          break;
        case 15:
          if(directed){
            id = idInput();

            if(dirGraph.deleteDirectedEdge(id)){
              System.out.println("El arco fue eliminado.");
            }else {
              System.out.println("El arco no fue eliminado, verifique que el arco existe.");
            }
            
          }else {

          }
          break;
        case 16:
          if(directed){
            id = idInput();

            try {
              DirectedEdge<?> edge = dirGraph.getDirectedEdge(id);
              System.out.println("DirectedEdge's id: " + edge.getId());
              System.out.println("DirectedEdge's data: " + edge.getData());
              System.out.println("DirectedEdge's weight: " + edge.getWeight());
              System.out.println("DirectedEdge's Initial End: " + edge.getInitialEnd());
              System.out.println("DirectedEdge's Final End: " + edge.getFinalEnd());
            } catch (NoSuchElementException e) {
              System.out.println("No existe ningún arco con el identificador indicado.");
            }

          }else {

          }
          break;
        case 17:
          break;
        case 18:
          break;
        case 19:
          id = idInput();

          try {
            
          } catch (NoSuchElementException e) {
            System.out.println("No existe ningún vertice con el identificador especificado.");
          }

          break;
        case 20:
          id = idInput();
          
          try {
            
          } catch (NoSuchElementException e) {
            System.out.println("No existe ningún vertice con el identificador especificado.");
          }

          break;
      }
    }

    return result;
  }

  private static String idInput(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Por favor, introduzca el identificador del vértice que desea buscar: ");
    String input = scanner.nextLine();
    return input;
  }

  private static Object dataInput(){
    Scanner scanner = new Scanner(System.in);
    Object data;

    while(true){

      System.out.println("Por favor, introduzca el dato del vértice que desea agregar: ");
      System.out.println("Recuerde que debe tener el tipo previamente especificado");
      String input = scanner.nextLine();
    
      try {
        data = vTrans.transform(input);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Por favor, introduzca un dato del tipo correcto.");
      }
    }

    return data;
  }

  private static Double weightInput(){
    Scanner scanner = new Scanner(System.in);
    Double weight;

    while(true){
      System.out.print("Por favor, introduzca el peso del vértice que desea agregar: ");
      String input = scanner.nextLine();

      try {
        weight = Double.parseDouble(input);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Por favor, introduzca un dato del tipo correcto.");
      }
    }

    return weight;
  }

  private static DirectedGraph dirGraphInitialization(String[] types) throws Exception{

    // Declares graph
    DirectedGraph<?,?> graph = new DirectedGraph<String, String>();

    System.out.println("DirectedGraph will be read."); // <-------------------QUITAR

    if(types[1].equals("B")){ // Boolean Vertex
      vTrans = new BooleanTransformer();

      if(types[2].equals("B")){ // Boolean Edge
        System.out.println("DirectedGraph<B,B>"); // <-------------------QUITAR
        eTrans = new BooleanTransformer();
        graph = new DirectedGraph<Boolean, Boolean>();
      }else if(types[2].equals("D")){ // Double Edge
        System.out.println("DirectedGraph<B,D>"); // <-------------------QUITAR
        eTrans = new DoubleTransformer();
        graph = new DirectedGraph<Boolean, Double>();
      }else if(types[2].equals("S")){ // String Edge
        System.out.println("DirectedGraph<B,S>"); // <-------------------QUITAR
        eTrans = new StringTransformer();

        graph = new DirectedGraph<Boolean, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[1].equals("D")){ // Double Vertex
      vTrans = new DoubleTransformer();

      if(types[2].equals("B")){ // Boolean Edge
        System.out.println("DirectedGraph<D,B>"); // <-------------------QUITAR
        eTrans = new BooleanTransformer();
        graph = new DirectedGraph<Double, Boolean>();
      }else if(types[2].equals("D")){ // Double Edge
        System.out.println("DirectedGraph<D,D>"); // <-------------------QUITAR
        eTrans = new DoubleTransformer();
        graph = new DirectedGraph<Double, Double>();
      }else if(types[2].equals("S")){ // String Edge
        System.out.println("DirectedGraph<D,S>"); // <-------------------QUITAR
        eTrans = new StringTransformer();
        graph = new DirectedGraph<Double, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[1].equals("S")){ // String Vertex
      vTrans = new StringTransformer();
      
      if(types[2].equals("B")){ // Boolean Edge
        System.out.println("DirectedGraph<S,B>"); // <-------------------QUITAR
        eTrans = new BooleanTransformer();
        graph = new DirectedGraph<String, Boolean>();
      }else if(types[2].equals("D")){ // Double Edge
        System.out.println("DirectedGraph<S,D>"); // <-------------------QUITAR
        eTrans = new DoubleTransformer();
        graph = new DirectedGraph<String, Double>();
      }else if(types[2].equals("S")){ // String Edge
        System.out.println("DirectedGraph<S,S>"); // <-------------------QUITAR
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
  
  private static UndirectedGraph undGraphInitialization(String[] types) throws Exception{
    
    // Declares graph
    UndirectedGraph<?,?> graph = new UndirectedGraph<String, String>();

    System.out.println("UndirectedGraph will be read."); // <-------------------QUITAR

    if(types[1].equals("B")){
      vTrans = new BooleanTransformer();


      if(types[2].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new UndirectedGraph<Boolean, Boolean>();
      }else if(types[2].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new UndirectedGraph<Boolean, Double>();
      }else if(types[2].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new UndirectedGraph<Boolean, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[1].equals("D")){
      vTrans = new DoubleTransformer();


      if(types[2].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new UndirectedGraph<Double, Boolean>();
      }else if(types[2].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new UndirectedGraph<Double, Double>();
      }else if(types[2].equals("S")){ // String Edge
        eTrans = new StringTransformer();
        graph = new UndirectedGraph<Double, String>();
      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }else if(types[1].equals("S")){
      vTrans = new StringTransformer();

      if(types[2].equals("B")){ // Boolean Edge
        eTrans = new BooleanTransformer();
        graph = new UndirectedGraph<String, Boolean>();
      }else if(types[2].equals("D")){ // Double Edge
        eTrans = new DoubleTransformer();
        graph = new UndirectedGraph<String, Double>();
      }else if(types[2].equals("S")){ // String Edge
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

  private static void mainMenu(){

    // Welcome
    System.out.println("¡BIENVENIDO! \n");

    // Information
    System.out.println("Si desea cargar un grafo desde un archivo,");
    System.out.println("ejecute este programa como java ClientProgram <nombreArchivo>\n");
    System.out.println("¡IMPORTANTE!: Recuerde asegurarse que el archivo está en la misma carpeta que el prgrama.\n");

  }

  private static String[] typesMenu(){

    String[] types = new String[3];
    Scanner scanner = new Scanner(System.in);

    while(true){
      System.out.println("Por favor, indique el tipo de grafo que desea crear: ");
      System.out.println("D --> Grafo Dirigido");
      System.out.println("N --> Grafo No Dirigido");

      types[0] = scanner.nextLine().toUpperCase();

      if(types[0].equals("N") || types[0].equals("D") ){
          break;
      }

    }

    while(true){
      System.out.println("Por favor, indique el tipo de dato que desea almacenar en los vértices: ");
      System.out.println("B --> Boolean");
      System.out.println("D --> Double");
      System.out.println("S --> String");

      types[1] = scanner.nextLine().toUpperCase();

      if(types[1].equals("B") || types[1].equals("D") || types[1].equals("S") ){
          break;
      }

    }

    while(true){
      System.out.println("Por favor, indique el tipo de dato que desea almacenar en los lados: ");
      System.out.println("B --> Boolean");
      System.out.println("D --> Double");
      System.out.println("S --> String");

      types[2] = scanner.nextLine().toUpperCase();

      if(types[2].equals("B") || types[2].equals("D") || types[2].equals("S") ){
          break;
      }

    }

    return types;
  }

  private static int generalMenu(boolean directed){

    String option = "";
    int intOp = 0;

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

      Scanner scanner = new Scanner(System.in);
      option = scanner.nextLine();

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

        if(intOp >= 0 && intOp <= upperLimit){
          break;
        }

      } catch (Exception e) {
        System.out.println("Por favor, ingrese una opción válida.");
      }

    }

    return intOp;
  }

  public static void main(String[] args) {

    if(args.length < 1){
      System.out.println("Graph won't be read from file."); // <-------------------QUITAR
      
      try {
        
        emptyGraph();
        System.out.println("Graph was created"); // <-------------------QUITAR

      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      } 
      

    } else {
      System.out.println("Graph will be read from file."); // <-------------------QUITAR

      try {
        
        readFile(args[0]);
        System.out.println("Graph was load"); // <-------------------QUITAR

      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      } 
      
    }

  }

}