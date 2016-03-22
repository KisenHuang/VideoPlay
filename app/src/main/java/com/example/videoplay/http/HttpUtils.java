package com.example.videoplay.http;

import com.example.videoplay.interfs.ICallBack;
import com.example.videoplay.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Huangwy on 2015/11/27.
 */
public class HttpUtils {
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void getHtmlForNetByConnectionInPool(final String urlString, final ICallBack iCallBack) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                iCallBack.send(getHtmlForNetByConnection(urlString));
            }
        });
    }

    public static String getHtmlForNetByConnection(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[1024 * 8];
                int len = 0;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                    baos.flush();
                }
                String html = new String(baos.toByteArray(),"gbk");

                return html;
            } else {
                LogUtil.i("test", "连接网络失败");
            }
        } catch (MalformedURLException e) {
//            e.printStackTrace();
            LogUtil.i("test", "MalformedURLException----->" + e.toString());
        } catch (IOException e) {
//            e.printStackTrace();
            LogUtil.i("test", "IOException----->" + e.toString());
        }
        return null;
    }

    public static void post(){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("name","bug").build();
        Request request = new Request.Builder().url("").post(body).build();
    }

}
