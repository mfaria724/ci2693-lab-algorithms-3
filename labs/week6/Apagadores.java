import java.util.ArrayList;
import java.util.Arrays;

public class Apagadores {

  public static void backtracking(Home home){
    
    ArrayList<int[]> result = new ArrayList<int[]>();
    
    int[] initialState = new int[home.getNumberOfRooms() + 1];
    Arrays.fill(initialState, 0);
    initialState[0] = 1;
    initialState[initialState.length - 1] = 0;

    result.add(initialState);

    result = BT(home, result);

  }

  public static ArrayList<int[]> BT(Home home, ArrayList<int[]> initialState){
    
    ArrayList<ArrayList<int[]>> solutions = new ArrayList<ArrayList<int[]>>();

    if(isSolutions(initialState)){
      solutions.add(initialState);
    }

    ArrayList<int[]> validActions = validActions(home, initialState);

    for(int i = 0; i < validActions.size(); i++){
      ArrayList<int[]> newState = apply(initialState, validActions.get(i));
      solutions.add(BT(home, newState));
    }

    return solutions;

  }

  public static boolean isSolution(ArrayList<int[]> initialState){
    
    int[] lastState = initialState.get(initialState.size() - 1);

    for(int i = 0; i < lastState.length - 2; i++){
      if(lastState[i] != 0){
        return false;
      }
    }

    if(lastState[lastState.length - 2] != 1){
      return false;
    }

    if(lastState[lastState.length - 1] != lastState.length - 2){
      return false;
    }

    return true;
  }

  public static void validActions(){

  } 

  public static void apply(){

  }

  public static Home readFile(String path){

    // Variables declaration.
    BufferedReader Lector;   
    String line;
    String[] quantities;
    int rooms;
    int connections;
    int switches;
    Home home = new Home(1);

    try {
      // Variables initialization.
      Lector = new BufferedReader(new FileReader(path));
      line = Lector.readLine();
      quantities = line.split(" ");

      rooms = Integer.parseInt(quantities[0]);  
      connections = Integer.parseInt(quantities[1]);    
      switches = Integer.parseInt(quantities[2]);  
      
      home = new Home(rooms);

      // Add connections.
      for(int i = 0; i < connections; i++){
        
        // Read line.
        line = Lector.readLine();

        // Gets rooms.
        String[] roomsIndexes = line.split(" ");
        int Room1 = Integer.parseInt(roomsIndexes[0]);
        int Room2 = Integer.parseInt(roomsIndexes[1]);

        // Adds connection.
        home.addConnection(Room1, Room2);
      }

      // Add switches.
      for(int i = 0; i < switches; i++){
        
        // Read line.
        line = Lector.readLine();

        // Gets rooms.
        String[] roomsIndexes = line.split(" ");
        int iRoom = Integer.parseInt(roomsIndexes[0]);
        int fRoom = Integer.parseInt(roomsIndexes[1]);

        // Adds connection.
        home.addSwitch(iRoom, fRoom);
      }

    } catch (FileNotFoundException ex) { // If file doesn't exist.
      System.out.println("El archivo especificado no existe, por favor, introduzca un archivo válido.");
      System.exit(0);
    } catch (IOException ex) { // If an I/O error occurs.
      System.out.println("Ha ocurrido un error de entrada/salida.");
      System.exit(0);
    } catch (NumberFormatException ex) { // If file format is invalid.
      System.out.println("El formato del archivo es incorrecto.");
      System.exit(0);
    } 

    // Message to user.
    System.out.println("El grafo de conexiones e interruputores de la casa fue leido correctamente.\n");
    return home;

  }
  public static void main(String[] args) {

    Home home;

    if(args.length < 1){
      System.out.println("Uso: java Apagadores <archivo>");
    }else{
      home = readFile(args[0]);
      backtracking(home);
    }



  }

}