/**
 * Class to transform string to double
 */
public class DoubleTransformer implements TypeTransformer<Double> {
  
  /**
   * Method to transform string to double.
   * @param value // String to be parsed.
   * @return
   */
  public Double transform(String value) throws NumberFormatException{
    return Double.parseDouble(value);
  }
}