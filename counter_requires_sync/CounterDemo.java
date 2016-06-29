public class CounterDemo {
 static int noSyncCount = 0;
 static int syncCount = 0;
 static int threadCount = 0;
 public static void main(String[] args) throws Exception {
  if (args.length < 1) {
    System.out.println("Usage: java CounterDemo <A_Number>");
    return;
  }
  
  threadCount = Integer.parseInt(args[0]);
  Thread[] workers = new Thread[threadCount];
  for (int i=0; i<threadCount; i++) {
    workers[i] = new Thread(new Worker(i));
  }

  for (int i=0; i<threadCount; i++) {
    workers[i].start();
  }
  

   while(true) {
     Thread.sleep(300);
     System.out.println("noSyncCount=" + noSyncCount + " syncCount: " + syncCount);
   }
 }

 static void inc() {
  noSyncCount++;
 }
 
 static synchronized void syncInc() {
   syncCount ++;
 }
}

class Worker implements Runnable {
  int num;
  static java.util.Random random = new java.util.Random(System.currentTimeMillis());
  public Worker(int num) {
    this.num = num;
  }
  public void run() {
    try {
      
      Thread.sleep(random.nextInt(50));
    } catch(Exception e) {}

    CounterDemo.inc();
    CounterDemo.syncInc();
  }
}
