import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

/** 
 * Graph instance, saves graph information as adjacencies matrix.
 */
public class Graph {
  
  // Variables Declaration
  private int[][] graph; // Graph matrix representation.
  private String[] colors; // Stores colors for DFS method.
  private int[] father; // Store antecesor of each vertex.
  private ArrayList<Integer> actualWay; // Stores the current way. 
  private String space = ""; // Indetation
  private int[] ordinal; // Ordinal sequence.
  private int order = 0; // Actual vertex discovered
  private String tree = ""; // Tree in string format.
  private int depth = 0; // Depth of the graph.
  private int trunc = -1; // Detph to trunc.
  private int connected_components = 0; // Number of strong connected components.
  private boolean[] visited; // Array that contains if an vertex have been visited before
  private int recursion = 0;

  /**
   * Initialices a graphs with v vertices and e edges.
   * @param v
   */
  Graph(int v){
    this.graph = new int[v][v];
    this.visited = new boolean[v];
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
    /**
     * 
     * @param o
     * @param options
     * @param trunc
     */
    public void bfs(int o, boolean[] options, int trunc){

      int v= this.graph.length;  // Variable that has the number of vertexes
      LinkedList<Integer> queue=new LinkedList<Integer>(); // List to put discovered vertexes to consider their adjacents
      queue.add(o); // Add the first in the queue
      int[] ordinal = new int[v];  // Array that contains the order
      Arrays.fill(ordinal, -1);  // Fills ordinal with -1
      int[] pred = new int[v];  // Array that contains thet father of every vertex
      Arrays.fill(pred, -1);    // Fills pred with -1
      int[] identacion = new int[v];  // Array used to know how much identation has every vertex for the output
      int counter = 0;   // Variable used to iterate camino array
      int level = 0;    // Variable that contains the level of bfs
      String salida; // Output
      String space = "  ";    // Output's identation
      String tree = o + "-" + o + "(raiz)\n"; // Output used in case of --arb 
      boolean exit = false;    // Variable used to break while
      this.connected_components += 1; // Add one to connected_components

      
      pred[o] = o;
      identacion[o] = 1;
      this.visited[o]=true;

      if(!options[0]){
        trunc = v;
      }
      
      // While used to consider all vertexes until there is no one that haven't been visited
      while(queue.size()!=0)
      {   
          if(exit){
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
              if((this.graph[x][i] == 1) && (!this.visited[i]))
              {  
                  queue.add(i);

                  // Saves the level 
                  level=identacion[x];
                  // Verifies
                  if(level > trunc){
                    exit = true;
                    break;
                  }

                  identacion[i]=identacion[x] + 1;
                  for(int j=0; j<identacion[x]; j++){
                      tree += space;
                  }
                  tree += x + "-" + i + " (arco de camino)\n";
                  pred[i] = x;
                  
                  
                  visited[i]=true;
              } else if((this.graph[x][i] == 1) && (this.visited[i])){

                  level=identacion[x];
                  if(level > trunc){
                    exit = true;
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

      for(int i=0;i<this.visited.length;i++){
        if(this.visited[i] != true){
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

      
      if(options[4]){
        for(int i=0;i<this.visited.length;i++){
          if(!this.visited[i]){
            recursion += 1;
            bfs(i, options, trunc);
            recursion = recursion - 1;
          }
        }

        if(this.recursion == 0){
          salida += "\n\nComponentes conexas: " + this.connected_components + "\n";
        }
      }
      
      if(this.recursion == 0){
        System.out.println(salida);
      }
  }
    
  /**
   * Method that returns al vertex adjacencies. 
   * @param vertex
   * @return
   */
  private ArrayList<Integer> adjacencies(int vertex){

    // Initialices List.
    ArrayList<Integer> adj = new ArrayList<Integer>();

    // Checks adjacencies matrix.
    for (int k = 0; k < this.graph[0].length; k++){
      if (this.graph[vertex][k] == 1 && k != vertex){
        adj.add(k);
      }
    }   

    return adj;

  }

  /**
   * Method to implement dfs over the graph. Some extra functionality has been implemented
   * as explained in problem requirements.
   * @param origin // Vertex to start
   * @param options // Options specified by user.
   * @param trunc // Depth to truncate.
   */
  public void dfs(int origin, boolean[] options, int trunc){
  
    // Set number of depth to truncate.
    if(options[0]){
      this.trunc = trunc;
    }

    // Initializes colors, father and ordinal array.
    this.colors = new String[this.graph[0].length];
    this.father = new int[this.graph[0].length];
    this.ordinal = new int[this.graph[0].length];

    // Fill array with default values.
    Arrays.fill(this.colors, "W");
    Arrays.fill(this.father, -1);
    Arrays.fill(this.ordinal, -1);

    // Origin father.
    this.father[origin] = origin;

    // Start a way.
    if (colors[origin].equals("W")){
      // Increments number of strong connected components.
      this.connected_components += 1;
      this.tree += origin + "-" + origin + " (raiz)\n";

      // Initialice identation and currentWay.
      this.space = "\t";
      this.actualWay = new ArrayList<Integer>();

      // Checks exceptions for Hamiltonean way.
      this.DFS_visit(origin); 

    }

    // Gets all vertices that were not visited.
    String result = "";
    for(int i = 0; i < this.colors.length; i++){
      if(this.colors[i].equals("W")){
        result += i + ",";
      }
    } 

    // If there wasn't vertices visited.
    if(result.equals("")){
      System.out.println("Todas las páginas son parte de la internet visible.\n");
    } else {
      System.out.println(result.substring(0, result.length() - 1) + "\n"); 
    }

    // Tree option.
    if(options[1]){
      System.out.println("Arbol: ");
      System.out.println(this.tree);
    }

    // Ordinal option.
    if(options[2]){
      System.out.println("Ordinales: ");
      for(int i = 0; i < this.father.length; i++){
        System.out.println(i + ": " + this.ordinal[i]);
      }
      System.out.print("\n");
    }

    // Predecessors option.
    if(options[3]){
      System.out.println("Predecesores: ");
      for(int i = 0; i < this.father.length; i++){
        System.out.println(i + ": " + this.father[i]);
      }
      System.out.print("\n");
    }

    // Strong Connected Components option.
    if(options[4]){
      for(int i = 0; i < this.colors.length; i++){
        if(this.colors[i].equals("W")){
          this.space = "\t";
          this.connected_components += 1;
          this.DFS_visit(i);
        }
      }
      System.out.println("Componentes Conexas: " + this.connected_components);
    }
  }

  /**
   * Applies DFS algorithms over an specific vertex.
   * @param vertex
   */
  private void DFS_visit(int vertex){

    // Changes the current vertex color.
    this.colors[vertex] = "N";
    this.ordinal[vertex] = this.order;
    this.depth += 1;
    this.order += 1;

    //Adds vertex to current way.
    this.actualWay.add(vertex);

    // Gets sucesors of the current vertex.
    ArrayList<Integer> suc = adjacencies(vertex);

    if(this.depth > this.trunc && this.trunc != -1){
      this.space = this.space.substring(0, this.space.length() - 1);
      this.depth -= 1;
      return;
    }

    // Iterate over sucesors.
    for (int j = 0; j < suc.size(); j++){

      // Checks if the vertex has been visited.
      if (this.colors[suc.get(j)].equals("W")){

        // Prints current visit
        this.tree += this.space + vertex + "-" + suc.get(j) + " (arco de camino)\n";
        this.father[suc.get(j)] = vertex;
        this.space += "\t";

        // Go over sucesors of the current sucesor.
        DFS_visit(suc.get(j));

        // Goes back and deletes the last element to go over another sucessor.
        this.actualWay.remove(this.actualWay.size() - 1);

        
      } else {

        // Returned directed edge.
        String line = "";
        for(int i = 0; i < this.actualWay.size(); i++){
          if(this.father[suc.get(j)] == this.actualWay.get(i)){
            line = this.space + vertex + "-" + suc.get(j) + " (arco de subida)\n";
            break;
          }  
        }
        
        // New directed edge.
        if(line.equals("")){
          line = this.space + vertex + "-" + suc.get(j) + " (arco cruzado)\n";
        }

        this.tree += line;
      }
    } 

    // Quits indentation and decreases depth.
    this.space = this.space.substring(0, this.space.length() - 1);
    this.depth -= 1;


  }

}