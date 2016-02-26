package xyz.marten.jfuzzer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Queue q = Queue.getInstance();
        q.load("small.txt");

//        Fuzzer f = new Fuzzer("http://marten.xyz/");
//        f.start();

        List<Fuzzer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Fuzzer("http://marten.xyz/"));
            list.get(i).start();
        }
    }
}
