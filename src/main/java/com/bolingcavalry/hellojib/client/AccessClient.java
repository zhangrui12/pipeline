package com.bolingcavalry.hellojib.client;

import com.bolingcavalry.hellojib.constant.Constant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author liaohua1
 * @date 2020/5/6 15:58
 */
@Component
public class AccessClient {

    /*@Resource(name = "callBackThreadPool")
    private ThreadPoolExecutor pool;*/

    public ExecutorService callBackThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        ExecutorService threadPool = new ThreadPoolExecutor(
                10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy()
        );
        return threadPool;
    }

    public void access() throws Exception {
        ExecutorService pool = callBackThreadPool();
        final URL url = new URL(Constant.URL);
        for (int i = 0; i < 10; i++) {
            pool.submit(() -> System.out.println(sendGet(url)));
        }

        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

    }

    private String sendGet(URL url) {
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;
        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws Exception{
        AccessClient accessClient = new AccessClient();
        accessClient.access();
    }
}
