package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * m.poizon.com Inc.
 * Copyright (c) 1999-2020 All Rights Reserved.
 *
 * @author panes
 * @contact pantao@theduapp.com
 */
public class Network {
    public static String request(String url) {
        try {
            URL mUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();

            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line);
                result.append("\n");
            }
            connection.disconnect();
            if (result.toString().length() == 0) {
                LogUtil.log("request " + url + " failed. response length = 0");
                return null;
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.log(e.getLocalizedMessage());
        }
        return null;
    }

    public static boolean download(String downloadUrl, String path) {
        try {
            LogUtil.log("start download " + downloadUrl);
            long start = System.currentTimeMillis();
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(downloadUrl).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            LogUtil.log("download finish. cost " + (System.currentTimeMillis() - start) + " ms");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
