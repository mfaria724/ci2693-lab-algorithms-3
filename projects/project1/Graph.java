// Exceptions
import java.util.NoSuchElementException;

/**
 * Class to implementen all graphs TAD methods. 
 * @param <V> // vertices type.
 * @param <E> // Edges type.
 */
public interface Graph<V, E> {
  
  /**
   * Opens file and upload its data into Adjacencies List implementation of graph.  
   * @param g // ToDo
   * @param file // File that user wants to open.
   * @return true if the file was opened correctly, othercase false.
   */
  public boolean loadGraph(Graph<V,E> g, String file);

  /**
   * Gets the number of vertices in a graph. 
   * @param g // Graphs that user wants to know the number of vertices.
   * @return // The number of vertices.
   */
  public Integer numVertices(Graph<V,E> g);

  /**
   * Gets the number of edges in a graph.
   * @param g // Graphs that user wants to know the number of edges.
   * @return // The number of edges.
   */
  public Integer numEdges(Graph<V,E> g);

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
   * @param g // Graph that user wants to add the vertex.
   * @param vertex // Vertex that user wants to add to graph.
   * @return // true if the vertex was added, othercase false.
   */  
  public boolean addVertex(Graph<V,E> g, Vertex<V> vertex);

  /**
   * Add a new vertex to the graph.
   * @param g // Graph that user wants to add the vertex.
   * @param id // Id of the vertex.
   * @param data // Value of vertex.
   * @param weight // Weight of the vertex.
   * @return // true if the vertex was added, other case returns true.
   */
  public boolean addVertex(Graph<V,E> g, String id, V data, double weight);

  /**
   * Gets the vertex with the specified id.
   * @param g // Graph to search the vertex
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public V getVertex(Graph<V,E> g, String id) throws NoSuchElementException;

  /**
   * Verifies if a vertex is in the graph.
   * @param g // Graph to search the vertex.
   * @param id // Vertex id.
   * @return // true if the vertex is in the graph, othercase false.
   */
  public boolean containsVertex(Graph<V,E> g, String id);

  /**
   * Verifies if a edge is in the graph.
   * @param g // Graph to search the edge.
   * @param v1 // End 1
   * @param v2 // End 2
   * @return // true if the edge is in the graph, othercase false.
   */
  public boolean cotainsEdge(Graph<V,E> g, String v1, String v2);

  /**
   * Deletes a vertex in a graph.
   * @param g // Graphs to delete the vertex.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public boolean deleteVertex(Graph<V,E> g, String Id);

  /**
   * Gets the list of vertices.
   * @param g // Graph to consider.
   * @return // The list of vertices.
   */
  public List<V> vertices(Graph<V,E> g);
  
  /**
   * Gets the list of edges.
   * @param g // Graph to consider.
   * @return //  The list of edges.
   */
  public List<E> edges(Graph<V,E> g);

  /**
   * Gets the degree of one vertex in the graph.
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(Graph<V,E> g, String id);

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public List<V> neighbourhood(Graph<V,E> g, String id) throws NoSuchElementException;

  /**
   * Gets all vertex incident edges.  
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public List<E> incidents(Graph<V,E> g, String id) throws NoSuchElementException;

  /**
   * Clones a graph into a new structure.
   * @param g // Graph to clone.
   * @return // A new graph clone.
   */
  public Graph<V,E> clone(Graph<V,E> g);

  /**
   * Gets a string graph representation.
   * @param g // Graph to convert in string.
   * @return // Graph converted to string.
   */
  public String toString(Graph<V,E> g);

}