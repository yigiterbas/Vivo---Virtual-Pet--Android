package com.game.hy.vivo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
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
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
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


public class operation extends Activity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,BillingProcessor.IBillingHandler,MoPubInterstitial.InterstitialAdListener {
    MoPubInterstitial mobInterstitial;
    MoPubInterstitial mobInterstitial2;
    private MoPubView moPubView;
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
Typeface tf;
    int[] gamestarcounter={0};
    int[] mProgressStatus={100};
    int scorecounter=0;
    final int heart_speed[] = {1000};
    int RPM = heart_speed[0] / 1000 * 80;
    int correctnum=0;
    int wrong=0;
    private int soundID;
    private SoundPool soundPool;
    AudioManager audioManager;
    float actVolume,maxVolume,volume;

    Operation operation;
    int highest =0;;
    int lowest =0;
    final int gamewaittime = 1800;
    long time =60;
    int maxiqscore=10000;
    int MAXGAMEPLAY = 3;
    int coin=0;
    InterstitialAd mInterstitialAd;
    Float BSCALE = 1f;

    Boolean mult2flag = false;

    Float BALPHA = 0.7f;
    final VunglePub vunglePub = VunglePub.getInstance();
    Boolean zerogamestar=false;
    int gamestar;

    int iq;
    int tries=0;
    Boolean iqplusone =false;
    Boolean vibration  = true;
    final Random r = new Random();
    int NONE= 100;
    int ENGLISH = 0;
    int TURKISH =1 ;
    boolean sifirla = false;
    int FRENCH =2;
    int SPANISH =3;
    int RUSSIAN =4;
    int CHINESE =5 ;
    int JAPANESE = 6;
    int PORTUGUESE =7;
Boolean notification=true;
    Boolean mutestate = false;
    int gamelang = NONE;
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
    final int i1 = (r.nextInt(3) + 0);

    BillingProcessor bp;
    final Activity cont = operation.this;
    int BILLINGSTATE = 0;
    int PURCHASE1000 = 1000;
    int PURCHASE500 = 500;
    int PURCHASE200 = 200;
    int UNLOCKIQ = 2;
    int PLUS5GAME = 3;
    int NOADD = 4;
    int MULTCOIN2 = 5;
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
    boolean adflag = false;
    IInAppBillingService mService;
    Boolean backtooption = false;
    int adcount = 0;

    String COIN200 = "coin200";
    String COIN500 = "coin500";
    String COIN1000 = "coin1000";

    String NOADDS = "noadd";
    String UNLOCKIQS = "unluckiq";
    String PLUS5GAMES = "plus5game";
    String MULTCOIN2S = "mult2";


    CountDownTimer waitTimer;


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
        AlertDialog alertDialog = new AlertDialog.Builder(operation.this).create();
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
        final Button questionmult2 = (Button) findViewById(R.id.questionmult2);
        final TextView textquestion = (TextView) findViewById(R.id.textView872);
        if (BILLINGSTATE == PURCHASE1000 || productId == COIN1000) {
            final TextView tcoin = (TextView) findViewById(R.id.textcoin);
            coin = coin + 10000;
            coinflip();
            tcoin.setText(Integer.toString(coin));
            editor.putInt("coin",coin);
            bp.consumePurchase(COIN1000);
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
            coinflip();
            tcoin.setText(Integer.toString(coin));
            editor.putInt("coin",coin);
            bp.consumePurchase(COIN500);
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
            coinflip();
            tcoin.setText(Integer.toString(coin));
            bp.consumePurchase(COIN200);
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



    public enum Operation {
        SUM, DIVISION, MULTIPLICATION, SUBSTRACT
    }
    void noadsalert()

    {
        AlertDialog alertDialog = new AlertDialog.Builder(operation.this).create();
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
    protected void onResume(){


        vunglePub.onResume();
        super.onResume();

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
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
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

            final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);

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



        super.onPause();}

    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);


