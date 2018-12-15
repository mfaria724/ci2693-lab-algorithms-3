/**
 * Reader
 */
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Exceptions  
 */ 
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class to implement expressions evaluator
 * @author Manuel Faria 15-10463
 * @author Juan Oropeza 15-11041
 */
public class Evaluador {

  /**
   * Stores graph structure
   */
  private static Graph graph;

  /**
   * Main routine
   * @param args Arguments passed by user
   */
  public static void main(String[] args) {
    if(args.length <= 0){
      System.out.println("Uso: java Evaluador <archivodeexpresiones>");
    }else{
      // Read specified file
      readGraph(args[0]);
    }
  }

  /**
   * Method to read each expression in file
   * @param path Path to file
   */
  private static void readGraph(String path){

    // Variables declaration.
    BufferedReader Lector; 
    String line;

    try {
      // Lector initialization
      Lector = new BufferedReader(new FileReader(path));

      // Read all lines
      while(true){
        line = Lector.readLine();

        // Last line, exits program.
        if(line == null){ System.exit(0); }

        // Initalize graph structure with root vertex
        graph = new Graph();
        Vertex v = new Vertex(line);  
        graph.addVertex(v);
        createTree(v);

        // Prints root value
        int rootElemValue = graph.adjacenciesList.get(0).getValue(); 
        System.out.println(rootElemValue);
      }

    } catch (FileNotFoundException e) {
      // File doesn't exist
      System.out.println("El archivo especificado no existe. Intente de nuevo.");
      System.exit(0);
    } catch (IOException e) {
      // I/O Error
      System.out.println("Ha ocurrido un error de E/S. Intente de nuevo.");
      System.exit(0);
    } catch (NumberFormatException e) {
      // Invalid file format
      System.out.println("Formato de archivo incorrecto. Intente de nuevo.");
      System.exit(0);
    } catch (Exception e){
      System.out.println("Formato de Archivo Inválido");
      System.exit(0);
    }

  }

  /**
   * Evaluates an expression
   * @param expression  expression to be evaluated
   * @return expression's value
   */
  public int evaluateExpression(String expression){

    // Initalize graph structure with root vertex
    graph = new Graph();
    Vertex v = new Vertex(expression);  
    graph.addVertex(v);
    try{
      createTree(v);
    } catch(Exception e){
      System.out.println("La expresion tiene un error");
      System.exit(0);
    }
    // Prints root value
    int rootElemValue = graph.adjacenciesList.get(0).getValue(); 


    return rootElemValue;
  }

  /**
   * Divides expression to create a graph (Binary Tree)
   * @param v Root vertex
   * @throws Exception If format has invalid format
   */
  public static void createTree(Vertex v)throws Exception{

    // Gets root expression.
    String expression = v.getExpression();

    // Checks if expression is a number.
    if(!expression.matches("[-]?[0-9]+")){
      // Get all expression characters.
      String[] expressionChars = expression.split("");
      
      // Checks operations to divide expression.
      checkInnerBinaryOp(v, expressionChars);
      
      // If value hasn't been setted, checks more operation types.
      if(!v.getHasValue()){
        checkOutterBinaryOp(v, expressionChars);
      }

      // If value hasn't been setted, checks more operation types.      
      if(!v.getHasValue()){
        checkOutterUnaryOp(v, expressionChars);
      }
    }else {
      // Sets value for a number
      v.setValue(Integer.parseInt(v.getExpression()));
    }
 
  }

  /**
   * Checks binary inner operators in expression to divide it. 
   * @param v Root vertex.
   * @param expressionChars Expression divided in chars.
   * @throws Exception If file has an invalid format.
   */
  private static void checkInnerBinaryOp(Vertex v, String[] expressionChars)throws Exception{

    // Operators to check
    String[] innerBinaryOp = {"+","-","*"};

    // Gets vertex expression.
    String expression = v.getExpression();

    // Checks all operators
    for(int i = 0; i < innerBinaryOp.length; i++){

      // Checks that operators are not in parenthesis.
      int level = 0;
      for(int j = expressionChars.length - 1; j > -1; j--){
        
        // Increment or decrement level to not consider operators inside
        // parenthesis.
        if(expressionChars[j].equals("(")){
          level++;
        }else if(expressionChars[j].equals(")")){
          level--;
        }

        // Operator has been found in a valid position.
        if(innerBinaryOp[i].equals(expressionChars[j]) && level == 0){
          // Saves main operation
          v.setOperation(innerBinaryOp[i]);
          
          // Left child
          // Gets first operand
          String leftChildStr = expression.substring(0,j);

          // Invalid file format
          if(leftChildStr.equals("")){ throw new Exception(); }

          // Creates vertex and its sub-tree.
          Vertex leftChild = new Vertex(leftChildStr);
          graph.addVertex(leftChild);
          v.addSucessor(leftChild);
          createTree(leftChild);

          // Right child 
          // Gets first operand
          String rightChildStr = expression.substring(j+1);

          // Invalid file format
          if(rightChildStr.equals("")){ throw new Exception(); }
          
          // Creates vertex and its sub-tree.
          Vertex rightChild = new Vertex(rightChildStr);
          graph.addVertex(rightChild);
          v.addSucessor(rightChild);
          createTree(rightChild);

          // Sets vertex value
          v.setValue(doOperation(leftChild.getValue(), rightChild.getValue(), v.getOperation()));
          return;
        }

      }
    }

  }

