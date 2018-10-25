import java.util.ArrayList;

/**
 * Class to implement all TAD Directed
 * @param <V>
 * @param <E>
 */
public class DirectedGraph<V,E> extends Graph<V,E> {

  private ArrayList<Vertex<V>> graph = new ArrayList<Vertex<V>>(); 

  /**
   * Opens file and upload its data into Adjacencies List implementation of graph.  
   * @param g // ToDo
   * @param file // File that user wants to open.
   * @return true if the file was opened correctly, othercase false.
   */
  public boolean loadGraph(String file) {

  };

  /**
   * Gets the number of vertices in a graph. 
   * @param g // Graphs that user wants to know the number of vertices.
   * @return // The number of vertices.
   */
  public Integer numVertices(){
    return g.graph.size();
  };

  /**
   * Gets the number of edges in a graph.
   * @param g // Graphs that user wants to know the number of edges.
   * @return // The number of edges.
   */
  public Integer numEdges(DirectedGraph<V,E> g){
    
    Integer nEdges = 0;
    
    for (int i = 0; i < g.graph.size(); i++){
      nEdges += g.graph.get(i).adjacencies.size();
    }

    return nEdges;

  };

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
   * @param g // Graph that user wants to add the vertex.
   * @param vertex // Vertex that user wants to add to graph.
   * @return // true if the vertex was added, othercase false.
   */  
  public boolean addVertex(Vertex<V> vertex){

    boolean newId = true; 

    for(int i = 0; i < g.graph.size(); i++){
      if (g.graph.get(i).getId(g.graph.get(i)) == vertex.getId(vertex)){
        newId = false;
        return false;
      }
    }


    return true;

  };

  /**
   * Add a new vertex to the graph.
   * @param g // Graph that user wants to add the vertex.
   * @param id // Id of the vertex.
   * @param data // Value of vertex.
   * @param weight // Weight of the vertex.
   * @return // true if the vertex was added, other case returns false.
   */
  public boolean addVertex(String id, V data, double weight){
  
    boolean result = false;

    Vertex<V> v = new Vertex<V>().createVertex(id, data, weight);
    result = this.addVertex(g, v);

    return result;

  };

 with the specified id.
   * @param g // Graph to search the vertex
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public V getVertex(String id) throws NoSuchElementException{

    try {
      
    } catch (Exception e) {
      //TODO: handle exception
    }
    
  };

  /**
 is in the graph.
   * @param g // Graph to search the vertex.
   * @param id // Vertex id.
   * @return // true if the vertex is in the graph, othercase false.
   */
  public boolean containsVertex(String id){

  };

  /**
   * Verifies if a edge is in the graph.
   * @param g // Graph to search the edge.
   * @param v1 // End 1
   * @param v2 // End 2
   * @return // true if the edge is  false.
   */
  public boolean cotainsEdge(String v1, String v2){

  };

  /**
   * Deletes a vertex in a graph.
   * @param g // Graphs to delete the vertex.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public 

  };

  /**
   * Gets the list of vertices.
   * @param g // Graph to consider.
   * @return // The list of vertices.
   */
  public List<V> vertices(DirectedGraph<

  };
  
  /**
   * Gets the list of edges.
   * @param g // Graph to consider.
   * @return //  The list of edges.
   */
  public List<E> edges(DirectedGraph<V,E> g){

  };

  /**
   * Gets the degree of one vertex in the graph.
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(String id){

  };

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException  vertex with that id.
   */
  public List<V> neighbourhood(String id) throws NoSuchElementException{

  };

  /**
   * Gets all vertex incident edges.  
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException  vertex with that id.
   */
  public List<E> incidents(String id) throws NoSuchElementException{

  };

  /**
   * Clones a graph into a new structure.
   * @param g // Graph to clone.
   * @return // A new graph clone.
   */
  public DirectedGraph<V,E> clone(){

  };

  /**
   * Gets a string graph representation.
   * @param g // Graph to convert in string.
   * @return // Graph converted to string.
   */
  public String toString(DirectedGraph<V,E> g){

  };
}