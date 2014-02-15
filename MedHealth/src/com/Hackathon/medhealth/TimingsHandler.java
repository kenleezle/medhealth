package com.Hackathon.medhealth;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Matrix4f;
import android.util.Log;

 
@SuppressWarnings("unused")
public class TimingsHandler extends SQLiteOpenHelper 
{

	private static final int	DATABASE_VERSION					= 1;
	private static final String	DATABASE_NAME						= "TimingsManager";
	private static final String	TABLE_TIMINGS						= "Table_Timings";
	private static final String	KEY_ID								= "id";
	private static final String	KEY_Time_Hour						= "Time_Hour";
	private static final String	KEY_Time_Min						= "Time_Min";
	private static final String	KEY_MED_QUANTITY					= "Med_quantity";
	
	//Simple constructor
    public TimingsHandler(Context context) 
	{
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) 
	{
        
    }
    
    
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{      
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMINGS);
        onCreate(db);  // Create tables again
    }
    
    void addTimingTable(String name)
    {
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	String CREATE_PROFILES_TABLE = "CREATE TABLE " + name + 
		        "("+ 
			    	KEY_ID + " INTEGER PRIMARY KEY," +											
			    	KEY_Time_Hour + " TEXT," +     																
			    	KEY_Time_Min + " TEXT," +	
			    	KEY_MED_QUANTITY + " TEXT" +	
		    	")";
		        
    	db.execSQL("DROP TABLE IF EXISTS " + name);
        db.execSQL(CREATE_PROFILES_TABLE);
    	
    }
    
    void addTiming(Timing timing, String Table) 
	{
    	//in order to add a row, you need to add all your data in a ContentValues object....
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_Time_Hour, timing.Time_Hour);
        values.put(KEY_Time_Min, timing.Time_Min);
        values.put(KEY_MED_QUANTITY, timing.Med_quantity);
         
        //.... and throw this object in an SQL function.
        db.insert(Table, null, values);
        db.close();
       
    }
 
    
    // Find a specific row in the table using its ID.
    Timing GetTimingByID(int id, String table) 
	{
    	// to look for a row in the SQL table, we need a Cursor object that uses array of strings with the table's columns. 
        SQLiteDatabase db = this.getReadableDatabase();
        String values[] = 
        		{
					KEY_ID,				//0
					KEY_Time_Hour,		//1
					KEY_Time_Min,	//2
					KEY_MED_QUANTITY	//3
        		};
        Cursor cursor = db.query(table, values, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        // I dunno what this line does, sorry lol!
        if (cursor != null) cursor.moveToFirst();
 
        
        // after we find the row you add its values in a Profile object Which is the function we made....
        Timing Target_Timing = new Timing();
        Target_Timing.id = Integer.parseInt(cursor.getString(0));
        Target_Timing.Time_Hour = Integer.parseInt(cursor.getString(1));
        Target_Timing.Time_Min = Integer.parseInt(cursor.getString(2));
        Target_Timing.Med_quantity = Integer.parseInt(cursor.getString(3));
        return Target_Timing;
    }
     
    
    public List<Timing> getAllTimings(String table) 
	{
        List<Timing> MedicinesList = new ArrayList<Timing>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + table, null);
  
        if (cursor.moveToFirst()) 
		{
            do 
			{
            	Timing Target_Timing = new Timing();
                Target_Timing.id = Integer.parseInt(cursor.getString(0));
                Target_Timing.Time_Hour = Integer.parseInt(cursor.getString(1));
                Target_Timing.Time_Min = Integer.parseInt(cursor.getString(2));
                Target_Timing.Med_quantity = Integer.parseInt(cursor.getString(3));
                MedicinesList.add(Target_Timing);
            } while (cursor.moveToNext());
        }
 
        return MedicinesList;
    }
    
    
    
    // Updates a row. Its same as the adding one but different return value.
    public int updateProfile(Timing timing, String table) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        
        values.put(KEY_Time_Hour, timing.Time_Hour);
        values.put(KEY_Time_Min, timing.Time_Min);
        values.put(KEY_MED_QUANTITY, timing.Med_quantity);
         
        return db.update(table, values, KEY_ID + " = ?", new String[] { String.valueOf(timing.id) });
    }
 
   
    // Deletes a row in the table using its ID.
    public void deleteTimingbyID(Timing timing, String table) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, KEY_ID + " = ?", new String[] { String.valueOf(timing.id) });
        db.close();
    }
 
 
    // Gets the number of rows in the table
    public long getTimingsCount(String table) 
	{
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, table);
    }
    

    
    
}