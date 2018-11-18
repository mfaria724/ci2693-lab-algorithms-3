import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class EncontrarAgua {

  public static void main(String[] args) throws Exception{

    // args[0] graph file
    // args[1] cases file
    // args[2] building name
    // args[3] number of people
    
    if(args.length > 3){

      try {
        UndirectedGraph graph = readGraph(args[0]);
        int people = Integer.parseInt(args[3]);
        System.out.println("Cantidad de personas: " + people);
        readCases(args[1], graph, args[2], people);
      } catch (NumberFormatException e) {
        System.out.println("Cantidad de personas inválida. Recuerde que la cantidad de personas debe ser un número entero.");
        System.exit(0);
      }// } catch (Exception e) {
      //   //TODO: handle exception
      // }

    }else{
      System.out.println("Uso: java EncontrarAgua <campus> <casos> <edif> <personas>");
    }

  }

  public static UndirectedGraph readGraph(String file){

    UndirectedGraph graph = new UndirectedGraph();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));

      String line;
      line = reader.readLine();
      int vertices = Integer.parseInt(line);
      line = reader.readLine();
      int edges = Integer.parseInt(line);
      
      for(int i = 0; i < vertices; i++){
        line = reader.readLine();
        String[] data = line.split(" ");
        int capacity = Integer.parseInt(data[1]);
        int floors = Integer.parseInt(data[2]);
        graph.addVertex(data[0], capacity, floors);
      }

      for(int i = 0; i < edges; i++){

        line = reader.readLine();
        String[] data = line.split(" ");
        int capacity = Integer.parseInt(data[2]);
        double distance = Double.parseDouble(data[3]);

        graph.addSimpleEdge(Integer.toString(i), capacity, distance, data[0], data[1]);
      }

      reader.close();

      System.out.println(graph.toString());

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

  public static void readCases(String file, UndirectedGraph graph, String origin, int people) throws Exception{

    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));      
      UndirectedGraph cloneGraph;
      String line;
      String caseId = reader.readLine();
      while(caseId != null){
        int cleanBathrooms = 0;

        System.out.println("Caso: " + caseId);
        cloneGraph = graph.clone();

        line = reader.readLine();
        int buildings = Integer.parseInt(line);
        line = reader.readLine();
        int afectedWays = Integer.parseInt(line);

        String[] buildingNames = new String[cloneGraph.numVertices()];
        Arrays.fill(buildingNames, "NAN");

        ArrayList<Vertex> vertices = cloneGraph.vertices();
        for(int i = 0; i < vertices.size(); i++){
          buildingNames[i] = vertices.get(i).getId();
        }

        // System.out.println("Edificios funcionando: " + buildings);
        for(int i = 0; i < buildings; i++){
          line = reader.readLine();
          String[] data = line.split(" ");
          
          // System.out.println("Existe edificio: " + data[0]);
          // System.out.println("Index edificio: " + cloneGraph.getVertexIndex(data[0]));

          cleanBathrooms += cloneGraph.getVertex(data[0]).getCapacity();
          buildingNames[cloneGraph.getVertexIndex(data[0])] = "HAS WATER";

          if(data.length == 2){
            int floorModification = 0;
            if(data.length > 1){
              floorModification += Integer.parseInt(data[1]);
            }

            cloneGraph.editVertexFloor(data[0], floorModification);
          }

          // System.out.println("Edificio Funcional: " + data[0]);
        }

        // System.out.println("Arreglo de existencia: ");
        // String ll = "[";
        // for(int i = 0; i < exists.length; i++){
        //   ll += exists[i] + ",";
        // }
        // System.out.println(ll);
        // System.out.println("Arreglo de nombres: ");
        // String ll = "[";
        // for(int i = 0; i < buildingNames.length; i++){
        //   ll += buildingNames[i] + ",";
        // }
        // System.out.println(ll);

        for(int i = 0; i < buildingNames.length; i++){
          if(!buildingNames[i].equals("HAS WATER")){
            cloneGraph.setVertexCapacity(buildingNames[i], 0);
          }
        }

        for(int i = 0; i < afectedWays; i++){
          line = reader.readLine();
          cloneGraph.deleteSimpleEdge(line);
        }

        line = reader.readLine();
        caseId = reader.readLine();

        cloneGraph.applyBellmanFord(origin, people, cleanBathrooms);
        // System.out.println(cloneGraph.toString());

      }


    } catch (FileNotFoundException e){
      System.out.println("El archivo especificado para los casos no existe. Intente de nuevo.");
      System.exit(0);
    } catch (NumberFormatException e){
      System.out.println("Formato de archivo inválido. Revise el archivo de casos e intente de nuevo. " + e.getMessage());
      System.out.println("Exception: " + e.getClass());
      System.exit(0);
    }// } catch (Exception e) {
    //   System.out.println("Finalizo en readCases. " + e.getMessage());
    //   System.exit(0);
    // }
      
    }
}