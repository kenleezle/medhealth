package com.Hackathon.medhealth;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class New_Timing extends Activity
{
	EditText Quantity;
	Button add_timing, cancel;
	TimePicker time;
	int Target_Quantity, time_hour, time_min = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_timing);
		
		Quantity = (EditText) findViewById(R.id.Quantity);
		add_timing = (Button) findViewById(R.id.add_timing);
		cancel = (Button) findViewById(R.id.cancel);
		time = (TimePicker) findViewById(R.id.timePicker1);
		
		
		cancel.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		add_timing.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Target_Quantity = Integer.parseInt(Quantity.getText().toString());
				time_hour = time.getCurrentHour();
				time_min = time.getCurrentMinute();
				
				
				Intent intent_result = new Intent(getApplicationContext(),New_Medicine.class);
				intent_result.putExtra("Time_Hour", time_hour);
				intent_result.putExtra("Time_Min", time_min);
				intent_result.putExtra("Quanity", Target_Quantity);
				setResult(RESULT_OK,intent_result);     
				finish();
				
			}
		});
		
		


		
		
		
		
	}
	
	
	

}
