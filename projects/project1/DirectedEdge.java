/**
 * Class to implement all Directed Edges TAD methods.
 * @param <T> // Type of data edge.
 */
public class DirectedEdge<T> extends Edge<T> {

  public DirectedEdge(String id, T data, Double weight, String iv, String fv){
    this.id = id;
    this.data = data;
    this.weight = weight;
    this.v1 = iv;
    this.v2 = fv;
  }

  /**
   * Gets initial end from edge.
   * @return // Edge's initial vertex.
   */
  public String getInitialEnd(){
    return this.v1;
  }

  /**
   * Gets final end from edge.
   * @return // Edge's final vertex.
   */
  public String getFinalEnd(){
    return this.v2;
  } 

  /**
   * Prints edge information.
   * @return 
   */
  public String toString(){

    String eString = "";
    
    eString += "Edge's id: " + this.id + "\n";
    eString += "Edge's data: " + this.data + "\n";
    eString += "Edge's weight: " + this.weight + "\n";
    eString += "Edge's Initial Vertex:" + this.v1 + "\n";
    eString += "Edge's Final Vertex:" + this.v2 + "\n";

    return eString;
  }  

}