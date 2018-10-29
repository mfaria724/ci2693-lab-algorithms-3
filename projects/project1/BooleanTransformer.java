public class BooleanTransformer implements TypeTransformer<Boolean> {
  public Boolean transform(String value){
    System.out.println("Boolean Transformer");
    return Boolean.parseBoolean(value);
  }
}