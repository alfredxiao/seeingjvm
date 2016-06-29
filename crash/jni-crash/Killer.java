public class Killer {
    public native void killMeNow();

    static {
        System.loadLibrary("Killer");
    }
}

