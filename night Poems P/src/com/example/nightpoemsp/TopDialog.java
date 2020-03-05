package com.example.nightpoemsp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TopDialog extends Activity implements OnClickListener{
	
	
	Button button;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_main);
	
	button = (Button) findViewById(R.id.button_show);
	button.setOnClickListener(this);
	
	
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_show:
				//AlertDialog.Builder dialog = new AlertDialog.Builder(TopDialog.this);
				AlertDialog dialog = new AlertDialog.Builder(TopDialog.this).create();
				
				dialog.setTitle("This is Dialog");
				dialog.setMessage("Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important." +
						"Something important.");
				dialog.setCancelable(false);
				//dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				//	@Override
				//	public void onClick(DialogInterface dialog, int which) {
				//	}
				//});
				//dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				//	@Override
				//	public void onClick(DialogInterface dialog, int which) {
				//	}
				//});
				dialog.show();
				
				
				
				 WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
				 
				 Display defaultDisplay  = getWindowManager().getDefaultDisplay();
				 
				 Point point = new Point();
				    defaultDisplay.getSize(point);
				    int x = point.x;
				    int y = point.y;
				    Log.d("night","x"+x+"y"+y);
				 params.width = 700;
				 params.height =1200;
				 
				 dialog.getWindow().setAttributes(params);  

				
				 
				
				break;
			default:
				break;
		}
	}
	
	
}
