// Tools
import java.util.ArrayList;

/**
 * Class to implement all graph functions.
 */
public class Graph{

  // Variables declaration
  public ArrayList<int[]> edges;
  private int n;
  public int graphCost;
  int[] parts;

  // Empty constructor
  Graph(){}

  // Graph initialization
  Graph(int n, int m){

    this.edges = new ArrayList<int[]>();
    this.graphCost = 0;
    this.n = n;
    this.parts = new int[n];
    for(int i=0; i<n;i++){
      this.parts[i] = i;
    }

  }

  /**
   * Get Set representation
   * @param x Set
   * @return Set's representant
   */
  public int find(int x){
    return this.parts[x];
  }

  /**
   * Joins two sets
   * @param x First set
   * @param y Second set
   */
  public void join(int x, int y){
    for(int i=0; i<n; i++){
      if(this.parts[i]==y){
        this.parts[i]=x;
      }
    }
  }

  /**
   * Adds an edges in an specific position
   * @param i Position
   * @param x First Vertex
   * @param y Second Vertex
   * @param z Cost
   */
  public void addEdge(int i, String x, String y, String z){
      int[] edge = new int[3];
      edge[0] = Integer.parseInt(x);
      edge[1] = Integer.parseInt(y);
      edge[2] = Integer.parseInt(z); 

      this.edges.add(i, edge);
  }

  /**
   * Get element from priotiy queue.
   * @param queue Queue
   * @return Element with higest priority.
   */
  private int[] dequeue(ArrayList<int[]> queue){
    int min = 0;

    // Get minimun element's index.
    for(int i=0; i<queue.size(); i++){
      if(queue.get(i)[2] < queue.get(min)[2]){
        min = i;
      }
    }

    // Returns element with higest priority.
    int[] output = {queue.get(min)[0],queue.get(min)[1], queue.get(min)[2]};
    queue.remove(min);
    return output;
      
  }

  /**
   * Krukal Algorithm's implementation.
   */
  public void kruskal(){

    // Variables initialization
    ArrayList<int[]> T = new ArrayList<>();
    int numComp = this.n;
    ArrayList<int[]> queue = new ArrayList<>(this.edges);

    // While there are more components
    while(numComp > 1){
      int[] edge = dequeue(queue);

      int x = edge[0];
      int y = edge[1];

      // Get representants
      int repX = this.find(x);
      int repY = this.find(y);

      // Compare representants.
      if(repX != repY){
        this.join(repX, repY);
        T.add(edge);
        numComp --;
      }
    }

    // Get consts.
    int costT = cost(T);
    System.out.println((graphCost - costT) + " UT ahorradas");

  }

  /**
   * Function to get cost in a Tree
   */
  private int cost(ArrayList<int[]> T){
    int cost = 0;

    // Iterate over edges to get its cost.
    for(int i=0;i<T.size();i++){
      cost+= T.get(i)[2];
    }

    return cost;
  }

}