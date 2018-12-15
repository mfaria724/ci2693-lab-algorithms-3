// File read
import java.io.BufferedReader;
import java.io.FileReader;

// Exceptions
import java.io.FileNotFoundException;
import java.io.IOException;


public class Alumbrado{

    private static void readFile(String path){

        // Variables Declaration
        BufferedReader Lector;   
        String line;
        String[] data;
        int n;
        int m;


    
        try {
          // Lector initialization
          Lector = new BufferedReader(new FileReader(path));
          
            // Variables initialization.
            line = Lector.readLine();
            data = line.split(" ");
    
            // N, M
            n = Integer.parseInt(data[0]);
            m = Integer.parseInt(data[1]);

            DisjointSets sets = new DisjointSets(n);
            Graph graph = new Graph(n, m);

            for(int i=0; i<m; i++){
                line = Lector.readLine();
                data = line.split(" ");
                graph.graphCost += Integer.parseInt(data[2]);
                graph.addEdge(i, Integer.parseInt(data[0]),Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            }
    
            System.out.println(graph.graphCost);

            for (int i=0;i<m;i++){
                System.out.print("\n");
                for(int j=0;j<3;j++){
                    System.out.print(graph.edges[i][j] + ", ");
                }
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
        //   // Unknown Error
          System.out.println("Ha ocurrido un error desconocido. Mensaje: " + e.getMessage());
          System.exit(0);
        }
    
      }

      public static void main(String[] args) {
        if(args.length < 1){
          System.out.println("Uso: java Alumbrado <archivo>");
        }else{
          readFile(args[0]);
        }    
      }
}