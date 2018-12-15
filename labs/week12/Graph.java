public class Graph{

    public int[][] edges;
    public int graphCost;

    Graph(int n, int m){
        this.edges = new int[m][3];
        this.graphCost = 0;

    }

    public void addEdge(int i, int x, int y, int z){
        this.edges[i][0] = x;
        this.edges[i][1] = y;
        this.edges[i][2] = z; 
    }

    public int kruskal(){
        
    }


}