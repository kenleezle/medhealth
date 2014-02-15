package com.Hackathon.medhealth;

import java.util.Calendar;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity
{
	DatabaseHandler				DB;
	NotificationCompat.Builder	Activity_notification;
	Intent						Activity_intent;
	PendingIntent				Activity_pendingintent;
	ImageButton t;
	AlarmManager alarmMgr;
	Calendar		calendar;


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
	   
		DB = new DatabaseHandler(this);
		alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
		try
		{
			Service_Medicine_time.myMediaPlayer.stop();
		} catch (Exception e){}
		
		
		if (DB.getProfilesCount() > 0)
			EnableAllAlarams(getApplicationContext());	
	}
	
	
	public void EnableAllAlarams(Context context)
	{
		List<Medicine> MList = DB.getAllMedicines(this);
		for (Medicine t : MList)
		{
			List<Timing> TList = t.get_timings(context);
			for (Timing tt : TList)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY,tt.Time_Hour);
				calendar.set(Calendar.MINUTE, tt.Time_Min);
				calendar.set(Calendar.SECOND, 0);
				Intent intent = new Intent(getApplicationContext(), Service_Medicine_time.class);
				PendingIntent alarmIntent = PendingIntent.getService(this, 0, intent, 0);
				alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
			}
			
			
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
            String contents = data.getStringExtra("SCAN_RESULT");
            saveMedicinesFromQR(contents);
		}
	}
	protected void saveMedicinesFromQR(String qrcontents) {
		Log.i("QR", "Received Result Data "+qrcontents);
		try {
			JSONObject jcontents = new JSONObject(qrcontents);
			JSONArray jmeds = jcontents.getJSONArray("meds");
			for (int i = 0; i < jmeds.length(); i++) {
				JSONObject jmed = jmeds.getJSONObject(i); 
				Log.i("QR", "Received Name "+ jmed.getString("name"));			
				Log.i("QR", "Received Color "+jmed.getString("color"));
				JSONArray jtimes = jmed.getJSONArray("times");
				for (int j = 0; j < jtimes.length(); j++) {
					String jtime = (String) jtimes.get(j); 
					Log.i("QR", "Received Time "+jtime);	
				}
			}
		}
		catch (Exception e) {
			Log.e("QR", e.toString());
		}
	}
}
