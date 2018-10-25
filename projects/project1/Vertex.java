/**
 * Class to implement TAD Vertex methods.
 * @param <T> // Type of data.
 */
public class Vertex<T> {
  
  /**
   * Creates a new vertex.
   * @param id // Vertex's id.
   * @param data // Vertex's data.
   * @param weight // Vertex's weight.
   * @return // New Vertex.
   */
  public Vertex<T> createVertex(String id, T data, double weight){
    return new Vertex<>();
  };

  /**
   * Gets the Vertex's weight 
   * @param v // Vertex's to evaluate.
   * @return // Vertex's weight
   */
  public Integer getWeight(Vertex<T> v){
    return 0;
  };

  /**
   * Gets the Vertex's id.
   * @param v // Vertex to evaluate.
   * @return // Vertex's id.
   */
  public String getId(Vertex<T> v){
    return "";
  }

  /**
   * Gets Vertex's data.
   * @param v // Vertex to evaluate.
   * @return // Vertex data.
   */
  public E getData(Vertex<T> v){

  }

  /**
   * Gets the vertex's string representation.
   * @param v // Vertex to evaluate.
   * @return // Vertex's string representation.
   */
  public String toString(Vertex<T> v){
    return "";
  }

}