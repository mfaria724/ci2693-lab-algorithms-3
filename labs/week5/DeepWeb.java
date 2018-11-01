import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main class to implement dfs and fbs over graphs.
 */
public class DeepWeb {

  /**
   * Reads file to upload graph.
   * @param path Path to file
   * @return Graph with file information loaded.
   */
  public static Graph readFile(String path){

    // Variables declaration.
    BufferedReader Lector;   
    String line;
    int v;
    int e;
    Graph graph = new Graph(1);

    try {
      // Variables initialization.
      Lector = new BufferedReader(new FileReader(path));
      v = Integer.parseInt(Lector.readLine());  
      e = Integer.parseInt(Lector.readLine());      
      graph = new Graph(v);

      // Add edges.
      for(int i = 0; i < e; i++){
        // Read line.
        line = Lector.readLine();

        // Gets vertices.
        String[] vertices = line.split(" ");
        int iv = Integer.parseInt(vertices[0]);
        int fv = Integer.parseInt(vertices[1]);

        // Adds edge.
        graph.addEdge(iv, fv);
      }

    } catch (FileNotFoundException ex) { // If file doesn't exist.
      System.out.println("El archivo especificado no existe, por favor, introduzca un archivo válido.");
      System.exit(0);
    } catch (IOException ex) { // If an I/O error occurs.
      System.out.println("Ha ocurrido un error de entrada/salida.");
      System.exit(0);
    } catch (NumberFormatException ex) { // If file format is invalid.
      System.out.println("El formato del archivo es incorrecto.");
      System.exit(0);
    } catch (Exception ex) { // If there is a repeated edge.
      if (ex.getMessage() == "Repeated Edge"){
        System.out.println("No se puede cargar grafos con arcos repetidos.");
        System.exit(0);
      }
    }

    // Message to user.
    System.out.println("El grafo fue leido correctamente.\n");
    return graph;

  }

  /**
   * Main method to initialize program.
   * @param args // Options specified above.
   */
  public static void main(String[] args){

    // Variables declaration.
    int o = 0;
    int trunc = 0;
    Boolean[] options;

    // Variables initialization.
    options = new Boolean[5]; // Format: [--trunc, --arb, --ord, --pred, --comp]
    Arrays.fill(options, false);

    // Checks correct program usage.
    if(args.length < 3){
      System.out.println("Uso: $ java DeepWeb <instancia> <origen> <dfs|bfs> [--trunc #] [--arb] [--ord] [--pred] [--comp]");
    }else{

      // Checks correct algorithm specified.
      if(!args[2].equals("bfs") && !args[2].equals("dfs")){
        System.out.println("Por favor, especifique un tipo de recorrido válido (bfs ó dfs).");
        System.exit(0);
      }

      // Checks origen valid format.
      try {
        o = Integer.parseInt(args[1]);      
      } catch (NumberFormatException ex) {
        System.out.println("El origen debe ser un número entero.");
        System.exit(0);
      }

      // Initialize graph.
      Graph graph = readFile(args[0]);

      // Reads options.
      try {
        // Checks others arguments.
        for(int i = 3; i < args.length; i++){
          switch (args[i]){
            case "--trunc": // Trunc option.
              options[0] = true;
              // Gets maximun depth.
              trunc = Integer.parseInt(args[i + 1]);
              i += 1;
              break;
            case "--arb": // Tree option.
              options[1] = true;          
              break;
            case "--ord": // Ordinal option.
              options[2] = true;          
              break;
            case "--pred": // Predecessot option.
              options[3] = true;          
              break;
            case "--comp": // Conex components option.
              options[4] = true;          
          }
        }  
      } catch (Exception ex) { // If trunc option isn't specified.
        System.out.println("Al utilizar la opción --trunc debe especificar la profundidad.");
        System.exit(0);
      } 
      
      // Does specified algorithm
      if(args[2].equals("dfs")){
        graph.dfs(o, options, trunc);
      } else {
        // graph.bfs(o, options, trunc);
      }

    }

  }

}