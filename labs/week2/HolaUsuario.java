/**
* Dice hola al primer parámetro
*/
class HolaUsuario{
  public static void main(String[] args) {
    if(args.length>0){
      System.out.println("Hola "+args[0]+"!");
    } else {
      System.out.println("No tienes nombre?");
    }
  }
}