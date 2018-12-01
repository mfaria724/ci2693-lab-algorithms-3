// Data Structures
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to implement Graph
 */
public class Graph {

  // Variables declaration.
  private int[][] graph;
  private int count;
  private int[] f;
  private int[] colors;

  /**
   * Constructor
   */
  Graph(int v){
    this.graph = new int[v][v];
  }

  /**
   * Add edge
   */
  public void addConnection(int v1, int v2){
    this.graph[v1][v2] = 1;
  }

  /**
   * Method to initialize recursive DFS method.
   */
  public void DFS(){

    // Get number of nodes.
    this.count = this.graph.length;

    // Variables initialization.
    this.f = new int[this.graph.length];
    this.colors = new int[this.graph.length];
    Arrays.fill(this.colors, 0);

    // Apply DFS for all nodes.
    for(int i = 0; i < this.colors.length; i++){
      
      try {
        if(this.colors[i] == 0){
          this.DFS_recursive(i);
        }        
      } catch (Exception e) {
        if(e.getMessage().equals("Graph has cycle")){
          System.out.println("IMPOSSIBLE");
          return;
        }
      }

    }

    // Prints the result to user.
    for(int i = 0; i < this.f.length; i++){
      System.out.println(this.f[i]);
    }

  }

  /**
   * DFS recursive implementation.
   */
  public void DFS_recursive(int v) throws Exception{

    // Marks vertex as visited.
    this.colors[v] += 1;

    // Gets all sucessors
    ArrayList<Integer> suc = this.sucessors(v);

    // Applies DFS over the sucessors.
    for(int i = 0; i < suc.size(); i++){
      int w = suc.get(i);
      if(this.colors[w] == 1){
        throw new Exception("Graph has cycle");
      }

      if(this.colors[w] == 0){
        this.DFS_recursive(w);
      }
    }

    // Save finalization time.
    this.colors[v] += 1;
    this.f[this.count - 1] = v + 1;
    this.count -= 1;

  }

  /**
   * Get all vertices sucessors
   */
  private ArrayList<Integer> sucessors(int v){

    // Variable initialization.
    ArrayList<Integer> suc = new ArrayList<Integer>();

    // Iterates over vertexes.
    for(int i = 0; i < this.graph.length; i++){
      if(this.graph[v][i] == 1){
        suc.add(i);
      }
    }

    return suc;

  }

}