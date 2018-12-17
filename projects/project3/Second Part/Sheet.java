/**
 * ArrayList and Arrays
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Used for regex
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to implement the spreed sheet
 * @author Manuel Faria 15-10463
 * @author Juan Oropeza 15-11041
 */
public class Sheet{

    // Variable Declaration
    String[][] exprMatrix; // expressions matrix
    int[][] adjMatrix;  // adjacencies matrix
    private int count;
    private int[][] f;  // matrix that has the topological order
    private int[] colors;   
    private boolean hasCycle = false;   // boolean variable used to identify if a cicly has been detected
    private String head = "";   // first vertex of the cycle
    private String cycle = "";  // cycle
    private String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};   // letters used by getName

    /**
     * Empty constructor
     */
    Sheet(){
        
    }

    /**
     * Constructor
     * @param n number of rows
     * @param m number of columns
     */
    Sheet(int n, int m){
        exprMatrix = new String[n][m];
        adjMatrix = new int[n*m][n*m];
    }

    /**
     * Method used to get name of cell given it coords
     * @param row   row's index
     * @param column    column's index
     * @return name  of the cell
     */
    public String getName(int row, int column){

        // Get number of the name
        String output = Integer.toString(row + 1);
        
        // Calls recursive function
        output = nameRec(column, output) + output;

        return output;
    }

    /**
     * Recursively method to construct the name
     * @param number number used to get the name
     * @param output String thhat has the name
     * @return 
     */
    public String nameRec(int number, String output){
        
        // Checks if the number if more than 26 to divide it
        if(number < 26){
            return this.letters[number];
        } else{
            int div = number / 26 - 1;
            int mod = number % 26;
            // Calls the function
            return nameRec(div,output) + letters[mod];

        }
    }
    
    /**
     * Method that gets the cells used in a expression
     * @param expression expression to consider
     * @return list of cells used in the expression
     */
    public ArrayList<String> findPred(String expression){

        // Initializes output
        ArrayList<String> output = new ArrayList<>();

        // Regex to get the cells 
        Pattern pattern = Pattern.compile("[a-zA-Z]+\\d+");
        Matcher matcher = pattern.matcher(expression);

        // while to get the cells
        while(matcher.find()){
          output.add(matcher.group(0));
        }

        return output;
    }

    /**
     * Method to get the index of a cell using the name
     * @param input cell's name
     * @return index of the cell
     */
    public int nameToIndex(String input){

        // regex to get the separate letters and numbers
        String[] cell = input.split("[^a-zA-Z0-9]+|(?<=[a-zA-Z])(?=[0-9])|(?<=[0-9])(?=[a-zA-Z])");

        // Variable that has the exponent
        int exp = cell[0].length() - 1;
        int n = this.exprMatrix.length;

        // Initializes the output
        int output = -1;
        // Get the characters of the letters
        char[] characters = cell[0].toCharArray();
        
        // Iterate over the characters
        for(int i=0; i<characters.length; i++){

            // Add the value, using a 26 base numeric system
            output += (Character.getNumericValue(characters[i]) - 9) * Math.pow(26, exp); 
            exp--;
        }


        return output * n + Integer.parseInt(cell[1]) - 1;
    }    

    /**
     * Methos to get coord of a cell using the name
     * @param input cells' name
     * @return coord of the cell
     */
    public int[] nameToCoord(String input){
        
        // Initializes output
        int[] output = {0,-1};

        // Separate letters and numbers
        String[] cell = input.split("[^a-zA-Z0-9]+|(?<=[a-zA-Z])(?=[0-9])|(?<=[0-9])(?=[a-zA-Z])");

        // Variable that has the exponent
        int exp = cell[0].length() - 1;

        // Get the characters of the letters
        char[] characters = cell[0].toCharArray();
        
        // Iterate over the characters
        for(int i=0; i<characters.length; i++){

            // Add the value, using a 26 base numeric system, and add it to columns
            output[1] += (Character.getNumericValue(characters[i]) - 9) * Math.pow(26, exp); 
            exp--;
        }
        
        // Get value of column
        output[0] = Integer.parseInt(cell[1]) - 1 ;

        return output;
    }

    /**
     * Method to get index of an cell using it coord of the expressions matrix
     * @param index cells' index
     * @return coord of the index
     */
    public int[] indexToCoord(int index){

        int n = this.exprMatrix.length;

        // Initializes output
        int[] output = new int[2];

        // Gets row and column
        output[0] = index % n;
        output[1] = index / n;

        return output;
    }


    /**
     * Method to initialize recursive DFS method.
     */
    public int[][] DFS(){

        // Get number of nodes.
        this.count = this.adjMatrix.length;

        // Variables initialization.
        this.f = new int[this.adjMatrix.length][2];
        this.colors = new int[this.adjMatrix.length];
        Arrays.fill(this.colors, 0);

        // Apply DFS for all nodes.
        for(int i = 0; i < this.colors.length; i++){
          
          if(this.colors[i] == 0){
            this.DFS_recursive(i);
          }        

        }

        return f;

    } 

    /**
     * DFS recursive implementation.
     */
    public void DFS_recursive(int v){
        // Marks vertex as visited.
        this.colors[v] += 1;

        // Gets all sucessors
        ArrayList<Integer> suc = this.sucessors(v);

        int[] coordRoot = this.indexToCoord(v);
        String vertexRoot = this.getName(coordRoot[0],coordRoot[1]);


        // Applies DFS over the sucessors.
        for(int i = 0; i < suc.size(); i++){
            int w = suc.get(i);

            
            if(this.colors[w] == 1){
              this.hasCycle = true;
              int[] coord = this.indexToCoord(w);
              this.head = this.getName(coord[0],coord[1]);
              this.cycle = this.head;
              if(v != w){
                return;
              }
            }
            
            if(this.colors[w] == 0){
                this.DFS_recursive(w);
            }
            
            if(this.hasCycle){
              int[] coord = this.indexToCoord(w);
              String vertex = this.getName(coord[0],coord[1]);
              this.cycle = vertex + " -> " + this.cycle;
              if(vertex.equals(this.head)){
                System.out.println(this.cycle);
                System.exit(0);
              }
              if(vertexRoot.equals(this.head)){
                this.cycle = vertexRoot + " -> " + this.cycle;
                System.out.println(this.cycle);
                System.exit(0);
              }
            }  


        }
        // Save finalization time.
        this.colors[v] += 1;
        this.f[this.count - 1][0] = indexToCoord(v)[0];
        this.f[this.count - 1][1] = indexToCoord(v)[1];
        this.count -= 1;

    }

    /**
     * Get all vertices sucessors
     */
    private ArrayList<Integer> sucessors(int v){

        // Variable initialization.
        ArrayList<Integer> suc = new ArrayList<Integer>();

        // Iterates over vertexes.
        for(int i = 0; i < this.adjMatrix.length; i++){
            if(this.adjMatrix[v][i] == 1){
                suc.add(i);
            }
        }

        return suc;

    }

    

}