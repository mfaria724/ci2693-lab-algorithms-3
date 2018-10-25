/**
 * 
 */
public class SimpleEdge<T> extends Edge<T> {
  
  /**
   * 
   * @param id
   * @param data
   * @param weight
   * @param u
   * @param v
   * @return
   */
  public Edge<T> createSimpleEdge(String id, T data, double weight, Vertex<T> u, Vertex<T> v){
    Edge<T> e = new Edge<T>();
    e.id = id;
    e.data = data;
    e.weight = weight;
    e.v1 = u;
    e.v2 = v;
    return e;
  }

  /**
   * 
   * @param e
   * @return
   */
  public Vertex<T> getEnd1(Edge<T> e){
    
    return e.v1; 
  }

  /**
   * 
   * @param e
   * @return
   */
  public Vertex<T> getEnd2(Edge<T> e){

    return e.v2;
  }

  /**
   * 
   * @param e
   * @return
   */
  public String toString(Edge<T> e){
    String eString = "";
    eString = "Edge's id: " + e.id + "\n";
    eString = "Edge's data: " + e.data + "\n";
    eString = "Edge's weight: " + e.weight + "/n";
    eString = "Edge's vertex 1: " + e.v1 + "\n";
    eString = "Edge's vertex 2: " + e.v2 + "/n";

    return eString;
  }

}