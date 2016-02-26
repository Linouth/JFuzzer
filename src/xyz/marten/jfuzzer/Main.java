package xyz.marten.jfuzzer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Queue q = Queue.getInstance();
        q.load("small.txt");

        Manager m = new Manager();
        m.start();

        List<Fuzzer> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            list.add(new Fuzzer("https://marten.xyz/"));
            list.get(i).start();
        }
    }
}
