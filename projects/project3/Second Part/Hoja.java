import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hoja{

    String[][] matrizExpresiones;
    int[][] matrizAdj;
    String[][] matrizCeldas;
    private int count;
    private int[][] f;
    private int[] colors;

    // private String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


    Hoja(){
        
    }
    Hoja(int n, int m){
        matrizExpresiones = new String[n][m];
        matrizAdj = new int[n*m][n*m];
        matrizCeldas = new String[n][m];
    }
    
    // public String getName(int fila, int columna){

    //     String salida = Integer.toString(fila + 1);
        
    //     salida = nameRec(columna, salida) + salida;

    //     return salida;
    // }

    // public String nameRec(int numero, String salida){
        
    //     if(numero < 26){
    //         return this.letras[numero];
    //     } else{
    //         int div = numero / 26 - 1;
    //         int mod = numero % 26;
    //         return nameRec(div,salida) + letras[mod];

    //     }
    // }
    
    public ArrayList<String> findPred(String expresion){
        ArrayList<String> salida = new ArrayList<>();

        Pattern pattern = Pattern.compile("[a-zA-Z]+\\d+");
        Matcher matcher = pattern.matcher(expresion);

        while(matcher.find()){
            salida.add(matcher.group(0));
        }

        return salida;
    }

    public int nameToIndex(String input){
        String[] celda = input.split("[^a-zA-Z0-9]+|(?<=[a-zA-Z])(?=[0-9])|(?<=[0-9])(?=[a-zA-Z])");
        // System.out.println(celda[0] + " " + celda[1]);


        int exp = celda[0].length() - 1;
        int n = this.matrizCeldas.length;

        int salida = -1;
        char[] caracteres = celda[0].toCharArray();
        
        for(int i=0; i<caracteres.length; i++){
            // System.out.println(Character.getNumericValue(caracteres[i]));
            salida += (Character.getNumericValue(caracteres[i]) - 9) * Math.pow(26, exp); 
            exp--;
        }

        // System.out.println("solo letras " + salida);
        // System.out.println("con numeros " + (salida * n + Integer.parseInt(celda[1]) - 1))
        return salida * n + Integer.parseInt(celda[1]) - 1;  // = x                     salida = (x + 1 - celda[1] ) / n
    }    

    public int[] nameToCoord(String input){
        int[] salida = {0,-1};

        String[] celda = input.split("[^a-zA-Z0-9]+|(?<=[a-zA-Z])(?=[0-9])|(?<=[0-9])(?=[a-zA-Z])");
        // System.out.println(celda[0] + " " + celda[1]);


        int exp = celda[0].length() - 1;
        
        // System.out.println(celda[0]);
        char[] caracteres = celda[0].toCharArray();
        
        for(int i=0; i<caracteres.length; i++){
            salida[1] += (Character.getNumericValue(caracteres[i]) - 9) * Math.pow(26, exp); 
            exp--;
        }
        
        salida[0] = Integer.parseInt(celda[1]) - 1 ;
        // System.out.println("solo letras " + salida);
        // System.out.println("con numeros " + (salida * n + Integer.parseInt(celda[1]) - 1));
        return salida;
    }

    public int[] indexToCoord(int index){
        int n = this.matrizCeldas.length;
        int[] salida = new int[2];

        salida[0] = index % n;
        salida[1] = index / n;
        System.out.println(n +  " " + index);
        return salida;
    }


    /**
     * Method to initialize recursive DFS method.
     */
    public void DFS(){

        // Get number of nodes.
        this.count = this.matrizAdj.length;

        // Variables initialization.
        this.f = new int[this.matrizAdj.length][2];
        this.colors = new int[this.matrizAdj.length];
        Arrays.fill(this.colors, 0);

        // Apply DFS for all nodes.
        for(int i = 0; i < this.colors.length; i++){
        
            try {
                if(this.colors[i] == 0){
                    this.DFS_recursive(i);
                }        
            } catch (Exception e) {
                if(e.getMessage().equals("matrizAdj has cycle")){
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }

        }

        // Prints the result to user.
        for(int i = 0; i < this.f.length; i++){
            System.out.println(this.f[i][0] + ", " + this.f[i][1]);
        }

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
                throw new Exception("matrizAdj has cycle");
            }

            if(this.colors[w] == 0){
                this.DFS_recursive(w);
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
        for(int i = 0; i < this.matrizAdj.length; i++){
            if(this.matrizAdj[v][i] == 1){
                suc.add(i);
            }
        }

        return suc;

    }

    

}