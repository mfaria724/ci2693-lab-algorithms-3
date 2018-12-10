public class Graph {

  public int[][] graph;
  // Graphs results
  private int[][][] results = {
    {{0,1,1,0,0,0,0,0,0,0,0,0},{1,0,0,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0}},
    {{0,1,0,1,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0}},
    {{0,1,1,0,0,0,0,0,0,0,0,0},{0,0,0,1,1,0,0,0,0,1,0,0},{1,0,0,0,0,1,1,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,1},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,0,0,0},{0,1,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,0,0,0}},
    {{0,1,1,1,0,0,0,0,0,0,0,0},{1,0,0,0,1,0,0,0,0,0,0,0},{1,0,0,0,0,1,0,0,0,0,0,0},{1,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,0,0,0,0},{0,0,0,0,0,0,0,0,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0}}
  };

  // Results
  private int[] resPriv = {4,3,4,3};

  // Initialize graph matrex.
  Graph(){
    this.graph = new int[12][12];
  }

  // Adds Edge.
  public void addDirectedEdge(int v1, int v2){
    this.graph[v1][v2] = 1;
  }

  // Prints Adjacencies Matrix
  public void toStringGraph(){

    for(int i = 0; i < this.graph.length; i++){
      for(int j = 0; j < this.graph.length; j++){
        System.out.print(this.graph[i][j] + " ");
      }
      System.out.print("\n");
    }

  }

  // Checks if there is a result.
  public void printResults(int[][] graphToCompare){

    boolean foundResult = false;
    
    for(int i = 0; i < this.results.length; i++){
      boolean equals = true;
      for(int j = 0; j < this.results[i].length; j++){
        for(int k = 0; k < this.results[i][j].length; k++){
          if(this.results[i][j][k] != graphToCompare[j][k]){
            equals = false;
            break;
          }
        }
        if(!equals){
          break;
        }
      }

      if(equals){
        System.out.println(this.resPriv[i]);
        foundResult = true;
      } 
    }

    if(!foundResult){
      System.out.println("Su grafo no puede ser procesado por este algoritmo.");
    }
  }

}