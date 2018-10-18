/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */

public class TraductorDesdeLista extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	
	/**Crea un grafo minimal*/

	TraductorDesdeLista(){
		this.grafo = new int[1][1];
	}
	
	
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 */
	public void agregarVertice(int id){

		// Verifica si el vertice ya esta en la matriz, en caso de no estarlo se crea una nueva matriz
		// de largo del indice del vertice y se copia toda la matriz orginal en esta.
		if(id + 1 > this.grafo.length){
			int nuevoGrafo[][] = new int[id + 1][id + 1];

			for(int i=0; i<this.grafo.length; i++){
				System.arraycopy(this.grafo[i], 0, nuevoGrafo[i], 0, this.grafo.length);
			}
			this.grafo = nuevoGrafo;
		}

	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){

		// Verifica si el vertice del extremo final esta dentro de la matriz,
		// en caso de no estarlo redimensiona la matriz nuevamente
		if(verticeFinal + 1 > this.grafo.length){
			this.agregarVertice(verticeFinal);
		}

		// Guarda en la matriz la relacion de adyacencia
		this.grafo[verticeInicial][verticeFinal] = 1;
	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		String resultado ="   ";

		// Corresponde a la primera linea con los vertices de la matriz
		for(int i=0; i<this.grafo.length;i++){
			resultado += i + " ";
		}
		resultado += "\n";
		// Corresponde  a la segunda linea llena de "-"
		for(int i=0;i< 2*grafo.length + 2; i++ ){
			resultado += "-";
		}
		resultado += "\n";
		// Recorre toda la matriz, imprimiendo los datos en el orden correspondiente
		for(int i=0;i<grafo.length;i++){
			resultado+= i + "|";
			for (int j=0; j<grafo.length;j++){
				resultado += " " + this.grafo[i][j];
			}
			resultado+= "\n";
		}
		return resultado;
	}
}