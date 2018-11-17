import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to implement all TAD Undirected
 */
public class UndirectedGraph{
  
  // UndirectedGraph atributes
  private ArrayList<Vertex> graph; // Adjacencies List
  private ArrayList<SimpleEdge> edges; // Edges List

  // Initialices an empty graph
  UndirectedGraph(){
    this.graph = new ArrayList<Vertex>();
    this.edges = new ArrayList<SimpleEdge>();
  }

  /**
   * Reads a file and charges a graph
   * @param file // File path
   * @param numVertices // Number of vertices
   * @param numEdges // Number of edges
   * @param verTrans // Vertices' transformer
   * @param edgeTrans // Edges' transformer
   * @return
   * @throws IOException // If there is a problem in the O.S
   */
  public boolean loadGraph(String file, int numVertices, int numEdges, TypeTransformer verTrans, TypeTransformer edgeTrans) throws IOException{
    
    // Initialice BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));

    // Read lines pre-read
    String line = reader.readLine();
    for(int i = 0; i < 5; i++){
      line = reader.readLine();
    }
    // Add vertexes
    for(int i = 0; i < numVertices; i++){

      // Transform line data
      String[] info = line.split(" ");
      
      // Parse data types
      String id = info[0];
      V data = verTrans.transform(info[1]);
      Double weight = Double.parseDouble(info[2]);

      // Add vertex to graph
      this.addVertex(id, data, weight);

      // Next vertex
      line = reader.readLine();
    }
    // Add edges
    for(int i = 0; i < numEdges; i++){

      // Parse data types
      String[] info = line.split(" ");
      String id = info[0];
      E data = edgeTrans.transform(info[1]);
      Double weight = Double.parseDouble(info[2]);
      String v1 = info[3];
      String v2 = info[4];
      
      // Add edge
      this.addSimpleEdge(id, data, weight, v1, v2);
      
      // Next line
      line = reader.readLine();
    }

    return true;
  }

  /**
   * Gets the number of vertices in a graph. 
   * @return // The number of vertices.
   */
  public int numVertices(){
    return this.graph.size();
  }

