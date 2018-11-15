package com.game.hy.vivo;

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

public class DragEllipseView extends View {
    private Paint mRectPaint;

    int mStartX = 0;
    int mStartY = 0;
    int mEndX = 0;
    int mEndY = 0;
    private boolean mDrawRect = false;
    private TextPaint mTextPaint = null;

    private OnUpCallback mCallback1 = null;

    public interface OnUpCallback {
        void onRectFinished(Rect rect);
    }

    public DragEllipseView(final Context context) {
        super(context);
        init();
    }

    public DragEllipseView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragEllipseView(final Context context, final AttributeSet attrs,
                           final int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Sets callback for up
     *
     * @param callback
     *            {@link OnUpCallback}
     */
    public void setOnUpCallback1(OnUpCallback callback) {
        mCallback1 = callback;
    }

    /**
     * Inits internal data
     */
    private void init() {
        mRectPaint = new Paint();
        mRectPaint.setColor(Color.GREEN);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(5);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.MAGENTA);
        mTextPaint.setTextSize(20);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDrawRect = false;
                mStartX = (int) event.getX();
                mStartY = (int) event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                final int x = (int) event.getX();
                final int y = (int) event.getY();
                if (!mDrawRect || Math.abs(x - mEndX) > 5
                        || Math.abs(y - mEndY) > 5) {
                    mEndX = x;
                    mEndY = y;
                    invalidate();
                }
                mDrawRect = true;
                break;

            case MotionEvent.ACTION_UP:
                if (mCallback1 != null) {
                    mCallback1.onRectFinished(new Rect(Math.min(mStartX, mEndX),
                            Math.min(mStartY, mEndY), Math.max(mEndX, mStartX),
                            Math.max(mEndY, mStartY)));
                }
                invalidate();
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawRect) {
            canvas.drawOval(new RectF(Math.min(mStartX, mEndX), Math.min(mStartY, mEndY),
                            Math.max(mEndX, mStartX), Math.max(mEndY, mStartY)),
                    mRectPaint);
            canvas.drawText(
                    "  (" + Math.abs(mStartX - mEndX) + ", "
                            + Math.abs(mStartY - mEndY) + ")",
                    Math.max(mEndX, mStartX), Math.max(mEndY, mStartY),
                    mTextPaint);
        }
    }
}