package com.example.nightpoemsp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class MainActivity  extends Activity implements OnClickListener {

	private Button buttonStart;
	private Button buttonShow;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        buttonStart = (Button) findViewById(R.id.button_start);
        buttonShow = (Button) findViewById(R.id.button_show);
        
        Log.d("night","create");
        
        buttonStart.setOnClickListener(this);
        buttonShow.setOnClickListener(this);
        
        
    }

    
    
    @Override
    public void onClick(View v) {
    switch (v.getId()) {
    case R.id.button_start:
    	buttonStart.setVisibility(View.GONE);
    	buttonShow.setVisibility(View.GONE);
    	
    	Log.d("night","click");
    	break;
    case R.id.button_show:
    	Intent intent = new Intent(MainActivity.this, Draw.class);
    	startActivity(intent);
    	
    	
    	Log.d("night","jump");
    	break;
    default:
    	Log.d("night","default");
    	break;
    	}
    }
    
    
    
    
    
    
    
    
}
