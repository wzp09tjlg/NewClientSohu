package com.sina.home.testfor_newclient.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Walter on 2016/2/15.
 * 常用的两种访问网络的方式 post 和  get
 */
public class HttpUtil {
    private static final String TAG = "HttpUtil";
    private static final int TIMEOUT_IN_MILLIONS = 5000;

    private HttpUtil(){
        throw new UnsupportedOperationException("");
    }

    //异步get请求
    public static void doGetAsyn(final String urlStr, final CallBack callBack)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Bitmap result = doGet(urlStr);
                    LogLog.e("is bitmap is null:" + (null == result) );
                    if (callBack != null)
                    {
                        LogLog.e("callback is not null, and there is will call the call back");
                        callBack.onRequestComplete(result);
                        LogLog.e("after call back mthod and will apprear some imagic thing");
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            };
        }.start();
    }

    public static void doPostAsyn(final String urlStr, final String params,
                                  final CallBack callBack) throws Exception
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Bitmap result = doPost(urlStr, params);
                    if (callBack != null)
                    {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            };
        }.start();
    }

    //get请求进行数据访问
    public static Bitmap doGet(String urlStr)
    {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try
        {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            LogLog.e("-----------------0");
            if (conn.getResponseCode() == 200)
            {
                LogLog.e("-----------------1");
//                is = conn.getInputStream();
//                baos = new ByteArrayOutputStream();
//                int len = -1;
//                byte[] buf = new byte[128];
//                LogLog.e("-----------------2");
//                while ((len = is.read(buf)) != -1)
//                {
//                    baos.write(buf, 0, len);
//                }
//                baos.flush();
                LogLog.e("-----------------3");
                if(conn.getInputStream() != null)
                    LogLog.e("-----------------4 not null");
                return BitmapFactory.decodeStream(conn.getInputStream());
            } else
            {
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
            }
            try
            {
                if (baos != null)
                    baos.close();
            } catch (IOException e)
            {
            }
            conn.disconnect();
        }
        return null ;
    }

    //post请求进行数据访问
    public static Bitmap doPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        Bitmap result = null;
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
//            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

//            if (param != null && !param.trim().equals(""))
//            {
//                // 获取URLConnection对象对应的输出流
//                out = new PrintWriter(conn.getOutputStream());
//                // 发送请求参数
//                out.print(param);
//                // flush输出流的缓冲
//                out.flush();
//            }
            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null)
//            {
//                result += line;
//            }
            LogLog.e("-------------------1");

            if (conn.getResponseCode() == 200)
            result = BitmapFactory.decodeStream(conn.getInputStream());
            LogLog.e("-------------------2  result:" + (null == result));
        } catch (Exception e)
        {
            e.printStackTrace();
            LogLog.e(e.getMessage());
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public interface CallBack
    {
        void onRequestComplete(Bitmap result);
    }
}
