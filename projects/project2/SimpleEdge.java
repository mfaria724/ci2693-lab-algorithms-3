/**
 * Class to implement all Simple Edges TAD methods.
 */
public class SimpleEdge{
  
  // Edge data.
  protected String id;
  protected double distance;
  protected int capacity;
  protected String v1;
  protected String v2;
  
  /**
   * // Creates a new simple edge
   * @param id // Edge's id.
   * @param distance // Edge's distance.
   * @param capacity // Edge's capacity.
   * @param v1 // Vertex 1.
   * @param v2 // Vertex 2.
   * @return // Edge object.
   * @return
   */
  public SimpleEdge(String id, double distance, int capacity, String v1, String v2){
    this.id = id;
    this.distance = distance;
    this.capacity = capacity;
    this.v1 = v1;
    this.v2 = v2;
  }

  /**
   * Get the capacity of an egde
   * @return // Edge's capacity
   */
  protected int getCapacity(){
      return this.capacity;
  }

  /**
   * Get id of an Edge
   * @return // Edge's id
   */
  protected String getId(){
      return this.id;
  }

  /**
   * Get distance of an Edge
   * @return // Edge's distance
   */
  protected int getDistance(){
      return this.distance;
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
    eString += "Edge's distance: " + this.distance + "\n";
    eString += "Edge's capacity: " + this.capacity + "\n";
    eString += "Edge's vertex 1: " + this.v1 + "\n";
    eString += "Edge's vertex 2: " + this.v2 + "\n";

    return eString;
  }

}