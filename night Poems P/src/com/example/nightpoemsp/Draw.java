//第二个活动

package com.example.nightpoemsp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
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
//	private Paint mPaint = null;    //用来记录当前是哪一种颜色     
//	private int whichColor = 0;          /** Called when the activity is first created. */    
	private int id = 0;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.drawing_board);
        
        Log.d("night","create board");
        
        //board=(DrawingBoard) findViewById(R.id.new_board);
        
        
        cleanButton = (Button) findViewById(R.id.btn_clear);
        cleanButton.setOnClickListener(this);
        
        saveButton = (Button) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(this);
         
        XmlPullParser parser = Draw.this.getResources().getXml(R.layout.drawing_board);
		AttributeSet attributes = Xml.asAttributeSet(parser);
    	
    	board= new DrawingBoard(getBaseContext(),attributes);
    	board.setId(id);
    	Log.d("night","id++="+id);
    	((ViewGroup) findViewById(R.id.space)).addView( board  );
        
        
		
	}
        
	@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
            	Log.d("night","clear");
            	((ViewGroup) findViewById(R.id.space)).removeView((DrawingBoard) findViewById(id));
            	
            	XmlPullParser parser = Draw.this.getResources().getXml(R.layout.drawing_board);
				AttributeSet attributes = Xml.asAttributeSet(parser);
            	
            	board= new DrawingBoard(getBaseContext(),attributes);
            	
            	id=id+1;
            	
            	board.setId(id);
            	
            	Log.d("night","id++="+id);
            	
            	((ViewGroup) findViewById(R.id.space)).addView( board  );
            	
                break;
            case R.id.btn_save:
            	Log.d("night","to save");
            	
            	
            	
            	
            	//ActivityCompat.requestPermissions(Draw.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_EXTERNAL);
            	
            	save();
            	
               // if (ContextCompat.checkSelfPermission(SurfaceViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //没有授权，则申请授权
                    //ActivityCompat.requestPermissions() 参数一：context 参数二：申请的权限名数组 参数三：请求码，要求唯一值
                    //ActivityCompat.requestPermissions(SurfaceViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_EXTERNAL);
                //} else {

            			
            	
            	
            	
               // }
               break;
        }
    }
	
	
	private void save() {
		/*        View dView = getWindow().getDecorView();
		        dView.setDrawingCacheEnabled(true);
		        dView.buildDrawingCache();*/
		        Bitmap bitmap = board.getBitmap();
		        
		        Date date = new Date(System.currentTimeMillis());
		        
                
		        if (bitmap != null) {
		            try {
		                // 获取内置SD卡路径
		                File dir = Environment.getExternalStorageDirectory();	
		                File folder = new File(dir, "night poems");
		                folder.mkdir();//创建文件夹

		                Log.d("night","get dir:"+folder.toString());
		                // 图片文件路径
		                File file = new File(folder,getSecondTimeStamp(date)+ ".png");
		                Log.d("night","make file");
		                FileOutputStream os = new FileOutputStream(file);
		                Log.d("night","make os");
		                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		                Log.d("night","compress pic");
		                os.flush();
		                os.close();
		                Log.d("night","save");
		                Toast.makeText(this, "存储到："+folder.toString(), Toast.LENGTH_SHORT).show();
		            } catch (Exception e) {
		             
		            }
		        }
		    }
	
	public static String getSecondTimeStamp(Date date){
        if (null == date) {
            return "X";
        }
        String timeStamp = String.valueOf(date.getTime()/1000);
        return timeStamp;
    }
	
	
        
	
	
	
        
        
}
	
	
	
	


