import java.io.BufferedReader;
import java.io.FileReader;
import java.time.chrono.ThaiBuddhistEra;
import java.util.Arrays;

public class EncontrarAgua {

  public static void main(String[] args) {

    // args[0] graph file
    // args[1] cases file
    // args[2] building name
    // args[3] number of people
    
    if(args.length < 4){

      UndirectedGraph graph = readGraph(args[0]);
      int people = Integer.parseInt(args[3]);
      readCases(args[1], graph, args[2], people);

    }else{
      System.out.println("Uso: java EncontrarAgua <grafo> <casos> <edif> <personas>");
    }

  }

  public static UndirectedGraph readGraph(String file){

    BufferedReader reader = new BufferedReader(new FileReader(file));
    UndirectedGraph graph = new UndirectedGraph();

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
      int distance = Double.parseDouble(data[3]);
      graph.addSimpleEdge(i, capacity, distance, data[0], data[1]);
    }

    reader.close();
    return graph;

  }

  public static void readCases(String file, UndirectedGraph graph, String origin, int people){

    BufferedReader reader = new BufferedReader(new FileReader(file));
    UndirectedGraph cloneGraph;
    String line;
    String caseId = reader.readLine();

    while(caseId != null){

      System.out.println(caseId);
      cloneGraph = graph.clone();

      line = reader.readLine();
      int buildings = Integer.parseInt(line);
      line = reader.readLine();
      int afectedWays = Integer.parseInt(line);

      boolean[] exists = cloneGraph.numVertices();
      Arrays.fill(exists, false);

      for(int i = 0; i < buildings; i++){
        line = reader.readLine();
        String[] data = line.split(" ");
        
        exists[cloneGraph.getVertexIndex(data[0])] = true;

        if(data.length == 2){
          int newFloor = Integer.parseInt(data[1]);
          cloneGraph.editVertexFloor(data[0], newFloor);
        }
      }

      for(int i = 0; i < exists.length; i++){
        if(!exists[i]){
          cloneGraph.deleteVertex(String.parseInt(i));
        }
      }

      for(int i = 0; i < afectedWays; i++){
        line = reader.readLine();
        cloneGraph.deleteSimpleEdge(line);
      }

      line = reader.readLine();
      caseId = reader.readLine();

      cloneGraph.applyBellmanFord(origin, people);
    }
  }
}