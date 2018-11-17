import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manuel Faria 15-10463
 * Class for main program
 */
public class CaminoRomano {
  
  public static void readFile(String file){

    try {

      // Variables initialization
      BufferedReader Lector = new BufferedReader(new FileReader(file));
      String line = Lector.readLine();
      // Number of cases
      int cases = Integer.parseInt(line);
      line = Lector.readLine();

      // Loop for cases.
      for(int i = 0; i < cases; i++){

        line = Lector.readLine();
        String[] data = line.split(" ");
        int ways = Integer.parseInt(data[0]);
        int queries = Integer.parseInt(data[1]);

        // Cities
        ArrayList<String> cities = new ArrayList<String>();
        // Ways
        ArrayList<String> edges = new ArrayList<String>();

        for(int j = 0; j < ways; j++){
          line = Lector.readLine();

          // Add new way.
          edges.add(line);

          // Get final and initial city of an edge.
          String[] ends = line.split(" ");

          // Check if there are already cities with that name
          boolean[] exists = new boolean[2];
          Arrays.fill(exists, false);

          for(int k = 0; k < cities.size(); k++){
            if(cities.get(k).equals(ends[0])){
              exists[0] = true;
            }
            if(cities.get(k).equals(ends[1])){
              exists[1] = true;              
            }
            if(exists[0] && exists[1]){
              break;
            }
          }

          // If cities didn't exist, add them.
          if(!exists[0]){
            cities.add(ends[0]);
          }
          if(!exists[1]){
            cities.add(ends[1]);
          }

        }

        // Initialize graph.
        Graph graph = new Graph(cities, edges);

        // Run floyd warhsall to get a minimun cost way between two cities.
        graph.floydWarshall(cities);

        // Prints result for all queries.
        for(int j = 0; j < queries; j++){
          line = Lector.readLine();
          String[] ends = line.split(" ");
          graph.printWay(cities, ends);
        }

      }

    } catch (Exception e) {
      System.out.println("Ha ocurrido un error: " + e.getClass());
    }

  }

  public static void main(String[] args) {

    if(args.length == 0){
      System.out.println("Uso: java CaminoRomano <archivo>");
    }else{
      readFile(args[0]);
    }

  }

}