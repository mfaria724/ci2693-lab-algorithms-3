import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {

  private ArrayList<ArrayList<Integer>> restaurant;  
  
  public Restaurant(){
    this.restaurant = new ArrayList<ArrayList<Integer>>();
  }

  public void addTable(int x, int y, boolean kitchen){
    ArrayList<Integer> vertex = new ArrayList<Integer>();
    vertex.add(x);
    vertex.add(y);

    // if(kitchen){
    //   this.restaurant.add(0, vertex);
    // }else {
      this.restaurant.add(vertex);
    // }
  }

  public void addConnection(int t1, int t2){
    this.restaurant.get(t1).add(t2);
    this.restaurant.get(t2).add(t1);
  }

  public double costE(int v1, int v2){
    int x =this.restaurant.get(v2).get(0) - this.restaurant.get(v1).get(0);
    int y = this.restaurant.get(v2).get(1) - this.restaurant.get(v1).get(1);
    return Math.sqrt((x * x) - (y * y));
  }

  public void dijkstra(int origin){
    System.out.println("Inicio dijkstra desde el nodo: " + origin);

    Double[] cost = new Double[this.restaurant.size()];
    Integer[] father = new Integer[this.restaurant.size()];

    Arrays.fill(cost, 2000.0);
    Arrays.fill(father, -1);

    father[origin] = origin;
    cost[origin] = 0.0;

    System.out.println(cost[origin]);
    System.out.println(origin);
    ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>(this.restaurant);
    int w = 0;

    System.out.println(this.restaurant.size());
    for(int i=0; i<this.restaurant.size(); i++){
      
      for(int v=2; v<this.restaurant.get(i).size(); v++ ){
        if(cost[v] > cost[i] + costE(i, v)){
          cost[v] = cost[i] + costE(i, v);
          System.out.println(costE(i, v));
          father[v] = i;
        }
      }
    } 

    for(int i=0;i<cost.length;i++){
      System.out.println("nodo: "+ i+ " padre: " + father[i]+ " costo: " + cost[i]);
    }


  }

}