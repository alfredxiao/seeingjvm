
public aspect Logging {
  pointcut myClass(): within(app.Greeter);

	pointcut myMethod () : myClass() &&	execution(String beautify(String));

	before () : myMethod() {
		System.out.println(">> entering " + thisJoinPoint);
	}

	after () : myMethod() {
		System.out.println("<< quiting " + thisJoinPoint);
	}
}
