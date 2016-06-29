import java.lang.reflect.Proxy;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;

public class TestExplorer2 {

    static int getRandomDirection() {
      return (int)(Math.random() * 4);
    }

    static void randomMove(Explorer e) {
      switch (getRandomDirection()) {
          case 0: e.goNorth(); break;
          case 1: e.goSouth(); break;
          case 2: e.goEast(); break;
          case 3: e.goWest(); break;
      }
    }

    static void test(Explorer e) {
        for (int n=0; n<10; n++) {
          randomMove(e);
        }
        System.out.println("Explorer ended at " + e.getX() + "," + e.getY());
    }

    public static void main(String[] args) throws Exception {
      Explorer real = new ExplorerImpl();
      Explorer wrapper = (Explorer) composeAspects(Explorer.class, Logger.class, real);
      test(wrapper);

      printProxyProperties(wrapper);
    }

    static Object composeAspects(Class interfaceClass, Class aspectHandlerClass, Object real) throws Exception {
      Class[] interfaceClasses = new Class[] {interfaceClass};

      AspectHandler handler = (AspectHandler) aspectHandlerClass.newInstance();
      handler.setRealObject(real);

      return  Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                interfaceClasses,
                handler);
    }

    static void printProxyProperties(Object proxy) {
      Class clazz = proxy.getClass();
      log("class name: " + clazz.getName());
      log("isFinal:" + Modifier.isFinal(clazz.getModifiers()));

      log("\ninterfaces:");
      for (Class interfaceClass : clazz.getInterfaces()) log("  " + interfaceClass.getName());

      log("\nsuperclasses:");
      Class go = clazz;
      while (go != null) {
        go = go.getSuperclass();
        if (go != null) log("  " + go.getName());
      }

      log("\nmethods:");
      for (Method method : clazz.getMethods()) {
        log("  " + method.getName());
        log("    isSynthetic:" + method.isSynthetic());

        int modifiers = method.getModifiers();
        log("    isPublic:" + Modifier.isPublic(modifiers));
      }
    }

    static void log(Object obj) {
      System.out.println("msg: " + obj);
    }
}

// reference:
// - Using Dynamic Proxies to Layer New Functionality Over Existing Code
// - https://blog.frankel.ch/the-power-of-proxies-in-java/
