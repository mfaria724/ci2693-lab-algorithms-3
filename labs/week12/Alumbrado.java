// File read
import java.io.BufferedReader;
import java.io.FileReader;

// Exceptions
import java.io.FileNotFoundException;
import java.io.IOException;


public class Alumbrado{

  private static Graph readFile(String path){

    // Variables Declaration
    BufferedReader Lector;   
    String line;
    String[] data;
    int n;
    int m;
    Graph graph = new Graph();

    try {
      // Lector initialization
      Lector = new BufferedReader(new FileReader(path));
      
      // Variables initialization.
      line = Lector.readLine();
      data = line.split(" ");

      // N, M
      n = Integer.parseInt(data[0]);
      m = Integer.parseInt(data[1]);

      // Graph Initialization.
      graph = new Graph(n, m);

      // Get edges and adds them to the graph.
      for(int i=0; i < m; i++){
        line = Lector.readLine();
        data = line.split(" ");
        graph.graphCost += Integer.parseInt(data[2]);
        graph.addEdge(i, data[0],data[1], data[2]);
      }

      // Checks file format
      line = Lector.readLine();
      if(!line.equals("0 0")){
        throw new NumberFormatException();
      }
        
    } catch (FileNotFoundException e) {
        // File doesn't exist
        System.out.println("El archivo especificado no existe. Intente de nuevo.");
        System.exit(0);
    } catch (IOException e) {
        // I/O Error
        System.out.println("Ha ocurrido un error de E/S. Intente de nuevo.");
        System.exit(0);
    } catch (NumberFormatException e) {
        // Invalid file format
        System.out.println("Formato de archivo incorrecto. Intente de nuevo.");
        System.exit(0);
    } catch (Exception e) {
        // Unknown Error
        System.out.println("Formato de archivo incorrecto. Intente de nuevo.");
        System.exit(0);
    }
    
    return graph;
  }

  /**
   * Main program
   * @param args
   */
  public static void main(String[] args) {
    // Check filename has been passed as parameter. 
    if(args.length < 1){
      System.out.println("Uso: java Alumbrado <archivo>");
    }else{
      Graph graph = readFile(args[0]);
      
      graph.kruskal();
    }    
  }
}
