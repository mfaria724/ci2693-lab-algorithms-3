import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**<p>Programa para convertir archivos Lista de Adyacencia a archivos Matriz de
 * Adyacencia.</p>
 * 
 * <p>Un archivo Lista de Adyacencia representa un grafo, donde cada
 * l&iacute;nea tiene el formato:</p>
 * <blockquote>
 * <code>v<sub>i</sub>: v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>i</sub></code> es un n&uacute;mero de un v&eacute;rtice, y 
 * <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code> son los n&uacute;meros
 * de los v&eacute;rtices adyacentes a <code>v<sub>i</sub></code></p>
 * <p>mientras que un archivo Matriz de adyacencia representa un grafo
 * en el formato</p>
 * <blockquote>
 * <code> &nbsp; v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub><br>
 * v<sub>1</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * v<sub>2</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * &vellip;<br>
 * v<sub>n</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub></code> son
 * los n&uacute;meros que identifican a los v&eacute;rtices, y <code>a<sub>i</sub></code>
 * indica si existe un arco desde el v&eacute;rtice al inicio de esa fila, y el
 * v&eacute;rtice al que corresponde esa columna.</p>
 * <p>El programa {@link #main} lee un archivo, detecta (a trav&eacute;s de
 * {@link #esLista(String)}) el formato del archivo, lo carga (a trav&eacute;s de
 * {@link #cargarGrafo(String)}) en un {@link TraductorGrafo}, y lo imprime
 * en el format contrario. Las funciones se ofrecen a nivel de package.</p>
 */
public class Cliente{
	/**Detecta el tipo de archivo basado en una muestra de una l&iacute;nea tomada del
	 * archivo.
	 * 
	 * @param  linea              La l&iacute;nea de muestra tomada del archivo
	 * @return <code>true</code>  si est&aacute; en el formato de un archivo lista de
	 *                            adyacencias;<br></br>
	 *         <code>false</code> si est&aacute; en el formato de un archivo Matriz de
	 *                            adyacencias.
	 * 
	 * @throws IllegalArgumentException si la l&iacute;nea no tiene ninguno de los dos
	 *                                  formatos
	 */
	static boolean esLista(String linea)
			throws IllegalArgumentException
	{ 
	// Verifica si la primera linea del archivo comienza con " " para saber si es una matriz
	// de adyacencia o una lista de adyacencia.
    if(linea.startsWith(" ")){
      return false;
    } else {
      return true;
    }
	}
	
	/**Carga la <code>linea</code> de un archivo Lista de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarLista(String linea, TraductorDesdeLista grafo)
			throws IllegalArgumentException
	{
		int vertice = Integer.parseInt(linea.substring(0,1));		// Guarda el vertice
		linea = linea.substring(3);									// Guarda los vertices sucesores 
		String[] lista = linea.split(" ");							// Almacena los vertices sucesores en un arreglo

		// Agrega vertice en la matriz de adyacencia
		grafo.agregarVertice(vertice);

		// Itera sobre los vertices sucesores al vertice agregado agregando los arcos en la matriz de adyacencia
		for(int i=0;i<lista.length;i++){
			grafo.agregarArco(vertice, Integer.parseInt(lista[i]));
		}

	}
	
	/**Carga la <code>linea</code> de un archivo Matriz de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarMatriz(String linea, TraductorDesdeMatriz grafo)
			throws IllegalArgumentException
	{
    if (linea.startsWith(" ") || linea.startsWith("-")) {
      return;
    } else {
      int verticeInicial = Integer.parseInt(linea.substring(0,1)); // Guarda el primer caracter.
      linea = linea.substring(3); //Guarda el resto de la cadena.
      String[] lista = linea.split(" "); //Convierte en array.

      // Itera sobre el array para encontrar los vertices adyacentes.
      for (int i = 0; i < lista.length; i++) {
        if (Integer.parseInt(lista[i]) == 1) {
          grafo.agregarArco(verticeInicial, i);
        }
      }
    }
	}
	
	// Metodo que se encarga de verificar si el grafo es no dirigido
	private static void noDirigido(TraductorGrafo grafo){

		// Inicializa variable booleana que almacena si el grafo es no dirigido
		boolean esNoDirigido = true;

		// Itera sobre la matriz de adyacencias del grafo verificando que paratodo i,j se cumple que
		// grafo[i][j] == grafo[j][i]
		for(int i=0;i<grafo.grafo.length;i++){
			for(int j=0;j<grafo.grafo.length;j++){
				if(grafo.grafo[i][j] != grafo.grafo[j][i]){
					// En caso de encontrar un caso para el cual no se cumple, se asigna a la variable booleana false
					esNoDirigido = false;
				}
			}
		}

		// Verifica el estado de la variable booleana, y dependiendo de este imprime el mensaje correspondiente
		if(esNoDirigido){
			System.out.println("El grafo es no dirigido");
		} else {
			System.out.println("El grafo es dirigido");
		}

	}
	/**Detecta el n&uacute;mero de v&eacute;rtices en un archivo Matriz de Adyacencias 
	 * basado en una muestra de una l&iacute;nea tomada del archivo.
	 * 
	 * @param  linea  La l&iacute;nea del archivo que se desea cargar.
	 * @return        el n&uacute;mero de v&eacute;rtices detectado
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static int detectarVertices(String linea)
			throws IllegalArgumentException
	{
    linea = linea.substring(3); // Elimina los espacios innecesarios
    String[] lista = linea.split(" ");
    
    return lista.length;
	}
	
	/**Carga un grafo desde un archivo y lo almacena en un
	 * {@link TraductorGrafo}.
	 * 
	 * @param  nombreArchivo nombre o ruta del archivo que se desea cargar.
	 * @return               Un <code>TraductorGrafo</code> que contiene el 
	 *                       grafo representado en el archivo dado.
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	static TraductorGrafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
	TraductorGrafo salida;
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		
		if(esLista(linea)){
			salida = new TraductorDesdeLista();
			do{
				cargarLista(linea, (TraductorDesdeLista)salida);
				linea = Lector.readLine();
			}while(linea != null);
		}else{
			salida = new TraductorDesdeMatriz(detectarVertices(linea));
			do{
				cargarMatriz(linea, (TraductorDesdeMatriz)salida);
				linea = Lector.readLine();
			}while(linea != null);
		}
		
		return salida;
	}
	
	/**Carga el grafo representado en el archivo dado y lo muestra en su
	 * representaci&oacute;n alternativa.
	 * 
	 * @param args Arreglo que contiene el nombre el archivo como &uacute;nico elemento
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		if(args.length < 1){
			System.err.println("Uso: java Cliente <nombreArchivo>");
			return;
		}
		
		TraductorGrafo g = cargarGrafo(args[0]);
		
		System.out.println(g.imprimirGrafoTraducido());
		noDirigido(g);
	}
}