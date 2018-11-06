import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Apagadores {

  public static int contador = 0;
  public static ArrayList<int[]> shortestWay = new ArrayList<>();

  public static void backtracking(Home home){
    
    ArrayList<ArrayList<int[]>> result = new ArrayList<ArrayList<int[]>>();
    ArrayList<int[]> initialState = new ArrayList<int[]>();
    
    int[] first = new int[home.getNumberOfRooms() + 1];
    Arrays.fill(first, 0);
    first[0] = 1;
    // first[first.length - 1] = 0;

    initialState.add(first);

    result = BT(home, initialState);

    String ll = "";
      
    for(int i=0; i< result.size();i++){  
      ll += "<";
      for(int j = 0; j < result.get(i).size(); j++){
        ll += "[";
        for(int k = 0; k < result.get(i).get(j).length; k++){
          ll += result.get(i).get(j)[k] + ",";
        }
        ll += "],";
      }
      ll += ">\n";
    }
    System.out.println(ll);
    
    System.out.println("shortestWay validActions: ");
    for(int i = 0; i < shortestWay.size(); i++){
      System.out.print("[");
      for(int j = 0; j < shortestWay.get(i).length; j++){
        System.out.print(shortestWay.get(i)[j]);
        System.out.print(",");
      }
      System.out.print("]\n");  
    }

    

    System.out.println("Resultados: " + result.size());

  }

  public static ArrayList<ArrayList<int[]>> BT(Home home, ArrayList<int[]> initialState){
    
    System.out.println("Esta iniciando BT");
    ArrayList<ArrayList<int[]>> solutions = new ArrayList<ArrayList<int[]>>();

    for(int i = 0; i < initialState.size(); i++){
      if(i > 9){
        System.exit(0);
      }
      System.out.println("Estado " + i + ": ");
      System.out.print("[");
      for(int j = 0; j < initialState.get(i).length; j++){
        System.out.print(initialState.get(i)[j]);
        System.out.print(",");
      }
      System.out.print("]\n");
    }

    if(isSolution(initialState)){
      if(shortestWay.size() == 0){
        shortestWay = initialState;
        solutions.add(initialState);
      } else {
        if(initialState.size() < shortestWay.size()){
          shortestWay = initialState;
          solutions.add(initialState);
        } else{
          solutions.add(initialState);
          
        }
      }
    }

    // if(Arrays.equals(initialState.get(initialState.size() - 1), [1,1,1,1,0,1]) ){
    //   System.exit(0);
    // }

    System.out.println("Paso is solution:");

    ArrayList<int[]> validActions = validActions(home, initialState);

    System.out.println("Paso acciones validas " + validActions.size());

    for(int i = 0; i < validActions.size(); i++){

      System.out.print("validAction " + i + ": ");
      System.out.print("[");
      for(int j = 0; j < validActions.get(i).length; j++){
        System.out.print(validActions.get(i)[j]);
        System.out.print(",");
      }
      System.out.print("]\n");

      ArrayList<int[]> newState = new ArrayList<int[]>(initialState); 

      String ll = "";
      
      System.out.println("newState antes: " + newState.size());
      for(int j = 0; j < newState.size(); j++){
        ll = "[";
        for(int k = 0; k < newState.get(j).length; k++){
          ll += newState.get(j)[k] + ",";
        }
        ll += "]\n";
      }
      System.out.println(ll);


      newState.add(validActions.get(i));


      System.out.println("newState despues: " + newState.size());
      for(int j = 0; j < newState.size(); j++){
        System.out.println("Elemento " + j);
        ll = "[";
        for(int k = 0; k < newState.get(j).length; k++){
          ll += newState.get(j)[k] + ",";
        }
        ll += "]\n";
        System.out.println(ll);
      }

      System.out.println("Aqui");
      
      // if(contador > 80){
        // System.exit(0);
      // }

      contador += 1;
      System.out.println("Entro recursion");
      solutions.addAll(BT(home, newState));
      System.out.println("Salgo recursion");

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
    int[] finalState = initialState.get(initialState.size() - 1);
    int n = finalState.length;
    int[] possibleState = new int[n];

    // System.out.print("finalState validActions: ");
    // System.out.print("[");
    // for(int j = 0; j < finalState.length; j++){
    //   System.out.print(finalState[j]);
    //   System.out.print(",");
    // }
    // System.out.print("]\n");
    // Prender o apagar las luces
    for(int i = 0; i < n-1 ; i++){
      // System.out.println("Iteracion: " + i);
      // System.out.println("finalState[n-1]: " + finalState[n-1]);
      // System.out.println("finalState[i]: " + finalState[i]);
      // System.out.println("Iteracion: " + home);

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

    

    // System.out.println("result validActions: ");
    // for(int i = 0; i < result.size(); i++){
    //   System.out.print("[");
    //   for(int j = 0; j < result.get(i).length; j++){
    //     System.out.print(result.get(i)[j]);
    //     System.out.print(",");
    //   }
    //   System.out.print("]\n");  
    // }


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

    System.out.println("Rooms: " + home.getConnections().length);
    System.out.println("Connections: ");
    String ll = "";
    for(int i = 0; i < home.getConnections().length; i++){
      for(int j = 0; j < home.getConnections().length; j++){
        ll += home.getConnections()[i][j] + " ";
      }
      ll += "\n";
    }
    System.out.println(ll);

    System.out.println("Switches: ");
    ll = "";
    for(int i = 0; i < home.getSwitches().length; i++){
      for(int j = 0; j < home.getSwitches().length; j++){
        ll += home.getSwitches()[i][j] + " ";
      }
      ll += "\n";
    }

    System.out.println(ll);


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