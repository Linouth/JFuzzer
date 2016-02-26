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

    public String pop() {
        if (dirs.size() > 0) {
            String out = dirs.get(0);
            dirs.remove(out);
            return out;
        }
        return null;
    }

    public void add(String dir) {
        dirs.add(dir);
    }
    public void add(List<String> dirs) {
        this.dirs.addAll(dirs);
    }
    public void load(String filename) {
        try {
            dirs.addAll(FileUtils.fileToList(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
