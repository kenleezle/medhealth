package com.Hackathon.medhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class contactinfodisplay extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactinfodisplay);
		
		
		
		ImageButton b1 = (ImageButton) findViewById(R.id.editButton);
		b1.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        // Do something in response to button click
		    	     
		    	     Intent k = new Intent(contactinfodisplay.this, Contacts.class);
		    	     startActivity(k);

		    	}    
		});	
	}}