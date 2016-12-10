package com.aleagames.viewapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Date;

/**
 * Created by clemente on 07/07/16.
 */
public class CustomView extends View{
    private int mWidth;
    private int mHeight;
    private Context mContext;
    private boolean mValue = false;
    private int mMove2x;
    private int mMove2y;
    private int mCount;
    private boolean mIsRunning = false;
    private Rect mStartStop;
    private Rect mRect;
    private Path mTriangle;
    private Path mPathRect;
    private String mText;
    private boolean mLastState = false;
    private Rect mReset;

    public CustomView(Context aContext) {
        super(aContext);
        mContext = aContext;
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);

        Paint vPaintBlue = new Paint();
        vPaintBlue.setColor(Color.BLUE);
        Paint vPaintYellow = new Paint();
        vPaintYellow.setColor(Color.YELLOW);
        mPathRect = getScreenPathRect();
        mTriangle = getTrianglePath();

        if (mValue) {
            canvas.drawPath(mPathRect, vPaintBlue);
        } else {
            canvas.drawPath(mTriangle, vPaintYellow);
        }

        mStartStop = new Rect(0,0, 200, 200);
        canvas.drawRect(mStartStop, vPaintYellow);
        mRect = new Rect(250, 0, 450, 200);
        Paint vBlack = new Paint();
        vBlack.setColor(Color.BLACK);
        canvas.drawRect(mRect, vBlack);
        Log.d("mValue", "" + mValue);

        Paint vTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        vTextPaint.setTextSize(50);
        mText = new Date().toString();
        canvas.drawText(mText, 500, 100, vTextPaint);

        mReset = new Rect(600, 0, 800, 300);
        canvas.drawRect(mReset, vPaintYellow);

        setSaveEnabled(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int vSpazioToolBar = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int vWid = resolveSizeAndState(vSpazioToolBar, widthMeasureSpec, 1);
        int vHei = resolveSizeAndState(MeasureSpec.getSize(vWid), heightMeasureSpec, 0);
        mWidth = vWid;
        mHeight = vHei;
        Log.d("Screen wid e hei", "" + mWidth + " "  + mHeight);
        setMeasuredDimension(mWidth, mHeight);
    }

    public Path getScreenPathRect() {
        Point v1 = new Point((mWidth / 4) + mMove2x,(mHeight / 4) + mMove2y);
        Point v2 = new Point(v1.x,v1.y + mHeight / 2);
        Point v3 = new Point(v2.x + mWidth / 2,v2.y);
        Point v4 = new Point(v3.x,v3.y - mHeight / 2);
        Path vSreenPath = new Path();
        vSreenPath.moveTo(v1.x,v1.y);
        vSreenPath.lineTo(v2.x,v2.y);
        vSreenPath.lineTo(v3.x,v3.y);
        vSreenPath.lineTo(v4.x,v4.y);
        return vSreenPath;
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        if (screenState == SCREEN_STATE_OFF) {
            mLastState = mIsRunning;
            stop();
        } else {
            restart();
        }
    }

    private void restart() {
        if (mLastState) {
            mIsRunning = true;
            start2Move();
        }
    }

    private void stop() {
        mIsRunning = false;
    }

    public Path getTrianglePath(){
        Point vO = new Point((mWidth / 2) + mMove2x, (mHeight / 2) + mMove2y);
        float vL;
        float vH;
        if (mWidth < mHeight){
            vL = mWidth / 3;
            vH = (float) (mWidth / (2 * Math.sqrt(3)));
        } else {
            vL = mHeight / 3;
            vH = (float) (mHeight / (2 * Math.sqrt(3)));
        }
        float vRadius = (float) ((Math.sqrt(3) / 3) * (vL));
        float vT = vH - vRadius;
        Point vA = new Point(vO.x, (int) (vO.y - vRadius));
        Point vB = new Point((int) (vO.x + (vL / 2)),(int) (vO.y + vT));
        Point vC = new Point((int) (vO.x - (vL / 2)), vB.y);
        Path vPath = new Path();
        vPath.moveTo(vA.x,vA.y);
        vPath.lineTo(vB.x, vB.y);
        vPath.lineTo(vC.x,vC.y);
        return vPath;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean result = super.onTouchEvent(event);
       switch (event.getAction()) {
           case MotionEvent.ACTION_DOWN:
               if(mStartStop.contains((int)event.getX(),(int) event.getY())){
                   mIsRunning = !mIsRunning;
                   start2Move();
               } else if (mRect.contains((int)event.getX(),(int) event.getY())){
                   mText = new Date().toString();
               } else {
                   mValue = !mValue;
               }
               if (mReset.contains((int)event.getX(),(int) event.getY())){
                   reset();
               }
           break;
       }
        postInvalidate();
        return result;
    }

    private void start2Move() {
        Thread vTh = new Thread(myRun());
        vTh.start();
    }


    private Runnable myRun(){
       Runnable vRun = new Runnable() {
            @Override
            public void run() {
                while (mIsRunning) {
                    mCount++;
                    Log.d("Runnable", "Runnable started");
                    if (mCount == 40) {
                        mCount = 0;
                    } else if (mCount <= 5 || mCount > 35) {
                        mMove2x += 30;
                    } else if (mCount <= 15) {
                        mMove2y += 50;
                    } else if (mCount <= 25) {
                        mMove2x -= 50;
                    } else if (mCount <= 35) {
                        mMove2y -= 50;
                    }
                    mText = new Date().toString();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        };
       return vRun;
   }

    private void reset(){
        mCount = 0;
        mValue = false;
        mMove2y = 0;
        mMove2x = 0;
        mIsRunning = false;
        mLastState = false;
        restart();
    }
}
