/** 
 * Graph instance, saves graph information as adjacencies matrix.
 */
public class Graph {

  // Variables Declaration
  private int[][] graph; // Graph matrix representation.

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

}