// File read
import java.io.BufferedReader;
import java.io.FileReader;

// Exceptions
import java.io.FileNotFoundException;
import java.io.IOException;


public class Mikado {

  public static void main(String[] args) {
    if(args.length < 1){
      System.out.println("Uso: java Mikado <archivo>");
    }else{
      readFile(args[0]);
    }    
  }

  private static void readFile(String path){

    // Variables Declaration
    BufferedReader Lector;   
    String line;
    String[] data;
    int n;
    int m;
    Graph graph;

    try {
      // Lector initialization
      Lector = new BufferedReader(new FileReader(path));
      
      
      // Iterates until a 0 0 line has been readed.
      while(true){

      
        // Variables initialization.
        line = Lector.readLine();
        data = line.split(" ");

        // Finish program instruction.
        if(data[0].equals("0") && data[1].equals("0")){
          System.exit(0);
        }

        // Quantities
        n = Integer.parseInt(data[0]);
        m = Integer.parseInt(data[1]);

        // Graph Initialization
        graph = new Graph(n);

        // Add all connections
        for(int i = 0; i < m; i++){

          // Read vertices
          line = Lector.readLine();
          data = line.split(" ");
          int v1 = Integer.parseInt(data[0]) - 1;
          int v2 = Integer.parseInt(data[1]) - 1;

          // Adds connection
          graph.addConnection(v1, v2);

        }

        graph.DFS();

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
    }// catch (Exception e) {
    //   // Unknown Error
    //   System.out.println("Ha ocurrido un error desconocido. Mensaje: " + e.getMessage());
    //   System.exit(0);
    // }

  }

}