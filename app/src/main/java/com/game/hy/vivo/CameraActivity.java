package com.game.hy.vivo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class  CameraActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final int CONTENT_REQUEST=1337;
    private File output=null;
    private DragRectView view;
    private ImageView original;
    int BallID;
        GoogleApiClient mGoogleApiClient;

    MyImageView im;
    int height;
    int width;
    int height22;
    int width22;

    private int soundID,soundID2;
    private SoundPool soundPool,soundPool2;
    AudioManager audioManager;
    float actVolume,maxVolume,volume;


    int STATE = 1;
    final int MAINSTATE = 1;
    final int FACECHOOSER = 2;


    Float BSCALE = 1f;
    Float BALPHA = 0.7f;int SLIDEANIMDUR=600;
    int GroupID;
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE =3;
    final int MY_PERMISSIONS_GET_ACCOUNT=4;
    final int MY_PERMISSIONS_REQUEST_CAMERA=5;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private Uri fileUri;
    Typeface tf;
    BitmapFactory.Options options;
    int ID;
    Point p1,p2,p3,p4;
    int x1,x2,y1,y2;
    boolean backbutton= false;
    int i1,i2,i3,i4;
    private static final String TAG = "BeGenerous";
    int CAMERA_REQUEST = 123;
    int SELECT_PICTURE=12;
    private static int RC_SIGN_IN = 9001;
    final Context xthis = this;
    IInAppBillingService mService;
    int FRAMEHEIGHT=450;
    Float FREAMEYPOS=50f;
    private AdView mAdView;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    int NONE= 100;
    int ENGLISH = 0;
    int TURKISH =1 ;
    int FRENCH =2;
    int SPANISH =3;
    int RUSSIAN =4;
    int CHINESE =5 ;
    int JAPANESE = 6;
    int PORTUGUESE =7;
    Boolean mutestate=false;
    Uri uri;
    int gamelang = NONE;
    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;
    boolean mExplicitSignOut = false;
    boolean mInSignInFlow = false; // set
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

    }

    public void onPause() {

        if (mAdView != null) {
            mAdView.pause();
        }

        super.onPause();}

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
        //findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        //findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
       // findViewById(R.id.button29).setVisibility(View.VISIBLE);
        //Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_id), iq);
