package com.vane.t05_test.utils.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by LG on 2016/10/15.
 * Tips:
 */

public class HttpUtils {
    private ExecutorService executors;
    private int maxThreadCount = 5;
    private int connectTimeout = 6000;
    private int readTimeout = 6000;
    private Handler hanler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            ResponseCallback responseCallback = (ResponseCallback) msg.obj;
            switch (msg.what){
                case 1:
                    responseCallback.Callback.onResponse(responseCallback.getResult());
                    break;
                case 2:
                    responseCallback.Callback.onError();
                    break;
            }
        }
    };
    private HttpUtils(){
        executors = Executors.newFixedThreadPool(maxThreadCount);
    }
    private static HttpUtils instance;
    public static HttpUtils getInstance(){
        if(instance == null){
            synchronized (HttpUtils.class){
                if(instance == null){
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }
    public void execute(Request request,Callback callback){
        HttpRunnable runnable = new HttpRunnable(request, callback);
        executors.execute(runnable);
    }

    public interface Callback{
        void onResponse(String response);
        void onError();
    }

    public static class ResponseCallback {
        private Object tag;
        private Callback Callback;
        private String result;
        public Object getTag() {
            return tag;
        }

        public Callback getCallback() {
            return Callback;
        }

        public String getResult() {
            return result;
        }

        public ResponseCallback(Object tag, Callback callback, String result) {
            this.tag = tag;
            this.Callback = callback;
            this.result = result;
        }
    }

    private class HttpRunnable<T> implements Runnable{
        private Callback Callback;
        private Request request;
        public HttpRunnable(Request request,Callback callback) {
            this.Callback = callback;
            this.request = request;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(request.getUrl());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod(request.getMethod());//设置访问方式
                //为HttpURLConnection添加头部数据
                Headers headers= request.getHeaders();
                if(headers != null){
                    List<String> headerDatas = headers.getNamesAndValues();
                    for (int i = 0; i < headerDatas.size(); i+=2) {
                        conn.addRequestProperty(headerDatas.get(i),headerDatas.get(i+1));
                    }
                }


                if("POST".equals(request.getMethod()) && request.getBody() != null){
                    conn.setDoOutput(true);
                    //设置请求主体
                    OutputStream os = conn.getOutputStream();
                    os.write(request.getBody().getBodyBytes());
                }

                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line ;
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                String result = sb.toString();
                Log.d("HttpUtils", result);
                if(Callback == null) return;
                ResponseCallback responseBallBack = new ResponseCallback(request.getUrl(), Callback, result);
                hanler.sendMessage(hanler.obtainMessage(1,responseBallBack));
                return;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(Callback == null) return;
            ResponseCallback responseBallBack = new ResponseCallback(request.getUrl(), Callback,null);
            hanler.sendMessage(hanler.obtainMessage(2,responseBallBack));
        }
    }
}
