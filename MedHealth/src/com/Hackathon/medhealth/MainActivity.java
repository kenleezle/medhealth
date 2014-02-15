package com.Hackathon.medhealth;

import java.util.Calendar;
import java.util.List;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity
{
	DatabaseHandler				DB;
	AlarmManager				alarmMgr;
	PendingIntent				alarmIntent;
	NotificationCompat.Builder	Activity_notification;
	Intent						Activity_intent;
	PendingIntent				Activity_pendingintent;
	ImageButton t;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);

	    t = (ImageButton) findViewById(R.id.imageButton2);
	   
	    t.setOnClickListener(new OnClickListener()
	    {

	      @Override
	      public void onClick(View v)
	      {
	        Intent i = new Intent(getApplicationContext(), New_Medicine.class);
	        startActivity(i);

	      }
	    });
	    
	    ImageButton b1 = (ImageButton) findViewById(R.id.imageButton4);
		b1.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        // Do something in response to button click
		    	try{
		    	     Intent k = new Intent(); //
		    	     k.setClass(MainActivity.this, Contacts.class);
		    	     startActivity(k);
		    	}catch(Exception e){
		    	}

		    	}

		});
		// For now this is the save button. This should have its own button.
	    ImageButton qr = (ImageButton) findViewById(R.id.imageButton1);
		qr.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
				    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // PRODUCT_MODE for bar codes
				    startActivityForResult(intent, 0);
				} catch (Exception e) {
				    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
				    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
				    startActivity(marketIntent);
				}
			}
		});
	   
	    Intent intent = new Intent(this, Service_Medicine_time.class);
	    alarmIntent = PendingIntent.getService(this, 0, intent, 0);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
            String contents = data.getStringExtra("SCAN_RESULT");
			Log.i("QR", "Received Result Data "+contents);
		}
	}
}
