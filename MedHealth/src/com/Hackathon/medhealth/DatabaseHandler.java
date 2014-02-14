package com.Hackathon.medhealth;
/*
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

 
@SuppressWarnings("unused")
public class DatabaseHandler extends SQLiteOpenHelper 
{
 
    // Helpful variables.
	

	private static final int	DATABASE_VERSION					= 1;

	// Database Name
	private static final String	DATABASE_NAME						= "ProfileManager";

	// Table name
	private static final String	TABLE_MEDICINES						= "Medicines";
	private static final String	KEY_ID								= "id";
	private static final String	KEY_NAME							= "name";
	private static final String	KEY_NOTI_ISACTIVE					= "noti_isactive";
	private static final String	KEY_COLOR_PATH						= "color_path";
	private static final String	KEY_IMG_PATH						= "img_path";
	private static final String	KEY_COMMENT							= "comment";

	
	
	
	
	//Simple constructor
    public DatabaseHandler(Context context) 
	{super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) 
	{
        String CREATE_PROFILES_TABLE = "CREATE TABLE " + TABLE_MEDICINES + 
		        "("+ 
			    	KEY_ID + " INTEGER PRIMARY KEY," +											
			    	KEY_NAME + " TEXT," +     																
			    	KEY_NOTI_ISACTIVE + " TEXT," +	
			    	KEY_COLOR_PATH + " TEXT," +	
			    	KEY_IMG_PATH + " TEXT," +
			    	
			    	//KEY_TYPE + " TEXT," +	
			    	
			    
					
					KEY_TIME_ISENABLED + " TEXT," +
					KEY_TIME_START_HOUR + " TEXT," +
					KEY_TIME_START_MINUTE + " TEXT," +
					KEY_TIME_END_HOUR + " TEXT," +
					KEY_TIME_END_MINUTE + " TEXT," +
		
					
					
		    	")";
		        
        
        db.execSQL(CREATE_PROFILES_TABLE);
    }
 
	// this function checks if there is an already a database in the App. if so, then it wont create another.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{      
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINES);
        onCreate(db);  // Create tables again
    }
    
    
    /////PROFILES FUNCTIONS//////
 
    // Create new row in the SQL Table. When creating a new row, the SQL will automatically give it a unique ID.
    void addProfile(Medicine Medicine) 
	{
    	//in order to add a row, you need to add all your data in a ContentValues object....
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_NAME, newProfile.name);
        values.put(KEY_IMG_PATH, newProfile.isActive); 
        values.put(KEY_TYPE, newProfile.Type); 
        
        values.put(KEY_AUDIO_PROFILE_ISENABLED, newProfile.audio_profile_isEnabled);
        values.put(KEY_AUDIO_PROFILE, newProfile.audio_profile);
        
        values.put(KEY_NOTIFICATION_ONENTER_ISENABLED, newProfile.Notification_onEnter_isEnabled);
        values.put(KEY_NOTIFICATION_ONENTER_MESSAGE, newProfile.Notification_onEnter_message);
        
        values.put(KEY_NOTIFICATION_ONEXIT_ISENABLED, newProfile.Notification_onExit_isEnabled);
        values.put(KEY_NOTIFICATION_ONEXIT_MESSAGE, newProfile.Notification_onExit_message);
        
        values.put(KEY_GPS_LATITUDE, newProfile.GPS_latitude); 
        values.put(KEY_GPS_LONGITUDE, newProfile.GPS_longitude);
        values.put(KEY_GPS_CIRCLE_RADIUS, newProfile.GPS_circle_radius);
        values.put(KEY_GPS_TIMEOUT, newProfile.GPS_timeout);

        values.put(KEY_BT_NAME, newProfile.BT_Name); 
        values.put(KEY_BT_MAC_ADDRESS, newProfile.BT_MACAddress);
        values.put(KEY_BT_TIMEOUT, newProfile.BT_timeout);
        
        values.put(KEY_WIFI_SSID, newProfile.WIFI_SSID);
        values.put(KEY_WIFI_BSSID, newProfile.WIFI_BSSID);
        values.put(KEY_WIFI_TIMEOUT, newProfile.WIFI_timeout);
        
        values.put(KEY_ISPENDING, newProfile.isPending);
        
        values.put(KEY_TIME_ISENABLED, newProfile.Time_isEnabled);
        values.put(KEY_TIME_START_HOUR, newProfile.Time_start_hour);
        values.put(KEY_TIME_START_MINUTE, newProfile.Time_start_minute);
        values.put(KEY_TIME_END_HOUR, newProfile.Time_end_hour);
        values.put(KEY_TIME_END_MINUTE, newProfile.Time_end_minute);
        
        values.put(KEY_IMG_ADDRESS, newProfile.img_address);
        values.put(KEY_SOUND_ADDRESS, newProfile.sound_address);
        
        values.put(KEY_ACTION_URL_LABEL, newProfile.Action_URL_label);
        values.put(KEY_ACTION_URL, newProfile.Action_URL);
        
        
        //.... and throw this object in an SQL function.
        db.insert(TABLE_PROFILES, null, values);
        db.close();
       
    }
 
    
    // Find a specific row in the table using its ID.
    Profile GetProfileByID(int id) 
	{
    	// to look for a row in the SQL table, we need a Cursor object that uses array of strings with the table's columns. 
        SQLiteDatabase db = this.getReadableDatabase();
        String values[] = 
        		{
				KEY_ID,//0
				
				KEY_NAME,//1
				KEY_ISACTIVE,//2
				KEY_TYPE, //3
				
        	    KEY_AUDIO_PROFILE_ISENABLED,//4
        	    KEY_AUDIO_PROFILE,//5
        	    
        	    KEY_NOTIFICATION_ONENTER_ISENABLED,//6
        	    KEY_NOTIFICATION_ONENTER_MESSAGE,//7
        	    
        	    KEY_NOTIFICATION_ONEXIT_ISENABLED,//8
        	    KEY_NOTIFICATION_ONEXIT_MESSAGE,//9
        	    
        	    KEY_GPS_LATITUDE,//10
        	    KEY_GPS_LONGITUDE,//11
        	    KEY_GPS_CIRCLE_RADIUS,//12
        	    KEY_GPS_TIMEOUT, //13
        	    
        	    KEY_BT_NAME,//14
        	    KEY_BT_MAC_ADDRESS,//15
        	    KEY_BT_TIMEOUT,//16
        	    
        	    KEY_WIFI_SSID, //17
        	    KEY_WIFI_BSSID,//18
        	    KEY_WIFI_TIMEOUT,//19
        	    
        	    KEY_ISPENDING,//20
        	    
        	    KEY_TIME_ISENABLED,
        	    KEY_TIME_START_HOUR,
        	    KEY_TIME_START_MINUTE,
        	    KEY_TIME_END_HOUR,
        	    KEY_TIME_END_MINUTE,
        	    
        	    KEY_IMG_ADDRESS,
        	    KEY_SOUND_ADDRESS,
        	    
        	    KEY_ACTION_URL_LABEL,
        	    KEY_ACTION_URL,
        	    
        		};
        Cursor cursor = db.query(TABLE_PROFILES, values, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        // I dunno what this line does, sorry lol!
        if (cursor != null) cursor.moveToFirst();
 
        
        // after we find the row you add its values in a Profile object Which is the function we made....
        Profile Target_profile = new Profile();
        
        Target_profile.id = Integer.parseInt(cursor.getString(0));
        
        Target_profile.name = cursor.getString(1);
        Target_profile.isActive = Integer.parseInt(cursor.getString(2));
        Target_profile.Type = cursor.getString(3);
        
        Target_profile.audio_profile_isEnabled = Integer.parseInt(cursor.getString(4));
        Target_profile.audio_profile = Integer.parseInt(cursor.getString(5));

        Target_profile.Notification_onEnter_isEnabled = Integer.parseInt(cursor.getString(6));
        Target_profile.Notification_onEnter_message = cursor.getString(7);
        
        Target_profile.Notification_onExit_isEnabled = Integer.parseInt(cursor.getString(8));
        Target_profile.Notification_onExit_message = cursor.getString(9);
        
        Target_profile.GPS_latitude = Double.parseDouble(cursor.getString(10));
        Target_profile.GPS_longitude = Double.parseDouble(cursor.getString(11));
        Target_profile.GPS_circle_radius = Double.parseDouble(cursor.getString(12));
        Target_profile.GPS_timeout = Integer.parseInt(cursor.getString(13));
        
        Target_profile.BT_Name = cursor.getString(14);
        Target_profile.BT_MACAddress = cursor.getString(15);
        Target_profile.BT_timeout = cursor.getString(16);
        
        Target_profile.WIFI_SSID = cursor.getString(17);
        Target_profile.WIFI_BSSID = cursor.getString(18);
        Target_profile.WIFI_timeout = cursor.getString(19);
        
        Target_profile.isPending = Integer.parseInt(cursor.getString(20));
        
        Target_profile.Time_isEnabled = Integer.parseInt(cursor.getString(21));
        Target_profile.Time_start_hour = Integer.parseInt(cursor.getString(22));
        Target_profile.Time_start_minute = Integer.parseInt(cursor.getString(23));
        Target_profile.Time_end_hour = Integer.parseInt(cursor.getString(24));
        Target_profile.Time_end_minute = Integer.parseInt(cursor.getString(25));
        
        Target_profile.img_address = cursor.getString(26);
        Target_profile.sound_address = cursor.getString(27);
        
        Target_profile.Action_URL_label = cursor.getString(28);
        Target_profile.Action_URL = cursor.getString(29);


        

        // .. and finally we return this object.
        return Target_profile;
    }
     
    
    // generate a list with All Profiles
    public List<Profile> getAllProfiles() 
	{
    	// a List object s kinda like an array but a little differnt.
        List<Profile> LocationList = new ArrayList<Profile>();
        SQLiteDatabase db = this.getWritableDatabase();
        // Define a cursor that points to all the table's rows.
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_PROFILES, null);
 
        // in this loop, each time the cursor points at a row, it'll take its data and throw it in a profile object  
        if (cursor.moveToFirst()) 
		{
            do 
			{
            	
            	Profile Target_profile = new Profile();
                
            	Target_profile.id = Integer.parseInt(cursor.getString(0));
                
                Target_profile.name = cursor.getString(1);
                Target_profile.isActive = Integer.parseInt(cursor.getString(2));
                Target_profile.Type = cursor.getString(3);
                
                Target_profile.audio_profile_isEnabled = Integer.parseInt(cursor.getString(4));
                Target_profile.audio_profile = Integer.parseInt(cursor.getString(5));

                Target_profile.Notification_onEnter_isEnabled = Integer.parseInt(cursor.getString(6));
                Target_profile.Notification_onEnter_message = cursor.getString(7);
                
                Target_profile.Notification_onExit_isEnabled = Integer.parseInt(cursor.getString(8));
                Target_profile.Notification_onExit_message = cursor.getString(9);
                
                Target_profile.GPS_latitude = Double.parseDouble(cursor.getString(10));
                Target_profile.GPS_longitude = Double.parseDouble(cursor.getString(11));
                Target_profile.GPS_circle_radius = Double.parseDouble(cursor.getString(12));
                Target_profile.GPS_timeout = Integer.parseInt(cursor.getString(13));
                
                Target_profile.BT_Name = cursor.getString(14);
                Target_profile.BT_MACAddress = cursor.getString(15);
                Target_profile.BT_timeout = cursor.getString(16);
                
                Target_profile.WIFI_SSID = cursor.getString(17);
                Target_profile.WIFI_BSSID = cursor.getString(18);
                Target_profile.WIFI_timeout = cursor.getString(19);
                
                Target_profile.isPending = Integer.parseInt(cursor.getString(20));
                
                Target_profile.Time_isEnabled = Integer.parseInt(cursor.getString(21));
                Target_profile.Time_start_hour = Integer.parseInt(cursor.getString(22));
                Target_profile.Time_start_minute = Integer.parseInt(cursor.getString(23));
                Target_profile.Time_end_hour = Integer.parseInt(cursor.getString(24));
                Target_profile.Time_end_minute = Integer.parseInt(cursor.getString(25));
                
                Target_profile.img_address = cursor.getString(26);
                Target_profile.sound_address = cursor.getString(27);
                
                Target_profile.Action_URL_label = cursor.getString(28);
                Target_profile.Action_URL = cursor.getString(29);

                
                LocationList.add(Target_profile);
            } while (cursor.moveToNext()); // if the cursor is still not on the last entry then repeat.
        }
 
        return LocationList;
    }
    
    
    
    // Updates a row. Its same as the adding one but different return value.
    public int updateProfile(Profile target_Profile) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_NAME, target_Profile.name);
        values.put(KEY_ISACTIVE, target_Profile.isActive); 
        values.put(KEY_TYPE, target_Profile.Type); 
        
        values.put(KEY_AUDIO_PROFILE_ISENABLED, target_Profile.audio_profile_isEnabled);
        values.put(KEY_AUDIO_PROFILE, target_Profile.audio_profile);
        
        values.put(KEY_NOTIFICATION_ONENTER_ISENABLED, target_Profile.Notification_onEnter_isEnabled);
        values.put(KEY_NOTIFICATION_ONENTER_MESSAGE, target_Profile.Notification_onEnter_message);
        
        values.put(KEY_NOTIFICATION_ONEXIT_ISENABLED, target_Profile.Notification_onExit_isEnabled);
        values.put(KEY_NOTIFICATION_ONEXIT_MESSAGE, target_Profile.Notification_onExit_message);
        
        values.put(KEY_GPS_LATITUDE, target_Profile.GPS_latitude); 
        values.put(KEY_GPS_LONGITUDE, target_Profile.GPS_longitude);
        values.put(KEY_GPS_CIRCLE_RADIUS, target_Profile.GPS_circle_radius);
        values.put(KEY_GPS_TIMEOUT, target_Profile.GPS_timeout);

        values.put(KEY_BT_NAME, target_Profile.BT_Name); 
        values.put(KEY_BT_MAC_ADDRESS, target_Profile.BT_MACAddress);
        values.put(KEY_BT_TIMEOUT, target_Profile.BT_timeout);
        
        values.put(KEY_WIFI_SSID, target_Profile.WIFI_SSID);
        values.put(KEY_WIFI_BSSID, target_Profile.WIFI_BSSID);
        values.put(KEY_WIFI_TIMEOUT, target_Profile.WIFI_timeout);
        
        values.put(KEY_ISPENDING, target_Profile.isPending);
        
        values.put(KEY_TIME_ISENABLED, target_Profile.Time_isEnabled);
        values.put(KEY_TIME_START_HOUR, target_Profile.Time_start_hour);
        values.put(KEY_TIME_START_MINUTE, target_Profile.Time_start_minute);
        values.put(KEY_TIME_END_HOUR, target_Profile.Time_end_hour);
        values.put(KEY_TIME_END_MINUTE, target_Profile.Time_end_minute);
        
        values.put(KEY_IMG_ADDRESS, target_Profile.img_address);
        values.put(KEY_SOUND_ADDRESS, target_Profile.sound_address);
        
        values.put(KEY_ACTION_URL_LABEL, target_Profile.Action_URL_label);
        values.put(KEY_ACTION_URL, target_Profile.Action_URL);
        
        // updating row
        return db.update(TABLE_PROFILES, values, KEY_ID + " = ?", new String[] { String.valueOf(target_Profile.id) });
    }
 
   
    // Deletes a row in the table using its ID.
    public void deleteProfilebyID(Profile target_profile) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROFILES, KEY_ID + " = ?", new String[] { String.valueOf(target_profile.id) });
        db.close();
    }
 
 
    // Gets the number of rows in the table
    public long getProfilesCount() 
	{
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_PROFILES);
    }
    

    
    //////WAITLIST FUNCTIONS //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // Create new row in the SQL Table. When creating a new row, the SQL will automatically give it a unique ID.
    int addWaitlistProfile(Profile newProfile) 
	{
    	int row_ID;
    	//in order to add a row, you need to add all your data in a ContentValues object....
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_NAME, newProfile.name);
        values.put(KEY_ISACTIVE, newProfile.isActive); 
        values.put(KEY_TYPE, newProfile.Type); 
        
        values.put(KEY_AUDIO_PROFILE_ISENABLED, newProfile.audio_profile_isEnabled);
        values.put(KEY_AUDIO_PROFILE, newProfile.audio_profile);
        
        values.put(KEY_NOTIFICATION_ONENTER_ISENABLED, newProfile.Notification_onEnter_isEnabled);
        values.put(KEY_NOTIFICATION_ONENTER_MESSAGE, newProfile.Notification_onEnter_message);
        
        values.put(KEY_NOTIFICATION_ONEXIT_ISENABLED, newProfile.Notification_onExit_isEnabled);
        values.put(KEY_NOTIFICATION_ONEXIT_MESSAGE, newProfile.Notification_onExit_message);
        
        values.put(KEY_GPS_LATITUDE, newProfile.GPS_latitude); 
        values.put(KEY_GPS_LONGITUDE, newProfile.GPS_longitude);
        values.put(KEY_GPS_CIRCLE_RADIUS, newProfile.GPS_circle_radius);
        values.put(KEY_GPS_TIMEOUT, newProfile.GPS_timeout);

        values.put(KEY_BT_NAME, newProfile.BT_Name); 
        values.put(KEY_BT_MAC_ADDRESS, newProfile.BT_MACAddress);
        values.put(KEY_BT_TIMEOUT, newProfile.BT_timeout);
        
        values.put(KEY_WIFI_SSID, newProfile.WIFI_SSID);
        values.put(KEY_WIFI_BSSID, newProfile.WIFI_BSSID);
        values.put(KEY_WIFI_TIMEOUT, newProfile.WIFI_timeout);
        
        values.put(KEY_ISPENDING, newProfile.isPending);
        
        values.put(KEY_TIME_ISENABLED, newProfile.Time_isEnabled);
        values.put(KEY_TIME_START_HOUR, newProfile.Time_start_hour);
        values.put(KEY_TIME_START_MINUTE, newProfile.Time_start_minute);
        values.put(KEY_TIME_END_HOUR, newProfile.Time_end_hour);
        values.put(KEY_TIME_END_MINUTE, newProfile.Time_end_minute);
        
        values.put(KEY_IMG_ADDRESS, newProfile.img_address);
        values.put(KEY_SOUND_ADDRESS, newProfile.sound_address);
        
        
        //.... and throw this object in an SQL function.
        row_ID = (int) db.insert(TABLE_WAITLIST, null, values);
        db.close();
        
        return row_ID;
    }
 
    
    // Find a specific row in the table using its ID.
    Profile GetWaitlistProfileByID(int id) 
	{
    	// to look for a row in the SQL table, we need a Cursor object that uses array of strings with the table's columns. 
        SQLiteDatabase db = this.getReadableDatabase();
        String values[] = 
        		{
				KEY_ID,//0
				
				KEY_NAME,//1
				KEY_ISACTIVE,//2
				KEY_TYPE, //3
				
        	    KEY_AUDIO_PROFILE_ISENABLED,//4
        	    KEY_AUDIO_PROFILE,//5
        	    
        	    KEY_NOTIFICATION_ONENTER_ISENABLED,//6
        	    KEY_NOTIFICATION_ONENTER_MESSAGE,//7
        	    
        	    KEY_NOTIFICATION_ONEXIT_ISENABLED,//8
        	    KEY_NOTIFICATION_ONEXIT_MESSAGE,//9
        	    
        	    KEY_GPS_LATITUDE,//10
        	    KEY_GPS_LONGITUDE,//11
        	    KEY_GPS_CIRCLE_RADIUS,//12
        	    KEY_GPS_TIMEOUT, //13
        	    
        	    KEY_BT_NAME,//14
        	    KEY_BT_MAC_ADDRESS,//15
        	    KEY_BT_TIMEOUT,//16
        	    
        	    KEY_WIFI_SSID, //17
        	    KEY_WIFI_BSSID,//18
        	    KEY_WIFI_TIMEOUT,//19
        	    
				KEY_ISPENDING,//20
				
        	    
        	    KEY_TIME_ISENABLED,
        	    KEY_TIME_START_HOUR,
        	    KEY_TIME_START_MINUTE,
        	    KEY_TIME_END_HOUR,
        	    KEY_TIME_END_MINUTE,
        	    
        	    KEY_IMG_ADDRESS,
        	    KEY_SOUND_ADDRESS
        		};
        Cursor cursor = db.query(TABLE_WAITLIST, values, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        // I dunno what this line does, sorry lol!
        if (cursor != null) cursor.moveToFirst();
 
        
        // after we find the row you add its values in a Profile object Which is the function we made....
        Profile Target_profile = new Profile();
        
        Target_profile.id = Integer.parseInt(cursor.getString(0));
        
        Target_profile.name = cursor.getString(1);
        Target_profile.isActive = Integer.parseInt(cursor.getString(2));
        Target_profile.Type = cursor.getString(3);
        
        Target_profile.audio_profile_isEnabled = Integer.parseInt(cursor.getString(4));
        Target_profile.audio_profile = Integer.parseInt(cursor.getString(5));

        Target_profile.Notification_onEnter_isEnabled = Integer.parseInt(cursor.getString(6));
        Target_profile.Notification_onEnter_message = cursor.getString(7);
        
        Target_profile.Notification_onExit_isEnabled = Integer.parseInt(cursor.getString(8));
        Target_profile.Notification_onExit_message = cursor.getString(9);
        
        Target_profile.GPS_latitude = Double.parseDouble(cursor.getString(10));
        Target_profile.GPS_longitude = Double.parseDouble(cursor.getString(11));
        Target_profile.GPS_circle_radius = Double.parseDouble(cursor.getString(12));
        Target_profile.GPS_timeout = Integer.parseInt(cursor.getString(13));
        
        Target_profile.BT_Name = cursor.getString(14);
        Target_profile.BT_MACAddress = cursor.getString(15);
        Target_profile.BT_timeout = cursor.getString(16);
        
        Target_profile.WIFI_SSID = cursor.getString(17);
        Target_profile.WIFI_BSSID = cursor.getString(18);
        Target_profile.WIFI_timeout = cursor.getString(19);
        
        Target_profile.isPending = Integer.parseInt(cursor.getString(20));
        
        Target_profile.Time_isEnabled = Integer.parseInt(cursor.getString(21));
        Target_profile.Time_start_hour = Integer.parseInt(cursor.getString(22));
        Target_profile.Time_start_minute = Integer.parseInt(cursor.getString(23));
        Target_profile.Time_end_hour = Integer.parseInt(cursor.getString(24));
        Target_profile.Time_end_minute = Integer.parseInt(cursor.getString(25));
        
        Target_profile.img_address = cursor.getString(26);
        Target_profile.sound_address = cursor.getString(27);

        // .. and finally we return this object.
        return Target_profile;
    }
     
    
    // generate a list with All Profiles
    
    public List<Profile> getAllWaitlistProfiles() 
	{
    	// a List object is kinda like an array but a little different.
        List<Profile> LocationList = new ArrayList<Profile>();
        SQLiteDatabase db = this.getWritableDatabase();
        // Define a cursor that points to all the table's rows.
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_WAITLIST, null);
 
        // in this loop, each time the cursor points at a row, it'll take its data and throw it in a profile object  
        if (cursor.moveToFirst()) 
		{
            do 
			{
            	
            	Profile Target_profile = new Profile();
                
            	Target_profile.id = Integer.parseInt(cursor.getString(0));
                
                Target_profile.name = cursor.getString(1);
                Target_profile.isActive = Integer.parseInt(cursor.getString(2));
                Target_profile.Type = cursor.getString(3);
                
                Target_profile.audio_profile_isEnabled = Integer.parseInt(cursor.getString(4));
                Target_profile.audio_profile = Integer.parseInt(cursor.getString(5));

                Target_profile.Notification_onEnter_isEnabled = Integer.parseInt(cursor.getString(6));
                Target_profile.Notification_onEnter_message = cursor.getString(7);
                
                Target_profile.Notification_onExit_isEnabled = Integer.parseInt(cursor.getString(8));
                Target_profile.Notification_onExit_message = cursor.getString(9);
                
                Target_profile.GPS_latitude = Double.parseDouble(cursor.getString(10));
                Target_profile.GPS_longitude = Double.parseDouble(cursor.getString(11));
                Target_profile.GPS_circle_radius = Double.parseDouble(cursor.getString(12));
                Target_profile.GPS_timeout = Integer.parseInt(cursor.getString(13));
                
                Target_profile.BT_Name = cursor.getString(14);
                Target_profile.BT_MACAddress = cursor.getString(15);
                Target_profile.BT_timeout = cursor.getString(16);
                
                Target_profile.WIFI_SSID = cursor.getString(17);
                Target_profile.WIFI_BSSID = cursor.getString(18);
                Target_profile.WIFI_timeout = cursor.getString(19);
                
                Target_profile.isPending = Integer.parseInt(cursor.getString(20));
                
                Target_profile.Time_isEnabled = Integer.parseInt(cursor.getString(21));
                Target_profile.Time_start_hour = Integer.parseInt(cursor.getString(22));
                Target_profile.Time_start_minute = Integer.parseInt(cursor.getString(23));
                Target_profile.Time_end_hour = Integer.parseInt(cursor.getString(24));
                Target_profile.Time_end_minute = Integer.parseInt(cursor.getString(25));
                
                Target_profile.img_address = cursor.getString(26);
                Target_profile.sound_address = cursor.getString(27);

                
                LocationList.add(Target_profile);
            } while (cursor.moveToNext()); // if the cursor is still not on the last entry then repeat.
        }
 
        return LocationList;
    }
    
    
    
    // Updates a row. Its same as the adding one but differnt return value.
    public int updateWaitlistProfile(Profile target_Profile) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_NAME, target_Profile.name);
        values.put(KEY_ISACTIVE, target_Profile.isActive); 
        values.put(KEY_TYPE, target_Profile.Type); 
        
        values.put(KEY_AUDIO_PROFILE_ISENABLED, target_Profile.audio_profile_isEnabled);
        values.put(KEY_AUDIO_PROFILE, target_Profile.audio_profile);
        
        values.put(KEY_NOTIFICATION_ONENTER_ISENABLED, target_Profile.Notification_onEnter_isEnabled);
        values.put(KEY_NOTIFICATION_ONENTER_MESSAGE, target_Profile.Notification_onEnter_message);
        
        values.put(KEY_NOTIFICATION_ONEXIT_ISENABLED, target_Profile.Notification_onExit_isEnabled);
        values.put(KEY_NOTIFICATION_ONEXIT_MESSAGE, target_Profile.Notification_onExit_message);
        
        values.put(KEY_GPS_LATITUDE, target_Profile.GPS_latitude); 
        values.put(KEY_GPS_LONGITUDE, target_Profile.GPS_longitude);
        values.put(KEY_GPS_CIRCLE_RADIUS, target_Profile.GPS_circle_radius);
        values.put(KEY_GPS_TIMEOUT, target_Profile.GPS_timeout);

        values.put(KEY_BT_NAME, target_Profile.BT_Name); 
        values.put(KEY_BT_MAC_ADDRESS, target_Profile.BT_MACAddress);
        values.put(KEY_BT_TIMEOUT, target_Profile.BT_timeout);
        
        values.put(KEY_WIFI_SSID, target_Profile.WIFI_SSID);
        values.put(KEY_WIFI_BSSID, target_Profile.WIFI_BSSID);
        values.put(KEY_WIFI_TIMEOUT, target_Profile.WIFI_timeout);
        
        values.put(KEY_ISPENDING, target_Profile.isPending);
        
        values.put(KEY_TIME_ISENABLED, target_Profile.Time_isEnabled);
        values.put(KEY_TIME_START_HOUR, target_Profile.Time_start_hour);
        values.put(KEY_TIME_START_MINUTE, target_Profile.Time_start_minute);
        values.put(KEY_TIME_END_HOUR, target_Profile.Time_end_hour);
        values.put(KEY_TIME_END_MINUTE, target_Profile.Time_end_minute);
        
        values.put(KEY_IMG_ADDRESS, target_Profile.img_address);
        values.put(KEY_SOUND_ADDRESS, target_Profile.sound_address);
        
        // updating row
        return db.update(TABLE_WAITLIST, values, KEY_ID + " = ?", new String[] { String.valueOf(target_Profile.id) });
    }
 
   
    // Deletes a row in the table using its ID.
    public void deleteWaitlistProfilebyID(Profile target_profile) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WAITLIST, KEY_ID + " = ?", new String[] { String.valueOf(target_profile.id) });
        db.close();
    }
 
 
    // Gets the number of rows in the table
    public long getWaitlistProfilesCount() 
	{
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_WAITLIST);
    }
    
}*/