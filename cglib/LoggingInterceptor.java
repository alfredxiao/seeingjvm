import java.util.*;
import net.sf.cglib.proxy.*;
import java.lang.reflect.*;

class LoggingInterceptor implements MethodInterceptor {
  public Object intercept(Object object,
                          Method method,
                          Object[] args,
                          MethodProxy methodProxy) throws Throwable {
      String className = object.getClass().getName();
      String methodName = method.getName();
      log("entering " + className + "." + methodName + "()");
      try {
        return methodProxy.invokeSuper(object, args);
      } finally {
        log("leaving " + className + "." + methodName + "()");
      }
  }

  static void log(String msg) {
    System.out.println(new Date().toString() + " : " + msg);
  }
}
