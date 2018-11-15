package com.game.hy.vivo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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





public class CollageViewHalfEllipsearc extends ImageView {
    public int col= Color.GREEN;
    private static final int PADDING = 1;
    private static final float STROKE_WIDTH = 4.0f;

    private Paint mBorderPaint;

    public CollageViewHalfEllipsearc(Context context) {
        this(context, null);
    }

    public CollageViewHalfEllipsearc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public CollageViewHalfEllipsearc(Context context, AttributeSet attrs, int defStyle) {
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
        super.onDraw(canvas);
        //canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING, mBorderPaint);
        Paint mRectPaint;
        mRectPaint = new Paint();
        mRectPaint.setColor(col);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(5);
        mRectPaint.setPathEffect(new DashPathEffect(new float[]{5, 10, 15, 20}, 0));
        //canvas.drawOval(new RectF(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING), mRectPaint);
        canvas.drawArc(new RectF( PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING),0,180,false,mRectPaint);
    }
}
