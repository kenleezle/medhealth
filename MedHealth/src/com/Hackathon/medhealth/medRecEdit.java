package com.Hackathon.medhealth;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class medRecEdit extends Activity{
	
	
	@Override
	  protected void onCreate(Bundle savedInstanceState)
	  {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_redesign_ib);

	    ImageButton save = (ImageButton) findViewById(R.id.imageButton2);
	    ImageButton cancel = (ImageButton) findViewById(R.id.imageButton1);
	    
	    cancel.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	          Intent k = new Intent(medRecEdit.this, medRec.class);
	          startActivity(k);
	        }
	      });
	    
	    save.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	          Intent k = new Intent(medRecEdit.this, medRec.class);
	          startActivity(k);
	        }
	      });
	    
			
	  }


}
