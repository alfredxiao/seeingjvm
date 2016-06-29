public class Demo {
  public static void main(String[] args) throws Exception {
    greet("Alfred");

    // now load the agent
    AgentLoader.loadAgent();

    Thread.sleep(200);
    greet("Gary");
  }

  static void greet(String name) {
    Greeter greeter = new Greeter();
    System.out.println(greeter.greet(name));
  }
}
