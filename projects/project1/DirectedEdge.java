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
  public DirectedEdge<T> createDirectedEdge(String id, T data, double weight, Vertex<> iv, Vertex<> fv){

    DirectedEdge<T> e =  new DirectedEdge<>();

    e.id = id;
    e.data = data;
    e.weight = weight;
    e.v1 = iv;
    e.v2 = fv;

    return e;
  }

  /**
   * Gets initial end from edge.
   * @return // Edge's initial vertex.
   */
  public Vertex<U> getInitialEnd(){
    return this.iv;
  }

  /**
   * Gets final end from edge.
   * @return // Edge's final vertex.
   */
  public Vertex<U> getFinalEnd(){
    return this.fv;
  } 

  /**
   * Prints edge information.
   * @return 
   */
  public String toString(){

    String eString = "";
    
    eString = "Edge's id: " + this.id + "/n";
    eString = "Edge's data: " + this.data + "/n";
    eString = "Edge's weight: " + this.weight + "/n";
    eString = "Edge's Initial Vertex: /n" + this.v1 + "/n";
    eString = "Edge's Final Vertex: /n" + this.v2 + "/n";

    return eString;
  }  

}