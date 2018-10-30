/**
 * Class to implement all Directed Edges TAD methods.
 * @param <T> // Type of data edge.
 */
public class DirectedEdge<T> extends Edge<T> {

  /**
   * Initializes edge.
   * @param id // DirectedEdge's id;
   * @param data // DirectedEdge's data;
   * @param weight // DirectedEdge's weight;
   * @param iv // DirectedEdge's initial vertex;
   * @param fv // DirectedEdge's final vertex;
   */
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
    
    eString += "Id del arco: " + this.id + "\n";
    eString += "Dato del arco: " + this.data + "\n";
    eString += "Peso del arco: " + this.weight + "\n";
    eString += "Id del vértice inicial del arco: " + this.v1 + "\n";
    eString += "Id del vértice final del arco: " + this.v2 + "\n";

    return eString;
  }  

}