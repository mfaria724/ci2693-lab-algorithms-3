import java.util.ArrayList;

/**
 * Class to implement TAD Vertex methods.
 */
public class Vertex{
  
  // Vertex capacity.
  private String id;
  private int capacity;
  private int floors;
  private ArrayList<Vertex> adjacents = new ArrayList<>();

  /**
   * Initializes empty vertex.
   */
  public Vertex(){
    
  }

  /**
   * Creates a new vertex.
   * @param id // Vertex's id.
   * @param capacity // Vertex's capacity.
   * @param floors // Vertex's floors.
   * @return // New Vertex.
   */
  public Vertex(String id, int capacity, int floors){

    // Initialice vertex.
    this.id = id;
    this.capacity = capacity;
    this.floors = floors;
    this.adjacents =  new ArrayList<Vertex>();

  }

  /**
   * Gets the Vertex's floors 
   * @return // Vertex's floors
   */
  public int getFloors(){
    return this.floors;
  };

  /**
   * Gets the Vertex's id.
   * @return // Vertex's id.
   */
  public String getId(){
    return this.id;
  }

  /**
   * Gets Vertex's capacity.
   * @return // Vertex capacity.
   */
  public int getCapacity(){
    return this.capacity;
  }

  /**
   * Gets the vertex's string representation.
   * @return // Vertex's string representation.
   */
  public String toString(){
    String vString = "";
    vString += "Id: " + this.id + "\n";
    vString += "Dato: " + this.capacity + "\n";
    vString += "Peso: " + this.floors + "\n";
    return vString;
  }

  /**
   * Get list of adjacent vertices.
   * @return
   */
  public ArrayList<Vertex> getAdjacencies(){
    ArrayList<Vertex> adj = new ArrayList<Vertex>(this.adjacents);
    return adj;
  }

  /**
   * Sets a new list of adjacents to the current one.
   * @param newAdj
   */
  public void setAdjacencies(ArrayList<Vertex> newAdj){
    this.adjacents = newAdj;
  }

  public void editCapacity(int substract){
    this.capacity = this.capacity - substract;
  }

}