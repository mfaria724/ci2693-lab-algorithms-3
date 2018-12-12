import java.util.ArrayList;

/**
 * Class to implement graph structure.
 */
public class Graph {

  /**
   * Adjacencies list to represent graph structure.
   */
  public ArrayList<Vertex> adjacenciesList;

  /**
   * Constructor (Graph Initialization)
   */
  Graph(){
    this.adjacenciesList = new ArrayList<Vertex>();
  }

  /**
   * Adds a vertex to the graph.
   * @param v Vertex to be added.
   */
  public void addVertex(Vertex v){
    this.adjacenciesList.add(v);
  }

}