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
  private ArrayList<T> adjacents;

  /**
   * Creates a new vertex.
   * @param id // Vertex's id.
   * @param data // Vertex's data.
   * @param weight // Vertex's weight.
   * @return // New Vertex.
   */
  public Vertex<T> createVertex(String id, T data, double weight){

    // Initialice vertexes.
    Vertex<T> v = new Vertex<T>();
    v.id = id;
    v.data = data;
    v.weight = weight;

    return v;
  };

  /**
   * Gets the Vertex's weight 
   * @param v // Vertex's to evaluate.
   * @return // Vertex's weight
   */
  public Integer getWeight(Vertex<T> v){
    return v.weight;
  };

  /**
   * Gets the Vertex's id.
   * @param v // Vertex to evaluate.
   * @return // Vertex's id.
   */
  public String getId(Vertex<T> v){
    return v.id;
  }

  /**
   * Gets Vertex's data.
   * @param v // Vertex to evaluate.
   * @return // Vertex data.
   */
  public T getData(Vertex<T> v){
    return v.data;
  }

  /**
   * Gets the vertex's string representation.
   * @param v // Vertex to evaluate.
   * @return // Vertex's string representation.
   */
  public String toString(Vertex<T> v){
    String vString = "";
    vString = "Vertex's id: " + v.id + "\n";
    vString = "Vertex's data: " + v.data + "\n";
    vString = "Vertex's weight: " + v.weight + "/n";
    return vString;
  }

}