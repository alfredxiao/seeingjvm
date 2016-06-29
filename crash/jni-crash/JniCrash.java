public class JniCrash {

  public static void main(String[] args) {
    System.out.println(System.getProperty("java.library.path"));

    System.out.println("Killing...");
    Killer killer = new Killer();
    killer.killMeNow();
  }
}

