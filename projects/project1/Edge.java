/**
 * Abstract class that has common classes for Directed and Simple edges
 * @param <T> // Type of data edge
 */
public abstract class Edge<T> {

    // Edge data.
    protected String id;
    protected T data;
    protected double weight;
    protected String v1;
    protected String v2;

    /**
     * Get the weight of an egde
     * @return // Edge's weight
     */
    protected double getWeight(){
        return this.weight;
    }

    /**
     * Get id of an Edge
     * @return // Edge's id
     */
    protected String getId(){
        return this.id;
    }

    /**
     * Get data of an Edge
     * @return // Edge's data
     */
    protected T getData(){
        return this.data;
    }
    
    /**
     * Get representation of an egde in string
     * @return // Edge's representation
     */
    public abstract String toString();

}