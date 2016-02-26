package xyz.marten.jfuzzer;

/**
 * Created by marten on 2/26/16.
 */
public class Manager extends Thread {
    private static boolean running = true;

    private Queue queue = Queue.getInstance();

    public void run() {
        while (running) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[ ] Total size: " + queue.size() + ", Checked: " + queue.getPoppedCount() + ", Percentage done: " + queue.getPerc() + "%");
        }
    }
}
