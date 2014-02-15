package com.Hackathon.medhealth;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

public class Service_Medicine_time extends Service
{

	AlarmManager alarmMgr;
	NotificationManager notimgr;
	Intent Activity_intent;
	PendingIntent Activity_pendingintent;
	NotificationCompat.Builder	Activity_notification;
	static MediaPlayer	myMediaPlayer;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		myMediaPlayer = new MediaPlayer();
		
		
		
		
		Activity_intent = new Intent(getApplicationContext(), MainActivity.class);
		Activity_pendingintent = PendingIntent.getActivity(this, 0, Activity_intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notimgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Activity_notification = new NotificationCompat.Builder(this)
		.setOngoing(true)
		.setContentTitle("HEY OLD FART!")
		.setContentText("It's Time to take Medication!")
		.setContentIntent(Activity_pendingintent)
		//.setSound(x)
		.setAutoCancel(true).setSmallIcon(R.drawable.ic_launcher);
		//.setDefaults(Notification.DEFAULT_ALL);
		
	}
	
	@Override
	public synchronized void onStart(Intent intent, int startId)
	{
		Uri tone  = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.tone );
		try
        {
			myMediaPlayer.setDataSource(getApplicationContext(), tone);
			myMediaPlayer.prepare();
            myMediaPlayer.start();
        }catch(Exception x)
        {
        	x.printStackTrace();
        }
		
	
		notimgr.notify(20083, Activity_notification.build());
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {return null;}

}
