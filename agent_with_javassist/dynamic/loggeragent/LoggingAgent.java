import java.lang.instrument.*;

public class LoggingAgent {
    /**
     * JVM hook to dynamically load javaagent at runtime.
     *
     * The agent class may have an agentmain method for use when the agent is
     * started after VM startup.
     *
     * @param args
     * @param inst
     * @throws Exception
     */
    public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println("agentmain method invoked with args: " + args + " and inst: " + inst);
        String className = "Greeter";
        String[] methodNames = new String[]{"greet", "beautify"};
        inst.addTransformer(new LoggingTransformer(className, methodNames), true);
        Class[] classes = inst.getAllLoadedClasses();
        for (Class c : classes) {
            if (c.getName().equals("Greeter") && inst.isModifiableClass(c)) {
               inst.retransformClasses(c);
               break;
            }
         }
    }
}
