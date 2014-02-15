package com.Hackathon.medhealth;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

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

	NotificationManager			notimgr;
	Intent						Activity_intent;
	PendingIntent				Activity_pendingintent;
	NotificationCompat.Builder	Activity_notification;
	static MediaPlayer			myMediaPlayer;

	@Override
	public void onCreate()
	{
		super.onCreate();
		Uri tone = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.tone);
		myMediaPlayer = new MediaPlayer();

		Activity_intent = new Intent(getApplicationContext(), MainActivity.class);
		Activity_pendingintent = PendingIntent.getActivity(this, 0, Activity_intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notimgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Activity_notification = new NotificationCompat.Builder(this)
		.setOngoing(true).setContentTitle("HEY!")
		.setContentText("It's Time to take Medication!")
		.setContentIntent(Activity_pendingintent)
		.setAutoCancel(true)
		.setSmallIcon(R.drawable.ic_launcher);
		
		try
		{
			myMediaPlayer.setDataSource(getApplicationContext(), tone);
			myMediaPlayer.prepare();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void onStart(Intent intent, int startId)
	{
		Calendar calendar = Calendar.getInstance();
		
		Log.d("TAG", "SERVICE START AT " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
		myMediaPlayer.start();
		
		notimgr.notify(20083, Activity_notification.build());

	}

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

}