// Our sample displays the request counts.
//updateRequestCounts();




    final String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Log.i(TAG, "#onConnected - GoogleApiClient accountName=" + accountName);

        Person me = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
    Player p = Games.Players.getCurrentPlayer(mGoogleApiClient);


    String displayName= p.getDisplayName();

        Person.Name name = me.getName();
        String given = name.getGivenName();
        String family = name.getFamilyName();

    Log.d(TAG, "PlayerID: " + displayName);

        Log.d(TAG, "Given: " + given + ", Family: " + family);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("chname", given + family);
    editor.putString("displayname", displayName);
        editor.putString("accountname", accountName);
        TextView name2 = (TextView) findViewById(R.id.name);
        //CharacterName=given + " " + family;
        //name2.setText(CharacterName);

        editor.commit();

        }

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .addApi(AppIndex.API).build();
        //tf = Typeface.createFromAsset(getAssets(), "browbold.ttf");
        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(R.layout.activity_camera);
        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        final Button Library = (Button) findViewById(R.id.button3);
        final Button camera = (Button) findViewById(R.id.button2);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("353720052948557")
                .build();
        TextView header = (TextView) findViewById(R.id.textView41);
        //Typeface brow = Typeface.createFromAsset(getAssets(), "browbold.ttf");

        //header.setTypeface(brow);
        header.setTextSize(25);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volume = actVolume / maxVolume;

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool2 = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {



            }

        });

        soundPool2.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {



            }

        });


        String root = Environment.getExternalStorageDirectory().toString();

            File myDir = new File(root + "/application/vivo/vivofiles/junk/");
            myDir.mkdirs();
            //Random generator = new Random();
            //int n = 10000;
            //n = generator.nextInt(n);
            String fname = ".pic1.jpeg";
            File file = new File (myDir, fname);


            Bitmap im4 = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.videopng);


        Log.d("yigiterbas",Environment.getExternalStorageState().toString());

      /*      Log.i(TAG, "" + file);
            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                im4.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();


            } catch (Exception e) {
                e.printStackTrace();
            }*/

        String photoPath4 = "/storage/emulated/0/application/vivo/vivofiles/junk/" + ".pic2.jpg";
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());



            builder.detectFileUriExposure();





        File file2 = new File(photoPath4);
        if(file2.exists()) file2.delete();
        File wallpaperDirectory = new File(root + "/application/vivo/vivofiles/junk/");
        wallpaperDirectory.mkdir();
        File outputFile = new File(wallpaperDirectory, ".nomedia");
        // now attach the OutputStream to the file object, instead of a String representation

        if (!outputFile.exists()){
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





        soundID = soundPool.load(this, R.raw.click2017, 1);
        soundID2 = soundPool2.load(this, R.raw.popupsound, 1);
       /*
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.GET_ACCOUNTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        MY_PERMISSIONS_GET_ACCOUNT);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }*/


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


        int newgame=shared1.getInt("newgame", 0);



        Boolean mute2 = shared1.getBoolean("mutestate", false);
            mutestate = mute2;
        //final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.click2017);
        gamelang = shared1.getInt("gamelang", NONE);
        if (gamelang == ENGLISH) updateEnglish();
        else if (gamelang == TURKISH) updateTurkish();
        else if (gamelang == FRENCH) updateFrench();
        else if (gamelang == SPANISH) updateSpanish();
        else if (gamelang == RUSSIAN) updateRussian();
        else if (gamelang == CHINESE) updateChinese();
        else if (gamelang == JAPANESE) updatejapanese();
        else if (gamelang == PORTUGUESE) updateporteugese();
        else updateEnglish();
        int ongame = shared1.getInt("ongame", 0);
        backbutton = shared1.getBoolean("backbutton", backbutton);

        if (ongame == 1)

        {
            facechooser();
            ongame = 0;
        }


        String photoPath = "/storage/extSdCard/DCIM/Camera/";
        File file1 = new File(photoPath);
        boolean deleted = file1.delete();
        // final RelativeLayout b = (RelativeLayout) findViewById(R.id.b);

        final Button back = (Button) findViewById(R.id.button25);
        final TextView t11 = (TextView) findViewById(R.id.textView27);
        //Typeface tf = Typeface.createFromAsset(getAssets(), "irisupsbold.ttf");
       // t11.setTypeface(tf);
        t11.setTextSize(20);

        final Button b = (Button) findViewById(R.id.button);
        final Button b1 = (Button) findViewById(R.id.rotate);
        final Button bakbutton = (Button) findViewById(R.id.button20);


        bakbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        bakbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        STATE = MAINSTATE;
                        RelativeLayout cameralayout = (RelativeLayout) findViewById(R.id.cameralayout);

                        FrameLayout facechooser = (FrameLayout) findViewById(R.id.facechooser);
                        // tomain();

                        RelativeLayout r = (RelativeLayout) findViewById(R.id.mainlayout);
                        r.removeView(im);

                        cameralayout.setVisibility(View.VISIBLE);
                        back.setVisibility(View.VISIBLE);
                        facechooser.setVisibility(View.INVISIBLE);
                        b.setVisibility(View.INVISIBLE);
                        bakbutton.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        bakbutton.setAlpha(1f);


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


        camera.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        camera.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        cameracall();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        camera.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        Library.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Library.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, SELECT_PICTURE);

                       /* if (Build.VERSION.SDK_INT < 19) {
                            Intent intent = new Intent();
                            //intent.setType("image/jpeg");
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            //intent.setAction(Intent.ACTION_PICK);

                            startActivityForResult(Intent.createChooser(intent,
                                    "Select Picture"), SELECT_PICTURE);
                        } else {
                            //Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                            Intent intent = new Intent();
                            intent.setType("image/*");
                           // intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent,
                                    "Select Picture"), SELECT_PICTURE);
                        }*/

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Library.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

                return;
            }
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;


            }

            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    String root = Environment.getExternalStorageDirectory().toString();
                    File file = new File(root + "/application/vivo/vivofiles/junk/", ".pic1.jpeg");
                    Bitmap im = BitmapFactory.decodeResource(this.getResources(),
                            R.mipmap.vivo8);

                    SaveImage(im);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri); // set the image file name


                    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


                } else {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                    //cameracall();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }



    public static int neededRotation(File ff)
    {
        try
        {

            ExifInterface exif = new ExifInterface(ff.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            { return 270; }
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            { return 180; }
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            { return 90; }
            return 0;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = "1.1.1";
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
public void cameracall(){


      if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        else{

          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          String root = Environment.getExternalStorageDirectory().toString();
          File file = new File(root + "/application/vivo/vivofiles/junk/", ".pic1.jpeg");

          Bitmap im = BitmapFactory.decodeResource(this.getResources(),
                  R.mipmap.vivo8);
          //file.mkdirs();

          SaveImage(im);


         // fileUri = Uri.parse("/application/vivo/vivofiles/junk/pic1.jpeg");

          //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
          intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri); // set the image file name

          // start the image capture Intent
          startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

          //Intent i = new Intent(this, CameraActivityClass.class);
          //startActivityForResult(i, CAMERA_REQUEST);

    }

    // }





    }


    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                CameraActivity.this);

        /* Setting Dialog Title
      //  alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
       // alertDialog.setMessage("Are you sure you want to leave the application?");
        // Setting Icon to Dialog
       // alertDialog.setIcon(R.mipmap.ic_launcher);
        // Setting Positive "Yes" Button
       // alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        //System.exit(0);
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
        alertDialog.show();*/




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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
    @Override
    protected void onStart() {
        super.onStart();
        if (!mInSignInFlow && !mExplicitSignOut) {
            // auto sign in
            mGoogleApiClient.connect();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Camera Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.game.hy.vivo/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
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

        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {


                SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);
                int ongame = shared1.getInt("ongame", 0);

                if (ongame == 1) {
                    ongame = 0;
                } else {


                    // Intent i=new Intent(Intent.ACTION_VIEW);

                    //i.setDataAndType(Uri.fromFile(output), "image/jpeg");
                    //startActivity(i);
                    //finish();
                    //FrameLayout facechooser = (FrameLayout) findViewById(R.id.facechooser);
                    //facechooser.setVisibility(View.VISIBLE);
                    //Intent i1 = new Intent();
                    //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    //i1.putExtra("Key123", 10);
                    //setResult(Activity.RESULT_OK, i1);
                    facechooser();
                    //setonfinish();
                    //finish();
                }
            } else if (resultCode != RESULT_OK) {
                backButtonHandler();
            }

        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

//                String result = data.toURI();
                // Image captured and saved to fileUri specified in the Intent

                Bundle params = new Bundle();
                params.putString("image_name", "camera");
                params.putString("full_text", "camera");
                mFirebaseAnalytics.logEvent("camera", params);

                String root = Environment.getExternalStorageDirectory().toString();

                int x =  getCameraPhotoOrientation(this,Uri.parse(root + "/application/vivo/vivofiles/junk/.pic1.jpeg"),root + "/application/vivo/vivofiles/junk/.pic1.jpeg");
               // Toast.makeText(this, "Image saved to:\n" +
                 //       Integer.toString(x), Toast.LENGTH_LONG).show();


                String photoPath =root + "/application/vivo/vivofiles/junk/" + ".pic1.jpeg";
                Bitmap setBitmap = BitmapFactory.decodeFile(photoPath,options);


                Matrix matrix = new Matrix();
                matrix.postRotate(x);
                Bitmap rotatedBitmap = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(),
                        setBitmap.getHeight(), matrix, true);
                SaveImage(rotatedBitmap);


                 facechooser();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }

        if (requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {

                if (data != null) {

                    Bundle params = new Bundle();
                    params.putString("image_name", "library");
                    params.putString("full_text", "library");
                    mFirebaseAnalytics.logEvent("library", params);

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                            // Show an expanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                        } else {

                            // No explanation needed, we can request the permission.

                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }


                    Uri selectedImageUri = data.getData();


                    String selectedImagePath = getPath(selectedImageUri);


                   final Matrix matrix25 = new Matrix();
                    if (selectedImagePath != null) {


                       final Bitmap first = BitmapFactory.decodeFile(selectedImagePath, options);
                        final RelativeLayout black = (RelativeLayout) findViewById(R.id.black);
                        black.setVisibility(View.VISIBLE);
                        new CountDownTimer(200, 100) { //40000 milli seconds is total time, 1000 milli seconds is time interval

                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {

                                if ( first == null) {  Toast toast = Toast.makeText(getApplicationContext(), "Invalid Photo", Toast.LENGTH_SHORT);
                                toast.show(); return;}


                                if (first.getHeight() > 1500 && first.getHeight() <= 2000) {
                                    matrix25.postScale(0.45f, 0.45f);

                                } else if (first.getHeight() > 2000 && first.getHeight() <= 3000)

                                    matrix25.postScale(0.35f, 0.35f);


                                else if (first.getHeight() > 3000 && first.getHeight() <= 3500)

                                    matrix25.postScale(0.3f, 0.3f);


                                else if (first.getHeight() > 3500 && first.getHeight() <= 4500)

                                    matrix25.postScale(0.25f, 0.25f);


                                else if (first.getHeight() > 4500 && first.getHeight() < 10000)

                                    matrix25.postScale(0.2f, 0.2f);

                                else matrix25.postScale(1f, 1f);


                                Bitmap photo = Bitmap.createBitmap(first, 0, 0, first.getWidth(), first.getHeight(), matrix25, true);


                                SaveImage2(photo);




                                new CountDownTimer(200, 100) { //40000 milli seconds is total time, 1000 milli seconds is time interval

                                    public void onTick(long millisUntilFinished) {
                                    }
                                    public void onFinish() {
                                        facechooser();
                                        black.setVisibility(View.INVISIBLE);
                                    }
                                }.start();


                            }
                        }.start();


                    } else {

                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Photo", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
             else {

                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Photo", Toast.LENGTH_SHORT);
                toast.show();

            }
        }

            } else if (resultCode != RESULT_OK) {
                backButtonHandler();
            }



    }
    private void SaveImage2(Bitmap finalBitmap) {

        Log.d("storage state:",  Environment.getExternalStorageState().toString());
        String root = Environment.getExternalStorageDirectory().toString();

        File myDir = new File(root + "/application/vivo/vivofiles/junk/");
        if(!myDir.exists()) {
            if (myDir.mkdirs()) {
                Log.i("dirrr", "Dir made");
            } else {
                Log.i("error", "error");
            }
        }

        else  Log.i("dirrr", "already exits");
        //myDir.mkdirs();
        //Random generator = new Random();
        //int n = 10000;
        //n = generator.nextInt(n);
        String fname = ".pic1.jpeg";
        File file = new File (myDir, fname);
  //check et yigit    //  if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
    public void setonfinish(){finish();}

    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }


    @Override
    public void onBackPressed() {


        final Button back = (Button) findViewById(R.id.button25);
        final TextView t11 = (TextView) findViewById(R.id.textView27);
        //Typeface tf = Typeface.createFromAsset(getAssets(), "irisupsbold.ttf");
        // t11.setTypeface(tf);
        t11.setTextSize(20);

        final Button b = (Button) findViewById(R.id.button);
        final Button b1 = (Button) findViewById(R.id.rotate);
        final Button bakbutton = (Button) findViewById(R.id.button20);

        if (STATE == MAINSTATE)
        tomain();

        else if (STATE == FACECHOOSER)
        {

            if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
            STATE = MAINSTATE;
            RelativeLayout cameralayout = (RelativeLayout) findViewById(R.id.cameralayout);

            FrameLayout facechooser = (FrameLayout) findViewById(R.id.facechooser);
            // tomain();

            RelativeLayout r = (RelativeLayout) findViewById(R.id.mainlayout);
            r.removeView(im);

            cameralayout.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            facechooser.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            bakbutton.setVisibility(View.INVISIBLE);


        }
    }

   void tomain(){



       SharedPreferences pref;
       pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
       SharedPreferences.Editor editor = pref.edit();
       editor.putInt("ongame", 1);
       editor.putBoolean("onresume", true);

       editor.commit();


       Intent i = new Intent(this, MainActivity.class);
      // Intent i1 = new Intent();
       //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
       //i1.putExtra("Key123", 10);
       //setResult(Activity.RESULT_OK, i1);*/
       startActivity(i);
     finish();

   }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/application/vivo/vivofiles/junk/");
        //myDir.mkdirs();
        //Random generator = new Random();
        //int n = 10000;
        //n = generator.nextInt(n);
        String fname = ".pic1.jpeg";
        File file = new File (myDir, fname);

       // if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            fileUri=Uri.fromFile(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int orgpictureheight = 0;
    int orgpicturewidth = 0;
    void facechooser()

    {
        final RelativeLayout black = (RelativeLayout) findViewById(R.id.black);
        black.setVisibility(View.INVISIBLE);
        Bundle params = new Bundle();
        params.putString("image_name", "facechooser");
        params.putString("full_text", "facechooser");
        mFirebaseAnalytics.logEvent("facechooser", params);

        STATE = FACECHOOSER;
        im = new MyImageView(this);
        RelativeLayout r2 = (RelativeLayout) findViewById(R.id.b);


        final Button b=(Button) findViewById(R.id.button);
        final Button b1=(Button) findViewById(R.id.rotate);
        final ImageView i = (ImageView) findViewById(R.id.imageView15);
        RelativeLayout cameralayout = (RelativeLayout) findViewById(R.id.cameralayout);

        final   Button backbutton2 = (Button) findViewById(R.id.button25);
        final   Button backbutton3 = (Button) findViewById(R.id.button20);

        backbutton3.setVisibility(View.VISIBLE);
        backbutton2.setVisibility(View.INVISIBLE);
        // tomain();
        cameralayout.setVisibility(View.VISIBLE);
        FrameLayout facechooser = (FrameLayout) findViewById(R.id.facechooser);
        b.setVisibility(View.VISIBLE);
        facechooser.setVisibility(View.VISIBLE);
        cameralayout.setVisibility(View.INVISIBLE);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("353720052948557")
                .build();
        mAdView.loadAd(adRequest);

        //DrawView v = (DrawView) findViewById(R.id.drawView1);
        TextView t= (TextView) findViewById(R.id.textView);

        //File dir = new File("DCIM/Camera/");
        String root = Environment.getExternalStorageDirectory().toString();

      Log.d("storage state:",  Environment.getExternalStorageDirectory().getPath().toString());


        String photoPath2 = root + "/application/vivo/vivofiles/junk/" + ".pic1.jpeg";

        //options.inJustDecodeBounds = true;
        //options.inSampleSize = 4;

        final Bitmap[] x111 = new Bitmap[10];
        final Bitmap[] setBitmap2 = new Bitmap[10];
        final Bitmap setBitmap = BitmapFactory.decodeFile(photoPath2,options);



        //Bitmap setBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.human);
        final float density1 = Resources.getSystem().getDisplayMetrics().density;
        int rx= getWindowManager().getDefaultDisplay().getWidth();
        float conversiony = FRAMEHEIGHT * density1;


       if (setBitmap == null) {  Toast toast = Toast.makeText(getApplicationContext(), "Invalid Photo", Toast.LENGTH_SHORT);
           toast.show(); return;}

        float btmwidth = setBitmap.getWidth();
        float   btmheight =setBitmap.getHeight();
        orgpictureheight = setBitmap.getHeight();
       orgpicturewidth = setBitmap.getWidth();
        Matrix matrix1 = new Matrix();
        if(setBitmap.getHeight()<setBitmap.getWidth())

        {

            matrix1.postScale(rx / btmwidth, rx / btmwidth);
        }


        else if (setBitmap.getHeight() == setBitmap.getWidth())
        {

            matrix1.postScale(rx / btmwidth, rx / btmwidth);

        }
        else {


            matrix1.postScale(conversiony / btmheight, conversiony / btmheight);
            //matrix1.postRotate(50);



        }
        Bitmap resizedBitmap = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);
        setBitmap2[0] = resizedBitmap;
        height=resizedBitmap.getHeight();
        width=resizedBitmap.getWidth();

        double h2 = (double)height/ (double) rx * (double)FRAMEHEIGHT*(double)density1;

        height22 = (int) h2;

        final TextView db5 = (TextView) findViewById(R.id.realwidth);
        final TextView db6 = (TextView) findViewById(R.id.realheight);



        db6.setText(Integer.toString(width));
        db5.setText(Integer.toString(height));




        i.setImageBitmap(resizedBitmap);
        SaveImage(resizedBitmap);
        //Drawable d = new BitmapDrawable(getResources(), resizedBitmap);
        // v.setBackgroundResource(R.mipmap.jelly);
        //v.setBackground(d);


        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        b1.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);

                        if(setBitmap2[0].getHeight() < setBitmap2[0].getWidth())
                        matrix.postScale((FRAMEHEIGHT*density1)/setBitmap2[0].getWidth(), (FRAMEHEIGHT*density1)/setBitmap2[0].getWidth());


                        final Bitmap setBitmap1 = Bitmap.createBitmap(setBitmap2[0], 0, 0,
                                setBitmap2[0].getWidth(), setBitmap2[0].getHeight(),
                                matrix, true);
                        setBitmap2[0] = setBitmap1;
                        SaveImage(setBitmap1);
                        height=setBitmap1.getHeight();
                        width=setBitmap1.getWidth();
                        db6.setText(Integer.toString(width));
                        db5.setText(Integer.toString(height));


                        i.setImageBitmap(setBitmap1);
                        x111[0]=setBitmap1;
                        SaveImage(setBitmap1);
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


      final  CollageViewRect coll = (CollageViewRect) findViewById(R.id.collageView12);

        Drawable drawable = getResources().getDrawable(R.mipmap.facechooser2);
        RelativeLayout r = (RelativeLayout) findViewById(R.id.mainlayout);

        im.setheight(450*density1);
        r.addView(im);
        final float density2 = Resources.getSystem().getDisplayMetrics().density;
        double screenwitdh= getWindowManager().getDefaultDisplay().getWidth();




        //int screenheright =(int) density2*400;
        //screenwitdh= screenwitdh/screenheright * 400;

        int screenheright =(int) (density2*FRAMEHEIGHT);
       // if (density1 == 3.5)  screenheright = 4*450;


        screenwitdh= screenwitdh/screenheright * FRAMEHEIGHT;


          //  drawable = new ScaleDrawable(drawable, 0, 100, screenheright).getDrawable();

        // drawable.setBounds(0, 0, 100, screenheright);


        Log.d("width screen:",Double.toString(screenwitdh));

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
// Scale it to 50 x 50

      //  Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,400, 500, true));


        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,(int)(90*density1),(int) (112.5*density1), true));
