import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

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
   * Verifies if a edge is in the graph.
   * @param id1 // End 1 id
   * @param id2 // End 2 id
   * @return // true if the edge is in the graph, othercase false.
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


  public void applyBellmanFord(String originId, int people, int cleanBathrooms){
    
    int initialPeople = people;

    while(people > 0 && this.haveWays && people > initialPeople - cleanBathrooms){
      people = bellmanFord(originId, people);
    }

    if(people > 0){
      System.out.println("  " + people + " personas sin asignar.");
    }

  }

  private int bellmanFord(String originId, int people){

    double[] distance = new double[this.graph.size()];
    int[] predecessors = new int[this.graph.size()];

    Arrays.fill(distance, Double.POSITIVE_INFINITY);

    Arrays.fill(predecessors, -1);

    distance[this.getVertexIndex(originId)] = 0;

    predecessors[this.getVertexIndex(originId)] = this.getVertexIndex(originId);

    boolean change = true;

    String m;
    String n;

    for(int i=0 ; i<this.graph.size() && change; i++){
      change = false;

      for(int j=0; j < this.edges.size(); j++){
        n = this.edges.get(j).getEnd1();
        m = this.edges.get(j).getEnd2();

        if( distance[this.getVertexIndex(m)] > distance[this.getVertexIndex(n)] + this.edges.get(j).getDistance()){
          distance[this.getVertexIndex(m)] = distance[this.getVertexIndex(n)] + this.edges.get(j).getDistance();
          predecessors[this.getVertexIndex(m)] = this.getVertexIndex(n);
          change = true;
        } else if( distance[this.getVertexIndex(n)] > distance[this.getVertexIndex(m)] + this.edges.get(j).getDistance()){
          distance[this.getVertexIndex(n)] = distance[this.getVertexIndex(m)] + this.edges.get(j).getDistance();
          predecessors[this.getVertexIndex(n)] = this.getVertexIndex(m);
          change = true;
        }
      }
    }

    for(int i=0; i<this.edges.size();i++){
      n = this.edges.get(i).getEnd1();
      m = this.edges.get(i).getEnd2();

      if(distance[this.getVertexIndex(m)] > distance[this.getVertexIndex(n)] + this.edges.get(i).getDistance()){
        System.out.println("Error, hay un circuito de peso negativo");
        System.exit(0);
      }
    }

    for(int i=0; i<distance.length;i++){
      distance[i] = distance[i] + Math.abs(this.graph.get(i).getFloors())*25;
    }

    int destination = betterOption(distance);

    if(destination == -1){
      this.haveWays = false;
    } else{
      ArrayList<Vertex> bestWay = reconstrucWay(destination, predecessors);
      bestWay.add(0, this.getVertex(originId));
      
      int assignedPeople = modifyGraph(bestWay);
      String printAssigned = "";

      if(people - assignedPeople < 0){
        printAssigned = Integer.toString(people);
      } else {
        printAssigned = Integer.toString(assignedPeople);
      }

      people -= assignedPeople;

      printWay(printAssigned, bestWay, distance, destination);
      
    }
    
    return people;

  }

  private void printWay(String printAssigned, ArrayList<Vertex> bestWay, double[] distance, int destination){

    String output = "\tRuta: " + bestWay.get(0).getId();
      
    for(int i = 1 ; i<bestWay.size();i++){
      output += " - " + bestWay.get(i).getId();
    }
    
    output += " (" + distance[destination] + " m)";
    
    System.out.println("  " + printAssigned + " personas a " + bestWay.get(bestWay.size() - 1).getId());
    System.out.println(output);

  }

  private int betterOption(double[] distance){
    int min = 0;
    boolean empty = true;

    for(int i=0; i<distance.length;i++){
      if(distance[min] != Double.POSITIVE_INFINITY && distance[min] != 0){
        empty =false;
      }
      if((distance[min] > distance[i] || distance[min] == 0) && distance[i] != 0 && this.graph.get(i).getCapacity() != 0){
        min = i;
      }
    }
    if(empty){
      min = -1;
    }

    return min;
  }

  private int modifyGraph(ArrayList<Vertex> bestWay){

    int minCapacity = getMinCapacity(bestWay);
    String m;
    String n;

    try {
      for(int i=0; i<bestWay.size() - 1; i++){
        m = bestWay.get(i).getId();
        n = bestWay.get(i + 1).getId();
  
        this.edges.get(this.getSimpleEdgeIndex(m, n)).editCapacity(minCapacity);
      }    
    } catch (Exception e) {
      
    }
    
    this.graph.get(this.getVertexIndex(bestWay.get(bestWay.size() - 1).getId())).editCapacity(minCapacity);

    for(int i=0; i<this.edges.size();i++){
      if(this.edges.get(i).getCapacity() == 0){
        this.deleteSimpleEdge(this.edges.get(i).getId());
      }
    }

    return minCapacity;

  }

  private ArrayList<Vertex> reconstrucWay(int destination, int[] predecessors){
    ArrayList<Vertex> way = new ArrayList<>();

    int j = destination;

    do{
      way.add(0,this.graph.get(j));
      j = predecessors[j];
    }while(j != predecessors[j]);


    return way;
  }

  private int getMinCapacity(ArrayList<Vertex> way){
    try {
      int min = this.getSimpleEdge(way.get(0).getId(), way.get(1).getId()).getCapacity();    
      
      for(int i=0; i< way.size() - 1;i++){
        if(min > this.getSimpleEdge(way.get(i).getId(), way.get(i+1).getId()).getCapacity()){
          min = this.getSimpleEdge(way.get(i).getId(), way.get(i+1).getId()).getCapacity();
        }
      }
  
      if(min > way.get(way.size() - 1).getCapacity()){
        min = way.get(way.size() -1).getCapacity();
      }

      return min;

    } catch (NoSuchElementException e) {
      return way.get(0).getCapacity();
    }

  }

  public void editVertexFloor(String id, int modification){

    Vertex v = this.getVertex(id);
    v.editVertexFloor(modification);

  }

  public void setVertexCapacity(String id, int newCapacity){

    Vertex v = this.getVertex(id);
    v.setCapacity(newCapacity);

  }

}