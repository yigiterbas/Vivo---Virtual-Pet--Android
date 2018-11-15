package com.game.hy.vivo;

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


public class CollageView extends ImageView {

    private static final int PADDING = 1;
    private static final float STROKE_WIDTH = 4.0f;
    int x;
    int y;
    int x1;
    int y1;
    private OnUpCallback mCallback2 = null;
    Rect r;
    Canvas s;
    Paint mRectPaint;
    private Paint mBorderPaint;
    public int col= Color.GREEN;


    public CollageView(Context context) {
        this(context, null);
    }

    public CollageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public interface OnUpCallback {
        void onRectFinished(Rect rect);
    }

    public void setOnUpCallback2(OnUpCallback callback) {
        mCallback2 = callback;
    }


    public CollageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initBorderPaint();
    }

    private void initBorderPaint() {
        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(Color.WHITE);
        mBorderPaint.setStrokeWidth(STROKE_WIDTH);


    }

    @Override
    protected void onDraw(Canvas canvas) {



        //canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING, mBorderPaint);

        mRectPaint = new Paint();
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4);
        mRectPaint.setPathEffect(new DashPathEffect(new float[]{5, 10, 15, 20}, 0));
        mRectPaint.setColor(col);

        x = PADDING;
        y = PADDING;
        x1 = getWidth() + PADDING;
        y1 = getHeight() + PADDING;




        canvas.drawOval(new RectF(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING), mRectPaint);
        super.onDraw(canvas);
    }


public synchronized void changecolor(int x)
{

    this.col=x;
    invalidate();

}



}