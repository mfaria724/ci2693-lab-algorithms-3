import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sheet{

    String[][] exprMatrix;
    int[][] adjMatrix;
    private int count;
    private int[][] f;
    private int[] colors;
    private boolean hasCycle = false;
    private String head = "";
    private String cycle = "";
    private String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


    Sheet(){
        
    }

    Sheet(int n, int m){
        exprMatrix = new String[n][m];
        adjMatrix = new int[n*m][n*m];
    }
    
    public String getName(int row, int column){

        String output = Integer.toString(row + 1);
        
        output = nameRec(column, output) + output;

        return output;
    }

    public String nameRec(int number, String output){
        
        if(number < 26){
            return this.letters[number];
        } else{
            int div = number / 26 - 1;
            int mod = number % 26;
            return nameRec(div,output) + letters[mod];

        }
    }
    
    public ArrayList<String> findPred(String expression){
        ArrayList<String> output = new ArrayList<>();

        Pattern pattern = Pattern.compile("[a-zA-Z]+\\d+");
        Matcher matcher = pattern.matcher(expression);

        while(matcher.find()){
            output.add(matcher.group(0));
        }

        return output;
    }

    public int nameToIndex(String input){
        String[] cell = input.split("[^a-zA-Z0-9]+|(?<=[a-zA-Z])(?=[0-9])|(?<=[0-9])(?=[a-zA-Z])");
        // System.out.println(cell[0] + " " + cell[1]);


        int exp = cell[0].length() - 1;
        int n = this.exprMatrix.length;

        int output = -1;
        char[] characters = cell[0].toCharArray();
        
        for(int i=0; i<characters.length; i++){
            // System.out.println(Character.getNumericValue(characters[i]));
            output += (Character.getNumericValue(characters[i]) - 9) * Math.pow(26, exp); 
            exp--;
        }

        // System.out.println("solo letters " + output);
        // System.out.println("con numeros " + (output * n + Integer.parseInt(cell[1]) - 1))
        return output * n + Integer.parseInt(cell[1]) - 1;  // = x                     output = (x + 1 - cell[1] ) / n
    }    

    public int[] nameToCoord(String input){
        int[] output = {0,-1};

        String[] cell = input.split("[^a-zA-Z0-9]+|(?<=[a-zA-Z])(?=[0-9])|(?<=[0-9])(?=[a-zA-Z])");
        // System.out.println(cell[0] + " " + cell[1]);


        int exp = cell[0].length() - 1;
        
        // System.out.println(cell[0]);
        char[] characters = cell[0].toCharArray();
        
        for(int i=0; i<characters.length; i++){
            output[1] += (Character.getNumericValue(characters[i]) - 9) * Math.pow(26, exp); 
            exp--;
        }
        
        output[0] = Integer.parseInt(cell[1]) - 1 ;
        // System.out.println("solo letters " + output);
        // System.out.println("con numeros " + (output * n + Integer.parseInt(cell[1]) - 1));
        return output;
    }

    public int[] indexToCoord(int index){
        int n = this.exprMatrix.length;
        int[] output = new int[2];

        output[0] = index % n;
        output[1] = index / n;
        // System.out.println(n +  " " + index);
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
        
            try {
                if(this.colors[i] == 0){
                    this.DFS_recursive(i);
                }        
            } catch (Exception e) {
                if(e.getMessage().equals("adjMatrix has cycle")){
                    System.out.println("IMPOSSIBLE");
                    System.exit(0);
                }
            }

        }

        // Prints the result to user.

        return f;
        // for(int i = 0; i < this.f.length; i++){
        //     System.out.println(this.f[i][0] + ", " + this.f[i][1]);
        // }

    } 

    /**
     * DFS recursive implementation.
     */
    public void DFS_recursive(int v) throws Exception{
        // Marks vertex as visited.
        this.colors[v] += 1;

        // Gets all sucessors
        ArrayList<Integer> suc = this.sucessors(v);

        // Applies DFS over the sucessors.
        for(int i = 0; i < suc.size(); i++){
            int w = suc.get(i);

            
            if(this.colors[w] == 1){
                // throw new Exception("adjMatrix has cycle");
                this.hasCycle = true;
                int[] coord = this.indexToCoord(w);
                this.head = this.getName(coord[0],coord[1]);
                this.cycle = this.head;
            }
            
            if(this.colors[w] == 0){
                System.out.println(w);
                this.DFS_recursive(w);
            }
            
            if(this.hasCycle){
                System.out.println("entre");
                int[] coord = this.indexToCoord(w);
                String vertex = this.getName(coord[0],coord[1]);
                System.out.println(vertex);
                this.cycle = vertex + "->" + this.cycle;
                if(vertex.equals(this.head)){
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