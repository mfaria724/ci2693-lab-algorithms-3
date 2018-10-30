import java.util.ArrayList;

public class Clase2{
    private ArrayList<String> aja = new ArrayList<String>();
    private String nombreSerio = "";

    // public String getNombreSerio(){

    // }

    public ArrayList<String> getAja(){
        ArrayList<String> ajaFalso = this.aja;
        return ajaFalso;
    }

    public void setAja(ArrayList<String> NewAja){
        System.out.println(this.aja.toString() + "aja dentro antes");
        System.out.println(NewAja.toString() + " new aja dentro");

        this.aja = NewAja;
        System.out.println(this.aja.toString() + " aja dentro despues");

    }
}