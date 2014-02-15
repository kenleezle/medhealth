package com.Hackathon.medhealth;
import java.util.List;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;

public class Medicine 
{
	int id;
	String name;
	int noti_isactive;
	String color_path;
	String img_path;
	String comment;
	String timings_table;
	
	public Medicine()
	{
		this.name = "";
		this.comment = "";
		this.id = 0;
		this.img_path = "";
		this.noti_isactive = 0;
		this.color_path = "";
		this.timings_table = "";
	}
	
	public void Add_timing(int start, int finish, int Quantity, Context context)
	{
		Timing new_Timing = new Timing();
		new_Timing.Time_Hour = start;
		new_Timing.Time_Min = finish;
		new_Timing.Med_quantity = Quantity;
		TimingsHandler handler = new TimingsHandler(context, timings_table);
		handler.addTiming(new_Timing);
	}
	
	List<Timing> get_timings(Context context)
	{
		TimingsHandler handler = new TimingsHandler(context, timings_table);
		return handler.getAllTimings();
		
	}
	
	
	
	
	
	
}



