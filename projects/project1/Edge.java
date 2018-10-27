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
     * Get the weight of an egde
     * @param e // Edge to consider
     * @return // Edge's weight
     */
    protected double getWeight(){

        return this.weight;
    }

    /**
     * Get id of an Edge
     * @param e // Edge to consider
     * @return // Edge's id
     */
    protected String getId(){

        return this.id;
    }

    /**
     * Get data of an Edge
     * @param e // Edge to consider
     * @return // Edge's data
     */
    protected T getData(){

        return this.data;
    }
    
    /**
     * Get representation of an egde in string
     * @param e // Egde to consider
     * @return // Edge's representation
     */
    public abstract String toString();

}