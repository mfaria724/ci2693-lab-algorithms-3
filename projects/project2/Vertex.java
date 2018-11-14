import java.util.ArrayList;

/**
 * Class to implement TAD Vertex methods.
 */
public class Vertex{
  
  // Vertex data.
  private String id;
  private int data;
  private int weight;
  private ArrayList<Vertex> adjacents = new ArrayList<>();

  /**
   * Initializes empty vertex.
   */
  public Vertex(){
    
  }

  /**
   * Creates a new vertex.
   * @param id // Vertex's id.
   * @param data // Vertex's data.
   * @param weight // Vertex's weight.
   * @return // New Vertex.
   */
  public Vertex(String id, int data, int weight){

    // Initialice vertex.
    this.id = id;
    this.data = data;
    this.weight = weight;
    this.adjacents =  new ArrayList<Vertex>();

  }

  /**
   * Gets the Vertex's weight 
   * @return // Vertex's weight
   */
  public int getWeight(){
    return this.weight;
  };

  /**
   * Gets the Vertex's id.
   * @return // Vertex's id.
   */
  public String getId(){
    return this.id;
  }

  /**
   * Gets Vertex's data.
   * @return // Vertex data.
   */
  public int getData(){
    return this.data;
  }

  /**
   * Gets the vertex's string representation.
   * @return // Vertex's string representation.
   */
  public String toString(){
    String vString = "";
    vString += "Id: " + this.id + "\n";
    vString += "Dato: " + this.data + "\n";
    vString += "Peso: " + this.weight + "\n";
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

}