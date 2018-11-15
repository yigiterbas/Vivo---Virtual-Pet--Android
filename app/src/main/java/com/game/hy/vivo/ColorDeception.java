package com.game.hy.vivo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.vending.billing.IInAppBillingService;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.sdk.InMobiSdk;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.vungle.publisher.VunglePub;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import static com.facebook.login.widget.ProfilePictureView.TAG;


public class ColorDeception extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,BillingProcessor.IBillingHandler,MoPubInterstitial.InterstitialAdListener {
    MoPubInterstitial mobInterstitial;
    MoPubInterstitial mobInterstitial2;
    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        if (interstitial.isReady()) {

        } else {
            Log.d("mopub:","errror");
        }
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) { }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) { Log.d("mopub:","shown");}

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {}

    private int soundID;
    private SoundPool soundPool;
    AudioManager audioManager;
    float actVolume,maxVolume,volume;
    int randomcounter;
    int score=0;
    CountDownTimer waitTimer;
    long time = 60;
    final int gamewaittime = 1800;
    int[] mProgressStatus={100};
    final Random r = new Random();
    final int i1 = (r.nextInt(3) + 0);
    final int heart_speed[] = {1000};
    int RPM = heart_speed[0] / 1000 * 80;
    int level =0;
    boolean adflag = false;
    Boolean iqplusone =false;
    InterstitialAd mInterstitialAd;
    int scorecounter=0;
    int NONE= 100;
    Boolean backtooption = false;
    int maxiqscore=10000;
    boolean mExplicitSignOut = false;
    boolean mInSignInFlow = false; // set to true when you're in the middle of the
    // sign in flow, to know you should not attempt
    // to connect in onStart()
    GoogleApiClient mGoogleApiClient;
    private static int RC_SIGN_IN = 9001;
    int REQUEST_LEADERBOARD=100111;
    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;
    Float BSCALE = 1f;
    Float BALPHA = 0.7f;
    int ENGLISH = 0;
    int TURKISH =1 ;
    int FRENCH =2;
    int SPANISH =3;
    int RUSSIAN =4;
    int CHINESE =5 ;
    int JAPANESE = 6;
    int PORTUGUESE =7;
    int gamelang = NONE;
    int tries=0;
    int correctnum=0;
    int wrong=0;
    int highest =0;;
    Boolean zerogamestar=false;
    int lowest =0;
    Boolean vibration = true;
    Boolean finishgame=false;

    Random s = new Random ();
    int[] gamestarcounter={0};
    int gamestar;
    Typeface tf;
    int coin=0;
    int delay = 300;
    int MAXGAMEPLAY = 3;
    Boolean mutestate = false;
    int colorarray[] ={Color.argb(255,25,189,155),Color.argb(255,255,169,221), Color.argb(255,232,75,60), Color.argb(255,53,152,219),Color.WHITE,Color.YELLOW,Color.argb(255,143,68,173),Color.argb(255,230,125,33)};
   /* String GREEN = getString(R.string.GREEN);
    String PINK = getString(R.string.PINK);
    String RED = getString(R.string.RED);
    String BLUE = getString(R.string.BLUE);
    String WHITE = getString(R.string.WHITE);
    String YELLOW = getString(R.string.YELLOW);
    String PURPLE = getString(R.string.PURPLE);
    String ORANGE = getString(R.string.ORANGE);*/



    int choice1array2[] = {Color.RED, Color.BLUE, Color.MAGENTA, Color.GREEN, Color.GRAY, Color.LTGRAY};
    int counterquestion = (s.nextInt(2) + 0);
    Button barray[] = new Button[2];
    Button barray2[] = new Button[3];

    int correctarray[];
    int adcount = 0;
    int correct=0;
    int iq;
    String TAG = "";
    Boolean mult2flag = false;
    BillingProcessor bp;
    final Activity cont = ColorDeception.this;
    int BILLINGSTATE = 0;
    int PURCHASE1000 = 1000;
    int PURCHASE500 = 500;
    int PURCHASE200 = 200;
    int UNLOCKIQ = 2;
    int PLUS5GAME = 3;
    int NOADD = 4;
    int SLIDEANIMDUR=600;
    Boolean plus5gameflag = false;
    Boolean GoodlegO=false;
    Boolean facebookgo=false;
    Boolean videogo=false;
    Boolean instago=false;
    Boolean twittergo = false;
    Boolean facecoin = false;
    Boolean googlecoin = false;
    Boolean instacoin = false;
    Boolean twitcoin = false;
    boolean unlockiqflag = false;

    IInAppBillingService mService;


    String COIN200 = "coin200";
    String COIN500 = "coin500";
    String COIN1000 = "coin1000";

    String NOADDS = "noadd";
    String UNLOCKIQS = "unluckiq";
    String PLUS5GAMES = "plus5game";
    String MULTCOIN2S = "mult2";
    int MULTCOIN2 = 5;

    public boolean isOnline() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    void alertbox()

    {
        AlertDialog alertDialog = new AlertDialog.Builder(ColorDeception.this).create();
        String warningtext = "";
        String messagetext = "";
        String oktext = "";

        if (gamelang == ENGLISH) {
            warningtext = "Warning";
            messagetext = "No Internet Connection";
            oktext = "OK";

        }

        else if (gamelang == TURKISH) {
            warningtext = "UYARI";
            messagetext = "İnternet bağlantısı yok";
            oktext = "TAMAM";

        }
        else if (gamelang == FRENCH) {
            warningtext = "ATTENTION";
            messagetext = "Pas de connexion Internet";
            oktext = "D'accord";

        }
        else if (gamelang == SPANISH) {
            warningtext = "ADVERTENCIA";
            messagetext = "Sin conexión a Internet";
            oktext = "BUENO";

        }
        else if (gamelang == RUSSIAN) {
            warningtext = "ПРЕДУПРЕЖДЕНИЕ";
            messagetext = "Нет соединения с интернетом";
            oktext = "ХОРОШО";

        }
        else if (gamelang == CHINESE) {
            warningtext = "警告";
            messagetext = "没有网络连接";
            oktext = "好的";

        }
        else if (gamelang == JAPANESE) {
            warningtext = "警告";
            messagetext = "インターネットに接続していない";
            oktext = "はい";

        }
        else if (gamelang == PORTUGUESE) {
            warningtext = "Atenção";
            messagetext = "Sem ligação à Internet";
            oktext = "OK";

        }
        else{
            warningtext = "Warning";
            messagetext = "No Internet Connection";
            oktext = "OK";

        }


        alertDialog.setTitle(warningtext);
        alertDialog.setMessage(messagetext);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, oktext,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    @Override
    public void onBillingInitialized() {

    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        final Button questionplus5game = (Button) findViewById(R.id.questionplus5);
        final Button questionunlockiq = (Button) findViewById(R.id.questionunlock );
        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
        final TextView textquestion = (TextView) findViewById(R.id.textView872);
        final Button questionmult2 = (Button) findViewById(R.id.questionmult2);
        if (BILLINGSTATE == PURCHASE1000 || productId == COIN1000) {
            final TextView tcoin = (TextView) findViewById(R.id.textcoin);
            coin = coin + 10000;
            coinflip();
            bp.consumePurchase(COIN1000);
            tcoin.setText(Integer.toString(coin));
            editor.putInt("coin",coin);
            editor.commit();
            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.coinabove10);
            if (!mutestate) {
                clickaudio.start();
                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        clickaudio.release();

                    }

                    ;
                });
            }
            BILLINGSTATE = 0;
        }

        if (BILLINGSTATE == PURCHASE500 || productId == COIN500) {
            final TextView tcoin = (TextView) findViewById(R.id.textcoin);
            coin = coin + 5000;
            bp.consumePurchase(COIN500);
            coinflip();

            tcoin.setText(Integer.toString(coin));
            editor.putInt("coin",coin);
            editor.commit();
            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.coinabove10);
            if (!mutestate) {
                clickaudio.start();
                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        clickaudio.release();

                    }

                    ;
                });
            }
            BILLINGSTATE = 0;
        }

        if (BILLINGSTATE == MULTCOIN2 || productId == MULTCOIN2S) {

            final RelativeLayout mult2layout = (RelativeLayout) findViewById(R.id.mult2);
            final Button mult2image = (Button) findViewById(R.id.imagemult2);
            final Button mult2button = (Button) findViewById(R.id.mult2button);
            final RelativeLayout mult2layout2 = (RelativeLayout) findViewById(R.id.mult22);
            mult2flag=true;
            //lock();
            showalert(MULTCOIN2);

            editor.putBoolean("mult2flag", true);
            editor.commit();
            mult2layout2.setEnabled(false);
            mult2button.setEnabled(false);
            mult2image.setEnabled(false);

            mult2layout2.setAlpha(0.33f);
            mult2button.setAlpha(0.33f);
            mult2image.setAlpha(0.33f);
            questionmult2.setAlpha(0.33f);
            questionmult2.setEnabled(false);
        }

        if (BILLINGSTATE == PURCHASE200 || productId == COIN200) {
            final TextView tcoin = (TextView) findViewById(R.id.textcoin);
            coin = coin + 2000;
            bp.consumePurchase(COIN200);
            coinflip();
            tcoin.setText(Integer.toString(coin));
            editor.putInt("coin",coin);
            editor.commit();
            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.coinabove10);
            if (!mutestate) {
                clickaudio.start();
                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        clickaudio.release();

                    }

                    ;
                });
            }
            BILLINGSTATE = 0;

        }


        if (BILLINGSTATE == UNLOCKIQ || productId == UNLOCKIQS) {

            final RelativeLayout unlcokiqlayout = (RelativeLayout) findViewById(R.id.unlockiq);
            final Button unlcokiqimage = (Button) findViewById(R.id.imageunlockiq);
            final Button unlockiqbutton = (Button) findViewById(R.id.unlockiqbutton);
            final RelativeLayout unlockiqlayout2 = (RelativeLayout) findViewById(R.id.unlockiq2);
            unlockiqflag=true;
            //lock();
            showalert(UNLOCKIQ);

            editor.putBoolean("unlockiqflag", true);
            editor.commit();
            unlockiqlayout2.setEnabled(false);
            unlockiqbutton.setEnabled(false);
            unlcokiqimage.setEnabled(false);

            unlockiqlayout2.setAlpha(0.33f);
            unlockiqbutton.setAlpha(0.33f);
            unlcokiqimage.setAlpha(0.33f);
            questionunlockiq.setAlpha(0.33f);
            questionunlockiq.setEnabled(false);

        }

        if (BILLINGSTATE == PLUS5GAME || productId == PLUS5GAMES)
        {

            final RelativeLayout plusgame = (RelativeLayout) findViewById(R.id.plusgamerel);


            final RelativeLayout plusgamelayout = (RelativeLayout) findViewById(R.id.coinlayoutplus5game);
            final Button plusgamel1 = (Button) findViewById(R.id.plusgameimg);
            final Button plusgamel2 = (Button) findViewById(R.id.plusgamebutton);
            MAXGAMEPLAY = 5;
            gamestar=5;
            plus5gameflag = true;
            showalert(PLUS5GAME);

            editor.putBoolean("plus5gameflag", plus5gameflag);
            editor.putInt("gamestar", gamestar);
            editor.putInt("MAXGAMEPLAY", MAXGAMEPLAY);
            editor.commit();
            plusgame.setEnabled(false);
            plusgamel1.setEnabled(false);
            plusgamel2.setEnabled(false);
            plusgame.setAlpha(0.33f);
            plusgamel1.setAlpha(0.33f);
            plusgamel2.setAlpha(0.33f);
            questionplus5game.setAlpha(0.33f);
            questionplus5game.setEnabled(false);
        }

        if (BILLINGSTATE == NOADD || productId == NOADDS) {

            final RelativeLayout coin10 = (RelativeLayout) findViewById(R.id.coin10rel);


            final RelativeLayout coin10layout = (RelativeLayout) findViewById(R.id.coinlayoutcoin10);
            final Button coin10l1 = (Button) findViewById(R.id.coin10img);
            final Button coin10l2 = (Button) findViewById(R.id.coin10button);
            adflag = true;
            showalert(PLUS5GAME);
            editor.putBoolean("adflag", true);
            editor.commit();

            addfunction();
            coin10.setEnabled(false);
            coin10l1.setEnabled(false);
            coin10l2.setEnabled(false);
            coin10.setAlpha(0.33f);
            coin10l1.setAlpha(0.33f);
            coin10l2.setAlpha(0.33f);
            questionnodd.setAlpha(0.33f);
            questionnodd.setEnabled(false);


        }
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {

    }

    @Override
    public void onPurchaseHistoryRestored() {


    }
    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
        }
    };


    String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoAGY3CL6BvTO/cwRLZ4D3O5w5B21ZO/ThlQNeJz7Cfj8vLC8+NzUxcO82JbHsuxAj1Wg0tTmuCdHUld0gt84enT+rjOqFuFC+7wFbCyWuM+grrM0DZUTP2wJF2IhGbZqFVDHbVBp2eutw8yaPzspvr3kadtUteMCLcNSdgMgKo+X/zEJ9z1rVhTVV+TmB3egpC7UVHkOW09oChFGe9e+kYXNAeDh5MWNgdbdvlDgFCY15nIaLDGRs8pLqaYYrtyod+1mXp0NGnHcvOpUJrWiHVfnFwSl0ZnXKGr3L8CQxLuL7LBoTqeR6HQ8ITKLCVdEbAqm87rFMYmwwK9WpQSI0wIDAQAB";




    @Override
    public void onConnected(Bundle connectionHint) {
        // show sign-out button, hide the sign-in button
        //findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        //findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        if(mGoogleApiClient.isConnected() )
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                    getString(R.string.leaderboard_id)), REQUEST_LEADERBOARD);
        // (your code here: update UI, enable functionality that depends on sign in, etc)
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            // Already resolving
            return;
        }

        // If the sign in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInflow) {
            mAutoStartSignInflow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, getString(R.string.signin_other_error))) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button
    }

    void noadsalert()

    {
        AlertDialog alertDialog = new AlertDialog.Builder(ColorDeception.this).create();
        String warningtext = "";
        String messagetext = "";
        String oktext = "";

        if (gamelang == ENGLISH) {
            warningtext = "Warning";
            messagetext = "Ads are not available";
            oktext = "OK";

        } else if (gamelang == TURKISH) {
            warningtext = "UYARI";
            messagetext = "Reklamlar şu an gösterilemiyor.";
            oktext = "TAMAM";

        } else if (gamelang == FRENCH) {
            warningtext = "ATTENTION";
            messagetext = "Les annonces ne sont pas disponibles.";
            oktext = "D'accord";

        } else if (gamelang == SPANISH) {
            warningtext = "ADVERTENCIA";
            messagetext = "Los anuncios no están disponibles";
            oktext = "BUENO";

        } else if (gamelang == RUSSIAN) {
            warningtext = "ПРЕДУПРЕЖДЕНИЕ";
            messagetext = "объявления не доступны";
            oktext = "ХОРОШО";

        } else if (gamelang == CHINESE) {
            warningtext = "警告";
            messagetext = "广告不可用";
            oktext = "好";

        } else if (gamelang == JAPANESE) {
            warningtext = "警告";
            messagetext = "広告は利用できません";
            oktext = "はい";

        } else if (gamelang == PORTUGUESE) {
            warningtext = "ATENÇÃO";
            messagetext = "Os anúncios não estão disponíveis";
            oktext = "OK";

        } else {
            warningtext = "Warning";
            messagetext = "Ads are not available";
            oktext = "OK";

        }
        alertDialog.setTitle(warningtext);
        alertDialog.setMessage(messagetext);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, oktext,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    InMobiInterstitial interstitialAd;
    InMobiInterstitial interstitialnew;
    InMobiBanner bannerAd2;

    @Override
    public void onStart() {
        super.onStart();
       // Chartboost.onStart(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        //Chartboost.onStop(this);
    }
    private MoPubView moPubView;

    void showad2()
    {

        if(mobInterstitial2.isReady()) mobInterstitial2.show();
        else mobInterstitial2.load();

    }

    void showad()
    {
        if(mobInterstitial.isReady()) mobInterstitial.show();
        else mobInterstitial.load();
    }


    protected void onResume()
    {

   // {        if (mAdView != null) {
     //   mAdView.resume();
   // }
        super.onResume();
       // vunglePub.onResume();
       // Chartboost.onResume(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        facebookgo = shared.getBoolean("facebookgo", facebookgo);
        GoodlegO = shared.getBoolean("GoodlegO", GoodlegO);
        instago = shared.getBoolean("instago", instago);
        twittergo = shared.getBoolean("twittergo", instago);
        videogo = shared.getBoolean("videogo", videogo);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();

        if (GoodlegO || instago || facebookgo ||twittergo) {



            final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
            final RelativeLayout pluscoinlayout = (RelativeLayout) findViewById(R.id.pluscoinlayout2);
            pluscoinlayout.setVisibility(View.VISIBLE);

            TextView tcoinamount = (TextView) findViewById(R.id.coinamount2);


            tcoinamount.setText("+" + Integer.toString(50));

            final Animation shake = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.lefttoright);

            final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
            if (!mutestate) {
                clickaudio2.start();
                clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        clickaudio2.release();

                    }

                    ;
                });
            }
            shake.setDuration(SLIDEANIMDUR);
            disableallchild.setVisibility(View.VISIBLE);
            pluscoinlayout.setAnimation(shake);


        }


    }

    public void onPause() {


        super.onPause();


    }

    void heartbeat()
    {




        heartimage.clearAnimation();
        heartimage.setAnimation(null);


        if( heartbeat<201 &&  heartbeat>= 80 ) pulse.setDuration(400);
        else if( heartbeat<80 &&  heartbeat>= 70 ) pulse.setDuration(500);
        else if( heartbeat<70 &&  heartbeat>= 60 ) pulse.setDuration(650);
        else  if( heartbeat<60 &&  heartbeat>= 50 ) pulse.setDuration(800);
        else  pulse.setDuration(1000);





        pulse.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                printheartbeat();

            }

            @Override
            public void onAnimationEnd(Animation animation) {




                if( heartbeat<201 &&  heartbeat>= 80 ) pulse.setDuration(400);
                else if( heartbeat<80 &&  heartbeat>= 70 ) pulse.setDuration(500);
                else if( heartbeat<70 &&  heartbeat>= 60 ) pulse.setDuration(650);
                else  if( heartbeat<60 &&  heartbeat>= 50 ) pulse.setDuration(800);
                else  pulse.setDuration(1000);



                heartimage.startAnimation(pulse);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                //printheartbeat();
            }
        });

        heartimage.startAnimation(pulse);

    }


    void printheartbeat()

    {

        final SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        heartbeat = shared.getInt("heartbeatrate", 70);
        heartbeat = heartbeat + randomi3;

     /*   if( mProgressStatus[0]<201 &&  mProgressStatus[0]>= 98 ) heartbeat = 80 + randomi3;
        else if( mProgressStatus[0]<98 &&  mProgressStatus[0]>= 95 ) heartbeat = 79 + randomi3;
        else if( mProgressStatus[0]<95 &&  mProgressStatus[0]>= 92 ) heartbeat = 78 + randomi3;
        else if( mProgressStatus[0]<92 &&  mProgressStatus[0]>= 89 ) heartbeat = 77 + randomi3;
        else if( mProgressStatus[0]<89 &&  mProgressStatus[0]>= 86 ) heartbeat = 76 + randomi3;
        else if( mProgressStatus[0]<86 &&  mProgressStatus[0]>= 83 ) heartbeat = 75 + randomi3;
        else if( mProgressStatus[0]<83 &&  mProgressStatus[0]>= 80 ) heartbeat = 74 + randomi3;
        else if( mProgressStatus[0]<80 &&  mProgressStatus[0]>= 77 ) heartbeat = 73 + randomi3;
        else if( mProgressStatus[0]<77 &&  mProgressStatus[0]>= 74 ) heartbeat = 72 + randomi3;
        else if( mProgressStatus[0]<74 &&  mProgressStatus[0]>= 71 ) heartbeat = 71 + randomi3;
        else if( mProgressStatus[0]<71 &&  mProgressStatus[0]>= 68 ) heartbeat = 70 + randomi3;
        else if( mProgressStatus[0]<68 &&  mProgressStatus[0]>= 65 ) heartbeat = 69 + randomi3;
        else if( mProgressStatus[0]<65 &&  mProgressStatus[0]>= 62 ) heartbeat = 68 + randomi3;
        else if( mProgressStatus[0]<62 &&  mProgressStatus[0]>= 59 ) heartbeat = 67 + randomi3;
        else if( mProgressStatus[0]<59 &&  mProgressStatus[0]>= 56 ) heartbeat = 66 + randomi3;
        else if( mProgressStatus[0]<56 &&  mProgressStatus[0]>= 53 ) heartbeat = 65 + randomi3;
        else if( mProgressStatus[0]<53 &&  mProgressStatus[0]>= 50 ) heartbeat = 64 + randomi3;

        else  heartbeat = 63 + randomi3;


        initialheartrate = heartbeat;*/

        heartratet2.setText(Integer.toString(heartbeat));

    }
    ImageView heartimage;
    TextView heartratet2;
    Animation pulse2;
    Animation pulse;
    Animation reset;
    int initialheartrate;
    int i2 = 0;
    int heartbeat = 80;
    int touchcount = 0;
    int randomi3 = 1;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_color_deception);

      //  String colortext[] = {GREEN,PINK,RED,BLUE,WHITE,YELLOW,PURPLE,ORANGE};
       // mAdView = (AdView) findViewById(R.id.ad_view);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        moPubView = (MoPubView) findViewById(R.id.banner_mopubview);
        moPubView.setAdUnitId("f399754aa73f462c9b4c02616428337f");
        moPubView.loadAd();

        moPubView.setBannerAdListener(new MoPubView.BannerAdListener() {
            @Override
            public void onBannerLoaded(MoPubView banner) {

            }

            @Override
            public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                moPubView.loadAd();
            }

            @Override
            public void onBannerClicked(MoPubView banner) {

            }

            @Override
            public void onBannerExpanded(MoPubView banner) {

            }

            @Override
            public void onBannerCollapsed(MoPubView banner) {

            }
        });
        final Button coinvideo = (Button) findViewById(R.id.coinvideoimg);
        final Button coinvideo2 = (Button) findViewById(R.id.coinvideobutton);
        final RelativeLayout coinvideolayout = (RelativeLayout) findViewById(R.id.coinvideorel);
        mobInterstitial = new MoPubInterstitial(this, "e3deeee7b8d946978ad960b6c0f5c4c7");
        mobInterstitial.setInterstitialAdListener(this);
        mobInterstitial.load();

        mobInterstitial2 = new MoPubInterstitial(this, "dcc632abe6224b77b6509e8588e4cc81");
        mobInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                if (interstitial.isReady()) {


                } else {
                    Log.d("mopub:","errror");
                }
            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {coinvideolayout.setEnabled(true);
                coinvideo2.setEnabled(true);
                coinvideo.setEnabled(true); }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) { coinvideolayout.setEnabled(true);
                coinvideo2.setEnabled(true);
                coinvideo.setEnabled(true);

                coinvideolayout.setEnabled(true);
                coinvideo2.setEnabled(true);
                coinvideo.setEnabled(true);


                //AdColony.requestInterstitial( ZONE_ID, listener, ad_options );


                mobInterstitial2.load();


            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {

            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {}
        });
        mobInterstitial2.load();

        Bundle params = new Bundle();
        params.putString("image_name", "colorsensestart");
        params.putString("full_text", "colorsensestart");
        mFirebaseAnalytics.logEvent("colorsensestart", params);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volume = actVolume / maxVolume;

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {



            }

        });

        soundID = soundPool.load(this, R.raw.click2017, 1);



        final TextView Time = (TextView) findViewById(R.id.time);

        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        adcount =  shared.getInt("adcount", adcount);
        adflag = shared.getBoolean("adflag", adflag);
        int newgame=shared.getInt("newgame", 0);
        mult2flag= shared.getBoolean("mult2flag", mult2flag);

        if (newgame==1)
        {Boolean mute2 = shared.getBoolean("mutestate", false);
            mutestate = mute2;}


        final Button b1 = (Button) findViewById(R.id.choice1);
        final Button b2 = (Button) findViewById(R.id.choice2);

        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1);
        final RelativeLayout l1 = (RelativeLayout) findViewById(R.id.linearLayout);
        final LinearLayout l2 = (LinearLayout) findViewById(R.id.lin2);
        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
        //final TextView t1 = (TextView) findViewById(R.id.textView);
        final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
        //tf = Typeface.createFromAsset(getAssets(), "browbold.ttf");
        ProgressBar p4 = (ProgressBar) findViewById(R.id.progressBar4);

        p4.setScaleY(3);
        p4.getProgressDrawable().setColorFilter(
                Color.argb(255, 25, 189, 155), android.graphics.PorterDuff.Mode.SRC_IN);



        final TextView heading = (TextView) findViewById(R.id.textView37);
        gamelang = shared.getInt("gamelang", NONE);
        if (gamelang == ENGLISH) updateEnglish();
        else if (gamelang == TURKISH) updateTurkish();
        else if (gamelang == FRENCH) updateFrench();
        else if (gamelang == SPANISH) updateSpanish();
        else if (gamelang == RUSSIAN) updaterussian();
        else if (gamelang == CHINESE) updatechinese();
        else if (gamelang == JAPANESE) updatejapanese();
        else if (gamelang == PORTUGUESE) updateporteguese();
        else updateEnglish();




        TextView question = (TextView) findViewById(R.id.textView45);

        gamestar= shared.getInt("gamestar", gamestar);
        //zerogamestar= shared.getBoolean("zerogamestar", zerogamestar);
        gamestarcounter[0] = shared.getInt("gamestarcounter", gamestarcounter[0]);
        MAXGAMEPLAY=shared.getInt("MAXGAMEPLAY", MAXGAMEPLAY);
        adflag = shared.getBoolean("adflag", adflag);
        facecoin = shared.getBoolean("facecoin", facecoin);
        googlecoin = shared.getBoolean("googlecoin", googlecoin);
        instacoin = shared.getBoolean("instacoin", instacoin);
        twitcoin = shared.getBoolean("twitcoin", twitcoin);
        unlockiqflag = shared.getBoolean("unlockiqflag", unlockiqflag);
        plus5gameflag = shared.getBoolean("plus5gameflag", plus5gameflag);
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoAGY3CL6BvTO/cwRLZ4D3O5w5B21ZO/ThlQNeJz7Cfj8vLC8+NzUxcO82JbHsuxAj1Wg0tTmuCdHUld0gt84enT+rjOqFuFC+7wFbCyWuM+grrM0DZUTP2wJF2IhGbZqFVDHbVBp2eutw8yaPzspvr3kadtUteMCLcNSdgMgKo+X/zEJ9z1rVhTVV+TmB3egpC7UVHkOW09oChFGe9e+kYXNAeDh5MWNgdbdvlDgFCY15nIaLDGRs8pLqaYYrtyod+1mXp0NGnHcvOpUJrWiHVfnFwSl0ZnXKGr3L8CQxLuL7LBoTqeR6HQ8ITKLCVdEbAqm87rFMYmwwK9WpQSI0wIDAQAB", this);
        final String app_id = "56c04bc0735f803a61000008";

        mProgressStatus[0] = shared.getInt("mProgressStatus2", mProgressStatus[0]);
        heart_speed[0] = shared.getInt("heartspeed", heart_speed[0]);
        final TextView t2 = (TextView) findViewById(R.id.editText2);
        final ImageView zoomTarget = (ImageView) findViewById(R.id.testImage);
        final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(zoomTarget, "scaleX", 0.85f); // set x scale for the heart animation
        int x=mProgressStatus[0];

        if(x>=85 && x <= 101)
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 14, 138, 92), android.graphics.PorterDuff.Mode.SRC_IN);

        else  if(x>=70 && x <= 84)
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 55, 181, 74), android.graphics.PorterDuff.Mode.SRC_IN);
        else  if(x>=60 && x <= 69)
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 192, 215, 47), android.graphics.PorterDuff.Mode.SRC_IN);
        else  if(x>=45 && x <= 59)
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 246, 193, 0), android.graphics.PorterDuff.Mode.SRC_IN);

        else  if(x>=30 && x <= 44)
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 244, 133, 0), android.graphics.PorterDuff.Mode.SRC_IN);
        else  if(x>=15 && x <= 29)
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 243, 89, 0), android.graphics.PorterDuff.Mode.SRC_IN);

        else p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 243, 33, 0), android.graphics.PorterDuff.Mode.SRC_IN);
        p4.setProgress(mProgressStatus[0]);


        heartratet2 = (TextView) findViewById(R.id.editText2);

        reset = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.slowdown);
        heartimage = (ImageView) findViewById(R.id.testImage);
        pulse2 = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.scale2);
        pulse = AnimationUtils.loadAnimation(this,  R.anim.scale);



        heartbeat();



      /*  scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);

        scaleXAnimator.setRepeatCount(1000000); // set animation count

        scaleXAnimator.setDuration(1000); // set the heart animation rate

        final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(zoomTarget, "scaleY", 0.85f); // set y scale for the heart animation

        scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);

        scaleYAnimator.setRepeatCount(1000000);

        scaleYAnimator.setDuration(1000);

        scaleXAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                int y;

                if (heart_speed[0] >= 500 && heart_speed[0] < 750) RPM = 85 + i1;
                else if (heart_speed[0] >= 0 && heart_speed[0] < 500) RPM = 90 + i1;
                else if (heart_speed[0] >= 750 && heart_speed[0] < 1000) RPM = 80 + i1;
                else if (heart_speed[0] >= 1000 && heart_speed[0] < 1200) RPM = 75 + i1;
                else if (heart_speed[0] >= 1200 && heart_speed[0] < 1500) RPM = 70 + i1;
                else if (heart_speed[0] >= 1500 && heart_speed[0] < 1800) RPM = 65 + i1;
                else if (heart_speed[0] >= 1800 && heart_speed[0] < 2000) RPM = 60 + i1;


                else RPM = 55;
                final Random r1 = new Random();
                final int i2 = (r1.nextInt(5) + 0);
                t2.setText(Integer.toString(RPM + i2)); // set random variable for RPM
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //t1.setText(Integer.toString(heart_speed[0]));
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {


                if (mProgressStatus[0] < 200 && mProgressStatus[0] > 80) {
//
//					scaleXAnimator.setDuration(2000);
                    scaleXAnimator.setDuration(1000);
                    heart_speed[0] = 1000;
//					t2.setText(Integer.toString(75));
                } else if (mProgressStatus[0] <= 80 && mProgressStatus[0] > 70) {

                    scaleXAnimator.setDuration(1200);
                    heart_speed[0] = 1200;


                } else if (mProgressStatus[0] <= 70 && mProgressStatus[0] > 60) {

                    scaleXAnimator.setDuration(1500);
                    heart_speed[0] = 1500;


                } else if (mProgressStatus[0] <= 60 && mProgressStatus[0] > 50) {

                    scaleXAnimator.setDuration(1800);
                    heart_speed[0] = 1800;


                } else if (mProgressStatus[0] <= 50 && mProgressStatus[0] > 30) {

                    scaleXAnimator.setDuration(1999);
                    heart_speed[0] = 1999;


                } else {

                    scaleXAnimator.setDuration(2200);
                    heart_speed[0] = 2200;
                }


                if (heart_speed[0] >= 500 && heart_speed[0] < 750) RPM = 85 + i1;
                else if (heart_speed[0] >= 0 && heart_speed[0] < 500) RPM = 90 + i1;
                else if (heart_speed[0] >= 750 && heart_speed[0] < 1000) RPM = 80 + i1;
                else if (heart_speed[0] >= 1000 && heart_speed[0] < 1200) RPM = 75 + i1;
                else if (heart_speed[0] >= 1200 && heart_speed[0] < 1500) RPM = 70 + i1;
                else if (heart_speed[0] >= 1500 && heart_speed[0] < 1800) RPM = 65 + i1;
                else if (heart_speed[0] >= 1800 && heart_speed[0] < 2000) RPM = 60 + i1;


                else RPM = 55;
                final Random r1 = new Random();
                final int i2 = (r1.nextInt(5) + 0);
                t2.setText(Integer.toString(RPM + i2));
            }
        });
        scaleYAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                int y;
                //Random r = new Random();

                //int i1 = (r.nextInt(10) + 0);
                if (heart_speed[0] >= 500 && heart_speed[0] < 750) RPM = 85 + i1;
                else if (heart_speed[0] >= 0 && heart_speed[0] < 500) RPM = 90 + i1;
                else if (heart_speed[0] >= 750 && heart_speed[0] < 1000) RPM = 80 + i1;
                else if (heart_speed[0] >= 1000 && heart_speed[0] < 1200) RPM = 75 + i1;
                else if (heart_speed[0] >= 1200 && heart_speed[0] < 1500) RPM = 70 + i1;
                else if (heart_speed[0] >= 1500 && heart_speed[0] < 1800) RPM = 65 + i1;
                else if (heart_speed[0] >= 1800 && heart_speed[0] < 2000) RPM = 60 + i1;


                else RPM = 55;
                //final Random r1 = new Random();
                //final int i2 = (r1.nextInt(3) + 0);
                t2.setText(Integer.toString(RPM));
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //t1.setText(Integer.toString(heart_speed[0]));
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                if (mProgressStatus[0] < 200 && mProgressStatus[0] > 80) {
//
//					scaleXAnimator.setDuration(2000);
                    scaleYAnimator.setDuration(1000);
                    heart_speed[0] = 1000;
//					t2.setText(Integer.toString(75));
                } else if (mProgressStatus[0] <= 80 && mProgressStatus[0] > 70) {

                    scaleYAnimator.setDuration(1200);
                    heart_speed[0] = 1200;


                } else if (mProgressStatus[0] <= 70 && mProgressStatus[0] > 60) {

                    scaleYAnimator.setDuration(1500);
                    heart_speed[0] = 1500;


                } else if (mProgressStatus[0] <= 60 && mProgressStatus[0] > 50) {

                    scaleYAnimator.setDuration(1800);
                    heart_speed[0] = 1800;


                } else if (mProgressStatus[0] <= 50 && mProgressStatus[0] > 30) {

                    scaleYAnimator.setDuration(1999);
                    heart_speed[0] = 1999;


                } else {

                    scaleYAnimator.setDuration(2200);
                    heart_speed[0] = 2200;
                }


                if (heart_speed[0] >= 500 && heart_speed[0] < 750) RPM = 85 + i1;
                else if (heart_speed[0] >= 0 && heart_speed[0] < 500) RPM = 90 + i1;
                else if (heart_speed[0] >= 750 && heart_speed[0] < 1000) RPM = 80 + i1;
                else if (heart_speed[0] >= 1000 && heart_speed[0] < 1200) RPM = 75 + i1;
                else if (heart_speed[0] >= 1200 && heart_speed[0] < 1
                        ) RPM = 70 + i1;
                else if (heart_speed[0] >= 1500 && heart_speed[0] < 1800) RPM = 65 + i1;
                else if (heart_speed[0] >= 1800 && heart_speed[0] < 2000) RPM = 60 + i1;


                else RPM = 55;
                final Random r1 = new Random();
                final int i2 = (r1.nextInt(5) + 0);
                t2.setText(Integer.toString(RPM + i2));
            }

        });*/


        addfunction();
        final TextView countertime = (TextView) findViewById(R.id.textView43);
       // t2.setTypeface(tf);
        t2.setTextSize(12);
       // countertime.setTypeface(tf);
        countertime.setTextSize(12);

        //final TextView scoretext = (TextView) findViewById(R.id.score);


        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.VISIBLE);
        //l3.setVisibility(View.VISIBLE);
        //t1.setVisibility(View.VISIBLE);
        //f3.setVisibility(View.VISIBLE);
        f3.setEnabled(true);
        g.setVisibility(View.INVISIBLE);



        iq=0;

        iq = shared.getInt("iq", iq);


        coin= shared.getInt("coin", coin);

       // scoretext.setTypeface(tf);

        iq = shared.getInt("iq", iq);
        final TextView cointext = (TextView) findViewById(R.id.textcoin);

        //cointext.setTypeface(tf);
        cointext.setTextSize(12);
      //  mAdView.loadAd(adRequest);

        final Button starimg = (Button) findViewById(R.id.imageView33);
        starimg.setTextColor(Color.WHITE);
        //starimg.setTypeface(tf);
        starimg.setTextSize(12);

        final int[] gamecounter={0};

        gamecounter[0] = gamestarcounter[0]%gamewaittime;

        gamecounter[0] = gamewaittime-gamecounter[0];

        if (gamecounter[0] % 60 < 10) countertime.setText(Integer.toString(gamecounter[0] / 60) + ":0" +Integer.toString( gamecounter[0]%60));
        else countertime.setText(Integer.toString(gamecounter[0]/60) + ":" +Integer.toString( gamecounter[0]%60));




        if(gamestar<=0) starimg.setText("0");
        else if(gamestar==1)starimg.setText("1");
        else if (gamestar == 2) starimg.setText("2");
        else if (gamestar == 3)starimg.setText("3");
        else if (gamestar == 4)starimg.setText("4");
        else if (gamestar == 5) starimg.setText("5");
        iq=0;
        TextView textiq = (TextView) findViewById(R.id.textiq);
        iq = shared.getInt("iq", iq);
        //textiq.setTypeface(tf);
        textiq.setTextSize(12);
        cointext.setText(Integer.toString(coin));



        vibration = shared.getBoolean("vibration", vibration);
        highest = shared.getInt("colordecephighest", highest);
        lowest = shared.getInt("colordeceplowest", lowest);
        scorecounter = shared.getInt("scorecounter", scorecounter);
        final CircularProgressBar timecircular = (CircularProgressBar) findViewById(R.id.circularprogressbar2);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("gamestop", false);
        if (gamestar >= MAXGAMEPLAY    ) countertime.setVisibility(View.INVISIBLE);
        else countertime.setVisibility(View.VISIBLE);

        waitTimer =  new CountDownTimer(1000000000, 1000) {
            public void onTick(long milsec) {

                Random r = new Random();
                randomi3 = (r.nextInt(3) + 0);
                if (gamestar >= MAXGAMEPLAY    ) countertime.setVisibility(View.INVISIBLE);
                else countertime.setVisibility(View.VISIBLE);

                gamecounter[0]--;
                gamestarcounter[0]++;
                int gcounter=gamestarcounter[0];
                long time2= System.currentTimeMillis();
                time2 = time2/1000; // in minutes
                editor.putLong("time2", time2);
                editor.putInt("gamestarcounter", gcounter);
                editor.commit();
                if(gamecounter[0]%60 <10) countertime.setText(Integer.toString(gamecounter[0]/60) + ":0" +Integer.toString( gamecounter[0]%60));
                else countertime.setText(Integer.toString(gamecounter[0]/60) + ":" +Integer.toString( gamecounter[0]%60));
                if (gamestarcounter[0] % gamewaittime == 0) {

                    gamecounter[0]=gamewaittime;
                    if(gamecounter[0]%60 <10) countertime.setText(Integer.toString(gamecounter[0]/60) + ":0" +Integer.toString( gamecounter[0]%60));
                    else countertime.setText(Integer.toString(gamecounter[0]/60) + ":" +Integer.toString( gamecounter[0]%60));
                    if (gamestar < MAXGAMEPLAY) {
                        gamestar++;
                        if(gamestar<=0) starimg.setText("0");
                        else if(gamestar==1)starimg.setText("1");
                        else if (gamestar == 2) starimg.setText("2");
                        else if (gamestar == 3)starimg.setText("3");
                        else if (gamestar == 4)starimg.setText("4");
                        else if (gamestar == 5) starimg.setText("5");
                    }

                    editor.putInt("gamestar", gamestar);
                   // gcounter=gamestarcounter[0];
                    //editor.putInt("gamestarcounter", gcounter);
                    long time= System.currentTimeMillis();
                    time = time/1000; // in minutes
                    editor.putLong("time2", time);
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

        final Button Bstart = (Button) findViewById(R.id.button31);
        final RelativeLayout Startlayout = (RelativeLayout) findViewById(R.id.StartRelLayout);
        final RelativeLayout buttonframe = (RelativeLayout) findViewById(R.id.linearLayout2);
       // final FrameLayout textfram = (FrameLayout) findViewById(R.id.textfram);

        timecircular.setMax(24000);

        textiq.setText(Integer.toString(iq));

        final ProgressBar pr1 =(ProgressBar) findViewById(R.id.progressBar2);



        final  ImageView brain3 =( ImageView) findViewById(R.id.testImagebrain);

        brain3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        brain3.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if(isOnline()) {
                            if (!mGoogleApiClient.isConnected()) {

                                //googlesigninbtnflg = true;
                                if (!BaseGameUtils.verifySampleSetup(ColorDeception.this, R.string.app_id)) {
                                    Log.w(TAG, "*** Warning: setup problems detected. Sign in may not work!");
                                }

                                // start the sign-in flow
                                Log.d(TAG, "Sign-in button clicked");
                                mSignInClicked = true;
                                mGoogleApiClient.connect();
                                //mainsignin = true;
                                //editor.putBoolean("mainsignin", mainsignin);
                                //editor.commit();
                            } else {
                                Player p = Games.Players.getCurrentPlayer(mGoogleApiClient);


                                if (mGoogleApiClient.isConnected())
                                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                                            getString(R.string.leaderboard_id)), REQUEST_LEADERBOARD);


                                else {
                                    mGoogleApiClient.disconnect();

                                    //googlesigninbtnflg=true;
                                    if (!BaseGameUtils.verifySampleSetup(ColorDeception.this, R.string.app_id)) {
                                        Log.w(TAG, "*** Warning: setup problems detected. Sign in may not work!");
                                    }

                                    // start the sign-in flow
                                    Log.d(TAG, "Sign-in button clicked");
                                    mSignInClicked = true;
                                    mGoogleApiClient.connect();
                                    // mainsignin = true;
                                    // editor.putBoolean("mainsignin", mainsignin);
                                    // editor.commit();
                                }

                            }

                        }
                        else alertbox();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        brain3.setAlpha(1f);
                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        final  TextView brain =( TextView) findViewById(R.id.textiq);


        final ImageView coins = (ImageView) findViewById(R.id.testImagecoin);


        coins.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        coins.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        Button unlockiqtext = (Button) findViewById(R.id.unlockiqbutton);
                        Button plu5gametext = (Button) findViewById(R.id.plusgamebutton);
                        Button noadtext = (Button) findViewById(R.id.coin10button);
                        Button coin2000text = (Button) findViewById(R.id.coin100button);
                        Button coin5000text = (Button) findViewById(R.id.coin500button);
                        Button coin10000text = (Button) findViewById(R.id.coin1000button);
                        Button mult2text = (Button) findViewById(R.id.mult2button);


                        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);

                        String unlockiqskun = "3,99 USD";
                        String plus5gameskun = "3,99 USD";
                        String noaddskun = "3,49 USD" ;
                        String coin200skun = "0.99 USD";
                        String coin500skun = "1.99 USD";
                        String coin1000skun = "3.49 USD";
                        String mult2skun = "1.5 USD";

                        unlockiqskun = shared.getString("unlockiqskun", unlockiqskun);
                        plus5gameskun = shared.getString("plus5gameskun", plus5gameskun);
                        noaddskun = shared.getString("noaddskun", noaddskun);
                        coin200skun = shared.getString("coin200skun", coin200skun);
                        coin500skun = shared.getString("coin500skun", coin500skun);
                        coin1000skun = shared.getString("coin1000skun", coin1000skun);
                        mult2skun = shared.getString("mult2skun", mult2skun);
                        Log.d("taggy",unlockiqskun );

                        unlockiqtext.setText(unlockiqskun);
                        plu5gametext.setText(plus5gameskun);
                        noadtext.setText(noaddskun);
                        coin2000text.setText(coin200skun);
                        coin5000text.setText(coin500skun );
                        coin10000text.setText(coin1000skun );
                        mult2text.setText(mult2skun );

                        coinshopfunction();

                        coins.setEnabled(false);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        coins.setAlpha(1f);
                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        brain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        brain.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        if(isOnline()) {
                            if (!mGoogleApiClient.isConnected()) {

                                //googlesigninbtnflg = true;
                                if (!BaseGameUtils.verifySampleSetup(ColorDeception.this, R.string.app_id)) {
                                    Log.w(TAG, "*** Warning: setup problems detected. Sign in may not work!");
                                }

                                // start the sign-in flow
                                Log.d(TAG, "Sign-in button clicked");
                                mSignInClicked = true;
                                mGoogleApiClient.connect();
                                //mainsignin = true;
                                //editor.putBoolean("mainsignin", mainsignin);
                                //editor.commit();
                            } else {
                                Player p = Games.Players.getCurrentPlayer(mGoogleApiClient);


                                if (mGoogleApiClient.isConnected())
                                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                                            getString(R.string.leaderboard_id)), REQUEST_LEADERBOARD);


                                else {
                                    mGoogleApiClient.disconnect();

                                    //googlesigninbtnflg=true;
                                    if (!BaseGameUtils.verifySampleSetup(ColorDeception.this, R.string.app_id)) {
                                        Log.w(TAG, "*** Warning: setup problems detected. Sign in may not work!");
                                    }

                                    // start the sign-in flow
                                    Log.d(TAG, "Sign-in button clicked");
                                    mSignInClicked = true;
                                    mGoogleApiClient.connect();
                                    // mainsignin = true;
                                    // editor.putBoolean("mainsignin", mainsignin);
                                    // editor.commit();
                                }

                            }

                        }
                        else alertbox();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        brain.setAlpha(1f);
                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        Bstart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstart.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (gamestar >= MAXGAMEPLAY)
                        {
                            gamecounter[0] = gamewaittime;
                            gamestarcounter[0]=0;
                            boolean sifirla = true;
                            editor.putBoolean("sifirla", sifirla);
                            editor.commit();
                            int gcounter = gamestarcounter[0];
                            editor.putInt("gamestarcounter", gcounter);
                            editor.commit();
                            //countertime.setVisibility(View.VISIBLE);
                        }

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        brain3.setEnabled(false);
                        finishgame=false;
                        brain3.setEnabled(false);
                        coins.setEnabled(false);
                        gamestar--;
                        editor.putInt("gamestar",gamestar);
                        editor.commit();
                        if(gamestar<=0)starimg.setText("0");
                        else if(gamestar==1)starimg.setText("1");
                        else if(gamestar==2)starimg.setText("2");
                        else if (gamestar == 3)starimg.setText("3");
                        else if (gamestar == 4)starimg.setText("4");
                        else if (gamestar == 5) starimg.setText("5");

                        Bstart.setVisibility(View.INVISIBLE);
                        Startlayout.setVisibility(View.INVISIBLE);
                        buttonframe.setVisibility(View.VISIBLE);
                       // textfram.setVisibility(View.VISIBLE);
                        timecircular.setVisibility(View.VISIBLE);

                        new CountDownTimer(24000, 10) {
                            public void onTick(long milsec) {
                                time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                int progress = (int) milsec;
                                timecircular.setProgress((int) milsec);
                                timecircular.setProgressColor(Color.argb(255, 25, 189, 155));
                                timecircular.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {

                                //g.setVisibility(View.VISIBLE);
                                pr1.setProgress(0);
                                finishgame=true;
                                final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                                //final TextView t1 = (TextView) findViewById(R.id.textView);
                                final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                                final RelativeLayout f4 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                                f4.setVisibility(View.INVISIBLE);
                                l3.setVisibility(View.INVISIBLE);
                                f3.setVisibility(View.INVISIBLE);
                                //Time.setText(Long.toString(0));

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                showZaire();

                                new CountDownTimer(300, 100) {

                                    public void onTick(long millisUntilFinished) {

                                    }

                                    public void onFinish() {
                                       if(adcount % 3 == 0 && !adflag)  showad();
                                    }

                                }.start();



                            }
                        }.start();
                        start();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstart.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        final Button back = (Button) findViewById(R.id.Back);


        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        back.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        editor.putInt("gamestar", gamestar);
                        int  gcounter=gamestarcounter[0];
                        editor.putInt("gamestarcounter", gcounter);
                        backtooption = true;
                        back();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        back.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void start(){

        String GREEN = getString(R.string.GREEN);
        String PINK = getString(R.string.PINK);
        String RED = getString(R.string.RED);
        String BLUE = getString(R.string.BLUE);
        String WHITE = getString(R.string.WHITE);
        String YELLOW = getString(R.string.YELLOW);
        String PURPLE = getString(R.string.PURPLE);
        String ORANGE = getString(R.string.ORANGE);




        if (gamelang == ENGLISH) {
            GREEN = "GREEN";
            PINK = "PINK";
            RED = "RED";
            BLUE = "BLUE";
            WHITE = "WHITE";
            YELLOW = "YELLOW";
            PURPLE = "PURPLE";
            ORANGE = "ORANGE";
        }



        else if (gamelang == TURKISH)
        {
            GREEN = "YEŞİL";
            PINK = "PEMBE";
            RED = "KIRMIZI";
            BLUE = "MAVİ";
            WHITE = "BEYAZ";
            YELLOW = "SARI";
            PURPLE = "MOR";
            ORANGE = "TURUNCU";
        }



        else if (gamelang == SPANISH)  {
            GREEN = "VERDE";
            PINK = "ROSADO";
            RED = "ROJO";
            BLUE = "AZUL";
            WHITE = "BALNCO";
            YELLOW = "AMARILLO";
            PURPLE = "PÚRPURA";
            ORANGE = "NARANJA";
        }


        else if (gamelang == FRENCH)  {
            GREEN = "VERT";
            PINK = "ROSE";
            RED = "ROUGE";
            BLUE = "BLEU";
            WHITE = "BLANC";
            YELLOW = "JAUNE";
            PURPLE = "VIOLET";
            ORANGE = "ORANGE";
        }

        if (gamelang == RUSSIAN) {
            GREEN = "ЗЕЛЕНЫЙ";
            PINK = "розовый";
            RED = "красный";
            BLUE = "СИНИЙ";
            WHITE = "белый";
            YELLOW = "желтый";
            PURPLE = "пурпурный";
            ORANGE = "оранжевый";
        }

        if (gamelang == CHINESE) {
            GREEN = "綠色";
            PINK = "粉";
            RED = "紅";
            BLUE = "藍色";
            WHITE = "白色";
            YELLOW = "黃色";
            PURPLE = "紫色";
            ORANGE = "橙子";
        }

        if (gamelang == JAPANESE) {
            GREEN = "緑";
            PINK = "ピンク";
            RED = "赤\n";
            BLUE = "青";
            WHITE = "白";
            YELLOW = "黄";
            PURPLE = "紫の";
            ORANGE = "オレンジ";
        }
        if (gamelang == PORTUGUESE) {
            GREEN = "VERDE";
            PINK = "ROSA";
            RED = "VERMELHO";
            BLUE = "AZUL";
            WHITE = "BRANCO";
            YELLOW = "AMARELO";
            PURPLE = "ROXA";
            ORANGE = "LARANJA";
        }



        String colortext[] = {GREEN,PINK,RED,BLUE,WHITE,YELLOW,PURPLE,ORANGE};
        final Button b1 = (Button) findViewById(R.id.choice1);
        final Button b2 = (Button) findViewById(R.id.choice2);

        if(finishgame==true)
        {
            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
            //final TextView t1 = (TextView) findViewById(R.id.textView);
            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);

        }


        //b1.setTypeface(tf);
        //b1.setTextSize(30);
        //b2.setTypeface(tf);
        //b2.setTextSize(30);


        barray[0]=b1;
        barray[1]=b2;
        Random r = new Random();
        Random r1 = new Random();
        Random r2 = new Random();
        int min = 0;
        int max = 7;
        int counter = r.nextInt((max - min) + 1) + min;
        int counterwrong = r1.nextInt((max - min) + 1) + min;
        int counterwrongtext = r2.nextInt((max - min) + 1) + min;
        while(counter==counterwrong | counter == counterwrongtext | counterwrong == counterwrongtext)


        {

            Random r11 = new Random();
            Random r12 = new Random();
            Random r13 = new Random();
            min = 0;
            max = 7;
            counter = r11.nextInt((max - min) + 1) + min;
            counterwrong = r12.nextInt((max - min) + 1) + min;
            counterwrongtext=r13.nextInt((max - min) + 1) + min;
        }

        Random r111 = new Random();
        randomcounter=r111.nextInt((1 - 0) + 1) + 0;

        Button other;

        if (randomcounter ==0) other=b2;
        else other =b1;


        barray[randomcounter].setBackgroundColor(colorarray[counter]);
        barray[randomcounter].setText(colortext[counter]);

        if (colorarray[counter]== Color.WHITE | colorarray[counter]== Color.YELLOW)
        {
            barray[randomcounter].setTextColor(Color.BLACK);
        }


        else  barray[randomcounter].setTextColor(Color.WHITE);

         boolean blocked = false;
       final  Handler handler = new Handler();
        other.setBackgroundColor(colorarray[counterwrong]);
        other.setText(colortext[counterwrongtext]);

        if (colorarray[counterwrong]== Color.WHITE | colorarray[counterwrong]== Color.YELLOW)
        {
            other.setTextColor(Color.BLACK);
        }
        else  other.setTextColor(Color.WHITE);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);
        b1.setEnabled(true);
        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (randomcounter == 1) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                             score = score + 50;


                        } else {
                            vibrate(150);
                            wrong++;
                            //b1.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }

                      //  final TextView scoretext = (TextView) findViewById(R.id.score);

                        tries++;
                        b1.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries < 3) {

                                    start();

                                } else {
                                    level++;
                                    start3();
                                }
                            }
                        }, delay); //10sec delay


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }


                }
                return true;
            }
        });
        b2.setEnabled(true);
        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 0) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            if (level == 1)
                                score = score + 50;
                            else score = score + 50;


                        } else {
                            vibrate(150);
                            wrong++;
                            //b2.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }
                       // final TextView scoretext = (TextView) findViewById(R.id.score);
                       // scoretext.setText(Integer.toString(score));
                        tries++;

                        b2.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries < 3) {

                                    start();

                                } else {
                                    level++;
                                    start3();
                                }
                            }
                        }, delay); //10sec delay

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }


                }
                return true;
            }
        });




    }


    void start4()
    {
        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
        //final TextView t1 = (TextView) findViewById(R.id.textView);
        final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
        RelativeLayout button2layout = (RelativeLayout) findViewById(R.id.linearLayout2);
        RelativeLayout frame4 = (RelativeLayout) findViewById(R.id.Buttonframe4);
        FrameLayout button3layout = (FrameLayout) findViewById(R.id.buttonframe3);
        String GREEN = getString(R.string.GREEN);
        String PINK = getString(R.string.PINK);
        String RED = getString(R.string.RED);
        String BLUE = getString(R.string.BLUE);
        String WHITE = getString(R.string.WHITE);
        String YELLOW = getString(R.string.YELLOW);
        String PURPLE = getString(R.string.PURPLE);
        String ORANGE = getString(R.string.ORANGE);


        if (gamelang == ENGLISH) {
            GREEN = "GREEN";
            PINK = "PINK";
            RED = "RED";
            BLUE = "BLUE";
            WHITE = "WHITE";
            YELLOW = "YELLOW";
            PURPLE = "PURPLE";
            ORANGE = "ORANGE";
        }



        else if (gamelang == TURKISH)
        {
            GREEN = "YEŞİL";
            PINK = "PEMBE";
            RED = "KIRMIZI";
            BLUE = "MAVİ";
            WHITE = "BEYAZ";
            YELLOW = "SARI";
            PURPLE = "MOR";
            ORANGE = "TURUNCU";
        }



        else if (gamelang == SPANISH)  {
            GREEN = "VERDE";
            PINK = "ROSADO";
            RED = "ROJO";
            BLUE = "AZUL";
            WHITE = "BALNCO";
            YELLOW = "AMARILLO";
            PURPLE = "PÚRPURA";
            ORANGE = "NARANJA";
        }


        else if (gamelang == FRENCH)  {
            GREEN = "VERT";
            PINK = "ROSE";
            RED = "ROUGE";
            BLUE = "BLEU";
            WHITE = "BLANC";
            YELLOW = "JAUNE";
            PURPLE = "VIOLET";
            ORANGE = "ORANGE";
        }

        if (gamelang == RUSSIAN) {
            GREEN = "ЗЕЛЕНЫЙ";
            PINK = "розовый";
            RED = "красный";
            BLUE = "СИНИЙ";
            WHITE = "белый";
            YELLOW = "желтый";
            PURPLE = "пурпурный";
            ORANGE = "оранжевый";
        }

        if (gamelang == CHINESE) {
            GREEN = "綠色";
            PINK = "粉";
            RED = "紅";
            BLUE = "藍色";
            WHITE = "白色";
            YELLOW = "黃色";
            PURPLE = "紫色";
            ORANGE = "橙子";
        }

        if (gamelang == JAPANESE) {
            GREEN = "緑";
            PINK = "ピンク";
            RED = "赤\n";
            BLUE = "青";
            WHITE = "白";
            YELLOW = "黄";
            PURPLE = "紫の";
            ORANGE = "オレンジ";
        }
        if (gamelang == PORTUGUESE) {
            GREEN = "VERDE";
            PINK = "ROSA";
            RED = "VERMELHO";
            BLUE = "AZUL";
            WHITE = "BRANCO";
            YELLOW = "AMARELO";
            PURPLE = "ROXA";
            ORANGE = "LARANJA";
        }



        String colortext[] = {GREEN,PINK,RED,BLUE,WHITE,YELLOW,PURPLE,ORANGE};
        if(finishgame==true)
        {

            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);
            frame4.setVisibility(View.INVISIBLE);

        }

        else {

            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);
            frame4.setVisibility(View.VISIBLE);
        }

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);

        final Button b1 = (Button) findViewById(R.id.btnselect1);
        final Button b2 = (Button) findViewById(R.id.btnselect2);
        final Button b3 = (Button) findViewById(R.id.btnselect3);
        final Button b4 = (Button) findViewById(R.id.btnselect4);


       /* b1.setTypeface(tf);
        b1.setTextSize(30);
        b2.setTypeface(tf);
        b2.setTextSize(30);
        b3.setTypeface(tf);
        b3.setTextSize(30);

        b4.setTypeface(tf);
        b4.setTextSize(30);*/

        Random r = new Random();


        int min = 0;
        int max = 3;
        int x11 = r.nextInt((max - min) + 1) + min;

        final int[] counter={x11};
        if(counter[0]==0){

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);






            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1)-1]);
            b3.setBackgroundColor(colorarray[number.get(2)-1]);
            b4.setBackgroundColor(colorarray[number.get(3)-1]);


            if (colorarray[number.get(0) - 1]== Color.WHITE | colorarray[number.get(0) - 1]== Color.YELLOW)
            {
                b1.setTextColor(Color.BLACK);
            }


            else  b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1]== Color.WHITE | colorarray[number.get(1) - 1]== Color.YELLOW)
            {
                b2.setTextColor(Color.BLACK);
            }


            else  b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1]== Color.WHITE | colorarray[number.get(2) - 1]== Color.YELLOW)
            {
                b3.setTextColor(Color.BLACK);
            }


            else  b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1]== Color.WHITE | colorarray[number.get(3) - 1]== Color.YELLOW)
            {
                b4.setTextColor(Color.BLACK);
            }


            else  b4.setTextColor(Color.WHITE);




            b1.setText(colortext[number.get(7) - 1]);
            b2.setText(colortext[number.get(1) - 1]);
            b3.setText(colortext[number.get(2) - 1]);
            b4.setText(colortext[number.get(3) - 1]);


        }

        else if(counter[0]==1){


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);

            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1)-1]);
            b3.setBackgroundColor(colorarray[number.get(2)-1]);
            b4.setBackgroundColor(colorarray[number.get(3)-1]);

            b1.setText(colortext[number.get(0) - 1]);
            b2.setText(colortext[number.get(7) - 1]);
            b3.setText(colortext[number.get(2) - 1]);
            b4.setText(colortext[number.get(3) - 1]);

            if (colorarray[number.get(0) - 1]== Color.WHITE | colorarray[number.get(0) - 1]== Color.YELLOW)
            {
                b1.setTextColor(Color.BLACK);
            }


            else  b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1]== Color.WHITE | colorarray[number.get(1) - 1]== Color.YELLOW)
            {
                b2.setTextColor(Color.BLACK);
            }


            else  b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1]== Color.WHITE | colorarray[number.get(2) - 1]== Color.YELLOW)
            {
                b3.setTextColor(Color.BLACK);
            }


            else  b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1]== Color.WHITE | colorarray[number.get(3) - 1]== Color.YELLOW)
            {
                b4.setTextColor(Color.BLACK);
            }


            else  b4.setTextColor(Color.WHITE);

        }

        else if(counter[0]==2){


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);

            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1)-1]);
            b3.setBackgroundColor(colorarray[number.get(2)-1]);
            b4.setBackgroundColor(colorarray[number.get(3)-1]);

            b1.setText(colortext[number.get(0) - 1]);
            b2.setText(colortext[number.get(1) - 1]);
            b3.setText(colortext[number.get(7) - 1]);
            b4.setText(colortext[number.get(3) - 1]);

            if (colorarray[number.get(0) - 1]== Color.WHITE | colorarray[number.get(0) - 1]== Color.YELLOW)
            {
                b1.setTextColor(Color.BLACK);
            }


            else  b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1]== Color.WHITE | colorarray[number.get(1) - 1]== Color.YELLOW)
            {
                b2.setTextColor(Color.BLACK);
            }


            else  b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1]== Color.WHITE | colorarray[number.get(2) - 1]== Color.YELLOW)
            {
                b3.setTextColor(Color.BLACK);
            }


            else  b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1]== Color.WHITE | colorarray[number.get(3) - 1]== Color.YELLOW)
            {
                b4.setTextColor(Color.BLACK);
            }


            else  b4.setTextColor(Color.WHITE);

        }


        else {

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);

            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1) - 1]);
            b3.setBackgroundColor(colorarray[number.get(2) - 1]);
            b4.setBackgroundColor(colorarray[number.get(3) - 1]);

            b1.setText(colortext[number.get(0) - 1]);
            b2.setText(colortext[number.get(1) - 1]);
            b3.setText(colortext[number.get(2) - 1]);
            b4.setText(colortext[number.get(7) - 1]);


            if (colorarray[number.get(0) - 1]== Color.WHITE | colorarray[number.get(0) - 1]== Color.YELLOW)
            {
                b1.setTextColor(Color.BLACK);
            }


            else  b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1]== Color.WHITE | colorarray[number.get(1) - 1]== Color.YELLOW)
            {
                b2.setTextColor(Color.BLACK);
            }


            else  b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1]== Color.WHITE | colorarray[number.get(2) - 1]== Color.YELLOW)
            {
                b3.setTextColor(Color.BLACK);
            }


            else  b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1]== Color.WHITE | colorarray[number.get(3) - 1]== Color.YELLOW)
            {
                b4.setTextColor(Color.BLACK);
            }


            else  b4.setTextColor(Color.WHITE);


        }
        final  Handler handler = new Handler();
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);




        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if(finishgame==true)
                        {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0]==0)
                        {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score=score+50;
                        }

                        else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }

                        b1.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 1) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }
                        b2.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 2) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }
                        b3.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        b4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b4.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 3) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }
                        b4.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b4.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


    }


    void start3(){

        String GREEN = getString(R.string.GREEN);
        String PINK = getString(R.string.PINK);
        String RED = getString(R.string.RED);
        String BLUE = getString(R.string.BLUE);
        String WHITE = getString(R.string.WHITE);
        String YELLOW = getString(R.string.YELLOW);
        String PURPLE = getString(R.string.PURPLE);
        String ORANGE = getString(R.string.ORANGE);


        if (gamelang == ENGLISH) {
            GREEN = "GREEN";
            PINK = "PINK";
            RED = "RED";
            BLUE = "BLUE";
            WHITE = "WHITE";
            YELLOW = "YELLOW";
            PURPLE = "PURPLE";
            ORANGE = "ORANGE";
        }



        else if (gamelang == TURKISH)
        {
            GREEN = "YEŞİL";
            PINK = "PEMBE";
            RED = "KIRMIZI";
            BLUE = "MAVİ";
            WHITE = "BEYAZ";
            YELLOW = "SARI";
            PURPLE = "MOR";
            ORANGE = "TURUNCU";
        }



        else if (gamelang == SPANISH)  {
            GREEN = "VERDE";
            PINK = "ROSADO";
            RED = "ROJO";
            BLUE = "AZUL";
            WHITE = "BLANCO";
            YELLOW = "AMARILLO";
            PURPLE = "PÚRPURA";
            ORANGE = "NARANJA";
        }


        else if (gamelang == FRENCH)  {
            GREEN = "VERT";
            PINK = "ROSE";
            RED = "ROUGE";
            BLUE = "BLEU";
            WHITE = "BLANC";
            YELLOW = "JAUNE";
            PURPLE = "VIOLET";
            ORANGE = "ORANGE";
        }

        if (gamelang == RUSSIAN) {
            GREEN = "ЗЕЛЕНЫЙ";
            PINK = "РОЗОВЫЙ";
            RED = "КРАСНЫЙ";
            BLUE = "СИНИЙ";
            WHITE = "БЕЛЫЙ";
            YELLOW = "ЖЕЛТЫЙ";
            PURPLE = "ПУРПУРНЫЙ";
            ORANGE = "ОРАНЖЕВЫЙ";
        }

        if (gamelang == CHINESE) {
            GREEN = "綠色";
            PINK = "粉";
            RED = "紅";
            BLUE = "藍色";
            WHITE = "白色";
            YELLOW = "黃色";
            PURPLE = "紫色";
            ORANGE = "橙子";
        }

        if (gamelang == JAPANESE) {
            GREEN = "緑";
            PINK = "ピンク";
            RED = "赤";
            BLUE = "青";
            WHITE = "白";
            YELLOW = "黄";
            PURPLE = "紫の";
            ORANGE = "オレンジ";
        }
        if (gamelang == PORTUGUESE) {
            GREEN = "VERDE";
            PINK = "ROSA";
            RED = "VERMELHO";
            BLUE = "AZUL";
            WHITE = "BRANCO";
            YELLOW = "AMARELO";
            PURPLE = "ROXA";
            ORANGE = "LARANJA";
        }

        String colortext[] = {GREEN,PINK,RED,BLUE,WHITE,YELLOW,PURPLE,ORANGE};
        RelativeLayout button2layout = (RelativeLayout) findViewById(R.id.linearLayout2);
        FrameLayout button3layout = (FrameLayout) findViewById(R.id.buttonframe3);

        if(finishgame==true)
        {
            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
            //final TextView t1 = (TextView) findViewById(R.id.textView);
            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);

        }

        else {


            button2layout.setVisibility(View.INVISIBLE);
            button3layout.setVisibility(View.VISIBLE);
        }


       // final TextView scoretext = (TextView) findViewById(R.id.score);
       // scoretext.setText(Integer.toString(score));



        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);
        final Button b1 = (Button) findViewById(R.id.choice11);
        final Button b2 = (Button) findViewById(R.id.choice21);
        final Button b3 = (Button) findViewById(R.id.choice3);

      /*  b1.setTypeface(tf);
        b1.setTextSize(30);
        b2.setTypeface(tf);
        b2.setTextSize(30);
        b3.setTypeface(tf);
        b3.setTextSize(30);*/

        boolean blocked = false;
        final  Handler handler = new Handler();

        barray2[0]=b1;
        barray2[1]=b2;
        barray2[2]=b3;

        Random r = new Random();
        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();

        int min = 0;
        int max = 7;
        int counter = r.nextInt((max - min) + 1) + min;
        int counterwrong = r1.nextInt((max - min) + 1) + min;
        int counterwrongtext = r2.nextInt((max - min) + 1) + min;
        int counter2 = r3.nextInt((max - min) + 1) + min;



        while(counter==counterwrong | counter == counterwrongtext | counterwrong == counterwrongtext | counter2==counter | counter2==counterwrongtext| counter2== counterwrong)


        {

            Random r11 = new Random();
            Random r12 = new Random();
            Random r13 = new Random();
            Random r14 = new Random();
            min = 0;
            max = 7;
            counter = r11.nextInt((max - min) + 1) + min;
            counterwrong = r12.nextInt((max - min) + 1) + min;
            counterwrongtext=r13.nextInt((max - min) + 1) + min;
            counter2 = r14.nextInt((max - min) + 1) + min;
        }

        Random r111 = new Random();
        randomcounter=r111.nextInt((2 - 0) + 1) + 0;

        Button other;
        Button real2;

        if (randomcounter ==0)

        {
            other = b3;
            real2= b2;
        }

        else if (randomcounter==1)

        {
            other = b1;
            real2 = b3;
        }


        else {
            other =b2;
            real2 =b1;
        };



        barray2[randomcounter].setBackgroundColor(colorarray[counter]);
        barray2[randomcounter].setText(colortext[counter]);

        if (colorarray[counter]== Color.WHITE | colorarray[counter]== Color.YELLOW)
        {
            barray2[randomcounter].setTextColor(Color.BLACK);
        }


        else  barray2[randomcounter].setTextColor(Color.WHITE);





        other.setBackgroundColor(colorarray[counterwrong]);
        other.setText(colortext[counterwrongtext]);

        if (colorarray[counterwrong]== Color.WHITE | colorarray[counterwrong]== Color.YELLOW)
        {
            other.setTextColor(Color.BLACK);
        }
        else  other.setTextColor(Color.WHITE);


        real2.setBackgroundColor(colorarray[counter2]);
        real2.setText(colortext[counter2]);

        if (colorarray[counter2]== Color.WHITE | colorarray[counter2]== Color.YELLOW)
        {
            real2.setTextColor(Color.BLACK);
        }
        else  real2.setTextColor(Color.WHITE);


        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if(finishgame==true)
                        {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 1) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                                score = score + 50;



                        } else {

                            wrong++;
                            vibrate(150);
                            //b1.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }

                        tries++;
                        b1.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                if (tries <= 15 & tries>3) {

                                    start3();

                                } else {
                                    level++;
                                    start4();
                                }
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if(finishgame==true)
                        {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 2) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                                score = score + 50;



                        } else {
                            wrong++;
                            vibrate(150);
                            //b2.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }

                        tries++;
                        b2.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries <= 15 & tries>3) {

                                    start3();

                                } else {
                                    level++;
                                    start4();
                                }
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if(finishgame==true)
                        {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 0)  {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(!mutestate) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score= score +50;


                        }
                        else {
                            wrong++;
                            //b3.startAnimation(shake);
                            vibrate(150);
                            score = score - 10;
                            if(score<=0)  score =0;

                        }
                        tries++;
                        b3.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries <= 15 & tries>3) {


                                    start3();

                                } else {
                                    level++;
                                    start4();
                                }
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });




    }

    public void onDestroy()

    {
        if (moPubView != null) {

            moPubView.destroy();}
        mobInterstitial.destroy();
        mobInterstitial2.destroy();


        super.onDestroy();

        if (bp != null)
            bp.release();


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int gcounter=gamestarcounter[0];
        editor.putInt("gamestarcounter", gcounter);
        editor.putInt("iq",iq);
       // editor.putBoolean("zerogamestar",zerogamestar);
        editor.putInt("gamestar",gamestar);
        editor.commit();


    }

    public void onBackPressed() {
        //Display alert message when back button has been pressed
        //moveTaskToBack(true);

        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
        backtooption = true;
        back();

    }

    public void showZaire() {
        Thread thread;
        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1);
        final RelativeLayout l1 = (RelativeLayout) findViewById(R.id.linearLayout);
        final LinearLayout l2 = (LinearLayout) findViewById(R.id.lin2);
        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
        //final TextView t1 = (TextView) findViewById(R.id.textView);
        final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);


        final ProgressBar prbar = (ProgressBar )findViewById(R.id.progressBar2);
        final TextView lowesttext = (TextView) findViewById(R.id.coinl);
        final TextView highesttext = (TextView) findViewById(R.id.highest);
        final TextView high=(TextView) findViewById(R.id.High);
        final TextView scoret=(TextView) findViewById(R.id.Result);

        final TextView correctnumtext = (TextView) findViewById(R.id.correct);
        final TextView incorrectnumtext = (TextView) findViewById(R.id.Incorrect);
        final TextView Accuracy = (TextView) findViewById(R.id.accuracy);
        final TextView scorecountertext = (TextView) findViewById(R.id.scorecountertext);
        final CircularProgressBar2 c1 = (CircularProgressBar2) findViewById(R.id.circularprogressbar);
        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
        final Button retry = (Button) findViewById(R.id.retry);
        final Button back = (Button) findViewById(R.id.Back);
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.progressBar3);
        final TextView coingift = (TextView) findViewById(R.id.cointext);
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();




        l3.setVisibility(View.INVISIBLE);
        f3.setVisibility(View.INVISIBLE);
        scorecounter = scorecounter+score ;
        if(score < lowest | lowest ==0 ) lowest = score ;
        if(score  > highest) highest = score;

        retry.setVisibility(View.VISIBLE);
        c2.setVisibility(View.INVISIBLE);

        if(iq < 5) maxiqscore = 5000;
        int coingain = 0;
        if (!mult2flag) coingain =score/100;

        else coingain =score/50;

        if (scorecounter>=maxiqscore) {
            editor.putBoolean("iqplusone", true);
            iq++;
            editor.putInt("iq",iq);

            coin=coin+50+coingain;
            editor.putInt("coin",coin);
            editor.putInt("gamecoin",coingain);
            coingift.setText(Integer.toString(50 + coingain));
            editor.commit();


        }
        else {
            coin=coin+coingain;
            editor.putInt("coin",coin);

            editor.putBoolean("iqplusone", false);
            coingift.setText(Integer.toString(coingain));
            editor.commit();

        }
        editor.commit();
        // else editor.putBoolean("iqplusone", false);

        int x = score;
        Intent i = new Intent();
        //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.putExtra("simscore", x);
        i.putExtra("iq", iq);

        setResult(Activity.RESULT_OK, i);
        //Toast toast = Toast.makeText(getApplicationContext(), "Time is Up",Toast.LENGTH_SHORT);
        //toast.show();
        prbar.setVisibility(View.INVISIBLE);

        l2.setVisibility(View.INVISIBLE);
        l3.setVisibility(View.INVISIBLE);
       // t1.setVisibility(View.INVISIBLE);
        f3.setVisibility(View.INVISIBLE);
        g.setVisibility(View.VISIBLE);


        editor.putInt("memhighest", highest);
        editor.putInt("memlowest", lowest);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();

        final FrameLayout uplevelframe = (FrameLayout) findViewById(R.id.uplevellayout);

        //lowesttext.setText("Highest Score: " + Integer.toString(highest));
        //highesttext.setText("Current Score: " +Integer.toString(gamescore[0]));
        correctnumtext.setText(Integer.toString(correctnum));
        incorrectnumtext.setText(Integer.toString(wrong));



        high.setText(Integer.toString(highest));
        //scoret.setText(Integer.toString(score));


        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, score);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                scoret.setText("" + (int) animation.getAnimatedValue());


            }

        });
        animator.start();





        double res = (double)scorecounter/maxiqscore*100;
        int res2 = (int) res;

        p2.setProgress(res2);
        p2.getProgressDrawable().setColorFilter(Color.rgb(25, 189, 155), PorterDuff.Mode.SRC_IN);
        p2.setScaleY(5f);


        // double accur = ((double)correctnum /(double) (correctnum + wrong))*100;
        double accur = ((double)correctnum /(double) (correctnum + wrong))*100;

        int accuring = (int) accur;


        c1.setProgress(accuring);
        c1.setTitle(Integer.toString(accuring) + "% ");
        final float density = Resources.getSystem().getDisplayMetrics().density;
        c1.settextsize(35 * (int) density);
        c1.setsubtextsize(10);


        // c1.setScaleX(3.8f);
        // c1.setScaleY(3.8f);
        c1.setProgressColor(Color.rgb(25, 189, 155));
        c1.setTitleColor(Color.rgb(25, 189, 155));
        c1.setSubTitleColor(Color.rgb(25, 189, 155));
        //c1.setSubTitle("GOOD");
        c1.setbackgroundcolor(Color.RED);


        retry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        retry.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (scorecounter >= maxiqscore) {
                            scorecounter = scorecounter - maxiqscore;
                            editor.putInt("scorecounter", scorecounter);
                            editor.commit();
                            //uplevel();

                        }
                        editor.putInt("gamestar", gamestar);
                        int  gcounter=gamestarcounter[0];
                        editor.putInt("gamestarcounter", gcounter);
                        editor.commit();
                        if (gamestar == 0) {
                            zerogamestar = true;
                            editor.putBoolean("zerogamestar", zerogamestar);
                            editor.commit();
                            backtooption = true;
                            back();

                        } else restartActivity();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        retry.setAlpha(1f);



                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

       // retry.setVisibility(View.INVISIBLE);

        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        back.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        editor.putInt("gamestar", gamestar);
                        int  gcounter=gamestarcounter[0];
                        editor.putInt("gamestarcounter", gcounter);
                        if (scorecounter >= maxiqscore) {
                            scorecounter = scorecounter - maxiqscore;
                            uplevel();
                            editor.putInt("scorecounter", scorecounter);
                            editor.commit();

                        }
                        backtooption = true;
                        back();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        back.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


    }
    public void restartActivity(){
        final Intent mIntent = getIntent();
        new CountDownTimer(200, 100) {
            public void onTick(long milsec) {



            }
        public void onFinish() {
            finish();
            startActivity(mIntent);
        }
        }.start();

    }
    void back()

    {
        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
        new CountDownTimer(200, 100) {
            public void onTick(long milsec) {



            }
            public void onFinish() {
                finish();
            }
        }.start();


    }

    void uplevel()

    {

        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1);

        final LinearLayout l2 = (LinearLayout) findViewById(R.id.lin2);
        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
        //final TextView t1 = (TextView) findViewById(R.id.textView);
        final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
        final FrameLayout uplevelframe = (FrameLayout) findViewById(R.id.uplevellayout);
        final TextView Leveltext = ( TextView) findViewById(R.id.Level);



        l2.setVisibility(View.INVISIBLE);
        l3.setVisibility(View.INVISIBLE);
       // t1.setVisibility(View.INVISIBLE);
        f3.setVisibility(View.INVISIBLE);
        f3.setEnabled(false);
        g.setVisibility(View.INVISIBLE);
        uplevelframe.setVisibility(View.VISIBLE);
        iq++;
        Leveltext.setText("Your IQ point increased 1 point and now " + Integer.toString(iq));


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("iq", iq);

        editor.commit();



    }

    public void updateEnglish(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);
        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);


        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("INFORMATION");
        button362.setText("OK");


        okbutton.setText("OK");
        congra.setText("PURCHASE");
        textViewconrats2.setText("BONUS AWARD");
        button3222.setText("COLLECT");
        textview552.setText("You have earned");



        buttonmath31.setText("PINK");
        buttonmath41.setText("WHITE");


        buttonmath11.setText("GREEN");
        buttonmath21.setText("RED");


        TextView37.setText("COLOR SENSE");
        TextView45.setText("How to Play ?");
        TextView44.setText("Choose the wrong color");
        highest.setText("SCORE");
        coinl.setText("HIGH");
        TextView38.setText("CORRECT");
        TextView39.setText("INCORRECT");
        coinlayout2.setText("COIN");
        Start.setText("Start");
        accuracy.setText("ACCURACY");

    }

    public void updateTurkish(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);

        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("BİLGİ");

        button362.setText("TAMAM");
        okbutton.setText("TAMAM");
        congra.setText("SATIN ALMA");
        textViewconrats2.setText("ÖDÜL");
        button3222.setText("TOPLA");
        textview552.setText("Kazandınız");


        buttonmath31.setText("PEMBE");
        buttonmath41.setText("BEYAZ");

        buttonmath11.setText("YEŞİL");
        buttonmath21.setText("KIRMIZI");


        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        TextView37.setText("COLOR SENSE");
        TextView45.setText("Nasıl Oynanır?");
        TextView44.setText("Yanlış rengi seçin");
        highest.setText("SKOR");
        coinl.setText("YÜKSEK");
        TextView38.setText("DOĞRU");
        TextView39.setText("YANLIŞ");
        coinlayout2.setText("JETON");
        Start.setText("Başlat");
        accuracy.setText("DOĞRULUK");

    }

    public void updateFrench(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);

        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);

        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);


        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("INFORMATION");

        button362.setText("D'accord");
        okbutton.setText("D'accord");
        congra.setText("ACHAT");
        textViewconrats2.setText("BONUS AWARD");
        button3222.setText("COLLECTE");
        textview552.setText("Vous avez gagné");


        buttonmath31.setText("ROSE");
        buttonmath41.setText("BLANC");

        buttonmath11.setText("VERT");
        buttonmath21.setText("ROUGE");


        TextView37.setText("COLOR SENSE");
        TextView45.setText("Comment Jouer ?");
        TextView44.setText("Choisissez la mauvaise couleur");
        highest.setText("SCORE");
        coinl.setText("HAUTE");
        TextView38.setText("CORRECT");
        TextView39.setText("INCORRECT");
        coinlayout2.setText("COIN");
        Start.setText("Démarrer");
        accuracy.setText("EXACTITUDE");
    }

    public void updateSpanish(){
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);

        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);
        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);


        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);


        textView762.setText("INFORMACIÓN");
        button362.setText("DE ACUERDO");

        okbutton.setText("BUENO");
        congra.setText("COMPRA");

        textViewconrats2.setText("Premio del Bono");
        button3222.setText("RECOGER");
        textview552.setText("Usted ha ganado");

        buttonmath31.setText("ROSADO");
        buttonmath41.setText("BLANCO");


        buttonmath11.setText("VERDE");
        buttonmath21.setText("ROJO");

        TextView37.setText("COLOR SENSE");
        TextView45.setText("Cómo Jugar ?");
        TextView44.setText("Elige el color equivocado");
        highest.setText("PUNTUACIÓN");
        coinl.setText("MÁS ALTO");
        TextView38.setText("CORRECTO");
        TextView39.setText("INCORRECTO");
        coinlayout2.setText("MONEDA");
        Start.setText("COMIENZO");
        accuracy.setText("EXACTITUD");
    }



    public void updaterussian(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);

        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);
        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);


        textView762.setText("ИНФОРМАЦИЯ");
        button362.setText("ХОРОШО");
        okbutton.setText("ХОРОШО");
        congra.setText("ПОКУПКА");
        textViewconrats2.setText("БОНУС ПОДАРОК");
        button3222.setText("СОБРАТЬ");
        textview552.setText("Вы заслужили");

        buttonmath31.setText("РОЗА");
        buttonmath41.setText("белый");

        buttonmath11.setText("зеленый");
        buttonmath21.setText("красный");
        TextView37.setText("COLOR SENSE");
        TextView45.setText("Как играть");
        TextView44.setText("Выберите неправильный цвет");
        highest.setText("ГОЛ");
        coinl.setText("ВЫСОКАЯ");
        TextView38.setText("ВЕРНЫЙ");
        TextView39.setText("НЕПРАВИЛЬНО");
        coinlayout2.setText("МОНЕТК");
        Start.setText("Начало");
        accuracy.setText("ТОЧНОСТЬ");

    }
    public void updatechinese(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);
        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);
        TextView37.setText("CALCUMATH");

        textView762.setText("信息");
        button362.setText("好的");


        okbutton.setText("好的");
        congra.setText("采购");
        textViewconrats2.setText("奖金奖");
        button3222.setText("搜集");
        textview552.setText("你赢了");

        buttonmath31.setText("粉");
        buttonmath41.setText("白色");


        buttonmath11.setText("绿色");
        buttonmath21.setText("红");
        TextView37.setText("COLOR SENSE");
        TextView45.setText("怎么玩");
        TextView44.setText("选错了颜色");
        highest.setText("得分了");
        coinl.setText("高");
        TextView38.setText("正确");
        TextView39.setText("不正確");
        coinlayout2.setText("硬币");
        Start.setText("开始");
        accuracy.setText("准确性");

    }

    public void updatejapanese(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);
        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);
        buttonmath31.setText("ピンク");
        buttonmath41.setText("白");

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("情報");
        button362.setText("オーケー");
        okbutton.setText("はい");
        congra.setText("購入");
        textViewconrats2.setText("ボーナス賞");
        button3222.setText("集める");
        textview552.setText("あなたが獲得しています");
        buttonmath11.setText("緑");
        buttonmath21.setText("赤");
        TextView37.setText("COLOR SENSE");
        TextView45.setText("遊び方");
        TextView44.setText("同じ色を選択してください");
        highest.setText("スコア");
        coinl.setText("高い");
        TextView38.setText("正しい");
        TextView39.setText("違う");
        coinlayout2.setText("コイン");
        Start.setText("開始");
        accuracy.setText("正確さ");

    }
    public void updateporteguese(){

        TextView TextView45 = (TextView) findViewById(R.id.textView45);
        TextView TextView44 = (TextView) findViewById(R.id.textView44);
        TextView highest = (TextView) findViewById(R.id.highest);
        TextView coinl = (TextView) findViewById(R.id.coinl);
        TextView TextView37 = (TextView) findViewById(R.id.textView37);
        TextView TextView38 = (TextView) findViewById(R.id.textView38);
        TextView TextView39 = (TextView) findViewById(R.id.textView39);
        TextView coinlayout2 = (TextView) findViewById(R.id.coinlayout2);
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        Button buttonmath11 = (Button) findViewById(R.id.buttonMath11);
        Button buttonmath21 = (Button) findViewById(R.id.buttonMath21);
        Button buttonmath31 = (Button) findViewById(R.id.buttonMath31);
        Button buttonmath41 = (Button) findViewById(R.id.buttonMath41);

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);
        textView762.setText("INFORMAÇÃO");

        button362.setText("BOM");
        okbutton.setText("BOM");
        congra.setText("COMPRA");

        textViewconrats2.setText("Prémio de Bónus");
        button3222.setText("RECOLHER");
        textview552.setText("Você ganhou");

        buttonmath31.setText("ROSA");
        buttonmath41.setText("BRANCO");


        buttonmath11.setText("VERDE");
        buttonmath21.setText("VERMELHO");
        TextView37.setText("COLOR SENSE");
        TextView45.setText("Como Jogar");
        TextView44.setText("Escolha a mesma cor");
        highest.setText("PONTO");
        coinl.setText("ALTO");
        TextView38.setText("CORRIGIR");
        TextView39.setText("ERRADO");
        coinlayout2.setText("MOEDA");
        Start.setText("Começar");
        accuracy.setText("PRECISÃO");

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);




        if (requestCode == RC_SIGN_IN) {
            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                mGoogleApiClient.connect();
            } else {
                // Bring up an error dialog to alert the user that sign-in
                // failed. The R.string.signin_failure should reference an error
                // string in your strings.xml file that tells the user they
                // could not be signed in, such as "Unable to sign in."
                BaseGameUtils.showActivityResultError(this,
                        requestCode, resultCode, R.string.signin_failure);
            }
        }
    }


    public void vibrate(int duration)
    {
        if(vibration==true)
        {
        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);}
    }


    void addfunction()
    {
        final RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.containerview);


        if (adflag)
        {
            adContainer.setVisibility(View.INVISIBLE);

        }
        else {

            adContainer.setVisibility(View.VISIBLE);

        }

    }
    public void pluscoinlayout7(){




        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox2);
        final Button questionunlock = (Button) findViewById(R.id.questionunlock);
        final Button questionunlock2 = (Button) findViewById(R.id.questionunlock2);
        final Button questionplus5game = (Button) findViewById(R.id.questionplus5);
        final Button questionplus5game2 = (Button) findViewById(R.id.questionplus52);

        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
        final Button questionnodd2 = (Button) findViewById(R.id.questionnoadd2);

        final Button questionnmult2 = (Button) findViewById(R.id.questionmult2);
        final Button questionnmult22 = (Button) findViewById(R.id.questionmult22);

        new CountDownTimer(SLIDEANIMDUR, 100) {
            public void onTick(long milsec) {


            }

            public void onFinish() {

                questionplus5game.setVisibility(View.VISIBLE);
                questionplus5game2.setVisibility(View.INVISIBLE);

                questionnodd.setVisibility(View.VISIBLE);
                questionnodd2.setVisibility(View.INVISIBLE);

                questionunlock2.setVisibility(View.INVISIBLE);
                questionunlock.setVisibility(View.VISIBLE);
                iquplevel.setVisibility(View.INVISIBLE);

                questionnmult2.setVisibility(View.VISIBLE);
                questionnmult22.setVisibility(View.INVISIBLE);
            }
        }.start();



    }


    public void coinflip () {


        final ImageView coinimage = (ImageView) findViewById(R.id.testImagecoin);


        final AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip2);
        final AnimatorSet set2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip2);
        final AnimatorSet set3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip2);
        set.setTarget(coinimage);

        set2.setTarget(coinimage);

        set3.setTarget(coinimage);

        set3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                set3.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                set3.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });




        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                set2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        set.start();


    }
    void showalert(int x)
    {




        RelativeLayout layout = (RelativeLayout) findViewById(R.id.pluscoinlayout3) ;
        TextView congrats21 = (TextView) findViewById(R.id.textView5521);
        final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
        disableallchild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {


                        break;
                    }
                    case MotionEvent.ACTION_UP:



                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        layout.setVisibility(View.VISIBLE);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        disableallchild.setVisibility(View.VISIBLE);
        shake.setDuration(SLIDEANIMDUR);
        layout.setAnimation(shake);
        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
        if(!mutestate) {
            clickaudio2.start();
            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    clickaudio2.release();

                }

                ;
            });
        }

        else if (x == MULTCOIN2)
        {

            if (gamelang == ENGLISH)  congrats21.setText("Coin earned from the games has doubled.");
            else if (gamelang == TURKISH)  congrats21.setText("Oyunlardan kazanılan paranız iki katına çıktı.");
            else if (gamelang == FRENCH)  congrats21.setText("Coin gagné à partir des jeux a doublé.");
            else if (gamelang == SPANISH) congrats21.setText("La moneda ganada de los juegos se ha duplicado.");
            else if (gamelang == RUSSIAN) congrats21.setText("Монета заработал от игр удвоилось");
            else if (gamelang == CHINESE) congrats21.setText("从游戏赚取的硬币翻了一番");
            else if (gamelang == JAPANESE) congrats21.setText("ゲームから得たコインは2倍になりました");
            else if (gamelang == PORTUGUESE) congrats21.setText("A moeda ganha dos jogos dobrou.");
            else   congrats21.setText("Coin earned from the games has doubled.");

        }


        if (x == NOADD)
        {

            if (gamelang == ENGLISH)  congrats21.setText("All in-app ads are now disabled.");
            else if (gamelang == TURKISH)  congrats21.setText("Tüm uygulama içi reklamlar artık devre dışı kaldı.");
            else if (gamelang == FRENCH)  congrats21.setText("Toutes les annonces in-app sont maintenant désactivées.");
            else if (gamelang == SPANISH) congrats21.setText("Todos los anuncios en la aplicación ahora no lo están.");
            else if (gamelang == RUSSIAN) congrats21.setText("Все объявления в приложении отключены");
            else if (gamelang == CHINESE) congrats21.setText("所有应用程式内广告现已停用");
            else if (gamelang == JAPANESE) congrats21.setText("すべてのアプリ内広告は、現在無効になっています");
            else if (gamelang == PORTUGUESE) congrats21.setText("Todos os anúncios in-app agora são desativados");
            else   congrats21.setText("All in-app ads are now disabled.");



        }

        else if (x == PLUS5GAME)
        {

            if (gamelang == ENGLISH)  congrats21.setText("For all games maximum right to play is 5.");
            else if (gamelang == TURKISH)  congrats21.setText("Tüm oyunlar için oynama hakkı maksimum  5'tir.");
            else if (gamelang == FRENCH)  congrats21.setText("Pour tous les jeux droit au maximum à jouer est 5.");
            else if (gamelang == SPANISH) congrats21.setText("En todos los partidos máxima derecho a jugar es 5.");
            else if (gamelang == RUSSIAN) congrats21.setText("Для всех игр максимум право играть 5");
            else if (gamelang == CHINESE) congrats21.setText("对于所有的游戏最大的游戏权利是5");
            else if (gamelang == JAPANESE) congrats21.setText("すべてのゲームのためにプレーする最大の権利は5です");
            else if (gamelang == PORTUGUESE) congrats21.setText("Para todos os jogos direito máximo para jogar é 5.");
            else   congrats21.setText("For all games maximum right to play is 5.");

        }

        else if (x == UNLOCKIQ)
        {

            if (gamelang == ENGLISH)  congrats21.setText("All market products are now unlocked.");
            else if (gamelang == TURKISH)  congrats21.setText("Tüm Pazar ürünlerinin kilidi açılmıştır.");
            else if (gamelang == FRENCH)  congrats21.setText("Tous les produits du marché sont maintenant débloqués.");
            else if (gamelang == SPANISH) congrats21.setText("Todos los productos del mercado están ahora desbloqueados.");
            else if (gamelang == RUSSIAN) congrats21.setText("Все продукты рынка теперь разблокирована.");
            else if (gamelang == CHINESE) congrats21.setText("所有市场产品现在解锁");
            else if (gamelang == JAPANESE) congrats21.setText("すべての市場の製品は、現在ロックされていません。");
            else if (gamelang == PORTUGUESE) congrats21.setText("Todos os produtos do mercado estão agora desbloqueado.");
            else   congrats21.setText("All market products are now unlocked.");

        }

    }


    void pluscoinaward(int x)

    {
        final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
        final MediaPlayer coinbelow10 = MediaPlayer.create(getApplicationContext(), R.raw.coinbelow10);
        final MediaPlayer coinabove10 = MediaPlayer.create(getApplicationContext(), R.raw.coinabove10);
        final RelativeLayout pluscoinlayout = ( RelativeLayout) findViewById(R.id.pluscoinlayout2);
        final int y = x;
        final TextView textcoin = (TextView) findViewById(R.id.textcoin);
        coin = coin+x;


        if(x>10)  {
            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.coinabove10);
            if(!mutestate) {
                clickaudio.start();
                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        clickaudio.release();

                    }

                    ;
                });
            }
        }
        else {
            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.coinbelow10);
            if(!mutestate) {
                clickaudio.start();
                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        clickaudio.release();

                    }

                    ;
                });
            }}

        coinflip();
        textcoin.setVisibility(View.INVISIBLE);


        new CountDownTimer(3000, 1000) {


            public void onTick(long milsec) {

            }

            public void onFinish() {

                textcoin.setVisibility(View.VISIBLE);
                ValueAnimator animator = new ValueAnimator();
                animator.setObjectValues(coin-y, coin);
                animator.setDuration(500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        textcoin.setText("" + (int) animation.getAnimatedValue());


                    }

                });
                animator.start();

            }
        }.start();

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("coin",coin);
        editor.commit();

    }


    public void pluscoinlayout2(){




        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.pluscoinlayout2);


        new CountDownTimer(SLIDEANIMDUR, 100) {
            public void onTick(long milsec) {


            }

            public void onFinish() {

                iquplevel.setVisibility(View.INVISIBLE);
            }
        }.start();



    }
    public void pluscoinlayout6(){




        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.pluscoinlayout3);


        new CountDownTimer(SLIDEANIMDUR, 100) {
            public void onTick(long milsec) {


            }

            public void onFinish() {

                iquplevel.setVisibility(View.INVISIBLE);
            }
        }.start();



    }
    public void pluscoinlayout8(){




        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox3);


        new CountDownTimer(SLIDEANIMDUR, 100) {
            public void onTick(long milsec) {


            }

            public void onFinish() {

                iquplevel.setVisibility(View.INVISIBLE);
            }
        }.start();



    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    void coinshopfunction()
    {
        final Button exit = (Button) findViewById(R.id.exit);
        final RelativeLayout startlayout = (RelativeLayout) findViewById(R.id.StartRelLayout);
        final RelativeLayout coinlayout = (RelativeLayout) findViewById(R.id.CoinLayout);
        final ImageView coins = (ImageView) findViewById(R.id.testImagecoin);
        final TextView Menutext = (TextView) findViewById(R.id.textView37);
        final RelativeLayout headermenu = (RelativeLayout) findViewById(R.id.headerlayout);
        final Button backbutton = (Button) findViewById(R.id.Back);

        exit.setVisibility(View.VISIBLE);
        startlayout.setVisibility(View.INVISIBLE);
        coinlayout.setVisibility(View.VISIBLE);
        backbutton.setVisibility(View.INVISIBLE);


        Drawable x = getResources().getDrawable(R.mipmap.sborder8);
        headermenu.setBackground(x);


        if (gamelang == ENGLISH)  Menutext.setText("COIN SHOP");
        else if (gamelang == TURKISH)  Menutext.setText("JETON MARKETİ");
        else if (gamelang == FRENCH)  Menutext.setText("ARGENT BOUTIQUE");
        else if (gamelang == SPANISH) Menutext.setText("TIENDA DE MONEDAS");
        else if (gamelang == RUSSIAN) Menutext.setText("МОНЕТА МАГАЗИН");
        else if (gamelang == CHINESE) Menutext.setText("硬币店");
        else if (gamelang == JAPANESE) Menutext.setText("コインショップ");
        else if (gamelang == PORTUGUESE) Menutext.setText("LOJA DE MOEDAS");

        else   Menutext.setText("COIN SHOP");


        exit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        exit.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        Drawable x = getResources().getDrawable(R.mipmap.sborder4);
                        headermenu.setBackground(x);


                        Menutext.setText("COLOR SENSE");


                        backbutton.setVisibility(View.VISIBLE);
                        exit.setVisibility(View.INVISIBLE);
                        startlayout.setVisibility(View.VISIBLE);
                        coinlayout.setVisibility(View.INVISIBLE);
                        coins.setEnabled(true);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        exit.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        final RelativeLayout instalayout = (RelativeLayout) findViewById(R.id.coinlayoutinsta);
        final Button instalayout1 = (Button) findViewById(R.id.imagedesert521);
        final Button instalayout2 = (Button) findViewById(R.id.instabutton);

        final RelativeLayout instalayout11 = (RelativeLayout) findViewById(R.id.instarel);


        final RelativeLayout twitlayout = (RelativeLayout) findViewById(R.id.coinlayouttwit);
        final Button twitlayout1 = (Button) findViewById(R.id.imagedesert5221);
        final Button twitlayout2 = (Button) findViewById(R.id.twitterbutton);

        final RelativeLayout twitlayout11 = (RelativeLayout) findViewById(R.id.twitrel);



        final RelativeLayout mult2layout = (RelativeLayout) findViewById(R.id.mult2);
        final Button mult2image = (Button) findViewById(R.id.imagemult2);
        final Button mult2button = (Button) findViewById(R.id.mult2button);
        final RelativeLayout mult2layout2 = (RelativeLayout) findViewById(R.id.mult22);
        final Button questionmult2 = (Button) findViewById(R.id.questionmult2 );
        final Button questionmult22 = (Button) findViewById(R.id.questionmult22);




        final RelativeLayout googlelayout = (RelativeLayout) findViewById(R.id.coinlayoutfacrel2);
        final Button googlelayout1 = (Button) findViewById(R.id.imagedesert522);
        final Button googlelayout2 = (Button) findViewById(R.id.facebutton22);

        final RelativeLayout googlelayout11 = (RelativeLayout) findViewById(R.id.facerel2);

        final RelativeLayout unlcokiqlayout = (RelativeLayout) findViewById(R.id.unlockiq);
        final Button unlcokiqimage = (Button) findViewById(R.id.imageunlockiq);
        final Button unlockiqbutton = (Button) findViewById(R.id.unlockiqbutton);
        final RelativeLayout unlockiqlayout2 = (RelativeLayout) findViewById(R.id.unlockiq2);




        final RelativeLayout videolayout = (RelativeLayout) findViewById(R.id.coinlayoutcoinvideo);
        final Button coinvideo = (Button) findViewById(R.id.coinvideoimg);
        final Button coinvideo2 = (Button) findViewById(R.id.coinvideobutton);
        final RelativeLayout coinvideolayout = (RelativeLayout) findViewById(R.id.coinvideorel);

        final RelativeLayout facelayout = (RelativeLayout) findViewById(R.id.coinlayoutfacrel);
        final Button facelayout1 = (Button) findViewById(R.id.imagedesert52);
        final Button facelayout12 = (Button) findViewById(R.id.facebutton);

        final RelativeLayout facelayout11 = (RelativeLayout) findViewById(R.id.facerel);

        final RelativeLayout coin500 = (RelativeLayout) findViewById(R.id.coin500rel);


        final RelativeLayout coin500layout = (RelativeLayout) findViewById(R.id.coinlayoutcoin500);
        final Button coin500l1 = (Button) findViewById(R.id.coin500img);
        final Button coin500l2 = (Button) findViewById(R.id.coin500button);


        final RelativeLayout plusgame = (RelativeLayout) findViewById(R.id.plusgamerel);


        final RelativeLayout plusgamelayout = (RelativeLayout) findViewById(R.id.coinlayoutplus5game);
        final Button plusgamel1 = (Button) findViewById(R.id.plusgameimg);
        final Button plusgamel2 = (Button) findViewById(R.id.plusgamebutton);

        final boolean isAvailable = BillingProcessor.isIabServiceAvailable(this);


        //  initInstances();
        //  initCallbackManager();
        //  refreshButtonsState();


        final Button questionplus5game = (Button) findViewById(R.id.questionplus5);
        final Button questionunlockiq = (Button) findViewById(R.id.questionunlock );
        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
        final TextView textquestion = (TextView) findViewById(R.id.textView872);

        if(mult2flag==false )
        {
            mult2layout2.setEnabled(true);
            mult2button.setEnabled(true);
            mult2image.setEnabled(true);
            mult2layout2.setAlpha(1f);

            mult2button.setAlpha(1f);
            mult2image.setAlpha(1f);
            questionmult2.setEnabled(true);
            questionmult2.setAlpha(1f);

        }


        else
        {
            mult2layout2.setEnabled(false);
            mult2button.setEnabled(false);
            mult2image.setEnabled(false);

            mult2layout2.setAlpha(0.33f);
            mult2button.setAlpha(0.33f);
            mult2image.setAlpha(0.33f);

            questionmult2.setEnabled(false);

            questionmult2.setAlpha(0.33f);

        }

        if(twitcoin==false)
        {
            twitlayout1.setEnabled(true);
            twitlayout2.setEnabled(true);
            twitlayout11.setEnabled(true);
            twitlayout1.setAlpha(1f);
            twitlayout2.setAlpha(1f);
            twitlayout11.setAlpha(1f);

        }
        else   {
            twitlayout1.setEnabled(false);
            twitlayout2.setEnabled(false);
            twitlayout11.setEnabled(false);

            twitlayout1.setAlpha(0.33f);
            twitlayout2.setAlpha(0.33f);
            twitlayout11.setAlpha(0.33f);

        }

        if(instacoin==false)
        {
            instalayout1.setEnabled(true);
            instalayout2.setEnabled(true);
            instalayout11.setEnabled(true);
            instalayout1.setAlpha(1f);
            instalayout2.setAlpha(1f);
            instalayout11.setAlpha(1f);

        }
        else   {
            instalayout1.setEnabled(false);
            instalayout2.setEnabled(false);
            instalayout11.setEnabled(false);

            instalayout1.setAlpha(0.33f);
            instalayout2.setAlpha(0.33f);
            instalayout11.setAlpha(0.33f);

        }


        if(facecoin==false)
        {
            facelayout1.setEnabled(true);
            facelayout11.setEnabled(true);
            facelayout12.setEnabled(true);
            facelayout1.setAlpha(1f);
            facelayout11.setAlpha(1f);
            facelayout12.setAlpha(1f);

        }
        else   {
            facelayout1.setEnabled(false);
            facelayout11.setEnabled(false);
            facelayout12.setEnabled(false);

            facelayout1.setAlpha(0.33f);
            facelayout11.setAlpha(0.33f);
            facelayout12.setAlpha(0.33f);

        }


        if(googlecoin==false)
        {
            googlelayout1.setEnabled(true);
            googlelayout11.setEnabled(true);
            googlelayout2.setEnabled(true);
            googlelayout1.setAlpha(1f);
            googlelayout11.setAlpha(1f);
            googlelayout2.setAlpha(1f);

        }
        else
        {
            googlelayout1.setEnabled(false);
            googlelayout11.setEnabled(false);
            googlelayout2.setEnabled(false);

            googlelayout1.setAlpha(0.33f);
            googlelayout11.setAlpha(0.33f);
            googlelayout2.setAlpha(0.33f);

        }


        if(plus5gameflag==false )
        {
            plusgame.setEnabled(true);
            plusgamel1.setEnabled(true);
            plusgamel2.setEnabled(true);
            plusgame.setAlpha(1f);
            plusgamel1.setAlpha(1f);
            plusgamel2.setAlpha(1f);
            questionplus5game.setEnabled(true);

            questionplus5game.setAlpha(1f);
        }

        else
        {

            plusgame.setEnabled(false);
            plusgamel1.setEnabled(false);
            plusgamel2.setEnabled(false);
            plusgame.setAlpha(0.33f);
            plusgamel1.setAlpha(0.33f);
            plusgamel2.setAlpha(0.33f);
            questionplus5game.setEnabled(false);

            questionplus5game.setAlpha(0.33f);
        }




        if(unlockiqflag==false )
        {
            unlockiqlayout2.setEnabled(true);
            unlockiqbutton.setEnabled(true);
            unlcokiqimage.setEnabled(true);
            unlockiqlayout2.setAlpha(1f);
            unlockiqbutton.setAlpha(1f);
            unlcokiqimage.setAlpha(1f);
            questionunlockiq.setEnabled(true);

            questionunlockiq.setAlpha(1f);

        }


        else
        {
            unlockiqlayout2.setEnabled(false);
            unlockiqbutton.setEnabled(false);
            unlcokiqimage.setEnabled(false);
            questionunlockiq.setEnabled(false);

            questionunlockiq.setAlpha(0.33f);
            unlockiqlayout2.setAlpha(0.33f);
            unlockiqbutton.setAlpha(0.33f);
            unlcokiqimage.setAlpha(0.33f);

        }




        final RelativeLayout coin100 = (RelativeLayout) findViewById(R.id.coin100rel);


        final RelativeLayout coin100layout = (RelativeLayout) findViewById(R.id.coinlayoutcoin100);
        final Button coin100l1 = (Button) findViewById(R.id.coin100img);
        final Button coin100l2 = (Button) findViewById(R.id.coin100button);




        final RelativeLayout coin1000 = (RelativeLayout) findViewById(R.id.coin1000rel);


        final RelativeLayout coin1000layout = (RelativeLayout) findViewById(R.id.coinlayoutcoin1000);
        final Button coin1000l1 = (Button) findViewById(R.id.coin1000img);
        final Button coin1000l2 = (Button) findViewById(R.id.coin1000button);

        final RelativeLayout coin10 = (RelativeLayout) findViewById(R.id.coin10rel);


        final RelativeLayout coin10layout = (RelativeLayout) findViewById(R.id.coinlayoutcoin10);
        final Button coin10l1 = (Button) findViewById(R.id.coin10img);
        final Button coin10l2 = (Button) findViewById(R.id.coin10button);


        if(adflag == false ) {


            coin10.setEnabled(true);
            coin10l1.setEnabled(true);
            coin10l2.setEnabled(true);
            coin10layout.setAlpha(1f);
            coin10l1.setAlpha(1f);
            coin10l2.setAlpha(1f);
            questionnodd.setEnabled(true);

            questionnodd.setAlpha(1f);
        }
        else {


            coin10.setEnabled(false);
            coin10l1.setEnabled(false);
            coin10l2.setEnabled(false);
            coin10layout.setAlpha(0.33f);
            coin10l1.setAlpha(0.33f);
            coin10l2.setAlpha(0.33f);
            questionnodd.setEnabled(false);

            questionnodd.setAlpha(0.33f);

        }

        mult2image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        mult2layout.setScaleX(BSCALE);
                        mult2layout.setScaleY(BSCALE);
                        mult2layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        BILLINGSTATE = MULTCOIN2;

                        if (isAvailable) {
                            // continue

                            bp.purchase(ColorDeception.this, MULTCOIN2S);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }

                        mult2layout.setScaleX(1f);
                        mult2layout.setScaleY(1f);
                        mult2layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        mult2button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        mult2layout.setScaleX(BSCALE);
                        mult2layout.setScaleY(BSCALE);
                        mult2layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        BILLINGSTATE = MULTCOIN2;

                        if (isAvailable) {
                            // continue

                            bp.purchase(ColorDeception.this, MULTCOIN2S);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }

                        mult2layout.setScaleX(1f);
                        mult2layout.setScaleY(1f);
                        mult2layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        mult2layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        mult2layout.setScaleX(BSCALE);
                        mult2layout.setScaleY(BSCALE);
                        mult2layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        BILLINGSTATE = MULTCOIN2;

                        if (isAvailable) {
                            // continue

                            bp.purchase(ColorDeception.this, MULTCOIN2S);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }

                        mult2layout.setScaleX(1f);
                        mult2layout.setScaleY(1f);
                        mult2layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        coin1000.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin1000layout.setScaleX(BSCALE);
                        coin1000layout.setScaleY(BSCALE);
                        coin1000layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE1000;
                            bp.purchase(cont, COIN1000);
                            // bp.getPurchaseTransactionDetails("coins");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }



                        coin1000layout.setScaleX(1f);
                        coin1000layout.setScaleY(1f);
                        coin1000layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        coin1000l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin1000layout.setScaleX(BSCALE);
                        coin1000layout.setScaleY(BSCALE);
                        coin1000layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE1000;
                            bp.purchase(cont, COIN1000);
                            //bp.getPurchaseTransactionDetails("android.test.purchased");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }


                        coin1000layout.setScaleX(1f);
                        coin1000layout.setScaleY(1f);
                        coin1000layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        coin1000l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin1000layout.setScaleX(BSCALE);
                        coin1000layout.setScaleY(BSCALE);
                        coin1000layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE1000;
                            bp.purchase(cont, COIN1000);
                            //bp.getPurchaseTransactionDetails("android.test.purchased");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }


                        coin1000layout.setScaleX(1f);
                        coin1000layout.setScaleY(1f);
                        coin1000layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });






        coin100.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin100layout.setScaleX(BSCALE);
                        coin100layout.setScaleY(BSCALE);
                        coin100layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE200;
                            bp.purchase(cont, COIN200);
                            // bp.getPurchaseTransactionDetails("coins");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }



                        coin100layout.setScaleX(1f);
                        coin100layout.setScaleY(1f);
                        coin100layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        coin100l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin100layout.setScaleX(BSCALE);
                        coin100layout.setScaleY(BSCALE);
                        coin100layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE200;
                            bp.purchase(cont, COIN200);
                            //bp.getPurchaseTransactionDetails("android.test.purchased");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }


                        coin100layout.setScaleX(1f);
                        coin100layout.setScaleY(1f);
                        coin100layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        coin100l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin100layout.setScaleX(BSCALE);
                        coin100layout.setScaleY(BSCALE);
                        coin100layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE200;
                            bp.purchase(cont, COIN200);
                            //bp.getPurchaseTransactionDetails("android.test.purchased");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }


                        coin100layout.setScaleX(1f);
                        coin100layout.setScaleY(1f);
                        coin100layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });




        coin500.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin500layout.setScaleX(BSCALE);
                        coin500layout.setScaleY(BSCALE);
                        coin500layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE500;
                            bp.purchase(cont, COIN500);
                            // bp.getPurchaseTransactionDetails("coins");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }
                        coin500layout.setScaleX(1f);
                        coin500layout.setScaleY(1f);
                        coin500layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        coin500l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin500layout.setScaleX(BSCALE);
                        coin500layout.setScaleY(BSCALE);
                        coin500layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE500;
                            bp.purchase(cont,COIN500);
                            // bp.getPurchaseTransactionDetails("coins");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }
                        coin500layout.setScaleX(1f);
                        coin500layout.setScaleY(1f);
                        coin500layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        coin500l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin500layout.setScaleX(BSCALE);
                        coin500layout.setScaleY(BSCALE);
                        coin500layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (isAvailable) {
                            // continue

                            BILLINGSTATE = PURCHASE500;
                            bp.purchase(cont, COIN500);
                            // bp.getPurchaseTransactionDetails("coins");
                            //
                        }
                        else
                        {

                            Log.d("billing:", "unavailable");

                        }
                        coin500layout.setScaleX(1f);
                        coin500layout.setScaleY(1f);
                        coin500layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });




        coinvideolayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        videolayout.setScaleX(BSCALE);
                        videolayout.setScaleY(BSCALE);
                        videolayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if(!mutestate)
                            soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        // if (vunglePub.isAdPlayable() == true) {


                        if(mobInterstitial2.isReady())
                        {

                            final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
                            coinvideolayout.setEnabled(false);
                            coinvideo.setEnabled(false);
                            coinvideo2.setEnabled(false);

                            final RelativeLayout pluscoinlayout = ( RelativeLayout) findViewById(R.id.pluscoinlayout2);
                            showad2();

                            new CountDownTimer(4000, 100) {

                                public void onTick(long millisUntilFinished) {

                                }

                                public void onFinish() {
                                    pluscoinlayout.setVisibility(View.VISIBLE);
                                    TextView tcoinamount = (TextView) findViewById(R.id.coinamount2);


                                    tcoinamount.setText("+" + Integer.toString(20));


                                    disableallchild.setVisibility(View.VISIBLE);
                                }

                            }.start();
                        }


                        else
                        {

                            mobInterstitial2.load();
                            noadsalert();
                        }

                        videolayout.setScaleX(1f);
                        videolayout.setScaleY(1f);
                        videolayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        coinvideo2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        videolayout.setScaleX(BSCALE);
                        videolayout.setScaleY(BSCALE);
                        videolayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if(!mutestate)
                            soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        // if (vunglePub.isAdPlayable() == true) {


                        if(mobInterstitial2.isReady())
                        {

                            final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
                            coinvideolayout.setEnabled(false);
                            coinvideo.setEnabled(false);
                            coinvideo2.setEnabled(false);

                            final RelativeLayout pluscoinlayout = ( RelativeLayout) findViewById(R.id.pluscoinlayout2);
                            showad2();

                            new CountDownTimer(4000, 100) {

                                public void onTick(long millisUntilFinished) {

                                }

                                public void onFinish() {
                                    pluscoinlayout.setVisibility(View.VISIBLE);
                                    TextView tcoinamount = (TextView) findViewById(R.id.coinamount2);


                                    tcoinamount.setText("+" + Integer.toString(20));


                                    disableallchild.setVisibility(View.VISIBLE);
                                }

                            }.start();
                        }


                        else
                        {

                            mobInterstitial2.load();
                            noadsalert();
                        }


                        videolayout.setScaleX(1f);
                        videolayout.setScaleY(1f);
                        videolayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        coinvideo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        videolayout.setScaleX(BSCALE);
                        videolayout.setScaleY(BSCALE);
                        videolayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if(!mutestate)
                            soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        // if (vunglePub.isAdPlayable() == true) {


                        if(mobInterstitial2.isReady())
                        {

                            final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
                            coinvideolayout.setEnabled(false);
                            coinvideo.setEnabled(false);
                            coinvideo2.setEnabled(false);

                            final RelativeLayout pluscoinlayout = ( RelativeLayout) findViewById(R.id.pluscoinlayout2);
                            showad2();

                            new CountDownTimer(4000, 100) {

                                public void onTick(long millisUntilFinished) {

                                }

                                public void onFinish() {
                                    pluscoinlayout.setVisibility(View.VISIBLE);
                                    TextView tcoinamount = (TextView) findViewById(R.id.coinamount2);


                                    tcoinamount.setText("+" + Integer.toString(20));


                                    disableallchild.setVisibility(View.VISIBLE);
                                }

                            }.start();
                        }


                        else
                        {

                            mobInterstitial2.load();
                            noadsalert();
                        }



                        videolayout.setScaleX(1f);
                        videolayout.setScaleY(1f);
                        videolayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        twitlayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        twitlayout.setScaleX(BSCALE);
                        twitlayout.setScaleY(BSCALE);
                        twitlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()){
                        Intent googleintent = new Intent(Intent.ACTION_VIEW);
                        //String facebookUrl = getFacebookPageURL(x);
                        googleintent.setData(Uri.parse(getResources().getString(R.string.twitteraccount)));
                        startActivity(googleintent);



                        twitcoin=true;
                        twittergo = true;
                        editor.putBoolean("twittergo",twittergo);
                        editor.putBoolean("twitcoin",twitcoin);
                        editor.commit();
                        twitlayout1.setEnabled(false);
                        twitlayout2.setEnabled(false);
                        twitlayout11.setEnabled(false);
                        twitlayout1.setAlpha(0.33f);
                        twitlayout2.setAlpha(0.33f);
                        twitlayout11.setAlpha(0.33f);}
                        else alertbox();


                        twitlayout.setScaleX(1f);
                        twitlayout.setScaleY(1f);
                        twitlayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        twitlayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        twitlayout.setScaleX(BSCALE);
                        twitlayout.setScaleY(BSCALE);
                        twitlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()){
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.twitteraccount)));
                            startActivity(googleintent);



                            twitcoin=true;
                            twittergo = true;
                            editor.putBoolean("twittergo",twittergo);
                            editor.putBoolean("twitcoin",twitcoin);
                            editor.commit();
                            twitlayout1.setEnabled(false);
                            twitlayout2.setEnabled(false);
                            twitlayout11.setEnabled(false);
                            twitlayout1.setAlpha(0.33f);
                            twitlayout2.setAlpha(0.33f);
                            twitlayout11.setAlpha(0.33f);}
                        else alertbox();



                        twitlayout.setScaleX(1f);
                        twitlayout.setScaleY(1f);
                        twitlayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        twitlayout11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        twitlayout.setScaleX(BSCALE);
                        twitlayout.setScaleY(BSCALE);
                        twitlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()){
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.twitteraccount)));
                            startActivity(googleintent);



                            twitcoin=true;
                            twittergo = true;
                            editor.putBoolean("twittergo",twittergo);
                            editor.putBoolean("twitcoin",twitcoin);
                            editor.commit();
                            twitlayout1.setEnabled(false);
                            twitlayout2.setEnabled(false);
                            twitlayout11.setEnabled(false);
                            twitlayout1.setAlpha(0.33f);
                            twitlayout2.setAlpha(0.33f);
                            twitlayout11.setAlpha(0.33f);}
                        else alertbox();



                        twitlayout.setScaleX(1f);
                        twitlayout.setScaleY(1f);
                        twitlayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });



        instalayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        instalayout.setScaleX(BSCALE);
                        instalayout.setScaleY(BSCALE);
                        instalayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isOnline()) {
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.instagramaccount)));
                            startActivity(googleintent);


                            instacoin = true;
                            instago = true;

                            editor.putBoolean("instacoin", instacoin);
                            editor.putBoolean("instago", instago);

                            editor.commit();
                            instalayout1.setEnabled(false);
                            instalayout2.setEnabled(false);
                            instalayout11.setEnabled(false);
                            instalayout1.setAlpha(0.33f);
                            instalayout2.setAlpha(0.33f);
                            instalayout11.setAlpha(0.33f);
                        }else alertbox();


                        instalayout.setScaleX(1f);
                        instalayout.setScaleY(1f);
                        instalayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        instalayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        instalayout.setScaleX(BSCALE);
                        instalayout.setScaleY(BSCALE);
                        instalayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isOnline()) {
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.instagramaccount)));
                            startActivity(googleintent);


                            instacoin = true;
                            instago = true;

                            editor.putBoolean("instacoin", instacoin);
                            editor.putBoolean("instago", instago);

                            editor.commit();
                            instalayout1.setEnabled(false);
                            instalayout2.setEnabled(false);
                            instalayout11.setEnabled(false);
                            instalayout1.setAlpha(0.33f);
                            instalayout2.setAlpha(0.33f);
                            instalayout11.setAlpha(0.33f);
                        }else alertbox();



                        instalayout.setScaleX(1f);
                        instalayout.setScaleY(1f);
                        instalayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        instalayout11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        instalayout.setScaleX(BSCALE);
                        instalayout.setScaleY(BSCALE);
                        instalayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isOnline()) {
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.instagramaccount)));
                            startActivity(googleintent);


                            instacoin = true;
                            instago = true;

                            editor.putBoolean("instacoin", instacoin);
                            editor.putBoolean("instago", instago);

                            editor.commit();
                            instalayout1.setEnabled(false);
                            instalayout2.setEnabled(false);
                            instalayout11.setEnabled(false);
                            instalayout1.setAlpha(0.33f);
                            instalayout2.setAlpha(0.33f);
                            instalayout11.setAlpha(0.33f);
                        }else alertbox();



                        instalayout.setScaleX(1f);
                        instalayout.setScaleY(1f);
                        instalayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        googlelayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        googlelayout.setScaleX(BSCALE);
                        googlelayout.setScaleY(BSCALE);
                        googlelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:




                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                       if(isOnline()) {
                           Intent googleintent = new Intent(Intent.ACTION_VIEW);
                           //String facebookUrl = getFacebookPageURL(x);
                           googleintent.setData(Uri.parse(getResources().getString(R.string.googleplusaccount)));
                           startActivity(googleintent);

                           GoodlegO = true;
                           googlecoin = true;

                           editor.putBoolean("GoodlegO", GoodlegO);
                           editor.putBoolean("googlecoin", googlecoin);

                           editor.commit();


                           googlelayout1.setEnabled(false);
                           googlelayout2.setEnabled(false);
                           googlelayout11.setEnabled(false);
                           googlelayout1.setAlpha(0.33f);
                           googlelayout2.setAlpha(0.33f);
                           googlelayout11.setAlpha(0.33f);
                       }else alertbox();


                        googlelayout.setScaleX(1f);
                        googlelayout.setScaleY(1f);
                        googlelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        googlelayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        googlelayout.setScaleX(BSCALE);
                        googlelayout.setScaleY(BSCALE);
                        googlelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()) {
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.googleplusaccount)));
                            startActivity(googleintent);

                            GoodlegO = true;
                            googlecoin = true;

                            editor.putBoolean("GoodlegO", GoodlegO);
                            editor.putBoolean("googlecoin", googlecoin);

                            editor.commit();


                            googlelayout1.setEnabled(false);
                            googlelayout2.setEnabled(false);
                            googlelayout11.setEnabled(false);
                            googlelayout1.setAlpha(0.33f);
                            googlelayout2.setAlpha(0.33f);
                            googlelayout11.setAlpha(0.33f);
                        }else alertbox();

                        googlelayout.setScaleX(1f);
                        googlelayout.setScaleY(1f);
                        googlelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        googlelayout11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        googlelayout.setScaleX(BSCALE);
                        googlelayout.setScaleY(BSCALE);
                        googlelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()) {
                            Intent googleintent = new Intent(Intent.ACTION_VIEW);
                            //String facebookUrl = getFacebookPageURL(x);
                            googleintent.setData(Uri.parse(getResources().getString(R.string.googleplusaccount)));
                            startActivity(googleintent);

                            GoodlegO = true;
                            googlecoin = true;

                            editor.putBoolean("GoodlegO", GoodlegO);
                            editor.putBoolean("googlecoin", googlecoin);

                            editor.commit();


                            googlelayout1.setEnabled(false);
                            googlelayout2.setEnabled(false);
                            googlelayout11.setEnabled(false);
                            googlelayout1.setAlpha(0.33f);
                            googlelayout2.setAlpha(0.33f);
                            googlelayout11.setAlpha(0.33f);
                        }else alertbox();
                        googlelayout.setScaleX(1f);
                        googlelayout.setScaleY(1f);
                        googlelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        facelayout11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        facelayout.setScaleX(BSCALE);
                        facelayout.setScaleY(BSCALE);
                        facelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()) {
                            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);

                            facebookIntent.setData(Uri.parse(getResources().getString(R.string.facebookaccount)));
                            startActivity(facebookIntent);

                       /* new CountDownTimer(10000, 1000) {

                            public void onTick(long milsec) {

                            }

                            public void onFinish() {

                                pluscoinaward(100);

                            }
                        }.start();*/

                            facebookgo = true;
                            facecoin = true;

                            editor.putBoolean("facecoin", facecoin);
                            editor.putBoolean("facebookgo", facebookgo);

                            editor.commit();
                            facelayout1.setEnabled(false);
                            facelayout11.setEnabled(false);
                            facelayout12.setEnabled(false);
                            facelayout1.setAlpha(0.33f);
                            facelayout11.setAlpha(0.33f);
                            facelayout12.setAlpha(0.33f);
                        }else alertbox();

                        facelayout.setScaleX(1f);
                        facelayout.setScaleY(1f);
                        facelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        facelayout12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        facelayout.setScaleX(BSCALE);
                        facelayout.setScaleY(BSCALE);
                        facelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()) {
                            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);

                            facebookIntent.setData(Uri.parse(getResources().getString(R.string.facebookaccount)));
                            startActivity(facebookIntent);

                       /* new CountDownTimer(10000, 1000) {

                            public void onTick(long milsec) {

                            }

                            public void onFinish() {

                                pluscoinaward(100);

                            }
                        }.start();*/

                            facebookgo = true;
                            facecoin = true;

                            editor.putBoolean("facecoin", facecoin);
                            editor.putBoolean("facebookgo", facebookgo);

                            editor.commit();
                            facelayout1.setEnabled(false);
                            facelayout11.setEnabled(false);
                            facelayout12.setEnabled(false);
                            facelayout1.setAlpha(0.33f);
                            facelayout11.setAlpha(0.33f);
                            facelayout12.setAlpha(0.33f);
                        }else alertbox();
                        facelayout.setScaleX(1f);
                        facelayout.setScaleY(1f);
                        facelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        facelayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        facelayout.setScaleX(BSCALE);
                        facelayout.setScaleY(BSCALE);
                        facelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(isOnline()) {
                            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);

                            facebookIntent.setData(Uri.parse(getResources().getString(R.string.facebookaccount)));
                            startActivity(facebookIntent);

                       /* new CountDownTimer(10000, 1000) {

                            public void onTick(long milsec) {

                            }

                            public void onFinish() {

                                pluscoinaward(100);

                            }
                        }.start();*/

                            facebookgo = true;
                            facecoin = true;

                            editor.putBoolean("facecoin", facecoin);
                            editor.putBoolean("facebookgo", facebookgo);

                            editor.commit();
                            facelayout1.setEnabled(false);
                            facelayout11.setEnabled(false);
                            facelayout12.setEnabled(false);
                            facelayout1.setAlpha(0.33f);
                            facelayout11.setAlpha(0.33f);
                            facelayout12.setAlpha(0.33f);
                        }else alertbox();
                        facelayout.setScaleX(1f);
                        facelayout.setScaleY(1f);
                        facelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        final Button coikokbutton3 = (Button) findViewById(R.id.button3222);

        final Button coikokbutton5 = (Button) findViewById(R.id.button32221);
        final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
        disableallchild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {


                        break;
                    }
                    case MotionEvent.ACTION_UP:



                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        final RelativeLayout pluscoinlayout2 = (RelativeLayout) findViewById(R.id.pluscoinlayout2);
        final RelativeLayout pluslifelayout3 = (RelativeLayout) findViewById(R.id.pluscoinlayout3);
        coikokbutton5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        coikokbutton5.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        final Animation shake = AnimationUtils.loadAnimation(cont, R.anim.righttoleft);
                        disableallchild.setVisibility(View.INVISIBLE);
                        shake.setDuration(SLIDEANIMDUR);
                        pluscoinlayout6();
                        pluslifelayout3.setAnimation(shake);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        coikokbutton5.setAlpha(1);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        coikokbutton3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        coikokbutton3.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        pluscoinlayout2.setVisibility(View.INVISIBLE);
                        final Animation shake = AnimationUtils.loadAnimation(cont, R.anim.righttoleft);
                        disableallchild.setVisibility(View.INVISIBLE);
                        shake.setDuration(SLIDEANIMDUR);
                        pluscoinlayout2();
                        pluscoinlayout2.setAnimation(shake);


                        if (facebookgo || GoodlegO || instago || twittergo) {
                            pluscoinaward(50);
                            facebookgo = false;
                            GoodlegO = false;
                            instago = false;
                            twittergo = false;


                            editor.putBoolean("twittergo", twittergo);
                            editor.putBoolean("instago", instago);
                            editor.putBoolean("GoodlegO", GoodlegO);
                            editor.putBoolean("facebookgo", facebookgo);
                            editor.commit();
                        }


                        else {

                            pluscoinaward(20);
                            // pluscoinlayout2.setAnimation(shake);
                            videogo = false;
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        coikokbutton3.setAlpha(1);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        final Button questionunlockiq2 = (Button) findViewById(R.id.questionunlock2);

        final Button questionplus5game2 = (Button) findViewById(R.id.questionplus52);


        final Button questionnodd2 = (Button) findViewById(R.id.questionnoadd2);



        final RelativeLayout warningquestion = (RelativeLayout) findViewById(R.id.warningbox2);
        questionplus5game.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        questionplus5game.setScaleX(BSCALE);
                        questionplus5game.setScaleY(BSCALE);
                        questionplus5game.setAlpha(BALPHA);
                        disableallchild.setVisibility(View.VISIBLE);
                        questionplus5game.setVisibility(View.INVISIBLE);
                        questionplus5game2.setVisibility(View.VISIBLE);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        warningquestion.setVisibility(View.VISIBLE);
                        final Animation shake = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.lefttoright);

                        shake.setDuration(SLIDEANIMDUR);
                        warningquestion.setAnimation(shake);
                        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                        if(!mutestate) {
                            clickaudio2.start();
                            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    clickaudio2.release();

                                }

                                ;
                            });
                        }

                        if (gamelang == ENGLISH)  textquestion.setText("Maximum right to play for all games will be 5");
                        else if (gamelang == TURKISH)  textquestion.setText("Tüm oyunlar için oynama hakkı maksimum 5 olacaktır.");
                        else if (gamelang == FRENCH)  textquestion.setText("Le droit maximum de jouer pour tous les jeux sera de 5");
                        else if (gamelang == SPANISH) textquestion.setText("Máximo derecho a jugar para todos los juegos será de 5");
                        else if (gamelang == RUSSIAN) textquestion.setText("Максимальное право играть на все игры будут 5");
                        else if (gamelang == CHINESE) textquestion.setText("所有游戏的最大游戏权限为5");
                        else if (gamelang == JAPANESE) textquestion.setText("すべてのゲームの最大プレー権は5");
                        else if (gamelang == PORTUGUESE) textquestion.setText("O máximo direito de jogar para todos os jogos será de 5");
                        else   textquestion.setText("Maximum right to play for all games will be 5");
                        final Button questionnodd2 = (Button) findViewById(R.id.questionnoadd2);
                        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
                        final TextView textquestion = (TextView) findViewById(R.id.textView872);


                        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox2);
                        final Button questionunlock = (Button) findViewById(R.id.questionunlock);
                        final Button questionunlock2 = (Button) findViewById(R.id.questionunlock2);


                        final Button questionnmult2 = (Button) findViewById(R.id.questionmult2);
                        final Button questionnmult22 = (Button) findViewById(R.id.questionmult22);



                        new CountDownTimer(3000, 100) {
                            public void onTick(long milsec) {


                            }

                            public void onFinish() {

                                questionplus5game.setVisibility(View.VISIBLE);
                                questionplus5game2.setVisibility(View.INVISIBLE);

                                questionnodd.setVisibility(View.VISIBLE);
                                questionnodd2.setVisibility(View.INVISIBLE);

                                questionunlock2.setVisibility(View.INVISIBLE);
                                questionunlock.setVisibility(View.VISIBLE);

                                disableallchild.setVisibility(View.INVISIBLE);
                                questionnmult2.setVisibility(View.VISIBLE);
                                questionnmult22.setVisibility(View.INVISIBLE);

                                questionplus5game.setEnabled(true);
                                questionnodd.setEnabled(true);
                                questionunlock.setEnabled(true);
                                questionnmult2 .setEnabled(true);
                            }
                        }.start();
                        questionplus5game.setScaleX(1f);
                        questionplus5game.setScaleY(1f);
                        questionplus5game.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        break;
                    }
                }
                return true;
            }
        });


        questionunlockiq2.setVisibility(View.INVISIBLE);
        questionunlockiq.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        questionunlockiq.setScaleX(BSCALE);
                        questionunlockiq.setScaleY(BSCALE);
                        questionunlockiq.setAlpha(BALPHA);
                        v.invalidate();
                        disableallchild.setVisibility(View.VISIBLE);
                        questionunlockiq.setVisibility(View.INVISIBLE);
                        questionunlockiq2.setVisibility(View.VISIBLE);

                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        warningquestion.setVisibility(View.VISIBLE);
                        final Animation shake = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.lefttoright);

                        // questionunlockiq.setEnabled(false);
                        shake.setDuration(SLIDEANIMDUR);
                        warningquestion.setAnimation(shake);
                        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                        if(!mutestate) {
                            clickaudio2.start();
                            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    clickaudio2.release();

                                }

                                ;
                            });
                        }

                        if (gamelang == ENGLISH)  textquestion.setText("All items in the market will be unlocked.");
                        else if (gamelang == TURKISH)  textquestion.setText("Tüm ürünlerin kilidi açılacaktır.");
                        else if (gamelang == FRENCH)  textquestion.setText("Tous les éléments seront déverrouillés.");
                        else if (gamelang == SPANISH) textquestion.setText("Todos los artículos en el mercado serán desbloqueados.");
                        else if (gamelang == RUSSIAN) textquestion.setText("Все детали на рынке будут разблокированы");
                        else if (gamelang == CHINESE) textquestion.setText("市场上的所有项目将被解锁");
                        else if (gamelang == JAPANESE) textquestion.setText("市場のすべてのアイテムはロックされません");
                        else if (gamelang == PORTUGUESE) textquestion.setText("Todos os itens no mercado serão desbloqueados.");
                        else   textquestion.setText("All items in the market will be unlocked.");

                        final Button questionnodd2 = (Button) findViewById(R.id.questionnoadd2);
                        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
                        final TextView textquestion = (TextView) findViewById(R.id.textView872);


                        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox2);
                        final Button questionunlock = (Button) findViewById(R.id.questionunlock);
                        final Button questionunlock2 = (Button) findViewById(R.id.questionunlock2);


                        final Button questionnmult2 = (Button) findViewById(R.id.questionmult2);
                        final Button questionnmult22 = (Button) findViewById(R.id.questionmult22);



                        new CountDownTimer(3000, 100) {
                            public void onTick(long milsec) {


                            }

                            public void onFinish() {

                                questionplus5game.setVisibility(View.VISIBLE);
                                questionplus5game2.setVisibility(View.INVISIBLE);

                                questionnodd.setVisibility(View.VISIBLE);
                                questionnodd2.setVisibility(View.INVISIBLE);

                                questionunlock2.setVisibility(View.INVISIBLE);
                                questionunlock.setVisibility(View.VISIBLE);

                                disableallchild.setVisibility(View.INVISIBLE);
                                questionnmult2.setVisibility(View.VISIBLE);
                                questionnmult22.setVisibility(View.INVISIBLE);

                                questionplus5game.setEnabled(true);
                                questionnodd.setEnabled(true);
                                questionunlock.setEnabled(true);
                                questionnmult2 .setEnabled(true);
                            }
                        }.start();
                        questionunlockiq.setScaleX(1f);
                        questionunlockiq.setScaleY(1f);
                        questionunlockiq.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        break;
                    }
                }
                return true;
            }
        });

        questionnodd2.setVisibility(View.INVISIBLE);
        questionnodd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        questionnodd.setScaleX(BSCALE);
                        questionnodd.setScaleY(BSCALE);
                        questionnodd.setAlpha(BALPHA);
                        v.invalidate();

                        disableallchild.setVisibility(View.VISIBLE);
                        questionnodd.setVisibility(View.INVISIBLE);
                        questionnodd2.setVisibility(View.VISIBLE);
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        warningquestion.setVisibility(View.VISIBLE);
                        final Animation shake = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.lefttoright);

                        shake.setDuration(SLIDEANIMDUR);
                        warningquestion.setAnimation(shake);
                        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                        if(!mutestate) {
                            clickaudio2.start();
                            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    clickaudio2.release();

                                }

                                ;
                            });
                        }

                        if (gamelang == ENGLISH)  textquestion.setText("All in-app ads will be disabled.");
                        else if (gamelang == TURKISH)  textquestion.setText("Tüm uygulama içi reklamlar devre dışı bırakılacaktır.");
                        else if (gamelang == FRENCH)  textquestion.setText("Toutes les annonces intégrées à l'application seront désactivées.");
                        else if (gamelang == SPANISH) textquestion.setText(" Todos los anuncios se eliminarán.");
                        else if (gamelang == RUSSIAN) textquestion.setText("Все объявления в приложении будут отключены.");
                        else if (gamelang == CHINESE) textquestion.setText("所有应用内广告都将被停用。");
                        else if (gamelang == JAPANESE) textquestion.setText("すべてのアプリ内広告は無効になります。");
                        else if (gamelang == PORTUGUESE) textquestion.setText("Todos os anúncios no aplicativo serão desativados.");
                        else   textquestion.setText("All in-app ads will be disabled.");
                        final Button questionnodd2 = (Button) findViewById(R.id.questionnoadd2);
                        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
                        final TextView textquestion = (TextView) findViewById(R.id.textView872);


                        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox2);
                        final Button questionunlock = (Button) findViewById(R.id.questionunlock);
                        final Button questionunlock2 = (Button) findViewById(R.id.questionunlock2);


                        final Button questionnmult2 = (Button) findViewById(R.id.questionmult2);
                        final Button questionnmult22 = (Button) findViewById(R.id.questionmult22);



                        new CountDownTimer(3000, 100) {
                            public void onTick(long milsec) {


                            }

                            public void onFinish() {

                                questionplus5game.setVisibility(View.VISIBLE);
                                questionplus5game2.setVisibility(View.INVISIBLE);

                                questionnodd.setVisibility(View.VISIBLE);
                                questionnodd2.setVisibility(View.INVISIBLE);

                                questionunlock2.setVisibility(View.INVISIBLE);
                                questionunlock.setVisibility(View.VISIBLE);

                                disableallchild.setVisibility(View.INVISIBLE);
                                questionnmult2.setVisibility(View.VISIBLE);
                                questionnmult22.setVisibility(View.INVISIBLE);

                                questionplus5game.setEnabled(true);
                                questionnodd.setEnabled(true);
                                questionunlock.setEnabled(true);
                                questionnmult2 .setEnabled(true);
                            }
                        }.start();
                        questionnodd.setScaleX(1f);
                        questionnodd.setScaleY(1f);
                        questionnodd.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        break;
                    }
                }
                return true;
            }
        });

        questionmult2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        questionmult2.setScaleX(BSCALE);
                        questionmult2.setScaleY(BSCALE);
                        questionmult2.setAlpha(BALPHA);
                        v.invalidate();

                        disableallchild.setVisibility(View.VISIBLE);
                        questionmult2.setVisibility(View.INVISIBLE);
                        questionmult22.setVisibility(View.VISIBLE);
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        warningquestion.setVisibility(View.VISIBLE);
                        final Animation shake = AnimationUtils.loadAnimation(ColorDeception.this, R.anim.lefttoright);

                        shake.setDuration(SLIDEANIMDUR);
                        warningquestion.setAnimation(shake);
                        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                        if(!mutestate) {
                            clickaudio2.start();
                            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    clickaudio2.release();

                                }

                                ;
                            });
                        }

                        if (gamelang == ENGLISH)  textquestion.setText("Your gained coins will be doubled.");
                        else if (gamelang == TURKISH)  textquestion.setText("Kazandığınız paralar iki katına çıkarılır.");
                        else if (gamelang == FRENCH)  textquestion.setText("Les pièces gagnées seront doublées.");
                        else if (gamelang == SPANISH) textquestion.setText("Sus monedas ganadas se duplicarán.");
                        else if (gamelang == RUSSIAN) textquestion.setText("Ваши полученные монеты будут удвоены.");
                        else if (gamelang == CHINESE) textquestion.setText("你获得的硬币将增加一倍。");
                        else if (gamelang == JAPANESE) textquestion.setText("コインは2倍になります");
                        else if (gamelang == PORTUGUESE) textquestion.setText("Suas moedas ganhas serão duplicadas.");
                        else   textquestion.setText("Your gained coins will be doubled.");
                        final Button questionnodd2 = (Button) findViewById(R.id.questionnoadd2);
                        final Button questionnodd = (Button) findViewById(R.id.questionnoadd);
                        final TextView textquestion = (TextView) findViewById(R.id.textView872);


                        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox2);
                        final Button questionunlock = (Button) findViewById(R.id.questionunlock);
                        final Button questionunlock2 = (Button) findViewById(R.id.questionunlock2);


                        final Button questionnmult2 = (Button) findViewById(R.id.questionmult2);
                        final Button questionnmult22 = (Button) findViewById(R.id.questionmult22);



                        new CountDownTimer(3000, 100) {
                            public void onTick(long milsec) {


                            }

                            public void onFinish() {

                                questionplus5game.setVisibility(View.VISIBLE);
                                questionplus5game2.setVisibility(View.INVISIBLE);

                                questionnodd.setVisibility(View.VISIBLE);
                                questionnodd2.setVisibility(View.INVISIBLE);

                                questionunlock2.setVisibility(View.INVISIBLE);
                                questionunlock.setVisibility(View.VISIBLE);

                                disableallchild.setVisibility(View.INVISIBLE);
                                questionnmult2.setVisibility(View.VISIBLE);
                                questionnmult22.setVisibility(View.INVISIBLE);

                                questionplus5game.setEnabled(true);
                                questionnodd.setEnabled(true);
                                questionunlock.setEnabled(true);
                                questionnmult2 .setEnabled(true);
                            }
                        }.start();
                        questionmult2.setScaleX(1f);
                        questionmult2.setScaleY(1f);
                        questionmult2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        break;
                    }
                }
                return true;
            }
        });

        final RelativeLayout warningquestion2 = (RelativeLayout) findViewById(R.id.warningbox2);
        final Button coikokbutton6 = (Button) findViewById(R.id.button362);
        final RelativeLayout pluslifelayout4 = (RelativeLayout) findViewById(R.id.warningbox3);
        final Button coikokbutton7 = (Button) findViewById(R.id.button363);


        final RelativeLayout warningbox3 = (RelativeLayout) findViewById(R.id.warningbox3);
        final Button buttoninfoyes = (Button) findViewById(R.id.button373);
        final TextView textinfoyes = (TextView) findViewById(R.id.textView873);



        coin10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin10layout.setScaleX(BSCALE);
                        coin10layout.setScaleY(BSCALE);
                        coin10layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = NOADD;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, NOADDS);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }


                     /*  warningbox3.setVisibility(View.VISIBLE);
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);
                       disableallchild.setVisibility(View.VISIBLE);
                       shake.setDuration(SLIDEANIMDUR);
                       warningbox3.setAnimation(shake);
                       final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                       if(!mutestate) {
                           clickaudio2.start();
                           clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                               public void onCompletion(MediaPlayer mp) {
                                   clickaudio2.release();

                               }

                               ;
                           });
                       }


                       BILLINGSTATE = NOADD;


                       Button unlockiqtext = (Button) findViewById(R.id.unlockiqbutton);
                       SkuDetails skun = bp.getPurchaseListingDetails("noadd");


                       if (gamelang == ENGLISH)  textinfoyes.setText("Click yes to disable in-app adds for " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == TURKISH)  textinfoyes.setText(Double.toString(round(skun.priceValue,2))+ " " + skun.currency + " harcayarak oyundaki reklamları devre dışı bırakmak  için evet'e basınız.");
                       else if (gamelang == FRENCH)  textinfoyes.setText("Cliquez sur Oui pour désactiver in-app ajoute pour " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == SPANISH) textinfoyes.setText("Haga clic en Sí para desactivar desde la aplicación agrega a  " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == RUSSIAN) textinfoyes.setText("Нажмите кнопку Да, чтобы отключить в приложение добавляет в течение " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == CHINESE) textinfoyes.setText("單擊是在應用停用增加了 " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == JAPANESE) textinfoyes.setText("アプリ内無効にするには、[はい]をクリックします" + Double.toString(round(skun.priceValue,2))+ " " + skun.currency + " のために追加されます");
                       else if (gamelang == PORTUGUESE) textinfoyes.setText("Clique em Sim para desativar o in-app adiciona para " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else   textinfoyes.setText("Click yes to disable in-app adds for 2 usd " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       plusgamelayout.setScaleX(1f);
                       plusgamelayout.setScaleY(1f);
                       plusgamelayout.setAlpha(1f);

*/

                        coin10layout.setScaleX(1f);
                        coin10layout.setScaleY(1f);
                        coin10layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        coin10l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin10layout.setScaleX(BSCALE);
                        coin10layout.setScaleY(BSCALE);
                        coin10layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        BILLINGSTATE = NOADD;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, NOADDS);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }

                        coin10layout.setScaleX(1f);
                        coin10layout.setScaleY(1f);
                        coin10layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        coin10l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        coin10layout.setScaleX(BSCALE);
                        coin10layout.setScaleY(BSCALE);
                        coin10layout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        BILLINGSTATE = NOADD;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, NOADDS);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }

                        coin10layout.setScaleX(1f);
                        coin10layout.setScaleY(1f);
                        coin10layout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });




        unlockiqlayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        unlcokiqlayout.setScaleX(BSCALE);
                        unlcokiqlayout.setScaleY(BSCALE);
                        unlcokiqlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = UNLOCKIQ;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, UNLOCKIQS);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }


