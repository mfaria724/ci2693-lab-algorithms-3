import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Class to implement all TAD Directed
 * @param <V>
 * @param <E>
 */
public class DirectedGraph<V,E> implements Graph<V,E> {

  // DirectedGraph atributtes.
  private ArrayList<Vertex<V>> graph; // Adjacencies List  
  private ArrayList<DirectedEdge<E>> edges; // Edges List

  // Initialices an empty graph.
  DirectedGraph(){
    this.graph = new ArrayList<Vertex<V>>();
    this.edges = new ArrayList<DirectedEdge<E>>();
  }

  /**
   * Reads a file and charges a graph.
   * @param file // File path
   * @param numVertices // Number of Vertices
   * @param numEdges // Number of edges.
   * @param verTrans // Vertices' transformer.
   * @param edgeTrans // Edges' transformer.
   * @return
   * @throws IOException // If there is a problem in the O.S.
   */
  public boolean loadGraph(String file, int numVertices, int numEdges, TypeTransformer<V> verTrans, TypeTransformer<E> edgeTrans) throws IOException{

    // Initialize BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));

    // Read lines pre-read
    String line = reader.readLine();
    for(int i = 0; i < 5; i++){
      line = reader.readLine();
    }

    // Add vertexes.
    for(int i = 0; i < numVertices; i++){

      // Transform line data.
      String[] info = line.split(" ");
      
      // Parse data types.
      String id = info[0];
      V data = verTrans.transform(info[1]);
      Double weight = Double.parseDouble(info[2]);

      // Add vertex to graph.
      this.addVertex(id, data, weight);

      // Next vertex.
      line = reader.readLine();
    }

    // Add edges.
    for(int i = 0; i < numEdges; i++){

      // Transform line data
      String[] info = line.split(" ");
      
      // Parse data types.
      String id = info[0];
      E data = edgeTrans.transform(info[1]);
      Double weight = Double.parseDouble(info[2]);
      String v1 = info[3];
      String v2 = info[4];

      // Add edge.
      this.addDirectedEdge(id, data, weight, v1, v2);

      // Next line.
      line = reader.readLine();
    }

    return true;
  };


  /**
   * Gets the number of vertices in a graph. 
   * @return // The number of vertices.
   */
  public Integer numVertices(){
    return this.graph.size();
  };

  /**
   * Gets the number of edges in a graph.
   * @return // The number of edges.
   */
  public Integer numEdges(){
    return this.edges.size();
  };

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
   * @param vertex // Vertex that user wants to add to graph.
   * @return // true if the vertex was added, othercase false.
   */  
  public boolean addVertex(Vertex<V> vertex){

    // Checks if there is a vertex with that id.
    // Iterate over graph vertexes
    for(int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == vertex.getId()){
        return false;
      }
    }

    // Add the vertex.
    this.graph.add(vertex);

