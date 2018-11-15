package com.game.hy.vivo;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;


import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MultiTouchListener implements View.OnTouchListener {
    TransformInfo info = new TransformInfo();
    private static final int INVALID_POINTER_ID = -1;
    public boolean isRotateEnabled = true;
    public boolean isTranslateEnabled = true;
    public boolean isScaleEnabled = true;
    public float minimumScale = 0.1f;
    public float maximumScale = 10.0f;
    private int mActivePointerId = INVALID_POINTER_ID;
    private float mPrevX;
    private float mPrevY;
    float xpos;
    float ypos;
    float xpos1;
    float ypos1;
    float scalelast=1;
    float x1,x2;
    float inx;
    float distance;
    float lastx;
    float lx;
    int selectarea;
    int janepicture=1;
    int eyearea1=2;
    int eyearea2=3;
    int selectpicture = 4;
    int screenwidth;



float height;
    float width;

    private ScaleGestureDetector mScaleGestureDetector;
    private OnUpCallback mCallback1 = null;
    public MultiTouchListener() {
        mScaleGestureDetector = new ScaleGestureDetector(new ScaleGestureListener());
    }

    public interface OnUpCallback {
        void onRectFinished(Rect rect);
    }


    private static float adjustAngle(float degrees) {
        if (degrees > 180.0f) {
            degrees -= 360.0f;
        } else if (degrees < -180.0f) {
            degrees += 360.0f;
        }

        return degrees;
    }

    public void setOnUpCallback1(OnUpCallback callback) {
        mCallback1 = callback;
    }
    private void move(View view, TransformInfo info) {

        View v = view;
        computeRenderOffset(view, info.pivotX, info.pivotY);
        adjustTranslation(view, info.deltaX, info.deltaY);

        // Assume that scaling still maintains aspect ratio.
        float scale = view.getScaleX() * info.deltaScale;
        scale = Math.max(info.minimumScale, Math.min(info.maximumScale, scale));
        scalelast = scale;
        float intposition = view.getX();


        int[] location={0,0};
        view.getLocationInWindow(location);


        View x = view;

        x.setScaleX(scale);

        //if(x.getX() > 0)
        view.setScaleX(scale);


        view.setScaleY(scale);
        //if (location[0] < 0) view.setX(0);


        adjustTranslation(view, v.getX() - view.getX(), v.getY() - view.getY());
       // xpos= info.deltaX;
       // ypos=info.deltaY;

        float rotation = adjustAngle(view.getRotation() + info.deltaAngle);
        view.setRotation(rotation);
    }

    private  void adjustTranslation(View view, float deltaX, float deltaY) {
        float[] deltaVector = {deltaX, deltaY};
        view.getMatrix().mapVectors(deltaVector);

        //xpos= view.getTranslationX() + deltaVector[0];

       // if (view.getTranslationX() + deltaVector[0] > 0)
        view.setTranslationX(view.getTranslationX() + deltaVector[0]);


        int[] location={0,0};
        view.getLocationInWindow(location);
        //if (location[0] < 0) view.setX(0);
        view.setTranslationY(view.getTranslationY() + deltaVector[1]);
    }

    private static void computeRenderOffset(View view, float pivotX, float pivotY) {
        if (view.getPivotX() == pivotX && view.getPivotY() == pivotY) {
            return;
        }

        float[] prevPoint = {0.0f, 0.0f};
        view.getMatrix().mapPoints(prevPoint);

        view.setPivotX(pivotX);
        view.setPivotY(pivotY);

        float[] currPoint = {0.0f, 0.0f};
        view.getMatrix().mapPoints(currPoint);

        float offsetX = currPoint[0] - prevPoint[0];
        float offsetY = currPoint[1] - prevPoint[1];

        view.setTranslationX(view.getTranslationX() - offsetX);
        view.setTranslationY(view.getTranslationY() - offsetY);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        inx= view.getX();

        mScaleGestureDetector.onTouchEvent(view, event);
        lx=view.getX();


        if (!isTranslateEnabled) {
            return true;
        }

        int action = event.getAction();
        switch (action & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                mPrevX = event.getX();
                mPrevY = event.getY();


                // Save the ID of this pointer.
                mActivePointerId = event.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position.
                width=view.getWidth();
                height=view.getHeight();

                int pointerIndex = event.findPointerIndex(mActivePointerId);
                if (pointerIndex != -1) {
                    float currX = event.getX(pointerIndex);
                    float currY = event.getY(pointerIndex);

                    // Only move if the ScaleGestureDetector isn't processing a
                    // gesture.


                    if (!mScaleGestureDetector.isInProgress()) {
                        int[] location={0,0};
                        final float density1 = Resources.getSystem().getDisplayMetrics().density;


                        adjustTranslation(view, currX - mPrevX, currY - mPrevY);
                        view.getLocationInWindow(location);

                        int scalelast1=1;



                        if(selectarea == selectpicture) {
                            if (location[0] < 0) view.setX(0);
                            if (location[0] + view.getWidth()*scalelast1 > screenwidth)
                                view.setX(screenwidth - (view.getWidth()*scalelast1));
                            if (location[1] < (50*density1)) view.setY(0);
                            if (location[1]+ view.getHeight()*scalelast1 > (500*density1)) view.setY((450*density1)-view.getHeight()*scalelast1);
                        }



                        if(selectarea == janepicture) {
                            if (location[0] < 0) view.setX(0);
                            if (location[0] + view.getWidth()*scalelast1 > screenwidth)
                                view.setX(screenwidth - (view.getWidth()*scalelast1));
                            if (location[1] < (134*density1)) view.setY(0);
                            if (location[1]+ view.getHeight()*scalelast1 > (584*density1)) view.setY((450*density1)-view.getHeight()*scalelast1);
                        }

                        if(selectarea == eyearea1) {
                            if (location[0] < 0) view.setX(0);
                            if (location[0] + view.getWidth()*scalelast > 156*density1)
                                view.setX((156*density1) - (view.getWidth()*scalelast));
                            if (location[1]-(50*density1) < 0) view.setY(0);
                            if (location[1]-(50*density1)+ view.getHeight()*scalelast > (84*density1)) view.setY((84*density1)-view.getHeight()*scalelast);
                        }
                        if(selectarea == eyearea2) {
                            if (location[0] < screenwidth-(156*density1)) view.setX(0);
                            if (location[0] + view.getWidth()*scalelast > screenwidth)
                                view.setX((156*density1) - (view.getWidth()*scalelast));
                            if (location[1]-(50*density1) < 0) view.setY(0);
                            if (location[1]-(50*density1)+ view.getHeight()*scalelast > (84*density1)) view.setY((84*density1)-view.getHeight()*scalelast);
                        }
                    }
                }



                break;
            }

            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER_ID;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:


                break;

            case MotionEvent.ACTION_UP:
                mActivePointerId = INVALID_POINTER_ID;


                break;

            case MotionEvent.ACTION_POINTER_UP: {
                // Extract the index of the pointer that left the touch sensor.
                int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointerId = event.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mPrevX = event.getX(newPointerIndex);
                    mPrevY = event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);

                }



                break;
            }
        }

        float density1 = Resources.getSystem().getDisplayMetrics().density;
        //final float add = density1*25;
        final float add = 0;
        int[] location={0,0};
        if (mCallback1 != null) {
            if (scalelast <= 0) scalelast = 1;

            view.getLocationInWindow(location);
            float angle =view.getRotation();

           // if (location[0]<0) view.setX(0f);

           //  mCallback1.onRectFinished(new Rect((int) angle, (int) (location[1]-add), (int) (view.getWidth() * scalelast), (int) (view.getHeight() * scalelast)));
            mCallback1.onRectFinished(new Rect((int) location[0], (int) (location[1]-add), (int) (view.getWidth() * scalelast), (int) (view.getHeight() * scalelast)));

        }

        return true;
    }

    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        View v;
        private float mPivotX;
        private float mPivotY;
        private Vector2D mPrevSpanVector = new Vector2D();

        @Override
        public boolean onScaleBegin(View view, ScaleGestureDetector detector) {
            mPivotX = detector.getFocusX();
            mPivotY = detector.getFocusY();
            mPrevSpanVector.set(detector.getCurrentSpanVector());
            return true;
        }

        @Override
        public boolean onScale(View view, ScaleGestureDetector detector) {



            info.deltaScale = isScaleEnabled ? detector.getScaleFactor() : 1.0f;
            info.deltaAngle = isRotateEnabled ? Vector2D.getAngle(mPrevSpanVector, detector.getCurrentSpanVector()) : 0.0f;
            info.deltaX = isTranslateEnabled ? detector.getFocusX() - mPivotX : 0.0f;
            info.deltaY = isTranslateEnabled ? detector.getFocusY() - mPivotY : 0.0f;



            info.pivotX = mPivotX;
            info.pivotY = mPivotY;
            info.minimumScale = minimumScale;
            info.maximumScale = maximumScale;


            move(view, info);
            v=view;
            return false;

        }
    }

    private class TransformInfo {

        public float deltaX;
        public float deltaY;
        public float deltaScale;
        public float deltaAngle;
        public float pivotX;
        public float pivotY;
        public float minimumScale;
        public float maximumScale;
    }







}