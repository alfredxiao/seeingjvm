class LoggingExplorer implements Explorer {
    Explorer realExplorer;
    public LoggingExplorer(Explorer realExplorer) {
        this.realExplorer = realExplorer;
    }

    static void log(String msg) {
      System.out.println("log : " + msg);
    }

    public int getX() {
        return realExplorer.getX();
    }
    public int getY() {
        return realExplorer.getY();
    }
    public void goNorth() {
        log("goNorth");
        realExplorer.goNorth();
    }
    public void goSouth() {
        log("goSouth");
        realExplorer.goSouth();
    }
    public void goEast() {
        log("goEast");
        realExplorer.goEast();
    }
    public void goWest() {
        log("goWest");
        realExplorer.goWest();
    }
}
