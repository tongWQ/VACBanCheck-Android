package com.example.twq.bancheck;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnCheck;
    TextView textViewVACBannedValue;
    TextView textViewGameBannedValue;
    TextView textViewCommunityBannedValue;
    TextView textViewEconomyBannedValue;
    TextView textViewDaysSinceLastBanValue;
    EditText editTextInput;

    String sysMessage;
    Boolean flagAPIConn;

    String toCheckSteamID;
    BanCheck checked;
    private Handler handler=new Handler();
    // Main Thread Update UI
    class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            if (sysMessage.equals("player")){

            }
            else if (sysMessage.equals("ban")){
                Player result = (Player)msg.obj;
                textViewCommunityBannedValue.setText(Boolean.toString(result.CommunityBanned));
                textViewDaysSinceLastBanValue.setText(Integer.toString(result.DaysSinceLastBan));
                textViewEconomyBannedValue.setText(result.EconomyBan);
                textViewGameBannedValue.setText(Integer.toString(result.NumberOfGameBans));
                textViewVACBannedValue.setText(Integer.toString(result.NumberOfVACBans));

                if (result.VACBanned){
                    textViewVACBannedValue.setTextColor(getColor(R.color.red));
                }
                if (result.NumberOfGameBans>0){
                    textViewGameBannedValue.setTextColor(getColor(R.color.red));
                }
                if (result.CommunityBanned){
                    textViewCommunityBannedValue.setTextColor(getColor(R.color.red));
                }
                if (result.EconomyBan!="none"){
                    textViewCommunityBannedValue.setTextColor(getColor(R.color.red));
                }

                sysMessage="";

            }
        }
    }

    public class APIConnectionThread extends Thread{
        public void run(){
            while(flagAPIConn){
               checked = new BanCheck(toCheckSteamID);
               flagAPIConn=false;
               Message msg = handler.obtainMessage();
               msg.obj=checked.PlayerChecked;
               handler.sendMessage(msg);
               sysMessage="ban";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        btnCheck = findViewById(R.id.buttonCheck);
        textViewVACBannedValue = findViewById(R.id.textViewVacBannedValue);
        textViewGameBannedValue = findViewById(R.id.textViewGameBannedValue);
        textViewCommunityBannedValue = findViewById(R.id.textViewCommunityBannedValue);
        textViewEconomyBannedValue = findViewById(R.id.textViewEconomyBannedValue);
        textViewDaysSinceLastBanValue = findViewById(R.id.textViewDaysSinceLastBanValue);
        editTextInput = findViewById(R.id.editTextInput);
        handler=new myHandler();
    }

    public void buttonCheckClick(View v){

        String inputValue = editTextInput.getText().toString();
        if (inputValue!=""){
            toCheckSteamID=inputValue;
            flagAPIConn=true;
            Thread t = new APIConnectionThread();
            t.start();
        }else {
            flagAPIConn=false;
            return;
        }


        /*
        BanCheck bc = new BanCheck();
        APIConnection.GetHttpResponse("https://api.steampowered.com/ISteamUser/GetPlayerBans/v1/?key=DCCF6B72328C01D1EE708DA272F01327&steamids=76561198118220136");
*/
         /*
        try{
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
            mp.prepare();
            mp.start();
            mp.setLooping(true);
            Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(new long[]{1000,1000},0);
        }
        catch(Exception ex) {

        }

*/
    }
}
