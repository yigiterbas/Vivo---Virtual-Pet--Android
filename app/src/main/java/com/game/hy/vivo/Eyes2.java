package com.game.hy.vivo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.vision.Frame;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Random;


public class Eyes2 extends Activity {
    private DragEllipseView view,view2,view3,view4;
    int BallID;
    int GroupID;
    boolean lefteyeb,righteyeb,mouthb,chinb;

    int STATE = 1;
    final int MAINSTATE = 1;
    final int PREVIEWSTATE = 2;
    final int VIVOPASSSTATE = 3;

    int var = 1;

    private int soundID,soundID2;
    private SoundPool soundPool;
    private SoundPool soundPool2;
    AudioManager audioManager;
    float actVolume,maxVolume,volume;
     Bitmap setBitmap;
    boolean backbuttoneyes= false;
    String name="";
    Point p1x,p2x,p3,p4;
    int eyesx1,eyesx2,eyesy1,eyesy2;
    int x1,x2,y1,y2;
    int strx21,strx22,stry21,stry22;
    int streyesx1,streyesx2,streyesy1,streyesy2;
    int pupilx,pupilx1,pupily,pupily1;
    Float BSCALE = 1f;
    MyImageView im;
    Float BALPHA = 0.7f;
    String displayname = "";
    int NONE= 100;
    Drawable x;
    Bitmap resizedBitmap1,resizedBitmap;
    int ENGLISH = 0;
    int TURKISH =1 ;
    int FRENCH =2;
    int SPANISH =3;
    int RUSSIAN =4;
    int CHINESE =5 ;
    int JAPANESE = 6;
    int PORTUGUESE =7;
    double m5x1,m5x2,m5y1,m5y2;
    int gamelang = NONE;
    int SLIDEANIMDUR=600;
    double m6x1,m6x2,m6y1,m6y2;
    Bitmap setBitmap1;
    private AdView mAdView;
    int strpupilx,strpupilx1,strpupily,strpupily1;
    int pupil2x,pupil2x1,pupil2y,pupil2y1;
    int strpupil2x,strpupil2x1,strpupil2y,strpupil2y1;
    int mouthx,mouthx1,mouthy,mouthy1;
    boolean movementm2=false;
    boolean movementm=false;
    boolean movementm5=false;
    boolean movementm6=false;
    int eyesXcoordinate,eyesXcoordinate2,eyesYcoordinate,eyesYcoordinate2;
    int eyesX2coordinate,eyesX2coordinate2,eyesY2coordinate,eyesY2coordinate2;
    int frameheight= 450;
    int pupil2X,pupil2Y,pupil2X1,pupil2Y1;
    int pupilX,pupilY,pupilX1,pupilY1;
    int pupilXnew,pupilYnew,pupilX1new,pupilY1new;

    int YCOORDINATE = 134;
    int EYESYCOORDINATE = 50;
    int puteyesx1,puteyesx2,puteyesy1,puteyesy2;

    int putpupilx,putpupilx1,putpupily,putpupily1;
    int putpupil2x,putpupil2x1,putpupil2y,putpupil2y1;
    int putmouthx,putmouthx1,putmouthy,putmouthy1;
    int putx21,putx22,puty21,puty22;
    int putx1,putx2,puty1,puty2;
    String CharacterName;
    int putxx1,putxx2,putyy1,putyy2;


    int putchinx,putchinx1,putchiny,putchiny1;
    MediaPlayer optionaudio;
    int mouthrealx,mouthrealx1,mouthrealy,mouthrealy1;
    int chinx,chinx1,chiny,chiny1;
    VideoView videoView;
    MediaController mediaController;
    MediaController mediaController2;
    VideoView videoView2;
    float height;
    float width;
    float realwidth,realheight;
    int x21,x22,y21,y22;
    final int[] eyes1={0,0,0,0};
    int newgame;
    int ID;
    int myValue;
    int myValuey;
    BitmapFactory.Options options;
    int p2xx;
    int p2y;
    int p3x;
    int p3y;
    int p4x;
    int p4y;
    int px1,py1,px2,py2;
    int p2x1,p2y1,p2x2,p2y2;
    int ix1,ix2,iy1,iy2;
    int i2x1,i2x2,i2y1,i2y2;
    Boolean mutestate = false;
    MyImageView righteyeview;
    MyImageView rightpupilview;
    MyImageView lefteyeview;
    MyImageView leftpupilview;
    MyImageView mouthview;

    protected void onResume()

    {        if (mAdView != null) {
        mAdView.resume();
    }

        super.onResume();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                //mp.release();

            }
        });

    }

    public void onPause() {

        if (mAdView != null) {
            mAdView.pause();
        }

        super.onPause();

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.pause();
                //mp.release();

            }
        });

    }
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_eyes2);


        mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("353720052948557")
                .build();


        mAdView.loadAd(adRequest);


        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                getWindow().getDecorView().setSystemUiVisibility(
                        //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }

            public void onFinish() {

            }

        }.start();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        final float density1 = Resources.getSystem().getDisplayMetrics().density;
        final float cony = (450*density1);
        final int rx= getWindowManager().getDefaultDisplay().getWidth();

        final Button skiptochoose = (Button) findViewById(R.id.button30);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);
        final RelativeLayout introlayout = (RelativeLayout) findViewById(R.id.introlayout);
        final Button introbutton = (Button) findViewById(R.id.introbutton);

        final Button lefteye = (Button) findViewById(R.id.lefteye);
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.Previewglobal);
        rel.setVisibility(View.INVISIBLE);

        final Button righteye = (Button) findViewById(R.id.righteye);
        final Button mouth = (Button) findViewById(R.id.mouth);
        final Button chin = (Button) findViewById(R.id.Chin);
        optionaudio=MediaPlayer.create(getApplicationContext(), R.raw.optionaudio);
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

        soundPool2 = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundPool2.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {



            }

        });

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                getWindow().getDecorView().setSystemUiVisibility(
                        //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);
            }

            public void onFinish() {

            }

        }.start();



        SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);
        soundID = soundPool.load(this, R.raw.click2017, 1);
        soundID2 = soundPool2.load(this, R.raw.optionaudio, 1);
        Boolean mute2 = shared1.getBoolean("mutestate", false);
        mutestate = mute2;
        //if (!mutestate) soundPool2.play(soundID2, volume, volume, 1, 0, 1f);
        /*    optionaudio = MediaPlayer.create(getApplicationContext(), R.raw.optionaudio);
            if (!mutestate) {
                optionaudio.start();
                optionaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        optionaudio.release();

                    }

                    ;
                });
            }*/

        ix1= (int) (density1* 230);
        ix2= (int) (density1* 13*6);
        iy1= (int) (density1*30);
        iy2= (int) (density1* 7*6);

        i2x1= (int) (density1* 30);
        i2x2= (int) (density1* 13*6);
        i2y1= (int) (density1*30);
        i2y2= (int) (density1* 7*6);



        options = new BitmapFactory.Options();
        //options.inSampleSize = 2;
        options.inPreferredConfig =  Bitmap.Config.RGB_565;

        Bundle params = new Bundle();
        params.putString("image_name", "eyeschoose");
        params.putString("full_text", "eyeschoose");
        mFirebaseAnalytics.logEvent("eyeschoose", params);


        int newgame=shared1.getInt("newgame", 0);

        gamelang = shared1.getInt("gamelang", NONE);
        if (gamelang == ENGLISH) updateEnglish();
        else if (gamelang == TURKISH) updateTurkish();
        else if (gamelang == FRENCH) updateFrench();
        else if (gamelang == SPANISH) updateSpanish();
            else if (gamelang == RUSSIAN) updaterussian();
            else if (gamelang == CHINESE) updatechinese();
            else if (gamelang == JAPANESE) updatejapanese();
            else if (gamelang == PORTUGUESE) updateportuguse();
            else updateEnglish();



        puteyesx1=shared1.getInt("puteyesx1", 0);
        puteyesy1=shared1.getInt("puteyesy1", 0);
        puteyesx2=shared1.getInt("puteyesx2", 0);
        puteyesy2=shared1.getInt("puteyesy2", 0);

        putx21=shared1.getInt("putx21", 0);
        putx22=shared1.getInt("putx22", 0);
        puty21=shared1.getInt("puty21", 0);
        puty22=shared1.getInt("puty22", 0);

        putx1=shared1.getInt("putx1", 0);
        putx2=shared1.getInt("putx2", 0);
        puty1=shared1.getInt("puty1", 0);
        puty2=shared1.getInt("puty2", 0);

        strx21=shared1.getInt("strx21", 0);
        strx22=shared1.getInt("strx22", 0);
        stry21=shared1.getInt("stry21", 0);
        stry22=shared1.getInt("stry22", 0);

        putxx1=shared1.getInt("putxx1", 0);
        putxx2=shared1.getInt("putxx2", 0);
        putyy1=shared1.getInt("putyy1", 0);
        putyy2=shared1.getInt("putyy2", 0);

        putpupilx=shared1.getInt("putpupilx", 0);
        putpupily=shared1.getInt("putpupily", 0);
        putpupilx1=shared1.getInt("putpupilx1", 0);
        putpupily1=shared1.getInt("putpupily1", 0);

        putpupil2x=shared1.getInt("putpupil2x", 0);
        putpupil2y=shared1.getInt("putpupil2y", 0);
        putpupil2x1=shared1.getInt("putpupil2x1", 0);
        putpupil2y1=shared1.getInt("putpupil2y1", 0);

        strpupilx=shared1.getInt("strpupilx", 0);
        strpupily=shared1.getInt("strpupily", 0);
        strpupilx1=shared1.getInt("strpupilx1", 0);
        strpupily1=shared1.getInt("strpupily1", 0);

        strpupil2x=shared1.getInt("strpupil2x", 0);
        strpupil2y=shared1.getInt("strpupil2y", 0);
        strpupil2x1=shared1.getInt("strpupil2x1", 0);
        strpupil2y1=shared1.getInt("strpupil2y1", 0);

        streyesx1=shared1.getInt("streyesx1", 0);
        streyesy1=shared1.getInt("streyesy1", 0);
        streyesx2=shared1.getInt("streyesx2", 0);
        streyesy2=shared1.getInt("streyesy2", 0);

        putmouthx=shared1.getInt("putmouthx", 0);
        putmouthy=shared1.getInt("putmouthy", 0);
        putmouthx1=shared1.getInt("putmouthx1", 0);
        putmouthy1=shared1.getInt("putmouthy1", 0);

        putchinx=shared1.getInt("putchinx", 0);
        putchiny=shared1.getInt("putchiny", 0);
        putchinx1=shared1.getInt("putchinx1", 0);
        putchiny1=shared1.getInt("putchiny1", 0);

        backbuttoneyes= shared1.getBoolean("backbuttoneyes", false);

        final        Button bpreview = (Button) findViewById(R.id.button);
        final   Button bback = (Button) findViewById(R.id.button26);
        introbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        introbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        //soundPool2.setVolume(soundID2, 1f, 1f);
                       // if (!mutestate) soundPool2.play(soundID2, volume, volume, 1, 0, 1f);
                      //  optionaudio = MediaPlayer.create(getApplicationContext(), R.raw.optionaudio);
                     /*   if(!mutestate) {
                            optionaudio.start();
                            optionaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    optionaudio.release();

                                }

                                ;
                            });
                        }*/
                        videoView2.start();

                        introlayout.setVisibility(View.VISIBLE);
                        introbutton.setVisibility(View.INVISIBLE);
                        skiptochoose.setVisibility(View.VISIBLE);
                        buttonbacktoface.setVisibility(View.VISIBLE);
                        bpreview.setVisibility(View.INVISIBLE);
                        bback.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        introbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });


       final Button bintro = (Button) findViewById(R.id.introbutton);

        bpreview.setVisibility(View.INVISIBLE);
        bback.setVisibility(View.INVISIBLE);
        bintro.setVisibility(View.INVISIBLE);

        skiptochoose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        skiptochoose.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                   //  if(optionaudio != null && optionaudio.isPlaying())   optionaudio.pause();
                        videoView2.pause();
                       // soundPool2.setVolume(soundID2, 0f, 0f);

                      // soundPool2.autoPause();
                        Bundle params = new Bundle();
                        params.putString("image_name", "skipchoose");
                        params.putString("full_text", "skipchoose");
                        mFirebaseAnalytics.logEvent("skipchoose", params);
                        introlayout.setVisibility(View.GONE);
                        introbutton.setVisibility(View.VISIBLE);
                        skiptochoose.setVisibility(View.INVISIBLE);
                        buttonbacktoface.setVisibility(View.INVISIBLE);
                        bpreview.setVisibility(View.VISIBLE);
                        bback.setVisibility(View.VISIBLE);
                        //bintro.setVisibility(View.VISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        skiptochoose.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });

        //Typeface brow = Typeface.createFromAsset(getAssets(), "browbold.ttf");

        final TextView header = (TextView) findViewById(R.id.textView58);
        final TextView header2 = (TextView) findViewById(R.id.textView88);
        //header2.setTypeface(brow);
        header2.setTextSize(25);
        //header.setTypeface(brow);
        header.setTextSize(25);
        name=shared1.getString("chname", name);
        displayname=shared1.getString("displayname", displayname);
        //final Bitmap setBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.foto);
        Matrix matrix1 = new Matrix();
        String root = Environment.getExternalStorageDirectory().toString();
        String photoPath = root +"/application/vivo/vivofiles/junk/" + ".pic2.jpg";
        setBitmap = BitmapFactory.decodeFile(photoPath,options);
        resizedBitmap=setBitmap;
        resizedBitmap1=setBitmap;

         height=setBitmap.getHeight();
         width=setBitmap.getWidth();



        final TextView debug = (TextView) findViewById(R.id.textView8722);
       final TextView debug2 = (TextView) findViewById(R.id.textView8723);
        //matrix1.postScale(cony / height, cony / height);



        if(setBitmap.getHeight()<=setBitmap.getWidth())

        {

            matrix1.postScale(rx / width, rx / width);
        }

        else {

            matrix1.postScale(cony / height, cony / height);

        }

        setBitmap1 = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);
        realwidth=setBitmap1.getWidth();
        realheight=setBitmap1.getHeight();

       // debug.setText(Float.toString(setBitmap.getWidth()));
       // debug2.setText(Float.toString(setBitmap.getHeight()));


        //final Bitmap setBitmap1 = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);

        //Log.d("setbtmap:height:",Integer.toString())

        final ImageView i1 = (ImageView) findViewById(R.id.collageBgView);
        i1.setImageBitmap(setBitmap1);
        ImageView i2 = (ImageView) findViewById(R.id.imageView2);
        final RelativeLayout frmright = (RelativeLayout) findViewById(R.id.RightEyeFrm);
        final RelativeLayout frmleft = (RelativeLayout) findViewById(R.id.LeftEyeFrm);
        final RelativeLayout mouthfrm = (RelativeLayout) findViewById(R.id.mouthfrm);

        final CollageView c = (CollageView) findViewById(R.id.collageView2);
       final CollageViewHalfEllipsearc cchin =(CollageViewHalfEllipsearc) findViewById(R.id.collageView1);
        final CollageView c3 = (CollageView) findViewById(R.id.collageView3);

        final CollageViewHalfEllipse c4 = (CollageViewHalfEllipse) findViewById(R.id.collageView4);

        final TextView ttext= (TextView) findViewById(R.id.textView3);

        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.ad);
        final RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.ad2);

        lefteyeview = new MyImageView(this);
        leftpupilview = new MyImageView(this);
        righteyeview = new MyImageView(this);
        rightpupilview = new MyImageView(this);
        mouthview = new MyImageView(this);


        righteyeview.setheight(450*density1);
        rightpupilview.setheight(84*density1);

        im = new MyImageView(this);
        im.setheight(450*density1);
        lefteyeview.setheight(450*density1);
        leftpupilview.setheight(84*density1);
        //leftpupilview.setWidth(156*density1);
        leftpupilview.setboundaryx((int)(rx- 156*density1));


        mouthview.setheight(450*density1);



        rightpupilview.setWidth(156*density1);

        rl.addView(im);
        rl.addView(lefteyeview);
        rl.addView(mouthview);
        rl.addView(righteyeview);

        rl2.addView(leftpupilview);
        rl2.addView(rightpupilview);

       final CollageView c5 =(CollageView) findViewById(R.id.collageView5);
        final CollageViewHalfEllipse cmouth =(CollageViewHalfEllipse) findViewById(R.id.collagemouth);

        final CollageView c6 =(CollageView) findViewById(R.id.collageView6);
        final MultiTouchListener m = new MultiTouchListener();
        final MultiTouchListener mchin = new MultiTouchListener();

        MultiTouchListener m1 = new MultiTouchListener();
        final  MultiTouchListener m2 = new MultiTouchListener();

        Drawable drawable = getResources().getDrawable(R.mipmap.ellipsechooser2);
        Drawable drawable2 = getResources().getDrawable(R.mipmap.ellipsechooser2);
        Drawable drawable3 = getResources().getDrawable(R.mipmap.circlechoose22);
        Drawable drawable4 = getResources().getDrawable(R.mipmap.circlechoose32);


        final float density2 = Resources.getSystem().getDisplayMetrics().density;
        double screenwitdh= getWindowManager().getDefaultDisplay().getWidth();
        int screenheright =(int) (density2*450);
        screenwitdh= screenwitdh/screenheright * 450;
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) drawable2).getBitmap();
        Bitmap bitmap3 = ((BitmapDrawable) drawable3).getBitmap();
        Bitmap bitmap4 = ((BitmapDrawable) drawable4).getBitmap();
