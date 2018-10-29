import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ClientProgram {

  private static void printMenu(boolean firstTime){
    System.out.println("printMenu() Function");

    // Checks if it is the first time.
    if (firstTime){
      System.out.println("¡BIENVENIDO!");
    }

    System.out.println("Indique una de las siguientes operaciones para continuar: ");

  }

  private static void readFile(String file){

    BufferedReader reader = new BufferedReader(new FileReader(file));
        
    String typeVertex = reader.readLine();
    String typeEdge = reader.readLine();
    String typeGraph = reader.readLine();
    
    String line = reader.readLine();
    int numVertices = Integer.parseInt(line);
    
    line = reader.readLine();    
    int numEdges = Integer.parseInt(line);

    if(typeVertex.equals("B")){
      typeVertex = "Boolean";
    }else if(typeVertex.equals("D")){
      typeVertex = "Double";
    }else if(typeVertex.equals("S")){
      typeVertex = "String";
    }else {
      throw new Exception("Invalid vertex type");
    }

    if(typeEdge.equals("B")){
      typeVertex = "Boolean";
    }else if(typeEdge.equals("D")){
      typeVertex = "Double";
    }else if(typeEdge.equals("S")){
      typeVertex = "String";
    }else {
      throw new Exception("Invalid edge type");
    }

    if(typeGraph.equals("D")){
      
      System.out.println("Directed Graph!");
      typeGraph = "DirectedGraph";

    }else if(typeGraph.equals("N")){
      
      System.out.println("Undirected Graph!");
      typeGraph = "SimpleGraph";

    }else {
      throw new Exception("Invalid graph type");
    }

    Object dataTypeVertex = Class.forName(typeVertex).newInstance();
    Object dataTypeEdge = Class.forName(typeEdge).newInstance();
    Object graph = Class.forName(typeGraph).newInstance();    

    for(int i = 0; i < numVertices; i++){
      line = reader.readLine();
      String[] input = line.split(" ");
      String vertexId = input[0];
      String vertexData = (dataTypeVertex)input[1];
      String vertexWeight = Double.parseDouble(input[2]);

      graph.addVertex(vertexId, vertexData, vertexWeight);
    }

    for(int j = 0; j < numEdges; j++){
      line = reader.readLine();
      String[] input = line.split(" ");
      String edgeId = input[0];
      String edgeData = (dataTypeEdge)input[1];
      String edgeWeight = Double.parseDouble(input[2]);
      String edgeV1 = input[3];
      String edgeV2 = input[4];

      if(typeGraph.equals("D")){
        graph.addDirectecEdge(edgeId, edgeData, edgeWeight, edgeV1, edgeV2);
      }else {
        graph.addSimpleEdge(edgeId, edgeData, edgeWeight, edgeV1, edgeV2);
      }
    }

  }

  public static void main(String[] args) {

    String mensajeError = "Uso: java ClientProgram <rutaArchivo>";

    if(args.length < 1){
      System.err.println(mensajeError);
      return;
    } else {

      try {
        
        readFile(args[0]);
        System.out.println("Graph was load");
        // printMenu(true);  

      } catch (Exception e) {
        //TODO: handle exception
      }
      
    }

  }

}