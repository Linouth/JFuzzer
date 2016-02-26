package xyz.marten.jfuzzer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marten on 2/26/16.
 */
public class Fuzzer extends Thread {
    private Queue queue;
    private String url;

    public Fuzzer(String url) {
        this.queue = Queue.getInstance();
        this.url = url;
    }

    public void run() {
        URL u = null;
        HttpURLConnection conn;
        int code = 0;

        String dir = null;
        while ((dir = queue.pop()) != null) {
            try {
                u = new URL(addDirToUrl(url, dir));
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                code = conn.getResponseCode();

                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (code == 200) {
                System.out.println("[+] " + u.toString());
            }
        }
    }

    private String addDirToUrl(String url, String dir) {
        if (url.substring(url.length() -1).equals("/")) {
            return url + dir;
        }
        return url + "/" + dir;
    }
}
