package com.game.hy.vivo;


import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Dimension;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.widget.Toast;


import com.android.vending.billing.IInAppBillingService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;
import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.vungle.publisher.VunglePub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    LikeView likeView;
    AudioManager manager;
    Bitmap logovivo2;
    Bitmap logovivo;
    Boolean notification=true;
    Boolean vibration=true;
    int NONE= 100;
    int ENGLISH = 0;
    int TURKISH =1 ;
    int newgame = 0;
    int SLIDEANIMDUR=600;
    Float BSCALE = 1f;
    Float BALPHA = 0.7f;
    Boolean onresume=false;
    int FRENCH =2;
    int SPANISH =3;
    int RUSSIAN =4;
    int CHINESE =5 ;
    int JAPANESE = 6;
    int PORTUGUESE =7;
    Context con = this;
    int alarmId = 11;
    String GETACCOUNTSTRING = "";
    String PERMISSIONDENIED = "";
    String GETACCOUNT = "";
    String READANDWRITE = "";
    String CAMERA = "";
    int STATE = 2;
    final int OPTIONEXIT = 1;
    final int MAINMENU = 2;

    private int soundID;
    private SoundPool soundPool;
    AudioManager audioManager;
    float actVolume,maxVolume,volume;
  /*  public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }*/


    final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 5;
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE =3;
    final int MY_PERMISSIONS_GET_ACCOUNT=4;
    int gamelang = NONE;
    CallbackManager callbackManager;

    final VunglePub vunglePub = VunglePub.getInstance();
    private static final String TAG = "BeGenerous";
    private static final String TAG1 = "badge";
    Button rel;
     Boolean mutestate = false;
    GoogleApiClient mGoogleApiClient;  // initialized in onCreate
    PlusOneButton mPbutton_standard;
    private static final int REQUEST_CODE = 0;
    //app's Google plus page URL
   // private static final String APPURL = "https://play.google.com/store/apps/details?id=YOUR_APPLICATION_PACKAGE_NAME";
    private static final String APPURL = "https://play.google.com/store/apps/developer?id=Facebook&hl=tr";
    int GAME_REQUEST = 1527;
        String Chractername ="";
    private static int RC_SIGN_IN = 9001;
    IInAppBillingService mService;
    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;
    MediaPlayer optionaudio;
    Boolean onceflag = false;
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "onConnected() called. Sign in successful!");
        //showSignOutBar();
        //checkPlayerStats();

        // This is *NOT* required; if you do not register a handler for
        // request events, you will get standard notifications instead.
        //Games.Requests.registerRequestListener(mGoogleApiClient, mRequestListener);

        if (connectionHint != null) {
            ArrayList<GameRequest> requests;
            // Do we have any requests pending? (getGameRequestsFromBundle never returns null
            requests = Games.Requests.getGameRequestsFromBundle(connectionHint);
            if (!requests.isEmpty()) {
                // We have requests in onConnected's connectionHint.
                Log.d(TAG, "onConnected: connection hint has " + requests.size() + " request(s)");
            }
            Log.d(TAG, "===========\nRequests count " + requests.size());
            // Use regular handler
            //handleRequests(requests);
        }
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);

        final String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Log.i(TAG, "#onConnected - GoogleApiClient accountName=" + accountName);

        Person me = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

        Person.Name name = me.getName();
        String given = name.getGivenName();
        String family = name.getFamilyName();

        Log.d(TAG, "Given: " + given + ", Family: " + family);


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("chname", given + family);
        editor.commit();


    }
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed() called, result: " + connectionResult);

        if (mResolvingConnectionFailure) {
            Log.d(TAG, "onConnectionFailed() ignoring connection failure; already resolving.");
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
                    RC_SIGN_IN, "signerorr")) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button
    }




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

          /*  DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.density = 4f;
            metrics.densityDpi = 640;
            metrics.heightPixels = 2560;
            metrics.widthPixels = 1440;
            metrics.scaledDensity = 4f;
            metrics.xdpi = 640.0f;
            metrics.ydpi = 640.0f;
            getResources().getDisplayMetrics().setTo(metrics);*/

