//�ڶ����

package com.example.nightpoemsp;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.xmlpull.v1.XmlPullParser;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Draw extends Activity  implements OnClickListener{
	
	
	 private static final int REQUEST_EXTERNAL_STORAGE = 1;
	 private static String[] PERMISSIONS_STORAGE = {
	     "android.permission.READ_EXTERNAL_STORAGE",
	     "android.permission.WRITE_EXTERNAL_STORAGE" };	
	
	
	private DrawingBoard board = null;     
	//private SurfaceHolder mSurfaceHolder = null;     
	private Button cleanButton = null;     
	private Button saveButton = null;          
	//private float oldX = 0f;     
	//private float oldY = 0f;          
//	private boolean canDraw = false;    
//	private Paint mPaint = null;    //������¼��ǰ����һ����ɫ     
//	private int whichColor = 0;          /** Called when the activity is first created. */    
	private int id = 0;
	
	private float mLastX, mLastY;
	
	//private float y1;
	
	boolean moved=false;
	int waitTime=1000;
	
	
	
	CountDownTimer timer = new CountDownTimer(waitTime, waitTime){//��x���룬ÿy����
        @Override
        public void onTick(long sin) {
        	
            //Toast.makeText(Draw.this, "" + sin/1000, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {
           //Toast.makeText(Draw.this, "����ʱ���", Toast.LENGTH_SHORT).show();
           save();
           clear();
           
           vibrate(Draw.this, 30);
           
          
           Log.d("night","vib");
           
        }
    };
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.drawing_board);
     
        
        //Log.d("night","create board");
        
        //board=(DrawingBoard) findViewById(R.id.new_board);
        
        
       // cleanButton = (Button) findViewById(R.id.btn_clear);
       // cleanButton.setOnClickListener(this);
        
      //  saveButton = (Button) findViewById(R.id.btn_save);
      //  saveButton.setOnClickListener(this);
         
        XmlPullParser parser = Draw.this.getResources().getXml(R.layout.drawing_board);
		AttributeSet attributes = Xml.asAttributeSet(parser);
    	
    	board= new DrawingBoard(getBaseContext(),attributes);
    	board.setId(id);
    	Log.d("night","id="+id);
    	((ViewGroup) findViewById(R.id.space)).addView( board  );
        
    	
    	
    	
    	
    	
    	
    	getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
    	
    	   	
    
    	//mBtn.setFocusable(true);
    	//mBtn.setFocusableInTouchMode(true);
    	//mBtn.requestFocus();

    	
    	/*
    	board.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {  

    	    @Override  

    	    public void onFocusChange(View v, boolean hasFocus) {  

    	        if(hasFocus) {

    	// �˴�Ϊ�õ�����ʱ�Ĵ�������

    	} else {

    	// �˴�Ϊʧȥ����ʱ�Ĵ�������
    		getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
    		
    		board.setFocusable(true);
    		board.setFocusableInTouchMode(true);
    		board.requestFocus();

    	}

    	    }

    	});
    	*/
    	

    	
    	//������Ļ���ȿ��Ʒ�ʽΪ�ֶ�
    	ContentResolver contentResolver = this.getContentResolver();

        try {

            int mode = Settings.System.getInt(contentResolver,

                    Settings.System.SCREEN_BRIGHTNESS_MODE);

            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {

                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,

                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

            }

        } catch (Settings.SettingNotFoundException e) {

            e.printStackTrace();
        }
     
    	
        Log.d("night","����Ϊ"+getScreenBrightness(this));
        
        
        //setWindowBrightness(30);
        
        setScreenBrightness(1);

        
        Log.d("night","���ȸ�Ϊ"+getScreenBrightness(this));
    	
    	
    	
    	
    	
		
	}
    
	@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        try {

            Object service = getSystemService("statusbar");
            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
            Method test = statusbarManager.getMethod("collapse");
            test.invoke(service);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	
	@Override
    public void onClick(View view) {
		/*    switch (view.getId()) {
            case R.id.btn_clear:
            	//Log.d("night","clear");
            	
            	clear();
            	
                break;
            case R.id.btn_save:
            	//Log.d("night","to save");
            	           	
            	save();
            	
               break;
        }
        */
    }
    
	
	
	private void save() {

		        Bitmap bitmap = board.getBitmap();
		        
		        Date date = new Date(System.currentTimeMillis());
		        
                
		        if (bitmap != null) {
		            try {
		                // ��ȡ����SD��·��
		                File dir = Environment.getExternalStorageDirectory();	
		                File folder = new File(dir, "night poems");
		                folder.mkdir();//�����ļ���

		                //Log.d("night","get dir:"+folder.toString());
		                // ͼƬ�ļ�·��
		                File file = new File(folder,getSecondTimeStamp(date)+ ".png");
		                //Log.d("night","make file");
		                FileOutputStream os = new FileOutputStream(file);
		                //Log.d("night","make os");
		                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		                //Log.d("night","compress pic");
		                os.flush();
		                os.close();
		               // Log.d("night","save");
		                //Toast.makeText(this, "�洢����"+folder.toString(), Toast.LENGTH_SHORT).show();
		            } catch (Exception e) {
		             
		            }
		        }
		    }
	
	private void clear() {
		
		((ViewGroup) findViewById(R.id.space)).removeView((DrawingBoard) findViewById(id));
    	
    	XmlPullParser parser = Draw.this.getResources().getXml(R.layout.drawing_board);
		AttributeSet attributes = Xml.asAttributeSet(parser);
    	
    	board= new DrawingBoard(getBaseContext(),attributes);
    	
    	id=id+1;
    	
    	board.setId(id);
    	
    	Log.d("night","id="+id);
    	
    	((ViewGroup) findViewById(R.id.space)).addView( board  );
		
    	Log.d("night","����"+getScreenBrightness(this));
	}
	
	
	public static String getSecondTimeStamp(Date date){
        if (null == date) {
            return "X";
        }
        String timeStamp = String.valueOf(date.getTime()/1000);
        return timeStamp;
    }
	
	
	   
	@Override
    public boolean onTouchEvent(MotionEvent event) {
		
		//y1 = board.getTop();
    	//Log.d("night","to top"+y1);
		
        float x = event.getX();
        //float y = event.getY()-192-50;
        float y = event.getY();//50 �����Ƕ���������

        
        
       
        
        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
            	//if(getWindow().getDecorView().getSystemUiVisibility()==View.SYSTEM_UI_FLAG_VISIBLE){
                    
              //	  getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
                
              //  } 
            	//ÿ�ο�ʼ���������Ϊture
                board.isDrawing = true ;
                //�����߳�
                new Thread(board).start();
                mLastX = x;
                mLastY = y;
                board.mPath.moveTo(mLastX, mLastY);
                timer.cancel();
                break;
            case MotionEvent.ACTION_MOVE:
            	moved=true;
            	timer.cancel();
            	//ƫ����
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if (dx >= 3 || dy >= 3) {
                    board.mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                }
                mLastX = x;
                mLastY = y;
                
                break;
            case MotionEvent.ACTION_UP:
                board.isDrawing = false;
                
              //����ʱCountDownTimer
              //ÿ��1000����ִ��һ��onTick
              //����ʱ���ִ��onFinish

              if(moved){
            	  moved=false;
            	  timer.start();
              }
              
            //  if(getWindow().getDecorView().getSystemUiVisibility()==View.SYSTEM_UI_FLAG_VISIBLE){
            //  
            //	  getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
              
             // } 
              //timer.start();
              
                
              
                
                
                
                break;
        }
        return true;
    }
	
	
	
	//��milliseconds����
	public static void vibrate(final Activity activity, long milliseconds) {
	        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
	        vib.vibrate(milliseconds);
	    }
	//��pattern[]��ʽ��
	public static void vibrate(final Activity activity, long[] pattern,int repeat){
	        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
	        vib.vibrate(pattern,repeat);
	    }
	//ȡ����
	public static void virateCancle(final Activity activity){
	        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
	        vib.cancel();
	    }
	
	
	
	private int getScreenBrightness(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        int defVal = 125;
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, defVal);
    }
    
	private void setWindowBrightness(int brightness) {
	    Window window = getWindow();
	    WindowManager.LayoutParams lp = window.getAttributes();
	    lp.screenBrightness = brightness / 255.0f;
	    window.setAttributes(lp);
	}
	
	public void setScreenBrightness(int paramInt){
	    try {
	        Settings.System.putInt(getApplicationContext().getContentResolver(),
	                Settings.System.SCREEN_BRIGHTNESS, paramInt);
	        Uri mUri = Settings.System.getUriFor("screen_brightness");
	        getApplicationContext().getContentResolver().notifyChange(mUri, null);
	    }catch (Exception e){
	        Log.e("Brightness", e.toString());
	    }
	}

	
	
        
}
	
	
	
	


