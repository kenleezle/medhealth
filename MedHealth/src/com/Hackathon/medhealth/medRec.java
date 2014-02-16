package com.Hackathon.medhealth;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;



public class medRec extends Activity {
	
	  @Override
	  protected void onCreate(Bundle savedInstanceState)
	  {
		  
		  
		  
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.info_ib);
	    
	    ImageButton edit = (ImageButton) findViewById(R.id.imageButton1);
	    ImageButton pre = (ImageButton) findViewById(R.id.imageButton2);
	    
	    edit.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	          Intent k = new Intent(medRec.this, medRecEdit.class);
	          startActivity(k);
	        }
	      });
	    
	    pre.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View b) {
	          Intent a = new Intent(medRec.this, Contacts.class);
	          startActivity(a);
	        }
	      });

			
	  }

}
