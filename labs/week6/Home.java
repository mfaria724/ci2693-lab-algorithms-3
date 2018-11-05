public class Home {
  
  private int[][] connections;
  private int[][] switches;

  Home(int rooms){
    this.connections = new int[rooms][rooms];
    this.switches = new int[rooms][rooms];
  }

  public void addConnection(int Room1, int Room2){
    this.connections[Room1][Room2] = 1;
    this.connections[Room2][Room1] = 1;
  }

  public void addSwitch(int iRoom, int fRoom){
    this.switches[iRoom][fRoom] = 1;
  }

  public int getNumberOfRooms(){
    return this.connections.length;
  }
  
}