package com.example.db;


import com.example.yjl.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {   
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "contacts.db";
	private static final String TABLE_NAME = "contacts";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_EMAIL = "email";
	//private static final String COLUMN_USERNAME = "username";
	private static final String COLUMN_PASS = "pass";
	SQLiteDatabase db;
	private static final String TABLE_CREATE = "create table contacts(id integer primary key not null,"+
	"name text not null, email text not null,pass text not null);";
	
	
	public DatabaseHelper(Context context) 
	  {     
	    super(context,DATABASE_NAME, null,DATABASE_VERSION);     
	     }     
	     
	     @Override    
    public void onCreate(SQLiteDatabase db) {     
	         // TODO 创建数据库后，对数据库的操作  
	         
	         //execSQL()为执行参数里面的SQL语句
	         db.execSQL(TABLE_CREATE);
	         
	         this.db=db;
	     }     
	 public void insertContact(Contact c)
	 {
		 db =this.getWritableDatabase();
		 ContentValues values = new ContentValues();
		 values.put(COLUMN_NAME, c.getName());
		 values.put(COLUMN_EMAIL, c.getEmail());
		 values.put(COLUMN_PASS, c.getPass());
		 
		 
		 db.insert(TABLE_NAME, null, values);
		 db.close();
	 }
	 
	 public String searchPass(String name)
	 {
		 db = this.getReadableDatabase();
		 String query = "select name, pass from "+ TABLE_NAME;
		 
		 Cursor cursor= db.rawQuery(query, null);
		 String a,b;
		 b="not found";
		 if(cursor.moveToFirst())
		 {
			 do{
				 a = cursor.getString(0);
				 
				 
				 if(a.equals(name))
				 {
					 b = cursor.getString(1);
					 break;
				 }
			 
			 }
			 while(cursor.moveToNext());
		 }
		 
		return b; 
		 
	 }
	 //
	 
	 public void upDate(String name,String pass,String email){
	    	 db = this.getReadableDatabase();
			 String query = "UPDATE "+ TABLE_NAME + " SET pass = '" + pass +"', email = '" + email + "' WHERE name = '" +name+"'";
			 this.db.execSQL(query);
	    	 
	     }
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {     
	       String query= "DROP TABLE IF EXISTS"+ TABLE_NAME;
	       this.db.execSQL(query);
	       this.onCreate(db);
	     }     
	     
	 //@Override    
	 //public void onOpen(SQLiteDatabase db) {     
	      //   super.onOpen(db);       
	         // TODO 每次成功打开数据库后首先被执行     
	 //    }     
	 }     
