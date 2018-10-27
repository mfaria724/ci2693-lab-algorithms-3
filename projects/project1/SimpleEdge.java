/**
 * Class to implement all Simple Edges TAD methods.
 * @param <T> // Type of data edge.
 */
public class SimpleEdge<T> extends Edge<T> {
  
  /**
   * // Creates a new simple edge
   * @param id // Edge's id.
   * @param data // Edge's data.
   * @param weight // Edge's weight.
   * @param u // Vertex 1.
   * @param v // Vertex 2.
   * @return // Edge object.
   * @return
   */
    SimpleEdge(String id, T data, double weight, Vertex<T> u, Vertex<T> v){
      this.id = id;
      this.data = data;
      this.weight = weight;
      this.v1 = u;
      this.v2 = v;
  }

  /**
   * Gets first end from edge.
   * @return // Edge's initial vertex.
   */
  public Vertex<T> getEnd1(){
    
    return this.v1; 
  }

  /**
   * Gets second end from edge.
   * @return // Edge's final vertex.
   */
  public Vertex<T> getEnd2(){

    return this.v2;
  }

  /**
   * Prints edge information.
   * @return 
   */
  public String toString(){
    String eString = "";
    eString = "Edge's id: " + this.id + "\n";
    eString = "Edge's data: " + this.data + "\n";
    eString = "Edge's weight: " + this.weight + "\n";
    eString = "Edge's vertex 1: " + this.v1 + "\n";
    eString = "Edge's vertex 2: " + this.v2 + "\n";

    return eString;
  }

}