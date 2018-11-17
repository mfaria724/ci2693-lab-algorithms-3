import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manuel Faria 15-10463
 * Class to implement graph.
 */
public class Graph {

  private int[][] graph;
  private int[][] father;

  Graph(ArrayList<String> cities, ArrayList<String> edges){

    // Initialize adjacencies matrix.
    this.graph = new int[cities.size()][cities.size()];

    // Adds all edges.
    for(int i = 0; i < edges.size(); i++){

      // Get cities names.
      String[] entries = edges.get(i).split(" "); 

      // Initialize vertexes indexes.
      int v1 = -1;
      int v2 = -1;

      // Get cities indexes.      
      for(int j = 0; j < cities.size(); j++){
        if(cities.get(j).equals(entries[0])){
          v1 = j;
        };
        if(cities.get(j).equals(entries[1])){
          v2 = j;
        };
        if((v1 != -1) && (v2 != -1)){
          break;
        }
      }

      // Adds ways between cities.
      this.graph[v1][v2] = 1;
      this.graph[v2][v1] = 1;

    }

  }

  /**
   * Floyd-Warshall algorithm.
   * @param cities
   */
  public void floydWarshall(ArrayList<String> cities){

    // Initialize arrays.
    int[][] cost = new int[this.graph.length][this.graph.length];
    this.father = new int[this.graph.length][this.graph.length];

    // Fill arrays
    for(int i = 0; i < this.graph.length; i++){
      
      // Fill costs
      cost[i] = this.graph[i].clone();
      
      // Arrays initialization
      Arrays.fill(cost[i], Integer.MAX_VALUE);
      Arrays.fill(father[i], -1);

      // Add current ways.
      for(int j = 0; j < this.graph.length; j++){
        if(this.graph[i][j] == 1){
          cost[i][j] = 1;
          father[i][j] = j;
        }else if(i == j){
          cost[i][j] = 0;
          father[i][j] = i;
        }
      }
    }

    // Floyd Warshal structure
    for(int k = 0; k < this.graph.length; k++){
      for(int i = 0; i < this.graph.length; i++){
        for(int j = 0; j < this.graph.length; j++){
          if( i != j ){ // There are no loops
            if(father[i][k] != -1 && father[k][j] != -1){ // (i,k)=(k,j)
              if(cost[i][j] > cost[i][k] + cost[k][j]){ // New minimun cosr
                cost[i][j] = cost[i][k] + cost[k][j]; // New cost
                father[i][j] = father[i][k]; // New father
              }
            }
          }
          
        }        
      }
    }
    
  }

  /**
   * Prints result in console.
   * @param cities
   * @param ends
   */
  public void printWay(ArrayList<String> cities, String[] ends){

    // Vertexes initialization.
    int v1 = -1;
    int v2 = -1;

    // Get vertexes indexes
    for(int j = 0; j < cities.size(); j++){

      if(cities.get(j).equals(ends[0])){
        v1 = j;
      };
      if(cities.get(j).equals(ends[1])){
        v2 = j;
      };
      if((v1 != -1) && (v2 != -1)){
        break;
      }

    }

    // Go trough array to get the way.
    String ll = "";
    while(true){

      ll += cities.get(v1).substring(0, 1);

      if(v1 == v2){
        break;
      }else{
        v1 = this.father[v1][v2];
      }

    }

    // Prints way.
    System.out.println(ll);

  }

}