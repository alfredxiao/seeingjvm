import java.lang.reflect.InvocationHandler;

interface AspectHandler extends InvocationHandler {
  void setRealObject(Object realObject);
}
