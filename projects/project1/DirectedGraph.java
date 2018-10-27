import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class to implement all TAD Directed
 * @param <V>
 * @param <E>
 */
public class DirectedGraph<V,E> extends Graph<V,E> {

  private ArrayList<Vertex<V>> graph = new ArrayList<Vertex<V>>(); 
  private ArrayList<DirectedEdge<E>> edges =  new ArrayList<DirectedEdge<E>>();

  /**
   * Opens file and upload its data into Adjacencies List implementation of graph.  
   * @param file // File that user wants to open.
   * @return true if the file was opened correctly, othercase false.
   */
  public boolean loadGraph(String file) {

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
    
    Integer nEdges = 0;
    
    for (int i = 0; i < this.graph.size(); i++){
      nEdges += this.graph.get(i).adjacencies.size();
    }

    return nEdges;

  };

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
   * @param vertex // Vertex that user wants to add to graph.
   * @return // true if the vertex was added, othercase false.
   */  
  public boolean addVertex(Vertex<V> vertex){

    for(int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == vertex.getId()){
        return false;
      }
    }

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
  
    boolean result = false;

    Vertex<V> v = new Vertex<V>().createVertex(id, data, weight);
    result = this.addVertex(g, v);

    return result;

  };

  /**
   * Gets the vertex with the specified id.
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public Vertex<V> getVertex(String id) throws NoSuchElementException{

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
        return this.graph.get(i);
      }
    }

    throw new NoSuchElementException("Vertex do not exists in the graph");
    
  };

  /**
   * Verifies if a vertex is in the graph.
   * @param id // Vertex id.
   * @return // true if the vertex is in the graph, othercase false.
   */
  public boolean containsVertex(String id){

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
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

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == v1){
        for (int j = 0; j < this.graph.get(i).getAdjacencies().size(); j++){
          if (this.graph.get(i).getAdjacencies().get(j) == v2){
            return true;
          }
        }
      }
    }

    return false;

  };

  /**
   * Deletes a vertex in a graph.
   * @param g // Graphs to delete the vertex.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public boolean deleteVertex(String Id){

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
        this.graph.remove(i);
      }
    }

  };

  /**
   * Gets the list of vertices.
   * @param g // Graph to consider.
   * @return // The list of vertices.
   */
  public ArrayList<Vertex<V>> vertices(){

    return this.graph;

  };
  
  /**
   * Gets the list of edges.
   * @param g // Graph to consider.
   * @return //  The list of edges.
   */
  public List<E> edges(){

    return this.edges;

  };

  /**
   * Gets the degree of one vertex in the graph.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(String id){

    Integer degree = 0;

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
        degree += this.graph.get(i).getAdjacencies().size();
      }
    }

    for(int j = 0; j < this.graph.size(); j++){
      for (int k = 0; k < this.graph.get(j).getAdjacencies().size(); k++){
        if (this.graph.get(j).getAdjacencies().get(k).getId() == id){
          degree += 1;
        };
      }
    }

    return degree;

  };

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public List<V> neighbourhood(String id) throws NoSuchElementException{

    ArrayList<Vertex<V>> neighbourhood = new ArrayList<Vertex<V>>();

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
        neighbourhood.addAll(this.graph.get(i).getAdjacencies());
      }
    }

    for (int j = 0; j < this.graph.size(); j++){
      for (int k = 0; k < this.graph.get(j).getAdjacencies().size(); k++){
        if (this.graph.get(j).getAdjacencies().get(k).getId().equals(id)){
          neighbourhood.add(this.graph.getAdjacencies().get(k));
        }
      }
    }

    return neighbourhood;

  };

  /**
   * Gets all vertex incident edges.  
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public List<E> incidents(String id) throws NoSuchElementException{

    ArrayList<DirectedEdge<E>> incidents = new ArrayList<DirectedEdge<E>>();

    for (int i = 0; i < this.edges.size(); i++){
      if(this.edges.get(i).getInitialEnd().equals(id) ||
         this.edges.get(i).getFinalEnd().equals(id)){

        incidents.add(this.edges.get(i));

      }
    }

    if (incidents.size() == 0){
      throw new NoSuchElementException("There is no vertex with that id.");
    } else {
      return incidents;
    }

  };

  /**
   * Clones a graph into a new structure.
   * @return // A new graph clone.
   */
  public DirectedGraph<V,E> clone(){

  };

  /**
   * Gets a string graph representation.
   * @return // Graph converted to string.
   */
  public String toString(){

  };

  /**
   * 
   * @param edge
   * @return
   */
  public boolean addDirectedEdge(DirectedEdge<E> edge){
    return true;
  }

  /**
   * 
   * @param id
   * @param data
   * @param weight
   * @param iv
   * @param fv
   * @return
   */
  public boolean addDirectedEdge(String id, E data, double weight, String iv, String fv){
    
    for (int i = 0; i < this.edges.size(); i++){
      if (this.edges.get(i).getId().equals(id)){
        return false;
      }
    }

    DirectedEdge<E> e = new DirectedEdge<>();
    return this.addDirectedEdge(e); 
  }

  /**
   * 
   * @param id
   * @return
   */
  public boolean deleteDirectedEdge(String id){

    for (int i = 0; i < this.edges.size(); i++){
      if (this.edges.get(i).getId().equals(id)){
        this.edges.remove(i);
        return true;
      }
    }

    return false;

  }

  /**
   * 
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public DirectedEdge<E> getDirectedEdge(String id) throws NoSuchElementException{

    for (int i = 0; i < this.edges.size(); i++){
      if(this.edges.get(i).getId().equals(id)){
        return this.edges.get(i);
      }
    }

    throw new NoSuchElementException("There is no edges woth that id");

  }

  /**
   * 
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public int innerDegree(String id) throws NoSuchElementException{

  }

  /**
   * 
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public int outterDegree(String id) throws NoSuchElementException{
    
  }

  /**
   * 
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public ArrayList<Vertex<V>> sucessors(String id) throws NoSuchElementException{

    for(int i = 0; i < this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i).getAdjacencies();
      }
    }

    throw new NoSuchElementException("There is no vertex with that id.");

  }

  public ArrayList<Vertex<V>> predecessor(String id) throws NoSuchElementException{

    ArrayList<Vertex<V>> suc = new ArrayList<Vertex<V>>();

    for(int i = 0; i < this.graph.size(); i++){
      if(!this.graph.get(i).getId().equals(id)){
        for(int j = 0; j < this.graph.get(i).getAdjacencies().size(); j++){
          if(this.graph.get(i).getAdjacencies().get(j).getId().equals(id)){
            suc.add(this.graph.get(i));
          }
        }
      }
    }

    if(suc.size() > 0){
      return suc;
    } 
    
    throw new NoSuchElementException("There is no vertex with that index.");

  }

}