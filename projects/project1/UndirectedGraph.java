import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

public class UndirectedGraph<V,E> implements Graph<V,E>{

  private ArrayList<Vertex<V>> graph;
  private ArrayList<SimpleEdge<E>> edges;
  
    UndirectedGraph(){
      graph = new ArrayList<Vertex<V>>();
      edges = new ArrayList<SimpleEdge<E>>();
    }
  /**
   * 
   * @param file
   * @param numVertices
   * @param numEdges
   * @param verTrans
   * @param edgeTrans
   * @return
   * @throws IOException
   */
  public boolean loadGraph(String file, int numVertices, int numEdges, TypeTransformer<V> verTrans, TypeTransformer<E> edgeTrans) throws IOException{
    
    System.out.println("loadGraph function");

    // Initialice BufferReader
    BufferedReader reader = new BufferedReader(new FileReader(file));

    String line = reader.readLine();

    for(int i = 0; i < 5; i++){
      line = reader.readLine();
    }

    System.out.println("Vertex initializations.");

    for(int i = 0; i < numVertices; i++){

      String[] info = line.split(" ");
      String id = info[0];
      V data = verTrans.transform(info[1]);
      Double weight = Double.parseDouble(info[2]);

      this.addVertex(id, data, weight);

      line = reader.readLine();
    }

    System.out.println("Edge initializations." + numEdges);

    for(int i = 0; i < numEdges; i++){

      String[] info = line.split(" ");
      String id = info[0];
      E data = edgeTrans.transform(info[1]);
      Double weight = Double.parseDouble(info[2]);
      String v1 = info[3];
      String v2 = info[4];

      System.out.println("Voy a agregar lado " + i);
      System.out.println("Id: " + id);
      System.out.println("Data: " + data);
      System.out.println("Weight: " + weight);
      System.out.println("V1: " + v1);
      System.out.println("V2: " + v2);

      this.addSimpleEdge(id, data, weight, v1, v2);

      line = reader.readLine();
    }

    System.out.println("Before return");
    return true;
  }

  /**
   * Gets the number of vertices in a graph. 
   * @return // The number of vertices.
   */
  public Integer numVertices(){
    return this.graph.size();
  }

  /**
   * Gets the number of edges in a graph.
   * @return // The number of edges.
   */
  public Integer numEdges(){
    
    return this.edges.size();
  }

  /**
   * Adds an existing vertex to the graph. If there is no vertex with
   * that id, it creates the vertex.
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
   * @param id // Id of the vertex.
   * @param data // Value of vertex.
   * @param weight // Weight of the vertex.
   * @return // true if the vertex was added, other case returns true.
   */
  public boolean addVertex(String id, V data, double weight){

    Vertex<V> v = new Vertex<V>(id, data, weight);
    return addVertex(v);

  }

  /**
   * Gets the vertex with the specified id.
   * @param id // Vertex id.
   * @return // The vertex.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public Vertex<V> getVertex(String id) throws NoSuchElementException{

    for(int i=0;i<this.graph.size(); i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i);
      }
    }
    throw new NoSuchElementException("Vertex doesn't exist");
  }

  /**
   * Verifies if a vertex is in the graph.
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
  /**
   * 
   * @param e
   * @return
   */
  public boolean addSimpleEdge(SimpleEdge<E> e){

    for(int i=0;i<edges.size();i++){
      if(edges.get(i).getId().equals(e.getId())){
        return false;
      }
    }

    this.edges.add(e);

    return true;
  }

  /**
   * 
   * @param id
   * @param data
   * @param weight
   * @param u
   * @param v
   * @return
   */
  public boolean addSimpleEdge(String id, E data, double weight, String v1, String v2){
    SimpleEdge<E> e = new SimpleEdge<E>(id, data, weight, v1, v2);
    return addSimpleEdge(e);
  }

  /**
   * Verifies if a edge is in the graph.
   * @param id1 // End 1 id
   * @param id2 // End 2 id
   * @return // true if the edge is in the graph, othercase false.
   */
  public boolean cotainsEdge(String id1, String id2){
    for(int i=0; i<this.edges.size();i++){
      if((this.edges.get(i).getEnd1().equals(id1) && this.edges.get(i).getEnd2().equals(id2))
      || (this.edges.get(i).getEnd1().equals(id2) && this.edges.get(i).getEnd2().equals(id1))){
        return true;
      }
    }

    return false;
  }

