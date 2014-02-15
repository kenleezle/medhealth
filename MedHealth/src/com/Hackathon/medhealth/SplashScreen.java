package com.example.medcare;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

 
public class SplashScreen extends Activity {
 
    // Splash screen timer
    
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
 
        Thread splash = new Thread (){
 
            public void run() {
            	try
            	{
            	 sleep(1000);
            	 Intent i = new Intent(SplashScreen.this, MainActivity.class);
                 startActivity(i);
            	}catch (Exception e){e.printStackTrace();}
                // This method will be executed once the timer is over
                // Start your app main activity
                
            	finally {finish();}
                // close this activity
                
            }
        };
        splash.start();
    }
 
}