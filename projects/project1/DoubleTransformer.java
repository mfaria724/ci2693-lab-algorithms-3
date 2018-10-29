public class DoubleTransformer implements TypeTransformer<Double> {
  public Double transform(String value) throws NumberFormatException{
    return Double.parseDouble(value);
  }
}