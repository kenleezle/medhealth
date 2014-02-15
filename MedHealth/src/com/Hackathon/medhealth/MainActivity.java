package com.Hackathon.medhealth;

import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{


	String []ContactNames={"Mustafa", "Abdullah", "Yousef", "Saeed", "Omar", "Rashid", "Islam", "Akram", "Ken", "Ahmed", "Mohammed"};

	
	ListView l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts);

		l=(ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.single_row, R.id.textView, ContactNames);
		l.setAdapter(adapter);
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		String str=adapter.getItemAtPosition(position).toString(); //getting the names from the previous string
		//Create here an intent to go to another activity.
		//For now we will only display a toast (showing their names only
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show(); 
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		new MenuInflater(this).inflate(R.menu.option, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId()==R.id.add){
			startActivity(new Intent(this, contactinfo.class));
			return (true);
		}
		return super.onOptionsItemSelected(item);
	}



}


