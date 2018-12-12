import java.util.ArrayList;

/**
 * Class to implement all vertex properties.
 * @author Manuel Faria 15-10463
 * @author Juan Oropeza 15-11041
 */
public class Vertex {

  private String expression; // Expression to be evaluated.
  private boolean hasValue = false; // Value has been setted.
  private String operation; // Tipe of the main operation.
  private int value; // Evaluated expression value.
  private ArrayList<Vertex> sucessors; // Sucessors (Adjacencies List)

  /**
   * Constructor (Vertex Initialization)
   * @param expression
   */
  Vertex(String expression){
    this.expression = expression;
    this.sucessors = new ArrayList<Vertex>();
  }

  /**
   * Expression setter
   * @return Expression's value
   */
  public String getExpression(){
    return this.expression;
  }

  /**
   * HasValue setter
   * @return true if value has been setted, false other case.
   */
  public boolean getHasValue(){
    return this.hasValue;
  }
  
  /**
   * Operation getter
   * @return Vertex main operation
   */
  public String getOperation(){
    return this.operation;
  }
  
  /**
   * Operation setter
   * @param op Operation for vertex
   */
  public void setOperation(String op){
    this.operation = op;
  }
  
  /**
   * Value getter
   * @return Expression evaluated value
   */
  public int getValue(){
    return this.value;
  }
  
  /**
   * Value setter
   * @param value Expression's value
   */
  public void setValue(int value){
    this.value = value;
    this.hasValue = true;
  }

  /**
   * Adds sucessors for adjacencies list.
   * @param v Sucessor Vertex object
   */
  public void addSucessor(Vertex v){
    this.sucessors.add(v);
  }

}