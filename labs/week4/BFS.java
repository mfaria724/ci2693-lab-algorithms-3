import java.util.Arrays;
import java.util.LinkedList;

public class BFS {

    private int[][] grafo;
    private boolean quieroCiclo;

    BFS(Grafo grafo, boolean quieroCiclo){
    
        this.grafo = grafo.getGrafo();
        this.quieroCiclo = quieroCiclo;

    }

    public void applyBFS(boolean quieroCiclo){
        for(int i=0; i<this.grafo.length; i++){
           try{
                this.BFS_visit(i);
           } catch (Exception e){
               if(e.getMessage().equals("Camino Encontrado")){
                   System.out.println("Wuju");
                   System.exit(0);
               }
           }
        }
        System.out.println("No existe camino");
    }

    private boolean Chequeo(boolean quieroCiclo,int[] camino){
        boolean hamiltoniano = true;
        for(int i=0;i<camino.length - 1;i++){
            if(this.grafo[camino[i]][camino[i + 1]] != 1){
                hamiltoniano = false;
            }
        }

        if(hamiltoniano && quieroCiclo){
            if (this.grafo[camino[0]][camino[this.grafo.length - 1]] != 1){
                hamiltoniano = false;
            }    
        }
        return hamiltoniano;
        
    }

    public void BFS_visit(int vertice) throws Exception{

        System.out.println("Recorrido desde "+vertice+":");

        int v= this.grafo.length;
        boolean visitado[]=new boolean[v];
        LinkedList<Integer> queue=new LinkedList<Integer>();
        int[] camino = new int[this.grafo.length];
        queue.add(vertice);
        int[] identacion = new int[v];
        int contador = 0;
        
        String salida = "";
        String space = "  ";
        
        identacion[vertice] = 0;
        visitado[vertice]=true;
        
        while(queue.size()!=0)
        {
            int x=queue.remove();

            salida += x + "-";
            camino[contador] = x;
            contador += 1;

            for (int i=0; i < v; i++) 
            {   
                if((this.grafo[x][i] == 1) && (!visitado[i]))
                {  
                    queue.add(i);
                    identacion[i]=identacion[x] + 1;
                    for(int j=0; j<identacion[x]; j++){
                        System.out.print(space);
                    }
                    System.out.println(x + "-" + i);
                    
                    visitado[i]=true;
                } else if((this.grafo[x][i] == 1) && (visitado[i])){
                    for(int j=0; j<identacion[x]; j++){
                        System.out.print(space);
                    }
                    System.out.println(x + "-" + i + " ya visitado");

                }
            }
        }

        if(Chequeo(this.quieroCiclo, camino)){
            if (this.quieroCiclo){
                System.out.print("Ciclo encontrado: ");
                System.out.println(salida.substring(0,salida.length() - 1));
                System.out.println("El ciclo tiene "+ this.grafo.length + " vertices.");
                throw new Exception("Camino Encontrado");       
            } else{
                System.out.print("Camino encontrado: ");
                System.out.println(salida.substring(0,salida.length() - 1));
                System.out.println("El camino tiene "+ this.grafo.length + " vertices.");
                throw new Exception("Camino Encontrado");
            }
        }
    }
}