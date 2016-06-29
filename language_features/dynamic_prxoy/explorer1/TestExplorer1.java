public class TestExplorer1 {

    static int getRandomDirection() {
      return (int)(Math.random() * 4);
    }

    static void randomMove(Explorer e) {
      switch (getRandomDirection()) {
          case 0: e.goNorth(); break;
          case 1: e.goSouth(); break;
          case 2: e.goEast(); break;
          case 3: e.goWest(); break;
      }
    }

    static void test(Explorer e) {
        for (int n=0; n<10; n++) {
          randomMove(e);
        }
        System.out.println("Explorer ended at " + e.getX() + "," + e.getY());
    }

    public static void main(String[] args) {
      Explorer real = new ExplorerImpl();
      Explorer wrapper = new LoggingExplorer(real);
      test(wrapper);
    }
}
