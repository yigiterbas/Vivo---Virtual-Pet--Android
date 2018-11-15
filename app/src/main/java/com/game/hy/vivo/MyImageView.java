package com.game.hy.vivo;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Yigit on 26.10.2016.
 */
public class MyImageView extends View {


    Boolean touched = false;
    private static final int INVALID_POINTER_ID = -1;
    private OnUpCallback mCallback1 = null;
    private OnUpCallback2 mCallback2 = null;
    Drawable mImage;
    float mPosX=0;
    float mPosY=0;
    float width = 192;
    float height = 192;
    float scrnwidth = 0;
    float scrnheight = 0;
    int boundaryx = 0;
    int bounddary = 0;







    boolean active = true;
    Rect rectc = new Rect(0,0,0,0);
    //width = 192;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId = INVALID_POINTER_ID;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public MyImageView(Context context) {
        this(context, null, 0);
        Drawable drawable = getResources().getDrawable(R.mipmap.circlechoose22);

        final float density2 = Resources.getSystem().getDisplayMetrics().density;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        // getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);


        getWindowVisibleDisplayFrame( rectc);
        double screenwitdh= rectc.right;

        scrnwidth = rectc.right;
        scrnheight = density2*450;


        //int screenheright =(int) density2*400;
        //screenwitdh= screenwitdh/screenheright * 400;

        int screenheright =(int) density2*450;
        screenwitdh= screenwitdh/screenheright * 450;


        //  drawable = new ScaleDrawable(drawable, 0, 100, screenheright).getDrawable();

        // drawable.setBounds(0, 0, 100, screenheright);




        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
// Scale it to 50 x 50

        //  Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,400, 500, true));


        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,(int) screenwitdh, 450, true));

        //  mImage = getResources().getDrawable(R.mipmap.ic_launcher);
        mImage = d;

        mImage.setBounds(10, 100, mImage.getIntrinsicWidth(), mImage.getIntrinsicHeight());
        // width = mImage.getIntrinsicWidth();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
       if(active ) {
           mScaleDetector.onTouchEvent(ev);

           final int action = ev.getAction();
           switch (action & MotionEvent.ACTION_MASK) {
               case MotionEvent.ACTION_DOWN: {

                   //this.bringToFront();
                   final float x = ev.getX();
                   final float y = ev.getY();

                   mLastTouchX = x;
                   mLastTouchY = y;
                   mActivePointerId = ev.getPointerId(0);
                   touched = true;
                   if (mCallback2 != null)  mCallback2.ontoouch(touched);
                   break;
               }

               case MotionEvent.ACTION_MOVE: {




                       final int pointerIndex = ev.findPointerIndex(mActivePointerId);
                       final float x = ev.getX(pointerIndex);
                       final float y = ev.getY(pointerIndex);

                       // Only move if the ScaleGestureDetector isn't processing a gesture.
                       if (!mScaleDetector.isInProgress()) {
                           final float dx = x - mLastTouchX;
                           final float dy = y - mLastTouchY;


                           mPosX += dx;
                           mPosY += dy;

                           if (mPosX <= 0+boundaryx) mPosX = 0+boundaryx;
                           if (mPosY <= 0) mPosY = 0;
                           if (mPosX  + mImage.getIntrinsicWidth() * mScaleFactor > scrnwidth)
                               mPosX = scrnwidth - mImage.getIntrinsicWidth() * mScaleFactor;
                           if (mPosY + mImage.getIntrinsicHeight() * mScaleFactor > scrnheight)
                               mPosY = scrnheight - mImage.getIntrinsicHeight() * mScaleFactor;

                           invalidate();
                       }

                       mLastTouchX = x;
                       mLastTouchY = y;

                   break;
               }

               case MotionEvent.ACTION_UP: {
                   mActivePointerId = INVALID_POINTER_ID;
                   touched = false;
                   if (mCallback2 != null) mCallback2.ontoouch(touched);
                   break;
               }

               case MotionEvent.ACTION_CANCEL: {
                   mActivePointerId = INVALID_POINTER_ID;
                   break;
               }

               case MotionEvent.ACTION_POINTER_UP: {
                   final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                           >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                   final int pointerId = ev.getPointerId(pointerIndex);
                   if (pointerId == mActivePointerId) {
                       // This was our active pointer going up. Choose a new
                       // active pointer and adjust accordingly.
                       final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                       mLastTouchX = ev.getX(newPointerIndex);
                       mLastTouchY = ev.getY(newPointerIndex);
                       mActivePointerId = ev.getPointerId(newPointerIndex);
                   }
                   break;
               }
           }
       }
        return true;

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        Log.d("DEBUG", "X: " + mPosX + " Y: " + mPosY);
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor);

        // width = mScaleFactor;
        mImage.draw(canvas);

        canvas.restore();
        if (mCallback1 != null) {


            // if (location[0]<0) view.setX(0f);

            //  mCallback1.onRectFinished(new Rect((int) angle, (int) (location[1]-add), (int) (view.getWidth() * scalelast), (int) (view.getHeight() * scalelast)));
            mCallback1.onRectFinished(new Rect((int) mPosX, (int) mPosY, (int) (mImage.getIntrinsicWidth() * mScaleFactor), (int) (mImage.getIntrinsicHeight() * mScaleFactor)));
            // mCallback1.onRectFinished(mImage.);
        }

        if (mCallback2 != null) mCallback2.ontoouch(touched);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {




            if( (mPosX + mImage.getIntrinsicWidth() * mScaleFactor < scrnwidth && mPosY + mImage.getIntrinsicHeight() * mScaleFactor < scrnheight  && mImage.getIntrinsicWidth() * mScaleFactor < scrnwidth-10 && mImage.getIntrinsicHeight() * mScaleFactor < scrnheight-10) ||detector.getScaleFactor() < 1 )
                mScaleFactor *= detector.getScaleFactor();


            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            invalidate();
            return true;
        }
    }


    public float corrdinatesx() {
        return mPosX;


    }

    public interface OnUpCallback2 {

        void ontoouch(boolean x);
    }
    public interface OnUpCallback {
        void onRectFinished(Rect rect);

    }

    public void setOnUpCallback1(OnUpCallback callback) {
        mCallback1 = callback;
    }

    public void setOnUpCallback2(OnUpCallback2 callback) {
        mCallback2 = callback;
    }
    public synchronized void setheight(float x)
    {
        scrnheight = x;
        invalidate();
    }
    public synchronized void setWidth(float x)
    {
        scrnwidth = x;
        invalidate();
    }

    public synchronized  void setimage(Drawable x)
    {
        mImage = x;
        //mImage.setBounds(50, 100, mImage.getIntrinsicWidth(), mImage.getIntrinsicHeight());

        invalidate();
    }
    public synchronized  void setbx(int x)
    {
        //mImage.setBounds(x, bounddary, mImage.getIntrinsicWidth(), mImage.getIntrinsicHeight());
        mPosX = x;
        invalidate();
    }

    public synchronized  void setby(int y)
    {
        mPosY = y;
        invalidate();
    }

    public synchronized void active (boolean x)
    {
        active = x;
        invalidate();

    }

    public synchronized void setboundaryx (int x)
    {

        boundaryx = x;
        invalidate();

    }

}