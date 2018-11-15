package com.game.hy.vivo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;




import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
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


public class CollageViewRect extends ImageView {
    public int col= Color.GREEN;
    private static final int PADDING = 1;
    private static final float STROKE_WIDTH = 4.0f;
    int x;
    int y;
    int x1;
    int y1;
    private OnUpCallback mCallback2 = null;
    Rect r;
    Canvas s;

    private Paint mBorderPaint;

    public CollageViewRect(Context context) {
        this(context, null);
    }

    public CollageViewRect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public interface OnUpCallback {
        void onRectFinished(Rect rect);
    }

    public void setOnUpCallback2(OnUpCallback callback) {
        mCallback2 = callback;
    }


    public CollageViewRect(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initBorderPaint();
    }

    private void initBorderPaint() {
        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(Color.GREEN);
        mBorderPaint.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
        //canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING, mBorderPaint);
        Paint mRectPaint;
         PathEffect effects;
       // effects = new DashPathEffect(new float[] { 4, 2, 4, 2 }, 0);

        mRectPaint = new Paint();
        mRectPaint.setColor(col);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4);
        mRectPaint.setPathEffect(new DashPathEffect(new float[]{5, 10, 15, 20}, 0));

        //mRectPaint.setPathEffect(new DashPathEffect(new float[]{5, 10, 15, 20}, 0));

        x = PADDING;
        y = PADDING;
        x1 = getWidth() + PADDING;
        y1 = getHeight() + PADDING;
canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING, mRectPaint);
    //    canvas.drawOval(new RectF(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING), mRectPaint);

    }






}