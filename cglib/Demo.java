import java.util.*;
import net.sf.cglib.proxy.*;
import java.lang.reflect.*;


public class Demo {
  public static void main(String[] args) {
    Greeter enhancedGreeter = (Greeter) Enhancer.create(Greeter.class, new LoggingInterceptor());

    System.out.println(enhancedGreeter.greet("Alfred"));

    System.out.println("Calling unproxied object directly...");
    System.out.println(new Greeter().greet("Gary"));
  }
}
