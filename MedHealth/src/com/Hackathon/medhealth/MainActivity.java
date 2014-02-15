package com.Hackathon.medhealth;

import java.util.Calendar;
import java.util.List;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	DatabaseHandler				DB;
	AlarmManager				alarmMgr;
	PendingIntent				alarmIntent;
	NotificationCompat.Builder	Activity_notification;
	Intent						Activity_intent;
	PendingIntent				Activity_pendingintent;
	Button t;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
	}
}
