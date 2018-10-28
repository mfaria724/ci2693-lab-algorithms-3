public class BooleanTransformer implements TypeTransformer<Boolean> {
  public Boolean transform(String value){
    return Boolean.parseBoolean(value);
  }
}