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

    if(kitchen){
      this.restaurant.add(0, vertex);
    }else {
      this.restaurant.add(vertex);
    }
  }

  public void addConnection(int t1, int t2){
    this.restaurant.get(t1).add(t2);
    this.restaurant.get(t2).add(t1);
  }

  public void dijkstra(int origin){
    System.out.println("Inicio dijkstra desde el nodo: " + origin);

    Double[] cost = new Double[this.restaurant.size()];
    Integer[] father = new Integer[this.restaurant.size()];

    Arrays.fill(cost, -1.0);
    Arrays.fill(father, -1);

  }

}