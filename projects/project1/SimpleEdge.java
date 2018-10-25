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
   * @param iv // Vertex 1.
   * @param fv // Vertex 2.
   * @return // Edge object.
   * @return
   */
  public Edge<T> createSimpleEdge(String id, T data, double weight, Vertex<T> u, Vertex<T> v){

    Edge<T> e = new Edge<T>();

    e.id = id;
    e.data = data;
    e.weight = weight;
    e.v1 = u;
    e.v2 = v;

    return e;
  }

  /**
   * Gets first end from edge.
   * @param e // Edge to consider.
   * @return // Edge's initial vertex.
   */
  public Vertex<T> getEnd1(Edge<T> e){
    
    return e.v1; 
  }

  /**
   * Gets second end from edge.
   * @param e // Edge to consider.
   * @return // Edge's final vertex.
   */
  public Vertex<T> getEnd2(Edge<T> e){

    return e.v2;
  }

  /**
   * Prints edge information.
   * @param e // Edge to print.
   * @return 
   */
  public String toString(Edge<T> e){
    String eString = "";
    eString = "Edge's id: " + e.id + "\n";
    eString = "Edge's data: " + e.data + "\n";
    eString = "Edge's weight: " + e.weight + "/n";
    eString = "Edge's vertex 1: " + e.v1 + "\n";
    eString = "Edge's vertex 2: " + e.v2 + "/n";

    return eString;
  }

}