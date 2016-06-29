import java.lang.management.ManagementFactory;
import com.sun.tools.attach.VirtualMachine;

public class AgentLoader {
    private static final String jarFilePath = "../lib/loggingagent.jar";

    public static void loadAgent() {
        System.out.println("dynamically loading javaagent...");
        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
        int p = nameOfRunningVM.indexOf('@');
        String pid = nameOfRunningVM.substring(0, p);

        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(jarFilePath, "");
            vm.detach();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