// Scale it to 50 x 50
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int)(density1*6*13), (int)(density1*6*7), true));
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, (int)(density1*50), (int)(density1*40), true));

        Drawable d3 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap3, (int)(density1*170), (int)(density1*170), true));
        Drawable d4 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap4, (int)(density1*100), (int)(density1*40), true));

        c.setImageDrawable(d);
        c3.setImageDrawable(d);

        c5.setImageDrawable(d2);
        c6.setImageDrawable(d2);

        cmouth.setImageDrawable(d4);
        //cchin.setImageDrawable(d3);
        cchin.setImageDrawable(d3);

        final MultiTouchListener m3 = new MultiTouchListener();
        final MultiTouchListener m5 = new MultiTouchListener();
        final MultiTouchListener m6 = new MultiTouchListener();
        final MultiTouchListener mmouth = new MultiTouchListener();

        mchin.isRotateEnabled=false;
        m1.isRotateEnabled = false;
        //m1.isTranslateEnabled=false;
        m1.maximumScale = 2f;
        m1.minimumScale = 1f;

        //Typeface tf = Typeface.createFromAsset(getAssets(), "browbold.ttf");
        TextView headermain = (TextView) findViewById(R.id.textView89);

        //headermain.setTypeface(tf);
        headermain.setTextSize(25);

        m.maximumScale = 2f;
        m.isRotateEnabled = false;
        m2.maximumScale = 2f;
        m2.isRotateEnabled=false;


        righteyeview.setimage(d);
        rightpupilview.setimage(d2);

        lefteyeview.setimage(d);
        leftpupilview.setimage(d2);
        mouthview.setimage(d4);

        lefteyeview.setbx((int)(220*density1));
        lefteyeview.setby((int)(density1*150));
        leftpupilview.setbx(rx- (int)(density1*136));
        leftpupilview.setby((int)(density1*20));

        righteyeview.setbx((int)(density1*80));
        righteyeview.setby((int)(density1)*150);
        rightpupilview.setbx((int)(density1*20));
        rightpupilview.setby((int)(density1*20));


        mouthview.setbx((int)((rx/2) - (int)(density1*50)));
        mouthview.setby((int)(density1*300));

        im.setimage(d3);
        im.setbx((int)((rx/2) - (int)(density1)*85));
        im.setby((int)(density1*250));


        m3.isRotateEnabled=false;
        m5.isRotateEnabled=false;
        m6.isRotateEnabled=false;

        m1.selectarea=1;
        m1.screenwidth= getWindowManager().getDefaultDisplay().getWidth();
        m2.selectarea=1;
        m2.screenwidth= getWindowManager().getDefaultDisplay().getWidth();
        m3.selectarea=1;
        m3.screenwidth= getWindowManager().getDefaultDisplay().getWidth();
        mmouth.selectarea=1;
        mmouth.screenwidth= getWindowManager().getDefaultDisplay().getWidth();
       // mchin.selectarea=1;
        //mchin.screenwidth= getWindowManager().getDefaultDisplay().getWidth();
        m.selectarea=1;
        m.screenwidth= getWindowManager().getDefaultDisplay().getWidth();

        m5.selectarea=2;
        m5.screenwidth= getWindowManager().getDefaultDisplay().getWidth();


        m6.selectarea=3;
        m6.screenwidth= getWindowManager().getDefaultDisplay().getWidth();

        final RelativeLayout ad = (RelativeLayout) findViewById(R.id.ad2);

       // backbuttoneyes=true;

       if(backbuttoneyes==true) {

           introlayout.setVisibility(View.GONE);
           introbutton.setVisibility(View.VISIBLE);
           skiptochoose.setVisibility(View.INVISIBLE);
           buttonbacktoface.setVisibility(View.INVISIBLE);
           bpreview.setVisibility(View.VISIBLE);
           bback.setVisibility(View.VISIBLE);

           ViewGroup.MarginLayoutParams lovemargin2 = new ViewGroup.MarginLayoutParams(ad.getLayoutParams());
            lovemargin2.setMargins(putx21, puty21, 0, 0);
            RelativeLayout.LayoutParams vp = new RelativeLayout.LayoutParams(lovemargin2);
            vp.height = puty22-puty21;
            vp.width = putx22-putx21;
            c.setLayoutParams(vp);

           ViewGroup.MarginLayoutParams lovemargin3 = new ViewGroup.MarginLayoutParams(ad.getLayoutParams());
           lovemargin3.setMargins(puteyesx1, puteyesy1, 0, 0);
           RelativeLayout.LayoutParams vp2 = new RelativeLayout.LayoutParams(lovemargin3);
           vp2.height = puteyesy2-puteyesy1;
           vp2.width = puteyesx2-puteyesx1;
           c3.setLayoutParams(vp2);

           ViewGroup.MarginLayoutParams lovemargin4 = new ViewGroup.MarginLayoutParams(ad.getLayoutParams());
           lovemargin4.setMargins(putmouthx, putmouthy, 0, 0);
           RelativeLayout.LayoutParams vp3 = new RelativeLayout.LayoutParams(lovemargin4);
           vp3.height = putmouthy1-putmouthy;
           vp3.width = putmouthx1-putmouthx;
           c4.setLayoutParams(vp3);

           ViewGroup.MarginLayoutParams lovemargin5 = new ViewGroup.MarginLayoutParams(ad.getLayoutParams());
           lovemargin5.setMargins(putchinx, putchiny, 0, 0);
           RelativeLayout.LayoutParams vp4 = new RelativeLayout.LayoutParams(lovemargin5);
           vp4.height = putchiny1-putchiny;
           vp4.width = putchinx1-putchinx;
           cchin.setLayoutParams(vp4);


           ViewGroup.MarginLayoutParams lovemargin6 = new ViewGroup.MarginLayoutParams(frmright.getLayoutParams());
           lovemargin6.setMargins(putpupilx, putpupily, 0, 0);
           RelativeLayout.LayoutParams vp5 = new RelativeLayout.LayoutParams(lovemargin6);
           vp5.height = putpupily1-putpupily;
           vp5.width = putpupilx1-putpupilx;
           c5.setLayoutParams(vp5);


           ViewGroup.MarginLayoutParams lovemargin7 = new ViewGroup.MarginLayoutParams(frmleft.getLayoutParams());
           lovemargin7.setMargins(putpupil2x, putpupil2y, 0, 0);
           RelativeLayout.LayoutParams vp6 = new RelativeLayout.LayoutParams(lovemargin7);
           vp6.height = putpupil2y1-putpupil2y;
           vp6.width = putpupil2x1-putpupil2x;
           c6.setLayoutParams(vp6);


           ImageView i3 = (ImageView) findViewById(R.id.imageView);
           Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1,(int) (putx1-(rx-setBitmap1.getWidth())/2),(int) (puty1 - (cony-setBitmap1.getHeight())/2),putx2, puty2);
           //Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1, x1,y1 - (cony-setBitmap1.getHeight()),(int) (130*x2/146), 70*y2/86);
           //Drawable x = new RoundImage(resizedBitmap2);
           //i2.setImageDrawable(x);
           frmright.setVisibility(View.VISIBLE);
           i3.setImageBitmap(croppedbitmap);



           ImageView i4 = (ImageView) findViewById(R.id.imageView2);
           Bitmap croppedbitmap2 = Bitmap.createBitmap(setBitmap1, (int) (putxx1-(rx-setBitmap1.getWidth())/2),(int) (putyy1 - (cony-setBitmap1.getHeight())/2),putxx2, putyy2);

           frmleft.setVisibility(View.VISIBLE);
           i4.setImageBitmap(croppedbitmap2);


           eyesx1=puteyesx1;
           eyesx2=puteyesx2;
           eyesy1=puteyesy1;
           eyesy2=puteyesy2;

           x21=putx21;
           x22=putx22;
           y21=puty21;
           y22=puty22;


           pupilx=putpupilx;
           pupily=putpupily;
           pupilx1=putpupilx1;
           pupily1=putpupily1;



           pupil2x=putpupil2x;
           pupil2y=putpupil2y;
           pupil2x1=putpupil2x1;
           pupil2y1=putpupil2y1;

           mouthx=putmouthx;
           mouthy=putmouthy;
           mouthx1=putmouthx1;
           mouthy1=putmouthy1;

           chinx=putchinx;
           chiny=putchiny;
           chinx1=putchinx1;
           chiny1=putchiny1;






        }


        else {


        }





        cchin.setOnTouchListener(mchin);
        c.setOnTouchListener(m);
        //c2.setOnTouchListener(m1);
        c3.setOnTouchListener(m2);
        c4.setOnTouchListener(m3);
        c5.setOnTouchListener(m5);
        c6.setOnTouchListener(m6);
        cmouth.setOnTouchListener(mmouth);

        Float x = c.getX();
        Bitmap resizedBitmap2;
        Float y = c.getY();
        final Bitmap[] b1 = new Bitmap[2];
        //b1[0] = setBitmap;

        c.setEnabled(false);
        c6.setEnabled(false);
        cchin.setEnabled(false);
        c3.setEnabled(false);
        c4.setEnabled(false);
        c5.setEnabled(false);
        cmouth.setEnabled(false);
        im.setAlpha(0.4f);
        im.active(false);


        lefteye.setAlpha(0.5f);
        righteye.setAlpha(0.5f);
        mouth.setAlpha(0.5f);
        chin.setAlpha(0.5f);
        lefteyeb=false;
        righteyeb=false;
        mouthb=false;
        chinb=false;


        lefteyeview.setAlpha(0.4f);
        leftpupilview.setAlpha(0.4f);
        lefteyeview.active(false);
        leftpupilview.active(false);

        righteyeview.setAlpha(0.4f);
        rightpupilview.setAlpha(0.4f);
        righteyeview.active(false);
        rightpupilview.active(false);

        mouthview.setAlpha(0.4f);
        mouthview.setAlpha(0.4f);
        mouthview.active(false);
        mouthview.active(false);



        lefteye.setAlpha(0.4f);

        c.setAlpha(0.4f);
        c6.setAlpha(0.4f);

        c3.setAlpha(0.4f);
        c4.setAlpha(0.4f);
        c5.setAlpha(0.4f);
        cchin.setAlpha(0.4f);


     /*   frmleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        leftpupilview.active(false);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                       // lefteyeview.active(true);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });*/



        lefteye.setSoundEffectsEnabled(false);

        lefteye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                if(lefteyeb == false) {

                    lefteyeview.bringToFront();
                    leftpupilview.bringToFront();
                    //c.changecolor(Color.RED);
                    //c6.changecolor(Color.RED);
                    lefteye.setAlpha(1f);
                    lefteyeview.setAlpha(1f);
                    leftpupilview.setAlpha(1f);
                    lefteyeview.active(true);
                    leftpupilview.active(true);

                    mouthview.setAlpha(0.4f);

                    mouthview.active(false);

                    righteyeview.setAlpha(0.4f);
                    rightpupilview.setAlpha(0.4f);
                    righteyeview.active(false);
                    rightpupilview.active(false);
                    c3.setAlpha(0.4f);
                    c4.setAlpha(0.4f);
                    c5.setAlpha(0.4f);
                    cchin.setAlpha(0.4f);
                    im.setAlpha(0.4f);
                    im.active(false);
                    righteye.setAlpha(0.5f);
                    mouth.setAlpha(0.5f);
                    cchin.setAlpha(0.5f);

                    c.setEnabled(true);
                    c6.setEnabled(true);
                    cchin.setEnabled(false);
                    c3.setEnabled(false);
                    c4.setEnabled(false);
                    c5.setEnabled(false);
                    cmouth.setEnabled(false);
                }


                else{



                }
            }
        });


        new CountDownTimer(100000000, 10) {
            public void onTick(long milsec) {

                leftpupilview.setOnUpCallback2(new MyImageView.OnUpCallback2() {
                    @Override
                    public void ontoouch(boolean x) {

                        Log.d("tagg", Boolean.toString(x));

                        if (!x) rl.bringToFront();
                        else leftpupilview.bringToFront();
                    }


                });


            }

            public void onFinish() {

            }
        }.start();

        new CountDownTimer(100000000, 10) {
            public void onTick(long milsec) {

                rightpupilview.setOnUpCallback2(new MyImageView.OnUpCallback2() {
                    @Override
                    public void ontoouch(boolean x) {

                        Log.d("tagg", Boolean.toString(x));

                        if (!x) rl.bringToFront();
                        else rightpupilview.bringToFront();
                    }


                });


            }

            public void onFinish() {

            }
        }.start();




        righteye.setSoundEffectsEnabled(false);

        righteye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                if(righteyeb == false) {

                    righteyeview.bringToFront();
                    rightpupilview.bringToFront();
                    //c.changecolor(Color.RED);
                    righteye.setAlpha(1f);
                    c3.setAlpha(1f);
                    c5.setAlpha(1f);

                    chin.setAlpha(0.4f);
                    c.setAlpha(0.4f);
                    c4.setAlpha(0.4f);
                    c6.setAlpha(0.4f);
                    im.setAlpha(0.4f);
                    im.active(false);
                    lefteye.setAlpha(0.4f);
                    mouth.setAlpha(0.4f);
                    cchin.setAlpha(0.4f);

                    lefteyeview.setAlpha(0.4f);
                    leftpupilview.setAlpha(0.4f);
                    lefteyeview.active(false);
                    leftpupilview.active(false);

                    mouthview.setAlpha(0.4f);
                    mouthview.active(false);

                    righteyeview.setAlpha(1f);
                    rightpupilview.setAlpha(1f);
                    righteyeview.active(true);
                    rightpupilview.active(true);
                    c3.setEnabled(true);
                    im.setEnabled(false);
                    c5.setEnabled(true);
                    cchin.setEnabled(false);
                    c.setEnabled(false);
                    c4.setEnabled(false);
                    c6.setEnabled(false);
                    cmouth.setEnabled(false);
                }


                else{



                }
            }
        });

        chin.setSoundEffectsEnabled(false);
        chin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                if(chinb == false) {
                    im.bringToFront();

                    //cchin.changecolor(Color.RED);
                    chin.setAlpha(1f);
                    righteye.setAlpha(0.5f);
                    mouth.setAlpha(0.5f);
                    lefteye.setAlpha(0.5f);

                    c3.setAlpha(0.4f);
                    c5.setAlpha(0.4f);
                    im.active(true);
                    cchin.setAlpha(1f);
                    im.setAlpha(1f);
                    c.setAlpha(0.4f);
                    c4.setAlpha(0.4f);
                    c6.setAlpha(0.4f);
                    lefteyeview.setAlpha(0.4f);
                    leftpupilview.setAlpha(0.4f);
                    lefteyeview.active(false);
                    leftpupilview.active(false);

                    righteyeview.setAlpha(0.4f);
                    rightpupilview.setAlpha(0.4f);
                    righteyeview.active(false);
                    rightpupilview.active(false);

                    mouthview.setAlpha(0.4f);

                    mouthview.active(false);
                    im.setEnabled(true);
                    cchin.setEnabled(true);
                    c5.setEnabled(false);
                    c3.setEnabled(false);
                    c.setEnabled(false);
                    c4.setEnabled(false);
                    c6.setEnabled(false);
                    cmouth.setEnabled(false);
                }


                else{



                }
            }
        });

        mouth.setSoundEffectsEnabled(false);
        mouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                if(chinb == false) {

                    mouthview.bringToFront();
                    //cchin.changecolor(Color.RED);
                    mouth.setAlpha(1f);
                    righteye.setAlpha(0.5f);
                    lefteye.setAlpha(0.5f);
                    chin.setAlpha(0.5f);
                    im.active(false);
                    c3.setAlpha(0.4f);
                    c5.setAlpha(0.4f);

                    cchin.setAlpha(0.4f);
                    c.setAlpha(0.4f);
                    c4.setAlpha(1f);
                    c6.setAlpha(0.4f);
                    im.setAlpha(0.4f);
                    lefteyeview.setAlpha(0.4f);
                    leftpupilview.setAlpha(0.4f);
                    lefteyeview.active(false);
                    leftpupilview.active(false);

                    righteyeview.setAlpha(0.4f);
                    rightpupilview.setAlpha(0.4f);
                    righteyeview.active(false);
                    rightpupilview.active(false);
                    mouthview.setAlpha(1f);

                    mouthview.active(true);
                    im.setEnabled(false);
                    c4.setEnabled(true);
                    c5.setEnabled(false);
                    c3.setEnabled(false);
                    c.setEnabled(false);
                   cchin.setEnabled(false);
                    c6.setEnabled(false);
                    cmouth.setEnabled(false);
                }


                else{



                }
            }
        });


        if (null != im) {
            im.setOnUpCallback1(new MyImageView.OnUpCallback() {

                @Override
                public void onRectFinished(final Rect rect) {

                    //t11.setText(Float.toString(rect.top));
                    Log.d("rr",

                            "Rect is (" + rect.left + ", " + rect.top + ", "
                                    + rect.right + ", " + rect.bottom + ")"
                    );

                    chinx = rect.left;
                    chinx1 = rect.right + chinx;
                    chiny = rect.top;
                    chiny1 = rect.bottom + chiny;

                }


            });

        }

        if (null != mouthview) {
            mouthview.setOnUpCallback1(new MyImageView.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {

                    mouthx = rect.left;
                    mouthx1 = rect.right+mouthx;
                    mouthy = rect.top;
                    mouthy1 = rect.bottom+mouthy;

                }


            });

        }




        ImageView i22 = (ImageView) findViewById(R.id.imageView);

       int yvalue =  (int) (i2y1 - (cony-setBitmap1.getHeight())/2);
        int xvalue =  i2x1-(rx-setBitmap1.getWidth())/2;

        if (yvalue < 0) yvalue = 0;
        if (xvalue < 0) xvalue = 0;
      //  Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1, (int) (i2x1-(rx-setBitmap1.getWidth())/2),(int) (i2y1 - (cony-setBitmap1.getHeight())/2),i2x2, i2y2);

        Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1, xvalue,yvalue,i2x2, i2y2);
        i22.setImageBitmap(croppedbitmap);



        if (null != righteyeview) {
            righteyeview.setOnUpCallback1(new MyImageView.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {
                    /*Toast.makeText(
                            getApplicationContext(),
                            "Rect is (" + rect.left + ", " + rect.top + ", "
                                    + rect.right + ", " + rect.bottom + ")",
                            Toast.LENGTH_LONG).show();*/

                    int rx= getWindowManager().getDefaultDisplay().getWidth();


                    x1 = rect.left;
                    x2 = rect.right;
                    y1 = rect.top;
                    y2 = rect.bottom;

                    putx1 = rect.left;
                    putx2 = rect.right;
                    puty1 = rect.top;
                    puty2 = rect.bottom;



                    //int hesap1=x2/138*130;
                    //int hesap2=y2/78*70;


                    //eyesx1 = (int)rect.left-(rx-setBitmap1.getWidth())/2;
                    eyesx1 = rect.left;
                    eyesx2 =  x2+eyesx1;
                    eyesy1 =  rect.top;
                    //eyesy1 = (int) (rect.top-(cony-setBitmap1.getHeight())/2);
                    eyesy2 = y1+y2;

                    //i.putExtra("Key",(int) (x1));
                    //i.putExtra("Key1",(int) (y1));
                    //i.putExtra("Key2",(int) (130*x2/138));
                    //i.putExtra("Key3",70*y2/78);



                    savex(x1);

                    if (x1 > 0 & y1 > 0 & x2 + x1 > 0 & y2 + y1 > 0) {

                        if ((x1 - (rx - setBitmap1.getWidth()) / 2) > 0 & y1 - (cony - setBitmap1.getHeight()) / 2 > 0 &  setBitmap1.getWidth()+ (rx - setBitmap1.getWidth()) / 2  >  x2+x1)  {
                            ImageView i2 = (ImageView) findViewById(R.id.imageView);
                            Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1,(int) (x1-(rx-setBitmap1.getWidth())/2),(int) (y1 - (cony-setBitmap1.getHeight())/2),x2, y2);
                            //Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1, x1,y1 - (cony-setBitmap1.getHeight()),(int) (130*x2/146), 70*y2/86);
                            //Drawable x = new RoundImage(resizedBitmap2);
                            //i2.setImageDrawable(x);
                            frmright.setVisibility(View.VISIBLE);
                            movementm2=true;
                            i2.setImageBitmap(croppedbitmap);



                        } else {
                            //t1.setText("Out of Bounds");
                            frmright.setVisibility(View.INVISIBLE);
                        }
                    }



                    if(movementm5==false){

                        SharedPreferences shared2 = getSharedPreferences("info", MODE_PRIVATE);
                        m5x1=shared2.getInt("m5x1", ((int) m5x1));
                        m5x2=shared2.getInt("m5x2",((int)m5x2));
                        m5y1=shared2.getInt("m5y1", ((int) m5y1));
                        m5y2=shared2.getInt("m5y2", ((int) m5y2));


                        pupilx=  eyesx1 + (int) m5x1;

                        pupily=eyesy1+(int) m5y1;
                        pupilx1=(int) m5x2+pupilx;
                        pupily1=pupily+(int) m5y2;


                    }



                }


            });

        }


        if (null != rightpupilview) {
            rightpupilview.setOnUpCallback1(new MyImageView.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {
                    /*Toast.makeText(
                            getApplicationContext(),
                            "Rect is (" + rect.left + ", " + rect.top + ", "
                                    + rect.right + ", " + rect.bottom + ")",
                            Toast.LENGTH_LONG).show();*/

                    int rx= getWindowManager().getDefaultDisplay().getWidth();

                    double zoom1 = (eyesx2-eyesx1);

                    double zoom = zoom1 / (int)(density1*156);
                    if (zoom ==0) zoom = 0.0000001;


                    double x1= rect.left*zoom;
                    double x2= rect.right*zoom;
                    double x3= (rect.top)*zoom;
                    double x4 =rect.bottom*zoom;


                    m5x1=x1;
                    m5x2=x2;
                    m5y1=x3;
                    m5y2=x4;

                    SharedPreferences pref;
                    pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putInt("m5x1", (int) m5x1);
                    editor.putInt("m5x2",(int)m5x2);
                    editor.putInt("m5y1",(int)m5y1);
                    editor.putInt("m5y2",(int)m5y2);
                    editor.commit();

                    if(x1>0 ) movementm5=true;

                    double lx1=rect.left;
                    double lx2=rect.right;
                    double ly1=rect.top;
                    double ly2=rect.bottom;



                    putpupilx=(int) lx1;
                    putpupilx1=(int)lx2+putpupilx;
                    putpupily=(int) ly1;
                    putpupily1=(int) ly2 + putpupily;


                    pupilx = (int) x1+eyesx1;
                    pupilx1 = (int) x2+pupilx;
                    pupily =  (int) x3+eyesy1;
                    pupily1 = (int) x4+pupily;



                }


            });

        }


        ImageView i222 = (ImageView) findViewById(R.id.imageView2);

        Log.d("iy1:",Integer.toString((int) (iy1)));
        Log.d("getheight:",Integer.toString((int) setBitmap1.getHeight()));
        Log.d("cony:",Integer.toString((int) cony));


        Bitmap croppedbitmap22 = Bitmap.createBitmap(setBitmap1, (int) (ix1-(rx-setBitmap1.getWidth())/2),(int) (iy1 - (cony-setBitmap1.getHeight())/2),ix2, iy2);
        i222.setImageBitmap(croppedbitmap22);



        if (null != lefteyeview) {
            lefteyeview.setOnUpCallback1(new MyImageView.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {


                    int rx= getWindowManager().getDefaultDisplay().getWidth();
                    x1 = rect.left;
                    x2 = rect.right;
                    y1 = rect.top;
                    y2 = rect.bottom;


                    putxx1 = rect.left;
                    putxx2 = rect.right;
                    putyy1 = rect.top;
                    putyy2 = rect.bottom;



                    x21 = rect.left;
                    x22 =  rect.right+x21;
                    y21 = rect.top;
                    y22 = rect.bottom+y21;

                    m.xpos=x21;
                    m.ypos=y1;
                    m.xpos1=x2+x1;
                    m.ypos1=y2+y1;


                    debug.setText(Integer.toString(rx));
                    debug2.setText(Float.toString(cony));
                    //i.putExtra("Key4", x1);
                    //i.putExtra("Key5",x2);
                    //i.putExtra("Key6",y1);
                    //i.putExtra("Key7",y2);


                    float scale = x2/(YCOORDINATE*density1);
                    //int cony = 1000;
                    //t1.setText(Float.toString(rx));
                    //t2.setText(Float.toString(setBitmap1.getWidth()));
                    Matrix matrix1 = new Matrix();
                    matrix1.postScale(1f, 1f);
                    if (x1 > 0 & y1 > 0 & x2 + x1 > 0 & y2 + y1 > 0) {

                        if ((x1 - (rx - setBitmap1.getWidth()) / 2) > 0 & y1 - (cony - setBitmap1.getHeight()) / 2 > 0 &  setBitmap1.getWidth()+ (rx - setBitmap1.getWidth()) / 2  >  x2 +x1)  {
                            ImageView i2 = (ImageView) findViewById(R.id.imageView2);
                            Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1, (int) (x1-(rx-setBitmap1.getWidth())/2),(int) (y1 - (cony-setBitmap1.getHeight())/2),x2, y2);
                            Bitmap resizedBitmap2 = Bitmap.createBitmap(croppedbitmap, 0, 0, croppedbitmap.getWidth(), croppedbitmap.getHeight(), matrix1, true);
                            frmleft.setVisibility(View.VISIBLE);
                            movementm=true;
                            i2.setImageBitmap(croppedbitmap);
                        } else {
                            //t1.setText("Out of Bounds");
                            frmleft.setVisibility(View.INVISIBLE);
                        }

                    }





                    if(movementm6==false){

                        SharedPreferences shared2 = getSharedPreferences("info", MODE_PRIVATE);
                        m5x1=shared2.getInt("m5x1", ((int) m5x1));
                        m5x2=shared2.getInt("m5x2",((int)m5x2));
                        m5y1=shared2.getInt("m5y1", ((int) m5y1));
                        m5y2=shared2.getInt("m5y2", ((int) m5y2));


                        pupil2x=  x21 + (int) m5x1;

                        pupil2y=y21+(int) m5y1;
                        pupil2x1=(int) m5x2+pupil2x;
                        pupil2y1=pupil2y+(int) m5y2;


                    }

                }


            });


        }






   /*     if (null != m) {
            m.setOnUpCallback1(new MultiTouchListener.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {


                    int rx= getWindowManager().getDefaultDisplay().getWidth();
                    x1 = rect.left;
                    x2 = rect.right;
                    y1 = rect.top-(YCOORDINATE*(int)density1);
                    y2 = rect.bottom;


                    putxx1 = rect.left;
                    putxx2 = rect.right;
                    putyy1 = rect.top-(YCOORDINATE*(int)density1);
                    putyy2 = rect.bottom;



                    x21 = rect.left;
                    x22 =  rect.right+x21;
                    y21 = rect.top-(YCOORDINATE*(int)density1);
                    y22 = rect.bottom+y21;

                    m.xpos=x21;
                    m.ypos=y1;
                    m.xpos1=x2+x1;
                    m.ypos1=y2+y1;


                    debug.setText(Integer.toString(rx));
                    debug2.setText(Float.toString(cony));
                    //i.putExtra("Key4", x1);
                    //i.putExtra("Key5",x2);
                    //i.putExtra("Key6",y1);
                    //i.putExtra("Key7",y2);


                    float scale = x2/(YCOORDINATE*(int)density1);
                    //int cony = 1000;
                    //t1.setText(Float.toString(rx));
                    //t2.setText(Float.toString(setBitmap1.getWidth()));
                    Matrix matrix1 = new Matrix();
                    matrix1.postScale(1f, 1f);
                    if (x1 > 0 & y1 > 0 & x2 + x1 > 0 & y2 + y1 > 0) {

                        if ((x1 - (rx - setBitmap1.getWidth()) / 2) > 0 & y1 - (cony - setBitmap1.getHeight()) / 2 > 0 &  setBitmap1.getWidth()+ (rx - setBitmap1.getWidth()) / 2  >  x2 +x1)  {
                            ImageView i2 = (ImageView) findViewById(R.id.imageView2);
                            Bitmap croppedbitmap = Bitmap.createBitmap(setBitmap1, (int) (x1-(rx-setBitmap1.getWidth())/2),(int) (y1 - (cony-setBitmap1.getHeight())/2),x2, y2);
                            Bitmap resizedBitmap2 = Bitmap.createBitmap(croppedbitmap, 0, 0, croppedbitmap.getWidth(), croppedbitmap.getHeight(), matrix1, true);
                            frmleft.setVisibility(View.VISIBLE);
                            movementm=true;
                            i2.setImageBitmap(croppedbitmap);
                        } else {
                            //t1.setText("Out of Bounds");
                            frmleft.setVisibility(View.INVISIBLE);
                        }

                    }





                    if(movementm6==false){

                        SharedPreferences shared2 = getSharedPreferences("info", MODE_PRIVATE);
                        m5x1=shared2.getInt("m5x1", ((int) m5x1));
                        m5x2=shared2.getInt("m5x2",((int)m5x2));
                        m5y1=shared2.getInt("m5y1", ((int) m5y1));
                        m5y2=shared2.getInt("m5y2", ((int) m5y2));


                        pupil2x=  x21 + (int) m5x1;

                        pupil2y=y21+(int) m5y1;
                        pupil2x1=(int) m5x2+pupil2x;
                        pupil2y1=pupil2y+(int) m5y2;


                    }

                }.


            });


        }*/


        videoView2 = (VideoView) findViewById(R.id.videoView2);
        mediaController2 = new MediaController(this);
        mediaController2.setAnchorView(videoView2);
        mediaController2.setVisibility(View.GONE);
        videoView2.setMediaController(mediaController2);


        videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro4));
        videoView2.setEnabled(false);

        videoView2.start();
      /*  videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0, 0);

            }
        });*/

        videoView2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView2.start();

            }
        });



        if (null != leftpupilview) {
            leftpupilview.setOnUpCallback1(new MyImageView.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {
                    //fr.setVisibility(View.VISIBLE);
                    //lefteyeview.active(false);
                    int rx= getWindowManager().getDefaultDisplay().getWidth();




                    double zoom1 = (x22-x21);

                    double zoom = zoom1 / (density1*156);
                    if (zoom ==0) zoom = 0.001;

                    double dist = rx-(156*density1);

                    double x1= (rect.left- (int)dist) *zoom;
                    double x2= rect.right*zoom;
                    double x3= (rect.top)*zoom;
                    double x4 =rect.bottom*zoom;


                    m6x1=x1;
                    m6x2=x2;
                    m6y1=x3;
                    m6y2=x4;

                    SharedPreferences pref;
                    pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putInt("m6x1", (int) m6x1);
                    editor.putInt("m6x2",(int)m6x2);
                    editor.putInt("m6y1",(int)m6y1);
                    editor.putInt("m6y2",(int)m6y2);
                    editor.commit();


                    if(x1>=0 ) movementm6=true;


                    double lx1=(rect.left- (int)dist) *zoom;
                    double lx2=rect.right;
                    double ly1=rect.top;
                    double ly2=rect.bottom;



                    putpupil2x=(int) lx1;
                    putpupil2x1=(int)lx2+putpupil2x;
                    putpupil2y=(int) ly1;
                    putpupil2y1=(int) ly2 + putpupil2y;


                    pupil2x = (int) (x21+x1);
                    pupil2x1 = (int) (x2+pupil2x);
                    pupil2y = (int) (x3+y21);
                    pupil2y1 = (int) (pupil2y+x4);
                    //m6.xpos=x1;





                    //m6.ypos=y1;
                    //m6.xpos1=x2;
                    //m6.ypos1=y2;

                }


            });

        }


 /*

        if (null != mmouth) {
            mmouth.setOnUpCallback1(new MultiTouchListener.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {


                    int rx= getWindowManager().getDefaultDisplay().getWidth();


                    double zoom1 = (mouthx1-mouthx);

                    double zoom = zoom1 / 350;
                    if (zoom ==0) zoom = 0.001;


                    double x111= (rect.left)*zoom;
                    double x211= rect.right*zoom;
                    double x311= (rect.top-450*density1)*zoom;
                    double x411 =rect.bottom*zoom;




                    mouthrealx = (int) (mouthx+50+x111);
                    mouthrealx1 = (int) (x211+mouthrealx);
                    mouthrealy = (int) (x311+x411/2+(mouthy+(mouthy1-mouthy)/2));
                    //mouthrealy=(int) (mouthy+(mouthy1-mouthy)/2);

                    mouthrealy1 = (int) (x411/2+mouthrealy);



                    //m6.xpos=x1;
                    //m6.ypos=y1;
                    //m6.xpos1=x2;
                    //m6.ypos1=y2;

                }


            });

        }*/



        final TextView t1 = (TextView) findViewById(R.id.textView3);

        //t1.setTypeface(tf);
        t1.setTextSize(20);
       final  Button b =(Button) findViewById(R.id.button);
        final Button back= ( Button ) findViewById(R.id.button26);

        buttonbacktoface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buttonbacktoface.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        tomain();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonbacktoface.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {



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
                        tomain();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        back.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {



                        break;
                    }
                }
                return true;
            }
        });

        final Context con = this;

       final RelativeLayout warningbox = (RelativeLayout) findViewById(R.id.warningbox);
        final Button buttonok = (Button) findViewById(R.id.button36);

        final RelativeLayout disablechild = (RelativeLayout) findViewById(R.id.disablechild) ;

        disablechild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

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


        buttonok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buttonok.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                       // warningbox.setVisibility(View.INVISIBLE);
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        final Animation shake = AnimationUtils.loadAnimation(con, R.anim.righttoleft);
                        shake.setDuration(SLIDEANIMDUR);
                        warningbox.setAnimation(shake);
                        pluscoinlayout3();


                        introbutton.setEnabled(true);
                        back.setEnabled(true);
                        bpreview.setEnabled(true);
                        disablechild.setVisibility(View.INVISIBLE);






                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonok.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });

       final float conversiony = frameheight * density1;
        final int    chinyy = (int) (chiny /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        final int    chinyy1 = (int) (chiny1 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
      final int mouthyy = (int) (mouthy /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
      final int  mouthyy1 = (int) (mouthy1 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);

        Log.d("mouthyy:",Integer.toString(mouthyy));
        Log.d("mouthyy1:",Integer.toString(mouthyy1));
        Log.d("chinyy1:",Integer.toString(chinyy1));


        float btmwidth2 = setBitmap.getWidth();
        float btmheight2 = setBitmap.getHeight();

        Matrix matrix12 = new Matrix();




        matrix12.postScale(conversiony / btmheight2, conversiony / btmheight2);
      final  Bitmap resizedBitmap22 = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);



        bpreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        bpreview.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        final int    chinyy = (int) (chiny /height*resizedBitmap22.getHeight()+(conversiony- resizedBitmap22.getHeight())/2);
                        final int    chinyy1 = (int) (chiny1 /height*resizedBitmap22.getHeight()+(conversiony- resizedBitmap22.getHeight())/2);
                        final int mouthyy = (int) (mouthy /height*resizedBitmap22.getHeight()+(conversiony- resizedBitmap22.getHeight())/2);
                        final int  mouthyy1 = (int) (mouthy1 /height*resizedBitmap22.getHeight()+(conversiony- resizedBitmap22.getHeight())/2);


                        final int cchinx= (int) (chinx/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
                        final int cchinx1= (int) (chinx1/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);


                        Log.d("mouthyy:",Integer.toString(mouthyy));
                        Log.d("mouthyy1:",Integer.toString(mouthyy1));
                        Log.d("chinyy1:",Integer.toString(chinyy1));
                        Log.d("resizedBitmap.getWidth:",Integer.toString(resizedBitmap22.getWidth()));

                        Log.d("chiny1:",Integer.toString(chiny1));
                        Log.d("rx*density1:",Integer.toString((int)(500 *density1)));

                      final int picx =   chinx - (rx-resizedBitmap.getWidth())/2;


                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if(eyesx1>0 & eyesx2>0 & mouthy+(mouthy1-mouthy)/2 > y22 & chinx1 < (rx) && chiny1 < 450 *density1 & mouthy+(mouthy1-mouthy)/2 > eyesy1 & chinx - (rx-resizedBitmap.getWidth())/2 + chinx1-chinx <  resizedBitmap22.getWidth() & mouthyy+(mouthyy1-mouthyy)/2-(chinyy1-(mouthyy+(mouthyy1-mouthyy)/2)) > 0 & eyesy1>0 & x22 > eyesx1 && eyesy2>0 & x21>0 & x22>0 &y21 > 0 & y22 >0 & eyesx2-eyesx1>0 & pupilx1-pupilx>0 & pupil2y1-pupil2y >0 & pupily>0 & mouthx>0 & mouthy>0 & mouthx1-mouthx>0 & chinx>0 & chiny>0 & chinx1-chinx>0 & chinx1>0 & chiny1>0 && mouthy + (mouthy1 - mouthy)/2 < chiny1)
                        // if(eyesx1>0 & eyesx2>0 & mouthy+(mouthy1-mouthy)/2 > y22 & mouthy+(mouthy1-mouthy)/2 > eyesy1 &  mouthyy+(mouthyy1-mouthyy)/2-(chinyy1-(mouthyy+(mouthyy1-mouthyy)/2)) > 0 & eyesy1>0 & x22 > eyesx1 && eyesy2>0 & x21>0 & x22>0 &y21 > 0 & y22 >0 & eyesx2-eyesx1>0 & pupilx1-pupilx>0 & pupil2y1-pupil2y >0 & pupily>0 & mouthx>0 & mouthy>0 & mouthx1-mouthx>0 & chinx>0 & chiny>0 & chinx1-chinx>0 & chinx1>0 & chiny1>0 && mouthy + (mouthy1 - mouthy)/2 < chiny1)


                        showZaire();

                        else
                        {
                            Bundle params = new Bundle();
                            params.putString("image_name", "eyeswarning");
                            params.putString("full_text", "eyeswarning");
                            mFirebaseAnalytics.logEvent("eyeswarning", params);
                            warningbox.setVisibility(View.VISIBLE);
                            final Animation shake = AnimationUtils.loadAnimation(con, R.anim.lefttoright);
                            shake.setDuration(SLIDEANIMDUR);
                            warningbox.setAnimation(shake);
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

                            introbutton.setEnabled(false);
                            back.setEnabled(false);
                            bpreview.setEnabled(false);
                           disablechild.setVisibility(View.VISIBLE);
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        bpreview.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });



        if(movementm5==false){


            m5x1=shared1.getInt("m5x1", ((int) m5x1));
            m5x2=shared1.getInt("m5x2",((int)m5x2));
            m5y1=shared1.getInt("m5y1", ((int) m5y1));
            m5y2=shared1.getInt("m5y1", ((int) m5y2));


            pupilx=  eyesx1 + (int) m5x1;

            pupily=eyesy1+(int) m5y1;
            pupilx1=(int) m5x2+pupilx;
            pupily1=pupily+(int) m5y2;


        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eyes, menu);
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


    public void showZaire() {
        STATE = PREVIEWSTATE;
        Thread thread;
        int rx= getWindowManager().getDefaultDisplay().getWidth();
        float density1 = Resources.getSystem().getDisplayMetrics().density;
        float cony = (450*density1);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
       // TextView t1 = (TextView) findViewById(R.id.textView4);
        //TextView t2 = (TextView) findViewById(R.id.textView5);


       videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.pause();
                //mp.release();

            }
        });

       // t1.setText(Float.toString(mouthrealy1));
       // t2.setText(Float.toString(mouthrealy));

    if (height < width)

    {
        if (movementm2 == true)
        {
            eyesx1 = (int) (eyesx1 / rx * realwidth);
            eyesy1 = (int) (eyesy1 / rx * realwidth);
            eyesx2 = (int) (eyesx2 / rx * realwidth);
            eyesy2 = (int) (eyesy2 / rx * realwidth);
       }

    } else {
        int lentgh = eyesx2 - eyesx1;


            eyesx1 = (int) ((eyesx1 - (rx - realwidth) / 2));
            eyesx2 = (int) ((eyesx2 - (rx - realwidth) / 2));
            eyesy1 = (int) ((eyesy1 - (cony - realheight) / 2));
            eyesy2 = (int) ((eyesy2 - (cony - realheight) / 2));

        int length2 = x22 - x21;


            x21 = (int) ((x21 - (rx - realwidth) / 2));
            x22 = (int) ((x22 - (rx - realwidth) / 2));
            y21 = (int) ((y21 - (cony - realheight) / 2));
            y22 = (int) ((y22 - (cony - realheight) / 2));


        mouthx = (int) ((mouthx - (rx - realwidth) / 2));
        mouthx1 = (int) ((mouthx1 - (rx - realwidth) / 2));
        mouthy = (int) ((mouthy - (cony - realheight) / 2));
        mouthy1 = (int) ((mouthy1 - (cony - realheight) / 2));

        mouthrealx = (int) ((mouthrealx - (rx - realwidth) / 2));
        mouthrealx1 = (int) ((mouthrealx1 - (rx - realwidth) / 2));
        mouthrealy = (int) ((mouthrealy - (cony - realheight) / 2));
        mouthrealy1 = (int) ((mouthrealy1 - (cony - realheight) / 2));



            pupilx = (int) ((pupilx - (rx - realwidth) / 2));
            pupilx1 = (int) ((pupilx1 - (rx - realwidth) / 2));
            pupily = (int) ((pupily - (cony - realheight) / 2));
            pupily1 = (int) ((pupily1 - (cony - realheight) / 2));



            pupil2x = (int) ((pupil2x - (rx - realwidth) / 2));
            pupil2x1 = (int) ((pupil2x1 - (rx - realwidth) / 2));
            pupil2y = (int) ((pupil2y - (cony - realheight) / 2));
            pupil2y1 = (int) ((pupil2y1 - (cony - realheight) / 2));

        //eyesy2 =(int) (eyesy2 / 450 * 350);
    }


        //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        Intent i = new Intent();
        //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


        editor.putInt("getrx",rx);


        if(movementm2==true) {
            i.putExtra("Key", eyesx1);
            i.putExtra("Key1", eyesy1);
            i.putExtra("Key2", eyesx2);
            i.putExtra("Key3", eyesy2);
        }
        else{

            i.putExtra("Key", streyesx1);
            i.putExtra("Key1", streyesy1);
            i.putExtra("Key2", streyesx2);
            i.putExtra("Key3", streyesy2);

        }

        if(movementm==true) {
            i.putExtra("Key4", x21);
            i.putExtra("Key5", x22);
            i.putExtra("Key6", y21);
            i.putExtra("Key7", y22);
        } else
        {
            i.putExtra("Key4", strx21);
            i.putExtra("Key5", strx22);
            i.putExtra("Key6", stry21);
            i.putExtra("Key7", stry22);

        }
        if(movementm5==true | movementm2==true) {
            i.putExtra("Key8", pupilx);
            i.putExtra("Key9", pupily);
            i.putExtra("Key10", pupilx1);
            i.putExtra("Key11", pupily1);
        }
        else
        {

            i.putExtra("Key8", strpupilx);
            i.putExtra("Key9", strpupily);
            i.putExtra("Key10", strpupilx1);
            i.putExtra("Key11", strpupily1);


        }

        if(movementm6==true || movementm==true) {
            i.putExtra("Key12", pupil2x);
            i.putExtra("Key13", pupil2y);
            i.putExtra("Key14", pupil2x1);
            i.putExtra("Key15", pupil2y1);
        }

        else
        {

            i.putExtra("Key12", strpupil2x);
            i.putExtra("Key13", strpupil2y);
            i.putExtra("Key14", strpupil2x1);
            i.putExtra("Key15", strpupil2y1);

        }
           i.putExtra("Key16", mouthx);
           i.putExtra("Key17", mouthy);
           i.putExtra("Key18", mouthx1);
        i.putExtra("Key19", mouthy1);

        i.putExtra("Key20", realwidth);
        i.putExtra("Key21", realheight);

        i.putExtra("Key22", chinx);
           i.putExtra("Key23", chiny);
           i.putExtra("Key24", chinx1);
           i.putExtra("Key25", chiny1);


        if(movementm2==true) {
            editor.putInt("streyesx1", eyesx1);
            editor.putInt("streyesy1", eyesy1);
            editor.putInt("streyesx2", eyesx2);
            editor.putInt("streyesy2", eyesy2);
        }

        else

        {
            editor.putInt("streyesx1", streyesx1);
            editor.putInt("streyesy1", streyesy1);
            editor.putInt("streyesx2", streyesx2);
            editor.putInt("streyesy2", streyesy2);
        }

        if(movementm==true) {
            editor.putInt("strx21", x21);
            editor.putInt("strx22", x22);
            editor.putInt("stry21", y21);
            editor.putInt("stry22", y22);
        }

        else

        {
            editor.putInt("strx21", strx21);
            editor.putInt("strx22", strx22);
            editor.putInt("stry21", stry21);
            editor.putInt("stry22", stry22);
        }



        if(movementm5==true || movementm2==true) {
            editor.putInt("strpupilx", pupilx);
            editor.putInt("strpupily", pupily);
            editor.putInt("strpupilx1", pupilx1);
            editor.putInt("strpupily1", pupily1);
        }

        else{


            editor.putInt("strpupilx", strpupilx);
            editor.putInt("strpupily", strpupily);
            editor.putInt("strpupilx1", strpupilx1);
            editor.putInt("strpupily1", strpupily1);

        }


        if(movementm6==true|| movementm==true) {
            editor.putInt("strpupil2x", pupil2x);
            editor.putInt("strpupil2y", pupil2y);
            editor.putInt("strpupil2x1", pupil2x1);
            editor.putInt("strpupil2y1", pupil2y1);
        }

        else {

            editor.putInt("strpupil2x", strpupil2x);
            editor.putInt("strpupil2y", strpupil2y);
            editor.putInt("strpupil2x1", strpupil2x1);
            editor.putInt("strpupil2y1", strpupil2y1);

        }
        editor.putInt("strmouthx", mouthx);
        editor.putInt("strmouthy", mouthy);
        editor.putInt("strmouthx1", mouthx1);
        editor.putInt("strmouthy1", mouthy1);

        editor.putInt("strchinx", chinx);
        editor.putInt("strchiny",chiny);
        editor.putInt("strchinx1",chinx1);
        editor.putInt("strchiny1",chiny1);



        editor.putInt("putx1", putx1);
        editor.putInt("putx2", putx2);
        editor.putInt("puty1", puty1);
        editor.putInt("puty2", puty2);


        editor.putInt("putxx1", putxx1);
        editor.putInt("putxx2", putxx2);
        editor.putInt("putyy1", putyy1);
        editor.putInt("putyy2",putyy2);

        editor.putInt("puteyesx1", eyesx1);
        editor.putInt("puteyesy1", eyesy1);
        editor.putInt("puteyesx2", eyesx2);
        editor.putInt("puteyesy2", eyesy2);

        editor.putInt("putx21", x21);
        editor.putInt("putx22",x22);
        editor.putInt("puty21",y21);
        editor.putInt("puty22",y22);

        editor.putInt("putpupilx", putpupilx);
        editor.putInt("putpupily",putpupily);
        editor.putInt("putpupilx1",putpupilx1);
        editor.putInt("putpupily1",putpupily1);

        editor.putInt("putpupil2x", putpupil2x);
        editor.putInt("putpupil2y",putpupil2y);
        editor.putInt("putpupil2x1",putpupil2x1);
        editor.putInt("putpupil2y1",putpupil2y1);

        editor.putInt("putmouthx", mouthx);
        editor.putInt("putmouthy", mouthy);
        editor.putInt("putmouthx1", mouthx1);
        editor.putInt("putmouthy1", mouthy1);

        editor.putInt("putchinx", chinx);
        editor.putInt("putchiny",chiny);
        editor.putInt("putchinx1",chinx1);
        editor.putInt("putchiny1",chiny1);
       // editor.putInt("ongame", 0);
        editor.putBoolean("backbutton", true);
        editor.commit();
        // i.putExtra("Key4",ID);
        //i.putExtra("Key5", x3);
        //i.putExtra("Key6",y3);
        // i.putExtra("Key7",x4);
        // i.putExtra("Key8",y4);

        setResult(Activity.RESULT_OK, i);
        //Toast toast = Toast.makeText(getApplicationContext(), "Time is Up",Toast.LENGTH_SHORT);
        //toast.show();
        //g.setVisibility(View.VISIBLE);
