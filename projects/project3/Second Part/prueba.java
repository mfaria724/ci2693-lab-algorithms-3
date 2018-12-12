public class prueba{
    int N = 2;
    int M = 4;
    private int[][] matrix = new int[N][M];
    private String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public int letToNum(String letras){
        int n = letras.length() - 1;
        int salida = 0;
        char[] caracteres = letras.toCharArray();
        
        for(int i=0; i<caracteres.length; i++){
            salida += (Character.getNumericValue(caracteres[i]) - 9) * Math.pow(26, n); 
            n--;
        }

        return salida;
    }    

    public int[] indexToCoord(int index){
        int[] salida = new int[2];

        salida[0] = index % this.N;
        salida[1] = index / this.N;
        System.out.println(this.N +  " " + index);
        return salida;
    }

    public static void main(String[] args){
        prueba test = new prueba();

        System.out.println(test.indexToCoord(4)[0] + " " + test.indexToCoord(4)[1]);
        // System.out.println(test.letToNum("Ba"));
        // System.out.println(test.digDec(150));
    }

}