import java.util.ArrayList;
import java.util.Arrays;

public class DFS {

  private int[][] grafo;
  private String[] colors;
  private int[] father;
  private ArrayList<Integer> recorridoActual;
  private String space = "  ";
  private boolean quieroCiclo = false;

  DFS(Grafo grafo){
    System.out.println("Esto es un DFS");

    this.grafo = grafo.getGrafo();
  }

  public void applyDFS(boolean quieroCiclo){

    System.out.println("quieroCiclo: " + quieroCiclo);
    this.quieroCiclo = quieroCiclo;
    
    this.colors = new String[this.grafo[0].length];
    this.father = new int[this.grafo[0].length];

    

    for (int i = 0; i < this.grafo[0].length; i ++){
      Arrays.fill(this.colors, "W");
      Arrays.fill(this.father, -1);
      if (colors[i].equals("W")){
        System.out.println("Recorrido desde " + i + ": ");
        this.recorridoActual = new ArrayList<Integer>();
        try {
          this.DFS_visit(i);
        } catch (Exception e) {
          if (e.getMessage().equals("Camino Encontrado")){
            System.out.println("Wuju");
            System.exit(0);
          }
        }   
      }
    }
    System.out.println("No existe camino");

  }

  public void DFS_visit(int vertex) throws Exception{

    this.colors[vertex] = "N";
    this.recorridoActual.add(vertex);

    if (this.recorridoActual.size() == this.grafo[0].length){
      if (quieroCiclo == true){
        if (this.chequeaCiclo() == true){
          String camino = "Ciclo encontrado: ";
      
          for (int i = 0; i < this.recorridoActual.size(); i++){
            camino += this.recorridoActual.get(i) + "-";
          }

          camino += this.recorridoActual.get(0);

          System.out.println(camino);
          System.out.println("El ciclo tiene " + this.recorridoActual.size() + " vertices.");
          throw new Exception("Camino Encontrado");
        }
      } else {
        String camino = "Camino encontrado: ";
      
        for (int i = 0; i < this.recorridoActual.size(); i++){
          camino += this.recorridoActual.get(i) + "-";
        }

        camino = camino.substring(0, camino.length() - 1);

        System.out.println(camino);
        System.out.println("El camino tiene " + this.recorridoActual.size() + " vertices.");
        throw new Exception("Camino Encontrado");
      }
    }

    ArrayList<Integer> sucesores = adyacentes(vertex);

    for (int j = 0; j < sucesores.size(); j++){
      if (this.colors[sucesores.get(j)].equals("W")){

        System.out.println(this.space + vertex + "-" + sucesores.get(j));
        this.father[sucesores.get(j)] = vertex;
        this.space += "  ";
        DFS_visit(sucesores.get(j));
        this.recorridoActual.remove(this.recorridoActual.size() - 1);

        
      } else {
        System.out.println(this.space + vertex + "-" + sucesores.get(j) + " ya visitado");
      }
    } 
    this.space = this.space.substring(0, this.space.length() - 2);

  }

  private ArrayList<Integer> adyacentes(int vertex){

    ArrayList<Integer> adj = new ArrayList<Integer>();

    for (int k = 0; k < this.grafo[0].length; k++){
      if (this.grafo[vertex][k] == 1 && k != vertex){
        adj.add(k);
      }
    }   

    return adj;

  }

  private boolean chequeaCiclo(){

    int initialVertex = this.recorridoActual.get(0);
    int finalVertex = this.recorridoActual.get(this.recorridoActual.size() - 1);

    if (this.grafo[initialVertex][finalVertex] == 1){
      return true;
    }

    return false;
  }


}