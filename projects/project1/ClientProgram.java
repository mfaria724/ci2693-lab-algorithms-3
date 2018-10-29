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

    // Initialice BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));
        
    // Gets types.
    String[] types =  new String[2];
    types[0] = reader.readLine();
    types[1] = reader.readLine();
    types[2] = reader.readLine();

    // Gets quantity of vertices and edges.
    String line = reader.readLine();
    int numVertices = Integer.parseInt(line);
    line = reader.readLine();    
    int numEdges = Integer.parseInt(line);

    // System.out.println("Vertices type: " + typeVertex);
    // System.out.println("Edges Type: " + typeEdge);
    // System.out.println("Graph Type: " + typeGraph);
    System.out.println("Num Vertices: " + numVertices);
    System.out.println("Num Edges: " + numEdges);

    // Declares graph
    Graph graph = graphInitialization(types); 
    
    // new DirectedGraph<String, String>();

    // vTrans = new StringTransformer();
    // eTrans= new StringTransformer();


    // // Graph Initialization
    // if(typeGraph.equals("D")){ // Directed Graph

    //   System.out.println("DirectedGraph will be read."); // <-------------------QUITAR

    //   if(typeVertex.equals("B")){ // Boolean Vertex

    //     if(typeEdge.equals("B")){ // Boolean Edge
    //       System.out.println("DirectedGraph<B,B>"); // <-------------------QUITAR
    //       vTrans = new BooleanTransformer();
    //       eTrans = new BooleanTransformer();
    //       graph = new DirectedGraph<Boolean, Boolean>();
    //     }else if(typeEdge.equals("D")){ // Double Edge
    //       vTrans = new BooleanTransformer();
    //       eTrans = new DoubleTransformer();
    //       graph = new DirectedGraph<Boolean, Double>();
    //     }else if(typeEdge.equals("S")){ // String Edge
    //       vTrans = new BooleanTransformer();
    //       graph = new DirectedGraph<Boolean, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else if(typeVertex.equals("D")){ // Double Vertex

    //     if(typeEdge.equals("B")){ // Boolean Edge
    //       vTrans = new DoubleTransformer();
    //       eTrans = new BooleanTransformer();
    //       graph = new DirectedGraph<Double, Boolean>();
    //     }else if(typeEdge.equals("D")){ // Double Edge
    //       vTrans = new DoubleTransformer();
    //       eTrans = new DoubleTransformer();
    //       graph = new DirectedGraph<Double, Double>();
    //     }else if(typeEdge.equals("S")){ // String Edge
    //       vTrans = new DoubleTransformer();
    //       graph = new DirectedGraph<Double, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else if(typeVertex.equals("S")){ // String Vertex

    //     if(typeEdge.equals("B")){ // Boolean Edge
    //       eTrans = new BooleanTransformer();
    //       graph = new DirectedGraph<String, Boolean>();
    //     }else if(typeEdge.equals("D")){ // Double Edge
    //       eTrans = new DoubleTransformer();
    //       graph = new DirectedGraph<String, Double>();
    //     }else if(typeEdge.equals("S")){ // String Edge
    //       graph = new DirectedGraph<String, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else { // Invalid Format
    //     throw new Exception("Invalid file format");
    //   }

    // }//else if (typeGraph.equals("N")) { // Undirected Graph

    //   System.out.println("UndirectedGraph will be read."); // <-------------------QUITAR

    //   if(typeVertex.equals("B")){

    //     if(typeEdge.equals("B")){ // Boolean Edge
    //       graph = new UndirectedGraph<Boolean, Boolean>();
    //     }else if(typeEdge.equals("D")){ // Double Edge
    //       graph = new UndirectedGraph<Boolean, Double>();
    //     }else if(typeEdge.equals("S")){ // String Edge
    //       graph = new UndirectedGraph<Boolean, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else if(typeVertex.equals("D")){

    //     if(typeEdge.equals("B")){ // Boolean Edge
    //       graph = new UndirectedGraph<Double, Boolean>();
    //     }else if(typeEdge.equals("D")){ // Double Edge
    //       graph = new UndirectedGraph<Double, Double>();
    //     }else if(typeEdge.equals("S")){ // String Edge
    //       graph = new UndirectedGraph<Double, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else if(typeVertex.equals("S")){

    //     if(typeEdge.equals("B")){ // Boolean Edge
    //       graph = new UndirectedGraph<String, Boolean>();
    //     }else if(typeEdge.equals("D")){ // Double Edge
    //       graph = new UndirectedGraph<String, Double>();
    //     }else if(typeEdge.equals("S")){ // String Edge
    //       graph = new UndirectedGraph<String, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else { // Invalid Format
    //     throw new Exception("Invalid file format");
    //   }

    // }else { // Invalid Format
    //   throw new Exception("Invalid file format");
    // }

    graph.loadGraph(file, numVertices, numEdges, vTrans, eTrans);
    
    System.out.println("Representación en String del Grafo.");
    System.out.println(graph.toString());

  }

  private static void emptyGraph() throws Exception{
    
    mainMenu();
    firstMenu();
    String[] types = typesMenu();

    Graph graph = graphInitialization(types);

    boolean directed = false;
    if(types[0].equals("D") || types[0].equals("d")){
      directed = true;
    }
    
    String option = "";
    while(!option.equals("0")){
      option = generalMenu(directed);
      boolean result = doOption(option, directed, graph);
    }

    graph.toString();

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

  private static boolean doOption(String option, boolean directed, Graph graph){

    Scanner scanner = new Scanner(System.in);

    String[] input = new String[5];
    String id;
    Object data;
    Double weight;


    if(option.equals("1")){
      Integer result = graph.numVertices();
      System.out.println("Número de Vértices: " + result);
    }else if(option.equals("2")){
      Integer result = graph.numEdges();
      System.out.println("Número de Lados: " + result);
    }else if(option.equals("3")){
      System.out.print("Por favor, introduzca el identificador del vértice que desea agregar: ");
      input[0] = scanner.nextLine();
      id = input[0];

      while(true){
        System.out.println("Por favor, introduzca el dato del vértice que desea agregar: ");
        System.out.println("Recuerde que debe tener el tipo previamente especificado");
        input[1] = scanner.nextLine();
      
        try {
          data = vTrans.transform(input[1]);
          break;
        } catch (NumberFormatException e) {
          System.out.println("Por favor, introduzca un dato del tipo correcto.");
        }
      }
      while(true){
        System.out.print("Por favor, introduzca el peso del vértice que desea agregar: ");
        input[2] = scanner.nextLine();

        try {
          weight = Double.parseDouble(input[2]);
          break;
        } catch (NumberFormatException e) {
          System.out.println("Por favor, introduzca un dato del tipo correcto.");
        }
      }

      System.out.println("Id: " + id);
      System.out.println("Data: " + data);
      System.out.println("Weight: " + weight);

      System.out.println(graph.addVertex(id, data, weight));

    }else if(option.equals("4")){
      System.out.print("Por favor, introduzca el identificador del vértice que desea buscar: ");
      input[0] = scanner.nextLine();

      try {
        Vertex<?> v = graph.getVertex(input[0]);
        System.out.println("Vértice encontrado");
        System.out.println("Id: " + v.getId());
        System.out.println("Dato: " + v.getData());
        System.out.println("Peso: " + v.getWeight());
      } catch (NoSuchElementException e) {
        System.out.println("No existe ningún vértice con ese identificador.");
      }

    }else if(option.equals("5")){
      System.out.print("Por favor, introduzca el identificador del vértice que desea buscar: ");
      input[0] = scanner.nextLine();

      if(graph.containsVertex(input[0])){
        System.out.println("El vértice de identificador " + input[0] + " pertenece al grafo.");
      }else {
        System.out.println("No existe ningún vértice con el identificador especificado.");
      }

    }else if(option.equals("6")){
      
    }else if(option.equals("7")){
      System.out.print("Por favor, introduzca el identificador del vértice que desea buscar: ");
      input[0] = scanner.nextLine();

      if(graph.deleteVertex(input[0])){
        System.out.println("El vértice de identificador " + input[0] + " fue eliminado.");
      }else {
        System.out.println("No existe ningún vértice en el grafo con el identificador especificado o");
      }
      
    }else if(option.equals("8")){
      ArrayList<Vertex<?>> vertices = graph.vertices();
      System.out.println("Se imprimirán los vértices en el siguiente formato: ");
      System.out.println("Id Dato Peso");
      for(int i = 0; i < vertices.size(); i++){
        Vertex<?> v = vertices.get(i);
        System.out.println(v.getId() + " " + v.getData() + " " + v.getWeight());
      }
    }else if(option.equals("9")){
      if(directed){
        ArrayList<DirectedEdge<?>> edges = graph.edges();
        System.out.println("Se imprimirán los arcos en el siguiente formato: ");
        System.out.println("Id Dato Peso IdVerticeInicial IdVerticeFinal");
        for(int i = 0; i < edges.size(); i++){
          DirectedEdge<?> e = edges.get(i);
          System.out.println(e.getId() + " " + e.getData() + " " + e.getWeight() + " " + e.getInitialEnd() + " " + e.getFinalEnd());
        }  
      }else {

      } 
    }else if(option.equals("10")){
      
    }else if(option.equals("11")){
      
    }else if(option.equals("12")){
      
    }else if(option.equals("13")){
      
    }else if(option.equals("14")){
      
    }else if(option.equals("15")){
      
    }else if(option.equals("16")){
      
    }else if(option.equals("17")){
      
    }
    return true;
  }

  private static Graph graphInitialization(String[] types) throws Exception{

    // Declares graph
    Graph graph = new DirectedGraph<String, String>();

    vTrans = new StringTransformer();
    eTrans = new StringTransformer();
    
    // Graph Initialization
    if(types[0].equals("D")){ // Directed Graph

      System.out.println("DirectedGraph will be read."); // <-------------------QUITAR

      if(types[1].equals("B")){ // Boolean Vertex

        if(types[2].equals("B")){ // Boolean Edge
          System.out.println("DirectedGraph<B,B>"); // <-------------------QUITAR
          vTrans = new BooleanTransformer();
          eTrans = new BooleanTransformer();
          graph = new DirectedGraph<Boolean, Boolean>();
        }else if(types[2].equals("D")){ // Double Edge
          System.out.println("DirectedGraph<B,D>"); // <-------------------QUITAR
          vTrans = new BooleanTransformer();
          eTrans = new DoubleTransformer();
          graph = new DirectedGraph<Boolean, Double>();
        }else if(types[2].equals("S")){ // String Edge
          System.out.println("DirectedGraph<B,S>"); // <-------------------QUITAR
          vTrans = new BooleanTransformer();
          graph = new DirectedGraph<Boolean, String>();
        }else { // Invalid Format
          throw new Exception("Invalid file format");
        }

      }else if(types[1].equals("D")){ // Double Vertex

        if(types[2].equals("B")){ // Boolean Edge
          System.out.println("DirectedGraph<D,B>"); // <-------------------QUITAR
          vTrans = new DoubleTransformer();
          eTrans = new BooleanTransformer();
          graph = new DirectedGraph<Double, Boolean>();
        }else if(types[2].equals("D")){ // Double Edge
          System.out.println("DirectedGraph<D,D>"); // <-------------------QUITAR
          vTrans = new DoubleTransformer();
          eTrans = new DoubleTransformer();
          graph = new DirectedGraph<Double, Double>();
        }else if(types[2].equals("S")){ // String Edge
          System.out.println("DirectedGraph<D,S>"); // <-------------------QUITAR
          vTrans = new DoubleTransformer();
          graph = new DirectedGraph<Double, String>();
        }else { // Invalid Format
          throw new Exception("Invalid file format");
        }

      }else if(types[1].equals("S")){ // String Vertex

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
          graph = new DirectedGraph<String, String>();
        }else { // Invalid Format
          throw new Exception("Invalid file format");
        }

      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }//else if (types[0].equals("N")) { // Undirected Graph

    //   System.out.println("UndirectedGraph will be read."); // <-------------------QUITAR

    //   if(types[1].equals("B")){

    //     if(types[2].equals("B")){ // Boolean Edge
    //       graph = new UndirectedGraph<Boolean, Boolean>();
    //     }else if(types[2].equals("D")){ // Double Edge
    //       graph = new UndirectedGraph<Boolean, Double>();
    //     }else if(types[2].equals("S")){ // String Edge
    //       graph = new UndirectedGraph<Boolean, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else if(types[1].equals("D")){

    //     if(types[2].equals("B")){ // Boolean Edge
    //       graph = new UndirectedGraph<Double, Boolean>();
    //     }else if(types[2].equals("D")){ // Double Edge
    //       graph = new UndirectedGraph<Double, Double>();
    //     }else if(types[2].equals("S")){ // String Edge
    //       graph = new UndirectedGraph<Double, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else if(types[1].equals("S")){

    //     if(types[2].equals("B")){ // Boolean Edge
    //       graph = new UndirectedGraph<String, Boolean>();
    //     }else if(types[2].equals("D")){ // Double Edge
    //       graph = new UndirectedGraph<String, Double>();
    //     }else if(types[2].equals("S")){ // String Edge
    //       graph = new UndirectedGraph<String, String>();
    //     }else { // Invalid Format
    //       throw new Exception("Invalid file format");
    //     }

    //   }else { // Invalid Format
    //     throw new Exception("Invalid file format");
    //   }

    // }else { // Invalid Format
    //   throw new Exception("Invalid file format");
    // }

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

  private static String generalMenu(boolean directed){

    String option = "";
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

      // Undirected
      if (directed){
        System.out.println("14 --> Agregar Arco");
        System.out.println("15 --> Eliminar Arco");
        System.out.println("16 --> Obtener Arco");
        System.out.println("17 --> Grado Interior");
        System.out.println("18 --> Grado Exterior");
        System.out.println("19 --> Sucesores");
        System.out.println("20 --> Predecesores");
      }
      // Directed
      else { 
        System.out.println("14 --> Agregar Arista");
        System.out.println("15 --> Eliminar Arista");
        System.out.println("16 --> Obtener Arista");
      }

      Scanner scanner = new Scanner(System.in);
      option = scanner.nextLine();

      try {
        int intOp = Integer.parseInt(option);
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

    return option;
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