import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class UndirectedGraph<V,E> {

    private ArrayList<Vertex<V>> graph;
    private ArrayList<SimpleEdge<E>> edges;
  
    UndirectedGraph(){
      graph = new ArrayList<Vertex<V>>();
      edges = new ArrayList<SimpleEdge<E>>();
    }
  /**
   * Opens file and upload its data into Adjacencies List implementation of graph.  
   * @param g // ToDo
   * @param file // File that user wants to open.
   * @return true if the file was opened correctly, othercase false.
   */
  public boolean loadGraph(String file){
    return true;
  }

  /**
   * Gets the number of vertices in a graph. 
   * @param g // Graphs that user wants to know the number of vertices.
   * @return // The number of vertices.
   */
  public Integer numVertices(){
    return this.graph.size();
  }

  /**
   * Gets the number of edges in a graph.
   * @param g // Graphs that user wants to know the number of edges.
   * @return // The number of edges.
   */
  public Integer numEdges(){
    
    return this.edges.size();
  }

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
   * @param g // Graph that user wants to add the vertex.
   * @param v // Vertex that user wants to add to graph.
   * @return // true if the vertex was added, othercase false.
   */  
  public boolean addVertex(Vertex<V> v){

    for(int i=0; i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(v.getId())){
        return false;
      }
    }

    this.graph.add(v);    

    return true;
    }

  /**
   * Add a new vertex to the graph.
   * @param g // Graph that user wants to add the vertex.
   * @param id // Id of the vertex.
   * @param data // Value of vertex.
   * @param weight // Weight of the vertex.
   * @return // true if the vertex was added, other case returns true.
   */
  public boolean addVertex(String id, V data, double weight){

    Vertex<V> v = new Vertex<V>(id, data, weight)
    return addVertex(v);

  }

  /**
   * Gets the vertex with the specified id.
   * @param g // Graph to search the vertex
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public V getVertex(String id) throws NoSuchElementException{

    for(int i=0;i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i);
      }
    }
    throw new NoSuchElementException("Vertex doesn't exist");
  }

  /**
   * Verifies if a vertex is in the graph.
   * @param g // Graph to search the vertex.
   * @param id // Vertex id.
   * @return // true if the vertex is in the g, othercase false.
   */
  public boolean containsVertex(String id){

    for(int i=0;i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return true;
      }
    }

    return false;
  }

  public boolean addSimpleEdge(SimpleEdge<E> e){

    for(int i=0;i<edges.size();i++){
      if(edges.get(i).getId().equals(e.getId())){
        return false;
      }
    }

    this.edges.add(e);

    return true;
  }

  public boolean addSimpleEdge(String id, E data, double weight, String u, String v){
    SimpleEdge<E> e = new SimpleEdge<E>(id, data, weight, u, v);
    return addSimpleEdge(e);
  }
  /**
   * Verifies if a edge is in the graph.
   * @param g // Graph to search the edge.
   * @param id1 // End 1 id
   * @param id2 // End 2 id
   * @return // true if the edge is in the graph, othercase false.
   */
  public boolean cotainsEdge(String id1, String id2){
    for(int i=0; i<this.edges.size();i++){
      if((this.edges.get(i).getEnd1().getId().equals(id1) && this.edges.get(i).getEnd2().getId().equals(id2))
      || (this.edges.get(i).getEnd1().getId().equals(id2) && this.edges.get(i).getEnd2().getId().equals(id1))){
        return true;
      }
    }

    return false;
  }

  /**
   * Deletes a vertex in a graph.
   * @param g // Graphs to delete the vertex.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public boolean deleteVertex(String Id){
    boolean result = false;
    for(int i=0;i<this.graph.size(); i++){
      for(int j=0;j<this.graph.get(i).getAdjacencies().size(); j++){
        if(this.graph.get(i).getAdjacencies().get(j).getId().equals(Id)){
          result = true;
        }
      }
    }

    for(int i=0; i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(Id)){
        this.graph.remove(i);
      }
    }
    for(int i=0; i<this.edges.size(); i++){
      if(this.edges.get(i).getEnd1().getId().equals(Id) || this.edges.get(i).getEnd2().getId().equals(Id)){
        this.edges.remove(i);
      }
    }
    
    return result;
  }

  /**
   * Gets the list of vertices.
   * @param g // Graph to consider.
   * @return // The list of vertices.
   */
  public List<Vertex<V>> vertices(){
    List<Vertex<V>> vertices = new List<Vertex<V>>();

    for(int i=0; i<this.graph.size(); i++){
      vertices.add(this.graph.get(i));
    }

    return vertices;
  }
  
  /**
   * Gets the list of edges.
   * @param g // Graph to consider.
   * @return //  The list of edges.
   */
  public List<Edge<E>> edges(){
    List<Edge<E>> out = new List<Edge<E>>();

    for(int i=0; i<this.edges.size(); i++){
      edges.add(this.edges.get(i));
    }

    return out;
  }

  /**
   * Gets the degree of one vertex in the graph.
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(String id){
    for(int i=0; i<this.graph.size();i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i).getAdjacencies().size();
      }
    }
  }

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public List<Vertex> adjacents(String id) throws NoSuchElementException{
    List<Vertex<V>> adjacents = new List<Vertex<V>>();

    for(int i=0;i<this.graph.size();i++){

    }
  }

  /**
   * Gets all vertex incident edges.  
   * @param g // Graph to consider.
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public List<E> incidents(String id) throws NoSuchElementException{

  }

  /**
   * Clones a graph into a new structure.
   * @param g // Graph to clone.
   * @return // A new graph clone.
   */
  public UndirectedGraph<V,E> clone(){

  }

  /**
   * Gets a string graph representation.
   * @param g // Graph to convert in string.
   * @return // Graph converted to string.
   */
  public String toString(){

  }
}