// Set your new, scaled drawable "d"
        x1=(int)(50*density1);
        y1=(int)(120*density1);
        x2=x1 + (int)screenwitdh;
        y2=y1 + FRAMEHEIGHT;
       // d.setBounds(50, 100, d.getIntrinsicWidth(), d.getIntrinsicHeight());


        if (backbutton)
        {
            SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);
            int width1 = shared1.getInt("width1", 0);
            int width2 = shared1.getInt("width2", 0);
            int height1 = shared1.getInt("height1", 0);
            int height2 = shared1.getInt("height2", 0);

            x1=width1;
            x2=width2;
            y1=height1;
            y2=height2;
            d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, x2-x1, y2-y1, true));

        }




        coll.setImageDrawable(d);


        final MultiTouchListener m3 = new MultiTouchListener();
        m3.maximumScale = 4f;
        m3.isRotateEnabled=false;
        m3.selectarea=4;
        m3.screenwidth= getWindowManager().getDefaultDisplay().getWidth();






        im.setimage(d);
        im.setbx(x1);
        im.setby(y1);





        final TextView t11 = (TextView) findViewById(R.id.textView99);


        coll.setOnTouchListener(m3);
        //final TextView t26 = (TextView) findViewById(R.id.textView26);



        new CountDownTimer(10000, 10) {
            public void onTick(long milsec) {

              //  t11.setText(Float.toString(coll.x));

            }

            public void onFinish() {



            }
        }.start();


