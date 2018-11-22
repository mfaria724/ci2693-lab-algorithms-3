import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Class to implement all TAD Functions
 */
public class UndirectedGraph{
  
  // UndirectedGraph atributes
  private ArrayList<Vertex> graph; // Adjacencies List
  private ArrayList<SimpleEdge> edges; // Edges List
  private boolean haveWays = true;


  // Initialices an empty graph
  UndirectedGraph(){
    this.graph = new ArrayList<Vertex>();
    this.edges = new ArrayList<SimpleEdge>();
  }

  /**
   * Gets the number of vertices in a graph. 
   * @return // The number of vertices.
   */
  public int numVertices(){
    return this.graph.size();
  }

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
   * @param v // Vertex that user wants to add to graph.
   * @return // true if the vertex was added, othercase false.
   */  
  public boolean addVertex(Vertex v){

    // Checks if there is a vertex with that id
    if(this.containsVertex(v.getId())){
      return false;
    }

    // Add the vertex
    this.graph.add(v);    

    return true;
  }

  /**
   * Add a new vertex to the graph.
   * @param id // Id of the vertex.
   * @param data // Value of vertex.
   * @param weight // Weight of the vertex.
   * @return // true if the vertex was added, other case returns true.
   */
  public boolean addVertex(String id, int data, int weight){

    // Createsa new vertex
    Vertex v = new Vertex(id, data, weight);

    return this.addVertex(v);

  }

  /**
   * Gets the vertex with the specified id.
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public Vertex getVertex(String id) throws NoSuchElementException{

    // Iterates over the list to get the vertex
    for(int i=0;i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i);
      }
    }

    // If there is no vertex with that id
    throw new NoSuchElementException("Vertex doesn't exist");
  }

    /**
   * Gets the vertex index with the specified id.
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public int getVertexIndex(String id) throws NoSuchElementException{

    // Iterates over the list to get the vertex
    for(int i=0;i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return i;
      }
    }

    // If there is no vertex with that id
    throw new NoSuchElementException("Vertex doesn't exist");
  }

  /**
   * Verifies if a vertex is in the graph.
   * @param id // Vertex id.
   * @return // true if the vertex is in the g, othercase false.
   */
  public boolean containsVertex(String id){

    // iterates over the list to find out if the vertex is in the graph
    for(int i=0;i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return true;
      }
    }

