public class Hamilton {

  public static void main(String[] args) {
    if(args.length < 1){
      System.err.println("Uso: java Hamilton <nombreArchivo> <BFS|DFS> <cycle>");
      return;
    } else if (args.length > 2) {
      System.out.println("Quiero ciclo");
    } 
    else {
      System.out.println("Uso normal");
    }
  }

}