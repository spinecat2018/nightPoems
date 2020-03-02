package com.example.nightpoemsp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingBoard extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    // SurfaceHolder
    private SurfaceHolder mSurfaceHolder;
    // 画布
    private Canvas mCanvas;
    // 子线程标志位
    private boolean isDrawing;
    // 画笔
    Paint mPaint;
    // 路径
    Path mPath;
    //上次的坐标
    private float mLastX, mLastY;

    
    public DrawingBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    
    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        
        //画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor( Color.parseColor("#FF4081") );
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        
        //路径
        mPath = new Path();
    }

    
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {//创建
        //isDrawing = true;
        //new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {//改变

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {//销毁
        isDrawing = false;
    }

    
    
    @Override
    public void run() {
        while (isDrawing){
            drawing();
        }
    }

    private void drawing() {
        try {
           mCanvas = mSurfaceHolder.lockCanvas();
           
           //mCanvas.drawColor(Color.WHITE);
           mCanvas.drawPath(mPath,mPaint);
           
        }finally {
           if (mCanvas != null){
               mSurfaceHolder.unlockCanvasAndPost(mCanvas);
           }
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        
        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
            	//每次开始将标记设置为ture
                isDrawing = true ;
                //开启线程
                new Thread(this).start();
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
            	//偏移量
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if (dx >= 3 || dy >= 3) {
                    mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                break;
        }
        return true;
    }
    
}