import java.util.ArrayList;

/**
 * Class to implement TAD Vertex methods.
 * @param <T> // Type of data.
 */
public class Vertex<T> {
  
  // Vertex data.
  private String id;
  private T data;
  private double weight;
  private ArrayList<Vertex<T>> adjacents;

  Vertex(){
    
  }

  /**
   * Creates a new vertex.
   * @param id // Vertex's id.
   * @param data // Vertex's data.
   * @param weight // Vertex's weight.
   * @return // New Vertex.
   */
  Vertex(String id, T data, double weight){

    // Initialice vertex.
    this.id = id;
    this.data = data;
    this.weight = weight;

  }

  /**
   * Gets the Vertex's weight 
   * @return // Vertex's weight
   */
  public Double getWeight(){
    return this.weight;
  };

  /**
   * Gets the Vertex's id.
   * @return // Vertex's id.
   */
  public String getId(){
    return this.id;
  }

  /**
   * Gets Vertex's data.
   * @return // Vertex data.
   */
  public T getData(){
    return this.data;
  }

  /**
   * Gets the vertex's string representation.
   * @return // Vertex's string representation.
   */
  public String toString(){
    String vString = "";
    vString = "Vertex's id: " + this.id + "\n";
    vString = "Vertex's data: " + this.data + "\n";
    vString = "Vertex's weight: " + this.weight + "/n";
    return vString;
  }

  public ArrayList<Vertex<T>> getAdjacencies(){
    ArrayList<Vertex<T>> adj = new ArrayList<Vertex<T>>();
    return adj;
  }

  public void setAdjacencies(ArrayList<Vertex<T>> newAdj){
    this.adjacents = newAdj;
  }

}