  /**
   * Deletes a vertex in a graph.
   * @param Id // Vertex's id.
   * @return // true if the vertex was deleted, othercase false.
   */
  public boolean deleteVertex(String Id){
    boolean result = false;
    for(int i=0;i<this.graph.size(); i++){
      for(int j=0;j<this.graph.get(i).getAdjacencies().size(); j++){
        if(this.graph.get(i).getAdjacencies().get(j).getId().equals(Id)){
          ArrayList<Vertex<V>> newAdj = this.graph.get(i).getAdjacencies();
          newAdj.remove(j);
          this.graph.get(i).setAdjacencies(newAdj);
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
      if(this.edges.get(i).getEnd1().equals(Id) || this.edges.get(i).getEnd2().equals(Id)){
        this.edges.remove(i);
      }
    }
    
    return result;
  }

  /**
   * Gets the list of vertices.
   * @return // The list of vertices.
   */
  public ArrayList<Vertex<V>> vertices(){
    ArrayList<Vertex<V>> vertices = new ArrayList<Vertex<V>>();

    for(int i=0; i<this.graph.size(); i++){
      vertices.add(this.graph.get(i));
    }

    return vertices;
  }
  
  /**
   * Gets the list of edges.
   * @return //  The list of edges.
   */
  public ArrayList<Edge<E>> edges(){
    ArrayList<Edge<E>> out = new ArrayList<Edge<E>>();

    for(int i=0; i<this.edges.size(); i++){
      edges.add(this.edges.get(i));
    }

    return out;
  }

  /**
   * Gets the degree of one vertex in the graph.
   * @param id // Vertex's id.
   * @return // Vertex's degree.
   */
  public Integer degree(String id) throws NoSuchElementException{
    for(int i=0; i<this.graph.size();i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i).getAdjacencies().size();
      }
    }

    throw new NoSuchElementException("Vertex doesn't exist");
  }

  /**
   * Gets all adjacent vertices to a vertex. 
   * @param id // Vertex's id.
   * @return // the list of adjacent vertices.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */

  public ArrayList<Vertex<V>> neighbourhood(String id) throws NoSuchElementException{
  
    for(int i=0;i<this.graph.size();i++){
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i).getAdjacencies();
      }
    }

    throw new NoSuchElementException("Vertex doesn't exist");
  }

    

  /**
   * Gets all vertex incident edges.  
   * @param id // Vertex's id.
   * @return // The list of edges.
   * @throws NoSuchElementException // If there is no vertex with that id.
   */
  public ArrayList<Edge<E>> incidents(String id) throws NoSuchElementException{

    ArrayList<Edge<E>> incidents = new ArrayList<Edge<E>>();
    for(int i=0;i<this.edges.size(); i++){
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
  public UndirectedGraph<V,E> clone(){
    UndirectedGraph<V,E> clone = new UndirectedGraph<V,E>();

    for(int i=0; i<this.graph.size(); i++){
      clone.graph.add(this.graph.get(i));
    }

    for(int i=0; i<this.edges.size(); i++){
      clone.edges.add(this.edges.get(i));
    }

    return clone;
  }

  /**
   * 
   * @param id
   * @return
   */
  public boolean deleteSimpleEdge(String id){
    String v1 ="";
    String v2 ="";
    boolean result = false;
    for(int i=0; i<this.edges.size();i++){
      if(this.edges.get(i).getId().equals(id)){
        v1 = this.edges.get(i).getEnd1();
        v2 = this.edges.get(i).getEnd2();
        result = true;
        this.edges.remove(i);
      }
    }

    if(result){
      for(int i=0; i<this.graph.size();i++){
        if(this.graph.get(i).getId().equals(v1)){
          for(int j=0;j<this.graph.get(i).getAdjacencies().size();j++){
            if(this.graph.get(i).getAdjacencies().get(j).getId().equals(v2)){
              ArrayList<Vertex<V>> newAdj = this.graph.get(i).getAdjacencies();
              newAdj.remove(j);
              this.graph.get(i).setAdjacencies(newAdj);
            } 
          }
        }
        if(this.graph.get(i).getId().equals(v2)){
          for(int j=0;j<this.graph.get(i).getAdjacencies().size();j++){
            if(this.graph.get(i).getAdjacencies().get(j).getId().equals(v1)){
              ArrayList<Vertex<V>> newAdj = this.graph.get(i).getAdjacencies();
              newAdj.remove(j);
              this.graph.get(i).setAdjacencies(newAdj);
            }
          }
        }
      }
    }

    return result;
  }
  /**
   * 
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public Edge<E> getSimpleEdge(String id) throws NoSuchElementException{
    for(int i=0; i<this.edges.size();i++){
      if(this.edges.get(i).getId().equals(id)){
        return this.edges.get(i);
      }
    }

    throw new NoSuchElementException("Edge doesn't exist");
  }

  /**
   * Gets a string graph representation.
   * @return // Graph converted to string.
   */
  public String toString(){

    String stringGraph = "";

    stringGraph += this.getVertexType(this.graph.get(0).getData()) + "\n";
    stringGraph += this.getEdgeType(this.edges.get(0).getData()) + "\n";
    stringGraph += "N \n";
    stringGraph += this.graph.size() + "\n";
    stringGraph += this.edges.size() + "\n";
    
    for(int i = 0; i < this.graph.size(); i++){
      Vertex v = this.graph.get(i);
      stringGraph += v.getId() + " ";
      stringGraph += v.getData() + " ";
      stringGraph += v.getWeight() + "\n";
    }

    for(int j = 0; j < this.edges.size(); j++){
      SimpleEdge e = this.edges.get(j);
      stringGraph += e.getId() + " ";
      stringGraph += e.getData() + " ";
      stringGraph += e.getWeight() + " ";
      stringGraph += e.getEnd1() + " ";
      stringGraph += e.getEnd2() + "\n";
    }

    return stringGraph;

  }

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
}
