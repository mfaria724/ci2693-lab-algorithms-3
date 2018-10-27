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

  private static void readFile(String file) throws IOException, FileNotFoundException{

    BufferedReader reader = new BufferedReader(new FileReader(file));
        
    String typeVertex = reader.readLine();
    String typeEdge = reader.readLine();
    String typeGraph = reader.readLine();
    
    String line = reader.readLine();
    int numVertices = Integer.parseInt(line);
    
    line = reader.readLine();    
    int numEdges = Integer.parseInt(line);

    if(typeVertex.equals("B")){
      dataTypeVertex = Boolean.class;
    }else if(typeVertex.equals("D")){
      dataTypeVertex = Double.class;
    }else if(typeVertex.equals("S")){
      dataTypeVertex = String.class;
    }else {
      throw new Exception("Invalid vertex type");
    }

    if(typeEdge.equals("B")){
      dataTypeEdge = Boolean.class;
    }else if(typeEdge.equals("D")){
      dataTypeEdge = Double.class;
    }else if(typeEdge.equals("S")){
      dataTypeEdge = String.class;
    }else {
      throw new Exception("Invalid edge type");
    }

    if(typeGraph.equals("D")){
      
      System.out.println("Directed Graph!");
      DirectedGraph<dataTypeVertex, dataTypeEdge> graph = new DirectedGraph<dataTypeVertex, dataTypeEdge>();

    }else if(typeGraph.equals("N")){
      
      System.out.println("Undirected Graph!");
      UndirectedGraph<dataTypeVertex, dataTypeEdge> graph = new UndirectedGraph<dataTypeVertex, dataTypeEdge>();

    }

    for(int i = 0; i < numVertices; i++){
      line = reader.readLine();
      String[] input = line.split(" ");
      String vertexId = input[0];
      String vertexData = input[1];
      String vertexWeight = input[2];
    }

    for(int j = 0; j < numEdges; j++){
      line = reader.readLine();
      String[] input = line.split(" ");
      String edgeId = input[0];
      String edgeData = input[1];
      String edgeWeight = input[2];
      String edgeV1 = input[3];
      String edgeV2 = input[4];
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
        // printMenu(true);  

      } catch (Exception e) {
        //TODO: handle exception
      }
      
    }

  }

}