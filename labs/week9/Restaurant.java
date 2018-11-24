import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Restaurant {

  // Variable declaration
  private double[][] graph;
  private static long intialTime;
  private static long finalTime;

  /**
   * Empty constructor
   */
  public Restaurant(){}

  /**
   * Constructor to initializes the graph
   * @param tables
   */
  public Restaurant(int tables){

    // Creates adjacencies matrix, and fill it with zeros
    this.graph = new double[tables][tables];
    for(int i = 0; i < this.graph.length; i++){
      Arrays.fill(this.graph[i], 0.0);
    }
  }

  /**
   * Method that add connectios to the graph
   * @param t1  Table 1 
   * @param t2  Table 2
   * @param cost Distance of the connections
   */
  public void addConnection(int t1, int t2, double cost){
    // Add to adjacencies matrix the cost of the connection
    this.graph[t1][t2] = cost;
    this.graph[t2][t1] = cost;
  }

  /**
   * Dijkstra method, to get the min cost ways
   * @param origin origin's vertex (kitchen)
   */
  public void dijkstra(int origin){

    this.intialTime = System.currentTimeMillis();

    // Initializes variables
    // predecessors and cost arrays
    double[] cost = new double[this.graph.length];
    int[] predecessors = new int[this.graph.length];

    // Fills cost array with inf
    Arrays.fill(cost, Double.POSITIVE_INFINITY);

    // Fill predecessors array with -1
    Arrays.fill(predecessors, -1);
    
    // Start origin predecessor with the origin 
    predecessors[origin] = origin;

    // Start origin cost with zero
    cost[origin] = 0;

    // Initializes the queue, and fills it with the vertexes
    ArrayList<Integer> queue =  new ArrayList<Integer>();
    for(int i = 0; i < this.graph.length; i++){
      queue.add(i);
    }

    // While the queue isn't empty
    while (queue.size() > 0){

      // Takes the element with min cost
      int w = minimunCost(queue, cost);

      // Get the adjacents of the w elements
      HashSet<Integer> adj = adjacencies(w); 
      
      // Iterate over the adjacents of w
      for (int v : adj) {
        
        // Checks if the actual cost is more than the cost of w plus the cost of the connection between w and v
        if(cost[v] > cost[w] + this.graph[v][w]){
          // If so, updates the cost
          cost[v] = cost[w] + this.graph[v][w];
          // Add w as predecessor of v
          predecessors[v] = w;
        }
      }

    }

    this.finalTime = System.currentTimeMillis();

    // Prints the result
    this.printResult(cost, predecessors);

  }

  /**
   * Method to get the element with the minimum cost of the queue
   * @param queue queue where the element will be searched
   * @param cost array that contains the cost of every element
   * @return
   */
  private int minimunCost(ArrayList<Integer> queue, double[] cost){
    
    // Initializes minimun as the firts element of the queue
    int min = queue.get(0);
    // Variable to get the index of the queue
    int j = 0;
    
    // Iterates over the queue
    for (int i = 0; i < queue.size(); i++) {  
      // Gets the element  
      int x = queue.get(i);

      // Checks if the i's cost is less than the minimum
      if(cost[min] > cost[x]){

        // If so, updates the min
        min = x;
        // Saves the index of the element in the queue
        j = i;
      }

    }

    // Removes the min cost element of the queue
    queue.remove(j);

    // Returns the minimun element
    return min;
  }

  /**
   * Method that gets the adjacents vertex of a given vertex
   * @param w vertex to consider
   * @return
   */
  private HashSet<Integer> adjacencies(int w){

    // Initializes adjacents
    HashSet<Integer> adj = new HashSet<Integer>();
    
    // Iterate over the w row
    for(int i = 0; i < this.graph.length; i++){

      // Checks if there is a connection between w and i
      if(this.graph[w][i] != 0){
        // If so, add the vertex to the adjacents
        adj.add(i);    
      }
    }

    return adj;

  }

  /**
   * Methos used to print the result in specified way
   * @param cost Array of cost the way to get to every element
   * @param predecessors array of the predecessors of every vertex in the way
   */
  private void printResult(double[] cost, int[] predecessors){
    
    System.out.println("Dijkstra:\n");
    // Initialize the result to be printed
    String result = "";
    
    // Iterates over the vertex
    for(int i = 0; i < this.graph.length; i++){
      result = "Nodo " + i + ": ";
      int n = 0;
      int x = predecessors[i];
      String way = i + "";
      
      // Add every step of the way
      while (x != predecessors[x]){
        way = x + "-->" + way;
        n += 1;
        x = predecessors[x];
      }

      if(i != predecessors[i]){
        way = x + "-->" + way;
        n += 1;
      }

      // String with the number of edges, and cost
      String thirdColumn = n + " lados (costo " + new DecimalFormat("0.0#").format(cost[i]) + ")";

      String template = "%-9s%-20s%-13s%n";

      // Prints the result
      System.out.printf(template, result, way, thirdColumn);

    }
    System.out.println("Dijkstra tomó: " + ((this.finalTime - this.intialTime) / 1000) + " segundos.");

  }

  public void applyAStar(int origin){
    System.out.println("\nAplico A Estrella");

    double[] g = new double[this.graph.length];
    double[] h = new double[this.graph.length];
    double[] f = new double[this.graph.length];
    double[] he = new double[this.graph.length];
  }

  public void aStar(){

  }

}