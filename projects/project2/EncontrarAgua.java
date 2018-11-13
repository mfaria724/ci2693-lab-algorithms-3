import java.io.BufferedReader;
import java.io.FileReader;

public class EncontrarAgua {

  public static void main(String[] args) {

    // args[0] graph file
    // args[1] cases file
    // args[2] building name
    // args[3] number of people

    if(args.length < 4){

      readGraph(args[0]);
      readCases(args[1]);

    }else{
      System.out.println("Uso: java EncontrarAgua <grafo> <casos> <edif> <personas>");
    }

  }

  public static void readGraph(String file){

    // Initialice BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));
        
    UndirectedGraph graph = new UndirectedGraph();

    String line;
    line = reader.readLine();
    int vertices = Integer.parseInt(line);
    line = reader.readLine();
    int edges = Integer.parseInt(line);
    

  }

  public static void readCases(String file){

  }

}