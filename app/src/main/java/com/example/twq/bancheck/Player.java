package com.example.twq.bancheck;

public class Player {
    public String SteamId;
    public boolean VACBanned;
    public boolean CommunityBanned;
    public int NumberOfVACBans;
    public  int DaysSinceLastBan;
    public int NumberOfGameBans;
    public String EconomyBan;
    private static final String playerSummriesURL  = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/";

}
