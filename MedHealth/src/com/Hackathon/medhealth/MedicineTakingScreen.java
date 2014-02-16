package com.Hackathon.medhealth;

import java.util.Calendar;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MedicineTakingScreen extends Activity
{
	DatabaseHandler	DB;
	Intent			Activity_intent;
	PendingIntent	Activity_pendingintent;
	TextView		label_name, label_quantity, label_comments;
	AlarmManager	alarmMgr;
	Calendar		calendar;
	ImageView 		IV_shape, IV_color;
	ImageButton		btn_done;
	NotificationManager			notimgr;
	Uri myshape, mycolor;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle B = getIntent().getExtras();
		setContentView(R.layout.notify_ib);
		notimgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		
		String bundle_name = B.getString("name");
		String bundle_med_quantity = B.getString("med_quantity");
		String bundle_comment = B.getString("comment");
		String bundle_shape = B.getString("img_path");
		String bundle_color = B.getString("color_path");
		
		label_name = (TextView) findViewById(R.id.label_name);
		label_quantity = (TextView) findViewById(R.id.label_quantity);
		label_comments = (TextView) findViewById(R.id.label_comment);
		IV_shape = (ImageView) findViewById(R.id.shape);
		IV_color = (ImageView) findViewById(R.id.color);
		btn_done = (ImageButton)findViewById(R.id.imageButton1);

		
		label_name.setText(bundle_name);
		label_quantity.setText(bundle_med_quantity);
		label_comments.setText(bundle_comment);
		
		if (bundle_shape.equalsIgnoreCase("Pill"))
			 myshape = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.pill);
		else if (bundle_shape.equalsIgnoreCase("Capsule"))
			myshape = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.capsule);
		else if (bundle_shape.equalsIgnoreCase("injection"))
			myshape = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.lnjection);
		else if (bundle_shape.equalsIgnoreCase("Liquid"))
			myshape = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.liquid);
		else if (bundle_shape.equalsIgnoreCase("Tablet"))
			myshape = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.tablet);
		
		if (bundle_color.equalsIgnoreCase("Red"))
			 mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.red);
		else if (bundle_color.equalsIgnoreCase("white"))
			mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.white);
		else if (bundle_color.equalsIgnoreCase("purple"))
			mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.pink);
		else if (bundle_color.equalsIgnoreCase("blue"))
			mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.blue);
		else if (bundle_color.equalsIgnoreCase("yellow"))
			mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.yellow);
		if (bundle_color.equalsIgnoreCase("green"))
			 mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.green);
		else if (bundle_color.equalsIgnoreCase("brown"))
			mycolor = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.brown);
		
			
	   
		IV_shape.setImageURI(myshape);
		IV_color.setImageURI(mycolor);
		
		
		
		btn_done.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				
				notimgr.cancel(20083);
				Toast.makeText(getApplicationContext(), "Good Job :)", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		
		
	}
	
	
	
	
}