  /**
   * Gets the number of edges in a graph.
   * @return // The number of edges.
   */
  public int numEdges(){
    
    return this.edges.size();
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
   * Gets the vertex with the specified id.
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public Vertex getVertexIndex(String id) throws NoSuchElementException{

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
  public boolean addSimpleEdge(String id, int data, int weight, String v1, String v2){
    SimpleEdge e = new SimpleEdge(id, data, weight, v1, v2);
    return this.addSimpleEdge(e);
  }

  /**
   * Verifies if a edge is in the graph.
   * @param id1 // End 1 id
   * @param id2 // End 2 id
   * @return // true if the edge is in the graph, othercase false.
   */
  public boolean cotainsEdge(String id1, String id2){
    // Iterates over the edge's list
    for(int i=0; i<this.edges.size();i++){
      // Checks if there's an edge with both vertex
      if((this.edges.get(i).getEnd1().equals(id1) && this.edges.get(i).getEnd2().equals(id2))
      || (this.edges.get(i).getEnd1().equals(id2) && this.edges.get(i).getEnd2().equals(id1))){
        return true;
      }
    }

    // If there isn't edfe
    return false;
  }

  /**
   * Deletes a vertex in a graph.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public boolean deleteVertex(String Id){

    // Initialize variables
    boolean result = false;

    // Iterates over the list of adjacencies
    for(int i=0;i<this.graph.size(); i++){
      for(int j=0;j<this.graph.get(i).getAdjacencies().size(); j++){
        // Deletes the vertex from the adjacencies list of another vertesx
        if(this.graph.get(i).getAdjacencies().get(j).getId().equals(Id)){
          ArrayList<Vertex> newAdj = this.graph.get(i).getAdjacencies();
          newAdj.remove(j);
          this.graph.get(i).setAdjacencies(newAdj);
          result = true;
        }
      }
    }

    // Deletes the vertex in the vertex list
    if(this.containsVertex(Id)){
      this.graph.remove(this.getVertex(Id));
    }

    // Deletes edges that contains the vertex
    int i =0;
    while(i < this.edges.size()){
      if(this.edges.get(i).getEnd1().equals(Id) || this.edges.get(i).getEnd2().equals(Id)){
        this.edges.remove(i);
        i = i - 1;
      }
      i += 1;
    }
    
    return result;
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
   * Gets the list of edges.
   * @return //  The list of edges.
   */
  public ArrayList<Edge> edges(){

    // Creates a new list
    ArrayList<Edge> out = new ArrayList<Edge>(this.edges);

    return out;
  }

  /**
   * Gets the degree of one vertex in the graph.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(String id) throws NoSuchElementException{

    // Checks if the vertex exist
    if(this.containsVertex(id)){
      // Return number of adjacents of the vertex
      return this.getVertex(id).getAdjacencies().size();
    }

    // If vertex doesn't exist
    throw new NoSuchElementException("Vertex doesn't exist");
  }

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */

  public ArrayList<Vertex> neighbourhood(String id) throws NoSuchElementException{
    // Checks if vertex exist
    if(this.containsVertex(id)){
      // Return vertex's adjacents
      return this.getVertex(id).getAdjacencies();
    }

    throw new NoSuchElementException("Vertex doesn't exist");
  }

    

  /**
   * Gets all vertex incident edges. 
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public ArrayList<Edge> incidents(String id) throws NoSuchElementException{
    
    // Creates new list
    ArrayList<Edge> incidents = new ArrayList<Edge>();

    //Iterates over edges' list
    for(int i=0;i<this.edges.size(); i++){
      // If the edge incise in the vertex, it's added to the list
      if(this.edges.get(i).getEnd1().equals(id) || this.edges.get(i).getEnd2().equals(id) ){
        incidents.add(this.edges.get(i));
      }
    }

    return incidents;
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
      clone.graph.add(this.graph.get(i));
    }

    // Copy all the edges in the new graph
    for(int i=0; i<this.edges.size(); i++){
      clone.edges.add(this.edges.get(i));
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
  public Edge getSimpleEdge(String id) throws NoSuchElementException{
    
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
    // Iterates over the edge's list
    for(int i=0; i<this.edges.size();i++){
      // Checks if there's an edge with both vertex
      if((this.edges.get(i).getEnd1().equals(id1) && this.edges.get(i).getEnd2().equals(id2))
      || (this.edges.get(i).getEnd1().equals(id2) && this.edges.get(i).getEnd2().equals(id1))){
        return this.edges.get(i);
      }
    }

    // If there isn't edfe
    return false;
  }

  public void applyBellmanFord(String originId, int people){
    
  }

  private void bellmanFord(String originId){

    double[] distance = new double[this.graph.size()];
    String[] predecessors = new String[this.graph.size()];

    Arrays.fill(distance, Double.POSITIVE_INFINITY);

    Arrays.fill(predecessors, -1);

    distance[this.getVertexIndex(originId)] = 0;

    predecessors[this.getVertexIndex(originId)] = this.getVertexIndex(originId);

    boolean change = true;

    String m;
    String n;

    for(int i=0 ; i<this.graph.size(); i++){
      change = false;

      for(int j=0; j < this.edges.size(); j++){
        n = this.getVertexIndex(this.edges.get(j).getEnd1());
        m = this.getVertexIndex(this.edges.get(j).getEnd2());

        if( distance[this.getVertexIndex(m)] > distance[this.getVertexIndex(n)] + this.getSimpleEdge(n, m).getDistance() + Math.abs(this.getVertex(m).getFloors())*25 ){
          distance[this.getVertexIndex(m)] = distance[this.getVertexIndex(n)] + this.getSimpleEdge(n, m).getDistance() + Math.abs(this.getVertex(m).getFloors())*25;
          predecessors[this.getVertexIndex(m)] = this.getVertexIndex(n);
          change = true;
        }
      }
    }

    for(int i=0; i<this.edges.size();i++){
      n = this.getVertexIndex(this.edges.get(j).getEnd1());
      m = this.getVertexIndex(this.edges.get(j).getEnd2());

      if(distance[this.getVertexIndex(m)] > distance[this.getVertexIndex(n)] + this.getSimpleEdge(n, m).getDistance() + Math.abs(this.getVertex(m).getFloors())*25){
        System.out.println("Error, hay un circuito de peso negativo");
      }
    }
  }

//   /**
//    * Gets a string graph representation.
//    * @return // Graph converted to string.
//    */
//   public String toString(){

//     //  Variables initialization
//     String stringGraph = "";

//     // Vertices type.
//     if(this.graph.size() != 0){
//       stringGraph += "Tipo de dato de Vértices: ";
//       stringGraph += this.getVertexType(this.graph.get(0).getData()) + "\n";    
//     }
    
//     // Edges type.
//     if(this.edges.size() != 0){
//       stringGraph += "Tipo de dato de Aristas: ";
//       stringGraph += this.getEdgeType(this.edges.get(0).getData()) + "\n";
//     }

//     // Graph type
//     stringGraph += "Grafo No Dirijido \n";

//     // Number of vertices.
//     stringGraph += "Número de Vértices: ";
//     stringGraph += this.graph.size() + "\n";

//     // Number of Directed Edges.
//     stringGraph += "Número de Aristas: ";
//     stringGraph += this.edges.size() + "\n";
    
//     // Vertices.
//     stringGraph += "Vértices: \n";
//     stringGraph += "Id\t\tDato\t\tPeso\n";
//     for(int i = 0; i < this.graph.size(); i++){
//       Vertex v = this.graph.get(i);
//       stringGraph += v.getId() + " \t\t";
//       stringGraph += v.getData() + "\t\t";
//       stringGraph += v.getWeight() + "\n";
//     }

//     // Simple Edges.
//     stringGraph += "Aristas: \n";
//     stringGraph += "Id\t\tDato\t\tPeso\t\tIdV1\t\tIdV2\n";
//     for(int j = 0; j < this.edges.size(); j++){
//       SimpleEdge e = this.edges.get(j);
//       stringGraph += e.getId() + "\t\t";
//       stringGraph += e.getData() + "\t\t";
//       stringGraph += e.getWeight() + "\t\t";
//       stringGraph += e.getEnd1() + "\t\t";
//       stringGraph += e.getEnd2() + "\n";
//     }

//     return stringGraph;

//   }

//   /**
//    * Auxiliary function for idnetufy vertex's data type.
//    * @param value
//    * @return
//    */
//     private String getVertexType(V value){
    
//     if(value instanceof String){
//       return "String";
//     }else if(value instanceof Double){
//       return "Double";
//     }else if(value instanceof Boolean){
//       return "Boolean";
//     }

//     return "None";

//   }
  
//   /**
//    * Auxiliary function for idnetufy edge's data type.
//    * @param value
//    * @return
//    */
//   private String getEdgeType(E value){
    
//     if(value instanceof String){
//       return "String";
//     }else if(value instanceof Double){
//       return "Double";
//     }else if(value instanceof Boolean){
//       return "Boolean";
//     }

//     return "None";

//   }
}
