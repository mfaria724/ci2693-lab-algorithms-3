import java.util.ArrayList;
import java.util.Arrays;

/** 
 * Graph instance, saves graph information as adjacencies matrix.
 */
public class Graph {

  // Variables Declaration
  private int[][] graph; // Graph matrix representation.
  private String[] colors; // Stores colors for DFS method.
  private int[] father; // Store antecesor of each vertex.
  private ArrayList<Integer> actualWay; // Stores the current way. 
  private String space = ""; // Indetation
  private int[] ordinal; // Ordinal sequence.
  private int order = 0; // Actual vertex discovered
  private String tree = ""; // Tree in string format.
  private int depth = 0; // Depth of the graph.
  private int trunc = -1; // Detph to trunc.
  private int connected_components = 0; // Number of strong connected components.

  /**
   * Initialices a graphs with v vertices and e edges.
   * @param v
   */
  Graph(int v){
    this.graph = new int[v][v];
  }

  /**
   * Adds and edge to the matrix representation.
   * @param iv
   * @param fv
   * @throws Exception
   */
  public void addEdge(int iv, int fv) throws Exception{

    if (this.graph[iv][fv] == 1){
      throw new Exception("Repeated Edge");
    }

    this.graph[iv][fv] = 1;

  }

  /**
   * Method that returns al vertex adjacencies. 
   * @param vertex
   * @return
   */
  private ArrayList<Integer> adjacencies(int vertex){

    // Initialices List.
    ArrayList<Integer> adj = new ArrayList<Integer>();

    // Checks adjacencies matrix.
    for (int k = 0; k < this.graph[0].length; k++){
      if (this.graph[vertex][k] == 1 && k != vertex){
        adj.add(k);
      }
    }   

    return adj;

  }

  /**
   * Method to implement dfs over the graph. Some extra functionality has been implemented
   * as explained in problem requirements.
   * @param origin // Vertex to start
   * @param options // Options specified by user.
   * @param trunc // Depth to truncate.
   */
  public void dfs(int origin, Boolean[] options, int trunc){
  
    // Set number of depth to truncate.
    if(options[0]){
      this.trunc = trunc;
    }

    // Initializes colors, father and ordinal array.
    this.colors = new String[this.graph[0].length];
    this.father = new int[this.graph[0].length];
    this.ordinal = new int[this.graph[0].length];

    // Fill array with default values.
    Arrays.fill(this.colors, "W");
    Arrays.fill(this.father, -1);
    Arrays.fill(this.ordinal, -1);

    // Origin father.
    this.father[origin] = origin;

    // Start a way.
    if (colors[origin].equals("W")){
      // Increments number of strong connected components.
      this.connected_components += 1;
      this.tree += origin + "-" + origin + " (raiz)\n";

      // Initialice identation and currentWay.
      this.space = "\t";
      this.actualWay = new ArrayList<Integer>();

      // Checks exceptions for Hamiltonean way.
      this.DFS_visit(origin); 

    }

    // Gets all vertices that were not visited.
    String result = "";
    for(int i = 0; i < this.colors.length; i++){
      if(this.colors[i].equals("W")){
        result += i + ",";
      }
    } 

    // If there wasn't vertices visited.
    if(result.equals("")){
      System.out.println("Todas las páginas son parte de la internet visible.\n");
    } else {
      System.out.println(result.substring(0, result.length() - 1) + "\n"); 
    }

    // Tree option.
    if(options[1]){
      System.out.println("Arbol: ");
      System.out.println(this.tree);
    }

    // Ordinal option.
    if(options[2]){
      System.out.println("Ordinales: ");
      for(int i = 0; i < this.father.length; i++){
        System.out.println(i + ": " + this.ordinal[i]);
      }
      System.out.print("\n");
    }

    // Predecessors option.
    if(options[3]){
      System.out.println("Predecesores: ");
      for(int i = 0; i < this.father.length; i++){
        System.out.println(i + ": " + this.father[i]);
      }
      System.out.print("\n");
    }

    // Strong Connected Components option.
    if(options[4]){
      for(int i = 0; i < this.colors.length; i++){
        if(this.colors[i].equals("W")){
          this.space = "\t";
          this.connected_components += 1;
          this.DFS_visit(i);
        }
      }
      System.out.println("Componentes Conexas: " + this.connected_components);
    }
  }

  /**
   * Applies DFS algorithms over an specific vertex.
   * @param vertex
   */
  private void DFS_visit(int vertex){

    // Changes the current vertex color.
    this.colors[vertex] = "N";
    this.ordinal[vertex] = this.order;
    this.depth += 1;
    this.order += 1;

    //Adds vertex to current way.
    this.actualWay.add(vertex);

    // Gets sucesors of the current vertex.
    ArrayList<Integer> suc = adjacencies(vertex);

    if(this.depth > this.trunc && this.trunc != -1){
      this.space = this.space.substring(0, this.space.length() - 1);
      this.depth -= 1;
      return;
    }

    // Iterate over sucesors.
    for (int j = 0; j < suc.size(); j++){

      // Checks if the vertex has been visited.
      if (this.colors[suc.get(j)].equals("W")){

        // Prints current visit
        this.tree += this.space + vertex + "-" + suc.get(j) + " (arco de camino)\n";
        this.father[suc.get(j)] = vertex;
        this.space += "\t";

        // Go over sucesors of the current sucesor.
        DFS_visit(suc.get(j));

        // Goes back and deletes the last element to go over another sucessor.
        this.actualWay.remove(this.actualWay.size() - 1);

        
      } else {

        // Returned directed edge.
        String line = "";
        for(int i = 0; i < this.actualWay.size(); i++){
          if(this.father[suc.get(j)] == this.actualWay.get(i)){
            line = this.space + vertex + "-" + suc.get(j) + " (arco de subida)\n";
            break;
          }  
        }
        
        // New directed edge.
        if(line.equals("")){
          line = this.space + vertex + "-" + suc.get(j) + " (arco cruzado)\n";
        }

        this.tree += line;
      }
    } 

    // Quits indentation and decreases depth.
    this.space = this.space.substring(0, this.space.length() - 1);
    this.depth -= 1;

  }

}