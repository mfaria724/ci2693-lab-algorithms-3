/**
 * Class to implement all Simple Edges TAD methods.
 */
public class SimpleEdge{
  
  // Edge data.
  private String id;
  private double distance;
  private int capacity;
  private String v1;
  private String v2;
  
  /**
   * Empty constructor
   */
  public SimpleEdge(){

  }
  /**
   * Initializes new edge with the data of a given edge
   * @param e Edge given
   */
  public SimpleEdge(SimpleEdge e){
    this.id = e.id;
    this.v1 = e.v1;
    this.v2 = e.v2;
    this.capacity = e.capacity;
    this.distance = e.distance;
  }

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
  public SimpleEdge(String id, int capacity, double distance, String v1, String v2){
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
  public int getCapacity(){
      return this.capacity;
  }

  /**
   * Get id of an Edge
   * @return // Edge's id
   */
  public String getId(){
      return this.id;
  }

  /**
   * Get distance of an Edge
   * @return // Edge's distance
   */
  public double getDistance(){
      return this.distance;
  }

  public void editCapacity(int substract){
    this.capacity = this.capacity - substract;
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