package xyz.marten.jfuzzer;

/**
 * Created by marten on 2/26/16.
 */
public class Manager extends Thread {
    private static boolean running = true;

    private Queue queue = Queue.getInstance();

    public void run() {
        long beginTime = System.currentTimeMillis();
        long now, elapsed, avr;
        int popped;

        while (running) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            now = System.currentTimeMillis();
            elapsed = now - beginTime; // Elapsed time in Milliseconds
            popped = queue.getPoppedCount();
            avr = popped / (elapsed/1000);

            System.out.println("[ ] Checked: " + popped + "/" + queue.size()
                    + ", Percentage done: " + queue.getPerc()
                    + "%, Running " + avr + " requests per second"
                    + ", Time remaining: " + (queue.size()-popped)/avr + " seconds"
            );
        }

        System.out.println("[ ] Waiting for threads to stop.");
    }

    public static void toggleRunning() {
        running = !running;
    }
}
