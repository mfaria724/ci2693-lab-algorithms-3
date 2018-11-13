/**
 * Class to transform inputs to Boolean.
 */
public class BooleanTransformer implements TypeTransformer<Boolean> {
  
  /**
   * Method to transform string to booleans
   * @param value // String to be converted.
   * @return  
   */
  public Boolean transform(String value){
    return Boolean.parseBoolean(value);
  }
}