    return false;
  }

  /**
   * Adds a simple edge to the graph
   * @param e // Pre-created edge.
   * @return
   */
  public boolean addSimpleEdge(SimpleEdge e){

    // Checks if there is an edge with that id.
    for(int i=0;i<edges.size();i++){
      if(edges.get(i).getId().equals(e.getId())){
        return false;
      }
    }

    // Checks that both vertices exist
    if(!containsVertex(e.getEnd1()) || !containsVertex(e.getEnd2())){
      return false;
    }

    // Adds the edge
    this.edges.add(e);

    // Initialize variables
    Vertex v1 = new Vertex();
    Vertex v2 = new Vertex();
  
    v2 = this.getVertex(e.getEnd2());
    v1 = this.getVertex(e.getEnd1());
    
    // Update adjacencies
    ArrayList<Vertex> newAdj = v1.getAdjacencies();
    newAdj.add(v2);
    this.getVertex(e.getEnd1()).setAdjacencies(newAdj);

    newAdj = v2.getAdjacencies();
    newAdj.add(v1);
    this.getVertex(e.getEnd2()).setAdjacencies(newAdj);

    return true;
  }

  /**
   * Adds a directed edge with provided data.
   * @param id // Edge's id
   * @param data // Edge's data
   * @param weight // Edge's weight
   * @param u // Edge's first vertex id.
   * @param v // Edge's second vertex id.
   * @return
   */
  public boolean addSimpleEdge(String id, int capacity, double distance, String v1, String v2){

    SimpleEdge e = new SimpleEdge(id, capacity, distance, v1, v2);
    return this.addSimpleEdge(e);

  }

  /**
   * Gets the list of vertices.
   * @return // The list of vertices.
   */
  public ArrayList<Vertex> vertices(){
    
    // Creates a new list
    ArrayList<Vertex> vertices = new ArrayList<Vertex>(this.graph);

    return vertices;
  }

  /**
   * Clones a graph into a new structure.
   * @return // A new graph clone.
   */
  public UndirectedGraph clone(){
    
    // Create new graph
    UndirectedGraph clone = new UndirectedGraph();

    // Copy all vertices in the new graph
    for(int i=0; i<this.graph.size(); i++){
      clone.graph.add(new Vertex(this.graph.get(i)));
    }

    // Copy all the edges in the new graph
    for(int i=0; i<this.edges.size(); i++){
      clone.edges.add(new SimpleEdge(this.edges.get(i)));
    }

    return clone;
  }

  /**
   * Deletes an edge
   * @param id // Edge's id
   * @return
   */
  public boolean deleteSimpleEdge(String id){

    // Variables initialization
    String v1 ="";
    String v2 ="";
    boolean result = false;

    // Iterates over the edges' list
    for(int i=0; i<this.edges.size();i++){
      if(this.edges.get(i).getId().equals(id)){
        // Saves the edge's vertex and removes the edge from the list
        v1 = this.edges.get(i).getEnd1();
        v2 = this.edges.get(i).getEnd2();
        result = true;
        this.edges.remove(i);
      }
    }

    // If the edge exist
    if(result){
      // Iterates over the adjacents of v1
      for(int j=0;j<this.getVertex(v1).getAdjacencies().size();j++){
        // Deletes v2 from the adjacents of v1
        if(this.getVertex(v1).getAdjacencies().get(j).getId().equals(v2)){
          ArrayList<Vertex> newAdj = this.getVertex(v1).getAdjacencies();
          newAdj.remove(j);
          this.getVertex(v1).setAdjacencies(newAdj);
        } 
      }
      // Iterates over the adjacents of v2
      for(int j=0;j<this.getVertex(v2).getAdjacencies().size();j++){
        // Deletes v1 from the adjacents of v2
        if(this.getVertex(v2).getAdjacencies().get(j).getId().equals(v1)){
          ArrayList<Vertex> newAdj = this.getVertex(v2).getAdjacencies();
          newAdj.remove(j);
          this.getVertex(v2).setAdjacencies(newAdj);
        }
      }
    }

    return result;
  }

  /**
   * Gets an existing simple edge
   * @param id // Edge's id
   * @return
   * @throws NoSuchElementException // If there is no edge
   */
  public SimpleEdge getSimpleEdge(String id) throws NoSuchElementException{
    
    // Iterates over edges' list to find the edge
    for(int i=0; i<this.edges.size();i++){
      if(this.edges.get(i).getId().equals(id)){
        return this.edges.get(i);
      }
    }

    // If there is no edge
    throw new NoSuchElementException("Edge doesn't exist");
  }

  
    /**
   * Gets an the min  existing simple edge given the ids of the end vertex
   * @param id1 // End 1 id
   * @param id2 // End 2 id
   * @return
   */
  public SimpleEdge getSimpleEdge(String id1, String id2){

    SimpleEdge min = this.edges.get(0);

    // Iterates over the edge's list
    for(int i=0; i<this.edges.size();i++){
      // Checks if there's an edge with both vertex
      if((this.edges.get(i).getEnd1().equals(id1) && this.edges.get(i).getEnd2().equals(id2))
      || (this.edges.get(i).getEnd1().equals(id2) && this.edges.get(i).getEnd2().equals(id1))){
        if(min.getCapacity() > this.edges.get(i).getCapacity()){
          min = this.edges.get(i);
        }
      }
    }

    return min;

  }
  /**
   * Method that given a ids of two vertex, returns the edge that contains them.
   * @param id1 End 1 id
   * @param id2 End 2 id
   * @return
   */
  public int getSimpleEdgeIndex(String id1, String id2){
    // Iterates over the edge's list
    for(int i=0; i<this.edges.size();i++){
      // Checks if there's an edge with both vertex
      if((this.edges.get(i).getEnd1().equals(id1) && this.edges.get(i).getEnd2().equals(id2))
      || (this.edges.get(i).getEnd1().equals(id2) && this.edges.get(i).getEnd2().equals(id1))){
        return i;
      }
    }

    // If there is no edge
    throw new NoSuchElementException("Edge doesn't exist");
  }

  /**
   * Method that apply bellmanford until there aren't people or bathrooms
   * @param originId Origin id for bellman ford
   * @param people Number of people that is going to be assigned
   * @param cleanBathrooms Number of bathrooms that are available
   */
  public void applyBellmanFord(String originId, int people, int cleanBathrooms){
    
    int initialPeople = people;

    // Checks if there are people to assign, ways and still are bathrooms to assign people
    while(people > 0 && this.haveWays && people > initialPeople - cleanBathrooms){
      people = bellmanFord(originId, people);
    }

    // If there are still people, they couldn't be assigned
    if(people > 0){
      System.out.println("  " + people + " personas sin asignar.");
    }

  }

  /**
   * Method that finds the minimun cost ways from a vertex to all its adjacencies
   * @param originId  Origin vertex
   * @param people Number of people that needs to be assigned
   * @return
   */
  private int bellmanFord(String originId, int people){

    // Initializes needed arrays
    double[] distance = new double[this.graph.size()];
    int[] predecessors = new int[this.graph.size()];

    Arrays.fill(distance, Double.POSITIVE_INFINITY);

    Arrays.fill(predecessors, -1);

    distance[this.getVertexIndex(originId)] = 0;

    predecessors[this.getVertexIndex(originId)] = this.getVertexIndex(originId);

    // Boleean variable used to detect if a change was made in a iteration
    boolean change = true;

    // Initializing string used for the vertexes id
    int m;
    int n;

    // Iterates |V| - 1 times to consider the largest elemental way
    for(int i=0 ; i<this.graph.size() && change; i++){
      change = false;

      // Iterates over the edges of the graph
      for(int j=0; j < this.edges.size(); j++){

        // Gets the ends of the edge
        n = this.getVertexIndex(this.edges.get(j).getEnd1());
        m = this.getVertexIndex(this.edges.get(j).getEnd2());

        // Verifies if the actual cost to get to an vertex is more that the cost of the previous vertex adding the cost of the edge
        // If so, update the cost to get to the vertex
        if( distance[m] > distance[n] + this.edges.get(j).getDistance()){
          distance[m] = distance[n] + this.edges.get(j).getDistance();
          predecessors[m] = n;
          change = true;
        } else if( distance[n] > distance[m] + this.edges.get(j).getDistance()){
          distance[n] = distance[m] + this.edges.get(j).getDistance();
          predecessors[n] = m;
          change = true;
        }
      }
    }

    // Iterates over the edges one more time to verifies is the same, if so it means that there is a negative cicle
    for(int i=0; i<this.edges.size();i++){
      n = this.getVertexIndex(this.edges.get(i).getEnd1());
      m = this.getVertexIndex(this.edges.get(i).getEnd2());

      if(distance[m] > distance[n] + this.edges.get(i).getDistance()){
        System.out.println("Error, hay un circuito de peso negativo");
        System.exit(0);
      } else if(distance[n] > distance[m] + this.edges.get(i).getDistance()){
        System.out.println("Error, hay un circuito de peso negativo");
        System.exit(0);
      }
    }

    // Adds to every vertex the distance that has to walk to get wo the floor
    for(int i=0; i<distance.length;i++){
      distance[i] = distance[i] + Math.abs(this.graph.get(i).getFloors())*25;
    }

    // Gets the vertex that has the way with the less distance
    int destination = betterOption(distance);

    // Checks if there isn't a way
    if(destination == -1){ // If there isn't a way
      this.haveWays = false;
    } else{ // If there is a way

      // Reconstrucs the way to the destination vertex
      ArrayList<Vertex> bestWay = reconstrucWay(destination, predecessors);
      bestWay.add(0, this.getVertex(originId));

      // Modifies the graph, and get the rest of people after assign them
      int assignedPeople = modifyGraph(bestWay);


      String printAssigned = "";

      // Checks if there isn't more people, to know what to print
      if(people - assignedPeople < 0){
        printAssigned = Integer.toString(people);
      } else {
        printAssigned = Integer.toString(assignedPeople);
      }

      people -= assignedPeople;

      // Prints the way
      printWay(printAssigned, bestWay, distance, destination);
    }
    
    // System.exit(0);
    return people;

  }

  /**
   * Method used to print the way
   * @param printAssigned Number of people assgined the destination bathroom
   * @param bestWay Way that is going to be printed
   * @param distance Distance of the way
   * @param destination Last vertex of the way
   */
  private void printWay(String printAssigned, ArrayList<Vertex> bestWay, double[] distance, int destination){


    String output = "\tRuta: " + bestWay.get(0).getId();
    
    // Iterates over the way to print every vertex
    for(int i = 1 ; i<bestWay.size();i++){
      output += " - " + bestWay.get(i).getId();
    }
    
    // Add the distance in the output
    output += " (" + new DecimalFormat("0.0#").format(distance[destination]) + " m)";
    
    // Prints number of people assigned and the destination
    System.out.println("  " + printAssigned + " personas a " + bestWay.get(bestWay.size() - 1).getId());
    System.out.println(output);

  }

  /**
   * Method to get from all the ways, the way with the minimum distance
   * @param distance Array that contains the distance from the origin to every vertex
   * @return
   */
  private int betterOption(double[] distance){
    // Initializes variables
    int min = 0;
    boolean empty = true;
    // Iterates over the distance array to get the minimun distance
    for(int i=0; i<distance.length;i++){
      if(distance[min] != Double.POSITIVE_INFINITY && distance[min] != this.graph.get(min).getFloors()*25.0){
        empty = false;
      }
      if((distance[min] > distance[i] || distance[min] == this.graph.get(i).getFloors()*25) && this.graph.get(i).getCapacity() != 0){
        min = i;
      }
    }
    if(empty){
      min = -1;
    }

    return min;
  }

  /**
   * Method that modifies the graph after assign people, and get the number of people assigned
   * @param bestWay Way to nearest bathroom
   * @return
   */
  private int modifyGraph(ArrayList<Vertex> bestWay){

    // Gets the mincapacity in all the way.
    int minCapacity = getMinCapacity(bestWay);
    String m;
    String n;

    // Iterates over the edges of the best way, substracting the mincapacity in every one
    try {
      for(int i=0; i<bestWay.size() - 1; i++){
        m = bestWay.get(i).getId();
        n = bestWay.get(i + 1).getId();
  
        this.edges.get(this.getSimpleEdgeIndex(m, n)).editCapacity(minCapacity);
      }    
    } catch (Exception e) {
      
    }
    
    // Substracts the mincapacity in the destination vertex
    this.graph.get(this.getVertexIndex(bestWay.get(bestWay.size() - 1).getId())).editCapacity(minCapacity);

    // Iterates over the edges
    for(int i=0; i<this.edges.size();i++){
      // If their capacity is 0, it is deleted from the graph
      if(this.edges.get(i).getCapacity() == 0){
        this.deleteSimpleEdge(this.edges.get(i).getId());
      }
    }

    // Return the number of people that is assigned
    return minCapacity;

  }

  /**
   * Method that reconstruct the way to the destination usign the predecessors array
   * @param destination Index of the last vertex of the way in the predecessors array
   * @param predecessors Array that contains the predecessors of every vertex
   * @return
   */
  private ArrayList<Vertex> reconstrucWay(int destination, int[] predecessors){

    // Initializes return structure
    ArrayList<Vertex> way = new ArrayList<>();

    // Initializes aux varibles to construct the way
    int j  = destination;

    // Add the actual vertex to the way, and gets the predecessor of it until the predecessor is it again
    do{
      way.add(0,this.graph.get(j));
      j = predecessors[j];
    }while(j != predecessors[j]);


    return way;
  }

  /**
   * Method used to get the mincapacity in a given way
   * @param way Way given
   * @return
   */
  private int getMinCapacity(ArrayList<Vertex> way){

    try {
      int min;

      if(way.get(0).getId().equals(way.get(1).getId())){
        min = way.get(0).getCapacity();
      } else{
        min = this.getSimpleEdge(way.get(0).getId(), way.get(1).getId()).getCapacity();   
        for(int i=0; i< way.size() - 1;i++){
          if(min > this.getSimpleEdge(way.get(i).getId(), way.get(i+1).getId()).getCapacity()){
            min = this.getSimpleEdge(way.get(i).getId(), way.get(i+1).getId()).getCapacity();
          }
        }
    
        if(min > way.get(way.size() - 1).getCapacity()){
          min = way.get(way.size() -1).getCapacity();
        } 
      }
      return min;

    } catch (NoSuchElementException e) {
      return way.get(0).getCapacity();
    }

  }

  /**
   * Method used to edit the floor of a given vertex
   * @param id id of vertex that is going to be modified
   * @param modification number of floors that are going to be added or substracted to the vertex
   */
  public void editVertexFloor(String id, int modification){

    Vertex v = this.getVertex(id);
    v.editVertexFloor(modification);

  }

  /**
   * Method that sets the capacity of an vertex
   * @param id Vertex that is going to be modified
   * @param newCapacity Capicity that is going to be setted
   */
  public void setVertexCapacity(String id, int newCapacity){

    Vertex v = this.getVertex(id);
    v.setCapacity(newCapacity);

  }

}