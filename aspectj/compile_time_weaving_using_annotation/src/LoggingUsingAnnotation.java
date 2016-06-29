import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Aspect
public class LoggingUsingAnnotation {

    @Before("execution (* app.Greeter.greet*(..))")
    public void advice(JoinPoint joinPoint) {
        System.out.printf(">>>>> called on '%s'%n", joinPoint);
    }
}
