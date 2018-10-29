import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class ClientProgram {

  private static void readFile(String file) throws Exception {

    // Initialice BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));
        
    // Gets types.
    String typeVertex = reader.readLine();
    String typeEdge = reader.readLine();
    String typeGraph = reader.readLine();

    // Gets quantity of vertices and edges.
    String line = reader.readLine();
    int numVertices = Integer.parseInt(line);
    line = reader.readLine();    
    int numEdges = Integer.parseInt(line);

    System.out.println("Vertices type: " + typeVertex);
    System.out.println("Edges Type: " + typeEdge);
    System.out.println("Graph Type: " + typeGraph);
    System.out.println("Num Vertices: " + numVertices);
    System.out.println("Num Edges: " + numEdges);

    // Declares graph
    Graph graph = new DirectedGraph<String, String>();

    TypeTransformer verTrans = new StringTransformer();
    TypeTransformer edgeTrans= new StringTransformer();
    
    // Graph Initialization
    if(typeGraph.equals("D")){ // Directed Graph

      System.out.println("DirectedGraph will be read."); // <-------------------QUITAR

      if(typeVertex.equals("B")){ // Boolean Vertex

        if(typeEdge.equals("B")){ // Boolean Edge
          System.out.println("DirectedGraph<B,B>"); // <-------------------QUITAR
          verTrans = new BooleanTransformer();
          edgeTrans = new BooleanTransformer();
          graph = new DirectedGraph<Boolean, Boolean>();
        }else if(typeEdge.equals("D")){ // Double Edge
          verTrans = new BooleanTransformer();
          edgeTrans = new DoubleTransformer();
          graph = new DirectedGraph<Boolean, Double>();
        }else if(typeEdge.equals("S")){ // String Edge
          verTrans = new BooleanTransformer();
          graph = new DirectedGraph<Boolean, String>();
        }else { // Invalid Format
          throw new Exception("Invalid file format");
        }

      }else if(typeVertex.equals("D")){ // Double Vertex

        if(typeEdge.equals("B")){ // Boolean Edge
          verTrans = new DoubleTransformer();
          edgeTrans = new BooleanTransformer();
          graph = new DirectedGraph<Double, Boolean>();
        }else if(typeEdge.equals("D")){ // Double Edge
          verTrans = new DoubleTransformer();
          edgeTrans = new DoubleTransformer();
          graph = new DirectedGraph<Double, Double>();
        }else if(typeEdge.equals("S")){ // String Edge
          verTrans = new DoubleTransformer();
          graph = new DirectedGraph<Double, String>();
        }else { // Invalid Format
          throw new Exception("Invalid file format");
        }

      }else if(typeVertex.equals("S")){ // String Vertex

        if(typeEdge.equals("B")){ // Boolean Edge
          edgeTrans = new BooleanTransformer();
          graph = new DirectedGraph<String, Boolean>();
        }else if(typeEdge.equals("D")){ // Double Edge
          edgeTrans = new DoubleTransformer();
          graph = new DirectedGraph<String, Double>();
        }else if(typeEdge.equals("S")){ // String Edge
          graph = new DirectedGraph<String, String>();
        }else { // Invalid Format
          throw new Exception("Invalid file format");
        }

      }else { // Invalid Format
        throw new Exception("Invalid file format");
      }

    }//else if (typeGraph.equals("N")) { // Undirected Graph

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

    graph.loadGraph(file, numVertices, numEdges, verTrans, edgeTrans);
    
    System.out.println("Representación en String del Grafo.");
    System.out.println(graph.toString());

  }

  private static void emptyGraph(){
    mainMenu();

    while(true){
      String option = firstMenu();

      if(option.equals("1")){
        System.out.println("Wants to create a new Graph!");
        break;
      }
    }

  };

  private static String firstMenu(){

    // Menu
    System.out.println("Por favor, seleccione una de las siguientes opciones: ");
    System.out.println("1 --> Crear Grafo");
    System.out.println("2 --> Cargar Grafo (El programa terminará)");
    Scanner scanner = new Scanner(System.in);
    String option = scanner.nextLine();

    if(option.equals("2")){
      System.out.println("¡Gracias por usar este programa!");
      System.exit(0);
    }

    return option;

  }

  private static void mainMenu(){

    // Welcome
    System.out.println("¡BIENVENIDO! \n");

    // Information
    System.out.println("Si desea cargar un grafo desde un archivo,");
    System.out.println("ejecute este programa como java ClientProgram <nombreArchivo>\n");
    System.out.println("¡IMPORTANTE!: Recuerde asegurarse que el archivo está en la misma carpeta que el prgrama.\n");

  }

  private static void generalMenu(){

    System.out.println("Por favor, indique el tipo de grafo que desea crear: ");
    System.out.println("D --> Grafo Dirigido");
    System.out.println("N --> Grafo No Dirigido");

    Scanner scanner = new Scanner(System.in);
    String typeGraph = scanner.nextLine();

    System.out.println("Por favor, indique el tipo de dato que desea almacenar en los vértices: ");
    System.out.println("B --> Boolean");
    System.out.println("D --> Double");
    System.out.println("S --> String");

    scanner = new Scanner(System.in);
    String typeVertex = scanner.nextLine();

    System.out.println("Por favor, indique el tipo de dato que desea almacenar en los lados: ");
    System.out.println("B --> Boolean");
    System.out.println("D --> Double");
    System.out.println("S --> String");

    scanner = new Scanner(System.in);
    String typeEdge = scanner.nextLine();

    // General Option
    System.out.println("Indique una opción a realizar: ");
    System.out.println("1 --> Número de vértices");
    System.out.println("2 --> Número de lados");
    System.out.println("3 --> Agregar Vértice");
    System.out.println("4 --> Obtener Vértice");
    System.out.println("5 --> Verificar Vértice");
    System.out.println("6 --> Verificar Lado");
    System.out.println("7 --> Eliminar Vértice");
    System.out.println("8 --> Obtener Vértices");
    System.out.println("9 --> Obtener Lados");
    System.out.println("10 --> Grado de un Vértice");
    System.out.println("11 --> Vértices Adyacentes");
    System.out.println("12 --> Clonar Grafo");
    System.out.println("13 --> Respresentación en Caracteres");

    // Undirected
    System.out.println("14 --> Agregar Arista");
    System.out.println("15 --> Eliminar Arista");
    System.out.println("16 --> Obtener Arista");

    // Directed
    System.out.println("17 --> Agregar Arco");
    System.out.println("18 --> Eliminar Arco");
    System.out.println("19 --> Obtener Arco");
    System.out.println("20 --> Grado Interior");
    System.out.println("21 --> Grado Exterior");
    System.out.println("22 --> Sucesores");
    System.out.println("23 --> Predecesores");

  }

  public static void main(String[] args) {

    if(args.length < 1){
      System.out.println("Graph won't be read from file."); // <-------------------QUITAR
      
      emptyGraph();

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