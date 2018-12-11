public class prueba{
    int N = 50;
    int M = 703;
    private int[][] matrix = new int[N][M];
    private String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public String llamafuncion(int fila, int columna){

        String salida = Integer.toString(fila + 1);
        
        salida = funcion(columna, salida) + salida;

        return salida;
    }

    public String funcion(int numero, String salida){
        
        if(numero < 26){
            return this.letras[numero];
        } else{
            int div = numero / 26 - 1;
            int mod = numero % 26;
            return funcion(div,salida) + letras[mod];

        }
    }

    public static void main(String[] args){
        prueba test = new prueba();

        System.out.println(test.llamafuncion(0, 27));
    }

}