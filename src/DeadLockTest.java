public class DeadLockTest {

    //There are two resources
    public final static String r1= "rrr1";
    public final static String r2 = "rrr2";

    public static void main(String[] args) {

        //Thread 1 is trying to lock r1 first
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

                    //then tries to lock r2
                    synchronized (r2){
                        System.out.println("trying r2 ");
                    }
                }
            }
        });

        //Thread 1 is trying to lock r2 first
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

                    //then r1
                    synchronized (r1){
                        System.out.println("trying r1 ");
                    }
                }
            }
        });

        //this results in a deadlock where both threads are waiting for each one to finish the task
        t1.start();
        t2.start();

    }

}
