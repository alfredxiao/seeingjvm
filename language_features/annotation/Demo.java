import framework.*;

public class Demo {
  public static void main(String[] args) throws Exception {

    Resource greeter = new Resource();
    CallGate callGate = new CallGate(greeter);

    String greeting = callGate.call(args[0], "Alfred");
    System.out.println(greeting);
  }
}
