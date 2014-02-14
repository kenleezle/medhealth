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
	
	public Medicine()
	{
		this.name = "";
		this.comment = "";
		this.id = 0;
		this.img_path = "";
		this.noti_isactive = 0;
		this.color_path = "";
	}
	
	
	
	
	
	
}


