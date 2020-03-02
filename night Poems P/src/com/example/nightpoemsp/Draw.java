package com.example.nightpoemsp;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.Button;

public class Draw extends Activity {
	
//	private SurfaceView mSurfaceView = null;     
	//private SurfaceHolder mSurfaceHolder = null;     
	//private Button cleanButton = null;     
	//private Button colorButton = null;          
	//private float oldX = 0f;     
	//private float oldY = 0f;          
//	private boolean canDraw = false;    
//	private Paint mPaint = null;    //用来记录当前是哪一种颜色     
//	private int whichColor = 0;          /** Called when the activity is first created. */    
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.drawing_board);
        
        Log.d("night","create board");
        
        //mSurfaceView = (SurfaceView)this.findViewById(R.id.board);    
		//mSurfaceHolder = mSurfaceView.getHolder();         
		//mPaint = new Paint();     
		//画笔的颜色     
		//mPaint.setColor(Color.RED);     
		//画笔的粗细     
		//mPaint.setStrokeWidth(10.0f);  
		
		//mPaint.setAntiAlias(true);
		
		//mPaint.setDither(true);
		
	}
        

        
        
        
        
}
	
	
	
	