            getWindow().getDecorView().setSystemUiVisibility(
                    //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_operation);
        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();


            final RelativeLayout videolayout = (RelativeLayout) findViewById(R.id.coinlayoutcoinvideo);
            final Button coinvideo = (Button) findViewById(R.id.coinvideoimg);
            final Button coinvideo2 = (Button) findViewById(R.id.coinvideobutton);
            final RelativeLayout coinvideolayout = (RelativeLayout) findViewById(R.id.coinvideorel);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

            /** Ad specific options to be sent with request */

      //  mAdView = (AdView) findViewById(R.id.ad_view);



          /*  MobileAds.initialize(this, "ca-app-pub-3355254771595654~8015051320");

            mInterstitialAd = new InterstitialAd(this);
           // mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            mInterstitialAd.setAdUnitId("ca-app-pub-3355254771595654/5225370524");
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    //requestNewInterstitial();
                    mInterstitialAd.loadAd(adRequest);
                   // beginPlayingGame();
                }
            });


            mInterstitialAd.loadAd(adRequest);*/
   /*     Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "12345");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "operation");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image123");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/


        Bundle params = new Bundle();
        params.putString("image_name", "operationstart");
        params.putString("full_text", "operationstart");
        mFirebaseAnalytics.logEvent("operationstart", params);

            setVolumeControlStream(AudioManager.STREAM_MUSIC);
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




            //requestNewInterstitial();


            //tf = Typeface.createFromAsset(getAssets(), "browbold.ttf");
            ProgressBar p4 = (ProgressBar) findViewById(R.id.progressBar4);

            p4.setScaleY(3);
            p4.getProgressDrawable().setColorFilter(
                    Color.argb(255, 25, 189, 155), android.graphics.PorterDuff.Mode.SRC_IN);






            final TextView t1 = (TextView) findViewById(R.id.textViewOperation);
            TextView optext2 = (TextView) findViewById(R.id.textView72);
            final TextView heading = (TextView) findViewById(R.id.textView37);

            //t1.setTypeface(tf);
            //t1.setTextSize(50);
            //optext2.setTypeface(tf);
              //      optext2.setTextSize(50);

            final String app_id = "56c04bc0735f803a61000008";
            mult2flag = shared.getBoolean("mult2flag", mult2flag);

            adcount =  shared.getInt("adcount", adcount);
            adflag = shared.getBoolean("adflag", adflag);
            facecoin = shared.getBoolean("facecoin", facecoin);
            googlecoin = shared.getBoolean("googlecoin", googlecoin);
            instacoin = shared.getBoolean("instacoin", instacoin);
            twitcoin = shared.getBoolean("twitcoin", twitcoin);
            notification = shared.getBoolean("notification", true);
            vibration = shared.getBoolean("vibration", true);
            unlockiqflag = shared.getBoolean("unlockiqflag", unlockiqflag);
            plus5gameflag = shared.getBoolean("plus5gameflag", plus5gameflag);
            bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoAGY3CL6BvTO/cwRLZ4D3O5w5B21ZO/ThlQNeJz7Cfj8vLC8+NzUxcO82JbHsuxAj1Wg0tTmuCdHUld0gt84enT+rjOqFuFC+7wFbCyWuM+grrM0DZUTP2wJF2IhGbZqFVDHbVBp2eutw8yaPzspvr3kadtUteMCLcNSdgMgKo+X/zEJ9z1rVhTVV+TmB3egpC7UVHkOW09oChFGe9e+kYXNAeDh5MWNgdbdvlDgFCY15nIaLDGRs8pLqaYYrtyod+1mXp0NGnHcvOpUJrWiHVfnFwSl0ZnXKGr3L8CQxLuL7LBoTqeR6HQ8ITKLCVdEbAqm87rFMYmwwK9WpQSI0wIDAQAB", this);
            MAXGAMEPLAY=shared.getInt("MAXGAMEPLAY", MAXGAMEPLAY);
            gamelang = shared.getInt("gamelang", NONE);
            if (gamelang == ENGLISH) updateEnglish();
            else if (gamelang == TURKISH) updateTurkish();
            else if (gamelang == FRENCH) updateFrench();
            else if (gamelang == SPANISH) updateSpanish();
            else if (gamelang == RUSSIAN) updateRussian();
            else if (gamelang == CHINESE) updateChinese();
            else if (gamelang == JAPANESE) updateJapanese();
            else if (gamelang == PORTUGUESE) updateportuguese();
            else updateEnglish();


// initialize the Publisher SDK
            vunglePub.init(this, app_id);

            //heading.setTypeface(tf);
           // heading.setTextSize(30);
            TextView question = (TextView) findViewById(R.id.textView45);
           // question.setTypeface(tf);
           // question.setTextSize(35);

            int newgame=shared.getInt("newgame", 0);


            if (newgame==1)
            {Boolean mute2 = shared.getBoolean("mutestate", false);
                mutestate = mute2;}



            gamestar= shared.getInt("gamestar", gamestar);
            //zerogamestar= shared.getBoolean("zerogamestar", zerogamestar);
            gamestarcounter[0] = shared.getInt("gamestarcounter", gamestarcounter[0]);
            notification = shared.getBoolean("notification", true);
            final  int gamestar1 = shared.getInt("gamestar", 0);
            mult2flag= shared.getBoolean("mult2flag", mult2flag);
            vibration = shared.getBoolean("vibration", true);
           // mutestate = shared.getBoolean("mutestate", mutestate);

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

        reset = AnimationUtils.loadAnimation(operation.this, R.anim.slowdown);
        heartimage = (ImageView) findViewById(R.id.testImage);
        pulse2 = AnimationUtils.loadAnimation(operation.this, R.anim.scale2);
        pulse = AnimationUtils.loadAnimation(this,  R.anim.scale);


      int  heartbeat2 = shared.getInt("heartbeatrate", 0);




        if( mProgressStatus[0]<201 &&  mProgressStatus[0]>= 98 ) heartbeat = 80 + randomi3;
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






     /*   if (heartbeat2 > heartbeat ) {heartbeat =heartbeat2;  slowdown();


            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            final SharedPreferences.Editor editor = pref.edit();
            editor.putInt("heartbeatrate",heartbeat);
            editor.commit();

        }
        else*/

        heartbeat();





      /*      scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);

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
                    else if (heart_speed[0] >= 1200 && heart_speed[0] < 1500) RPM = 70 + i1;
                    else if (heart_speed[0] >= 1500 && heart_speed[0] < 1800) RPM = 65 + i1;
                    else if (heart_speed[0] >= 1800 && heart_speed[0] < 2000) RPM = 60 + i1;


                    else RPM = 55;
                    final Random r1 = new Random();
                    final int i2 = (r1.nextInt(5) + 0);
                    t2.setText(Integer.toString(RPM + i2));
                }

            });*/

            final  TextView brain =( TextView) findViewById(R.id.textiq);

            addfunction();





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

                            if (isOnline()) {
                                if (!mGoogleApiClient.isConnected()) {

                                    //googlesigninbtnflg = true;
                                    if (!BaseGameUtils.verifySampleSetup(cont, R.string.app_id)) {
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
                                        if (!BaseGameUtils.verifySampleSetup(cont, R.string.app_id)) {
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
                            if (isOnline()) {

                                if (!mGoogleApiClient.isConnected()) {

                                    //googlesigninbtnflg = true;
                                    if (!BaseGameUtils.verifySampleSetup(cont, R.string.app_id)) {
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
                                        if (!BaseGameUtils.verifySampleSetup(cont, R.string.app_id)) {
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


        /*if (process[5]< 50) tearsanimation();
        else stoptearsanimation();*/


           // final AnimatorSet set = new AnimatorSet();
            //set.playSequentially();
            //set.playTogether(scaleXAnimator, scaleYAnimator);
            //set.start();




            coin= shared.getInt("coin", coin);



            final TextView countertime = (TextView) findViewById(R.id.textView43);
           // t2.setTypeface(tf);
            t2.setTextSize(12);
            //countertime.setTypeface(tf);
            countertime.setTextSize(12);
            final int[] gamecounter={0};

            //gamecounter[0] = gamestarcounter[0]%gamewaittime;

            gamecounter[0] = gamestarcounter[0]%gamewaittime;

            gamecounter[0] = gamewaittime-gamecounter[0];


            if (gamecounter[0] % 60 < 10) countertime.setText(Integer.toString(gamecounter[0] / 60) + ":0" +Integer.toString( gamecounter[0]%60));
            else countertime.setText(Integer.toString(gamecounter[0]/60) + ":" +Integer.toString( gamecounter[0]%60));
          //  TextView scoretext = (TextView) findViewById(R.id.score);


           // scoretext.setTypeface(tf);
            //scoretext.setTextSize(18);
            iq = shared.getInt("iq", iq);
            final TextView cointext = (TextView) findViewById(R.id.textcoin);






            final RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.containerview);


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
                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) { }

                @Override
                public void onInterstitialShown(MoPubInterstitial interstitial) { coinvideolayout.setEnabled(true);
                    coinvideo2.setEnabled(true);
                    coinvideo.setEnabled(true);

                    final RelativeLayout coinwarning = (RelativeLayout) findViewById(R.id.coinwarning);
                    final RelativeLayout disableallchild = (RelativeLayout) findViewById(R.id.draglayout);
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


            //cointext.setTypeface(tf);
            //cointext.setTextSize(12);
           // mAdView.loadAd(adRequest);

            final Button starimg = (Button) findViewById(R.id.imageView33);
            starimg.setTextColor(Color.WHITE);
            //starimg.setTypeface(tf);
            //starimg.setTextSize(12);
            starimg.setTextSize(12);
            if(gamestar<=0) starimg.setText("0");
            else if(gamestar==1)starimg.setText("1");
            else if (gamestar == 2) starimg.setText("2");
            else if (gamestar == 3)starimg.setText("3");
            else if (gamestar == 4)starimg.setText("4");
            else if (gamestar == 5) starimg.setText("5");
        iq=0;
        TextView textiq = (TextView) findViewById(R.id.textiq);
            //textiq.setTypeface(tf);
           // textiq.setTextSize(12);
        iq = shared.getInt("iq", iq);

            cointext.setText(Integer.toString(coin));
            highest = shared.getInt("operhighest", highest);

            vibration=shared.getBoolean("vibration", vibration);
            lowest = shared.getInt("operlowest", lowest);
        scorecounter = shared.getInt("scorecounter", scorecounter);

        textiq.setText(Integer.toString(iq));


        final ProgressBar pr1 =(ProgressBar) findViewById(R.id.progressBar2);

        if (gamestar >= MAXGAMEPLAY    ) countertime.setVisibility(View.INVISIBLE);
        else countertime.setVisibility(View.VISIBLE);



        final CircularProgressBar timecircular = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
       // final TextView Time = (TextView) findViewById(R.id.time);
            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            final SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("gamestop", false);




        waitTimer =    new CountDownTimer(1000000000, 1000) {
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

                            if(gamestar<=0)starimg.setText("0");
                            else if(gamestar==1)starimg.setText("1");
                            else if(gamestar==2)starimg.setText("2");
                            else if (gamestar == 3)starimg.setText("3");
                            else if (gamestar == 4)starimg.setText("4");
                            else if (gamestar == 5) starimg.setText("5");
                        }

                        editor.putInt("gamestar", gamestar);
                       // gcounter=gamestarcounter[0];
                       // editor.putInt("gamestarcounter", gcounter);
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
            final RelativeLayout buttonframe = (RelativeLayout) findViewById(R.id.Buttonframe);
            final FrameLayout textfram = (FrameLayout) findViewById(R.id.textfram);
            timecircular.setMax(24000);
            final Context con =this;
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
                            textfram.setVisibility(View.VISIBLE);
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
                                    //Time.setText(Long.toString(0));


                                    adcount++;
                                    editor.putInt("adcount", adcount);
                                    editor.commit();
                                    showZaire();

                                    new CountDownTimer(300, 100) {

                                        public void onTick(long millisUntilFinished) {

                                        }

                                            public void onFinish() {
                                                if(adcount % 3 == 0 && !adflag )  showad();
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
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            back.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
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

    public void onBackPressed() {
        //Display alert message when back button has been pressed
        //moveTaskToBack(true);

        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
        back();

    }
    public void start()

    {


        final ImageButton buttonOption1 = (ImageButton) findViewById(R.id.buttonMath1);
        final ImageButton buttonOption2 = (ImageButton) findViewById(R.id.buttonMath2);
        final ImageButton buttonOption3 = (ImageButton) findViewById(R.id.buttonMath3);
        final ImageButton buttonOption4 = (ImageButton) findViewById(R.id.buttonMath4);


        //  buttonOption1.setOnClickListener(this);

        buttonOption1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch(v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;

                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
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
                            tries++;
                            points=points+50;


                            updateScore();
                        }
                        else  {
                        wrong++;
                        points = points - 10;
                        if(points<=0)  points =0;
                            updateScore();

                    }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption1.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption1.setBackground(x);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();



                        }
                        else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption1.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
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


        buttonOption2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch(v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;;
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
                            tries++;

                            points=points+50;
                            updateScore();
                        }
                        else {
                            wrong++;

                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore();
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption2.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption2.setBackground(x);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();



                        }
                        else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption2.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
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

        final Button retry = (Button) findViewById(R.id.retry);
        retry.setVisibility(View.INVISIBLE);

        buttonOption3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                       final boolean play = true;

                        switch(v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
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

                           tries++;
                            points=points+50;


                            updateScore();
                        }
                         else {
                        wrong++;
                        points = points - 10;
                        if (points <= 0) points = 0;
                            updateScore();
                    }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption3.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption3.setBackground(x);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();



                        }
                        else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption3.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
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



        buttonOption4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption4.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch(v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {

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
                           points=points+50;
                            updateScore();
                        }

                        else {
                            wrong++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore();
                        }
                        //if(play) {
                        //createOperation();
                    //}
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption4.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption4.setBackground(x);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();



                        }
                        else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption4.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
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
  /*      retry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        retry.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        SharedPreferences pref;
                        pref = getSharedPreferences("info", MODE_PRIVATE);
                        final SharedPreferences.Editor editor = pref.edit();
                        gamestar--;
                        if(gamestar ==0) back();

                        editor.putInt("gamestar", gamestar);
                        editor.commit();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        retry.setAlpha(1f);
                        restartActivity();


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
        });*/


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


        //buttonOption2.setOnClickListener(this);
        // buttonOption3.setOnClickListener(this);
        //buttonHome.setOnClickListener(this);

        createOperation();



    }








    private void createOperation() {


        final ImageButton buttonOption1 = (ImageButton) findViewById(R.id.buttonMath1);
        final ImageButton buttonOption2 = (ImageButton) findViewById(R.id.buttonMath2);
        final ImageButton buttonOption3 = (ImageButton) findViewById(R.id.buttonMath3);
        final ImageButton buttonOption4 = (ImageButton) findViewById(R.id.buttonMath4);



        Drawable x = getResources().getDrawable(R.drawable.roundcornerbutton);
        buttonOption1.setBackground(x);
        buttonOption2.setBackground(x);
        buttonOption3.setBackground(x);
        buttonOption4.setBackground(x);


        operation = generateRandOperation();

        TextView op = (TextView) findViewById(R.id.textView42);






        Random r = new Random();
        Random r1 = new Random();
        int min = 2;
        int max = 10;
        int operand1 = r.nextInt((max - min) + 1) + min;
        int operand2 = r1.nextInt((max - min) + 1) + min;



        // int operand1 = NumberUtilities.generateRandNumber(2, 100);
        // int operand2 = NumberUtilities.generateRandNumber(2, 100);


        if(tries<3)


        { min = 1;
             max = 6;

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number.add(i);
            Collections.shuffle(number);

            operand1= number.get(0);
            operand2= number.get(1);

            while ( operand1 == operand2 || (operand1 == 4 && operand2 == 2))
            {
                Collections.shuffle(number);
                operand1= number.get(0);
                operand2= number.get(1);

            }
           // operand1 = r.nextInt((max - min) + 1) + min;
           //  operand2 = r1.nextInt((max - min) + 1) + min;
            operation=generateRandOperationsubadd();

        }

        else if(tries>=3 & tries <11)

        {

            min = 4;
            max = 11;

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number.add(i);
            Collections.shuffle(number);

            operand1= number.get(0);
            operand2= number.get(1);

            while ( operand1 == operand2 || (operand1 == 4 && operand2 == 2))
            {
                Collections.shuffle(number);
                operand1= number.get(0);
                operand2= number.get(1);



            }


            operation=generateRandOperation();
            if (operation == Operation.DIVISION) {

                min = 2;
                max = 50;

                ArrayList<Integer> number2 = new ArrayList<Integer>();
                for (int i = min; i <= max; ++i) number2.add(i);
                Collections.shuffle(number2);

                operand1= number2.get(0);
                operand2= number2.get(1);

                while ( operand1 == operand2 || (operand1 == 4 && operand2 == 2))
                {
                    Collections.shuffle(number2);
                    operand1= number2.get(0);
                    operand2= number2.get(1);

                }
            }

        }

        else {
            min = 7;
            max = 23;

            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number2.add(i);
            Collections.shuffle(number2);

            operand1= number2.get(0);
            operand2= number2.get(1);

            while ( operand1 == operand2 || (operand1 == 4 && operand2 == 2))
            {
                Collections.shuffle(number2);
                operand1= number2.get(0);
                operand2= number2.get(1);

            }


            operation=generateRandOperation();
            if (operation == Operation.DIVISION) {

                min = 5;
                max = 100;

                ArrayList<Integer> number3 = new ArrayList<Integer>();
                for (int i = min; i <= max; ++i) number3.add(i);
                Collections.shuffle(number3);


                operand1= number3.get(0);
                operand2= number3.get(1);

                while ( operand1 == operand2 || (operand1 == 4 && operand2 == 2))
                {
                    Collections.shuffle(number2);
                    operand1= number2.get(0);
                    operand2= number2.get(1);

                }
            }

            if (operation == Operation.MULTIPLICATION) {

                min = 5;
                max = 20;
                ArrayList<Integer> number3 = new ArrayList<Integer>();
                for (int i = min; i <= max; ++i) number3.add(i);
                Collections.shuffle(number3);

                operand1= number3.get(0);
                operand2= number3.get(1);

                while ( operand1 == operand2 || (operand1 == 4 && operand2 == 2))
                {
                    Collections.shuffle(number2);
                    operand1= number2.get(0);
                    operand2= number2.get(1);

                }

            }

        }

        int resultvalue = calculateRightValue(operation, operand1, operand2);


        while(operation == Operation.DIVISION & operand1%operand2!=0)

        {


            Random r11 = new Random();
            Random r12 = new Random();
            if (tries>11) {

                min = 4;
                max = 100;
            }


            ArrayList<Integer> number3 = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number3.add(i);
            Collections.shuffle(number3);

            operand1= number3.get(0);
            operand2= number3.get(1);


            while ( operand1 == 4 && operand2 == 2)
            {
                Collections.shuffle(number3);
                operand1= number3.get(0);
                operand2= number3.get(1);

            }


            resultvalue = calculateRightValue(operation, operand1, operand2);


        }


        //rightValue= Double.valueOf(dtime.format(rightValue));
        //rightValue= Double.parseDouble(new DecimalFormat("##.##").format(rightValue));



        //resultvalue = (int)Math.round(resultvalue * 100) / (double) 100;

        if((operation==Operation.DIVISION && operand1%operand2==0) | operation==Operation.SUM| operation==Operation.SUBSTRACT| operation==Operation.MULTIPLICATION) {

                String operationText = String.valueOf(operand1);
                String operationText2=String.valueOf(operand2) + " = " + resultvalue;

            TextView optext = (TextView) findViewById(R.id.textViewOperation);
            TextView optext2 = (TextView) findViewById(R.id.textView72);

            optext.setText(operationText);
            optext2.setText(operationText2);

            TextView header = (TextView) findViewById(R.id.textView37);




            TextView question = (TextView) findViewById(R.id.textView45);
            TextView qintro = (TextView) findViewById(R.id.textView44);
           // qintro.setTypeface(tf);
           // qintro.setTextSize(30);

           // question.setTypeface(tf);
           // question.setTextSize(30);


            //DecimalFormat dtime = new DecimalFormat("#.##");
        }

        Random r2 = new Random();
        Random r3 = new Random();
        int min1 = 0;
        int max1 = 2;
        rightBox = r2.nextInt((max1 - min1) + 1) + min1;
        Operation randWrongValue1 = generateRandOperation();

        Operation randWrongValue2 = generateRandOperation();
        Operation randWrongValue3 = generateRandOperation();
        /*if(operation == Operation.SUM ) {

            randWrongValue1 = Operation.MULTIPLICATION;
            randWrongValue2 = Operation.DIVISION;
            randWrongValue3 = Operation.SUBSTRACT;
        }

        else if (operation == Operation.DIVISION )

        {

            randWrongValue1 = Operation.SUBSTRACT;
            randWrongValue2 = Operation.SUM;
            randWrongValue3 = Operation.MULTIPLICATION;

        }

        else if (operation == Operation.MULTIPLICATION )

        {

            randWrongValue1 = Operation.SUBSTRACT;
            randWrongValue2 = Operation.SUM;
            randWrongValue3 = Operation.DIVISION;

        }

        else if (operation == Operation.SUBSTRACT )

        {

            randWrongValue1 = Operation.MULTIPLICATION;
            randWrongValue2 = Operation.SUM;
            randWrongValue3 = Operation.DIVISION;

        }*/


        //rightBox = NumberUtilities.generateRandNumber(1, 3);
        //float randWrongValue1 = NumberUtilities.generateRandNumber(2);

/*
        switch (rightBox) {
            case 0:
                buttonOption1.setText(getOperationString(operation));
                buttonOption2.setText(getOperationString(randWrongValue1));
                buttonOption3.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(randWrongValue3));
                break;
            case 1:
                buttonOption2.setText(getOperationString(operation));
                buttonOption1.setText(getOperationString(randWrongValue1));
                buttonOption3.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(randWrongValue3));
                break;
            case 2:
                buttonOption3.setText(getOperationString(operation));
                buttonOption1.setText(getOperationString(randWrongValue1));
                buttonOption2.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(randWrongValue3));
                break;

            case 3:
                buttonOption3.setText(getOperationString(randWrongValue3));
                buttonOption1.setText(getOperationString(randWrongValue1));
                buttonOption2.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(operation));
                break;


        }*/
    }

    private int calculateRightValue(Operation oper, int operand1, int operand2) {
        int calculation = 0;

        if (oper == Operation.SUM) {
            calculation = operand1 + operand2;
        } else if (oper == Operation.MULTIPLICATION) {
            calculation = operand1 * operand2;
        } else if (oper == Operation.SUBSTRACT) {
            calculation =  operand1 - operand2;
        } else if (oper == Operation.DIVISION) {
            calculation =  operand1 / operand2;
        }

        return calculation;
    }
    private String getOperationString(Operation oper) {
        String operationText = "";
        if (oper == Operation.SUM) {
            operationText = "+";
        } else if (oper == Operation.MULTIPLICATION) {
            operationText = "x";
        } else if (oper == Operation.SUBSTRACT) {
            operationText = "-";
        } else if (oper == Operation.DIVISION) {
            operationText = "/";
        }
        return operationText;
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    private Operation generateRandOperation() {

        Random r4 = new Random();
        int min1 = 0;
        int max1 = 3;
        int rand = r4.nextInt((max1 - min1) + 1) + min1;


        //int rand = NumberUtilities.generateRandNumber(1, 4);


        Operation operation = null;

        switch(rand) {
            case 0:
                operation = Operation.SUM;
                break;
            case 1:
                operation = Operation.SUBSTRACT;
                break;
            case 2:
                operation = Operation.MULTIPLICATION;
                break;
            case 3:
                operation = Operation.DIVISION;
                break;
        }
        return operation;
    }



    @Override
    public void onClick(View view) {
        boolean correct = false;
        boolean play = true;

        switch(view.getId()) {
            case R.id.buttonMath1:
                if (operation == Operation.SUM) {
                    correct = true;
                }
                break;
            case R.id.buttonMath2:
                if (operation == Operation.DIVISION) {
                    correct = true;
                }
                break;
            case R.id.buttonMath3:
                if (operation == Operation.MULTIPLICATION) {
                    correct = true;
                }
                break;

            case R.id.buttonMath4:
                if (operation == Operation.SUBSTRACT) {
                    correct = true;
                }
                break;
            //case R.id.imageButtonHome:
            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
            //play = false;
            //break;
        }
        if (correct) {
             points = points+50;
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




            updateScore();
        }

        else wrong++;

        if(play) {
            createOperation();
        }
    }

    private void updateScore() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        TextView textiq = (TextView) findViewById(R.id.textiq);
        textiq.setText(Integer.toString(iq));

    }


    public void showZaire() {

       /* if(gamestar<=0)
        {

            if (vunglePub.isAdPlayable() == true)
            vunglePub.playAd();

        }*/


        Thread thread;
        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1);
        final Button retry = (Button) findViewById(R.id.retry);
        final FrameLayout t = (FrameLayout) findViewById(R.id.textfram);
        final RelativeLayout l = (RelativeLayout) findViewById(R.id.linearLayout);
        final RelativeLayout buttonframe =  (RelativeLayout) findViewById(R.id.Buttonframe);
        final ProgressBar pr1 =(ProgressBar) findViewById(R.id.progressBar3);
        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
        final ProgressBar prbar = (ProgressBar )findViewById(R.id.progressBar2);
        final TextView lowesttext = (TextView) findViewById(R.id.coinl);
        final TextView highesttext = (TextView) findViewById(R.id.highest);
        final TextView high=(TextView) findViewById(R.id.High);
        final TextView score=(TextView) findViewById(R.id.Result);
        final TextView coingift = (TextView) findViewById(R.id.cointext);
        final TextView correctnumtext = (TextView) findViewById(R.id.correct);
        final TextView incorrectnumtext = (TextView) findViewById(R.id.Incorrect);
        final TextView Accuracy = (TextView) findViewById(R.id.accuracy);
        final TextView scorecountertext = (TextView) findViewById(R.id.scorecountertext);
        final CircularProgressBar2 c1 = (CircularProgressBar2) findViewById(R.id.circularprogressbar);

        final Button back = (Button) findViewById(R.id.Back);
        final ProgressBar p2 = (ProgressBar) findViewById(R.id.progressBar3);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        retry.setVisibility(View.VISIBLE);
        c2.setVisibility(View.INVISIBLE);
        if(points < lowest | lowest ==0 ) lowest = points ;
        if(points  > highest) highest = points;
        int coingain = 0;

        scorecounter = scorecounter+points ;
        if (!mult2flag) coingain =points/100;

        else coingain =points/50;

        if(iq < 5) maxiqscore = 5000;




        if (scorecounter>=maxiqscore) {
            editor.putBoolean("iqplusone", true);
            iq++;
            editor.putInt("iq",iq);





            coin=coin+50+coingain;
            editor.putInt("coin",coin);
            editor.putInt("gamecoin",coingain);
            coingift.setText(Integer.toString(50 + coingain));
            editor.commit();

         /*   if(notification==true)


            {
                if (vibration==true & mutestate ==false) showNotificationiq();
                else if (vibration==false & mutestate ==false) showNotificationiqnovibs();
                else if (vibration==false & mutestate ==true) showNotificationiqnovibsnosound();
                else if (vibration==true & mutestate ==true) showNotificationiqnosound();
            }*/


        }
        else {
            coin=coin+coingain;
            editor.putInt("coin",coin);

            editor.putBoolean("iqplusone", false);
            coingift.setText(Integer.toString(coingain));
            editor.commit();

        }

        editor.commit();
        int x = points;
        Intent i = new Intent();
        //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.putExtra("simscore", x);
        i.putExtra("iq", iq);

        setResult(Activity.RESULT_OK, i);
        //Toast toast = Toast.makeText(getApplicationContext(), "Time is Up",Toast.LENGTH_SHORT);
        //toast.show();
        prbar.setVisibility(View.INVISIBLE);
        buttonframe.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        g.setVisibility(View.VISIBLE);


        editor.putInt("operhighest", highest);
        editor.putInt("operlowest", lowest);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();

        final FrameLayout uplevelframe = (FrameLayout) findViewById(R.id.uplevellayout);

        //lowesttext.setText("Highest Score: " + Integer.toString(highest));
        //highesttext.setText("Current Score: " +Integer.toString(gamescore[0]));
        correctnumtext.setText(Integer.toString(correctnum));
        incorrectnumtext.setText(Integer.toString(wrong));

        high.setText(Integer.toString(highest));
        //score.setText(Integer.toString(points));

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, points);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                score.setText("" + (int) animation.getAnimatedValue());


            }

        });
        animator.start();



        scorecountertext.setText("For +1 IQ point :" + scorecounter + "/10000");

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



            //c1.setSubTitle("BAD");


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
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (scorecounter >= maxiqscore) {
                            scorecounter = scorecounter - maxiqscore;
                            editor.putInt("scorecounter", scorecounter);
                            editor.commit();
                            //uplevel();

                        }

                        editor.putInt("gamestar", gamestar);
                        int gcounter=gamestarcounter[0];
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
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });



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
                        int gcounter=gamestarcounter[0];
                        editor.putInt("gamestarcounter", gcounter);
                        //editor.putBoolean("zerogamestar",zerogamestar);
                        editor.commit();
                        if (scorecounter >= maxiqscore) {
                            scorecounter = scorecounter - maxiqscore;
                            editor.putInt("scorecounter", scorecounter);
                            editor.commit();
                            //uplevel();

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

    private int rightBox;
    private int points;



    public void onDestroy()

    {
        if (moPubView != null) {

            moPubView.destroy();}
        mobInterstitial.destroy();
        mobInterstitial2.destroy();
        if (bp != null)
            bp.release();

        super.onDestroy();

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("iq",iq);
        //editor.putBoolean("zerogamestar",zerogamestar);
        editor.putInt("gamestar",gamestar);
        int gcounter=gamestarcounter[0];
         editor.putInt("gamestarcounter", gcounter);

        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
        editor.commit();
      //  Chartboost.onDestroy(this);

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
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamestar", gamestar);
        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
        editor.commit();
        new CountDownTimer(100, 100) {
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
        final FrameLayout t = (FrameLayout) findViewById(R.id.textfram);
        final LinearLayout l = (LinearLayout) findViewById(R.id.linearLayout);

        final FrameLayout uplevelframe = (FrameLayout) findViewById(R.id.uplevellayout);
        final TextView Leveltext = ( TextView) findViewById(R.id.Level);



        g.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        l.setVisibility(View.INVISIBLE);
        uplevelframe.setVisibility(View.VISIBLE);
        iq++;
        Leveltext.setText("Your IQ point increased 1 point and now " + Integer.toString(iq));


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("iq", iq);

        editor.commit();



    }
    private Operation generateRandOperationsubadd() {

        Random r4 = new Random();
        int min1 = 0;
        int max1 = 1;
        int rand = r4.nextInt((max1 - min1) + 1) + min1;


        //int rand = NumberUtilities.generateRandNumber(1, 4);


        Operation operation = null;

        switch(rand) {
            case 0:
                operation = Operation.SUM;
                break;
            case 1:
                operation = Operation.SUBSTRACT;
                break;

        }
        return operation;
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


        TextView37.setText("4 SIGNS");
        TextView45.setText("How to Play ?");
        TextView44.setText("Guess the correct Result");
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
        Button Start =  (Button) findViewById(R.id.button31);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);

        TextView textView762 = (TextView) findViewById(R.id.textView762);

        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("BİLGİ");

        button362.setText("TAMAM");
        okbutton.setText("TAMAM");
        congra.setText("SATIN ALMA");
        textViewconrats2.setText("ÖDÜL");
        button3222.setText("TOPLA");
        textview552.setText("Kazandınız");

        TextView37.setText("4 SIGNS");
        TextView45.setText("Nasıl Oynanır?");
        TextView44.setText("Doğru Sonucu tahmin et");
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
        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);

        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("INFORMATION");

        button362.setText("D'accord");
        okbutton.setText("D'accord");
        congra.setText("ACHAT");
        textViewconrats2.setText("BONUS AWARD");
        button3222.setText("COLLECTE");
        textview552.setText("Vous avez gagné");

        TextView37.setText("4 SIGNS");
        TextView45.setText("Comment Jouer ?");
        TextView44.setText("Deviner le Résultat Correct");
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

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);

        TextView textView762 = (TextView) findViewById(R.id.textView762);

        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("INFORMACIÓN");
        button362.setText("DE ACUERDO");

        okbutton.setText("BUENO");
        congra.setText("COMPRA");

        textViewconrats2.setText("Premio del Bono");
        button3222.setText("RECOGER");
        textview552.setText("Usted ha ganado");

        TextView37.setText("4 SIGNS");
        TextView45.setText("Cómo Jugar ?");
        TextView44.setText("Adivinar el Resultado Correcto");
        highest.setText("PUNTUACIÓN");
        coinl.setText("MÁS ALTO");
        TextView38.setText("CORRECTO");
        TextView39.setText("INCORRECTO");
        coinlayout2.setText("MONEDA");
        Start.setText("COMIENZO");
        accuracy.setText("EXACTITUD");
    }

    public void updateRussian(){

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

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);

        TextView textView762 = (TextView) findViewById(R.id.textView762);

        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("ИНФОРМАЦИЯ");
        button362.setText("ХОРОШО");
        okbutton.setText("ХОРОШО");
        congra.setText("ПОКУПКА");
        textViewconrats2.setText("БОНУС ПОДАРОК");
        button3222.setText("СОБРАТЬ");
        textview552.setText("Вы заслужили");


        TextView37.setText("4 SIGNS");
        TextView45.setText("Как играть ?");
        TextView44.setText("Угадайте правильный результат");
        highest.setText("ГОЛ");
        coinl.setText("ВЫСОКАЯ");
        TextView38.setText("ВЕРНЫЙ");
        TextView39.setText("НЕПРАВИЛЬНО");
        coinlayout2.setText("МОНЕТК");
        Start.setText("Начало");
        accuracy.setText("ТОЧНОСТЬ");

    }
    public void updateChinese(){

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


        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);
        TextView textView762 = (TextView) findViewById(R.id.textView762);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("信息");
        button362.setText("好的");


