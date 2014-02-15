package com.Hackathon.medhealth;

import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Contacts extends Activity implements OnItemClickListener{


	String []ContactNames;     //={"Mustafa", "Abdullah", "Yousef", "Saeed", "Omar", "Rashid", "Islam", "Akram", "Ken", "Ahmed", "Mohammed"};

	int[] images={R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10}; 
	
	ListView l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts);

		Resources res=getResources();
		ContactNames=res.getStringArray(R.array.titles);
		
		l=(ListView) findViewById(R.id.listView1);
		//ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.single_row, R.id.textView, ContactNames);
		myAdapter adapter= new myAdapter(this, ContactNames, images);
		l.setAdapter(adapter);
		
	}
	
	class myAdapter extends ArrayAdapter<String>
	{
		Context context;
		int []images;
		String []titleArray;
		myAdapter(Context c, String[] titles, int imgs[])
		{
			super(c,R.layout.single_row,R.id.textView, titles);
			this.context=c;
			this.images=imgs;
			this.titleArray=titles;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			View row=inflater.inflate(R.layout.single_row, parent, false);
			ImageView myImage= (ImageView) row.findViewById(R.id.imageView1);
			TextView myTitle= (TextView) row.findViewById(R.id.textView);
			
			myImage.setImageResource(images[position]);
			myTitle.setText(titleArray[position]);
			
			
			
			return row;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent k = new Intent(Contacts.this, contactinfodisplay.class);
	     startActivity(k);
		
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
			//startActivity(new Intent(this, contactinfo.class));
			try{
	    	     Intent k = new Intent(); //
	    	     k.setClass(Contacts.this, contactinfo.class);
	    	     startActivity(k);
			}catch(Exception e){}
			
			return (true);
		}
		return super.onOptionsItemSelected(item);
	}



}


