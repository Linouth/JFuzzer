package xyz.marten.jfuzzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.URL;

/**
 * Created by marten on 2/26/16.
 */
public class Fuzzer extends Thread {
    private Queue queue;
    private String url;

    private String errorKeyword = null;
    private int timeout = 0;

    public Fuzzer(String url) {
        this.queue = Queue.getInstance();
        this.url = url;
    }

    public void run() {
        URL u = null;
        HttpURLConnection conn;

        int code = 0;
        String outUrl = null;

        String dir;
        while ((dir = queue.pop()) != null) {
            try {
                u = new URL(addDirToUrl(url, dir));
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36");
                conn.connect();

                code = conn.getResponseCode();
                outUrl = conn.getURL().toString();

                conn.disconnect();
            } catch (NoRouteToHostException e) {
                System.out.println("[!] System is out of TCP sockets, slowing down threads. \n" +
                                   "[!] Prevent this either enable TCP reuse and recycle or set a higher timeout.");
                this.timeout += 50;
            } catch (IOException e) {
                Manager.toggleRunning();
                e.printStackTrace();
            }

            if (code == 200) {
                boolean ret = true;
                if (errorKeyword != null && outUrl.contains(errorKeyword)) {
                    ret = false;
                }

                if (ret) System.out.println("[+] " + u.toString());
            }

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String addDirToUrl(String url, String dir) {
        if (url.substring(url.length() -1).equals("/")) {
            return url + dir;
        }
        return url + "/" + dir;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public void setErrorKeyword(String keyword) {
        this.errorKeyword = keyword;
    }
}
