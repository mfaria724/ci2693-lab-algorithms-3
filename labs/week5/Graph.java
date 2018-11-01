import java.util.LinkedList;
import java.util.Arrays;

/** 
 * Graph instance, saves graph information as adjacencies matrix.
 */
public class Graph {
  
  // Variables Declaration
  private int[][] graph; // Graph matrix representation.

  /**
   * Initialices a graphs with v vertices and e edges.
   * @param v
   */
  Graph(int v){
    this.graph = new int[v][v];
  }

  /**
   * Adds and edge to the matrix representation.
   * @param iv
   * @param fv
   * @throws Exception
   */
  public void addEdge(int iv, int fv) throws Exception{

    if (this.graph[iv][fv] == 1){
      throw new Exception("Repeated Edge");
    }

    this.graph[iv][fv] = 1;

  }

    public void bfs(int o, boolean[] options, int trunc){

      int v= this.graph.length;  // Variable that has the number of vertexes
      boolean visited[]=new boolean[v]; // Array that contains if an vertex have been visited before
      LinkedList<Integer> queue=new LinkedList<Integer>(); // List to put discovered vertexes to consider their adjacents
      queue.add(o); // Add the first in the queue
      int[] ordinal = new int[this.graph.length];  // Array that contains the way to be considered
      Arrays.fill(ordinal, -1);
      int[] pred = new int[this.graph.length];
      Arrays.fill(pred, -1);
      int[] identacion = new int[v];  // Array used to know how much identation has every vertex for the output
      int counter = 0;   // Variable used to iterate camino array
      int level = 0;
      String salida; // Output
      String space = "  ";    // Output's identation
      String tree = o + "-" + o + "(raiz)\n";
      boolean salir = false;
      
      pred[o] = o;
      identacion[o] = 1;
      visited[o]=true;

      if(!options[0]){
        trunc = this.graph.length;
      }
      
      // While used to consider all vertexes until there is no one that haven't been visited
      while(queue.size()!=0)
      {   
          if(salir){
            break;
          }

          // Remove vertex in the head of the queue
          int x=queue.remove();

          // Add vertex to the output
          ordinal[counter] = x;
          counter += 1;

          // Find all the adjacencies to the vertex
          for (int i=0; i < v; i++) 
          {   
              // If the adj vertex haven't been visited, its added to queue
              if((this.graph[x][i] == 1) && (!visited[i]))
              {  
                  queue.add(i);
                  level=identacion[x];

                  if(level > trunc){
                    salir = true;
                    break;
                  }

                  identacion[i]=identacion[x] + 1;
                  System.out.println(identacion[x]);
                  for(int j=0; j<identacion[x]; j++){
                      tree += space;
                  }
                  tree += x + "-" + i + " (arco de camino)\n";
                  pred[i] = x;
                  
                  
                  visited[i]=true;
              } else if((this.graph[x][i] == 1) && (visited[i])){
                  level=identacion[x];

                  if(level > trunc){
                    salir = true;
                    break;
                  }

                  for(int j=0; j<identacion[x]; j++){
                      tree += space;
                  }
                  tree += x + "-" + i + " (arco cruzado)\n";
              }
          }
      }
      
      salida = "";
      for(int i=0;i<visited.length;i++){
        if(visited[i] != true){
          salida += i + ", ";
        }
      }
      if(salida != ""){
        salida = (salida.substring(0,salida.length() - 2));
      } else {
        salida ="Todas las paginas son parte de la internet visible.";
      }
      if(options[1]){
        salida += "\n\nArbol:\n" + tree;

      }
   
      if(options[2]){
        salida += "\n\nOrdinales:\n";
        for(int i=0; i<ordinal.length;i++){
          salida += i + ": " + ordinal[i] + "\n";
        }
      }

      if(options[3]){
        salida += "\n\nPredecesores:\n";
        for(int i=0; i<pred.length; i++){
          salida += i + ": " + pred[i] + "\n";
        }
      }

      System.out.println(salida);
  }
  public void dfs(Boolean[] options, int trunc){
    
  }

}