/*
                       warningbox3.setVisibility(View.VISIBLE);
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);
                       disableallchild.setVisibility(View.VISIBLE);
                       shake.setDuration(SLIDEANIMDUR);
                       warningbox3.setAnimation(shake);
                       final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                       if(!mutestate) {
                           clickaudio2.start();
                           clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                               public void onCompletion(MediaPlayer mp) {
                                   clickaudio2.release();

                               }

                               ;
                           });
                       }


                       Button unlockiqtext = (Button) findViewById(R.id.unlockiqbutton);
                       SkuDetails skun = bp.getPurchaseListingDetails("unluckiq");

                       BILLINGSTATE = UNLOCKIQ;
                       if (gamelang == ENGLISH)  textinfoyes.setText("Click yes to unlock all market items for " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == TURKISH)  textinfoyes.setText(Double.toString(round(skun.priceValue,2))+ " " + skun.currency + " harcayarak pazar ürünlerinin kilidini açmak için evet'e basınız.");
                       else if (gamelang == FRENCH)  textinfoyes.setText("Cliquez sur Oui pour déverrouiller tous les éléments du marché pour " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == SPANISH) textinfoyes.setText("Haga clic en Sí para desbloquear todos los objetos en el Mercado de " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == RUSSIAN) textinfoyes.setText("Нажмите кнопку Да, чтобы разблокировать все элементы рынка за " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == CHINESE) textinfoyes.setText("單擊是解開所有的市場物品 " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == JAPANESE) textinfoyes.setText(Double.toString(round(skun.priceValue,2))+ " " + skun.currency + " のためのすべての市場の項目のロックを解除するには[はい]をクリックします");
                       else if (gamelang == PORTUGUESE) textinfoyes.setText("Clique em Sim para desbloquear todos os itens do mercado para " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else   textinfoyes.setText("Click yes to unlock all market items for " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);

*/
                        unlcokiqlayout.setScaleX(1f);
                        unlcokiqlayout.setScaleY(1f);
                        unlcokiqlayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        unlockiqbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        unlcokiqlayout.setScaleX(BSCALE);
                        unlcokiqlayout.setScaleY(BSCALE);
                        unlcokiqlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = UNLOCKIQ;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, UNLOCKIQS);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }



                        unlcokiqlayout.setScaleX(1f);
                        unlcokiqlayout.setScaleY(1f);
                        unlcokiqlayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });



        unlcokiqimage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        unlcokiqlayout.setScaleX(BSCALE);
                        unlcokiqlayout.setScaleY(BSCALE);
                        unlcokiqlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = UNLOCKIQ;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, UNLOCKIQS);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }

                        unlcokiqlayout.setScaleX(1f);
                        unlcokiqlayout.setScaleY(1f);
                        unlcokiqlayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        plusgamel2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        plusgamelayout.setScaleX(BSCALE);
                        plusgamelayout.setScaleY(BSCALE);
                        plusgamelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = PLUS5GAME;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, PLUS5GAMES);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }


    /*                   warningbox3.setVisibility(View.VISIBLE);
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);
                       disableallchild.setVisibility(View.VISIBLE);
                       shake.setDuration(SLIDEANIMDUR);
                       warningbox3.setAnimation(shake);
                       final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                       if(!mutestate) {
                           clickaudio2.start();
                           clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                               public void onCompletion(MediaPlayer mp) {
                                   clickaudio2.release();

                               }

                               ;
                           });
                       }


                       BILLINGSTATE = PLUS5GAME;


                       Button unlockiqtext = (Button) findViewById(R.id.unlockiqbutton);
                       SkuDetails skun = bp.getPurchaseListingDetails("plus5game");


                       if (gamelang == ENGLISH)  textinfoyes.setText("Click yes to set maximum right to play for all games to 5 for " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == TURKISH)  textinfoyes.setText(Double.toString(round(skun.priceValue,2))+ " " + skun.currency + " harcayarak maksimum oyun oynama hakkınızı 5 çıkarmak için evet'e basınız.");
                       else if (gamelang == FRENCH)  textinfoyes.setText("Cliquez sur Oui pour définir le droit maximal à jouer pour tous les jeux à 5 pour " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == SPANISH) textinfoyes.setText("Haga clic en Sí para configurar el máximo derecho a jugar todos los juegos a 5 para " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == RUSSIAN) textinfoyes.setText("Нажмите кнопку Да, чтобы настроить максимальное право играть во все игры на 5 в течение " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == CHINESE) textinfoyes.setText("單擊是為所有的遊戲玩到5最大權 " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else if (gamelang == JAPANESE) textinfoyes.setText(Double.toString(round(skun.priceValue,2))+ " " + skun.currency + " のため5に、すべてのゲームのためにプレーする最大の権利を設定するには、[はい]をクリックします");
                       else if (gamelang == PORTUGUESE) textinfoyes.setText("Clique em Sim para definir máxima direito de jogar para todos os jogos a 5 para " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);
                       else   textinfoyes.setText("Click yes to set maximum right to play for all games to 5 for " + Double.toString(round(skun.priceValue,2))+ " " + skun.currency);*/

                        plusgamelayout.setScaleX(1f);
                        plusgamelayout.setScaleY(1f);
                        plusgamelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });




        plusgamel1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        plusgamelayout.setScaleX(BSCALE);
                        plusgamelayout.setScaleY(BSCALE);
                        plusgamelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        //coin[0] = coin[0] + 1000;
                        //coinflip();
                        //tcoin.setText(Integer.toString(coin[0]));
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = PLUS5GAME;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, PLUS5GAMES);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }
                        plusgamelayout.setScaleX(1f);
                        plusgamelayout.setScaleY(1f);
                        plusgamelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });







        plusgame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        plusgamelayout.setScaleX(BSCALE);
                        plusgamelayout.setScaleY(BSCALE);
                        plusgamelayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        BILLINGSTATE = PLUS5GAME;



                        if (isAvailable) {
                            // continue

                            bp.purchase(cont, PLUS5GAMES);
                            // bp.getPurchaseTransactionDetails("coins");
                        }
                        else
                        {
                            Log.d("billing:", "unavailable");

                        }
                        plusgamelayout.setScaleY(1f);
                        plusgamelayout.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        buttoninfoyes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        buttoninfoyes.setScaleX(BSCALE);
                        buttoninfoyes.setScaleY(BSCALE);
                        buttoninfoyes.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:



                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);



                        final Animation shake = AnimationUtils.loadAnimation(cont, R.anim.righttoleft);
                        disableallchild.setVisibility(View.INVISIBLE);
                        shake.setDuration(SLIDEANIMDUR);
                        pluscoinlayout8();
                        pluslifelayout4.setAnimation(shake);

                        if(BILLINGSTATE == NOADD)

                        {

                            if (isAvailable) {
                                // continue

                                bp.purchase(cont, NOADDS);
                                // bp.getPurchaseTransactionDetails("coins");
                            }
                            else
                            {
                                Log.d("billing:", "unavailable");

                            }
                        }

                        else if(BILLINGSTATE == UNLOCKIQ)

                        {

                            if (isAvailable) {
                                // continue

                                bp.purchase(cont, UNLOCKIQS);
                                // bp.getPurchaseTransactionDetails("coins");
                            }
                            else
                            {
                                Log.d("billing:", "unavailable");

                            }
                        }

                        else if(BILLINGSTATE == PLUS5GAME)

                        {

                            if (isAvailable) {
                                // continue

                                bp.purchase(cont,PLUS5GAMES);
                                // bp.getPurchaseTransactionDetails("coins");
                            }
                            else
                            {
                                Log.d("billing:", "unavailable");

                            }
                        }

                        buttoninfoyes.setScaleX(1f);
                        buttoninfoyes.setScaleY(1f);
                        buttoninfoyes.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });





        coikokbutton7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        coikokbutton7.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                        final Animation shake = AnimationUtils.loadAnimation(cont, R.anim.righttoleft);
                        disableallchild.setVisibility(View.INVISIBLE);
                        shake.setDuration(SLIDEANIMDUR);
                        pluscoinlayout8();
                        pluslifelayout4.setAnimation(shake);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        coikokbutton7.setAlpha(1);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        coikokbutton6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        coikokbutton6.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);



                        final Animation shake = AnimationUtils.loadAnimation(cont, R.anim.righttoleft);
                        disableallchild.setVisibility(View.INVISIBLE);
                        shake.setDuration(SLIDEANIMDUR);
                        pluscoinlayout7();
                        warningquestion2.setAnimation(shake);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        coikokbutton6.setAlpha(1);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
    }

}

