import java.io.BufferedReader;
import java.io.FileReader;

/** Almacena un grafo dinamicamente para ser recorrido mediante un recorrido
 * BFS o DFS segun lo indique el usuario.
 */
public class Grafo {

  private int[][] grafo;
  private int vertices;
  private int aristas;

  Grafo(int vertices, int aristas){
    this.vertices = vertices;
    this.aristas = aristas;
    this.grafo = new int[vertices][vertices];
  }

  public void agregarArista(int vertice1, int vertice2) throws Exception{

    if (this.grafo[vertice1][vertice2] == 1 || this.grafo[vertice2][vertice1] == 1){
      System.out.println("Vertice 1: " + vertice1);
      System.out.println("Vertice 2: " + vertice2);
      throw new Exception("Arista Repetida");
    }

    this.grafo[vertice1][vertice2] = 1;
    this.grafo[vertice2][vertice1] = 1;

  }

  public int[][] getGrafo(){
    return this.grafo;
  }
}

