package com.example.twq.bancheck;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIConnection {

    public static String SteamUserAPIKey="DCCF6B72328C01D1EE708DA272F01327";
    public  static String GetHttpResponse(String url){
        String result="";
        try{
            URL connURL = new URL(url);
            URLConnection conn = connURL.openConnection();
            conn.setRequestProperty("Method","GET");

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line=in.readLine())!=null){
                sb.append(line);
            }
            result = sb.toString();
        }
        catch (Exception e){
            result=e.getMessage();
        }

        return result;
    }

    public static String GetIDFromPersonalURL(String url){
        String idResult="";
        if (url.contains("profiles")){
            String[] spilted = url.split("\\/");
            idResult=spilted[spilted.length-1];
        }
        return idResult;
    }

}
