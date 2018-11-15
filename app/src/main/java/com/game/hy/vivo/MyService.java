package com.game.hy.vivo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class MyService extends Service {
    Boolean notificationstatus=true;
    int pr0,pr1,pr2,pr3,pr4,pr5,cnt,spd,spd1;
    String CharacterName="a";
    int countcounter = 0;
    final int[] process= {0,0,0,0,0,0,0};
    final int[] gamestarcounter = {0};
    final int[] gamestar = {0};
    int[] mProgressStatus={0};
    int speedcounter = 1800;
    int iq = 0;
    final int gamewaittime = 1800;
    Boolean badgecount = false;
    Boolean vibration=true;
    Boolean mutestate=true;
    int newgame= 0;
    Context con = this;
    Boolean loyalty=false;
    int originalcounter=0;
    int BELOW50TIME = 180;
    int ABOVE50TIME = 120;
    Boolean fatal = false;
    Boolean energy = false;
    int delaycounter =0;
    Boolean gamestop=false;
    int originalcounterhunger= 0;
    int originalcounterhygiene= 0;
    int originalcounterwc= 0;
    int originalcountersleep= 0;
    int originalcounterlove= 0;
    Boolean energy1 = true;
    Boolean energy2 = true;
    int color = Color.argb(255,190,24,38);
    int MAXGAMEPLAY=3;

    int BELOW50HUNGER = 240;
    int ABOVE50HUNGER = (int)((double)240 * (double)1.1);
    int ABOVE100HUNGER = (int)((double)240 * (double)1.2);


    int BELOW50HYGIENE = 300;
    int ABOVE50HYGIENE = (int)((double)300 * (double)1.1);
    int ABOVE100HYGIENE = (int)((double)300 * (double)1.2);



    int BELOW50WC = 360;
    int ABOVE50WC = (int)((double)360 * (double)1.1);
    int ABOVE100WC = (int)((double)360 * (double)1.2);

    int BELOW50SLEEP = 330;
    int ABOVE50SLEEP = (int)((double)330 * (double)1.1);
    int ABOVE100SLEEP = (int)((double)330 * (double)1.2);

    int BELOW50NONSLEEP = 60;
    int ABOVE50NONSLEEP = (int)((double)60 * (double)1.1);
    int ABOVE100NONSLEEP = (int)((double)60 * (double)1.2);

    int BELOW50LOVE = 216;
    int ABOVE50SLOVE = (int)((double)216 * (double)1.1);
    int ABOVE100LOVE = (int)((double)216 * (double)1.2);
    int NONE= 100;
    int ENGLISH = 0;
    int TURKISH =1 ;
    int FRENCH =2;
    int SPANISH =3;
    int RUSSIAN =4;
    int CHINESE =5 ;
    int JAPANESE = 6;
    int PORTUGUESE =7;
    int gamelang = NONE;
    int hungerspeed = 1800;
    int hygienespeed = 1800;
    int wcspeed = 1800;
    int sleepspeed = 1800;
    int lovespeed = 1800;
    String notifheader1 = "";
    String notifheader2 = "";
    String notifbody1 = "";
    String notifbody12 = "";
    String notifbody2 = "";
    private static final String TAG1 = "badge";
    //long time,time1;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
      //  Toast.makeText(this, " IT Created ", Toast.LENGTH_LONG).show();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SharedPreferences pref;
         pref = getSharedPreferences("info", MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();
      //  Toast.makeText(this, " IT Started", Toast.LENGTH_LONG).show();
       final Calendar cl = Calendar.getInstance();


      /*  SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        //Using getXXX- with XX is type date you wrote to file "name_file"
        int x0 = shared.getInt("procss0key", 101);
        int x1 = shared.getInt("procss1key", 101);
        int x2 = shared.getInt("procss2key", 101);
        int x3 = shared.getInt("procss3key", 101);
        int x4 = shared.getInt("procss4key", 101);
        int x5 = shared.getInt("procss5key", 101);
        originalcounter=shared.getInt("originalcounter", originalcounter);
         notificationstatus = shared.getBoolean("notification", true);
        final  int gamestar1 = shared.getInt("gamestar", 0);
        vibration = shared.getBoolean("vibration", true);
        mutestate = shared.getBoolean("mutestate", mutestate);

//Using getXXX- with XX is type date you wrote to file "name_file"
        MAXGAMEPLAY = shared.getInt("MAXGAMEPLAY", MAXGAMEPLAY);
        CharacterName = shared.getString("charactername", "");
        //final int gamestarcounter1 = shared.getInt("gamestarcounter", 0);
        gamestarcounter[0]=shared.getInt("gamestarcounter", 0);
        badgecount=shared.getBoolean("badgecount", badgecount);
        loyalty = shared.getBoolean("loyaltycount", loyalty);
        newgame=shared.getInt("newgame", newgame);



        gamestar[0]=gamestar1;


        final boolean sleep = shared.getBoolean("sleep", true);


        final long time = shared.getLong("time", 0);
        final long lastplaytime = shared.getLong("lastplaytime",0);
        final long[] time1={10};


        process[0]= x0;
        process[1]= x1;
        process[2]= x2;
        process[3]= x3;
        process[4]= x4;
        process[5]= x5;


        int counterindex =  shared.getInt("counter", 1);
        final int[] counter = {counterindex};
        counter[0]=counterindex;
        final int speed = shared.getInt("speed1", 20);
        final int speed1 = shared.getInt("speed2", 20);
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object


        final long time2[]={100000};

        final int[] servicecounter = {0};
        //
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object

        time2[0]=time;

        time1[0] = System.currentTimeMillis();

        time1[0] = time1[0] / 1000;

        long [] diff={100000};

        diff[0] = time1[0] - time;




        if(mProgressStatus[0]> 50) hygienespeed = ABOVE50TIME;
        else hygienespeed = BELOW50TIME;

        originalcounter=originalcounter+(int)diff[0];

        double div = (double) diff[0] /hygienespeed;

        int div1 = (int) div;
       /* process[0]= process[0]-div1;
        process[1]= process[1]-div1;
        process[2]= process[2]-div1;
        if (sleep==true ) process[3] = process[3] + div1;
        else process[3] = process[3] - div1;
        process[4]= process[4]-div1;
        process[5]= process[5]-div1;*/
      /*  checkoverall();
        double gamdiv = (double) diff[0] / (double)gamewaittime;
        int div2= (int) gamdiv;

        gamestarcounter[0]= gamestarcounter[0]+ (int) diff[0];

        if((gamestar[0]+div2) <=MAXGAMEPLAY)
            gamestar[0] = gamestar[0]+div2;
        else gamestar[0]=MAXGAMEPLAY;

        int gmstar =  gamestar[0];
       // editor.putInt("gamestar", gmstar);
       // editor.commit();

        servicecounter[0]= originalcounter;*/


   /*     final Timer timer = new Timer();
        final TimerTask timerTask;
        timerTask = new TimerTask() {
            @Override
            public void run() {

                counter[0]++;
                cnt = counter[0];


                servicecounter[0]++;

            //         Toast.makeText(con,Integer.toString( servicecounter[0]), Toast.LENGTH_LONG).show();


                double service = servicecounter[0];


                editor.putInt("originalcounter", (int) service);
                editor.commit();


*/



               /* if (servicecounter[0] % speedcounter == 0) {


                    process[0] = process[0] - 1;
                    process[1] = process[1] - 1;
                    process[2] = process[2] - 1;

                    if (sleep == true) process[3] = process[3] + 1;
                    else process[3] = process[3] - 1;
                    process[4] = process[4] - 1;
                    process[5] = process[5] - 1;
                    // process[1]= (int) (process[1]-decrementtime);
                    checkoverall();

                    //process[0]--;
                    pr0 = process[0];
                    spd = speed;
                    pr1 = process[1];
                    pr2 = process[2];
                    pr3 = process[3];
                    pr4 = process[4];
                    pr5 = process[5];

                    double service2 = (double) servicecounter[0] / (double) speedcounter;
                    servicecounter[0] = (int) service2;

                    editor.putInt("originalcounter", (int) service);
                    editor.commit();

                    editor.putInt("procss0key", pr0);
                    editor.putInt("procss1key", pr1);
                    editor.putInt("procss2key", pr2);
                    editor.putInt("procss3key", pr3);
                    editor.putInt("procss4key", pr4);
                    editor.putInt("procss5key", pr5);

                    editor.putInt("speed1", spd);
                    editor.putInt("speed2", spd1);
                    editor.putInt("counter", cnt);

                    long time1 = System.currentTimeMillis();
                    time1 = time1 / 1000;
                    editor.putLong("time", time1);


                    editor.commit();


                    //refresh your textview
                }
            }

            ;
        }; timer.schedule(timerTask, 1000, 1000);

*/

 /*       final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            private long time = 0;

            @Override
            public void run() {
                // do stuff then
                // can call h again after work!


                SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                //Using getXXX- with XX is type date you wrote to file "name_file"
                int x0 = shared.getInt("procss0key", 101);
                int x1 = shared.getInt("procss1key", 101);
                int x2 = shared.getInt("procss2key", 101);
                int x3 = shared.getInt("procss3key", 101);
                int x4 = shared.getInt("procss4key", 101);
                int x5 = shared.getInt("procss5key", 101);

                process[0] = x0;
                process[1] = x1;
                process[2] = x2;
                process[3] = x3;
                process[4] = x4;
                process[5] = x5;

                checkoverall();
                originalcounter = shared.getInt("originalcounter", originalcounter);
                notificationstatus = shared.getBoolean("notification", true);

                vibration = shared.getBoolean("vibration", true);
                mutestate = shared.getBoolean("mutestate", mutestate);

//Using getXXX- with XX is type date you wrote to file "name_file"
                MAXGAMEPLAY = shared.getInt("MAXGAMEPLAY", MAXGAMEPLAY);
                CharacterName = shared.getString("charactername", "");
                //final int gamestarcounter1 = shared.getInt("gamestarcounter", 0);

                badgecount = shared.getBoolean("badgecount", badgecount);
                loyalty = shared.getBoolean("loyaltycount", loyalty);
                gamestop = shared.getBoolean("gamestop", gamestop);
                newgame = shared.getInt("newgame", newgame);




                final boolean sleep = shared.getBoolean("sleep", true);


                final long time = shared.getLong("time", 0);
                final long lastplaytime = shared.getLong("lastplaytime", 0);
                final long[] time1 = {10};

                if (mProgressStatus[0] > 50) speedcounter = ABOVE50TIME;
                else speedcounter = BELOW50TIME;


                Long timenow = System.currentTimeMillis() / 1000;

                final long utcOffset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
                final long DAY_IN_MILLIS = 24 * 60 * 60;
                final long DAY_IN_MILLIS2 = 24 * 60 * 60;
                final long DAY_IN_MILLIS3 = 13 * 60 * 60;
                final long DAY_IN_MILLIS4 = 20 * 60 * 60;


                long seconds = System.currentTimeMillis() / 1000 + utcOffset / 1000;
                long seconds2 = System.currentTimeMillis() / 1000 + utcOffset / 1000;
                long seconds4 = System.currentTimeMillis() / 1000 + utcOffset / 1000;
                //seconds[0] = System.currentTimeMillis()/1000;

                seconds = seconds % DAY_IN_MILLIS;
                seconds = seconds % DAY_IN_MILLIS2;
                seconds2 = seconds % DAY_IN_MILLIS3;
                seconds4 = seconds % DAY_IN_MILLIS4;


                if (timenow - lastplaytime > 60 * 60 * 6) fatal = true;

                if (seconds == 0) {

                    editor.putBoolean("isplustencoin", true);
                    editor.commit();
                }

                if (gamestop) {
                    gamestarcounter[0] = shared.getInt("gamestarcounter", 0);
                    final int gamestar1 = shared.getInt("gamestar", 0);
                    gamestarcounter[0]++;

                if (gamestarcounter[0] % gamewaittime == 0)

                {
                    if (gamestar[0] < MAXGAMEPLAY)
                        gamestar[0]++;
                    else gamestar[0] = MAXGAMEPLAY;

                    int gmstar = gamestar[0];
                    editor.putInt("gamestar", gmstar);
                    gamestar[0] = gamestar1;
                    editor.commit();

                }

                int gmscounter = gamestarcounter[0];
                editor.putInt("gamestarcounter", gmscounter);
                editor.commit();

            }

                if (((fatal && mProgressStatus[0] <=10 && (seconds2 == 0 || seconds4 == 0) && newgame == 1) && notificationstatus == true)) {
                    if (vibration == true && mutestate == false) showNotificationfatalwarning();
                    else if (vibration == false && mutestate == false)
                        showNotificationfatalwarningnnovibs();
                    else if (vibration == false && mutestate == true)
                        showNotificationfatalwarningnosoundnovibs();
                    else if (vibration == true && mutestate == true)
                        showNotificationfatalwarningnosound();

                }

               if(gamestop) delaycounter++;

                if(delaycounter % speedcounter==0 && gamestop) {



                    process[0] = process[0] - 1;
                    process[1] = process[1] - 1;
                    process[2] = process[2] - 1;

                    if (sleep == true) process[3] = process[3] + 1;
                    else process[3] = process[3] - 1;
                    process[4] = process[4] - 1;
                    process[5] = process[5] - 1;
                    // process[1]= (int) (process[1]-decrementtime);
                    checkoverall();
                    delaycounter=0;
                    //process[0]--;
                    pr0 = process[0];

                    pr1 = process[1];
                    pr2 = process[2];
                    pr3 = process[3];
                    pr4 = process[4];
                    pr5 = process[5];


                    editor.putInt("procss0key", pr0);
                    editor.putInt("procss1key", pr1);
                    editor.putInt("procss2key", pr2);
                    editor.putInt("procss3key", pr3);
                    editor.putInt("procss4key", pr4);
                    editor.putInt("procss5key", pr5);

                    editor.commit();
                }


                Log.d("TimerExample", "Going ... " + delaycounter);
                Log.d("TimerExample", "Going for... " + process[1]);
                //h.postDelayed(this, 1000);
            }
        }, 2000);*/



        new CountDownTimer(1000000000, 1000) {
            public void onTick(long milsec) {

                if (countcounter < 1)
                {



                }

                countcounter++;
                SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                //Using getXXX- with XX is type date you wrote to file "name_file"
            /*    int x0 = shared.getInt("procss0key", 101);
                int x1 = shared.getInt("procss1key", 101);
                int x2 = shared.getInt("procss2key", 101);
                int x3 = shared.getInt("procss3key", 101);
                int x4 = shared.getInt("procss4key", 101);
                int x5 = shared.getInt("procss5key", 101);

                process[0] = x0;
                process[1] = x1;
                process[2] = x2;
                process[3] = x3;
                process[4] = x4;
                process[5] = x5;*/

                //checkoverall();
                originalcounter = shared.getInt("originalcounter", originalcounter);
                originalcounterhunger=shared.getInt("originalcounterhunger", originalcounterhunger);
                originalcounterhygiene=shared.getInt("originalcounterhygiene", originalcounterhygiene);
                originalcounterwc=shared.getInt("originalcounterwc", originalcounterwc);
                originalcountersleep=shared.getInt("originalcountersleep", originalcountersleep);
                originalcounterlove=shared.getInt("originalcounterlove", originalcounterlove);



                notificationstatus = shared.getBoolean("notification", true);

                vibration = shared.getBoolean("vibration", true);
                mutestate = shared.getBoolean("mutestate", mutestate);

//Using getXXX- with XX is type date you wrote to file "name_file"
                MAXGAMEPLAY = shared.getInt("MAXGAMEPLAY", MAXGAMEPLAY);
                CharacterName = shared.getString("charactername", "");
                //final int gamestarcounter1 = shared.getInt("gamestarcounter", 0);

                badgecount = shared.getBoolean("badgecount", badgecount);
                loyalty = shared.getBoolean("loyaltycount", loyalty);
                gamestop = shared.getBoolean("gamestop", gamestop);
                newgame = shared.getInt("newgame", newgame);


                gamelang = shared.getInt("gamelang", NONE);
                if (gamelang == ENGLISH)
                {
                    notifheader1 = "Energy Warning";
                    notifheader2 = "Fatal Warning";
                    notifbody1 = CharacterName + "'s energy is about to run out.";
                    notifbody12 = "Feed and love your vivo please." ;
                    notifbody2 = "You think I'm a game, but my heart is breaking";
                }


                else if (gamelang == TURKISH)   {
                    notifheader1 = "Enerji Uyarısı";
                    notifheader2 = "Ölümcül Uyarı";
                    notifbody1 = CharacterName + "'nin enerjisi tükenmek üzere";
                    notifbody12 = "Vivo'nu besle ve sev lütfen." ;
                    notifbody2 = "Sen benim bir oyun olduğumu düşünüyorsun, ama kalbim kırılıyor";
                }
                else if (gamelang == FRENCH)   {
                    notifheader1 = "Avertissement énergétique";
                    notifheader2 = "Avertissement fatal";
                    notifbody1 = "L'énergie d'" + CharacterName + " est sur le point de s'épuiser";
                    notifbody12 = "Nourrissez et aimez votre Vivo s'il vous plaît.";
                    notifbody2 = "Vous pensez que je suis un jeu, mais mon coeur se brise";
                }
                else if (gamelang == SPANISH)  {
                    notifheader1 = "Advertencia de energía";
                    notifheader2 = "Advertencia Fatal";
                    notifbody1 = "La energía de"+ CharacterName + " está a punto de agotarse";
                    notifbody12 = "Alimente y ame su Vivo por favor." ;
                    notifbody2 = "Crees que soy un juego, pero mi corazón se está rompiendo";
                }
                else if (gamelang == RUSSIAN)  {
                    notifheader1 = "Энергия Предупреждение";
                    notifheader2 = "Фатальная Предупреждение";
                    notifbody1 = "энергия" + CharacterName + "составляет около выбежать";
                    notifbody12 = "Корма и любите свою Vivo пожалуйста." ;
                    notifbody2 = "Вы думаете, я игру, но мое сердце разрывается";
                }
                else if (gamelang == CHINESE)  {
                    notifheader1 = "能源警告";
                    notifheader2 = "致命警告";
                    notifbody1 = CharacterName + "的能量即将用完";
                    notifbody12 = "喂，爱你的Yigit请。" ;
                    notifbody2 = "你认为我是一个游戏，但我的心打破了";
                }
                else if (gamelang == JAPANESE)  {
                    notifheader1 = "エネルギー警告";
                    notifheader2 = "致命的な警告";
                    notifbody1 = CharacterName + "エネルギーがなくなる";
                    notifbody12 = "あなたのVivoを餌にしてください" ;
                    notifbody2 = "あなたは私がゲームだと思うが、私の心は壊れている";
                }
                else if (gamelang == PORTUGUESE)  {
                    notifheader1 = "Aviso de Energia";
                    notifheader2 = "Aviso fatal";
                    notifbody1 = "A energia de" + CharacterName + " está prestes a acabar";
                    notifbody12 = "Alimente e ame seu Vivo por favor." ;
                    notifbody2 = "Você acha que eu sou um jogo, mas meu coração está quebrando";
                }
                else
                {
                    notifheader1 = "Energy Warning";
                    notifheader2 = "Fatal Warning";
                    notifbody1 = CharacterName + "'s energy is about to run out.";
                    notifbody12 = "Feed and love your vivo please" ;
                    notifbody2 = "You think I'm a game, but my heart is breaking";
                }


                final boolean sleep = shared.getBoolean("sleep", true);


                final long time = shared.getLong("time", 0);
                final long lastplaytime = shared.getLong("lastplaytime", 0);
                final long[] time1 = {10};

                if (mProgressStatus[0] > 50) speedcounter = ABOVE50TIME;
                else speedcounter = BELOW50TIME;


                Long timenow = System.currentTimeMillis() / 1000;

               /* final long utcOffset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
                final long DAY_IN_MILLIS = 24 * 60 * 60;
                final long DAY_IN_MILLIS2 = 24 * 60 * 60;
                final long DAY_IN_MILLIS3 = 19 * 60 * 60 + 46*60;
                final long DAY_IN_MILLIS4 = 20 * 60 * 60;

                long seconds = System.currentTimeMillis() / 1000 + utcOffset / 1000;
                long seconds2 = System.currentTimeMillis() / 1000 + utcOffset / 1000;
                long seconds4 = System.currentTimeMillis() / 1000 + utcOffset / 1000;
                //seconds[0] = System.currentTimeMillis()/1000;




                seconds = seconds % DAY_IN_MILLIS;
                seconds = seconds % DAY_IN_MILLIS2;
               Long seconds22 = seconds2 % DAY_IN_MILLIS3;
                seconds4 = seconds4 % DAY_IN_MILLIS4;*/



                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int minutes = Calendar.getInstance().get(Calendar.MINUTE);
                int sec = Calendar.getInstance().get(Calendar.SECOND);

               int seconds= hour*10000+minutes*100+sec;

               if (timenow - lastplaytime > 60 * 60 * 120) fatal = true;
                else fatal=false;


                if (timenow - lastplaytime > 60 * 60 * 24) energy1 = true;
                else energy1=false;

                if (timenow - lastplaytime > 60 * 60 * 72) energy2 = true;
                else energy2=false;

              //  if (seconds == 0) {

                //    editor.putBoolean("isplustencoin", true);
                  //  editor.commit();
              //  }

               /* if (gamestop) {
                    gamestarcounter[0] = shared.getInt("gamestarcounter", 0);
                    final int gamestar1 = shared.getInt("gamestar", 0);
                    gamestarcounter[0]++;

                    if (gamestarcounter[0] % gamewaittime == 0)

                    {
                        if (gamestar[0] < MAXGAMEPLAY)
                            gamestar[0]++;
                        else gamestar[0] = MAXGAMEPLAY;

                        int gmstar = gamestar[0];
                        editor.putInt("gamestar", gmstar);
                        gamestar[0] = gamestar1;
                        editor.commit();

                    }

                    int gmscounter = gamestarcounter[0];
                    editor.putInt("gamestarcounter", gmscounter);
                    editor.commit();

                }*/

                if(gamestop)
                checkoverall();


                if (((fatal && gamestop &&  mProgressStatus[0] <=10 && seconds == 130000 ) && notificationstatus == true)) {

                    if (vibration == true && mutestate == false) showNotificationfatalwarning();
                    else if (vibration == false && mutestate == false)
                        showNotificationfatalwarningnnovibs();
                    else if (vibration == false && mutestate == true)
                        showNotificationfatalwarningnosoundnovibs();
                    else if (vibration == true && mutestate == true)
                        showNotificationfatalwarningnosound();

                }

               /* if(gamestop) delaycounter++;

                if(delaycounter % speedcounter==0 && gamestop) {



                    process[0] = process[0] - 1;
                    process[1] = process[1] - 1;
                    process[2] = process[2] - 1;

                    if (sleep == true) process[3] = process[3] + 1;
                    else process[3] = process[3] - 1;
                    process[4] = process[4] - 1;
                    process[5] = process[5] - 1;
                    // process[1]= (int) (process[1]-decrementtime);
                    checkoverall();
                    delaycounter=0;
                    //process[0]--;
                    pr0 = process[0];

                    pr1 = process[1];
                    pr2 = process[2];
                    pr3 = process[3];
                    pr4 = process[4];
                    pr5 = process[5];


                 /*   editor.putInt("procss0key", pr0);
                    editor.putInt("procss1key", pr1);
                    editor.putInt("procss2key", pr2);
                    editor.putInt("procss3key", pr3);
                    editor.putInt("procss4key", pr4);
                    editor.putInt("procss5key", pr5);

                    editor.commit();
                }*/


               // Log.d("TimerExample", "Going ... " + delaycounter);
                //Log.d("TLoimerExample", "Going for... " + process[1]);
               // Toast.makeText(con, Integer.toString(delaycounter), Toast.LENGTH_SHORT).show();

            }

            public void onFinish() {

            }
        }.start();


        /*






        new CountDownTimer(1000000000, 1000) {
            public void onTick(long milsec) {
                counter[0]++;
                cnt = counter[0];


                servicecounter[0]++;

                //Toast.makeText(con,Integer.toString( servicecounter[0]), Toast.LENGTH_LONG).show();


                double service= servicecounter[0];


                editor.putInt("originalcounter",(int)service);
                editor.commit();

                final long utcOffset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
                final long DAY_IN_MILLIS = 24*60*60;
                final long DAY_IN_MILLIS2 =24*60*60;
                final long DAY_IN_MILLIS3 =13*60*60;
                final long DAY_IN_MILLIS4 =20*60*60;


                long seconds = System.currentTimeMillis()/1000 + utcOffset/1000;
                long seconds2 = System.currentTimeMillis()/1000 + utcOffset/1000;
                long seconds4 = System.currentTimeMillis()/1000 + utcOffset/1000;
                //seconds[0] = System.currentTimeMillis()/1000;

                seconds= seconds% DAY_IN_MILLIS;
                seconds= seconds% DAY_IN_MILLIS2;
                seconds2= seconds% DAY_IN_MILLIS3;
                seconds4= seconds% DAY_IN_MILLIS4;

                if(seconds==0) {

                    editor.putBoolean("isplustencoin", true);
                    editor.commit();
                }

                gamestarcounter[0]++;

                if(gamestarcounter[0]%gamewaittime == 0)

                {
                    if (gamestar[0] <3)
                        gamestar[0]++;
                    else  gamestar[0]=3;
                    editor.putInt("gamestar",gamestar[0]);
                    editor.commit();

                }

                editor.putInt("gamestarcounter",gamestarcounter[0]);
                editor.commit();


                if(mProgressStatus[0]> 50) speedcounter = ABOVE50TIME;
                else speedcounter = BELOW50TIME;


                Long timenow= System.currentTimeMillis()/1000;




                if(timenow-lastplaytime> 60*60*6) fatal = true;


                if(((fatal && (seconds2==0 || seconds4==0)&& newgame==1) && notificationstatus==true))
                {
                    if (vibration==true && mutestate ==false) showNotificationfatalwarning();
                    else if (vibration==false && mutestate ==false) showNotificationfatalwarningnnovibs();
                    else if (vibration==false && mutestate ==true) showNotificationfatalwarningnosoundnovibs();
                    else if (vibration==true && mutestate ==true) showNotificationfatalwarningnosound();

                }


                if (servicecounter[0] % speedcounter == 0)
                {


                    process[0] = process[0] - 1;
                    process[1] = process[1] - 1;
                    process[2] = process[2] - 1;

                    if (sleep==true ) process[3] = process[3] + 1;
                    else process[3] = process[3] - 1;
                    process[4] = process[4] - 1;
                    process[5] = process[5] - 1;
                    // process[1]= (int) (process[1]-decrementtime);
                     checkoverall();

                    //process[0]--;
                    pr0 = process[0];
                    spd = speed;
                    pr1 = process[1];
                    pr2 = process[2];
                    pr3 = process[3];
                    pr4 = process[4];
                    pr5 = process[5];

                    double service2= (double) servicecounter[0] / (double) speedcounter;
                    servicecounter[0]=(int) service2;

                    editor.putInt("originalcounter",(int)service);
                    editor.commit();

                    editor.putInt("procss0key", pr0);
                    editor.putInt("procss1key", pr1);
                    editor.putInt("procss2key", pr2);
                    editor.putInt("procss3key", pr3);
                    editor.putInt("procss4key", pr4);
                    editor.putInt("procss5key", pr5);

                    editor.putInt("speed1", spd);
                    editor.putInt("speed2", spd1);
                    editor.putInt("counter", cnt);

                    long time1= System.currentTimeMillis();
                    time1 = time1/1000;
                    editor.putLong("time",time1);


                    editor.commit();
                }


            }

            public void onFinish() {

                //g.setVisibility(View.VISIBLE);
                // Time.setText(Long.toString(0));
                //showZaire();
                // showNotification();
            }
        }.start();
*/
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
       /* SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("process0", pr0);
        editor.putInt("process1", pr1);
        editor.putInt("speed1", spd);
        editor.putInt("counter", cnt);
        editor.commit();*/
        Toast.makeText(this, "IT Stopped", Toast.LENGTH_SHORT).show();
        //Thread.currentThread().interrupt();
        stopForeground(true);
    }


    public void showNotificationenergywarning() {

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();

        badgecount=true;
//Bitmap bitm = BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction);
        int counter=0;
        editor.putBoolean("badgecount", badgecount);
        editor.commit();

        counter=1;

        try {
            Badges.setBadge(this, counter);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            Log.d(TAG1, badgesNotSupportedException.getMessage());
        }

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader1)
                .setSmallIcon(R.mipmap.largenotifiaction)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle(notifbody1)
                .setContentText(notifbody12)
                .setVibrate(new long[]{1000, 1000})
                .setColor(color)
               // .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setAutoCancel(true)
                .setContentIntent(pi)
                //.setSmallIcon(0)
                .build();
        notification.contentView.setImageViewResource(android.R.id.icon, R.mipmap.largenotifiaction);
        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);





    }


    public void showNotificationenergywarningnosound() {

        energy=false;

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader1)
                .setSmallIcon(R.mipmap.largenotifiaction)
               // .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentTitle(notifbody1)
                .setContentText(notifbody12)
                .setContentIntent(pi)
                .setVibrate(new long[]{1000, 1000})
                .setColor(color)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }


    public void showNotificationenergywarningnnovibs() {

        energy=false;
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader1)
                .setSmallIcon(R.mipmap.largenotifiaction)
                .setContentTitle(notifbody1)
                .setContentText(notifbody2 )
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setColor(color)

                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



    }

    public void showNotificationenergywarningnosoundnovibs() {

        energy=false;

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader1)
                .setSmallIcon(R.mipmap.largenotifiaction)

                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentTitle(notifbody1)
                .setContentText(notifbody12 )
                .setColor(color)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);


    }


    public void showNotificationfatalwarning() {
        fatal = false;
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader2)
                .setContentTitle(notifheader2)
                .setContentText(notifbody2)
                .setSmallIcon(R.mipmap.largenotifiaction)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setColor(color)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentIntent(pi)
                .setVibrate(new long[]{1000, 1000})
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }


    public void showNotificationfatalwarningnosound() {


        fatal = false;
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader2)
                .setContentTitle(notifheader2)
                .setContentText(notifbody2)
                .setSmallIcon(R.mipmap.largenotifiaction)
                .setColor(color)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentIntent(pi)
                .setVibrate(new long[]{1000, 1000})
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }


    public void showNotificationfatalwarningnnovibs() {

        fatal = false;
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader2)
                .setContentTitle(notifheader2)
                .setContentText(notifbody2)
                .setSmallIcon(R.mipmap.largenotifiaction)
                .setColor(color)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }

    public void showNotificationfatalwarningnosoundnovibs() {


        fatal = false;

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(notifheader2)
                .setContentTitle(notifheader2)
                .setSmallIcon(R.mipmap.largenotifiaction)
                .setColor(color)

                .setContentText(notifbody2)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }




    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Vivo")
                .setSmallIcon(R.mipmap.vivoentsmall2)
                .setContentTitle("Check")
                .setContentText(CharacterName+"Olaaaaa")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    void checkoverall()


    {


        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        int x0 = shared.getInt("procss0key", 101);
        int iq = shared.getInt("iq", 0);
        int x1 = shared.getInt("procss1key", 101);
        int x2 = shared.getInt("procss2key", 101);
        int x3 = shared.getInt("procss3key", 101);
        int x4 = shared.getInt("procss4key", 101);
        int x5 = shared.getInt("procss5key", 101);
        boolean sleep = shared.getBoolean("sleep", true);
        process[0] = x0;
        process[1] = x1;
        process[2] = x2;
        process[3] = x3;
        process[4] = x4;
        process[5] = x5;

        final long time = shared.getLong("lastplaytime", 0);
        long time1= System.currentTimeMillis();

        double timedif = time1-time;
        if(iq<51) hungerspeed = BELOW50HUNGER;
        else if(iq>50  && iq<101) hungerspeed = ABOVE50HUNGER;
        else hungerspeed = ABOVE100HUNGER;

        if(iq<51) hygienespeed = BELOW50HYGIENE;
        else if(iq>50  && iq<101) hygienespeed = ABOVE50HYGIENE;
        else hygienespeed = ABOVE100HYGIENE;


        if(iq<51) wcspeed = BELOW50WC;
        else if(iq>50  && iq<101) wcspeed = ABOVE50WC;
        else wcspeed = ABOVE100WC;


        if(sleep == false) {
            if (iq < 51) sleepspeed = BELOW50SLEEP;
            else if (iq > 50 && iq < 101) sleepspeed = ABOVE50SLEEP;
            else sleepspeed = ABOVE100SLEEP;
        }
        else
        {
            if (iq < 51) sleepspeed = BELOW50NONSLEEP;
            else if (iq > 50 && iq < 101) sleepspeed = ABOVE50NONSLEEP;
            else sleepspeed = ABOVE100NONSLEEP;
        }


        if(iq<51) lovespeed = BELOW50LOVE;
        else if(iq>50  && iq<101) lovespeed = ABOVE50SLOVE;
        else lovespeed = ABOVE100LOVE;

        double diff = (double) timedif / (double)speedcounter;

        double diffhunger = (double) timedif / (double)hungerspeed;
        double diffhygiene = (double) timedif / (double)hygienespeed;
        double diffwc = (double) timedif / (double)wcspeed;
        double diffsleep = (double) timedif / (double)sleepspeed;
        double difflove = (double) timedif / (double)lovespeed;


        process[0]=  process[0] - (int) diffhunger;
        process[1]=  process[1] - (int) diffhygiene;
        process[2]=  process[2] - (int) diffwc;
       if(sleep)  process[3]=  process[3] + (int) diffsleep;
        else  process[3]=  process[3] - (int) diffsleep;

        //process[4]=  process[4] - (int) diff;
        process[5]=  process[5] - (int) difflove;






        // ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
        double y = (double) process[0] * (0.30) + (double)process[1] * (0.15) + (double)process[3] * (0.2) + (double)process[2] * (0.15)  + (double)process[5] * (0.2);
        int x = (int) y;
        mProgressStatus[0]=x;
        //mProgressStatus[0] = x;
        //TextView name1 = (TextView) findViewById(R.id.name);
        //mProgress.setProgress(x);
        //name1.setText(Long.toString(x));


        if(x<= 20) energy=true;
        else energy=false;



        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);
        int sec = Calendar.getInstance().get(Calendar.SECOND);

        int seconds2= hour*10000+minutes*100+sec;


        if (energy &&(seconds2==130000 ) && gamestop && notificationstatus==true  && (energy1 || energy2))


        {

            if (vibration==true && mutestate ==false) showNotificationenergywarning();
            else if (vibration==false && mutestate ==false) showNotificationenergywarningnnovibs();
            else if (vibration==false && mutestate ==true) showNotificationenergywarningnosoundnovibs();
            else if (vibration==true && mutestate ==true) showNotificationenergywarningnosound();

        }


    }


}