  /**
   * Checks binary outter operators in expression to divide it. 
   * @param v Root vertex.
   * @param expressionChars Expression divided in chars.
   * @throws Exception If file has an invalid format.
   */
  private static void checkOutterBinaryOp(Vertex v, String[] expressionChars) throws Exception{

    // Operators to check
    String[] outterBinaryOp = {"MAX","MIN"};

    // Gets vertex expression.
    String expression = v.getExpression();

    // Checks all operators
    for(int i = 0; i < outterBinaryOp.length; i++){
      // Checks first 3 characters of the expression.
      if(expression.substring(0,3).equals(outterBinaryOp[i])){

        // Saves operation in vertex.
        v.setOperation(outterBinaryOp[i]);

        // Gets operands
        String operand = expression.substring(4,expressionChars.length - 1);
        String[] operandChars = operand.split("");
        
        // Iterates over chars to get both operands
        int level = 0;
        for(int k = 0; k < operandChars.length; k++){
          if(operandChars[k].equals("(")){
            level++;
          }else if(operandChars[k].equals(")")){
            level--;
          }

          if(operandChars[k].equals(",") && level == 0){

            // Left child
            // Gets first operand
            String leftChildStr = operand.substring(0,k);

            // Invalid file format
            if(leftChildStr.equals("")){ throw new Exception(); }
            
            // Creates vertex and its sub-tree.
            Vertex leftChild = new Vertex(leftChildStr);
            graph.addVertex(leftChild);
            v.addSucessor(leftChild);
            createTree(leftChild);

            // Right child 
            // Gets first operand
            String rightChildStr = operand.substring(k+1);

            // Invalid file format
            if(rightChildStr.equals("")){ throw new Exception(); }

            // Creates vertex and its sub-tree.
            Vertex rightChild = new Vertex(rightChildStr);
            graph.addVertex(rightChild);
            v.addSucessor(rightChild);
            createTree(rightChild);

            // Sets vertex value
            v.setValue(doOperation(leftChild.getValue(), rightChild.getValue(), v.getOperation()));

            return;
          }

        }
        // Invalid format
        throw new Exception();
      }
    }

  }

  /**
   * Checks unary outter operators in expression to divide it. 
   * @param v Root vertex.
   * @param expressionChars Expression divided in chars.
   * @throws Exception If file has an invalid format.
   */
  private static void checkOutterUnaryOp(Vertex v, String[] expressionChars)throws Exception{

    // Operators to check
    String[] outterUnaryOp = {"SUM"};

    // Gets vertex expression.
    String expression = v.getExpression();

    // Checks all operators
    for(int i = 0; i < outterUnaryOp.length; i++){

      // Checks first 3 characters of the expression.
      if(expression.substring(0,3).equals(outterUnaryOp[i])){
        
        // Saves operation in vertex.
        v.setOperation(outterUnaryOp[i]);

        // Child 
        // Gets  operand
        String childStr = expression.substring(4, expressionChars.length - 1);
        
        // Invalid file format
        if(childStr.equals("")){ throw new Exception(); }

        // Creates vertex and its sub-tree.
        Vertex child = new Vertex(childStr);
        graph.addVertex(child);
        v.addSucessor(child);
        createTree(child);

        // Sets vertex value
        v.setValue(doOperation(child.getValue(), 0, v.getOperation()));

        return;
      }
    }

  }

  /**
   * Do built-in operations 
   * @param op1 First operand
   * @param op2 Second operand (Provide neutro if operation is unary)
   * @param operation Operation symbol
   * @return Value of evaluated operation
   */
  private static int doOperation(int op1, int op2, String operation){

    // Checks operation
    if(operation.equals("+")){
      // Binary sum
      return op1 + op2;
    } else if(operation.equals("-")){
      // Binary substraction
      return op1 - op2;
    } else if(operation.equals("*")){
      // Binary multiplication
      return op1 * op2;
    } else if(operation.equals("MAX")){
      // Maximun
      return Math.max(op1, op2);
    } else if(operation.equals("MIN")){
      // Minimun
      return Math.min(op1, op2);
    } else if(operation.equals("SUM")){
      // Unary sum
      if(op1 > 0){
        int sum = 0;
        for(int i = 1; i <= op1; i++){
          sum += i;
        }
        return sum;
      }else if(op1 < 0){
        int sum = 0;
        for(int i = -1; i >= op1; i--){
          sum += i;
        }
        return sum;
      }else{
        // Other case
        return 0;
      }
    }else{
        // Other case
      return 0;
    }
  }

}