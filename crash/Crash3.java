import sun.misc.Unsafe;
import java.lang.reflect.*;

public class Crash3 {

  public static void main(String[] args) throws Exception {
    Field f = Unsafe.class.getDeclaredField( "theUnsafe" ); 
    f.setAccessible( true ); 
    Unsafe unsafe = (Unsafe) f.get( null );
  }
}
