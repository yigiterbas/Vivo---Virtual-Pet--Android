package com.game.hy.vivo;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;


public class CircularProgressBar2 extends ProgressBar {
    private static final String TAG = "CircularProgressBar";
    public int storedprocess;

    //private static final int STROKE_WIDTH = 20;
    float density= Resources.getSystem().getDisplayMetrics().density;
    int STROKE_WIDTH = 40* (int) density;
    public long Speed ;
    private String mTitle = "";
    Resources res1 = getResources();
    //public int pColor = res1.getColor(R.color.circular_progress_default_progress);

    private String mSubTitle = "";
    //public final ObjectAnimator progressBarAnimator = ObjectAnimator.ofFloat( this,"animateProgress",0,4);
    public int mStrokeWidth = STROKE_WIDTH;
    public boolean ispressed = false;
    private final RectF mCircleBounds = new RectF();
    Resources res = getResources();
    public final Paint mProgressColorPaint = new Paint();
    public final Paint mBackgroundColorPaint = new Paint();
    public final Paint mTitlePaint = new Paint();
    public final Paint mSubtitlePaint = new Paint();
    //public int progresscolor;

    public int storedstart = 0;
    public int progress2 = 0;
    public int storedend = 100;
    private boolean mHasShadow = true;
    private int mShadowColor = Color.BLACK;
    private int color;

    //Button button_sbm = (Button) findViewById(R.id.button);
    public interface ProgressAnimationListener{
        public void onAnimationStart();
        public void onAnimationFinish();
        public void onAnimationProgress(int progress);
    }


    public void SetSpeed(long x){

        Speed = x;
    }

    public CircularProgressBar2(Context context) {
        super(context);
        init(null, 0);
    }

    public CircularProgressBar2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircularProgressBar2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void init(AttributeSet attrs, int style){
        //so that shadow shows up properly for lines and arcs
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.CircularProgressBar, style, 0);

        String color;
        Resources res = getResources();

        //int progcolor = this.pColor;

        this.mHasShadow = a.getBoolean(R.styleable.CircularProgressBar_cpb_hasShadow, true);

        color = a.getString(R.styleable.CircularProgressBar_cpb_progressColor);
        if(color==null)
            mProgressColorPaint.setColor(res.getColor(R.color.circular_progress_default_progress));
        else
            mProgressColorPaint.setColor(Color.parseColor(color));

        color = a.getString(R.styleable.CircularProgressBar_cpb_backgroundColor);
        if(color==null)
            mBackgroundColorPaint.setColor(res.getColor(R.color.circular_progress_default_background));
        else
            mBackgroundColorPaint.setColor(Color.parseColor(color));

        color = a.getString(R.styleable.CircularProgressBar_cpb_titleColor);
        if(color==null)
            mTitlePaint.setColor(res.getColor(R.color.circular_progress_default_title));
        else
            mTitlePaint.setColor(Color.parseColor(color));

        color = a.getString(R.styleable.CircularProgressBar_cpb_subtitleColor);
        if(color==null)
            mSubtitlePaint.setColor(res.getColor(R.color.circular_progress_default_subtitle));
        else
            mSubtitlePaint.setColor(Color.parseColor(color));


        String t = a.getString(R.styleable.CircularProgressBar_cpb_title);
        if(t!=null)
            mTitle = t;

        t = a.getString(R.styleable.CircularProgressBar_cpb_subtitle);
        if(t!=null)
            mSubTitle = t;

        // mStrokeWidth = a.getInt(R.styleable.CircularProgressBar_cpb_strokeWidth, STROKE_WIDTH);
        //    mStrokeWidth = STROKE_WIDTH;

        a.recycle();


        mProgressColorPaint.setAntiAlias(true);
        mProgressColorPaint.setStyle(Paint.Style.STROKE);
        mProgressColorPaint.setStrokeWidth(mStrokeWidth);

        mBackgroundColorPaint.setAntiAlias(true);
        mBackgroundColorPaint.setStyle(Paint.Style.STROKE);
        mBackgroundColorPaint.setStrokeWidth(mStrokeWidth);

        float density1=Resources.getSystem().getDisplayMetrics().density;

        int textsize = 12 * (int) density1;

        mTitlePaint.setTextSize(textsize);
        mTitlePaint.setStyle(Paint.Style.FILL);
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setTypeface(Typeface.create("Roboto-Thin", Typeface.NORMAL));
        mTitlePaint.setShadowLayer(0.1f, 0, 1, Color.GRAY);

        mSubtitlePaint.setTextSize(10);
        mSubtitlePaint.setStyle(Paint.Style.FILL);
        mSubtitlePaint.setAntiAlias(true);
        mSubtitlePaint.setTypeface(Typeface.create("Roboto-Thin", Typeface.BOLD));
        //		mSubtitlePaint.setShadowLayer(0.1f, 0, 1, Color.GRAY);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawArc(mCircleBounds, 0, 360 , false, mBackgroundColorPaint);

