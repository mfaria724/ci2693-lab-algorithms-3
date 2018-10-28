public class DoubleTransformer implements TypeTransformer<Double> {
  public Double transform(String value){
    return Double.parseDouble(value);
  }
}