if(null!= im) {
    im.setOnUpCallback1(new MyImageView.OnUpCallback() {

        @Override
        public void onRectFinished(final Rect rect) {

            t11.setText(Float.toString(rect.top));
            Log.d("rr",

                    "Rect is (" + rect.left + ", " + rect.top + ", "
                            + rect.right + ", " + rect.bottom + ")"
            );
            x1 = rect.left;
            x2 = rect.right + x1;
            y1 = rect.top + (int) (50 * density1);
            ;
            y2 = rect.bottom + y1;

            //t11.setText(Float.toString(coll.x));

            backbutton = false;
            TextView db1 = (TextView) findViewById(R.id.width);
            TextView db2 = (TextView) findViewById(R.id.width1);
            TextView db3 = (TextView) findViewById(R.id.height);
            TextView db4 = (TextView) findViewById(R.id.height1);
            db1.setText(Integer.toString(x1));
            db2.setText(Integer.toString(x2));
            db3.setText(Integer.toString(y1));
            db4.setText(Integer.toString(y2));

        }


    });

}





     /*  if (null != m3) {
            m3.setOnUpCallback1(new MultiTouchListener.OnUpCallback() {
                @Override
                public void onRectFinished(final Rect rect) {
                    Log.d("rr",

                        "Rect is (" + rect.left + ", " + rect.top + ", "
                                + rect.right + ", " + rect.bottom + ")"
                        );

                    // t26.setText(Integer.toString(rect.left));
                    x1 = rect.left;
                    x2 = rect.right + x1;
                    y1 = rect.top;
                    y2 = rect.bottom + y1;

                    //t11.setText(Float.toString(coll.x));

                    backbutton = false;
                    TextView db1 = (TextView) findViewById(R.id.width);
                    TextView db2 = (TextView) findViewById(R.id.width1);
                    TextView db3 = (TextView) findViewById(R.id.height);
                    TextView db4 = (TextView) findViewById(R.id.height1);
                    db1.setText(Integer.toString(x1));
                    db2.setText(Integer.toString(x2));
                    db3.setText(Integer.toString(y1));
                    db4.setText(Integer.toString(y2));

                }


            });

        }*/


        if(backbutton==true) {
            SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);
            int width1 = shared1.getInt("width1", 0);
            int width2 = shared1.getInt("width2", 0);
            int height1 = shared1.getInt("height1", 0);
            int height2 = shared1.getInt("height2", 0);

            x1=width1;
            x2=width2;
            y1=height1;
            y2=height2;


            TextView db1 = (TextView) findViewById(R.id.width);
            TextView db2 = (TextView) findViewById(R.id.width1);
            TextView db3 = (TextView) findViewById(R.id.height);
            TextView db4 = (TextView) findViewById(R.id.height1);
            db1.setText(Integer.toString(x1));
            db2.setText(Integer.toString(x2));
            db3.setText(Integer.toString(y1));
            db4.setText(Integer.toString(y2));

            ViewGroup.MarginLayoutParams lovemargin2 = new ViewGroup.MarginLayoutParams(r.getLayoutParams());
            lovemargin2.setMargins(width1, height1, 0, 0);
            RelativeLayout.LayoutParams vp = new RelativeLayout.LayoutParams(lovemargin2);


            vp.height = height2-height1;
            vp.width = width2-width1;
            coll.setLayoutParams(vp);

        }


        else {


        }
        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        b.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        SharedPreferences pref;
                        pref = getSharedPreferences("info", MODE_PRIVATE);

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("backbuttoneyes", false);
                        editor.commit();
                        showZaire();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b.setAlpha(1f);


                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });



    }


    public void updateEnglish()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);

        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("WARNING");
        textView87.setText("Selection Out Of Bounds. Select in the region please.");
        button36.setText("OK");


        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);

        rotate.setText("ROTATE");
        button20.setText("BACK");
        button.setText("NEXT");



        textView12.setText("Choose Your Face");
        textView26.setText("Selection Out Of Bounds. Please Select in the region.");
        textView27.setText("Choose Your Character Photo");
        textView41.setText("NEW CHARACTER");
        button2.setText("Camera");
        button3.setText("Gallery");
    }

    public void updateTurkish()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);

        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);

        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("UYARI");
        textView87.setText("Yaptığınız seçim sınırların dışındadır.Seçiminizi sınırlar içinde yapınız.");
        button36.setText("TAMAM");
        rotate.setText("ÇEVİR");
        button20.setText("GERİ");
        button.setText("İLERİ");


        textView12.setText("Yüzünüzü Seçiniz");
        textView26.setText("Yaptığınız seçim sınırların dışındadır.Seçiminizi sınırlar içinde yapınız.");
        textView27.setText("Karakterinize Resim Bulunuz");
        textView41.setText("YENİ KARAKTER");
        button2.setText("Kamera");
        button3.setText("Galeri");
    }


    public void updateFrench()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);


        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);
        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("ATTENTION");
        textView87.setText("Le choix que vous faites est hors des limites. S'il vous plaît choisir dans les limites.");
        button36.setText("D'ACCORD");
        rotate.setText("FAIRE TOURNER");
        button20.setText("ARRIÈRE");
        button.setText("PROCHAIN");


        textView12.setText("Choisissez Votre Visage");
        textView26.setText("Le choix que vous faites est hors des limites. S'il vous plaît choisir dans les limites.");
        textView27.setText("Choisissez Votre Photo de Caractère");
        textView41.setText("NOUVEAU CARACTÈRE");
        button2.setText("Caméra");
        button3.setText("Galerie");
    }

    public void updateSpanish()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);



        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);
        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("ADVERTENCIA");
        textView87.setText("La elección que haga está fuera de límites. Por favor, elija dentro de los límites.");
        button36.setText("BUENO");
        rotate.setText("GIRAR");
        button20.setText("ESPALDA");
        button.setText("SIGUIENTE");



        textView12.setText("Elija su cara");
        textView26.setText("La elección que haga está fuera de límites. Por favor, elija dentro de los límites.");
        textView27.setText("Elige tu Foto Carácter");
        textView41.setText("NUEVO CARÁCTER");
        button2.setText("Cámara");
        button3.setText("Galería");
    }


    public void updateRussian()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);

        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("ОПАСНОСТЬ");
        textView87.setText("Выбор вы сделаете это за пределы. Пожалуйста, выберите в пределах.");
        button36.setText("ИСПРАВНЫЙ");


        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);

        rotate.setText("ВРАЩАТЬ");
        button20.setText("НАЗАД");
        button.setText("СЛЕДУЮЩИЙ");



        textView12.setText("выбрать лицо персонажа");
        textView26.setText("Выбор вы сделаете это за пределы. Пожалуйста, выберите в пределах.");
        textView27.setText("Выберите символ фото");
        textView41.setText("НОВЫЙ ПЕРСОНАЖ");
        button2.setText("камера");
        button3.setText("Галерея");
    }

    public void updateChinese()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);

        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("警告");
        textView87.setText("你做的选择是超越界限。 请在限额内选择");
        button36.setText("好的");


        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);

        rotate.setText("旋转");
        button20.setText("背部");
        button.setText("下一个");



        textView12.setText("选择你的脸");
        textView26.setText("你做的选择是超越界限。 请在限额内选择");
        textView27.setText("选择你的角色照片");
        textView41.setText("新字符");
        button2.setText("相机");
        button3.setText("画廊");
    }
    public void updatejapanese()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);

        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("警告");
        textView87.setText("あなたが選ぶ選択肢は範囲外です。 制限内で選択してください");
        button36.setText("オーケー");


        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);

        rotate.setText("回転させます");
        button20.setText("バック");
        button.setText("次");



        textView12.setText("あなたの顔を選びます");
        textView26.setText("あなたが選ぶ選択肢は範囲外です。 制限内で選択してください");
        textView27.setText("あなたのキャラクターの写真を選択");
        textView41.setText("新しい文字");
        button2.setText("カメラ");
        button3.setText("ギャラリー");
    }
    public void updateporteugese()
    {
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView26 = (TextView) findViewById(R.id.textView26);
        TextView textView27 = (TextView) findViewById(R.id.textView27);
        TextView textView41 = (TextView) findViewById(R.id.textView41);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);

        TextView textView76 = (TextView) findViewById(R.id.textView76);
        TextView textView87 = (TextView) findViewById(R.id.textView87);
        Button button36 = (Button) findViewById(R.id.button36);

        textView76.setText("ATENÇÃO");
        textView87.setText("A escolha que você faz está fora dos limites. Por favor, escolha dentro dos limites.");
        button36.setText("BOM");


        Button rotate = (Button) findViewById(R.id.rotate);
        Button button20 = (Button) findViewById(R.id.button20);
        Button button = (Button) findViewById(R.id.button);

        rotate.setText("RODAR");
        button20.setText("COSTAS");
        button.setText("PRÓXIMO");



        textView12.setText("Escolha o seu rosto");
        textView26.setText("A escolha que você faz está fora dos limites. Por favor, escolha dentro dos limites.");
        textView27.setText("Escolha sua foto caráter");
        textView41.setText("NOVO CARÁTER");
        button2.setText("Câmera");
        button3.setText("Galeria");
    }






    public void showZaire() {



       final CollageViewRect c12 =(CollageViewRect) findViewById(R.id.collageView12);
       final Button rotate = (Button) findViewById(R.id.rotate);
      final  Button back = (Button) findViewById(R.id.button);


        final   Button backbutton2 = (Button) findViewById(R.id.button25);


        backbutton2.setVisibility(View.INVISIBLE);
        final Button b=(Button) findViewById(R.id.button);
        final Button b1=(Button) findViewById(R.id.rotate);
        Intent i = new Intent();

        float density1 = Resources.getSystem().getDisplayMetrics().density;





        if(!backbutton)
        {  y1=(int) (y1-50*density1);
        y2=(int) (y2-50*density1);
        }
        if(width>height) {

          //  y1=  y1- ((FRAMEHEIGHT * (int)density1)  - height) / 2;
          //  y2=  y2- ((FRAMEHEIGHT * (int)density1)  - height) / 2;

                    /* double calcx1 = (double) x1 /  500 *450;
               double calcx2 = (double) x2 /  500 *450;
               double calcy1 = (double) y1 /  500 *450;
               double calcy2 = (double) y2 /  500 *450;

               x1=( int) calcx1;
               x2=( int) calcx2;;
               y1=( int) calcy1;
               y2=( int) calcy2;;*/
        }

        TextView t1 = (TextView) findViewById(R.id.textView26);

       final RelativeLayout warningbox = (RelativeLayout) findViewById(R.id.warningbox);
        //Typeface tf = Typeface.createFromAsset(getAssets(), "browbold.ttf");
        //t1.setTypeface(tf);
        t1.setTextSize(25);

       // double  xx1 = x1/500*450;
       // double xx2=x2/500*450;
       // double yy1=y1/500*450;
       // double  yy2=y2/500*450;

        //x1 = (int) xx1;
        //x2 = (int) xx2;
        //y1 = (int) yy1;
        //y2 = (int) yy2;

        TextView t1width = (TextView) findViewById(R.id.textView93);
        TextView t1height = (TextView) findViewById(R.id.textView97);

        t1width.setText(Integer.toString(width));
        t1height.setText(Integer.toString(height));

        final Button b20 = (Button) findViewById(R.id.button20);
      final  Button buttonok = (Button)findViewById(R.id.button36);

        final Context con = this;
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

                        if (!mutestate) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        final Animation shake = AnimationUtils.loadAnimation(con, R.anim.righttoleft);
                        shake.setDuration(SLIDEANIMDUR);
                        warningbox.setAnimation(shake);
                        pluscoinlayout3();

                        c12.setEnabled(true);
                        rotate.setEnabled(true);
                        back.setEnabled(true);
                        b20.setEnabled(true);

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




      // int =  (int) (450*density1 - (70*density1-setb.getHeight())/2);


        int rx= getWindowManager().getDefaultDisplay().getWidth();


        Log.d("yiğityeni- width:", Integer.toString(width));
        Log.d("yiğityeni- height:", Integer.toString(height));
        Log.d("yiğityeni- y1:", Integer.toString(y1));
        Log.d("yiğityeni- y2:", Integer.toString(y2));
        Log.d("yiğityeni- FRAMEHEIGHT:", Integer.toString(FRAMEHEIGHT));

        if(height>=width) {
            if (y1<0 ||x1 < (rx - width) / 2 | y1 < ((FRAMEHEIGHT * density1) - height) / 2 | x1 - (rx - width) / 2 + x2-x1 > width | x1 + (x2 - x1) > rx - (rx - width) / 2 | y1 + (y2 - y1) > (FRAMEHEIGHT * density1) - ((FRAMEHEIGHT * density1) - height) / 2 | x2 - x1 > width | y2 - y1 > height)


            {

                warningbox.setVisibility(View.VISIBLE);
                final Animation shake = AnimationUtils.loadAnimation(con, R.anim.lefttoright);
                shake.setDuration(SLIDEANIMDUR);
                warningbox.setAnimation(shake);
                if (!mutestate) soundPool2.play(soundID2, volume, volume, 1, 0, 1f);

              /*  final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                if (!mutestate) {
                    clickaudio.start();
                    clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            clickaudio.release();

                        }

                        ;
                    });
                }*/
                c12.setEnabled(false);
                rotate.setEnabled(false);
                back.setEnabled(false);
                b20.setEnabled(false);
            }

            else {

                if(width>=height) {







                   //  y1=  y1- ((FRAMEHEIGHT * (int)density1)  - height22) / 2;
                  //   y2=  y2- ((FRAMEHEIGHT * (int)density1)  - height22) / 2;

                    /* double calcx1 = (double) x1 /  500 *450;
               double calcx2 = (double) x2 /  500 *450;
               double calcy1 = (double) y1 /  500 *450;
               double calcy2 = (double) y2 /  500 *450;

               x1=( int) calcx1;
               x2=( int) calcx2;;
               y1=( int) calcy1;
               y2=( int) calcy2;;*/
                }


                TextView db1 = (TextView) findViewById(R.id.width);
                TextView db2 = (TextView) findViewById(R.id.width1);
                TextView db3 = (TextView) findViewById(R.id.height);
                TextView db4 = (TextView) findViewById(R.id.height1);
                db1.setText(Integer.toString(x1));
                db2.setText(Integer.toString(x2));
                db3.setText(Integer.toString(y1));
                db4.setText(Integer.toString(y2));



                i.putExtra("Key", x1);
                i.putExtra("Key1", y1);
                i.putExtra("Key2", x2);
                i.putExtra("Key3", y2);


                SharedPreferences pref;
                pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("width1", x1);
                editor.putInt("width2", x2);
                editor.putInt("height1", y1);
                editor.putInt("height2", y2);
                editor.putInt("ongame", 0);

                editor.commit();

                eyes2();

                //  setResult(Activity.RESULT_OK, i);
                //finish();
            }
        }


       else{

              //  y1 = y1 - (int)(450*density1 - height)/2;
            float conversiony = 450 * density1;

            String root = Environment.getExternalStorageDirectory().toString();
            String photoPath = root + "/application/vivo/vivofiles/junk/" + ".pic1.jpeg";
            Bitmap trybitmap = BitmapFactory.decodeFile(photoPath,options);



            Matrix matrix1 = new Matrix();

            float rx2 = (int)(getWindowManager().getDefaultDisplay().getWidth());

                matrix1.postScale(rx2 / trybitmap.getWidth(), (rx2*density1) / trybitmap.getWidth());


//            Bitmap resizedBitmap2 = Bitmap.createBitmap(trybitmap, 0, 0, trybitmap.getWidth(), trybitmap.getHeight(), matrix1, true);

            double heightreal = trybitmap.getHeight() * (rx2 / trybitmap.getWidth());
            Log.d("yigit y1:", Integer.toString(y1));
            Log.d("yigit conversiony:", Float.toString(conversiony));
            Log.d("yigit resizedBgetHeigt:", Double.toString(heightreal));
            Log.d("yigit y2:", Double.toString(y2));
            Log.d("yigit height22:", Double.toString(heightreal));

            if (
                    x1<0 || y1 < ((FRAMEHEIGHT * density1) - height) / 2
                   ||(x2-x1) > rx
                    ||x2>rx
                    ||y2 > ((FRAMEHEIGHT * density1)  - height)/2 + height

                    || y2 - y1 > height22 || y1 - ((int)conversiony - heightreal) / 2 <0 || y1 - ((int)conversiony - heightreal) / 2 + y2 - y1 > heightreal
                    )
            //  if((x1 < (rx - width) / 2 | y1 < ((FRAMEHEIGHT * density1) - height) / 2 | x1 + (x2 - x1) > rx - (rx - width) / 2) | y1 + (y2 - y1) > (FRAMEHEIGHT * density1) - ((FRAMEHEIGHT * density1) - height) / 2 )
            {

                //eyes2();


                warningbox.setVisibility(View.VISIBLE);
                final Animation shake = AnimationUtils.loadAnimation(con, R.anim.lefttoright);
                shake.setDuration(SLIDEANIMDUR);
                warningbox.setAnimation(shake);

                if (!mutestate) soundPool2.play(soundID2, volume, volume, 1, 0, 1f);
              /*  final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                if(!mutestate) {
                    clickaudio.start();
                    clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            clickaudio.release();

                        }

                        ;
                    });
                }*/
                c12.setEnabled(false);
                rotate.setEnabled(false);
                back.setEnabled(false);
                b20.setEnabled(false);
            }
            // }
           else {


              /* double calcx1 = (double) x1 /  500 *450;
               double calcx2 = (double) x2 /  500 *450;
               double calcy1 = (double) y1 /  500 *450;
               double calcy2 = (double) y2 /  500 *450;

               x1=( int) calcx1;
               x2=( int) calcx2;;
               y1=( int) calcy1;
               y2=( int) calcy2;;*/

             // y1=  y1- ((FRAMEHEIGHT * (int)density1)  - height22) / 2;
               // y2=  y2- ((FRAMEHEIGHT * (int)density1)  - height22) / 2;



                i.putExtra("Key", x1);
                i.putExtra("Key1", y1);
                i.putExtra("Key2", x2);
                i.putExtra("Key3", y2);


                SharedPreferences pref;
                pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("width1", x1);
                editor.putInt("width2", x2);
                editor.putInt("height1", y1);
                editor.putInt("height2", y2);
                editor.putInt("ongame", 0);

                editor.commit();

                eyes2();

                //  setResult(Activity.RESULT_OK, i);
                //finish();
            }
        }



    }