/*
            DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

            Configuration config = this.getResources().getConfiguration();
            displayMetrics.densityDpi = DisplayMetrics.DENSITY_XXXHIGH;
            config.densityDpi = DisplayMetrics.DENSITY_XXXHIGH;

            this.getResources().updateConfiguration(config, displayMetrics);
*/

           // mp1.setVolume(100,100);
         
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                    .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                    .build();
            getWindow().getDecorView().setSystemUiVisibility(
                    //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            setContentView(R.layout.activity_main);
           // mPbutton_standard = (PlusOneButton)findViewById(R.id.id_plus_one_button_standard);
            //intro();

            AppEventsLogger.activateApp(this);
            //AppLinkData appLinkData = AppLinkData.createFromActivity(this);
           // App.setAppLinkData(appLinkData);

            Boolean mute2;

            File wallpaperDirectory = new File("/storage/emulated/0/application/vivo/vivofiles/junk/");
            File wallpaperDirectory2 = new File("/storage/emulated/0/application/");
            File wallpaperDirectory3 = new File("/storage/emulated/0/application/vivo/");
            File wallpaperDirectory4 = new File("/storage/emulated/0/application/vivo/vivofiles/");
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;

            Log.d("yigit screen height:", Integer.toString(height));
            Log.d("yigit screen width:", Integer.toString(width));


            DisplayMetrics dm = new DisplayMetrics();

            double SCREEN_DPI = dm.densityDpi;
            double density1 = Resources.getSystem().getDisplayMetrics().density;

            Log.d("DPI:", Double.toString(density1));
            wallpaperDirectory2.mkdir();
            wallpaperDirectory3.mkdir();
            wallpaperDirectory4.mkdir();
            wallpaperDirectory.mkdir();

           // Log.d("tag", wallpaperDirectory.getAbsolutePath());
            // have the object build the directory structure, if needed.

            // create a File object for the output file






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
            SharedPreferences pref1;
            pref1 = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            SharedPreferences.Editor editor1 = pref1.edit();

            BitmapFactory.Options options = new BitmapFactory.Options();

            SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);

            int ongame =shared1.getInt("ongame", 0);
            onresume =shared1.getBoolean("onresume", onresume);
           // options.inPreferredConfig =  Bitmap.Config.RGB_565;
            gamelang = shared1.getInt("gamelang", NONE);
          //  options.inSampleSize = 2;
            // logovivo= BitmapFactory.decodeResource(getResources(), R.mipmap.vivoentsmall2,options);
             logovivo2= BitmapFactory.decodeResource(getResources(), R.mipmap.viv3,options);
            final ImageView vivoimg = (ImageView) findViewById(R.id.imageView18);
            //vivoimg.setImageBitmap(logovivo);

            final ImageView vivoimg2 = (ImageView) findViewById(R.id.imageView40);
            //final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.clicklast);
            optionaudio=MediaPlayer.create(getApplicationContext(), R.raw.optionaudio);



            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
            mFirebaseAnalytics.setMinimumSessionDuration(20000);
            newgame=shared1.getInt("newgame", 0);

            int firstinstall = shared1.getInt("firstinstall", 0);




                if (firstinstall == 0)
                {
                    Bundle params = new Bundle();
                    params.putString("image_name", "firstime");
                    params.putString("full_text", "firstime");
                    mFirebaseAnalytics.logEvent("firstime", params);
                    Log.d("first install test:", "firstintall");
                }

            editor1.putInt("firstinstall",1);
            editor1.commit();



            mute2 = shared1.getBoolean("mutestate", false);
            mutestate = mute2;


            vivoimg2.setImageBitmap(logovivo2);

            String root = Environment.getExternalStorageDirectory().toString();

            String r1 = root;
            String r2 = "/storage/emulated/0";
            Log.d("yigit velelele:",root);
            Log.d("yigit velelele:","/storage/emulated/0");
            String photoPath = "/storage/emulated/0/application/vivo/vivofiles/junk/" + ".pic2.jpg";
            onceflag = shared1.getBoolean("onceflag",onceflag);
            Boolean fileflag = false;
            File file2 = new File(photoPath);
            if(!file2.exists())

            {
                photoPath = root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg";

                File file3 = new File(photoPath);
                if(file3.exists()) fileflag = true;

                Log.d("yigit velelele:","hayır burdayız");
            }
            else if (!r1.equals("/storage/emulated/0") && !onceflag )
            {




                Log.d("yigit velelele:","burdayız");

                    Bitmap setBitmap = BitmapFactory.decodeFile(photoPath, options);
                    try {
                        File wallpaperDirectory33 = new File(root + "/application/vivo/vivofiles/junk/");
                        wallpaperDirectory33.mkdir();
                        FileOutputStream out = new FileOutputStream(root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg");
                        setBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        //file2.delete();
                        photoPath = root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg";
                        fileflag = true;
                        editor1.putBoolean("onceflag",true);
                        editor1.commit();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }


            else
            {
                fileflag = true;

            }

            photoPath = root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg";

            SharedPreferences pref4;
            pref4 = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor4 = pref4.edit();
            editor4.putString("storagepath",photoPath);
            editor4.commit();
            File file = new File(photoPath);


            if((newgame ==0 && ongame ==0) || (!fileflag && ongame ==0) ) intro();
            if (newgame==0 && ongame ==1 && !mutestate && optionaudio != null) optionaudio.start();
            //else introgogame();





            if(newgame == 1  && ongame ==0 && fileflag) {

                gogame();
                Bundle params = new Bundle();
                params.putString("image_name", "vivostart");
                params.putString("full_text", "vivostart");
                mFirebaseAnalytics.logEvent("vivostart", params);
                //quit() ;
            }



            ImageView i1 = (ImageView) findViewById(R.id.imageView18);
            AnimatorSet set;
            set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flipanimator);
            set.setTarget(i1);

            //set.setDuration(1000);
            ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(i1 ,
                    "rotationY", 0f, 180f);
            imageViewObjectAnimator.setDuration(750); // miliseconds
            imageViewObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
            imageViewObjectAnimator.start();

            //set.start();



            editor1.putInt("ongame", 0);

            editor1.commit();


            FacebookSdk.sdkInitialize(getApplicationContext());



            String array[] = { "ENGLISH", "TÜRKÇE", "FRANÇAIS", "ESPAÑOLA","РУССКИЙ","中文", "日本語","PORTUGUÊS"};
            ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this, R.layout.spinner, array);
            sp_adapter.setDropDownViewResource
                    (R.layout.spinner_selector);
            Spinner sp = (Spinner) findViewById(R.id.spinner);
            sp.setAdapter(sp_adapter);

            // final Locale trlocale= Locale.forLanguageTag("tr-TR");
            //final Locale spnlocale = Locale.forLanguageTag("es-ES");
            final Locale trlocale= new Locale("tr-TR");
            final Locale eslocale= new Locale("es-ES");

           /* if(newgame ==1 ) {
                if (gamelang == ENGLISH) sp.setSelection(0);
                else if (gamelang == TURKISH) sp.setSelection(1);
                else if (gamelang == FRENCH) sp.setSelection(2);
                else if (gamelang == SPANISH) sp.setSelection(3);
                else {



                    if (Locale.getDefault().getLanguage().equals("tr")) sp.setSelection(1);
                    else if (Locale.getDefault().getLanguage().equals("en")) sp.setSelection(0);
                    else if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("fr_CH") || Locale.getDefault().getLanguage().equals("fr_CA"))
                        sp.setSelection(2);
                    else if (Locale.getDefault().getLanguage().equals("es")) sp.setSelection(3);
                    else sp.setSelection(0);

                }
            }
            else {


                if (Locale.getDefault().getLanguage().equals("tr")) sp.setSelection(1);
                else if (Locale.getDefault().getLanguage().equals("en")) sp.setSelection(0);
                else if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("fr_CH") || Locale.getDefault().getLanguage().equals("fr_CA"))
                    sp.setSelection(2);
                else if (Locale.getDefault().getLanguage().equals("es")) sp.setSelection(3);
                else sp.setSelection(0);



            }*/

            if (gamelang == ENGLISH) sp.setSelection(0);
            else if (gamelang == TURKISH) sp.setSelection(1);
            else if (gamelang == FRENCH) sp.setSelection(2);
            else if (gamelang == SPANISH) sp.setSelection(3);
            else if (gamelang == RUSSIAN) sp.setSelection(4);
            else if (gamelang == CHINESE) sp.setSelection(5);
            else if (gamelang == JAPANESE) sp.setSelection(6);
            else if (gamelang == PORTUGUESE) sp.setSelection(7);

            else {



                if (Locale.getDefault().getLanguage().equals("tr")) sp.setSelection(1);
                else if (Locale.getDefault().getLanguage().equals("en")) sp.setSelection(0);
                else if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("fr_CH") || Locale.getDefault().getLanguage().equals("fr_CA"))
                    sp.setSelection(2);
                else if (Locale.getDefault().getLanguage().equals("es")) sp.setSelection(3);
                else if (Locale.getDefault().getLanguage().equals("ru_RU") || Locale.getDefault().getLanguage().equals("ru")) sp.setSelection(4);
                else if (Locale.getDefault().getLanguage().equals("zh_CN)") || Locale.getDefault().getLanguage().equals("zh_TW")) sp.setSelection(5);
                else if (Locale.getDefault().getLanguage().equals("ja_JP)") || Locale.getDefault().getLanguage().equals("ja")) sp.setSelection(6);
                else if (Locale.getDefault().getLanguage().equals("pt_BR)") || Locale.getDefault().getLanguage().equals("pt_PT")) sp.setSelection(7);
                else sp.setSelection(0);

            }



            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView arg0, View arg1,
                                           int arg2, long arg3) {
                    Configuration config = new Configuration();
                    switch (arg2) {


                        case 0:
                         //   if(!mutestate) clickaudio.start();
                            updateEnglish();

                            break;


                        case 1:

                           // if(!mutestate) clickaudio.start();
                            updateTurkish();

                            break;

                        case 2:
                          //  if(!mutestate) clickaudio.start();
                            updateFrench();
                            break;
                        case 3:
                          //  if(!mutestate) clickaudio.start();
                            updateSpanish();
                            break;
                        case 4:
                            //   if(!mutestate) clickaudio.start();
                            updateRussian();

                            break;


                        case 5:

                            // if(!mutestate) clickaudio.start();
                            updateChinese();

                            break;

                        case 6:
                            //  if(!mutestate) clickaudio.start();
                            updateJapanese();
                            break;
                        case 7:
                            //  if(!mutestate) clickaudio.start();
                            updatePortegese();
                            break;

                        default:

                            break;
                    }
                    getResources().updateConfiguration(config, null);
                }

                public void onNothingSelected(AdapterView arg0) {
                    // TODO Auto-generated method stub

                }
            });




          //  tf = Typeface.createFromAsset(getAssets(), "sans.ttf");
            final TextView heading = (TextView) findViewById(R.id.textView11);


            //heading.setTypeface(tf);
            heading.setTextSize(25);
            final Button b6 = (Button ) findViewById(R.id.button6);
            final Button faqbutton = (Button) findViewById(R.id.faqbutton);
            final Button quitbutton = (Button) findViewById(R.id.quitbutton);

            final Button Options = (Button) findViewById(R.id.option);
            final TextView t = (TextView) findViewById(R.id.textView6);
            final Button b5 = (Button ) findViewById(R.id.button5);
            final Button bnext = (Button ) findViewById(R.id.button12);
           final RelativeLayout rel1 = (RelativeLayout) findViewById(R.id.mainlayout);
            final RelativeLayout optlayout = (RelativeLayout) findViewById(R.id.Optionlayout);
            final RelativeLayout faqlayout = (RelativeLayout) findViewById(R.id.faqlayout);

            final Button cancel = (Button) findViewById(R.id.cancelbtn);
            final Button backbutton = (Button) findViewById(R.id.button22);





            //options.inJustDecodeBounds = true;



            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        getPackageName(),
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            }
            catch (PackageManager.NameNotFoundException e) {

            }
            catch (NoSuchAlgorithmException e) {

            }

            initInstances();
            initCallbackManager();
            refreshButtonsState();


           final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);


            buttoncontact.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            buttoncontact.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);


                            if (isOnline()) {
                            try{
                                Intent googleintent = new Intent(Intent.ACTION_VIEW);
                                //String facebookUrl = getFacebookPageURL(x);
                                googleintent.setData(Uri.parse("http://www.vivobros.com/contact.html"));
                                startActivity(googleintent);}

                            catch (Exception e){}

                            }
                            else alertbox();



                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            buttoncontact.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });







            Options.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            Options.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            STATE = OPTIONEXIT;
                            rel1.setVisibility(View.INVISIBLE);
                            optlayout.setVisibility(View.VISIBLE);
                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            Options.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });


            backbutton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            backbutton.setAlpha(BALPHA);
                            STATE = MAINMENU;
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            faqlayout.setVisibility(View.INVISIBLE);
                            rel1.setVisibility(View.VISIBLE);
                            optlayout.setVisibility(View.INVISIBLE);

                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            backbutton.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });
            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            final SharedPreferences.Editor editor = pref.edit();
            final SwitchButton sound = (SwitchButton) findViewById(R.id.switch2);
            final SwitchButton notificationswitch = (SwitchButton) findViewById(R.id.switch1);

            editor.putBoolean("gamestop", false);
           // insertDummyContactWrapper();

            //editor.putBoolean("mutestate", false);
            //notification=shared1.getBoolean("notification",notification);
            //vibration=shared1.getBoolean("vibration",vibration);
            //mutestate=shared1.getBoolean("mutestate", mutestate);
            editor.commit();
           // final Button signin = (Button) findViewById(R.id.sign_in_button);
           // final Button signout = (Button) findViewById(R.id.sign_out_button);
            final SwitchButton vibrationswitch = (SwitchButton) findViewById(R.id.switch3);

            if (notification == true) {
                notificationswitch.setChecked(true);

            }

            else notificationswitch.setChecked(false);

            if (vibration == true) {
                vibrationswitch.setChecked(true);

            }

            else vibrationswitch.setChecked(false);

            if (mutestate == true) {
                sound.setChecked(false);

            }

            else sound.setChecked(true);
            vibrationswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked == false) {
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        vibration = false;
                        editor.putBoolean("vibration", vibration);
                        editor.commit();

                    } else

                    {
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        vibration = true;
                        vibrate(250);

                        editor.putBoolean("vibration", vibration);
                        editor.commit();

                    }


                }
            });

            //editor.putBoolean("vibration", vibration);
            //editor.commit();
       /*     signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!BaseGameUtils.verifySampleSetup(MainActivity.this, R.string.app_id)) {
                        Log.w(TAG, "*** Warning: setup problems detected. Sign in may not work!");
                    }

                    // start the sign-in flow
                    Log.d(TAG, "Sign-in button clicked");
                    mSignInClicked = true;
                    mGoogleApiClient.connect();


                    // Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_id), iq);

                }
            });*/

          /*  signout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSignInClicked = false;
                    Games.signOut(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    // show sign-in button, hide the sign-out button
                    findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
                    findViewById(R.id.sign_out_button).setVisibility(View.GONE);
                }
            });
*/




            notificationswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked == false) {
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        notification = false;
                        editor.putBoolean("notification", notification);
                        editor.commit();

                    } else

                    {
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        notification = true;
                        editor.putBoolean("notification", notification);
                        editor.commit();

                    }


                }
            });
            //editor.putBoolean("notification", notification);
            //editor.commit();

            sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences pref;
                    pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
                    SharedPreferences.Editor editor = pref.edit();
                    if (isChecked == false) {


                        optionaudio.pause();

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        mutestate = true;
                        editor.putBoolean("mutestate", mutestate);
                        editor.commit();

                    } else

                    {


                            optionaudio.start();
                            optionaudio.setLooping(true);

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        mutestate = false;
                        editor.putBoolean("mutestate", mutestate);
                        editor.commit();

                    }


                }
            });




            faqbutton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            faqbutton.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            faqlayout.setVisibility(View.VISIBLE);
                            rel1.setVisibility(View.INVISIBLE);
                            optlayout.setVisibility(View.INVISIBLE);

                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            faqbutton.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });



            quitbutton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            quitbutton.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            quitbutton.setAlpha(1f);
                            new CountDownTimer(100, 100) {
                                public void onTick(long milsec) {
                                }
                                public void onFinish() {
                                    //optionaudio.stop();
                                    //optionaudio.release();
                                    quit();
                                }
                            }.start();

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });


            cancel.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            cancel.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            rel1.setVisibility(View.VISIBLE);
                            optlayout.setVisibility(View.INVISIBLE);
                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            cancel.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });

            b5.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            b5.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                            go();
                            //quit();
                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            b5.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });

            b6.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v.setScaleX(BSCALE);
                            v.setScaleY(BSCALE);
                            b6.setAlpha(BALPHA);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                            int x = shared.getInt("procss0key", -1);
                            if (x != -1) gogame();
                            else t.setVisibility(View.VISIBLE);
                            v.setScaleX(1f);
                            v.setScaleY(1f);
                            b6.setAlpha(1f);

                            break;
                        case MotionEvent.ACTION_CANCEL: {

                            break;
                        }
                    }
                    return true;
                }
            });



            if(newgame==0){



            }

            bnext.setSoundEffectsEnabled(false);
            bnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences pref;
                    pref = getSharedPreferences("info", MODE_PRIVATE);

                    SharedPreferences.Editor editor = pref.edit();

                    //Chractername=e1.getText().toString();
                    // editor.putString("charactername", Chractername);
                    editor.commit();
                    go();
                }
            });

            //editor.commit();
        }

    private void initInstances() {
        //rel = (Button) findViewById(R.id.login);

    }



    void alertbox()

    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
            messagetext = "沒有網絡連接";
            oktext = "好";

        }
        else if (gamelang == JAPANESE) {
            warningtext = "警告";
            messagetext = "インターネットに接続していない";
            oktext = "はい";

        }
        else if (gamelang == PORTUGUESE) {
            warningtext = "ATENÇÃO";
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
    @Override
    public void onBackPressed() {


        final Button b6 = (Button ) findViewById(R.id.button6);
        final Button faqbutton = (Button) findViewById(R.id.faqbutton);
        final Button quitbutton = (Button) findViewById(R.id.quitbutton);

        final Button Options = (Button) findViewById(R.id.option);
        final TextView t = (TextView) findViewById(R.id.textView6);
        final Button b5 = (Button ) findViewById(R.id.button5);
        final Button bnext = (Button ) findViewById(R.id.button12);
        final RelativeLayout rel1 = (RelativeLayout) findViewById(R.id.mainlayout);
        final RelativeLayout optlayout = (RelativeLayout) findViewById(R.id.Optionlayout);
        final RelativeLayout faqlayout = (RelativeLayout) findViewById(R.id.faqlayout);
       if (STATE == MAINMENU)
        quit();

        else if (STATE == OPTIONEXIT)
       {

           if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
           faqlayout.setVisibility(View.INVISIBLE);
           rel1.setVisibility(View.VISIBLE);
           optlayout.setVisibility(View.INVISIBLE);
       }


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


        public void go(){

            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);

            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("backbutton", false);
            editor.putBoolean("backbuttoneyes", false);
            if(mGoogleApiClient.isConnected()==true)
            editor.putBoolean("mainsignin",true);

            else editor.putBoolean("mainsignin",false);

            editor.putBoolean("mutestate", mutestate);



            editor.commit();
            insertDummyContactWrapper();
            //int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.GET_ACCOUNTS);
        /*   if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.GET_ACCOUNTS)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.GET_ACCOUNTS)) {


                    showMessageOKCancel(GETACCOUNTSTRING,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestperm();
                                }
                            });

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    return;
                }

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.GET_ACCOUNTS},
                            MY_PERMISSIONS_GET_ACCOUNT);
               return;
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.

            }



        else {
               //optionaudio.stop();
               //optionaudio.release();

               Intent i = new Intent(this, CameraActivity.class);

               startActivity(i);
               quit();

           }
            //finish();*/

        }




    private void initCallbackManager() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                refreshButtonsState();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    private void refreshButtonsState() {
      /*  if (!isLoggedIn()) {
            rel.setVisibility(View.VISIBLE);
            likeView.setVisibility(View.INVISIBLE);
        } else {
            rel.setVisibility(View.INVISIBLE);
            likeView.setVisibility(View.VISIBLE);
        }*/
    }

    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle Facebook Login Result

        callbackManager.onActivityResult(requestCode, resultCode, data);
        FrameLayout grd = (FrameLayout) findViewById(R.id.yigit);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        final TextView iqnum = (TextView) findViewById(R.id.textiq);


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



      /*  public  void gogame()
        {

            Intent i = new Intent(this, splashscreen.class);
            int newgame = 1;
            i.putExtra("newgame", newgame);

            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            SharedPreferences.Editor editor = pref.edit();
            //editor.putBoolean("mutestate", false);
            editor.putInt("newgame",newgame);

            editor.commit();
           //quit();
            startActivity(i);

        }*/


       public  void gogame()
        {


            final Button b6 = (Button ) findViewById(R.id.button6);
            final Button faqbutton = (Button) findViewById(R.id.faqbutton);
            final Button quitbutton = (Button) findViewById(R.id.quitbutton);

            final Button Options = (Button) findViewById(R.id.option);
            final TextView t = (TextView) findViewById(R.id.textView6);
            final Button b5 = (Button ) findViewById(R.id.button5);
            final Button bnext = (Button ) findViewById(R.id.button12);
            final RelativeLayout rel1 = (RelativeLayout) findViewById(R.id.mainlayout);
            final RelativeLayout optlayout = (RelativeLayout) findViewById(R.id.Optionlayout);
            final RelativeLayout faqlayout = (RelativeLayout) findViewById(R.id.faqlayout);

            final Button cancel = (Button) findViewById(R.id.cancelbtn);
            final Button backbutton = (Button) findViewById(R.id.button22);
            final Button contact = (Button) findViewById(R.id.buttoncontact);

            b6.setEnabled(false);
            faqbutton.setEnabled(false);
            quitbutton.setEnabled(false);
            t.setEnabled(false);
            b5.setEnabled(false);
            bnext.setEnabled(false);
            cancel.setEnabled(false);
            contact.setEnabled(false);
            backbutton.setEnabled(false);
            Options.setEnabled(false);
            //Intent i = new Intent(this, GameforActivity.class);
            //int newgame = 1;
            //i.putExtra("newgame", newgame);

            Intent service = new Intent(this, MyService.class);  //your Intent localIntent = new Intent("com.test.sample");
            //intent.putExtra("alarmId", alarmId); // So we can catch the id on BroadcastReceiver
            PendingIntent alarmIntent;
            alarmIntent = PendingIntent.getBroadcast(this,
                    alarmId, service,
                    0);
            AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

            //am.cancel(alarmIntent);
            //this.stopService(new Intent(getBaseContext(), MyService.class));
            //this.stopService(new Intent(getBaseContext(), alarmService.class));


            final RelativeLayout introfrm = (RelativeLayout) findViewById(R.id.intro2);
            introfrm.setVisibility(View.VISIBLE);
            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("gamestop", false);
            editor.commit();
            //editor.putBoolean("mutestate", false);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(MainActivity.this, GameforActivity.class));
                    finish();
                }
            }, secondsDelayed * 1000);


           //quit();
           // startActivity(i);

        }


    protected void onResume() {
        super.onResume();

        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        vunglePub.onResume();
        // optionaudio=MediaPlayer.create(getApplicationContext(), R.raw.optionaudio);
        //optionaudio.start();
        // Refresh the state of the +1 button each time the activity receives focus.
        //  mPbutton_standard.initialize(APPURL, REQUEST_CODE);

        if (optionaudio != null)

        {    if (optionaudio.isPlaying()) {
                //optionaudio.pause();
            } else {
                SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);
                int newgame = shared1.getInt("newgame", 0);

                mutestate = shared1.getBoolean("mutestate", mutestate);


                if (onresume && !mutestate) {
                    optionaudio.start();
                    optionaudio.setLooping(true);
                }
            }
         }
    }


    void back()
    {
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        vunglePub.onPause();
//        optionaudio.stop();
 //       //optionaudio.release();

        if (optionaudio.isPlaying()) {
            optionaudio.pause();
        }
    }



    void intro()


    {
        final FrameLayout introfrm = (FrameLayout) findViewById(R.id.introfrm);
        final RelativeLayout introfrm2 = (RelativeLayout) findViewById(R.id.intro2);

        final Button b6 = (Button ) findViewById(R.id.button6);
        final Button faqbutton = (Button) findViewById(R.id.faqbutton);
        final Button quitbutton = (Button) findViewById(R.id.quitbutton);

        final Button Options = (Button) findViewById(R.id.option);
        final TextView t = (TextView) findViewById(R.id.textView6);
        final Button b5 = (Button ) findViewById(R.id.button5);
        final Button bnext = (Button ) findViewById(R.id.button12);
        final RelativeLayout rel1 = (RelativeLayout) findViewById(R.id.mainlayout);
        final RelativeLayout optlayout = (RelativeLayout) findViewById(R.id.Optionlayout);
        final RelativeLayout faqlayout = (RelativeLayout) findViewById(R.id.faqlayout);

        final Button cancel = (Button) findViewById(R.id.cancelbtn);
        final Button backbutton = (Button) findViewById(R.id.button22);
        final Button contact = (Button) findViewById(R.id.buttoncontact);

        b6.setEnabled(false);
        faqbutton.setEnabled(false);
        quitbutton.setEnabled(false);
        t.setEnabled(false);
        b5.setEnabled(false);
        bnext.setEnabled(false);
        cancel.setEnabled(false);
        contact.setEnabled(false);
        backbutton.setEnabled(false);
        Options.setEnabled(false);

        introfrm2.setVisibility(View.VISIBLE);
        int timeinterval = 3000;

        if (newgame == 1) timeinterval = 1500;
        else timeinterval = 3000;


        new CountDownTimer(timeinterval, 100) {
            public void onTick(long milsec) {
            }
            public void onFinish() {

                introfrm2.setVisibility(View.INVISIBLE);


                if(!mutestate){
                    optionaudio.start();
                    optionaudio.setLooping(true);
                }
                onresume=true;

                b6.setEnabled(true);
                faqbutton.setEnabled(true);
                quitbutton.setEnabled(true);
                t.setEnabled(true);
                b5.setEnabled(true);
                bnext.setEnabled(true);
                cancel.setEnabled(true);
                contact.setEnabled(true);
                backbutton.setEnabled(true);
                Options.setEnabled(true);

            }
        }.start();


    }

    void introgogame()


    {
        final FrameLayout introfrm = (FrameLayout) findViewById(R.id.introfrm);
        final RelativeLayout introfrm2 = (RelativeLayout) findViewById(R.id.intro2);

        introfrm2.setVisibility(View.VISIBLE);



        new CountDownTimer(3000, 1000) {
            public void onTick(long milsec) {
            }
            public void onFinish() {

                introfrm2.setVisibility(View.INVISIBLE);


            }
        }.start();


    }


    public void updateEnglish(){


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", ENGLISH);

        editor.commit();


        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("CONTACT");

        textView28.setText("Project Coordinator");
        textView31.setText("Project Designer");
        textView35.setText("Chief Developer");
        textView56.setText("Chief Marketing Officer");
        textView14.setText("CREDITS");
        GETACCOUNTSTRING = "You need following permissions:";
        PERMISSIONDENIED = "Permission is Denied";
        READANDWRITE = "Read and write to storage";
        CAMERA = "Camera";
        GETACCOUNT = "Acount information";

        button5.setText("CREATE CHARACTER");
        option.setText("OPTIONS");
        faqbutton.setText("CREDITS");
        quitbutton.setText("QUIT");
        Textview9.setText("Notification");
        Textview10.setText("Sound");
        Textview11.setText("OPTIONS");
        Textview14.setText("CREDITS");
        Textview33.setText("Vibration");
        Textview53.setText("Language");

    }
    public void updateTurkish() {


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", TURKISH);

        editor.commit();


        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);

        GETACCOUNTSTRING = "Belirtilen izinler gereklidir:";
        PERMISSIONDENIED = "İzin verilmedi.";
        READANDWRITE = "Hafızaya yazma ve okuma ";
        CAMERA = "Kamera";
        GETACCOUNT = "Hesap bilgileri";


        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);

        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("İLETİŞİM");
        textView28.setText("Proje Koordinatörü");
        textView31.setText("Proje Yaratıcısı");
        textView35.setText("Baş Yazılımcı ");
        textView56.setText("Pazarlama Direktörü");
        textView14.setText("HAKKIMIZDA");

        button5.setText("KARAKTER YARAT");
        option.setText("SEÇENEKLER");
        faqbutton.setText("KATKISI OLANLAR");
        quitbutton.setText("ÇIKIŞ");
        Textview9.setText("Bildirim");
        Textview10.setText("Ses");
        Textview11.setText("SEÇENEKLER");
        Textview14.setText("HAKKIMIZDA");
        Textview33.setText("Titreşim");
        Textview53.setText("Dil Seçeneği");




    }
    public void updateSpanish(){



        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", SPANISH);

        editor.commit();

        GETACCOUNTSTRING = "Necesita permisos Siguientes:";
        PERMISSIONDENIED = "Permiso denegado";
        READANDWRITE = "Leer y escribir en el almacenamiento";
        CAMERA = "cámara";
        GETACCOUNT = "Información de la cuenta";
        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);


        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);

        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("CONTACTO");


        button5.setText("CREAR CARÁCTER");
        option.setText("OPCIONES");
        faqbutton.setText("CRÉDITOS");
        quitbutton.setText("Dejar");
        Textview9.setText("Notificación");
        Textview10.setText("Sonido");
        Textview11.setText("OPCIONES");
        Textview14.setText("CRÉDITOS");
        Textview33.setText("Vibración");
        Textview53.setText("Idioma");




    }
    public void updateFrench(){



        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", FRENCH);

        editor.commit();

        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);


        GETACCOUNTSTRING = "Vous avez besoin des autorisations suivantes:";

        PERMISSIONDENIED = "L'autorisation est refusée";
        READANDWRITE = "Lire et écrire dans la mémoire";
        CAMERA = "Caméra";
        GETACCOUNT = "Compte infromation";


        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("CONTACT");

        textView28.setText("Coordinateur de Projet");
        textView31.setText("Project Creator");
        textView35.setText("Développeur en Chef");
        textView56.setText("Directeur marketing");
        textView14.setText("CRÉDITS");



        button5.setText("CRÉER CARACTÈRE");
        option.setText("OPTIONS");
        faqbutton.setText("CRÉDITS");
        quitbutton.setText("Dejar");
        Textview9.setText("Notification");
        Textview10.setText("Son");
        Textview11.setText("OPTIONS");
        Textview14.setText("CRÉDITS");
        Textview33.setText("Vibration");
        Textview53.setText("Langue");


    }


    public void updateRussian(){


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", RUSSIAN);

        editor.commit();

        GETACCOUNTSTRING = "Вам необходимо следующие разрешения:";
        PERMISSIONDENIED = "разрешение отказано";
        READANDWRITE = "чтения и записи в память";
        CAMERA = "камера";
        GETACCOUNT = "Информация об аккаунте";


        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);

        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("КОНТАКТ");

        textView28.setText("Координатор проекта");
        textView31.setText("разработчик проекта");
        textView35.setText("Главный разработчик");
        textView56.setText("Директор по маркетингу");
        textView14.setText("КРЕДИТЫ");


        button5.setText("СОЗДАТЬ ПЕРСОНАЖА");
        option.setText("ОПЦИИ");
        faqbutton.setText("КРЕДИТЫ");
        quitbutton.setText("УВОЛИТЬСЯ");
        Textview9.setText("уведомление");
        Textview10.setText("звук");
        Textview11.setText("ОПЦИИ");
        Textview14.setText("КРЕДИТЫ");
        Textview33.setText("вибрация");
        Textview53.setText("язык");

    }



    public void updateChinese(){

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", CHINESE);

        editor.commit();


        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("联系");

        GETACCOUNTSTRING = "您需要以下权限：";
        PERMISSIONDENIED = "权限被拒绝";
        READANDWRITE = "读写存储";
        CAMERA = "相机";
        GETACCOUNT = "帐户信息";

        button5.setText("创造性格");
        option.setText("选项");
        faqbutton.setText("信用");
        quitbutton.setText("放弃");
        Textview9.setText("通知");
        Textview10.setText("声音");
        Textview11.setText("选项");
        Textview14.setText("信用");
        Textview33.setText("振动");
        Textview53.setText("语言");


    }



    public void updateJapanese(){


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", JAPANESE);

        editor.commit();

        GETACCOUNTSTRING = "次の権限が必要です：";

        PERMISSIONDENIED = "許可が拒否されました";
        READANDWRITE = "メモリへの読み取りおよび書き込み";
        CAMERA = "カメラ";
        GETACCOUNT = "口座情報";


        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);

        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("接触");

        textView28.setText("プロジェクトコーディネーター");
        textView31.setText("プロジェクトデザイナー");
        textView35.setText("チーフデベロッパー");
        textView56.setText("チーフマーケティングオフィサー");
        textView14.setText("クレジット");


        button5.setText("キャラクターを作る");
        option.setText("オプション");
        faqbutton.setText("クレジット");
        quitbutton.setText("やめます");
        Textview9.setText("お知らせ");
        Textview10.setText("音");
        Textview11.setText("オプション");
        Textview14.setText("クレジット");
        Textview33.setText("振動");
        Textview53.setText("言語");

    }



    public void updatePortegese(){


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("gamelang", PORTUGUESE);

        editor.commit();

        GETACCOUNTSTRING = "Você precisa seguintes permissões:";

        PERMISSIONDENIED = "a permissão é negada";
        READANDWRITE = "ler e escrever para a memória";
        CAMERA = "Câmera";
        GETACCOUNT = "Informação da conta";

        Button button5 = (Button) findViewById(R.id.button5);
        Button option = ( Button) findViewById(R.id.option);
        Button faqbutton = (Button) findViewById(R.id.faqbutton);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        TextView Textview9 = (TextView) findViewById(R.id.textView9);
        TextView Textview10 = (TextView) findViewById(R.id.textView10);
        TextView Textview11 = (TextView) findViewById(R.id.textView11);
        TextView Textview14 = (TextView) findViewById(R.id.textView14);
        TextView Textview33 = (TextView) findViewById(R.id.textView33);
        TextView Textview53 = (TextView) findViewById(R.id.textView53);
        TextView textView28 = (TextView) findViewById(R.id.textView28);
        TextView textView31 = (TextView) findViewById(R.id.textView31);
        TextView textView35 = (TextView) findViewById(R.id.textView35);
        TextView textView56 = (TextView) findViewById(R.id.textView56);
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        final Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        buttoncontact.setText("CONTATO");
        textView28.setText("Coordenador de projeto");
        textView31.setText("Designer de projeto");
        textView35.setText("Cesenvolvedor -Chefe");
        textView56.setText("Diretor de marketing");
        textView14.setText("CRÉDITOS");


        button5.setText("СRIAR CARÁTER");
        option.setText("OPÇÕES");
        faqbutton.setText("CRÉDITOS");
        quitbutton.setText("SAIR");
        Textview9.setText("Notificação");
        Textview10.setText("Som");
        Textview11.setText("OPÇÕES");
        Textview14.setText("CRÉDITOS");
        Textview33.setText("Vibração");
        Textview53.setText("Língua");

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

           // case MY_PERMISSIONS_GET_ACCOUNT: {
                // If request is cancelled, the result arrays are empty.
            /*   if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent i = new Intent(this,CameraActivity.class);
                    startActivity(i);
                    quit();

                } else {*/


                    /*ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.GET_ACCOUNTS},
                            MY_PERMISSIONS_GET_ACCOUNT);*/
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
               // }
               // return;



//            }

            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
            {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.GET_ACCOUNTS, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    Intent i = new Intent(this,CameraActivity.class);
                    startActivity(i);
                    quit();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, PERMISSIONDENIED, Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
public void onDestroy(){

    super.onDestroy();
    //logovivo.recycle();
    logovivo2.recycle();
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("onresume", false);
        editor.commit();
}


    void quit()

{
    finish();
    //System.exit(0);
}


    public void vibrate(int duration)
    {

        if(vibration==true)
        {
            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibs.vibrate(duration);}
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void requestperm()
    {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.GET_ACCOUNTS},
                MY_PERMISSIONS_GET_ACCOUNT);


    }


    private void insertDummyContactWrapper() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.GET_ACCOUNTS))
            permissionsNeeded.add(GETACCOUNT);
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add(READANDWRITE);
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add(CAMERA);

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = GETACCOUNTSTRING + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(this,permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

        Intent i = new Intent(this,CameraActivity.class);
        startActivity(i);
        quit();
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,permission))
                return false;
        }
        return true;
    }





}
