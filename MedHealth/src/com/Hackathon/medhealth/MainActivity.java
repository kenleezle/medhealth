package com.example.medcare;

import java.util.Timer;
import java.util.TimerTask;



//import com.android.falldetector.R;
//import com.android.falldetector.notify;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	
	static String smsNumber = "0503839984";
	static   String caregiverNumber = "tel:0503839984";
	MediaPlayer mp;
	Timer t = new Timer();




	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		ImageButton saveMe = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton preS = (ImageButton) findViewById(R.id.imageButton2);
		ImageButton medRec = (ImageButton) findViewById(R.id.imageButton3);
		ImageButton guardian = (ImageButton) findViewById(R.id.imageButton4);
		
		saveMe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	mp = MediaPlayer.create(MainActivity.this, R.raw.calling);
            	mp.start();	   
            	sendSMS();
            }
        });
		
	//	preS.setOnClickListener(new View.OnClickListener() {
     //       public void onClick(View v) {
                // Perform action on click
      //      }
     //   });
		
	//	medRec.setOnClickListener(new View.OnClickListener() {
     //       public void onClick(View v) {
                // Perform action on click
     //       }
     //   });
		
	//	guardian.setOnClickListener(new View.OnClickListener() {
    //        public void onClick(View v) {
                // Perform action on click
    //        }
    //    });
	}
	
	/* class autoNotifyCaregiver extends TimerTask {
	    	public void run() { 
	        	if(mp.isPlaying() == true) 
	        		mp.stop();
	        	mp = MediaPlayer.create(MainActivity.this, R.raw.calling);
	        	mp.start();
	        	sendSMS();
	        	callCaregiver(); // limitation: not possible to play a sound or tts over a call http://developer.android.com/guide/topics/media/index.html
				//notify.this.finish();
	    	}
	    }
	 */
	 public static void sendSMS()
		{
		  SmsManager.getDefault().sendTextMessage(smsNumber, null, "I've fallen and I can't get up!", null, null);
		}/*
	 public void callCaregiver()
		{
	    	AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	    	//am.STREAM_VOICE_CALL
	    	am.setSpeakerphoneOn(true);
	    	am.isSpeakerphoneOn();
	        am.setStreamVolume(AudioManager.STREAM_VOICE_CALL, am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), AudioManager.FLAG_SHOW_UI); 
	        am.setRouting(AudioManager.MODE_CURRENT, AudioManager.ROUTE_ALL, 1); 
			startActivityForResult(new Intent(Intent.ACTION_CALL, Uri.parse(caregiverNumber)), 1);
	    	am.setSpeakerphoneOn(false);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
