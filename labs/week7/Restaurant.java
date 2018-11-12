import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Restaurant {

  private double[][] graph;

  public Restaurant(){}

  public Restaurant(int tables){
    this.graph = new double[tables][tables];
    for(int i = 0; i < this.graph.length; i++){
      Arrays.fill(this.graph[i], 0.0);
    }
  }

  public void addConnection(int t1, int t2, double cost){
    this.graph[t1][t2] = cost;
    this.graph[t2][t1] = cost;
  }

  public void dijkstra(int origin){

    double[] cost = new double[this.graph.length];
    int[] predecessors = new int[this.graph.length];

    Arrays.fill(cost, Double.POSITIVE_INFINITY);
    Arrays.fill(predecessors, -1);
  
    predecessors[origin] = origin;
    cost[origin] = 0;

    ArrayList<Integer> queue =  new ArrayList<Integer>();
    for(int i = 0; i < this.graph.length; i++){
      queue.add(i);
    }

    while (queue.size() > 0){
      int w = minimunCost(queue, cost);

      for(int i = 0; i < queue.size(); i++){
        System.out.println(queue.get(i));
      }

      HashSet<Integer> adj = adjacencies(w); 
      for (int v : adj) {
        if(cost[v] > cost[w] + this.graph[v][w]){
          cost[v] = cost[w] + this.graph[v][w];
          predecessors[v] = w;
        }
      }

    }

    String ll = "[";
    for(int i = 0; i < cost.length; i++){
      ll += cost[i] + ",";
    }
    ll += "]";
    System.out.println("Costos: " + ll);

    ll = "[";
    for(int i = 0; i < predecessors.length; i++){
      ll += predecessors[i] + ",";
    }
    ll += "]";
    System.out.println("Papas: " + ll);

  }

  private int minimunCost(ArrayList<Integer> queue, double[] cost){
    
    int min = queue.get(0);
    int j = 0;
  
    for (int i = 0; i < queue.size(); i++) {    
      int x = queue.get(i);

      if(cost[min] > cost[x]){
        min = x;
        j = i;
      }

    }

    queue.remove(j);
    System.out.println("Elemento min: " + min);
    return min;
  }

  private HashSet<Integer> adjacencies(int w){

    HashSet<Integer> adj = new HashSet<Integer>();
    
    for(int i = 0; i < this.graph.length; i++){
      if(this.graph[w][i] != 0){
        adj.add(i);    
      }
    }

    return adj;

  }

}