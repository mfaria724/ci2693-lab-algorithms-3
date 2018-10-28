import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to implement all TAD Directed
 * @param <V>
 * @param <E>
 */
public class DirectedGraph<V,E> implements Graph<V,E> {

  private ArrayList<Vertex<V>> graph; 
  private ArrayList<DirectedEdge<E>> edges;

  DirectedGraph(){
    this.graph = new ArrayList<Vertex<V>>();
    this.edges = new ArrayList<DirectedEdge<E>>();
  }

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

      this.addDirectedEdge(id, data, weight, v1, v2);

      line = reader.readLine();
    }

    System.out.println("Before return");
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
    
    Integer nEdges = 0;
    
    for (int i = 0; i < this.graph.size(); i++){
      nEdges += this.graph.get(i).getAdjacencies().size();
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

    Vertex<V> v = new Vertex<V>(id, data, weight);
    result = this.addVertex(v);

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
          if (this.graph.get(i).getAdjacencies().get(j).getId() == v2){
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
  public boolean deleteVertex(String id){

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
        this.graph.remove(i);
      }
    }

    return true;

  };

  /**
   * Gets the list of vertices.
   * @param g // Graph to consider.
   * @return // The list of vertices.
   */
  public ArrayList<Vertex<V>> vertices(){

    return new ArrayList<Vertex<V>>();

  };
  
  /**
   * Gets the list of edges.
   * @param g // Graph to consider.
   * @return //  The list of edges.
   */
  public ArrayList<Edge<E>> edges(){

    ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>();

    for(int i = 0; i < this.edges.size(); i++){
      edges.add(this.edges.get(i));
    }
    return edges;

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
  public ArrayList<Vertex<V>> neighbourhood(String id) throws NoSuchElementException{

    ArrayList<Vertex<V>> neighbourhood = new ArrayList<Vertex<V>>();

    for (int i = 0; i < this.graph.size(); i++){
      if (this.graph.get(i).getId() == id){
        neighbourhood.addAll(this.graph.get(i).getAdjacencies());
      }
    }

    for (int j = 0; j < this.graph.size(); j++){
      for (int k = 0; k < this.graph.get(j).getAdjacencies().size(); k++){
        if (this.graph.get(j).getAdjacencies().get(k).getId().equals(id)){
          neighbourhood.add(this.graph.get(j).getAdjacencies().get(k));
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
  public ArrayList<Edge<E>> incidents(String id) throws NoSuchElementException{

    ArrayList<Edge<E>> incidents = new ArrayList<Edge<E>>();

    // for (int i = 0; i < this.edges.size(); i++){
    //   if(this.edges.get(i).getInitialEnd().equals(id) ||
    //      this.edges.get(i).getFinalEnd().equals(id)){

    //     incidents.add(this.edges.get(i));

    //   }
    // }

    if (incidents.size() == 0){
      throw new NoSuchElementException("There is no vertex with that id.");
    } else {
      return incidents;
    }

  };

  /**
   * Clones a graph into a new structure.
   * @return // A new graph clone.
  //  */
  // public DirectedGraph<V,E> clone(){

  // };

  /**
   * Gets a string graph representation.
   * @return // Graph converted to string.
   */
  public String toString(){

    String stringGraph = "";

    stringGraph += this.getVertexType(this.graph.get(0).getData()) + "\n";
    stringGraph += this.getEdgeType(this.edges.get(0).getData()) + "\n";
    stringGraph += "D \n";
    stringGraph += this.graph.size() + "\n";
    stringGraph += this.edges.size() + "\n";
    
    for(int i = 0; i < this.graph.size(); i++){
      Vertex v = this.graph.get(i);
      stringGraph += v.getId() + " ";
      stringGraph += v.getData() + " ";
      stringGraph += v.getWeight() + "\n";
    }

    for(int j = 0; j < this.edges.size(); j++){
      DirectedEdge e = this.edges.get(j);
      stringGraph += e.getId() + " ";
      stringGraph += e.getData() + " ";
      stringGraph += e.getWeight() + " ";
      stringGraph += e.getInitialEnd() + " ";
      stringGraph += e.getFinalEnd() + "\n";
    }

    return stringGraph;
  };

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

  /**
   * 
   * @param edge
   * @return
   */
  public boolean addDirectedEdge(DirectedEdge<E> edge){

    this.edges.add(edge);
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
    
    System.out.println("DIRECTED EDGE ANTES");
    System.out.println("Voy a agregar lado " + id);
    System.out.println("Id: " + id);
    System.out.println("Data: " + data);
    System.out.println("Weight: " + weight);
    System.out.println("V1: " + iv);
    System.out.println("V2: " + fv);
    System.out.println("Size: " + this.edges.size());

    // for (int i = 0; i < this.edges.size(); i++){
    //   System.out.println("Iterador: " + i);
    //   System.out.println("Id actual: " + this.edges.get(i).getId());
    //   if (this.edges.get(i).getId().equals(id)){
    //     return false;
    //   }
    // }

    System.out.println("DIRECTED EDGE DESPUES");
    System.out.println("Voy a agregar lado " + id);
    System.out.println("Id: " + id);
    System.out.println("Data: " + data);
    System.out.println("Weight: " + weight);
    System.out.println("V1: " + iv);
    System.out.println("V2: " + fv);

    DirectedEdge<E> e = new DirectedEdge<E>(id, data, weight, iv, fv);
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
  public Edge<E> getDirectedEdge(String id) throws NoSuchElementException{

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

    int degree = 0;
    boolean exist = false;

    for(int i = 0; i < this.graph.size(); i++){
      if(!this.graph.get(i).getId().equals(id)){
        for(int j = 0; j < this.graph.get(i).getAdjacencies().size(); j++){
          if(this.graph.get(i).getAdjacencies().get(j).getId().equals(id)){
            degree += 1;
            exist = true;
          }
        }
      }
    }

    if(exist){
      return degree;
    }

    throw new NoSuchElementException("There is no vertex with that id.");

  }

  /**
   * 
   * @param id
   * @return
   * @throws NoSuchElementException
   */
  public int outterDegree(String id) throws NoSuchElementException{
    
    for(int i = 0; i < this.graph.size(); i++){ 
      if(this.graph.get(i).getId().equals(id)){
        return this.graph.get(i).getAdjacencies().size();
      }
    }

    throw new NoSuchElementException("There is no vertex with that id.");

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