// y1 - ((int)conversiony - resizedBitmap.getHeight()) / 2
    public void onDestroy()

    {

        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();


    }
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Camera Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.game.hy.vivo/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
        mGoogleApiClient.disconnect();
    }



    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath){
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

           // Log.i("RotateImage", "Exif orientation: " + orientation);
            //Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }


void eyes2()

{


    String root = Environment.getExternalStorageDirectory().toString();
    String photoPath = root + "/application/vivo/vivofiles/junk/" + ".pic1.jpeg";
    Bitmap setBitmap = BitmapFactory.decodeFile(photoPath,options);


    float density1 = Resources.getSystem().getDisplayMetrics().density;
    int rx = getWindowManager().getDefaultDisplay().getWidth();
    float conversiony = 450 * density1;
    float btmwidth = setBitmap.getWidth();
    float btmheight = setBitmap.getHeight();

    Drawable x;
    Matrix matrix1 = new Matrix();
    Bitmap croppedBitmap;
    Bitmap result;





    if (setBitmap.getHeight() <= setBitmap.getWidth())

    {

        matrix1.postScale(rx / btmwidth, rx / btmwidth);

        // matrix1.postRotate(50);

        Bitmap resizedBitmap = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);
        Log.d("yigit y1:", Integer.toString(y1));
        Log.d("yigit conversiony:", Float.toString(conversiony));
        Log.d("yigit resizedBgetHeigt:", Integer.toString(resizedBitmap.getWidth()));
        Log.d("yigit btmwidth:", Float.toString(btmwidth));
        Log.d("yigit btmheight:", Float.toString(btmheight));
        croppedBitmap = Bitmap.createBitmap(resizedBitmap, x1,  y1 - ((int)conversiony - resizedBitmap.getHeight()) / 2, x2 - x1, y2 - y1);
       // croppedBitmap = Bitmap.createBitmap(setBitmap, x1, y1, x2 - x1, y2 - y1);
        result = croppedBitmap;

                   /* Matrix matrixre = new Matrix();
                    double screenwitdh= getWindowManager().getDefaultDisplay().getWidth();
                    int screenheright =(int) density1*450;
                    screenwitdh= screenwitdh/screenheright * 450;
                    matrixre.postScale(450 / 500, (float) screenwitdh / btmwidth);
                    Bitmap result1 = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrixre, true);
                    */


        //SaveImage3(result);
        SaveImage3(result);
        Intent i = new Intent(this, Eyes2.class);
        startActivityForResult(i, 11);
    } else {


        matrix1.postScale(conversiony / btmheight, conversiony / btmheight);
        Bitmap resizedBitmap = Bitmap.createBitmap(setBitmap, 0, 0, setBitmap.getWidth(), setBitmap.getHeight(), matrix1, true);
        Log.d("yigit y1:", Integer.toString(y1));
        Log.d("yigit conversiony:", Float.toString(conversiony));
        Log.d("yigit resizedBgetHeigt:", Integer.toString(resizedBitmap.getWidth()));
        Log.d("yigit btmwidth:", Float.toString(btmwidth));
        Log.d("yigit btmheight:", Float.toString(btmheight));
        //Bitmap croppedBitmap = Bitmap.createBitmap(resizedBitmap, xcoordinate - (rx-resizedBitmap.getWidth())/2, ycoordinate, width,height);
        croppedBitmap = Bitmap.createBitmap(resizedBitmap, x1 - (rx - resizedBitmap.getWidth()) / 2, y1, x2 - x1, y2 - y1);

        result = croppedBitmap;
/*

                    Matrix matrixre = new Matrix();
                    double screenwitdh= getWindowManager().getDefaultDisplay().getWidth();
                    double screenheright =(int) density1*450;
                    screenwitdh= screenwitdh/screenheright * 450;
                    //matrixre.postScale(450 / 500, (float) screenwitdh / 300);
                    double widthratio = screenwitdh / 450;

                    float w = (float ) widthratio;
                    //double heightratio = ;
                    matrixre.postScale(w, 450f/500f);

                    Bitmap result1 = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrixre, true);
*/
        SaveImage3(result);
        Intent i = new Intent(this, Eyes2.class);
        startActivityForResult(i, 11);
    }
}
    private void SaveImage3(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root +"/application/vivo/vivofiles/junk/");
        String fname = ".pic2.jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
}