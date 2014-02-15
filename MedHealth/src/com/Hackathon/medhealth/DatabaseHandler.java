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
public class DatabaseHandler extends SQLiteOpenHelper 
{
 
    // Helpful variables.
	

	private static final int	DATABASE_VERSION					= 1;

	// Database Name
	private static final String	DATABASE_NAME						= "MedicineManager";

	// Table name
	private static final String	TABLE_MEDICINES						= "Medicines";
	private static final String	KEY_ID								= "id";
	private static final String	KEY_NAME							= "name";
	private static final String	KEY_NOTI_ISACTIVE					= "noti_isactive";
	private static final String	KEY_COLOR_PATH						= "color_path";
	private static final String	KEY_IMG_PATH						= "img_path";
	private static final String	KEY_COMMENT							= "comment";
	private static final String	KEY_TIMINGS_TABLE					= "timings_table";
	

	
	
	
	
	//Simple constructor
    public DatabaseHandler(Context context) 
	{super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) 
	{
        String CREATE_MEDICINES_TABLE = "CREATE TABLE " + TABLE_MEDICINES + 
		        "("+ 
			    	KEY_ID + " INTEGER PRIMARY KEY," +											
			    	KEY_NAME + " TEXT," +     																
			    	KEY_NOTI_ISACTIVE + " TEXT," +	
			    	KEY_COLOR_PATH + " TEXT," +	
			    	KEY_COMMENT + " TEXT," +	
			    	KEY_IMG_PATH + " TEXT," +
			    	KEY_TIMINGS_TABLE + " TEXT" +
		    	")";
		        
        
        db.execSQL(CREATE_MEDICINES_TABLE);
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
    void addMedicine(Medicine Medicine) 
	{
    	//in order to add a row, you need to add all your data in a ContentValues object....
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(KEY_NAME, Medicine.name);
        values.put(KEY_NOTI_ISACTIVE, Medicine.noti_isactive); 
        values.put(KEY_COLOR_PATH, Medicine.color_path);
        values.put(KEY_IMG_PATH, Medicine.img_path);
        values.put(KEY_COMMENT, Medicine.comment);
        values.put(KEY_TIMINGS_TABLE, Medicine.timings_table);
         
        //.... and throw this object in an SQL function.
        db.insert(TABLE_MEDICINES, null, values);
        db.close();
       
    }
 
    
    // Find a specific row in the table using its ID.
    Medicine GetMedicineByID(int id, Context context) 
	{
    	// to look for a row in the SQL table, we need a Cursor object that uses array of strings with the table's columns. 
        SQLiteDatabase db = this.getReadableDatabase();
        String values[] = 
        		{
				KEY_ID,//0
				KEY_NAME,			//1
				KEY_NOTI_ISACTIVE,	//2
				KEY_COLOR_PATH, //3
				KEY_IMG_PATH,	//4
				KEY_COMMENT,	//5
				KEY_TIMINGS_TABLE
        	    
        		};
        Cursor cursor = db.query(TABLE_MEDICINES, values, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        // I dunno what this line does, sorry lol!
        if (cursor != null) cursor.moveToFirst();
 
        
        // after we find the row you add its values in a Profile object Which is the function we made....
        Medicine Target_profile = new Medicine("temp", context);
        Target_profile.id = Integer.parseInt(cursor.getString(0));
        Target_profile.name = cursor.getString(1);
        Target_profile.noti_isactive = Integer.parseInt(cursor.getString(2));
        Target_profile.color_path = cursor.getString(3);
        Target_profile.img_path = cursor.getString(4);
        Target_profile.comment = cursor.getString(5);
        Target_profile.timings_table = cursor.getString(6);
        
        return Target_profile;
    }
     
    
    // generate a list with All Profiles
    public List<Medicine> getAllMedicines(Context context) 
	{
    	// a List object s kinda like an array but a little different.
        List<Medicine> MedicinesList = new ArrayList<Medicine>();
        SQLiteDatabase db = this.getWritableDatabase();
        // Define a cursor that points to all the table's rows.
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_MEDICINES, null);
 
        // in this loop, each time the cursor points at a row, it'll take its data and throw it in a profile object  
        if (cursor.moveToFirst()) 
		{
            do 
			{
            	Medicine Target_profile = new Medicine("temp", context);
                Target_profile.id = Integer.parseInt(cursor.getString(0));
                Target_profile.name = cursor.getString(1);
                Target_profile.noti_isactive = Integer.parseInt(cursor.getString(2));
                Target_profile.color_path = cursor.getString(3);
                Target_profile.img_path = cursor.getString(4);
                Target_profile.comment = cursor.getString(5);
                Target_profile.timings_table = cursor.getString(6);
                
                MedicinesList.add(Target_profile);
            } while (cursor.moveToNext()); // if the cursor is still not on the last entry then repeat.
        }
 
        return MedicinesList;
    }
    
    
    
    // Updates a row. Its same as the adding one but different return value.
    public int updateProfile(Medicine Medicine) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        
        values.put(KEY_NAME, Medicine.name);
        values.put(KEY_NOTI_ISACTIVE, Medicine.noti_isactive); 
        values.put(KEY_COLOR_PATH, Medicine.color_path);
        values.put(KEY_IMG_PATH, Medicine.img_path);
        values.put(KEY_COMMENT, Medicine.comment);
        values.put(KEY_TIMINGS_TABLE, Medicine.timings_table);
        // updating row
        return db.update(TABLE_MEDICINES, values, KEY_ID + " = ?", new String[] { String.valueOf(Medicine.id) });
    }
 
   
    // Deletes a row in the table using its ID.
    public void deleteProfilebyID(Medicine target_profile) 
	{
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICINES, KEY_ID + " = ?", new String[] { String.valueOf(target_profile.id) });
        db.close();
    }
 
 
    // Gets the number of rows in the table
    public long getProfilesCount() 
	{
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_MEDICINES);
    }
    

    
    
}