import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Hamilton extends BFS {

  static Grafo cargarGrafo(String nombreArchivo) throws IOException{
    Grafo salida = new Grafo();
    
    BufferedReader Lector = new BufferedReader(
    new FileReader(nombreArchivo));
      
    String linea = Lector.readLine();
      
    int N = Integer.parseInt(linea);

    linea = Lector.readLine();
    int M = Integer.parseInt(linea); 
    
    salida.grafo = new int[N][N];

    for(int i=0; i<M; i++){
      linea = Lector.readLine();
      int vertice1 = Integer.parseInt(linea.substring(0,1));
      int vertice2 = Integer.parseInt(linea.substring(2,3));
      salida.grafo[vertice1][vertice2] = 1;
      salida.grafo[vertice2][vertice1] = 1;
    }

    return salida;
  }
  public static void main(String[] args) throws IOException {
    if(args.length < 1){
      System.err.println("Uso: java Hamilton <nombreArchivo> <BFS|DFS> <cycle>");
      return;
    } else if (args.length > 2) {
      System.out.println("Quiero ciclo");
    } 
    else {
      Grafo g = cargarGrafo(args[0]);
      System.out.println(g.imprimirGrafoTraducido());
      System.out.println("Uso normal");
    }
  }

}