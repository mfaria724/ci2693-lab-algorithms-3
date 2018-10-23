import java.util.Arrays;
import java.util.LinkedList;

public class BFS {

    private int[][] grafo;

    BFS(Grafo grafo, boolean quieroCiclo){
    
        this.grafo = grafo.getGrafo();
        System.out.println("Esto es un BFS");
        System.out.println("quieroCiclo: " + quieroCiclo);

    }

    public void metodo(int vertice){

        int v= this.grafo.length;//a[][] is adj matrix declared globally
        boolean visited[]=new boolean[v];//indexing done from 1 to n
        LinkedList<Integer> queue=new LinkedList<Integer>();
        int[] camino = new int[this.grafo.length];
        int contador = 0;
        String salida = "";
        visited[vertice]=true;
        queue.add(vertice);
        while(queue.size()!=0)
        {
            int x=queue.remove();

            salida += x + "-";
            camino[contador] = x;
            contador += 1;
            for (int i=1; i < v; i++) 
                if((this.grafo[x][i] == 1) && (!visited[i]))
                {
                queue.add(i);
                visited[i]=true;
                }
        }
        boolean hamiltoniano = true;
        for(int i=0;i<camino.length - 1;i++){
            if(this.grafo[camino[i]][camino[i + 1]] != 1){
                hamiltoniano = false;
            }
        }

        if(hamiltoniano){
            System.out.println("Se encontro un camino hamiltoniano");
            System.out.println("El camino es: ");
            System.out.println(salida.substring(0,salida.length() - 1));
        }
    }
}