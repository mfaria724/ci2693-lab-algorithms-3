import java.io.BufferedReader;
import java.io.FileReader;

public class Planificacion {

  // Starts Application
  public static void main(String[] args) throws Exception {
    if(args.length < 1){
      System.out.println("Uso: java Planificacion <archivo>");
    }else {
      readFile(args[0]);
    }
  }

  // Reads file and prints results.
  public static void readFile(String path)throws Exception{

      BufferedReader Lector; 
      Lector = new BufferedReader(new FileReader(path));
      String line = Lector.readLine();
      Graph graph = new Graph();

      // Cases
      while(!line.equals("0")){

        graph = new Graph();

        // Lines in case
        while(!line.equals("0")){
          
          String[] edges = line.split(" ");
          int v1 = Integer.parseInt(edges[0]) - 1;

          // Al edges in a line
          for(int i = 1; i < edges.length - 1; i ++){
            String direction = edges[i].substring(edges[i].length() - 1);

            // Directed Edge
            if(direction.equals("u") || direction.equals("d")){
              int v2 = Integer.parseInt(edges[i].substring(0, edges[i].length() - 1)) - 1;

              if (direction.equals("u")){
                graph.addDirectedEdge(v2, v1);
              }else if(direction.equals("d")){
                graph.addDirectedEdge(v1, v2);
              }
              
            }
            // UndirectedEdge
            else{
              int v2 =  Integer.parseInt(edges[i]) - 1;
              graph.addDirectedEdge(v1,v2);
              graph.addDirectedEdge(v2,v1);
            }

          }

          // Next Line          
          line = Lector.readLine();
        }

        // Checks if there is a result.
        graph.printResults(graph.graph);
        line = Lector.readLine();

      }
    

  }

} 