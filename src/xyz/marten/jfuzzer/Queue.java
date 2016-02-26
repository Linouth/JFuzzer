package xyz.marten.jfuzzer;

import xyz.marten.jfuzzer.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marten on 2/26/16.
 */
public class Queue {
    public static Queue queue = new Queue();
    public static Queue getInstance() {
        return queue;
    }


    private List<String> dirs = new ArrayList<>();
    private int popped = 0;
    private int size = 0;

    public String pop() {
        if (dirs.size() > 0) {
            String out = dirs.get(0);
            dirs.remove(out);
            popped++;
            return out;
        }
        return null;
    }

    public void add(String dir) {
        dirs.add(dir);
        size++;
    }
    public void add(List<String> dirs) {
        this.dirs.addAll(dirs);
        size += dirs.size();
    }
    public void load(String filename) {
        try {
            List<String> dirs = FileUtils.fileToList(filename);
            this.dirs.addAll(dirs);
            size += dirs.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPoppedCount() {
        return popped;
    }
    public int size() {
        return size;
    }
    public double getPerc() {
        double avr = (double)Math.round(((double) popped/size)*100*100) / 100; // Calculate average and round at two decimal places
        return avr;
    }
}
