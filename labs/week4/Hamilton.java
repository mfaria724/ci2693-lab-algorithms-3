import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Hamilton {

  static Grafo cargarGrafo(String nombreArchivo) throws IOException, Exception{
    
    Grafo grafo = new Grafo();

    try {
      BufferedReader Lector = new BufferedReader(
      new FileReader(nombreArchivo));
        
      String linea = Lector.readLine();
        
      int vertices = Integer.parseInt(linea);
      linea = Lector.readLine();
      int aristas = Integer.parseInt(linea); 

      grafo = new Grafo(vertices, aristas);

      for(int i=0; i < aristas; i++){
        linea = Lector.readLine();

        String[] verticesArista = linea.split(" ");
        int vertice1 = Integer.parseInt(verticesArista[0]);
        int vertice2 = Integer.parseInt(verticesArista[1]);

        try {
          grafo.agregarArista(vertice1, vertice2);
        } catch (Exception e) {
          if (e.getMessage() == "Arista Repetida"){
            System.out.println("No se puede cargar grafos con aristas repetidas.");
            System.exit(0);
          }
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("El archivo especificado no existe, por favor, introduzca un archivo válido.");
      System.exit(0);
    }

    return grafo;

  }
  public static void main(String[] args) throws IOException, Exception {

    String mensajeError = "Uso: java Hamilton <nombreArchivo> <BFS|DFS> <cycle>";

    if(args.length < 1){
      System.err.println(mensajeError);
      return;
    } else {
      
      Grafo g = cargarGrafo(args[0]);
      
      boolean quieroCiclo = false;

      if (args.length > 2){
        if (args[2].equals("cycle")) {
          quieroCiclo = true;
        }
      }

      if (args[1].equals("BFS")){
        BFS bfs = new BFS(g, quieroCiclo);
      } else if(args[1].equals("DFS")){
        DFS dfs = new DFS(g);
        dfs.applyDFS(quieroCiclo);
        // dfs.generalModel(0);
      } else {
        System.out.println(mensajeError);
      }

    }
  }

}