    return true;

  };

  /**
   * Add a new vertex to the graph.
   * @param id // Id of the vertex.
   * @param data // Value of vertex.
   * @param weight // Weight of the vertex.
   * @return // true if the vertex was added, other case returns false.
   */
  public boolean addVertex(String id, V data, double weight){

    // Creates a new vertex
    Vertex<V> v = new Vertex<V>(id, data, weight);
    
    return this.addVertex(v);

  };

  /**
   * Gets the vertex with the specified id.
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public Vertex<V> getVertex(String id) throws NoSuchElementException{

    // Iterates over the list to get the vertex.
    for (int i = 0; i < this.graph.size(); i++){
      Vertex<V> v = this.graph.get(i);

      if (v.getId().equals(id)){
        return v;
      }
    }

    // If there is no vertex with that id.
    throw new NoSuchElementException("Vertex do not exists in the graph");
    
  };

  /**
   * Verifies if a vertex is in the graph.
   * @param id // Vertex id.
   * @return // true if the vertex is in the graph, othercase false.
   */
  public boolean containsVertex(String id){

    // Iterates over the list to find out if the vertex is in the graph.
    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId().equals(id)){
        return true;
      }
    }

    return false;

  };

  /**
   * Verifies if a edge is in the graph.
   * @param v1 // End 1
   * @param v2 // End 2
   * @return // true if the edge is in the graph, othercase false.
   */
  public boolean cotainsEdge(String v1, String v2){

    // Iterates over the graph's adjacencies list
    for (int i = 0; i < this.graph.size(); i++){
      // If vertex exists searchs it's sucessors
      if (this.graph.get(i).getId().equals(v1)){
        // Iterates over it's sucessors
        ArrayList<Vertex<V>> adj = this.graph.get(i).getAdjacencies();

        for (int j = 0; j < adj.size(); j++){
          // If fv is a sucessor, edge exists.
          if (adj.get(j).getId().equals(v2)){
            return true;
          }
        }
        // There is no edge.
        return false;
      }
    }

    return false;

  };

  /**
   * Deletes a vertex in a graph.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public boolean deleteVertex(String id){

    // Variable initialization.
    boolean exists = false;

    // Iterates over the list to find the vertex.
    for (int i = 0; i < this.graph.size(); i++){
      // If the vertex is found, deletes it.
      if (this.graph.get(i).getId().equals(id)){
        this.graph.remove(i);
        exists = true;
      }
    }

    // Checks if vertex exists.
    if(!exists){
      return false;
    }

    // Iterates over edges list.
    for(int j = 0; j < this.edges.size(); j++){
      // If there is an edge with the vertex in one of its ends, deletes it too.
      if (this.edges.get(j).getInitialEnd().equals(id) || 
          this.edges.get(j).getFinalEnd().equals(id)){
        this.edges.remove(j);
      }
    }

    return exists;

  };

  /**
   * Gets the list of vertices.
   * @return // The list of vertices.
   */
  public ArrayList<Vertex<V>> vertices(){

    // Creates a new list.
    ArrayList<Vertex<V>> v = new ArrayList<Vertex<V>>(this.graph);

    return v;
  };
  
  /**
   * Gets the list of edges.
   * @return //  The list of edges.
   */
  public ArrayList<Edge<E>> edges(){

    // Creates a new list.
    ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>(this.edges);

    return edges;

  };

  /**
   * Gets the degree of one vertex in the graph.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(String id) throws NoSuchElementException{

    // Initializes variables
    Integer degree = 0;
    boolean exists = false;

    // Iterate over the vertexes.
    for(int i = 0; i < this.graph.size(); i++){

      // Save some variables.
      Vertex<V> v = this.graph.get(i);
      ArrayList<Vertex<V>> adj = v.getAdjacencies();

      // If vertex exists. Adds vertex's quantity of sucessors.
      if(v.getId().equals(id)){
        exists = true;
        degree += adj.size();
      }
      
      // Iterates over it's sucessors to find loops.
      for(int j = 0; j < adj.size(); j++){
        if(adj.get(j).getId().equals(id)){
          degree += 1;
        }
      }

    }
    
    // If vertex exists, return degree.
    if(exists){
      return degree;
    }

    // If vertex doesn't exist.
    throw new NoSuchElementException("Vertex doesn't exists.");

  };

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public ArrayList<Vertex<V>> neighbourhood(String id) throws NoSuchElementException{

    // Creates an empty list.
    ArrayList<Vertex<V>> neighbourhood = new ArrayList<Vertex<V>>();

    // If vertex exists add all it's sucessors.
    if(this.containsVertex(id)){
      ArrayList<Vertex<V>> adj = new ArrayList<Vertex<V>>(this.getVertex(id).getAdjacencies());
      neighbourhood.addAll(adj);
    }

    // Iterates over all vertexes to find all predecessors.
    ArrayList<Vertex<V>> pre = this.predecessors(id);
    
    neighbourhood.addAll(pre);

    return neighbourhood;

  };

  /**
   * Gets all vertex incident edges.  
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public ArrayList<Edge<E>> incidents(String id) throws NoSuchElementException{

    // If there is not such vertex.
    if (this.containsVertex(id)){
      throw new NoSuchElementException("There is no vertex with that id.");
    } 

    // Creates an empty list.
    ArrayList<Edge<E>> incidents = new ArrayList<Edge<E>>();

    // Iterates over edges to find incidences.
    for (int i = 0; i < this.edges.size(); i++){
      Edge<E> e = this.edges.get(i);

      if(e.getInitialEnd().equals(id) || e.getFinalEnd().equals(id)){
        incidents.add(e);
      }
    }

    return incidents;
  };

  /**
   * Clones a graph into a new structure.
   * @return // A new graph clone.
  //  */
  public DirectedGraph<V,E> clone(){
    DirectedGraph<V,E> aja = new DirectedGraph<>();
    return aja; // <--------------------------------------------------- MALISIMO
   };

  /**
   * Gets a string graph representation.
   * @return // Graph converted to string.
   */
  public String toString(){

    // Variables initialization.
    String stringGraph = "";

    // Vertices type.
    if(this.graph.size() != 0){
      stringGraph += "Tipo de dato de Vértices: ";
      stringGraph += this.getVertexType(this.graph.get(0).getData()) + "\n";    
    }
    
    // Edges type.
    if(this.edges.size() != 0){
      stringGraph += "Tipo de dato de Arcos: ";
      stringGraph += this.getEdgeType(this.edges.get(0).getData()) + "\n";
    }

    // Graph type
    stringGraph += "Grafo Dirijido (Digrafo) \n";

    // Number of vertices.
    stringGraph += "Número de Vértices: ";
    stringGraph += this.graph.size() + "\n";

    // Number of Directed Edges.
    stringGraph += "Número de Arcos: ";
    stringGraph += this.edges.size() + "\n";
    
    // Vertices.
    stringGraph += "Vértices: \n";
    stringGraph += "Id\t\tDato\t\tPeso\n";
    for(int i = 0; i < this.graph.size(); i++){
      Vertex v = this.graph.get(i);
      stringGraph += v.getId() + " \t\t";
      stringGraph += v.getData() + "\t\t";
      stringGraph += v.getWeight() + "\n";
    }

    // Directed Edges.
    stringGraph += "Arcos: \n";
    stringGraph += "Id\t\tDato\t\tPeso\t\tIdVI\t\tIdVF\n";
    for(int j = 0; j < this.edges.size(); j++){
      DirectedEdge e = this.edges.get(j);
      stringGraph += e.getId() + "\t\t";
      stringGraph += e.getData() + "\t\t";
      stringGraph += e.getWeight() + "\t\t";
      stringGraph += e.getInitialEnd() + "\t\t";
      stringGraph += e.getFinalEnd() + "\n";
    }

    return stringGraph;
  };

  /**
   * Auxiliary function for idnetufy vertex's data type.
   * @param value
   * @return
   */
  private String getVertexType(V value){
    
    if(value instanceof String){
      return "String";
    }else if(value instanceof Double){
      return "Double";
    }else if(value instanceof Boolean){
      return "Boolean";
    }

    return "None";

  }

  /**
   * Auxiliary function for idnetufy edge's data type.
   * @param value
   * @return
   */
  private String getEdgeType(E value){
    
    if(value instanceof String){
      return "String";
    }else if(value instanceof Double){
      return "Double";
    }else if(value instanceof Boolean){
      return "Boolean";
    }

    return "None";

  }

  /**
   * Adds a directed edge to the graph.
   * @param edge // Pre-created edge.
   * @return
   */
  public boolean addDirectedEdge(DirectedEdge<E> edge){

    // Checks if there is an edge with that id.
    for(int i = 0; i < this.edges.size(); i++){
      if(this.edges.get(i).getId().equals(edge.getId())){
        return false;
      }
    }

    // Adds the edge.
    this.edges.add(edge);

    return true;
  }

  /**
   * Adds a directed edge with provided data.
   * @param id // Edge's id
   * @param data // Edge's data
   * @param weight // Edge's weight
   * @param iv // Edge's initial vertex id.
   * @param fv // Edge's final vertex id.
   * @return
   */
  public boolean addDirectedEdge(String id, E data, double weight, String iv, String fv){

    // Initializes variables.
    Vertex<V> fVer = this.getVertex(fv);

    // Checks that both vertices exists.
    if(!(this.containsVertex(iv)) || !(this.containsVertex(fv))){
      return false;
    }

    // Iterate over graph's vertices to update adjacencies list.
    for(int i = 0; i < this.graph.size(); i++){
      Vertex<V> iVer = this.graph.get(i);

      // Updates adjacencies.
      if(iVer.getId().equals(iv)){
        ArrayList<Vertex<V>> adj = iVer.getAdjacencies();
        adj.add(fVer);        
        iVer.setAdjacencies(adj);
        break;
      }
    }

    // Creates edge's and adds it to the graph.
    DirectedEdge<E> e = new DirectedEdge<E>(id, data, weight, iv, fv);
    return this.addDirectedEdge(e); 
  }

  /**
   * Deletes and edge.
   * @param id // Edge's id.
   * @return
   */
  public boolean deleteDirectedEdge(String id){

    // Variables initialization.
    String iv = "";
    String fv = "";

    // Iterates over edges to find the edge.
    for (int i = 0; i < this.edges.size(); i++){
      DirectedEdge<E> e = this.edges.get(i);
      if (e.getId().equals(id)){
        // Saves vertices' ids.
        iv = e.getInitialEnd();
        fv = e.getFinalEnd();
        this.edges.remove(i);
      }
    }
    
    // If there is no edge.
    if(iv.equals("")){
      return false;
    }

    // Iterates over graph's vertexes.
    for (int i = 0; i < this.graph.size(); i++){
      Vertex<V> b = this.graph.get(i);
      // When find the vertex.
      if(b.getId().equals(iv)){
        ArrayList<Vertex<V>> v = b.getAdjacencies();
        
        // Updates adjacencies.
        for(int j = 0; j < v.size(); j++){
          if(v.get(j).getId().equals(fv)){
            v.remove(j);
            b.setAdjacencies(v);
            return true;
          }
        }
      }
    }

    return false;

  }

  /**
   * Gets an existing directed edge.
   * @param id // Edge's id.
   * @return
   * @throws NoSuchElementException // If there is no edge.
   */
  public DirectedEdge<E> getDirectedEdge(String id) throws NoSuchElementException{

    // Iterates over edges listo to find the edge.
    for (int i = 0; i < this.edges.size(); i++){
      if(this.edges.get(i).getId().equals(id)){
        return this.edges.get(i);
      }
    }

    // If there is no edge.
    throw new NoSuchElementException("There is no edges woth that id");

  }

  /**
   * Gets vertex's inner degree.
   * @param id // Vertex's id.
   * @return
   * @throws NoSuchElementException
   */
  public int innerDegree(String id) throws NoSuchElementException{

    // Variables initialization.
    int degree = 0;
    boolean exist = false;

    // Iterates over vertexes to iterate over its sucessors.
    for(int i = 0; i < this.graph.size(); i++){
      Vertex<V> v = this.graph.get(i);

      // If vertex is found.
      if(v.getId().equals(id)){
        exist = true;
      }

      // Iterates over adjacencies to search vertex.
      ArrayList<Vertex<V>> adj = v.getAdjacencies();
      for(int j = 0; j < adj.size(); j++){

        if(adj.get(j).getId().equals(id)){
          degree += 1;
        }

      }

    }

    // If vertex exists, return it's degree.
    if(exist){
      return degree;
    }

    // If vertex isn't found.
    throw new NoSuchElementException("There is no vertex with that id.");

  }

  /**
   * Gets vertex's outter degree.
   * @param id // vertex's id.
   * @return
   * @throws NoSuchElementException
   */
  public int outterDegree(String id) throws NoSuchElementException{
    
    // Iterates over vertices to find its sucessors.
    for(int i = 0; i < this.graph.size(); i++){ 
      Vertex<V> v = this.graph.get(i);


      if(v.getId().equals(id)){
        return v.getAdjacencies().size();
      }
    }

    // IIf there is no vertex.
    throw new NoSuchElementException("There is no vertex with that id.");

  }

  /**
   * Get all vertex's sucessors.
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public ArrayList<Vertex<V>> sucessors(String id) throws NoSuchElementException{

    // If vertex exists, return it's sucessors.
    ArrayList<Vertex<V>> sucessors = new ArrayList<>();
    if(this.containsVertex(id)){
      sucessors = this.getVertex(id).getAdjacencies();
      return sucessors;
    }

    // If vertex doesn't exists.
    throw new NoSuchElementException("There is no vertex with that id.");

  }

  /**
   * Get all vertex's predecessors.
   * @param id // vertex's id.
   * @return
   * @throws NoSuchElementException
   */
  public ArrayList<Vertex<V>> predecessors(String id) throws NoSuchElementException{

    // Creates an empty list.
    ArrayList<Vertex<V>> pre = new ArrayList<Vertex<V>>();
    
    // Iterates over all vertexes.
    for(int i = 0; i < this.graph.size(); i++){
      Vertex<V> v = this.graph.get(i);
      ArrayList<Vertex<V>> adj = v.getAdjacencies();

      // Iterates over it's sucessors.
      for(int j = 0; j < adj.size(); j++){
        if(adj.get(j).getId().equals(id)){
          pre.add(v);
        }
      }

    }

    // If vertex exists, return pre.
    if(this.containsVertex(id)){
      return pre;
    } 
    
    // If vertex doesn't exists.
    throw new NoSuchElementException("There is no vertex with that index.");

  }

}