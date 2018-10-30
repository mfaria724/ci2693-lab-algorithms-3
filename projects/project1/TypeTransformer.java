/**
 * Interface to transform dynamic data types.
 * @param <T> // Data Type
 */
public interface TypeTransformer<T>{

  /**
   * Transform string to specified data type.
   * @param value // Value to be transformed.
   * @return
   * @throws NumberFormatException // If can't converto to datatype T
   */
  public T transform(String value) throws NumberFormatException; 

}