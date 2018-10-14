/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeMatriz extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
  public void readMatrixFile(String path){

  }
  
	/**Crea un grafo con el n&uacute;mero de v&eacute;rtices dado
	 * 
	 * @param vertices El n&uacute;mero de v&eacute;rtices del grafo
	 */
	TraductorDesdeMatriz(int vertices){
    this.grafo = new int[vertices][vertices];
	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){

    // Guarda en la matriz la relacion de adyacencia.
    this.grafo[verticeInicial][verticeFinal] = 1;

	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
    String resultado = "";

    // Itera sobre la matriz mientras se guarda la relacion.
    for (int i = 0; i < this.grafo.length; i++){
      resultado += i + ": ";
      for (int j = 0; j < this.grafo[i].length; j++){
        if (this.grafo[i][j] == 1){
          resultado += j + " ";
        }
      }
      resultado += "\n";
    }
    
    return resultado;
	}
}