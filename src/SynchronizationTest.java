public class SynchronizationTest {

    public static void main(String[] args) throws InterruptedException {

        Resource r = new Resource();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++){
                    r.increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++){
                    r.increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        r.print();


    }
}

class Resource{
    private Integer count =0;

    public void increment(){

        synchronized (count){
            this.count++;
        }
    }

    public void print(){
        System.out.println(this.count);
    }
}
