import java.lang.instrument.*;

public class LoggingAgent {

   public static void premain(String agentArgs, Instrumentation inst) {
       System.out.println("running LoggingAgent.premain()...");
       String className = "Greeter";
       String[] methodNames = new String[]{"greet", "beautify"};
       inst.addTransformer(new LoggingTransformer(className, methodNames));
   }
}
