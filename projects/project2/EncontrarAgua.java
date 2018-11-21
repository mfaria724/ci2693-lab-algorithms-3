import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class EncontrarAgua {

  /**
   * Main method to execute the program.
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception{

    // args[0] graph file
    // args[1] cases file
    // args[2] building name
    // args[3] number of people
    
    // Checks that all requisites had been provided. 
    if(args.length > 3){

      try {

        // Initialize variables
        UndirectedGraph graph = readGraph(args[0]);
        int people = Integer.parseInt(args[3]);

        // Read all cases and prints result.
        readCases(args[1], graph, args[2], people);

      } catch (NumberFormatException e) {
        System.out.println("Cantidad de personas inválida. Recuerde que la cantidad de personas debe ser un número entero.");
        System.exit(0);
      } catch (Exception e) {
        System.out.println("Ha ocurrido un error.");
        System.exit(0);
      }

    }else{
      System.out.println("Uso: java EncontrarAgua <campus> <casos> <edif> <personas>");
    }

  }

  /**
   * Method to read campus file.
   * @param file // File that contains campus graph.
   * @return
   */
  public static UndirectedGraph readGraph(String file){

    // Graph initialization.
    UndirectedGraph graph = new UndirectedGraph();

    try {

      // Lector
      BufferedReader reader = new BufferedReader(new FileReader(file));

      // Number of buildings and edges.
      String line;
      line = reader.readLine();
      int vertices = Integer.parseInt(line);
      line = reader.readLine();
      int edges = Integer.parseInt(line);
      
      // Adds buildings
      for(int i = 0; i < vertices; i++){
        line = reader.readLine();
        String[] data = line.split(" ");
        int capacity = Integer.parseInt(data[1]);
        int floors = Integer.parseInt(data[2]);
        graph.addVertex(data[0], capacity, floors);
      }

      // Adds edges.
      for(int i = 0; i < edges; i++){

        line = reader.readLine();
        String[] data = line.split(" ");
        int capacity = Integer.parseInt(data[2]);
        double distance = Double.parseDouble(data[3]);

        graph.addSimpleEdge(Integer.toString(i), capacity, distance, data[0], data[1]);
      }

      // Closes reader.
      reader.close();

    } catch (FileNotFoundException e) {
      System.out.println("El archivo especificado para el campus no existe. Intente de nuevo.");
      System.exit(0);
    } catch (Exception e) {
      System.out.println("Finalizo en readGraph. " + e.getMessage());
      System.exit(0);
    }

    System.out.println("El archivo del campus se ha leido correctamente.");
    return graph;

  }

  /**
   * Method to read cases file.
   * @param file // File that cotains all cases.
   * @param graph // Campus graph.
   * @param origin // Initial building.
   * @param people //  Quantity of people to be moved.
   * @throws Exception
   */
  public static void readCases(String file, UndirectedGraph graph, String origin, int people) throws Exception{

    try {

      // Variables initialization.
      BufferedReader reader = new BufferedReader(new FileReader(file));      
      UndirectedGraph cloneGraph;
      String line;
      String caseId = reader.readLine();

      // Reads cases until there is no more cases.
      while(caseId != null){

        // Initialize variables.
        int cleanBathrooms = 0;

        // Prints case id.
        System.out.println("\nCaso: " + caseId);
        
        // Creates a new graph.
        cloneGraph = graph.clone();

        // Get all graph modifications.
        line = reader.readLine();
        int buildings = Integer.parseInt(line);
        line = reader.readLine();
        int afectedWays = Integer.parseInt(line);

        // Initialization to buildings that are going to be deleted.
        String[] buildingNames = new String[cloneGraph.numVertices()];
        Arrays.fill(buildingNames, "NAN");

        // Get all building names.
        ArrayList<Vertex> vertices = cloneGraph.vertices();
        for(int i = 0; i < vertices.size(); i++){
          buildingNames[i] = vertices.get(i).getId();
        }

        // Saves wich building has water.
        for(int i = 0; i < buildings; i++){
          
          line = reader.readLine();
          String[] data = line.split(" ");
          
          cleanBathrooms += cloneGraph.getVertex(data[0]).getCapacity();
          buildingNames[cloneGraph.getVertexIndex(data[0])] = "HAS WATER";

          // Changes distance if floor has changed.
          if(data.length == 2){
            int floorModification = 0;
            if(data.length > 1){
              floorModification += Integer.parseInt(data[1]);
            }

            cloneGraph.editVertexFloor(data[0], floorModification);
          }

        }

        // Change capacity for buildings that do not have avalible bathrooms.
        for(int i = 0; i < buildingNames.length; i++){
          if(!buildingNames[i].equals("HAS WATER")){
            cloneGraph.setVertexCapacity(buildingNames[i], 0);
          }
        }

        // Deletes edges that are not avalible.
        for(int i = 0; i < afectedWays; i++){
          line = reader.readLine();
          cloneGraph.deleteSimpleEdge(line);
        }

        // Reads new case id.
        line = reader.readLine();
        caseId = reader.readLine();

        // Applies Bellman Ford to get all avalible ways.
        cloneGraph.applyBellmanFord(origin, people, cleanBathrooms);

      }


    } catch (FileNotFoundException e){
      System.out.println("El archivo especificado para los casos no existe. Intente de nuevo.");
      System.exit(0);
    } catch (NumberFormatException e){
      System.out.println("Formato de archivo inválido. Revise el archivo de casos e intente de nuevo. " + e.getMessage());
      System.out.println("Exception: " + e.getClass());
      System.exit(0);
    } catch (Exception e) {
      System.out.println("Finalizo en readCases. " + e.getMessage());
      System.exit(0);
    }
      
  }
}