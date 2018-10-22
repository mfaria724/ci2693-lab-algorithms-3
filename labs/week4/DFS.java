import java.util.ArrayList;

public class DFS {

  private int[][] grafo;
  private ArrayList<ArrayList<Integer>> cerrados;
  private ArrayList<ArrayList<Integer>> abiertos;

  DFS(Grafo grafo, boolean quieroCiclo){
    System.out.println("Esto es un DFS");
    System.out.println("quieroCiclo: " + quieroCiclo);

    this.grafo = grafo.getGrafo();
  }

  public ArrayList<ArrayList<Integer>> generalModel(int vertice){
    this.cerrados = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> primerCamino = new ArrayList<Integer>();
    primerCamino.add(vertice);

    this.abiertos =  new ArrayList<ArrayList<Integer>>();
    this.abiertos.add(primerCamino);

    System.out.println("Tama;o de abiertos: " + this.abiertos.size());

    while (this.abiertos.size() > 0){
      ArrayList<Integer> pi = this.seleccionar(abiertos); 

      System.out.print(pi.toString());

      this.abiertos.remove(pi);

      System.out.println("Tama;o de abiertos: " + this.abiertos.size());

      this.cerrados.add(pi);
      ArrayList<Integer> sucesores = this.sucesores(pi.get(pi.size() - 1));

      for (int i = 0; i < sucesores.size(); i++){
        int suc = sucesores.get(i);
        ArrayList<Integer> ph = new ArrayList<Integer>(pi);
        ArrayList<Integer> pj = new ArrayList<Integer>(ph);
        
        System.out.println("Eliminar: " + this.eliminar(pj));

        if(!this.eliminar(pj)){
          this.abiertos.add(pj);
        }
      }
      System.out.println("Tama;o de abiertos: " + this.abiertos.size());

      // this.hamiltoneano();

      
    }

    System.out.println(cerrados.toString());

    return cerrados;
  }

  ArrayList<Integer> seleccionar(ArrayList<ArrayList<Integer>> abiertos){
    return abiertos.get(0);
  }

  ArrayList<Integer> sucesores(int vertice){
    ArrayList<Integer> sucesores = new ArrayList<Integer>();

    for (int i = 0; i < this.grafo[vertice].length ; i++){
      if (this.grafo[vertice][i] == 1){
        sucesores.add(i);
      }
    }

    return sucesores;
  }

  boolean eliminar(ArrayList<Integer> camino){
    
    for (int i = 0; i < this.cerrados.size(); i++){
      if (this.cerrados.get(i).get(0) == camino.get(0) &&
          this.cerrados.get(i).get(this.cerrados.size() - 1) == camino.get(camino.size() - 1) &&
          this.cerrados.get(i).size() != 1)
      {
        System.out.println("v0 de pk: " + this.cerrados.get(i).get(0));
        System.out.println("vl de pk: " + this.cerrados.get(i).get(this.cerrados.size() - 1));
        return true;
      }
    }

    for (int i = 0; i < this.abiertos.size(); i++){
      if (this.abiertos.get(i).get(0) == camino.get(0) &&
          this.abiertos.get(i).get(this.abiertos.size() - 1) == camino.get(camino.size() - 1))
      {
        this.abiertos.remove(this.abiertos.get(i));
        return false;
      }
    }

    return false;
  }

  private void hamiltoneano(){

    for (int i = 0; i < this.cerrados.size(); i++){
      if (this.cerrados.get(i).size() != this.grafo[0].length){
        this.cerrados.remove(i);
      }
    }

  }

}