import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class LoggingTransformer implements ClassFileTransformer {
    String targetClassName;
    String[] targetMethodNames;

    public LoggingTransformer(String className, String[] methodNames) {
        this.targetClassName = className;
        this.targetMethodNames = methodNames;
    }

    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] byteCode = classfileBuffer;

        if (className.equals(this.targetClassName)) {
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(targetClassName);
                for (CtMethod m : cc.getDeclaredMethods()) {
                    m.addLocalVariable("elapsedTime", CtClass.longType);
                    m.insertBefore(elapsedTimeStart() + logMethodEntering(m));
                    m.insertAfter(elapsedTimeEnd() + logMethodQuiting(m));
                }
                byteCode = cc.toBytecode();
                cc.detach();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return byteCode;
    }

    static String elapsedTimeStart() {
        return "elapsedTime = System.currentTimeMillis();";
    }

    static String logMethodEntering(CtMethod m) {
        return escape(String.format("System.out.println('>> Entering %1$s()');", m.getName()));
    }

    static String elapsedTimeEnd() {
        return "elapsedTime = System.currentTimeMillis() - elapsedTime;";
    }

    static String logMethodQuiting(CtMethod m) {
        return escape(String.format("System.out.println('<< Quiting %1$s()\\n<<<< elapsedTime(ms): ' + elapsedTime);", m.getName()));
    }

    static String escape(String s) {
        return s.replace('\'', '\"');
    }
}
