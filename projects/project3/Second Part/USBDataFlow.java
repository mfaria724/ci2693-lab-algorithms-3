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
 * Class to use the spreed sheet
 * @author Manuel Faria 15-10463
 * @author Juan Oropeza 15-11041
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
                    sheet.exprMatrix[i][j] = data[j].toUpperCase();

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
          } catch (IndexOutOfBoundsException e){
            System.out.println("Se está referenciando una celda fuera del rango. El formato de archivo es incorrecto. Intente de nuevo.");
            System.exit(0);
          } catch (Exception e) {
          // Unknown Error
            System.out.println("Ha ocurrido un error desconocido. Mensaje: " + e.getMessage());
            System.exit(0);
          }
            
            return sheet;
      }

      /**
       * Main route
       * @param args Arguments passed by user
       */
      public static void main(String[] args) {
        // Checks if file was provided
        if(args.length < 1){
          System.out.println("Uso: java test <archivo>");
        }else{
          // 
          Sheet sheet = readFile(args[0]);
          // Evualuator of expression
          Evaluador evaluador = new Evaluador();

          // Gets the topogical sort
          int[][] topologicalSort = sheet.DFS();

          // Iterate over the the array of topological sort
          for(int i = 0; i<topologicalSort.length; i++){
            
            int x = topologicalSort[i][0];
            int y = topologicalSort[i][1];

            // Gets the valor of the expression of the cell
            String valor = Integer.toString(evaluador.evaluateExpression(sheet.exprMatrix[x][y]));

            // Update the expression matrix
            sheet.exprMatrix[x][y] = valor;

            // Iterate over the expression matrix to update the evaluated cells
            for(int j=0; j<sheet.exprMatrix.length;j++){
              for(int k=0; k<sheet.exprMatrix[0].length;k++){
                sheet.exprMatrix[j][k] = sheet.exprMatrix[j ][k].replace(sheet.getName(x,y), valor);
              }
            }
          }

          // Prints the final sheet
          printResultMatrix(sheet);


        }    
      }

      /**
       * Method to print the final expr result
       * @param sheet sheet to get the expressions matrix 
       */
      public static void printResultMatrix(Sheet sheet){
        for(int i=0;i<sheet.exprMatrix.length;i++){
          for(int j=0;j<sheet.exprMatrix[0].length;j++){
            System.out.print(sheet.exprMatrix[i][j] + "\t");
          }
          System.out.print("\n");
        }
      }
}