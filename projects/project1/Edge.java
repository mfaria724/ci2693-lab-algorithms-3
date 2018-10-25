/**
 * Abstract class that has common classes for Directed and Simple edges
 * @param <T> // Type of data edge
 */
public abstract class Edge<T> {

    protected String id;
    protected T data;
    protected double weight;
    protected Vertex<U> v1;
    protected Vertex<U> v2;

    /**
     * Create new edge with a id, data and weight
     * @param id // Edge's id
     * @param data // Edge's data
     * @param p // Edge's weight
     * @return  // Edge
     */
    protected Edge<T> createEdge(String id,T data, double p){

        Edge<T> e = new Edge<T>();
        e.id = id;
        e.data = data;
        e.weight = weight;
        
        return e;
    }

    /**
     * Get the weight of an egde
     * @param e // Edge to consider
     * @return // Edge's weight
     */
    protected double getWeight(Edge<T> e){

        return e.weight;
    }

    /**
     * Get id of an Edge
     * @param e // Edge to consider
     * @return // Edge's id
     */
    protected String getId(Edge<T> e){

        return e.id;
    }

    /**
     * Get data of an Edge
     * @param e // Edge to consider
     * @return // Edge's data
     */
    protected T getData(Edge<T> e){

        return e.data;
    }
    
    /**
     * Get representation of an egde in string
     * @param e // Egde to consider
     * @return // Edge's representation
     */
    protected abstract String toString(Edge<T> e);
}