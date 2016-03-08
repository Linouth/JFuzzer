package xyz.marten.jfuzzer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Queue q = Queue.getInstance();
        q.load("small.txt");

        String [] extensions = {"php", "html"};
//        q.addExtensions(extensions); // Adding extensions AFTER adding all items to the queue.

//        q.continueAt(131382);


        Manager m = new Manager();
        m.start();

        List<Fuzzer> threads = new ArrayList<>();
        Fuzzer f = null;
        for (int i = 0; i < 25; i++) {
            f = new Fuzzer("http://marten.xyz/");
//            f.setErrorKeyword("hostinger");

            threads.add(f);
            threads.get(i).start();
        }
    }
}
