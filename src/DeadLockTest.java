public class DeadLockTest {
    public final static String r1= "rrr1";
    public final static String r2 = "rrr2";

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (r1){
                    System.out.println(r1+" is locked");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (r2){
                        System.out.println("trying r2 ");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (r2){
                    System.out.println(r2+" is locked");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (r1){
                        System.out.println("trying r1 ");
                    }
                }
            }
        });

        t1.start();
        t2.start();

    }

}
