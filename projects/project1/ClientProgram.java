import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.NoSuchFileException;

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

  public static void main(String[] args) {

    if(args.length < 1){
      System.out.println("Graph won't be read from file."); // <-------------------QUITAR

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