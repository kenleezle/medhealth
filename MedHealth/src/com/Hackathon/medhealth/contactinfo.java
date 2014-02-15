package com.Hackathon.medhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class contactinfo extends Activity{
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactinfo);
	
		ImageButton SB = (ImageButton) findViewById(R.id.SaveB);
		ImageButton CB = (ImageButton) findViewById(R.id.CancelB);
		SB.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        // Do something in response to button click
		    	try{
		    	    //Save to the database
		    		finish();
		    	}catch(Exception e){}
		    	
		    	}
		    
		});
		
		CB.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        // Do something in response to button click
		    	 try{
		    	     
		    	     finish();
		    	}catch(Exception e){}
		    	
		    	}
		    
		});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
