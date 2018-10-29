public interface TypeTransformer<T>{
  public T transform(String value) throws NumberFormatException; 
}