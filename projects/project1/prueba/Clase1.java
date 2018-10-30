import java.util.ArrayList;

public class Clase1{
    public static void main(String[] args){
        Clase2 clase = new Clase2();
        ArrayList<String> aja = clase.getAja();
        System.out.println(aja.toString() + " aja antes");
        ArrayList<String> newAja = new ArrayList<String>();
        newAja.add("aja");
        System.out.println(newAja.toString() + " new aja");
        clase.setAja(newAja);
        System.out.println(clase.getAja().toString() + " aja despues");
    }
}