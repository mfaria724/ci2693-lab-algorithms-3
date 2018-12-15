import java.util.ArrayList;

public class Graph{

    public ArrayList<int[]> edges;
    private int n;
    public int graphCost;
    int[] parts;

    Graph(){

    }

    Graph(int n, int m){
        this.edges = new ArrayList<int[]>();
        this.graphCost = 0;
        this.n = n;
        this.parts = new int[n];
        for(int i=0; i<n;i++){
            this.parts[i] = i;
        }


    }

    public int find(int x){
        return this.parts[x];
    }

    public void join(int x, int y){
        for(int i=0; i<n; i++){
            if(this.parts[i]==y){
                this.parts[i]=x;
            }
        }
    }

    public void addEdge(int i, String x, String y, String z){
        int[] edge = new int[3];
        edge[0] = Integer.parseInt(x);
        edge[1] = Integer.parseInt(y);
        edge[2] = Integer.parseInt(z); 

        this.edges.add(i, edge);
    }

    private int[] dequeue(ArrayList<int[]> queue){
        int min = 0;

        for(int i=0; i<queue.size(); i++){
            if(queue.get(i)[2] < queue.get(min)[2]){
                min = i;
            }
        }

        int[] output = {queue.get(min)[0],queue.get(min)[1], queue.get(min)[2]};
        queue.remove(min);
        return output;
        
    }

    public void kruskal(){

        ArrayList<int[]> T = new ArrayList<>();
        int numComp = this.n;


        ArrayList<int[]> queue = new ArrayList<>(this.edges);


        while(numComp > 1){
            int[] edge = dequeue(queue);

            int x = edge[0];
            int y = edge[1];

            
            int repX = this.find(x);
            int repY = this.find(y);

            if(repX != repY){
                this.join(repX, repY);
                T.add(edge);
                numComp --;

            }
        }

        int costT = cost(T);
        
        System.out.println((graphCost - costT) + " UT ahorradas");


        
    }

    private int cost(ArrayList<int[]> T){
        int cost = 0;

        for(int i=0;i<T.size();i++){
            cost+= T.get(i)[2];
        }

        return cost;
    }


}