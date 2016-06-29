import java.lang.reflect.Method;

class Logger implements AspectHandler {
    private Object delegate;

    public Object invoke(Object proxy, Method meth, Object[] args) throws Throwable {
        System.out.println("log : " + meth.getName());
        return meth.invoke(delegate, args);
    }

    public void setRealObject(Object realObject) {
      this.delegate = realObject;
    }
}
