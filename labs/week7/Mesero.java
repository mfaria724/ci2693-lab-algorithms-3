import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Mesero {

  public static Restaurant readFile(String path, int origin){

    // Variables declaration.
    BufferedReader Lector;   
    String line;
    int tables;
    int connections;
    Restaurant restaurant = new Restaurant();
    double[][] coordinates;

    try {
      // Variables initialization.
      Lector = new BufferedReader(new FileReader(path));
      line = Lector.readLine();
      tables = Integer.parseInt(line);
      coordinates = new double[tables][2];
      restaurant = new Restaurant(tables);

      // Checks valid origin.
      if(origin >= tables){
        Lector.close();
        // throw new Exception("Invalid Origin");
      }

      // Add tables.
      for(int i = 0; i < tables; i++){
        
        // Read line.
        line = Lector.readLine();

        // Gets coordinates.
        String[] tableCoord = line.split(" ");

        // Saves coordinates
        coordinates[i][0] = Double.parseDouble(tableCoord[0]);
        coordinates[i][1] = Double.parseDouble(tableCoord[1]);
        
      }

      // Read line.
      line = Lector.readLine();
      connections = Integer.parseInt(line);

      // Add connections.
      for(int i = 0; i < connections; i++){
        
        // Read line.
        line = Lector.readLine();

        // Gets rooms.
        String[] tablesInd = line.split(" ");
        int t1 = Integer.parseInt(tablesInd[0]);
        int t2 = Integer.parseInt(tablesInd[1]);
        double cost = costE(t1,t2, coordinates);

        // Adds connection.
        restaurant.addConnection(t1, t2, cost);
      }

    } catch (FileNotFoundException ex) { // If file doesn't exist.
      System.out.println("El archivo especificado no existe, por favor, introduzca un archivo válido.");
      System.exit(0);
    } catch (IOException ex) { // If an I/O error occurs.
      System.out.println("Ha ocurrido un error de entrada/salida.");
      System.exit(0);
    } catch (Exception e){ // If file format is incorrect.
      if(e.getMessage().equals("Invalid Origin")){
        System.out.println("Nodo de origen inválido");
        System.out.println("Por favor, introduzca un nodo de origen válido.");
        System.exit(0);
      }else {
        System.out.println("El formato del archivo es incorrecto.");
        System.exit(0);
      }
    }

    // Message to user.
    System.out.println("El grafo de mesas y conexiones del restaurante fue leido correctamente.\n");
    return restaurant;

  }

  public static double costE(int t1, int t2, double[][] coordinates){
    double x = coordinates[t1][0] - coordinates[t2][0];
    double y = coordinates[t1][1] - coordinates[t2][1];
    return Math.sqrt(( x * x ) + ( y * y ));
  }

  public static void main(String[] args) {
    if(args.length < 2){
      System.out.println("Uso: java Mesero <instancia> <origen>");
    } else{
      try {
        int origin = Integer.parseInt(args[1]);
        Restaurant restaurant = readFile(args[0], origin);
        restaurant.dijkstra(origin);
      } catch (NumberFormatException e) {
        System.out.println("Nodo de origen inválido");
        System.out.println("Por favor, introduzca un nodo de origen válido.");
      } catch (Exception e){
        System.out.println("Ha ocurrido un error desconocido.");
        System.out.println("El programa finalizará.");
      }
    }
  }

}