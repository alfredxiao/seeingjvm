package framework;

import java.lang.reflect.*;

public class CallGate {
  Callable callable;

  public CallGate(Callable callable) {
    this.callable = callable;
  }

  public String call(String username, String value) throws Exception {
    Method method = callable.getClass().getMethod("call", String.class, String.class);
    AccessControl accessControl = method.getAnnotation(AccessControl.class);

    if (accessControl.allow().equals(username)) {
      return callable.call(username, value);
    } else {
      throw new RuntimeException("Access Denied");
    }
  }
}