//        videoView.pause();


        RelativeLayout rel = (RelativeLayout) findViewById(R.id.Previewglobal);
        rel.setVisibility(View.VISIBLE);


        showeyelid();

        //finish();


    }

    @Override
    public void onBackPressed() {
        final ImageView movement = (ImageView) findViewById(R.id.imageView22);
        final ImageView grd = (ImageView) findViewById(R.id.imageViewlast);
        ImageView movement2= (ImageView) findViewById(R.id.imageView5);
        ImageView img2 = (ImageView) findViewById(R.id.imageView7);
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.Previewglobal);
        final FrameLayout f=(FrameLayout) findViewById(R.id.frameeyelid);
        final FrameLayout fbottom=(FrameLayout) findViewById(R.id.frameeylidbtm);
        final FrameLayout fr = (FrameLayout) findViewById(R.id.eyelidwhite);
        ImageView img = (ImageView) findViewById(R.id.imageView6);
        ImageView righteyewhite = (ImageView) findViewById(R.id.righteyewhite);
        ImageView whiteback = (ImageView) findViewById(R.id.imageView4);
        final FrameLayout picture = (FrameLayout) findViewById(R.id.Picture);
        //rfinal FrameLayout picture = (FrameLayout) findViewById(R.id.Picture);el.setBackgroundColor(Color.argb(255, 171, 255, 102));
        final Button backtoselection = (Button) findViewById(R.id.backbutton);
        final Button reanimate = (Button) findViewById(R.id.reanimatebutton);

        final FrameLayout frame = (FrameLayout) findViewById(R.id.frameLayout);
        final ImageView smallpic = (ImageView) findViewById(R.id.smallpic);
        final TextView vivoid = (TextView) findViewById(R.id.textView7);
        final TextView vivoidnumber = (TextView) findViewById(R.id.textView8);
        final Button gotogamebutton = (Button) findViewById(R.id.button13);
        final Button bnext = (Button) findViewById(R.id.button2);
        final Button bgotogame = (Button) findViewById(R.id.button13);
        backtoselection.setVisibility(View.INVISIBLE);
        reanimate.setVisibility(View.INVISIBLE);
        final RelativeLayout header2 = (RelativeLayout) findViewById(R.id.Headerlast);
            final EditText e1 = (EditText) findViewById(R.id.editText12);
            final TextView Button= (Button) findViewById(R.id.button13);
            final TextView e2 = (TextView) findViewById(R.id.editText13);
            final TextView e3 = (TextView) findViewById(R.id.editText14);
            final Button backbtnn= (Button) findViewById(R.id.button27);

        final TextView t1 = ( TextView)findViewById(R.id.textname);
        final TextView t2 = ( TextView)findViewById(R.id.textname2);
        final TextView t3 = ( TextView)findViewById(R.id.textdate);
        final TextView t4 = ( TextView)findViewById(R.id.vivoidnumber);
        final TextView maxlength= (TextView) findViewById(R.id.textView40);




            final MediaPlayer audiomuhur = MediaPlayer.create(getApplicationContext(), R.raw.damga);
            final ImageView muhur = (ImageView) findViewById(R.id.imageView55);
            final ImageView stamp = (ImageView) findViewById(R.id.imageView54);

        Bitmap x1 = BitmapFactory.decodeResource(getResources(), R.mipmap.whiterect );
        final FrameLayout fr1 = (FrameLayout) findViewById(R.id.white);
        ImageView img1 = (ImageView) findViewById(R.id.imageView4);
        final FrameLayout f1=(FrameLayout) findViewById(R.id.frame);

        final FrameLayout righteyeback = (FrameLayout) findViewById(R.id.righteyeback);
        final FrameLayout righteye = (FrameLayout) findViewById(R.id.righteyelast);

        final ImageView righteyeimage = (ImageView) findViewById(R.id.righteyeimg);
        ImageView righteyebackimg = (ImageView) findViewById(R.id.righteyewhite);
        final FrameLayout righteyelid = (FrameLayout) findViewById(R.id.righteyelidtop);
        final FrameLayout righteyelidback = (FrameLayout) findViewById(R.id.righteyelidback);
        final FrameLayout righteyelidbtm = (FrameLayout) findViewById(R.id.righteyelidbottom);
        ImageView righteyelidtopimage= (ImageView) findViewById(R.id.righteyelidtp);
        ImageView righteyelidbottomimage = (ImageView) findViewById(R.id.righteyelidbtm);
        ImageView righteyelidbackimg = (ImageView) findViewById(R.id.righteyelidbak);

        final Button button1 = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button26);
        final Button button3 = (Button) findViewById(R.id.introbutton);

        if (STATE == MAINSTATE)
        tomain();

        else if (STATE == PREVIEWSTATE)
        {
            STATE = MAINSTATE;
            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
            SharedPreferences pref;
            pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("backbuttoneyes", true);
            editor.commit();
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            rel.setVisibility(View.INVISIBLE);

        }

        else if (STATE == VIVOPASSSTATE)
        {


            STATE = PREVIEWSTATE;
            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
            backtoselection.setVisibility(View.VISIBLE);
            reanimate.setVisibility(View.VISIBLE);
            muhur.setVisibility(View.INVISIBLE);
            stamp.setVisibility(View.INVISIBLE);
            vivoidnumber.setVisibility(View.INVISIBLE);
            picture.setVisibility(View.INVISIBLE);
            vivoid.setVisibility(View.INVISIBLE);
            frame.setVisibility(View.VISIBLE);
            bnext.setVisibility(View.VISIBLE);
            bgotogame.setVisibility(View.INVISIBLE);
            e1.setVisibility(View.INVISIBLE);
            maxlength.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            e3.setVisibility(View.INVISIBLE);
            t1.setVisibility(View.INVISIBLE);
            t2.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            t4.setVisibility(View.INVISIBLE);
            backbtnn.setVisibility(View.INVISIBLE);
            header2.setVisibility(View.VISIBLE);
            rel.setBackgroundColor(Color.argb(0,0,0,0));

            backbtnn.setAlpha(1f);

        }
    }
    void savex(float y){


        x1= (int) y;


    }


    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Eyes2.this);
        // Setting Dialog Title
        alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to leave the application?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.mipmap.ic_launcher);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    void tomain(){

        RelativeLayout r = (RelativeLayout) findViewById(R.id.ad2);
        r.removeView(im);
      //  if(optionaudio != null && optionaudio.isPlaying())  optionaudio.release();
       // soundPool2.release();
       // soundPool2.autoPause();
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("ongame", 1);
        editor.putBoolean("backbutton", true);


        editor.commit();


        Intent i = new Intent(this, CameraActivity.class);
        //Intent i1 = new Intent();
        //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //i1.putExtra("Key123", 10);
        //setResult(Activity.RESULT_OK, i1);
        startActivity(i);
        finish();

    }
    @Override
    public void onDestroy(){



        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
        setBitmap.recycle();
        setBitmap1.recycle();
        resizedBitmap.recycle();
        resizedBitmap1.recycle();
        x = getResources().getDrawable(R.mipmap.vivo8);

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.pause();
                mp.release();

            }
        });
    }
    public void showeyelid(){
        final ImageView movement = (ImageView) findViewById(R.id.imageView22);
        final ImageView grd = (ImageView) findViewById(R.id.imageViewlast);
        ImageView movement2= (ImageView) findViewById(R.id.imageView5);
        ImageView img2 = (ImageView) findViewById(R.id.imageView7);
        final FrameLayout f=(FrameLayout) findViewById(R.id.frameeyelid);
        final FrameLayout fbottom=(FrameLayout) findViewById(R.id.frameeylidbtm);
        final FrameLayout fr = (FrameLayout) findViewById(R.id.eyelidwhite);
        ImageView img = (ImageView) findViewById(R.id.imageView6);
        ImageView righteyewhite = (ImageView) findViewById(R.id.righteyewhite);
        ImageView whiteback = (ImageView) findViewById(R.id.imageView4);

        final FrameLayout righteyelid = (FrameLayout) findViewById(R.id.righteyelidtop);
        final FrameLayout righteyelidback = (FrameLayout) findViewById(R.id.righteyelidback);
        final FrameLayout righteyelidbtm = (FrameLayout) findViewById(R.id.righteyelidbottom);
        ImageView righteyelidtopimage= (ImageView) findViewById(R.id.righteyelidtp);
        ImageView righteyelidbottomimage = (ImageView) findViewById(R.id.righteyelidbtm);
        ImageView righteyelidbackimg = (ImageView) findViewById(R.id.righteyelidbak);

        final Button button1 = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button26);
        final Button button3 = (Button) findViewById(R.id.introbutton);


        Bundle params = new Bundle();
        params.putString("image_name", "preview");
        params.putString("full_text", "preview");
        mFirebaseAnalytics.logEvent("preview", params);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);

        if(movementm2==true) {

            myValue = eyesx1;
            myValuey = eyesy1;
            p2xx = eyesx2;
            p2y = eyesy2;



        }
        else{

            myValue = streyesx1;
            myValuey = streyesy1;
            p2xx = streyesx2;
            p2y = streyesy2;

        }

        if(movementm==true) {


            eyesXcoordinate = x21;
            eyesXcoordinate2 = x22;
            eyesYcoordinate = y21;
            eyesYcoordinate2 = y22;

        } else
        {
            eyesXcoordinate = strx21;
            eyesXcoordinate2 = strx22;
            eyesYcoordinate = stry21;
            eyesYcoordinate2 = stry22;

        }
        if(movementm5==true | movementm2==true) {


            pupilXnew = pupilx;
            pupilYnew = pupily;
            pupilX1new = pupilx1;
            pupilY1new = pupily1;

        }
        else
        {


            pupilXnew = strpupilx;
            pupilYnew = strpupily;
            pupilX1new = strpupilx1;
            pupilY1new = strpupily1;


        }

        if(movementm6==true || movementm==true) {

            pupil2X = pupil2x;
            pupil2Y = pupil2y;
            pupil2X1 = pupil2x1;
            pupil2Y1 = pupil2y1;

        }

        else
        {



            pupil2X = strpupil2x;
            pupil2Y = strpupil2y;
            pupil2X1 = strpupil2x1;
            pupil2Y1 = strpupil2y1;

        }

        eyesX2coordinate = myValue;
        eyesX2coordinate2 = p2xx;
        eyesY2coordinate = myValuey;
        eyesY2coordinate2 = p2y;

        righteyelid.setVisibility(View.VISIBLE);
        righteyelidback.setVisibility(View.VISIBLE);
        righteyelidbtm.setVisibility(View.VISIBLE);
        f.setVisibility(View.VISIBLE);
        fbottom.setVisibility(View.VISIBLE);
        fr.setVisibility(View.VISIBLE);

        //i.putExtra("Key20", realwidth);
        //i.putExtra("Key21", realheight);

        width=realwidth;
        height=realheight;

        final Button btn = (Button) findViewById(R.id.button2);
        //FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
       // btn.setText("Finish");

        String root = Environment.getExternalStorageDirectory().toString();
        String photoPath = root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg";
        //  String photoPath2 = "/storage/emulated/0/DCIM/Camera/" + ".pic22.jpg";

        //String photoPath3 = "/storage/emulated/0/DCIM/Camera/" + "righteye.jpg";



        setBitmap = BitmapFactory.decodeFile(photoPath,options);
        // Bitmap setBitmap2 = BitmapFactory.decodeFile(photoPath2);
        // Bitmap setBitmap3 = BitmapFactory.decodeFile(photoPath3);
        //Bitmap setBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jelly);
        //Bitmap setBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.human);
        float density1 = Resources.getSystem().getDisplayMetrics().density;
        int rx= getWindowManager().getDefaultDisplay().getWidth();
        float conversiony = frameheight * density1;

        //  float btmwidth3 = setBitmap3.getWidth();
        // float btmheight3 = setBitmap3.getHeight();

        float btmwidth = setBitmap.getWidth();
        float btmheight = setBitmap.getHeight();

        Matrix matrix1 = new Matrix();

        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();

        Bitmap croppedBitmap1,croppedBitmap,croppedBitmap3;

        Bitmap result;



        Bitmap croppedimage2,rightcroppedimage2,rightcroppedimage;


        matrix1.postScale(conversiony / btmheight, conversiony / btmheight);
        resizedBitmap = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);
        //p2xd = eyesXcoordinate+ ((myValue-p2x)/rx*(eyesXcoordinate2-eyesXcoordinate)-(rx-resizedBitmap.);
        //p2x = (int) p2xd;

        eyesX2coordinate= (int) (eyesX2coordinate/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
        eyesX2coordinate2= (int) (eyesX2coordinate2/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
        eyesXcoordinate= (int) (eyesXcoordinate/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
        eyesXcoordinate2= (int) (eyesXcoordinate2/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);

        eyesYcoordinate = (int) (eyesYcoordinate /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        eyesYcoordinate2 = (int) (eyesYcoordinate2 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        eyesY2coordinate = (int) (eyesY2coordinate /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        eyesY2coordinate2 = (int) (eyesY2coordinate2 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);


        //croppedimage2 = Bitmap.createBitmap(resizedBitmap, eyesXcoordinate - (rx-resizedBitmap.getWidth())/2, eyesYcoordinate+ (eyesYcoordinate2-eyesYcoordinate)/2,eyesXcoordinate2-eyesXcoordinate,(eyesYcoordinate2-eyesYcoordinate)/2 );
//        croppedBitmap = Bitmap.createBitmap(resizedBitmap, eyesXcoordinate - (rx-resizedBitmap.getWidth())/2, eyesYcoordinate ,eyesXcoordinate2-eyesXcoordinate,(eyesYcoordinate2-eyesYcoordinate)/2 );

     //   rightcroppedimage2 = Bitmap.createBitmap(resizedBitmap, eyesX2coordinate - (rx-resizedBitmap.getWidth())/2, eyesY2coordinate+ (eyesY2coordinate2-eyesY2coordinate)/2,eyesX2coordinate2-eyesX2coordinate,(eyesY2coordinate2-eyesY2coordinate)/2 );
       // rightcroppedimage = Bitmap.createBitmap(resizedBitmap, eyesX2coordinate - (rx-resizedBitmap.getWidth())/2, eyesY2coordinate ,eyesX2coordinate2-eyesX2coordinate,(eyesY2coordinate2-eyesY2coordinate)/2 );


        resizedBitmap1= resizedBitmap;


        grd.setImageBitmap(resizedBitmap1);



        ViewGroup.MarginLayoutParams marginParams1 = new ViewGroup.MarginLayoutParams(fr.getLayoutParams());
        marginParams1.setMargins(eyesXcoordinate, eyesYcoordinate, 0, 0);
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(marginParams1);
        layoutParams1.width =eyesXcoordinate2-eyesXcoordinate ;
        layoutParams1.height=eyesYcoordinate2-eyesYcoordinate;
        fr.setLayoutParams(layoutParams1);
        fr.setVisibility(View.VISIBLE);

        //img.setBackgroundColor(resizedBitmap1.getPixel(eyesXcoordinate, eyesYcoordinate2 + 5));
        //GradientDrawable drawable3 = (GradientDrawable)img.getBackground();
        // drawable3.setColor(resizedBitmap1.getPixel(eyesXcoordinate + 40 - (rx - resizedBitmap.getWidth()) / 2, (int) (eyesYcoordinate - (conversiony - resizedBitmap.getHeight()) / 2 - 10)));

        // GradientDrawable drawable111 = (GradientDrawable)img.getBackground();
        //drawable111.setColor(resizedBitmap.getPixel(pupilrightx - (rx - resizedBitmap.getWidth()) / 2 - 5, (int) (pupilrighty - (conversiony - resizedBitmap.getHeight()) / 2 + 35))); //original
        // drawable111.setColor(Color.WHITE);

        ViewGroup.MarginLayoutParams marginParamsright = new ViewGroup.MarginLayoutParams(righteyelidback.getLayoutParams());
        marginParamsright.setMargins(eyesX2coordinate, eyesY2coordinate, 0, 0);
        FrameLayout.LayoutParams layoutParamsright = new FrameLayout.LayoutParams(marginParamsright);
        layoutParamsright.width =eyesX2coordinate2 - eyesX2coordinate ;
        layoutParamsright.height= eyesY2coordinate2-eyesY2coordinate;
        righteyelidback.setLayoutParams(layoutParamsright);
        righteyelidback.setVisibility(View.VISIBLE);



        righteyelid.setVisibility(View.INVISIBLE);
        righteyelidbtm.setVisibility(View.INVISIBLE);
        righteyelidback.setVisibility(View.INVISIBLE);
        f.setVisibility(View.INVISIBLE);
        fbottom.setVisibility(View.INVISIBLE);
        fr.setVisibility(View.INVISIBLE);
        final Button backtoselection = (Button) findViewById(R.id.backbutton);
        final Button reanimate = (Button) findViewById(R.id.reanimatebutton);
        Bitmap x1 = BitmapFactory.decodeResource(getResources(), R.mipmap.whiterect );
        final FrameLayout fr1 = (FrameLayout) findViewById(R.id.white);
        ImageView img1 = (ImageView) findViewById(R.id.imageView4);
        final FrameLayout f1=(FrameLayout) findViewById(R.id.frame);

        final FrameLayout righteyeback = (FrameLayout) findViewById(R.id.righteyeback);
        final FrameLayout righteye = (FrameLayout) findViewById(R.id.righteyelast);

        final ImageView righteyeimage = (ImageView) findViewById(R.id.righteyeimg);
        ImageView righteyebackimg = (ImageView) findViewById(R.id.righteyewhite);

        f1.setVisibility(View.VISIBLE);
        righteye.setVisibility(View.VISIBLE);
        righteyeimage.setVisibility(View.VISIBLE);
        backtoselection.setVisibility(View.VISIBLE);
        reanimate.setVisibility(View.VISIBLE);
        //btn.setText("Create VivoID");


        int myValue = pupilXnew;
        int p2xx=  pupilX1new;
        int myValuey=  pupilYnew;
        int p2y =  pupilY1new;

        myValue= (int) (myValue/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
        p2xx= (int) (p2xx/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
        pupil2X= (int) (pupil2X/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
        pupil2X1= (int) (pupil2X1/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);

        myValuey = (int) (myValuey /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        p2y = (int) (p2y /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        pupil2Y = (int) (pupil2Y /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
        pupil2Y1 = (int) (pupil2Y1 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);

        pupilXnew=myValue;
        pupilX1new=p2xx;
        pupilYnew=myValuey;
        pupilY1new=p2y;

        int pupilerror= 1*(int)density1;

        Bitmap croppedBitmapright;

        if(setBitmap.getHeight()<setBitmap.getWidth())

        {

            matrix1.postScale(rx / btmwidth, rx / btmwidth);

            //resizedBitmap = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);
            croppedBitmap = Bitmap.createBitmap(resizedBitmap, myValue,(int) (myValuey-(conversiony- resizedBitmap.getHeight())/2), p2xx-myValue,p2y-myValuey);

            croppedBitmapright = Bitmap.createBitmap(resizedBitmap, pupil2X,(int) (pupil2Y-(conversiony- resizedBitmap.getHeight())/2), pupil2X1-pupil2X,pupil2Y1-pupil2Y);


        }

        else {

            int valx = (int)  myValue - (rx-resizedBitmap.getWidth())/2 ;

            if (valx < 0) valx = 0;


            matrix1.postScale(conversiony / btmheight, conversiony / btmheight);
            croppedBitmap = Bitmap.createBitmap(resizedBitmap, valx, myValuey , p2xx-myValue,p2y-myValuey);
            croppedBitmapright = Bitmap.createBitmap(resizedBitmap, pupil2X - (rx-resizedBitmap.getWidth())/2, pupil2Y , pupil2X1-pupil2X,pupil2Y1-pupil2Y);

        }

        Bitmap important = resizedBitmap;


        ViewGroup.MarginLayoutParams marginParams11 = new ViewGroup.MarginLayoutParams(fr1.getLayoutParams());
        marginParams11.setMargins(myValue+pupilerror, myValuey+pupilerror, 0, 0);
        FrameLayout.LayoutParams layoutParams11 = new FrameLayout.LayoutParams(marginParams11);
        layoutParams11.width =p2xx-myValue-2*pupilerror ;
        layoutParams11.height=p2y - myValuey-2*pupilerror ;
        fr1.setLayoutParams(layoutParams11);
        fr1.setVisibility(View.VISIBLE);
        //img1.setBackgroundColor(resizedBitmap.getPixel(myValue - (rx - resizedBitmap.getWidth()) / 2 + p2x - myValue + 5, myValuey + 15));
        // GradientDrawable drawable = (GradientDrawable)img1.getBackground();
        // drawable.setColor(resizedBitmap.getPixel(myValue - (rx - resizedBitmap.getWidth()) / 2- 5, myValuey + 35));
        GradientDrawable drawable111 = (GradientDrawable)img1.getBackground();



        ViewGroup.MarginLayoutParams marginParamsright1 = new ViewGroup.MarginLayoutParams(righteyeback.getLayoutParams());
        marginParamsright1.setMargins(pupil2X+pupilerror, pupil2Y+pupilerror, 0, 0);
        FrameLayout.LayoutParams layoutParamsright1 = new FrameLayout.LayoutParams(marginParamsright1);
        layoutParamsright1.width =pupil2X1-pupil2X -2*pupilerror;
        layoutParamsright1.height=pupil2Y1-pupil2Y-2*pupilerror  ;
        righteyeback.setLayoutParams(layoutParamsright1);
        righteyeback.setVisibility(View.VISIBLE);
        //righteyebackimg.setBackgroundColor(resizedBitmap.getPixel(pupil2X - (rx - resizedBitmap.getWidth()) / 2 + pupil2X1 - pupil2X + 5, pupil2Y + 15));
        // GradientDrawable drawable1 = (GradientDrawable)righteyebackimg.getBackground();
        // drawable1.setColor(resizedBitmap.getPixel(pupil2X - (rx - resizedBitmap.getWidth()) / 2 - 5, pupil2Y + 35));
        //drawable1.setColor(Color.GREEN);
        GradientDrawable drawable11 = (GradientDrawable)righteyebackimg.getBackground();



        ViewGroup.MarginLayoutParams marginParams21 = new ViewGroup.MarginLayoutParams(f1.getLayoutParams());
        marginParams21.setMargins(myValue, myValuey, 0, 0);
        FrameLayout.LayoutParams layoutParams21 = new FrameLayout.LayoutParams(marginParams21);
        layoutParams21.height = p2y - myValuey+pupilerror*2;
        layoutParams21.width= p2xx-myValue+pupilerror*2;
        f1.setLayoutParams(layoutParams21);
        movement.setImageBitmap(getclip(croppedBitmap));

        drawable11.setColor(important.getPixel(pupil2X  - (rx - important.getWidth()) / 2 - (int)(2*density1), (int) (pupil2Y - (conversiony - important.getHeight()) / 2 + (int)(9*density1))));
        drawable111.setColor(resizedBitmap.getPixel(myValue - (rx - important.getWidth()) / 2   - (int)(2*density1), (int) (myValuey - (conversiony - important.getHeight()) / 2)+ (int)(9*density1))); //original

        ViewGroup.MarginLayoutParams marginParamsright21 = new ViewGroup.MarginLayoutParams(righteye.getLayoutParams());
        marginParamsright21.setMargins(pupil2X, pupil2Y, 0, 0);
        FrameLayout.LayoutParams layoutParamsright21 = new FrameLayout.LayoutParams(marginParamsright21);
        layoutParamsright21.height = pupil2Y1-pupil2Y ;
        layoutParamsright21.width= pupil2X1-pupil2X;
        righteye.setLayoutParams(layoutParamsright21);
        righteyeimage.setImageBitmap(getclip(croppedBitmapright));


        final float density2 = Resources.getSystem().getDisplayMetrics().density;
        final float  distancex= 5;
        final float distancey= 3;
        final float setY =  movement.getY();
        final float setX = movement.getX();
        //final float setX = (pupil2X-pupilX1)/2+pupilX1;;

        final double midx=((double)pupilX-(double)pupil2X1)/2+pupil2X1;
        final float middlex = (float) midx;

        final float setrightY =  righteyeimage.getY();
        final float setrightX = righteyeimage.getX();


        final float myvalueyfinal = myValuey-2;
        final float myvaluexfinal = middlex-2;



        final float myvalueyfinalright = pupil2Y-2;
        final float myvaluexfinalright = middlex-2;




        FrameLayout r1 = (FrameLayout) findViewById(R.id.frameLayout);
        r1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:


                        float opposite = event.getRawY() - setY-myvalueyfinal;
                        float adjacent = event.getRawX()-  setX-myvaluexfinal;
                        //float adjacent = event.getRawX()-  middlex;
                        float oppositeright = event.getRawY() - setrightY-myvalueyfinalright;
                        float adjacentright = event.getRawX()-  setrightX-myvaluexfinalright;
                        //float adjacentright = event.getRawX()-  middlex;



                        double degree = Math.atan(opposite / adjacent);
                        float dX= (float) (distancex*density2*Math.cos(degree));
                        float dY = (float) (distancey*density2*Math.sin(degree));

                        double degreeright = Math.atan(oppositeright / adjacentright);
                        float dXright= (float) (distancex*density2*Math.cos(degreeright));
                        float dYright = (float) (distancey*density2*Math.sin(degreeright));




                        if(adjacent < 0 ){dX=dX*-1;  dY=dY*-1;}
                        if(adjacentright < 0 ){dXright=dXright*-1;  dYright=dYright*-1;}


                        movement.animate()
                                .x(setX + dX)
                                .y(setY + dY)
                                .setDuration(0)
                                .start();


                        righteyeimage.animate()
                                .x(setrightX + dX)
                                .y(setY + dY)
                                .setDuration(0)
                                .start();



                       /* righteyeimage.animate()
                                .x(setrightX + dXright)
                                .y(setrightY + dYright)
                                .setDuration(0)
                                .start();*/

                        break;


                    case MotionEvent.ACTION_MOVE:

                        float opposite1 = event.getRawY() - setY-myvalueyfinal;
                        float adjacent1 = event.getRawX()-  setX-myvaluexfinal;


                        float oppositeright1 = event.getRawY() - setrightY-myvalueyfinalright;
                        float adjacentright1 = event.getRawX()-  setrightX-myvaluexfinalright;

                        //float adjacentright1 = event.getRawX()-  middlex-myvaluexfinalright;

                        double degree1 = Math.atan(opposite1 / adjacent1);
                        Float dX1= (float) (distancex*density2*Math.cos(degree1));
                        Float dY1 = (float) (distancey*density2*Math.sin(degree1));

                        double degreeright1 = Math.atan(oppositeright1 / adjacentright1);
                        Float dX1right= (float) (distancex*density2*Math.cos(degreeright1));
                        Float dY1right = (float) (distancey*density2*Math.sin(degreeright1));




                        if(adjacent1 < 0 ){ dX1=dX1*-1; dY1=dY1*-1;}
                        if(adjacentright1 < 0 ){ dX1right=dX1right*-1; dY1right=dY1right*-1;}
                        // else if (adjacent1 < 0 && opposite1<0){dX1=dX1*-1;}



                        movement.animate()
                                .x(setX+ dX1)
                                .y(setY + dY1)
                                .setDuration(0)
                                .start();

                        righteyeimage.animate()
                                .x(setrightX+ dX1)
                                .y(setY + dY1)
                                .setDuration(0)
                                .start();

                      /*  righteyeimage.animate()
                                .x(setrightX+ dX1right)
                                .y(setrightY + dY1right)
                                .setDuration(0)
                                .start();*/

                        break;

                    case MotionEvent.ACTION_UP:


                        movement.animate()
                                .x(setX )
                                .y(setY )
                                .setDuration(0)
                                .start();

                        righteyeimage.animate()
                                .x(setrightX )
                                .y(setY )
                                .setDuration(0)
                                .start();

                        /*righteyeimage.animate()
                                .x(setrightX )
                                .y(setrightY )
                                .setDuration(0)
                                .start();*/


                    default:
                        return false;
                }
                return true;

            }
        });


        ImageView mvm = (ImageView) findViewById(R.id.imageView8);
        ImageView ellipse = (ImageView) findViewById(R.id.imageView9);

        String photoPathmouth = root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg";


        Bitmap setBitmapmouth = BitmapFactory.decodeFile(photoPathmouth,options);


        Bitmap croppedBitmapmouth,halfmouth,croppedBitmapmouth2;

        Bitmap resultmouth;


        if(setBitmapmouth.getHeight()<setBitmapmouth.getWidth())

        {


            croppedBitmapmouth = Bitmap.createBitmap(resizedBitmap, mouthx,(int) (mouthy-(conversiony- resizedBitmap.getHeight())/2), mouthx1-mouthx,mouthy1-mouthy);
            croppedBitmapmouth2 = Bitmap.createBitmap(resizedBitmap, mouthx - (rx-resizedBitmap.getWidth())/2, mouthy , mouthx1+mouthx,mouthy1-mouthy);
            resultmouth = croppedBitmapmouth;
            //SaveImage2(resultmouth);

        }

        else {


            mouthx= (int) (mouthx/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
            mouthx1= (int) (mouthx1/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
            mouthy = (int) (mouthy /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
            mouthy1 = (int) (mouthy1 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);

            chinx= (int) (chinx/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
            chinx1= (int) (chinx1/width*resizedBitmap.getWidth()+(rx - resizedBitmap.getWidth())/2);
            chiny = (int) (chiny /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);
            chiny1 = (int) (chiny1 /height*resizedBitmap.getHeight()+(conversiony- resizedBitmap.getHeight())/2);

            Log.d("mouthy:",Integer.toString(mouthy));
            Log.d("mouthy1:",Integer.toString(mouthy1));
            Log.d("chiny1:",Integer.toString(chiny1));
            Log.d("resizedBitmap.getWidth:",Integer.toString(resizedBitmap.getWidth()));

            Log.d("resizetmap.getHeight:",Integer.toString(resizedBitmap.getHeight()));

            //croppedBitmapmouth = Bitmap.createBitmap(resizedBitmap, mouthx - (rx-resizedBitmap.getWidth())/2, mouthy , mouthx1-mouthx,mouthy1-mouthy);
            croppedBitmapmouth = Bitmap.createBitmap(resizedBitmap, chinx - (rx-resizedBitmap.getWidth())/2,  mouthy+(mouthy1-mouthy)/2-(chiny1-(mouthy+(mouthy1-mouthy)/2)), chinx1-chinx,(chiny1-(mouthy+(mouthy1-mouthy)/2))*2);
            croppedBitmapmouth2 = Bitmap.createBitmap(resizedBitmap, mouthx - (rx-resizedBitmap.getWidth())/2, mouthy , mouthx1-mouthx,mouthy1-mouthy);


        }


        //resizedBitmap= sketch(resizedBitmap);
        //croppedBitmapmouth=sketch(croppedBitmapmouth);
        //grd.setImageBitmap(resizedBitmap);
        final FrameLayout fmouth=(FrameLayout) findViewById(R.id.framemouth);
        final FrameLayout fmouthupblack=(FrameLayout) findViewById(R.id.topmouthblackframe);
        final ImageView fmouthblackimg=(ImageView) findViewById(R.id.topblackframeimg);


        final FrameLayout frmouth = (FrameLayout) findViewById(R.id.frameblack);
        final FrameLayout fmouthup = (FrameLayout) findViewById(R.id.mouthuplayout);
        final ImageView fmouthupimg = (ImageView) findViewById(R.id.mouthupimg);

        //ImageView img = (ImageView) findViewById(R.id.imageView4);




        ViewGroup.MarginLayoutParams marginParams2mouthup = new ViewGroup.MarginLayoutParams(fmouthup.getLayoutParams());
        marginParams2mouthup.setMargins(mouthx, mouthy, 0, 0);
        FrameLayout.LayoutParams layoutParams2mouthup = new FrameLayout.LayoutParams(marginParams2mouthup);
        layoutParams2mouthup.height = mouthy1-mouthy;
        layoutParams2mouthup.width= mouthx1-mouthx;
        fmouthup.setLayoutParams(layoutParams2mouthup);
        fmouthupimg.setImageBitmap(getcliphalfup((croppedBitmapmouth2)));




        ViewGroup.MarginLayoutParams marginParams2mouth = new ViewGroup.MarginLayoutParams(fmouth.getLayoutParams());
        marginParams2mouth.setMargins(chinx,  mouthy+(mouthy1-mouthy)/2-(chiny1-(mouthy+(mouthy1-mouthy)/2)), 0, 0);
        FrameLayout.LayoutParams layoutParams2mouth = new FrameLayout.LayoutParams(marginParams2mouth);
        //layoutParams2mouth.height = mouthy1-mouthy;
        layoutParams2mouth.height = (chiny1-(mouthy+(mouthy1-mouthy)/2))*2;
        layoutParams2mouth.width=chinx1-chinx;
        fmouth.setLayoutParams(layoutParams2mouth);
        mvm.setImageBitmap(getcliphalf((croppedBitmapmouth)));
        fmouth.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams marginParams1mouth = new ViewGroup.MarginLayoutParams(frmouth.getLayoutParams());
        // marginParams1mouth.setMargins(mouthx+(mouthx1-mouthx)/4, mouthy+(mouthy1-mouthy)/2, 0, 0);
        marginParams1mouth.setMargins(mouthx, mouthy+(mouthy1-mouthy)/2-10, 0, 0);
        FrameLayout.LayoutParams layoutParams1mouth = new FrameLayout.LayoutParams(marginParams1mouth);
        //layoutParams1mouth.width =(mouthx1-mouthx)/2 ;
        layoutParams1mouth.width =mouthx1-mouthx ;
        layoutParams1mouth.height=60 ;
        frmouth.setLayoutParams(layoutParams1mouth);
        frmouth.setVisibility(View.VISIBLE);
        Bitmap x1mouth2 = BitmapFactory.decodeResource(getResources(), R.mipmap.blackrect);
        Drawable x121 = new RoundImage(x1mouth2);

        frmouth.setBackground(x121);


        final Animation mAnimation22,animationblack;

        mAnimation22 = new TranslateAnimation(0, 0, 0, -10);
        // mAnimation = new TranslateAnimation(0, 0, -20, 0);
        mAnimation22.setDuration(100);
        mAnimation22.setFillAfter(true);
        mAnimation22.setRepeatCount(5);
        mAnimation22.setRepeatMode(Animation.REVERSE);
        fmouthupimg.setAnimation(mAnimation22);
        fmouthupimg.setVisibility(View.VISIBLE);


        final Animation mAnimation;

        mAnimation = new TranslateAnimation(0, 0, 0, 15);
        // mAnimation = new TranslateAnimation(0, 0, -20, 0);

        mAnimation.setDuration(100);
        mAnimation.setFillAfter(true);
        mAnimation.setRepeatCount(5);
        mAnimation.setRepeatMode(Animation.REVERSE);

        mvm.setAnimation(mAnimation);
        mvm.setVisibility(View.VISIBLE);
        final RelativeLayout rel = (RelativeLayout) findViewById(R.id.Previewglobal);



        backtoselection.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backtoselection.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        STATE = MAINSTATE;
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        SharedPreferences pref;
                        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("backbuttoneyes", true);
                        editor.commit();
                        button1.setVisibility(View.VISIBLE);
                        button2.setVisibility(View.VISIBLE);
                        button3.setVisibility(View.VISIBLE);
                        rel.setVisibility(View.INVISIBLE);

                        //eyes2();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backtoselection.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        eyescircleanimation();

        reanimate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        reanimate.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        showeyelid();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        reanimate.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });


        final RelativeLayout header2 = (RelativeLayout) findViewById(R.id.Headerlast);
        header2.setVisibility(View.VISIBLE);

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        btn.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        //STATE = VIVOPASSSTATE;
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        header2.setVisibility(View.INVISIBLE);
                        STATE = VIVOPASSSTATE;
                        createid();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        btn.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });



    }
    public static Bitmap getclip(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    public static Bitmap getcliphalf(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectf = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);


        //canvas.drawArc(new RectF(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING), 0, 180, true, mRectPaint);
        canvas.drawArc(rectf, 0, 180, true, paint);
        //canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,

        //        bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    public static Bitmap getcliphalfup(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectf = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);

        //canvas.drawArc(new RectF(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING), 0, 180, true, mRectPaint);
        canvas.drawArc(rectf, 180, 180, true, paint);
        //canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,

        //        bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public void createid()

    {

        Bundle params = new Bundle();
        params.putString("image_name", "createid");
        params.putString("full_text", "createid");
        mFirebaseAnalytics.logEvent("createid", params);
        STATE = VIVOPASSSTATE;
        /*final MediaPlayer audiomuhur = MediaPlayer.create(getApplicationContext(), R.raw.damga);
        final ImageView muhur = (ImageView) findViewById(R.id.imageView55);
        final ImageView stamp = (ImageView) findViewById(R.id.imageView54);
        stamp.setVisibility(View.VISIBLE);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.topbottom);
        shake.setDuration(1500);
        stamp.setAnimation(shake);
        audiomuhur.start();

        new CountDownTimer(1500, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {

                muhur.setVisibility(View.VISIBLE);
                stamp.setVisibility(View.INVISIBLE);

            }
        }.start();*/
        final ImageView muhur = (ImageView) findViewById(R.id.imageView55);
        final ImageView stamp = (ImageView) findViewById(R.id.imageView54);

        final Button backbtnn= (Button) findViewById(R.id.button27);
        backbtnn.setVisibility(View.VISIBLE);

        final RelativeLayout rel = (RelativeLayout) findViewById(R.id.idframelayout);
        //rel.setBackgroundColor(Color.argb(255, 171, 255, 102));
        final Button backtoselection = (Button) findViewById(R.id.backbutton);
        final Button reanimate = (Button) findViewById(R.id.reanimatebutton);
        x = getResources().getDrawable(R.mipmap.idbackground);
        rel.setBackground(x);
        final FrameLayout frame = (FrameLayout) findViewById(R.id.frameLayout);
        final ImageView smallpic = (ImageView) findViewById(R.id.smallpic);
        final TextView vivoid = (TextView) findViewById(R.id.textView7);
        final TextView vivoidnumber = (TextView) findViewById(R.id.textView8);
        final Button gotogamebutton = (Button) findViewById(R.id.button13);

        backtoselection.setVisibility(View.INVISIBLE);
        reanimate.setVisibility(View.INVISIBLE);



        Long time = System.currentTimeMillis() / 1000;




        vivoidnumber.setVisibility(View.VISIBLE);
        final Random r1 = new Random();
        final int i1 = (r1.nextInt(9999999) + 1000000);
        final Random r2 = new Random();
        final int i2 = (r2.nextInt(9) + 0);

        final String ID;
        //ID = Integer.toString(i1) ;

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();


        vivoidnumber.setText(Long.toString(time));
        ID=Long.toString(time);
        // options.inSampleSize = 2;

        final FrameLayout picture = (FrameLayout) findViewById(R.id.Picture);

        String root = Environment.getExternalStorageDirectory().toString();
        String photoPath =root + "/application/vivo/vivofiles/junk/" + ".pic2.jpg";
        setBitmap = BitmapFactory.decodeFile(photoPath,options);



        picture.setVisibility(View.VISIBLE);
        smallpic.setImageBitmap(setBitmap);

        final Button bnext = (Button) findViewById(R.id.button2);
        final Button bgotogame = (Button) findViewById(R.id.button13);
        vivoid.setVisibility(View.VISIBLE);
        frame.setVisibility(View.INVISIBLE);
        bnext.setVisibility(View.INVISIBLE);
        bgotogame.setVisibility(View.VISIBLE);
        final EditText e1 = (EditText) findViewById(R.id.editText12);
        final TextView e2 = (TextView) findViewById(R.id.editText13);
        final TextView t1 = ( TextView)findViewById(R.id.textname);
        final TextView t2 = ( TextView)findViewById(R.id.textname2);
        final TextView t3 = ( TextView)findViewById(R.id.textdate);
        final TextView t4 = ( TextView)findViewById(R.id.vivoidnumber);
        final TextView maxlength= (TextView) findViewById(R.id.textView40);

        final TextView e3 = (TextView) findViewById(R.id.editText14);
       // Typeface tf = Typeface.createFromAsset(getAssets(), "CalibriL.ttf");
        //meattext2.setTypeface(tf);
       // gotogamebutton.setTypeface(tf);

       // e1.setText(name);
        e2.setText(displayname);
       // e1.setTextColor(Color.WHITE);
        //e1.setHintTextColor(Color.WHITE);
        //e2.setHintTextColor(Color.WHITE);
        //e3.setHintTextColor(Color.WHITE);


        //e2.setTextColor(Color.WHITE);
        //e3.setTextColor(Color.WHITE);
        //vivoid.setTextColor(Color.WHITE);
        // vivoid.setTextSize(14f);
        //vivoidnumber.setTextColor(Color.WHITE);
        //vivoidnumber.setTextSize(14f);

       // t1.setTypeface(tf);
       // t2.setTypeface(tf);
       /// t3.setTypeface(tf);
        //e1.setTypeface(tf);
       // e2.setTypeface(tf);
       //// e3.setTypeface(tf);
       // vivoid.setTypeface(tf);
       // vivoidnumber.setTypeface(tf);;

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH)+1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //Log.d("yigit",Integer.toString(mMonth));
        e3.setText(mDay + "/" + mMonth + "/" + mYear);

        //maxlength.setVisibility(View.VISIBLE);

        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
        t4.setVisibility(View.VISIBLE);
        e1.setVisibility(View.VISIBLE);
        e2.setVisibility(View.VISIBLE);
        e3.setVisibility(View.VISIBLE);

       final Context con = this;

        final RelativeLayout warning = (RelativeLayout) findViewById(R.id.warningbox2);
        final Button okbutton = (Button) findViewById(R.id.button362);


        okbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        okbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        final Animation shake = AnimationUtils.loadAnimation(con, R.anim.righttoleft);
                        shake.setDuration(SLIDEANIMDUR);
                        warning.setAnimation(shake);
                        warninggone();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        okbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });




        bgotogame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        bgotogame.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                       // STATE = VIVOPASSSTATE;

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        SharedPreferences pref;
                        pref = getSharedPreferences("info", MODE_PRIVATE);

                        SharedPreferences.Editor editor = pref.edit();

                        CharacterName = e1.getText().toString();
                        String name = e1.getText().toString();
                        String lastname = e2.getText().toString();
                        String birthdate = e3.getText().toString();
                        editor.putString("charactername", CharacterName);
                        editor.putString("name", name);
                        editor.putString("lastname", lastname);
                        editor.putString("birthdate", birthdate);
                        editor.putString("vivoid", ID);
                        editor.commit();


                        if(CharacterName.length() < 3)

                        {
                            warning.setVisibility(View.VISIBLE);
                            final Animation shake = AnimationUtils.loadAnimation(con, R.anim.lefttoright);

                            shake.setDuration(SLIDEANIMDUR);
                            warning.setAnimation(shake);

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

                        }
                        else if (CharacterName.length() > 20 ) {
                            warning.setVisibility(View.VISIBLE);
                            final Animation shake = AnimationUtils.loadAnimation(con, R.anim.lefttoright);
                            shake.setDuration(SLIDEANIMDUR);
                            warning.setAnimation(shake);
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

                        }

                        else

                        {
                            //goGame();
                        stampanimation();

                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        bgotogame.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });


        final RelativeLayout header2 = (RelativeLayout) findViewById(R.id.Headerlast);


        backbtnn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbtnn.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        STATE = PREVIEWSTATE;
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        backtoselection.setVisibility(View.VISIBLE);
                        reanimate.setVisibility(View.VISIBLE);
                        muhur.setVisibility(View.INVISIBLE);
                        stamp.setVisibility(View.INVISIBLE);
                        vivoidnumber.setVisibility(View.INVISIBLE);
                        picture.setVisibility(View.INVISIBLE);
                        vivoid.setVisibility(View.INVISIBLE);
                        frame.setVisibility(View.VISIBLE);
                        bnext.setVisibility(View.VISIBLE);
                        bgotogame.setVisibility(View.INVISIBLE);
                        e1.setVisibility(View.INVISIBLE);
                        maxlength.setVisibility(View.INVISIBLE);
                        e2.setVisibility(View.INVISIBLE);
                        e3.setVisibility(View.INVISIBLE);
                        t1.setVisibility(View.INVISIBLE);
                        t2.setVisibility(View.INVISIBLE);
                        t3.setVisibility(View.INVISIBLE);
                        t4.setVisibility(View.INVISIBLE);
                        backbtnn.setVisibility(View.INVISIBLE);
                        header2.setVisibility(View.VISIBLE);
                        rel.setBackgroundColor(Color.argb(0,0,0,0));
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbtnn.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {


                        break;
                    }
                }
                return true;
            }
        });


    }
    public void goGame(){

        Bundle params = new Bundle();
        params.putString("image_name", "gogameinit");
        params.putString("full_text", "gogameinit");
        mFirebaseAnalytics.logEvent("gogameinit", params);

        Intent i = new Intent(this, GameforActivity.class);

        //EditText e1= (EditText) findViewById(R.id.editText);
        //String x = e1.getText().toString();

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        editor.putInt("newgame",0);
        editor.commit();

        i.putExtra("Pupil2X", pupil2X);
        i.putExtra("Pupil2Y", pupil2Y);
        i.putExtra("Pupil2X1", pupil2X1);
        i.putExtra("Pupil2Y1", pupil2Y1);

        i.putExtra("PupilX", pupilXnew);
        i.putExtra("PupilY", pupilYnew);
        i.putExtra("PupilX1", pupilX1new);
        i.putExtra("PupilY1", pupilY1new);

        i.putExtra("mouthx", mouthx);
        i.putExtra("mouthy", mouthy);
        i.putExtra("mouthx1", mouthx1);
        i.putExtra("mouthy1", mouthy1);


        i.putExtra("eyesx",eyesXcoordinate );
        i.putExtra("eyesy",eyesYcoordinate );
        i.putExtra("eyesx1",eyesXcoordinate2 );
        i.putExtra("eyesy1",eyesYcoordinate2 );

        i.putExtra("eyes2x",eyesX2coordinate );
        i.putExtra("eyes2y",eyesY2coordinate );
        i.putExtra("eyes2x1",eyesX2coordinate2 );
        i.putExtra("eyes2y1",eyesY2coordinate2 );

        i.putExtra("chinx",chinx );
        i.putExtra("chinx1",chinx1 );
        i.putExtra("chiny",chiny );
        i.putExtra("chiny1",chiny1 );

        //i.putExtra("edittext",x );
        i.putExtra("width",width );
        i.putExtra("height",height );
        newgame = 0;
        i.putExtra("newgame",newgame);


        startActivity(i);
        finish();

    }

    public void pluscoinlayout3(){




        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox);


        new CountDownTimer(SLIDEANIMDUR, 100) {
            public void onTick(long milsec) {


            }

            public void onFinish() {

                iquplevel.setVisibility(View.INVISIBLE);
            }
        }.start();



    }

    public void warninggone(){




        final RelativeLayout iquplevel = (RelativeLayout) findViewById(R.id.warningbox2);


        new CountDownTimer(SLIDEANIMDUR, 100) {
            public void onTick(long milsec) {


            }

            public void onFinish() {

                iquplevel.setVisibility(View.INVISIBLE);
            }
        }.start();



    }


    public void stampanimation(){
        final EditText e1 = (EditText) findViewById(R.id.editText12);
        final TextView Button= (Button) findViewById(R.id.button13);
        final TextView e2 = (TextView) findViewById(R.id.editText13);
        final TextView e3 = (TextView) findViewById(R.id.editText14);
        final Button backbtnn= (Button) findViewById(R.id.button27);
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        Button.setEnabled(false);
        backbtnn.setEnabled(false);


        final MediaPlayer audiomuhur = MediaPlayer.create(getApplicationContext(), R.raw.damga);
        final ImageView muhur = (ImageView) findViewById(R.id.imageView55);
        final ImageView stamp = (ImageView) findViewById(R.id.imageView54);
        stamp.setVisibility(View.VISIBLE);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.topbottom);
        shake.setDuration(900);
        stamp.setAnimation(shake);
        if(!mutestate) audiomuhur.start();

        new CountDownTimer(1500, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {

                muhur.setVisibility(View.VISIBLE);
                stamp.setVisibility(View.INVISIBLE);

            }
        }.start();

        new CountDownTimer(2500, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {


                stamp.invalidate();
                audiomuhur.release();
                goGame();

            }
        }.start();


        new CountDownTimer(900, 100) {
            public void onTick(long milsec) {

            }

            public void onFinish() {
                final ScaleAnimation shrinkAnim = new ScaleAnimation(1.0f, 0.85f, 1.0f, 0.85f);
                shrinkAnim.setDuration(600);
                stamp.setAnimation(shrinkAnim);
                shrinkAnim.start();

            }
        }.start();



    }

    public void eyescircleanimation() {

  //      final FrameLayout f = (FrameLayout) findViewById(R.id.frame);
        final ImageView movement = (ImageView) findViewById(R.id.imageView22);
//        final FrameLayout righteye = (FrameLayout) findViewById(R.id.righteye);
        final ImageView righteyeimage = (ImageView) findViewById(R.id.righteyeimg);

        // f.setVisibility(View.VISIBLE);
        //  righteye.setVisibility(View.VISIBLE);


      //  final MediaPlayer kiss = MediaPlayer.create(getApplicationContext(), R.raw.kiss);




        // if(!mutestate[0])
        //      soundPool3.play(soundID3, volume, volume, 1, 0, 1f);



        AnimationSet animations = new AnimationSet(true);
        final AnimationSet animations2 = new AnimationSet(true);

        ArcTranslationAnimation anim = new ArcTranslationAnimation(0, 0 , 0, -15);
        anim.setDuration(150);
        //anim.setFillAfter(true);
        animations.addAnimation(anim);


        final ArcTranslationAnimation anim2 = new ArcTranslationAnimation(0, -15 , 0, 15);
        anim2.setStartOffset(150);
        anim2.setDuration(150);
        //anim2.setFillAfter(true);
        animations.addAnimation(anim2);

        final ArcTranslationAnimation anim3 = new ArcTranslationAnimation(0, 15 , 0, 15);
        anim3.setStartOffset(300);
        anim3.setDuration(150);
        // anim3.setFillAfter(true);
        animations.addAnimation(anim3);


        final ArcTranslationAnimation anim4 = new ArcTranslationAnimation(0, 15 , 0, -15);
        anim4.setStartOffset(450);
        anim4.setDuration(150);
        //anim4.setFillAfter(true);
        animations.addAnimation(anim4);

        ArcTranslationAnimation anim5 = new ArcTranslationAnimation(0, -15 , 0, -15);
        anim5.setStartOffset(600);
        anim5.setDuration(150);
        //anim.setFillAfter(true);
        animations.addAnimation(anim5);


        final ArcTranslationAnimation anim6 = new ArcTranslationAnimation(0, -15 , 0, 15);
        anim6.setStartOffset(750);
        anim6.setDuration(150);
        //anim2.setFillAfter(true);
        animations.addAnimation(anim6);

        final ArcTranslationAnimation anim7 = new ArcTranslationAnimation(0, 15 , 0, 15);
        anim7.setStartOffset(900);
        anim7.setDuration(150);
        // anim3.setFillAfter(true);
        animations.addAnimation(anim7);


        final ArcTranslationAnimation anim8 = new ArcTranslationAnimation(0, 15 , 0, -15);
        anim8.setStartOffset(1050);
        anim8.setDuration(150);
        //anim8.setFillAfter(true);
        animations.addAnimation(anim8);


        ArcTranslationAnimation anim9 = new ArcTranslationAnimation(0, -15 , 0, -15);
        anim9.setStartOffset(1200);
        anim9.setDuration(150);
        //anim.setFillAfter(true);
        animations.addAnimation(anim9);


        final ArcTranslationAnimation anim10 = new ArcTranslationAnimation(0, -15 , 0, 15);
        anim10.setStartOffset(1350);
        anim10.setDuration(150);
        //anim2.setFillAfter(true);
        animations.addAnimation(anim10);

        final ArcTranslationAnimation anim11 = new ArcTranslationAnimation(0, 15 , 0, 15);
        anim11.setStartOffset(1500);
        anim11.setDuration(150);
        // anim3.setFillAfter(true);
        animations.addAnimation(anim11);


        final ArcTranslationAnimation anim12 = new ArcTranslationAnimation(0, 15 , 0, -15);
        anim12.setStartOffset(1650);
        anim12.setDuration(150);
        anim12.setFillAfter(true);
        animations.addAnimation(anim12);






        movement.startAnimation(animations);
        righteyeimage.startAnimation(animations);









    }





    public void updateEnglish(){

    TextView textview58 =(TextView)  (findViewById(R.id.textView58));
    Button button30 =(Button) (findViewById(R.id.button30));
    TextView textView89 =(TextView)  (findViewById(R.id.textView89));
    Button righteye =(Button) (findViewById(R.id.righteye));
    Button lefteye =(Button) (findViewById(R.id.lefteye));
    Button mouth =(Button) (findViewById(R.id.mouth));
    Button Chin =(Button) (findViewById(R.id.Chin));
    Button back =(Button) (findViewById(R.id.button26));
    Button introbutton =(Button) (findViewById(R.id.introbutton));
    Button preview =(Button) (findViewById(R.id.button));
    TextView warning =(TextView)  (findViewById(R.id.textView76));
    Button ok =(Button) (findViewById(R.id.button36));
    TextView selection =(TextView)  (findViewById(R.id.textView87));
    Button gotogame = (Button) findViewById(R.id.button13);
    Button backbutton = (Button) findViewById(R.id.backbutton);
    Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
    Button createvivoid = (Button) findViewById(R.id.button2);
    TextView Preview =(TextView)  (findViewById(R.id.textView88));
    TextView vivoid =(TextView)  (findViewById(R.id.textView7));
    TextView maxlength =(TextView)  (findViewById(R.id.textView40));
    TextView vivoname = (TextView) findViewById(R.id.textname2);
    TextView birthday = (TextView) findViewById(R.id.textdate);
    TextView t1 = (TextView) findViewById(R.id.textView872);
    TextView t2 = (TextView) findViewById(R.id.textView762);
    Button b1 = (Button) findViewById(R.id.button362);
    final Button buttonbacktoface = (Button) findViewById(R.id.button3011);

    t1.setText("Vivo name's length should be between 3 and 11 letters.");
    t2.setText("WARNING");
    b1.setText("OK");

    textview58.setText("INTRODUCTION");
    button30.setText("SKIP INTRO");
    textView89.setText("CHARACTER");
    righteye.setText("RIGHT EYE");
    lefteye.setText("LEFT EYE");
    mouth.setText("MOUTH");
    Chin.setText("CHIN");
    back.setText("BACK");
    buttonbacktoface.setText("BACK");
    introbutton.setText("INTRO");
    preview.setText("NEXT");
    warning.setText("WARNING");
    selection.setText("Selection Out Of Bounds. Select in the region please.");
    gotogame.setText("GO TO GAME");
    backbutton.setText("BACK");
    reanimatebutton.setText("REANIMATE");
    createvivoid.setText("NEXT");
    Preview.setText("PREVIEW");
    vivoid.setText("VIVO PASS");
    maxlength.setText("Max Length(15)");
    vivoname.setText("Vivo Name");
    birthday.setText("Date of Birth");

}

    public void updateTurkish(){

        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);

        final Button backbutton2 = (Button) findViewById(R.id.button27);

        backbutton2.setText("GERİ");

        t1.setText("Vivo Adınız 3 ile 11 karakter arasında olmalıdır.");
        t2.setText("UYARI");
        b1.setText("TAMAM");
        ok.setText("TAMAM");

        textview58.setText("ÖRNEK");
        button30.setText("ATLA");
        textView89.setText("KARAKTER");
        righteye.setText("SAĞ GÖZ");
        lefteye.setText("SOL GÖZ");
        mouth.setText("AĞIZ");
        Chin.setText("ÇENE");
        back.setText("GERİ");
        buttonbacktoface.setText("GERİ");
        introbutton.setText("ÖRNEK");
        preview.setText("İLERİ");
        warning.setText("UYARI");
        selection.setText("Yaptığınız seçim sınırların dışındadır.Seçiminizi sınırlar içinde yapınız.");
        gotogame.setText("OYUNA BAŞLA");
        backbutton.setText("GERİ");
        reanimatebutton.setText("TEKRARLA");
        createvivoid.setText("İLERİ");
        Preview.setText("GÖSTERİM");
        vivoid.setText("VIVO PASS");
        maxlength.setText("En Fazla (15)");
        vivoname.setText("Vivo Adı");
        birthday.setText("Doğum Tarihi");



    }

    public void updateFrench(){

        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);

        final Button backbutton2 = (Button) findViewById(R.id.button27);

        backbutton2.setText("ARRIÈRE");



        t1.setText("La longueur du nom de Vivo doit être comprise entre 3 et 11 lettres.");
        t2.setText("ATTENTION");
        b1.setText("D'ACCORD");
        ok.setText("D'ACCORD");
        textview58.setText("EXEMPLE");
        button30.setText("SAUTER L'INTRO");
        textView89.setText("CARACTÈRE");
        righteye.setText("OEIL DROIT");
        lefteye.setText("OEIL GAUCHE");
        mouth.setText("BOUCHE");
        Chin.setText("MENTON");
        back.setText("ARRIÈRE");
        buttonbacktoface.setText("ARRIÈRE");
        introbutton.setText("INTRO");
        preview.setText("PROCHAIN");
        warning.setText("ATTENTION");
        selection.setText("Le choix que vous faites est hors des limites. S'il vous plaît choisir dans les limites.");
        gotogame.setText("ALLER AU JEU");
        backbutton.setText("ARRIÈRE");
        reanimatebutton.setText("RANIMER");
        createvivoid.setText("PROCHAIN");
        Preview.setText("AVANT-PREMIÈRE");
        vivoid.setText("VIVO PASS");
        maxlength.setText("Longueur Max(15)");
        vivoname.setText("Vivo Prénom");
        birthday.setText("Date de Naissance");




    }

    public void updateSpanish(){

        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);

        final Button backbutton2 = (Button) findViewById(R.id.button27);

        backbutton2.setText("ESPALDA");


        t1.setText("La longitud del nombre de Vivo debe ser entre 3 y 11 letras.");
        t2.setText("ADVERTENCIA");
        b1.setText("BUENO");
        ok.setText("BUENO");
        textview58.setText("EJEMPLO");
        button30.setText("PRESIONA INTRO");
        textView89.setText("CARÁCTER");
        righteye.setText("OJO DERECHO");
        lefteye.setText("OJO IZQUIERDO");
        mouth.setText("BOCA");
        Chin.setText("BARBILLA");
        back.setText("ESPALDA");
        buttonbacktoface.setText("ESPALDA");
        introbutton.setText("INTRO");
        preview.setText("SIGUIENTE");
        warning.setText("ADVERTENCIA");
        selection.setText("La elección que haga está fuera de límites. Por favor, elija dentro de los límites.");
        gotogame.setText("IR AL JUEGO");
        backbutton.setText("ESPALDA");
        reanimatebutton.setText("REANIMAR");
        createvivoid.setText("SIGUIENTE");
        Preview.setText("AVANCE");
        vivoid.setText("VIVO PASS");
        maxlength.setText("Max Longitud(15)");
        vivoname.setText("Vivo Nombre");
        birthday.setText("Fecha de Nacimiento");




    }

    public void updaterussian(){

        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);

        final Button backbutton2 = (Button) findViewById(R.id.button27);

        backbutton2.setText("НАЗАД");
        t1.setText("Длина Vivo зовут должно быть от 3 до 11 букв.");
        t2.setText("ПРЕДУПРЕЖДЕНИЕ");
        b1.setText("ХОРОШО");
        ok.setText("ХОРОШО");
        textview58.setText("ВВЕДЕНИЕ");
        button30.setText("ПРОПУСКАТЬ");
        textView89.setText("ПЕРСОНАЖ");
        righteye.setText("ПРАВЫЙ ГЛАЗ");
        lefteye.setText("ЛЕВЫЙ ГЛАЗ");
        mouth.setText("РОТ");
        Chin.setText("ПОДБОРОДОК");
        back.setText("НАЗАД");
        buttonbacktoface.setText("НАЗАД");
        introbutton.setText("ВСТУПЛЕНИЕ");
        preview.setText("СЛЕДУЮЩИЙ");
        warning.setText("ПРЕДУПРЕЖДЕНИЕ");
        selection.setText("Выбор вы сделаете это за пределы. Пожалуйста, выберите в пределах.");
        gotogame.setText("ПЕРЕЙТИ К ИГРЕ");
        backbutton.setText("НАЗАД");
        reanimatebutton.setText("РЕАНИМИРОВАТЬ");
        createvivoid.setText("СЛЕДУЮЩИЙ");
        Preview.setText("АНОНС");
        vivoid.setText("VIVO PASS");
        maxlength.setText("Max Length(15)");
        vivoname.setText("Vivo имя");
        birthday.setText("Дата рождения");

    }

    public void updatechinese(){


        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);
        final Button backbutton2 = (Button) findViewById(R.id.button27);
        t1.setText("Vivo名称的长度应在3到11个字母之间.");
        t2.setText("警告");
        b1.setText("好的");
        ok.setText("好的");
        textview58.setText("介绍");
        button30.setText("跳过介绍");
        textView89.setText("字符");
        righteye.setText("右眼");
        lefteye.setText("左眼");
        mouth.setText("口");
        Chin.setText("下巴");
        back.setText("背部");
        backbutton2.setText("背部");
        buttonbacktoface.setText("背部");
        introbutton.setText("介绍");
        preview.setText("下一个");
        warning.setText("警告");
        selection.setText("你做的选择是超越界限。 请在限额内选择");
        gotogame.setText("去游戏");
        backbutton.setText("背部");
        reanimatebutton.setText("赋予生命");
        createvivoid.setText("下一个");
        Preview.setText("预习");
        vivoid.setText("VIVO PASS");
        maxlength.setText("Max Length(15)");
        vivoname.setText("Vivo名称");
        birthday.setText("生日");
    }

    public void updatejapanese(){

        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);
        final Button backbutton2 = (Button) findViewById(R.id.button27);

        backbutton2.setText("バック");
        t1.setText("生体名の長さは3〜11文字でなければなりません.");
        t2.setText("警告");
        b1.setText("OK");
        ok.setText("OK");
        textview58.setText("導入");
        button30.setText("スキップ");
        textView89.setText("キャラクター");
        righteye.setText("右目");
        lefteye.setText("左目");
        mouth.setText("口");
        Chin.setText("顎");
        back.setText("バック");
        buttonbacktoface.setText("バック");
        introbutton.setText("イントロ");
        preview.setText("次");
        warning.setText("警告");
        selection.setText("あなたが選ぶ選択肢は範囲外です。 制限内で選択してください");
        gotogame.setText("ゲームに行く");
        backbutton.setText("バック");
        reanimatebutton.setText("アニメイト");
        createvivoid.setText("次");
        Preview.setText("プレビュー");
        vivoid.setText("VIVO PASS");
        maxlength.setText("Max Length(15)");
        vivoname.setText("Vivo 名");
        birthday.setText("誕生日");

    }

    public void updateportuguse(){

        TextView textview58 =(TextView)  (findViewById(R.id.textView58));
        Button button30 =(Button) (findViewById(R.id.button30));
        TextView textView89 =(TextView)  (findViewById(R.id.textView89));
        Button righteye =(Button) (findViewById(R.id.righteye));
        Button lefteye =(Button) (findViewById(R.id.lefteye));
        Button mouth =(Button) (findViewById(R.id.mouth));
        Button Chin =(Button) (findViewById(R.id.Chin));
        Button back =(Button) (findViewById(R.id.button26));
        Button introbutton =(Button) (findViewById(R.id.introbutton));
        Button preview =(Button) (findViewById(R.id.button));
        TextView warning =(TextView)  (findViewById(R.id.textView76));
        Button ok =(Button) (findViewById(R.id.button36));
        TextView selection =(TextView)  (findViewById(R.id.textView87));
        Button gotogame = (Button) findViewById(R.id.button13);
        Button backbutton = (Button) findViewById(R.id.backbutton);
        Button reanimatebutton = (Button) findViewById(R.id.reanimatebutton);
        Button createvivoid = (Button) findViewById(R.id.button2);
        TextView Preview =(TextView)  (findViewById(R.id.textView88));
        TextView vivoid =(TextView)  (findViewById(R.id.textView7));
        TextView maxlength =(TextView)  (findViewById(R.id.textView40));
        TextView vivoname = (TextView) findViewById(R.id.textname2);
        TextView birthday = (TextView) findViewById(R.id.textdate);
        TextView t1 = (TextView) findViewById(R.id.textView872);
        TextView t2 = (TextView) findViewById(R.id.textView762);
        Button b1 = (Button) findViewById(R.id.button362);
        final Button buttonbacktoface = (Button) findViewById(R.id.button3011);
        final Button backbutton2 = (Button) findViewById(R.id.button27);

        backbutton2.setText("COSTAS");
        t1.setText("Vivo nome do comprimento deve ser entre 3 e 11 letras.");
        t2.setText("ATENÇÃO");
        b1.setText("BOM");
        ok.setText("BOM");
        textview58.setText("INTRODUÇÃO");
        button30.setText("PULAR");
        textView89.setText("CARÁTER");
        righteye.setText("OLHO DIREITO");
        lefteye.setText("OLHO ESQUERDO");
        mouth.setText("BOCA");
        Chin.setText("QUEIXO");
        back.setText("COSTAS");
        buttonbacktoface.setText("COSTAS");
        introbutton.setText("INTRO");
        preview.setText("PRÓXIMO");
        warning.setText("ATENÇÃO");
        selection.setText("A escolha que você faz está fora dos limites. Por favor, escolha dentro dos limites.");
        gotogame.setText("IR PARA O JOGO");
        backbutton.setText("COSTAS");
        reanimatebutton.setText("ANIMAR");
        createvivoid.setText("PRÓXIMO");
        Preview.setText("VISUALIZAÇÃO");
        vivoid.setText("VIVO PASS");
        maxlength.setText("Max Length(15)");
        vivoname.setText("Vivo Nome");
        birthday.setText("Data de Nascimento");

    }


}
