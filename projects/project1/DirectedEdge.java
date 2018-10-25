/**
 * Class to implement all Directed Edges TAD methods.
 * @param <T> // Type of data edge.
 */
public class DirectedEdge<T> extends Edge<T> {

  /**
   * Creates a new directed edge.
   * @param id // Edge's id.
   * @param data // Edge's data.
   * @param weight // Edge's weight.
   * @param iv // Initial vertex
   * @param fv // Final vertex.
   * @return // Edge object.
   */
  public Edge<T> createDirectedEdge(String id, T data, double weight, Vertex<> iv, Vertex<> fv){

    Edge<T> e =  new Edge<>();

    e.id = id;
    e.data = data;
    e.weight = weight;
    e.v1 = iv;
    e.v2 = fv;

    return e;
  }

  /**
   * Gets initial end from edge.
   * @param e // Edge to consider.
   * @return // Edge's initial vertex.
   */
  public Vertex<U> getInitialEnd(Edge<T> e){
    return e.iv;
  }

  /**
   * Gets final end from edge.
   * @param e // Edge to consider.
   * @return // Edge's final vertex.
   */
  public Vertex<U> getFinalEnd(Edge<T> e){
    return e.fv;
  } 

  /**
   * Prints edge information.
   * @param e // Edge to print.
   * @return 
   */
  public String toString(Edge<T> e){

    String eString = "";
    
    eString = "Edge's id: " + e.id + "/n";
    eString = "Edge's data: " + e.data + "/n";
    eString = "Edge's weight: " + e.weight + "/n";
    eString = "Edge's Initial Vertex: /n" + e.v1.toString() + "/n";
    eString = "Edge's Final Vertex: /n" + e.v2.toString() + "/n";

    return eString;
  }  

}