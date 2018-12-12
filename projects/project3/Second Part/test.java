import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class test{

    private static Hoja readFile(String path){

        // Variables Declaration
        BufferedReader Lector;   
        String line;
        String[] data;
        int n;
        int m;
        Hoja hoja = new Hoja();
    
        try {
             // Lector initialization
            Lector = new BufferedReader(new FileReader(path));

            // Dimesiones.
            line = Lector.readLine();
            data = line.split(" ");
            n = Integer.parseInt(data[0]);
            m = Integer.parseInt(data[1]);

            hoja = new Hoja(n,m);

            for(int i=0; i<n;i++){
                line = Lector.readLine();
                data = line.split(" "); 
                for(int j=0; j<m; j++){
                    hoja.matrizExpresiones[i][j] = data[j];
                    ArrayList<String> pred = hoja.findPred(data[j]);
                    // System.out.println("j " + j + " n " + n + " i " + i);
                    for(int k=0; k<pred.size();k++){
                        // System.out.println(pred.get(k));
                        // System.out.println(hoja.nameToIndex(pred.get(k)));
                        System.out.println("n "+ n + " n*m " + n*m + " i(adj) " + hoja.nameToIndex(pred.get(k)) + " j(adj) "+ (j*n + i) + " i " + i + " j " + j);
                        System.out.println(hoja.matrizAdj[0].length);
                        hoja.matrizAdj[hoja.nameToIndex(pred.get(k))][j*n + i] = 1;
                    }                    
                }
            }

            String string = "b1";

            // int[] prueba = hoja.nameToCoord(string);
            // System.out.println(prueba[0] +  " " + prueba[1]);

            int aja = hoja.nameToIndex(string);
            System.out.println(aja);

            for(int i=0; i<n*m;i++){
                System.out.print("\n");
                for(int j=0; j<n*m; j++){
                    System.out.print(hoja.matrizAdj[i][j] + ", ");
                }
            }

            // for(int i=0; i<n;i++){
            //     System.out.print("\n");
            //     for(int j=0; j<m; j++){
            //         System.out.print(hoja.grafoCeldas[i][j] + ", ");
            //     }
            // }

            
            
          } catch (FileNotFoundException e) {
            // File doesn't exist
            System.out.println("El archivo especificado no existe. Intente de nuevo.");
            System.exit(0);
          } catch (IOException e) {
            // I/O Error
            System.out.println("Ha ocurrido un error de E/S. Intente de nuevo.");
            System.exit(0);
          } catch (NumberFormatException e) {
            // Invalid file format
            System.out.println("Formato de archivo incorrecto. Intente de nuevo.");
            System.exit(0);
          }// catch (Exception e) {
            // Unknown Error
            //   System.out.println("Ha ocurrido un error desconocido. Mensaje: " + e.getMessage());
            //   System.exit(0);
            // }
            
            return hoja;
      }

      public static void main(String[] args) {
        if(args.length < 1){
          System.out.println("Uso: java test <archivo>");
        }else{
          Hoja hoja = readFile(args[0]);

          hoja.DFS();

        }    
      }
}