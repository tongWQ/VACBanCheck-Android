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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BanCheck {
    private String PlayerPersonalURL;
    public Player PlayerChecked;
    //private  int playerCount;
    private String ResponseJsonFile;

    private  static final String GetPlayerBansAPIURL="https://api.steampowered.com/ISteamUser/GetPlayerBans/v1/";
    public BanCheck(){

    }
/*
    public BanCheck(String url){
        PlayerPersonalURL = url;
        String apiRequestURL = "";
        //String jsonFile;

    }
    */
    public BanCheck(String steamid){
        String requestURL=GetPlayerBansAPIURL+"?key="+APIConnection.SteamUserAPIKey+"&steamids="+steamid+",";
        ResponseJsonFile = APIConnection.GetHttpResponse(requestURL);
        SetResultToPlayer(ResponseJsonFile);
    }

    public Player SetResultToPlayer(String responsed){
        PlayerChecked = new Player();

        JSONObject jObj = JSONObject.parseObject(responsed);
        JSONArray array = jObj.getJSONArray("players");
        for (int i=0;i<array.size();i++){
            JSONObject jo = array.getJSONObject(i);
            PlayerChecked.SteamId = jo.getString("SteamId");
            PlayerChecked.CommunityBanned =jo.getBoolean("CommunityBanned");
            PlayerChecked.VACBanned=jo.getBoolean("VACBanned");
            PlayerChecked.NumberOfVACBans=jo.getInteger("NumberOfVACBans");
            PlayerChecked.NumberOfGameBans =jo.getInteger("NumberOfGameBans");
            PlayerChecked.DaysSinceLastBan =jo.getInteger("DaysSinceLastBan");
            PlayerChecked.EconomyBan =jo.getString("EconomyBan");
        }
/*
        PlayerChecked.SteamId = jObj.getJSONObject("players").getString("SteamId");
        PlayerChecked.CommunityBanned = Boolean.getBoolean(jObj.getJSONObject("players").getString("CommunityBanned"));
        PlayerChecked.VACBanned=Boolean.getBoolean(jObj.getJSONObject("players").getString("VACBanned"));
        PlayerChecked.NumberOfVACBans=Integer.getInteger(jObj.getJSONObject("players").getString("NumberOfVACBans"));
        PlayerChecked.NumberOfGameBans = Integer.getInteger(jObj.getJSONObject("players").getString("NumberOfVACBans"));
        PlayerChecked.DaysSinceLastBan = Integer.getInteger(jObj.getJSONObject("players").getString("DaysSinceLastBan"));
        PlayerChecked.NumberOfGameBans = Integer.getInteger(jObj.getJSONObject("players").getString("NumberOfGameBans"));
        PlayerChecked.EconomyBan = jObj.getJSONObject("players").getString("EconomyBan");
*/

        return PlayerChecked;
    }
}
