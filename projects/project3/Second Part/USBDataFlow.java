/**
 * Reader
 */
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Exceptions  
 */ 
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ArrayList
 */
import java.util.ArrayList;

/**
 * Class to implement the spreed sheet
 */
public class USBDataFlow{

    /**
    * Method to read each expression in file
    * @param path Path to file
    */
    private static Sheet readFile(String path){

        // Variables Declaration
        BufferedReader Lector;   
        String line;
        String[] data;  // Array that has n and m in the first iteration, and the cells expresions after.
        int n;  // Number of rows
        int m;  // Number of columns
        Sheet sheet = new Sheet();  
    
        try {
             // Lector initialization
            Lector = new BufferedReader(new FileReader(path));

            // Dimesiones.
            line = Lector.readLine();
            data = line.split(" ");
            n = Integer.parseInt(data[0]);
            m = Integer.parseInt(data[1]);

            sheet = new Sheet(n,m);

            // Iterate over the rows of the sheet
            for(int i=0; i<n;i++){

                line = Lector.readLine();
                data = line.split(" "); 
                
                // Iterate over the columns of the sheet
                for(int j=0; j<m; j++){

                    // Take of the "=" of the expression
                    data[j] = data[j].replace("=", "");

                    // Update the exprMatrix with the expression
                    sheet.exprMatrix[i][j] = data[j];

                    // Find the cells that are in the actual expression
                    ArrayList<String> pred = sheet.findPred(data[j]);

                    // Iterate over the cells that are in the expression
                    for(int k=0; k<pred.size();k++){
                        int x = sheet.nameToIndex(pred.get(k));
                        int y = j*n + i;
                        // Updates the adjMatrix
                        sheet.adjMatrix[x][y] = 1;
                    }                    
                }
            }

            // for(int i=0; i<n*m;i++){
            //     System.out.print("\n");
            //     for(int j=0; j<n*m; j++){
            //         System.out.print(sheet.adjMatrix[i][j] + ", ");
            //     }
            // }

            // System.out.println(" ");            
            
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
            
            return sheet;
      }

      public static void main(String[] args) {
        if(args.length < 1){
          System.out.println("Uso: java test <archivo>");
        }else{
          Sheet sheet = readFile(args[0]);
          Evaluador evaluador = new Evaluador();



          int[][] topologicalSort = sheet.DFS();

          for(int i = 0; i<topologicalSort.length; i++){
            String valor = Integer.toString(evaluador.evaluateExpression(sheet.exprMatrix[topologicalSort[i][0]][topologicalSort[i][1]]));
            System.out.println(valor);
            sheet.exprMatrix[topologicalSort[i][0]][topologicalSort[i][1]] = valor;
            for(int j=0; j<sheet.exprMatrix.length;j++){
              for(int k=0; k<sheet.exprMatrix[0].length;k++){
                sheet.exprMatrix[j][k] = sheet.exprMatrix[j ][k].replace(sheet.getName(topologicalSort[i][0],topologicalSort[i][1]), valor);
              }
            }
          }

          for(int i=0;i<sheet.exprMatrix.length;i++){
            System.out.print("\n");
            for(int j=0;j<sheet.exprMatrix[0].length;j++){
              System.out.print(sheet.exprMatrix[i][j] + ", ");
            }
          }

        }    
      }
}