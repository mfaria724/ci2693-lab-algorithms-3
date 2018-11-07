/**
 * Class to implement a graph with two types of edges.
 */
public class Home {
  
  // Variables declaration.
  private int[][] connections;
  private int[][] switches;

  /**
   * Initialization of variables
   * @param rooms // Number of house rooms.
   */
  Home(int rooms){
    // Variables initialization.
    this.connections = new int[rooms][rooms];
    this.switches = new int[rooms][rooms];
  }

  /**
   * Adds a new connection between rooms. (SimpleEdge)
   * @param Room1
   * @param Room2
   */
  public void addConnection(int Room1, int Room2){
    this.connections[Room1][Room2] = 1;
    this.connections[Room2][Room1] = 1;
  }

  /**
   * Adds a new switch to one room to another.
   * @param iRoom
   * @param fRoom
   */
  public void addSwitch(int iRoom, int fRoom){
    this.switches[iRoom][fRoom] = 1;
  }

  /**
   * Gets the number of rooms.
   * @return
   */
  public int getNumberOfRooms(){
    return this.connections.length;
  }

  /**
   * Gets all switches.
   * @return
   */
  public int[][] getSwitches(){
    int[][] result = this.switches;
    return result;
  }

  /**
   * Gets all connections.
   * @return
   */
  public int[][] getConnections(){
    int[][] result = this.connections;
    return result;
  }
  
}