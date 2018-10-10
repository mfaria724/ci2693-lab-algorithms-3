/**La clase <code>Bicycle</code> representa una bicicleta del mundo real.
 * Mantiene su {@link #rapidez}, {@link #velocidad} y {@link #cadencia}
 */
public class Bicycle {
  
  /**Almacena la velocidad alctual de la bicicleta.
   * 
   */
  protected int gears;

	/**Almacena la cadencia de los pedales en revoluciones por minuto.
	 * Se puede obtener con {@link #getCadence()} y fijar con
	 * {@link #changeCadence(int)}.
	 */
    int cadencia;
    
    /**Almacena la rapidez traslacional en millas por hora.
     * Se puede obtener con {@link #getSpeed()} y fijar con
     * {@link #speedUp(int)}.
     */
    int rapidez;
    
    /**Almacena el engranaje actual del cassette posterior de la
     * bicicleta (primera, segunda, tercera, etc).
     */
    int velocidad;

    /**Define el factor de conversión entre cadencia y
    * rapidez. Las subclases pueden cambiarlo según corresponda
    * a su tipo de bicicleta.
    */
    protected int factor = 25;

    /**Construye una bicicleta con la cantidad especificada de
    * velocidades.
    * @param gears Número de velocidades
    */
    public Bicycle(int gears){
      this.cadencia = 0;
      this.rapidez = 0;
      this.velocidad = 1;
      this.gears = gears;
    }

    /**Convierte la cadencia a la rapidez equivalente
    * sin considerar la gravedad o el momento de inercia.
    *
    * @return la rapidez producida por la cadencia
    *
    actual en millas por hora.
    */
    protected int convertirCadenciaARapidez(){
      return this.cadencia*this.velocidad/this.factor;
    }
    /**Fija la cadencia al número de revoluciones indicado
    * y actualiza la rapidez de forma acorde
    *
    * @param nuevaCadencia
    *
    Número de revoluciones por minuto
    *
    al cual se fijará la cadencia.
    */
    public void changeCadence(int nuevaCadencia) {
      this.cadencia = nuevaCadencia;
    }
    
    /**Fija la cadencia al n&uacute;mero de revoluciones indicado
     * 
     * @param nuevaCadencia
     * 		N&uacute;mero de revoluciones por minuto
     *      al cual se fijar&aacute; la cadencia.
     */
    public void changeCadence(int nuevaCadencia) {
         this.cadencia = nuevaCadencia;
    }

    /**Fija el engranaje actual al dado por la
     * <code>nuevaVelocidad</code>-&eacute;sima velocidad
     * @param nuevaVelocidad
     * 		Ordinal de la velocidad que se fijar&aacute;.
     */
    public void changeGear(int nuevaVelocidad) throws IllegalArgumentException {
      if ( nuevaVelocidad > gears || nuevaVelocidad <= 0) {
        throw new IllegalArgumentException(
          "No se puede poner" + nuevaVelocidad + "a."+
          " La bici solo tiene velocidades del 1" + 
          " al " + gears
        );
      }   
      this.velocidad = nuevaVelocidad;
    }

    /**Suma a {@link rapidez} el aumento indicado en millas por hora.
     * Note que este m&eacute;todo no afecta la cadencia.
     * 
     * @param aumento
     * 		Cantidad de millas en las que se 
     *      aumentar&aacute; la rapidez.
     */
    public void speedUp(int aumento) {
        this.rapidez = this.rapidez + aumento;   
    }

    /**Resta a {@link rapidez} la disminuci&oacute;n indicada
     * en millas por hora.
     * Note que este m&eacute;todo no afecta la cadencia.
     * 
     * @param disminucion
     * 		Cantidad de millas en las que 
     * 		disminuir&aacute; la rapidez.
     */
    public void applyBrakes(int disminucion) {
         this.rapidez = this.rapidez - disminucion;
    }

    /**Imprime los valores actuales de {@link #cadencia},
     * {@link #rapidez} y {@link #velocidad} por c&oacute;nsola.
     */
    public void printStates() {
        System.out.println(
        		" cadencia:"  + this.cadencia +
        		" rapidez:"   + this.rapidez  +
        		" velocidad:" + this.velocidad
        		);
   }
   
    /**Obtiene la cadencia actual.
     * 
     * @return el valor actual de {@link #cadencia}.
     */
   public int getCadence(){
	   return this.cadencia;
   }
   
   /**Obtiene la rapidez actual.
    * 
    * @return el valor actual de {@link #rapidez}.
    */
   public int getSpeed(){
	   return this.rapidez;
   }
   
   /**Obtiene la velocidad actual.
    * 
    * @return el valor actual de {@link #velocidad}.
    */
   public int getGear(){
	   return this.velocidad;
   }
   
   /**M&eacute;todo que envuelve a {@link #changeCadence(int)}
    * 
    * @param nuevaCadencia
    * 		N&uacute;mero de revoluciones por minuto
    *      al cual se fijar&aacute; la cadencia.
    */
   public void changeRpm(int newValue){
       changeCadence(newValue);
   }

}
