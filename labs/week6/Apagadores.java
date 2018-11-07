import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main class to implement main method an lab function.
 */
public class Apagadores {

  /**
   * Applies BFS over the implicit graph.
   * @param home // Graph to calculate implicit graph.
   * @return
   */
  public static ArrayList<int[]> BFS(Home home){

    // Variables initialization.
    ArrayList<int[]> shortestWay = new ArrayList<int[]>();

    // Initial state.
    int[] first = new int[home.getNumberOfRooms() + 1];
    Arrays.fill(first, 0);
    first[0] = 1;
    shortestWay.add(first);

    // Queue of states.
    ArrayList<ArrayList<int[]>> queue = new ArrayList<ArrayList<int[]>>();
    queue.add(shortestWay);

    // Until there is no elements in the queue.
    while (queue.size() != 0){

      // Pick an element from queue.
      ArrayList<int[]> x = queue.get(0);
      queue.remove(0);

      // Gets all valid actions.
      ArrayList<int[]> validActions = validActions(home, x);

      // Applies all valid actions.
      for(int i = 0; i < validActions.size(); i++){
        
        // Initializes a new way.
        shortestWay = new ArrayList<int[]>(x);
        int[] action = validActions.get(i);

        // Adds new action to that way.
        shortestWay.add(action);

        // Checks if you found solution.
        if(isSolution(shortestWay)){

          // Prints result and returns.
          printResult(shortestWay);
          return shortestWay;
        }
        
        // Adds new action to the queue.
        queue.add(shortestWay);
      }

    }

    // Prints result.
    printResult(shortestWay);
    return shortestWay;

  }

  /**
   * Checks if a solution has been found.
   * @param initialState // State for the solution.
   * @return
   */
  public static boolean isSolution(ArrayList<int[]> initialState){
    
    // Gets last element.
    int[] lastState = initialState.get(initialState.size() - 1);

    // Checks a room has lights off.
    for(int i = 0; i < lastState.length - 2; i++){
      if(lastState[i] != 0){
        return false;
      }
    }

    // Checks if  last room has lights on.
    if(lastState[lastState.length - 2] != 1){
      return false;
    }

    // Checks if is in the last room.
    if(lastState[lastState.length - 1] != lastState.length - 2){
      return false;
    }

    return true;
  
  }

  public static ArrayList<int[]> validActions(Home home, ArrayList<int[]> initialState){

    ArrayList<int[]> result = new ArrayList<int[]>();
    int[] finalState = initialState.get(initialState.size() - 1);
    int n = finalState.length;
    int[] possibleState = new int[n];

    for(int i = 0; i < n-1 ; i++){

      // Prender luz
      if(finalState[i] == 0 && home.getSwitches()[finalState[n-1]][i] == 1){
        possibleState = finalState.clone();
        possibleState[i] = 1;
        if(!containsArray(initialState,possibleState)){
        
          result.add(possibleState);
        }
        // Apagar Luz
      } else if(finalState[i] == 1 && home.getSwitches()[finalState[n-1]][i] == 1){
        possibleState = finalState.clone();
        possibleState[i] = 0; 
        if(!containsArray(initialState,possibleState)){

          result.add(possibleState);
        }
      }

      // Moverse
      if(finalState[i] == 1 && home.getConnections()[finalState[n-1]][i] == 1){
        possibleState = finalState.clone();
        possibleState[n-1] = i;

        if(!containsArray(initialState,possibleState)){

          result.add(possibleState);
        }
      }
    }

    return result;
  
  }

  private static boolean containsArray(ArrayList<int[]> states, int[] possibleState){
    boolean result = false;

    for(int i=0;i<states.size();i++){
      if(Arrays.equals(states.get(i),possibleState)){
        result = true;
      }
    }
    return result;
  }

  /**
   * Prints result in specified way.
   * @param way
   */
  public static void printResult(ArrayList<int[]> way){

    // Checks if there is a way.
    if(way.size() != 1){

      // Number of steps.
      System.out.println("El problema puede ser resuelto en " + (way.size() - 1) + " pasos.");

      // Prints each step.
      for(int i = 1; i < way.size(); i++){
        int[] last = way.get(i - 1);
        int[] actual = way.get(i);

        // Movement or lights.
        if(last[last.length - 1] != actual[actual.length - 1]){
          System.out.println("- Muévete a la habitación " + (actual[actual.length - 1] + 1));
        }else{

          for(int j = 0; j < last.length - 1; j++){
            if(last[j] < actual[j]){ // Lights on
              System.out.println("- Enciende la luz de la habitación " + (j + 1));
              break;
            }else if(last[j] > actual[j]) { // Lights off
              System.out.println("- Apaga la luz de la habitación " + (j + 1));
              break;
            }
          }
          
        }
      }

    }else { // There is no way.
      System.out.println("El problema no puede ser resuelto.");
    }

  }

  /**
   * Reads a file with an specific format and charges it into Home representation (Graph with 
   * two types of edges.)
   * @param path // Path to file.
   * @return
   */
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
    } catch (Exception e){ // If file firmat is incorrect.
      System.out.println("El formato del archivo es incorrecto.");
      System.exit(0);
    }

    // Message to user.
    System.out.println("El grafo de conexiones e interruputores de la casa fue leido correctamente.\n");
    return home;

  }

  /**
   * Main program to run application.
   * @param args
   */
  public static void main(String[] args) {

    // Variables initialization.
    Home home;

    // Checks if file was provided.
    if(args.length < 1){
      System.out.println("Uso: java Apagadores <archivo>");
    }else{
      // Reads home graph from file.
      home = readFile(args[0]);

      // Applies BFS in implicit graph.
      BFS(home);
    }

  }

}