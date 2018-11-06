import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Apagadores {

  public static void backtracking(Home home){
    
    ArrayList<ArrayList<int[]>> result = new ArrayList<ArrayList<int[]>>();
    ArrayList<int[]> initialState = new ArrayList<int[]>();
    
    int[] first = new int[home.getNumberOfRooms() + 1];
    Arrays.fill(first, 0);
    first[0] = 1;
    first[first.length - 1] = 0;

    initialState.add(first);

    result = BT(home, initialState);

    System.out.println("Resultados: " + result.size());

  }

  public static ArrayList<ArrayList<int[]>> BT(Home home, ArrayList<int[]> initialState){
    
    ArrayList<ArrayList<int[]>> solutions = new ArrayList<ArrayList<int[]>>();

    for(int i = 0; i < initialState.size(); i++){
      System.out.println("Estado " + i + ": ");
      System.out.print("[");
      for(int j = 0; j < initialState.get(i).length; j++){
        System.out.print(initialState.get(i)[j]);
        System.out.print(",");
      }
      System.out.print("]\n");
    }

    if(isSolution(initialState)){
      solutions.add(initialState);
    }

    System.out.println("Paso is solution:");

    ArrayList<int[]> validActions = validActions(home, initialState);

    System.out.println("Paso acciones validas");

    for(int i = 0; i < validActions.size(); i++){
      ArrayList<int[]> newState = new ArrayList<int[]>(initialState); 
      newState.add(validActions.get(i));
      solutions.addAll(BT(home, newState));
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

  public static ArrayList<int[]> validActions(Home home, ArrayList<int[]> initialState){
    
    ArrayList<int[]> result = new ArrayList<int[]>();
    int[] finalState = result.get(result.size() - 1);
    int n = finalState.length;
    int[] possibleState = new int[n];

    // Prender o apagar las luces
    for(int i=0; i<n - 1 ; i++){
      if(finalState[i] == 0 && home.getSwitches()[finalState[n]][finalState[i]] == 1){
        possibleState = finalState;
        possibleState[i] = 1;
        result.add(possibleState);

      } else if(finalState[i] == 1 && home.getSwitches()[finalState[n]][finalState[i]] == 0){
        possibleState = finalState;
        possibleState[i] = 0;
        result.add(possibleState);
      }

      if(finalState[i] == 1 && home.getConnections()[finalState[n]][finalState[i]] == 1){
        possibleState = finalState;
        possibleState[n] = i;
        result.add(possibleState);
      }
    }

    return result;
  
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
        int Room1 = Integer.parseInt(roomsIndexes[0]) - 1;
        int Room2 = Integer.parseInt(roomsIndexes[1]) - 1;

        // Adds connection.
        home.addConnection(Room1, Room2);
      }

      // Add switches.
      for(int i = 0; i < switches; i++){
        
        // Read line.
        line = Lector.readLine();

        // Gets rooms.
        String[] roomsIndexes = line.split(" ");
        int iRoom = Integer.parseInt(roomsIndexes[0]) - 1;
        int fRoom = Integer.parseInt(roomsIndexes[1]) - 1;

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

    // System.out.println("Rooms: " + home.getConnections().length);
    // System.out.println("Connections: ");
    // String ll = "";
    // for(int i = 0; i < home.getConnections().length; i++){
    //   for(int j = 0; j < home.getConnections().length; j++){
    //     ll += home.getConnections()[i][j] + " ";
    //   }
    //   ll += "\n";
    // }
    // System.out.println(ll);

    // System.out.println("Switches: ");
    // ll = "";
    // for(int i = 0; i < home.getSwitches().length; i++){
    //   for(int j = 0; j < home.getSwitches().length; j++){
    //     ll += home.getSwitches()[i][j] + " ";
    //   }
    //   ll += "\n";
    // }

    // System.out.println(ll);


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