        okbutton.setText("好的");
        congra.setText("采购");
        textViewconrats2.setText("奖金奖");
        button3222.setText("搜集");
        textview552.setText("你赢了");


        TextView37.setText("4 SIGNS");
        TextView45.setText("怎么玩 ？");
        TextView44.setText("竞猜正确的结果");
        highest.setText("得分了");
        coinl.setText("高");
        TextView38.setText("正确");
        TextView39.setText("不正确");
        coinlayout2.setText("硬币");
        Start.setText("开始");
        accuracy.setText("准确性");

    }
    public void updateJapanese(){

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

        TextView textViewconrats2 = (TextView) findViewById(R.id.textViewconrats2);
        TextView congra = (TextView) findViewById(R.id.textViewconrats21);
        TextView textview552 = (TextView) findViewById(R.id.textView552);
        Button button3222 = (Button) findViewById(R.id.button3222);
        Button okbutton = (Button) findViewById(R.id.button32221);

        TextView textView762 = (TextView) findViewById(R.id.textView762);

        Button button362 = (Button) findViewById(R.id.button362);

        textView762.setText("情報");
        button362.setText("オーケー");
        okbutton.setText("はい");
        congra.setText("購入");
        textViewconrats2.setText("ボーナス賞");
        button3222.setText("集める");
        textview552.setText("あなたが獲得しています");

        TextView37.setText("4 SIGNS");
        TextView45.setText("遊び方");
        TextView44.setText("正しい結果を推測します");
        highest.setText("スコア");
        coinl.setText("高い");
        TextView38.setText("正しい");
        TextView39.setText("違う");
        coinlayout2.setText("コイン");
        Start.setText("開始");
        accuracy.setText("正確さ");

    }
    public void updateportuguese(){

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

        TextView37.setText("4 SIGNS");
        TextView45.setText("Como Jogar");
        TextView44.setText("Acho que o resultado correto");
        highest.setText("PONTO");
        coinl.setText("ALTO");
        TextView38.setText("CORRIGIR");
        TextView39.setText("ERRADO");
        coinlayout2.setText("MOEDA");
        Start.setText("Começar");
        accuracy.setText("PRECISÃO");

    }



    public void vibrate(int duration)
    {

        if(vibration==true)
        {
        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);}
    }

    public void showNotificationiq() {

        //final MediaPlayer message = MediaPlayer.create(getApplicationContext(), R.raw.message);

        //message.start();
        // Uri soundUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.message);


        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                //| View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Brain Development")
                .setContentTitle("Brain Development")
                .setContentText("Congrulations! You won 1 bp. Your new bp is" + iq)
                .setSmallIcon(R.mipmap.vivologonotification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))

                .setDefaults(Notification.DEFAULT_SOUND)
                .setVibrate(new long[]{1000, 1000})

                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



        new CountDownTimer(3000, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {

                getWindow().getDecorView().setSystemUiVisibility(
                        //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //message.release();
            }
        }.start();
    }

    public void showNotificationiqnosound() {

        //final MediaPlayer message = MediaPlayer.create(getApplicationContext(), R.raw.message);

        //message.start();
        // Uri soundUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.message);


        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                //| View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Brain Development")
                .setContentTitle("Brain Development")
                .setContentText("Congrulations! You won 1 bp. Your new bp is" + iq)
                .setSmallIcon(R.mipmap.vivologonotification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setVibrate(new long[]{1000, 1000})
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



        new CountDownTimer(3000, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {

                getWindow().getDecorView().setSystemUiVisibility(
                        //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //message.release();
            }
        }.start();
    }



    public void showNotificationiqnovibs() {

        //final MediaPlayer message = MediaPlayer.create(getApplicationContext(), R.raw.message);

        //message.start();
        // Uri soundUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.message);


        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                //| View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Brain Development")
                .setContentTitle("Brain Development")
                .setContentText("Congrulations! You won 1 bp. Your new bp is" + iq)
                .setSmallIcon(R.mipmap.vivologonotification)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



        new CountDownTimer(3000, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {

                getWindow().getDecorView().setSystemUiVisibility(
                        //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //message.release();
            }
        }.start();
    }

    public void showNotificationiqnovibsnosound() {

        //final MediaPlayer message = MediaPlayer.create(getApplicationContext(), R.raw.message);

        //message.start();
        // Uri soundUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.message);


        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                //| View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Brain Development")
                .setContentTitle("Brain Development")
                .setContentText("Congrulations! You won 1 bp. Your new bp is" + iq)
                .setSmallIcon(R.mipmap.vivologonotification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



        new CountDownTimer(3000, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {

                getWindow().getDecorView().setSystemUiVisibility(
                        //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //message.release();
            }
        }.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (!bp.handleActivityResult(requestCode, resultCode, data))
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

    void addfunction()
    {
        final RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.containerview);


        if (adflag)
        {
            adContainer.setVisibility(View.INVISIBLE);
          //  mAdView.setVisibility(View.INVISIBLE);
        }
        else {

            adContainer.setVisibility(View.VISIBLE);
            //mAdView.setVisibility(View.VISIBLE);
        }

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


                       Menutext.setText("4 SIGNS");


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







       final RelativeLayout googlelayout = (RelativeLayout) findViewById(R.id.coinlayoutfacrel2);
       final Button googlelayout1 = (Button) findViewById(R.id.imagedesert522);
       final Button googlelayout2 = (Button) findViewById(R.id.facebutton22);

       final RelativeLayout googlelayout11 = (RelativeLayout) findViewById(R.id.facerel2);

       final RelativeLayout unlcokiqlayout = (RelativeLayout) findViewById(R.id.unlockiq);
       final Button unlcokiqimage = (Button) findViewById(R.id.imageunlockiq);
       final Button unlockiqbutton = (Button) findViewById(R.id.unlockiqbutton);
       final RelativeLayout unlockiqlayout2 = (RelativeLayout) findViewById(R.id.unlockiq2);

       final RelativeLayout mult2layout = (RelativeLayout) findViewById(R.id.mult2);
       final Button mult2image = (Button) findViewById(R.id.imagemult2);
       final Button mult2button = (Button) findViewById(R.id.mult2button);
       final RelativeLayout mult2layout2 = (RelativeLayout) findViewById(R.id.mult22);
       final Button questionmult2 = (Button) findViewById(R.id.questionmult2 );
       final Button questionmult22 = (Button) findViewById(R.id.questionmult22);

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

                           bp.purchase(operation.this, MULTCOIN2S);
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

                           bp.purchase(operation.this, MULTCOIN2S);
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

                           bp.purchase(operation.this, MULTCOIN2S);
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
                       if (isOnline()){
                           Intent googleintent = new Intent(Intent.ACTION_VIEW);
                       //String facebookUrl = getFacebookPageURL(x);
                       googleintent.setData(Uri.parse(getResources().getString(R.string.twitteraccount)));
                       startActivity(googleintent);


                       twitcoin = true;
                       twittergo = true;
                       editor.putBoolean("twittergo", twittergo);
                       editor.putBoolean("twitcoin", twitcoin);
                       editor.commit();
                       twitlayout1.setEnabled(false);
                       twitlayout2.setEnabled(false);
                       twitlayout11.setEnabled(false);
                       twitlayout1.setAlpha(0.33f);
                       twitlayout2.setAlpha(0.33f);
                       twitlayout11.setAlpha(0.33f);

               }
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
                       if (isOnline()) {
                           Intent googleintent = new Intent(Intent.ACTION_VIEW);
                       //String facebookUrl = getFacebookPageURL(x);
                       googleintent.setData(Uri.parse(getResources().getString(R.string.twitteraccount)));
                       startActivity(googleintent);


                       twitcoin = true;
                       twittergo = true;
                       editor.putBoolean("twittergo", twittergo);
                       editor.putBoolean("twitcoin", twitcoin);
                       editor.commit();
                       twitlayout1.setEnabled(false);
                       twitlayout2.setEnabled(false);
                       twitlayout11.setEnabled(false);
                       twitlayout1.setAlpha(0.33f);
                       twitlayout2.setAlpha(0.33f);
                       twitlayout11.setAlpha(0.33f);

               }
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
                       if (isOnline()) {
                           Intent googleintent = new Intent(Intent.ACTION_VIEW);
                           //String facebookUrl = getFacebookPageURL(x);
                           googleintent.setData(Uri.parse(getResources().getString(R.string.twitteraccount)));
                           startActivity(googleintent);


                           twitcoin = true;
                           twittergo = true;
                           editor.putBoolean("twittergo", twittergo);
                           editor.putBoolean("twitcoin", twitcoin);
                           editor.commit();
                           twitlayout1.setEnabled(false);
                           twitlayout2.setEnabled(false);
                           twitlayout11.setEnabled(false);
                           twitlayout1.setAlpha(0.33f);
                           twitlayout2.setAlpha(0.33f);
                           twitlayout11.setAlpha(0.33f);

                       }
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
                       if(isOnline()) {
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
                       }
                       else alertbox();


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
                       if(isOnline()) {
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
                       }
                       else alertbox();



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
                       if(isOnline()) {
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
                       }
                       else alertbox();



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
                       }
                       else alertbox();


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
                       }
                       else alertbox();

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
                       }
                       else alertbox();
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
                       }
                       else
                       alertbox();


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
                       }
                       else
                           alertbox();
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
                       }
                       else
                           alertbox();
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


       final Button questionunlock = (Button) findViewById(R.id.questionunlock);
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
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);

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

                       if (gamelang == ENGLISH)  textquestion.setText("Maximum right to play for all games will be 5.");
                       else if (gamelang == TURKISH)  textquestion.setText("Tüm oyunlar için oynama hakkı maksimum 5 olacaktır.");
                       else if (gamelang == FRENCH)  textquestion.setText("Le droit maximum de jouer pour tous les jeux sera de 5.");
                       else if (gamelang == SPANISH) textquestion.setText("Máximo derecho a jugar para todos los juegos será de 5.");
                       else if (gamelang == RUSSIAN) textquestion.setText("Максимальное право играть на все игры будут 5");
                       else if (gamelang == CHINESE) textquestion.setText("所有游戏的最大游戏权限为5");
                       else if (gamelang == JAPANESE) textquestion.setText("すべてのゲームの最大プレー権は5");
                       else if (gamelang == PORTUGUESE) textquestion.setText("O máximo direito de jogar para todos os jogos será de 5");
                       else   textquestion.setText("Maximum right to play for all games will be 5.");


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
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);

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
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);

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
                       else if (gamelang == SPANISH) textquestion.setText("Todos los anuncios se eliminarán.");
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

       questionmult22.setVisibility(View.INVISIBLE);
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
                       final Animation shake = AnimationUtils.loadAnimation(operation.this, R.anim.lefttoright);

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
            else if (gamelang == FRENCH)  congrats21.setText("Coin gagné à partir des jeux a doublé");
            else if (gamelang == SPANISH) congrats21.setText("La moneda ganada de los juegos se ha duplicado");
            else if (gamelang == RUSSIAN) congrats21.setText("Монета заработал от игр удвоилось");
            else if (gamelang == CHINESE) congrats21.setText("从游戏赚取的硬币翻了一番");
            else if (gamelang == JAPANESE) congrats21.setText("ゲームから得たコインは2倍になりました");
            else if (gamelang == PORTUGUESE) congrats21.setText("A moeda ganha dos jogos dobrou");
            else   congrats21.setText("Coin earned from the games has doubled.");

        }


        if (x == NOADD)
        {

            if (gamelang == ENGLISH)  congrats21.setText("All in-app ads are now disabled.");
            else if (gamelang == TURKISH)  congrats21.setText("Tüm uygulama içi reklamlar artık devre dışı kaldı.");
            else if (gamelang == FRENCH)  congrats21.setText("Toutes les annonces in-app sont maintenant désactivées");
            else if (gamelang == SPANISH) congrats21.setText("Todos los anuncios en la aplicación ahora no lo están");
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
            else if (gamelang == FRENCH)  congrats21.setText("Pour tous les jeux droit au maximum à jouer est 5");
            else if (gamelang == SPANISH) congrats21.setText("En todos los partidos máxima derecho a jugar es 5");
            else if (gamelang == RUSSIAN) congrats21.setText("Для всех игр максимум право играть 5");
            else if (gamelang == CHINESE) congrats21.setText("对于所有的游戏最大的游戏权利是5");
            else if (gamelang == JAPANESE) congrats21.setText("すべてのゲームのためにプレーする最大の権利は5です");
            else if (gamelang == PORTUGUESE) congrats21.setText("Para todos os jogos direito máximo para jogar é 5");
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
}