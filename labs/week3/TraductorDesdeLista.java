/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
import java.util.ArrayList;

public class TraductorDesdeLista extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	public ArrayList<String> lista;
	
	/**Crea un grafo minimal*/

	TraductorDesdeLista(){
		this.lista = new ArrayList<ArrayList<String>>();

	//	throw new UnsupportedOperationException("Este metodo aun no ha sido "
	//			+"implementado");
	}

	public void matriciar(){

		int vertices = this.lista.size();
		this.grafo = new int[vertices][vertices];

		for (int k = 0; k < this.lista.size(); k++) {
			String[] fila = this.lista.get(i).split(" ");
			for (int j = 1; j < fila.length; j++){
				this.grafo[k][parseInt(fila[j])] = 1;
			}
		}
	}
	
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 */
	public void agregarVertice(int id){
	//	throw new UnsupportedOperationException("Este metodo aun no ha sido "
	//			+"implementado");
	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){
	//	throw new UnsupportedOperationException("Este metodo aun no ha sido "
	//			+"implementado");
	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		throw new UnsupportedOperationException("Este metodo aun no ha sido "
				+"implementado");
	}
}