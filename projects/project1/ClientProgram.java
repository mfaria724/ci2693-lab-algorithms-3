public class ClientProgram {

  private static void printMenu(boolean firstTime){
    System.out.println("printMenu() Function");

    // Checks if it is the first time.
    if (firstTime){
      System.out.println("¡BIENVENIDO!");
    }

    System.out.println("Indique una de las siguientes operaciones para continuar: ");

  }

  public static void main(String[] args) {

    String mensajeError = "Uso: java ClientProgram <rutaArchivo>";

    if(args.length < 1){
      System.err.println(mensajeError);
      return;
    } else {

      try {
        // TADGrafo grafo = new cargarGrafo();
        
        printMenu(true);  

      } catch (Exception e) {
        //TODO: handle exception
      }
      
    }

  }

}