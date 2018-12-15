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
  
public class Alumbrado {

  public static void main(String[] args) {
    if(args.length < 1){
      System.out.println("Uso: java Alumbrado <instancia>");
    }else {
      readFile(args[0]);
    }
  }

  public static void readFile(String path){
    System.out.println("Path: " + path);

    // Variables declaration.
    BufferedReader Lector; 
    String line;

    try {
      // Lector initialization
      Lector = new BufferedReader(new FileReader(path));

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
    } catch (Exception e){
      System.out.println("Formato de Archivo Inválido");
      System.exit(0);
    }
  }

}