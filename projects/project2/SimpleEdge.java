/**
 * Class to implement all Simple Edges TAD methods.
 * @param  // Type of data edge.
 */
public class SimpleEdge{
  
  // Edge data.
  protected String id;
  protected int data;
  protected int weight;
  protected String v1;
  protected String v2;
  
  /**
   * // Creates a new simple edge
   * @param id // Edge's id.
   * @param data // Edge's data.
   * @param weight // Edge's weight.
   * @param v1 // Vertex 1.
   * @param v2 // Vertex 2.
   * @return // Edge object.
   * @return
   */
  public SimpleEdge(String id, int data, int weight, String v1, String v2){
    this.id = id;
    this.data = data;
    this.weight = weight;
    this.v1 = v1;
    this.v2 = v2;
  }

  /**
   * Get the weight of an egde
   * @return // Edge's weight
   */
  protected int getWeight(){
      return this.weight;
  }

  /**
   * Get id of an Edge
   * @return // Edge's id
   */
  protected String getId(){
      return this.id;
  }

  /**
   * Get data of an Edge
   * @return // Edge's data
   */
  protected int getData(){
      return this.data;
  }
  

  /**
   * Gets first end from edge.
   * @return // Edge's initial vertex.
   */
  public String getEnd1(){
    
    return this.v1; 
  }

  /**
   * Gets second end from edge.
   * @return // Edge's final vertex.
   */
  public String getEnd2(){

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
    eString += "Edge's vertex 1: " + this.v1 + "\n";
    eString += "Edge's vertex 2: " + this.v2 + "\n";

    return eString;
  }

}