        int prog = getProgress();
        float scale = getMax() > 0 ? (float)prog/getMax() *360: 0;
        float density1=Resources.getSystem().getDisplayMetrics().density;
        if(mHasShadow)
            mProgressColorPaint.setShadowLayer(	3, 0, 1, mShadowColor);
        canvas.drawArc(mCircleBounds, 270, scale, false, mProgressColorPaint);


        if(!TextUtils.isEmpty(mTitle)){
            int xPos =  (int)(getMeasuredWidth()/2 - mTitlePaint.measureText(mTitle) / 2);
            int yPos = (int) (getMeasuredHeight()/2);

            float titleHeight = Math.abs(mTitlePaint.descent() + mTitlePaint.ascent());
            if(TextUtils.isEmpty(mSubTitle)){
                yPos += titleHeight/2;
            }
            canvas.drawText(mTitle, xPos, yPos, mTitlePaint);

            yPos += titleHeight;
            xPos = (int)(getMeasuredWidth()/2 - mSubtitlePaint.measureText(mSubTitle) / 2);

            canvas.drawText(mSubTitle, xPos, yPos, mSubtitlePaint);
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min+2*STROKE_WIDTH, min+2*STROKE_WIDTH);

        mCircleBounds.set(STROKE_WIDTH, STROKE_WIDTH, min+STROKE_WIDTH, min+STROKE_WIDTH);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);

        // the setProgress super will not change the details of the progress bar
        // anymore so we need to force an update to redraw the progress bar
        invalidate();
    }

    public void animateProgressTo(final int start, final int end, final ProgressAnimationListener listener){

        long Speed2= Speed;
        int finalstored = end;
        int startstored = start;
        storedstart = start;
        storedend = end;

        if(start!=0)
            setProgress(storedstart);

        final ObjectAnimator progressBarAnimator = ObjectAnimator.ofFloat(this, "animateProgress", start, storedend);
        progressBarAnimator.setDuration(Speed2);
        //progressBarAnimator.setRepeatCount(10);
        //		progressBarAnimator.setInterpolator(new AnticipateOvershootInterpolator(2f, 1.5f));
        progressBarAnimator.setInterpolator(new LinearInterpolator());

        progressBarAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                CircularProgressBar2.this.setProgress(end);
                if (listener != null)
                    listener.onAnimationFinish();
                progressBarAnimator.setDuration(Speed);
            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
                progressBarAnimator.setDuration(Speed);
            }

            @Override
            public void onAnimationStart(final Animator animation) {
                if (listener != null)
                    listener.onAnimationStart();
            }
        });

        progressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                int progress = ((Float) animation.getAnimatedValue()).intValue();
                storedprocess = progress;
                if (ispressed == true) { progressBarAnimator.cancel(); }
                if (progress != CircularProgressBar2.this.getProgress()) {
                    Log.d(TAG, progress + "");
                    CircularProgressBar2.this.setProgress(progress);
                    if (listener != null)
                        listener.onAnimationProgress(progress);
                }
            }
        });
        progressBarAnimator.start();
        //if (storedprocess == 84) {progressBarAnimator.pause();}


    }


	/*public synchronized boolean isAnimationRunning() {
		return progressBarAnimator != null && progressBarAnimator.isRunning();
	}

	public synchronized void stopAnimation() {
		if (isAnimationRunning()) {
			progressBarAnimator.pause();
			progressBarAnimator = null;
		}

	}*/


    public synchronized void setTitle(String title){
        this.mTitle = title;
        invalidate();
    }

    public synchronized void setProgressColor(int color) {


        mProgressColorPaint.setColor(color);

        invalidate();


    }




    public synchronized void setbackgroundcolor(int color) {


        mBackgroundColorPaint.setColor(color);

        invalidate();


    }





    public synchronized void setSubTitle(String subtitle){
        this.mSubTitle = subtitle;
        invalidate();
    }

    public synchronized void setstroke(int stroke){
        this.mStrokeWidth = stroke;
        invalidate();
    }


    public synchronized void setSubTitleColor(int color){
        mSubtitlePaint.setColor(color);
        invalidate();
    }

    public synchronized void settextsize(int size){
        mTitlePaint.setTextSize(size);
        invalidate();
    }

    public synchronized void setsubtextsize(int size){
        mSubtitlePaint.setTextSize(size);
        invalidate();
    }


    public synchronized void setTitleColor(int color){
        mTitlePaint.setColor(color);
        invalidate();
    }

    public synchronized void setHasShadow(boolean flag){
        this.mHasShadow = flag;
        invalidate();
    }

    public synchronized void setShadow(int color){
        this.mShadowColor = color;
        invalidate();
    }

    public String getTitle(){
        return mTitle;
    }

    public boolean getHasShadow(){
        return mHasShadow;
    }
}
