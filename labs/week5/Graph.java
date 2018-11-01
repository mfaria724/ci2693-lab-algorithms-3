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

    public void bfs(int vertice){

      System.out.println("Recorrido desde "+vertice+":");

      int v= this.graph.length;  // Variable that has the number of vertexes
      boolean visited[]=new boolean[v]; // Array that contains if an vertex have been visited before
      LinkedList<Integer> queue=new LinkedList<Integer>(); // List to put discovered vertexes to consider their adjacents
      queue.add(vertice); // Add the first in the queue
      int[] camino = new int[this.graph.length];  // Array that contains the way to be considered
      int[] identacion = new int[v];  // Array used to know how much identation has every vertex for the output
      int contador = 0;   // Variable used to iterate camino array
      
      String salida = ""; // Output
      String space = "  ";    // Output's identation
      
      identacion[vertice] = 1;
      visited[vertice]=true;
      
      // While used to consider all vertexes until there is no one that haven't been visited
      while(queue.size()!=0)
      {   
          // Remove vertex in the head of the queue
          int x=queue.remove();

          // Add vertex to the output
          salida += x + "-";
          camino[contador] = x;
          contador += 1;

          // Find all the adjacencies to the vertex
          for (int i=0; i < v; i++) 
          {   
              // If the adj vertex haven't been visited, its added to queue
              if((this.graph[x][i] == 1) && (!visited[i]))
              {  
                  queue.add(i);
                  identacion[i]=identacion[x] + 1;
                  for(int j=0; j<identacion[x]; j++){
                      System.out.print(space);
                  }
                  System.out.println(x + "-" + i);
                  
                  visited[i]=true;
              } else if((this.graph[x][i] == 1) && (visited[i])){
                  for(int j=0; j<identacion[x]; j++){
                      System.out.print(space);
                  }
                  System.out.println(x + "-" + i + " ya visitado");

              }
          }
      }
  }
  public void dfs(Boolean[] options, int trunc){
    
  }

}