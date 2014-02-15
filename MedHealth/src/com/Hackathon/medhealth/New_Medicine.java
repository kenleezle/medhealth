package com.Hackathon.medhealth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class New_Medicine extends Activity
{
	private static final int	REQUEST_TIME		= 6382; // onActivityResult request code
	EditText name, comment;
	//DatabaseHandler DB;
	// TimingsHandler	TH;
	Button add, type, color, timing;
	AlertDialog.Builder color_builder, Type_builder, Timing_builder;
	CharSequence colors[] = {"Red", "White", "purple", "Blue", "Yellow", "Green", "Brown"};
	CharSequence Types[] = {"Pill", "Capsule", "injection", "Liquid", "Tablet"};
	int Target_Quantity, time_hour, time_min, no_Timings = 0;
	String Color = "Red";
	String Type = "Pill";
	/*
	Intent intent;
	PendingIntent alarmIntent;
	*/
	List <Timing> MyTimings;
	
	public static void addMedicineAndTimingsToDB(String name,String comment,String color_path,String img_path,List<Timing> MyTimings, Context context) {
		Intent intent;
		PendingIntent alarmIntent;
		DatabaseHandler DB;
		TimingsHandler	TH;
		
		DB = new DatabaseHandler(context);
		TH = new TimingsHandler(context);


		Medicine new_medicine = new Medicine(name,context);
		new_medicine.comment = comment;
		new_medicine.color_path = color_path;
		new_medicine.img_path = img_path;
		new_medicine.timings_table = new_medicine.name; //chunk
		DB.addMedicine(new_medicine);
		
		
		for(Timing N : MyTimings)
			TH.addTiming(N, new_medicine.name);
		
		
		List<Medicine> MList = DB.getAllMedicines(context);
		int request = 0;
		for (Medicine t : MList)
		{
			
			List<Timing> TList = t.get_timings(context);
			for (Timing tt : TList)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.set(Calendar.HOUR_OF_DAY,tt.Time_Hour);
				calendar.set(Calendar.MINUTE, tt.Time_Min);
				calendar.set(Calendar.SECOND, 0);
				
				
				
				intent = new Intent(context, Service_Medicine_time.class);
				alarmIntent = PendingIntent.getService(context, request , intent, 0);
				
				AlarmManager alarmMgr; 
				alarmMgr = (AlarmManager) context.getSystemService(ALARM_SERVICE);
				alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
				request++;
			}
		}
		Toast toast = Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT);
		toast.show();
			
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_medicine);
		
		
		//TH = new TimingsHandler(this);
		name = (EditText) findViewById(R.id.text_name);
		comment = (EditText) findViewById(R.id.comment);
		timing = (Button) findViewById(R.id.btn_Timing);
		color = (Button) findViewById(R.id.btn_color);
		type = (Button) findViewById(R.id.btn_type);
		add = (Button) findViewById(R.id.btn_add);
		
		
		
		
		MyTimings = new ArrayList<Timing>();
		
		color_builder = new AlertDialog.Builder(this);
        color_builder.setTitle("Pick your Color for the Medicine");
        color_builder.setCancelable(true);
        color_builder.setItems(colors, new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Log.d("TAG", "you chose " + colors[which]);
				Color = colors[which].toString();
			}
		});
        
        Type_builder = new AlertDialog.Builder(this);
        Type_builder.setTitle("What's the type of the Medicine?");
        Type_builder.setCancelable(true);
        Type_builder.setItems(Types, new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Log.d("TAG", "you chose " + Types[which]);
				Type = Types[which].toString();
			}
		});
        
        
        
        
        
        
		
		timing.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), New_Timing.class);
				startActivityForResult(i, REQUEST_TIME);
		         
			}
		});
		
		color.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				 AlertDialog alert11 = color_builder.create();
		         alert11.show();
				
			}
		});
		
		type.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				AlertDialog alert11 = Type_builder.create();
				alert11.show();
			}
		});

		add.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				if (no_Timings == 0 || name.getText().toString().isEmpty())
				{
					Toast.makeText(getApplicationContext(), "invalid Arguments", Toast.LENGTH_SHORT).show();
				}
				else
				{
					addMedicineAndTimingsToDB(name.getText().toString(),comment.getText().toString(),Color,Type,MyTimings,getApplicationContext());											
					finish();
				}
					
				
				/*
				Medicine X = DB.GetMedicineByID(1);
				List <Timing> test_list = X.get_timings(getApplicationContext());
				
				for (Timing t : test_list)
				{
					Log.d("TAG", "Time: " + t.Time_Hour + ":" + t.Time_Min);
				}
				
				*/
			}
			
		});
		


	}

	
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    // Get the layout inflater
	    LayoutInflater inflater = this.getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.timing_dialog, null))
	    // Add action buttons
	           .setPositiveButton("OK", new DialogInterface.OnClickListener() 
	           {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // sign in the user ...
	               }
	           })
	           .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() 
	           {
	               public void onClick(DialogInterface dialog, int id) 
	               {
	                   dialog.cancel();
	               }
	           });      
	    return builder.create();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		
		if (requestCode == REQUEST_TIME)
		{
			if (resultCode == RESULT_OK)
			{
				if (data != null)
				{
					Target_Quantity = data.getIntExtra("Quanity", 0);
					time_hour = data.getIntExtra("Time_Hour", 0);
					time_min = data.getIntExtra("Time_Min", 0);
					Timing new_timing = new Timing();
					new_timing.Med_quantity = Target_Quantity;
					new_timing.Time_Hour = time_hour;
					new_timing.Time_Min = time_min;
					MyTimings.add(new_timing);
					
					Log.d("TAG", "Q: " + Target_Quantity);
					Log.d("TAG", "H: " + time_hour);
					Log.d("TAG", "m: " + time_min);
					
					no_Timings++;
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	

}
