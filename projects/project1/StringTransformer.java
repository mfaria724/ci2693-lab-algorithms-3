/**
 * Class to map string to strings.
 * It's necessary to parse doubles and booleans
 */
public class StringTransformer implements TypeTransformer<String> {
  
  /**
   * Method to return String.
   */
  public String transform(String value){
    return value;
  }
}