package com.example.myweather.Util;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NetUtil {
   // https://www.tianqiapi.com/index/doc?version=day
    private static String BASE_URL = "https://tianqiapi.com/free/day";
    private static String APPID = "76623682";
    private static String APP_SECRET = "GAANVR3f";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String doGet(String url){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
           // StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

            //1.建立连接
            URL requestUrl = new URL(url);
           // URL requestUrl = new URL("https://www.baidu.com");
            //Log.e("TAG", "connect....0 ");
            urlConnection = (HttpURLConnection)requestUrl.openConnection();
            //Log.e("TAG", "connect....1 ");
            urlConnection.setRequestMethod("GET");
            //Log.e("TAG", "connect....2 ");
            urlConnection.setConnectTimeout(5000);
            //Log.e("TAG", "connect....3 ");
            urlConnection.setRequestProperty("Charset","utf-8");
            urlConnection.connect();

           // Log.e("TAG", "connect....4   "+ urlConnection.getResponseCode());
            //2.从连接里获取输入流（二进制）
            InputStream inputStream = urlConnection.getInputStream();

            //3.将输入流包转换成BufferReader
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            //4.从BufferReader中一行行读取字符串，用StringBuilder接收
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine())!= null){
               // Log.e("TAG", "Get DATA.... "+line);
                builder.append(line);
               // builder.append("\n");
            }
            if(builder.length() == 0){
                Log.e("TAG", "not Get DATA.... ");
                return null;
            }

            //5.StringBuilder拼接最终的字符串文本
            bookJSONString = builder.toString();

       }catch (Exception e){
            e.printStackTrace();
           // Log.e("TAG", "doGET:error ");
        }finally {
            //关闭连接
            if(urlConnection != null){
                urlConnection.disconnect();
               // Log.e("TAG", "doGet: disconnect ");
            }
        }
        //Log.e("TAG", "Get DATA.... "+bookJSONString);
          return bookJSONString;

    }

    public static boolean doPost(String urlStr){
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;
        boolean result = false;

        try {
            URL url = new URL(urlStr);
            //1.打开连接
            urlConnection = (HttpURLConnection) url.openConnection();

            //2.准备请求数据
            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("userName","fxj");
            paramMap.put("password","123");
            String paraData = paramMapToString(paramMap);

            //3.设置连接信息
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setRequestProperty("Content-Length",String.valueOf(paraData.length()));

            //设置conn可以向服务器输出内容
            urlConnection.setDoOutput(true);

            //4.获取输出流，并进行输出
            outputStream = urlConnection.getOutputStream();
            outputStream.write(paraData.getBytes());

            //5.获取服务器端的响应结果
            int code = urlConnection.getResponseCode();
            if(code == 200){
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }

            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static String paramMapToString(Map<String,String> paraMap){

        StringBuilder stringBuider = new StringBuilder();

        Set<Map.Entry<String,String>> entries = paraMap.entrySet();

        for (Map.Entry<String,String> entry:entries
             ) {
            stringBuider.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        //去掉最后一个&
        stringBuider.deleteCharAt(stringBuider.length() - 1);

        return stringBuider.toString();


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getWeatherOfCity(String city){
        //拼接出get请求的url
        String weatherUrl = BASE_URL+"?"+"appid="+APPID+"&"+"appsecret="+APP_SECRET+"&city="+city;
        Log.e("TAG", "weatherurl: "+weatherUrl);

        String weatherResult = doGet(weatherUrl);

        Log.e("TAG", "weatherResult: "+weatherResult);

        return decodeUnicode(weatherResult);

    }

    public static String decodeUnicode(String unicodeStr){

        if(unicodeStr == null){
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if(unicodeStr.charAt(i) == '\\'){
                if((i < maxLoop - 5) &&((unicodeStr.charAt(i+1) == 'u')||(unicodeStr.charAt(i+1) == 'U'))){
                   try {
                       retBuf.append((char)Integer.parseInt(unicodeStr.substring(i+2,i+6),16));
                       i += 5;

                   }catch (NumberFormatException localNumberFormatException){
                       retBuf.append(unicodeStr.charAt(i));
                   }

                }else {
                    retBuf.append(unicodeStr.charAt(i));
                }
            }else{
                retBuf.append(unicodeStr.charAt(i));
            }

        }

        return retBuf.toString();


    }

}
