package com.example.where2go;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

public class Handler_sqlite extends SQLiteOpenHelper{
	
	
	public Handler_sqlite(Context ctx)
	{
		super(ctx, "comida", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String query = "CREATE TABLE carne ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +"dias INTEGER, veces INTEGER);";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nue)
	{
		db.execSQL("DROP TABLE IF EXISTS carne");
		onCreate(db);
	}

	public void insertReg(int dias, int veces)
	{
		ContentValues values = new ContentValues();
		values.put("dias", dias); 
		values.put("veces", veces);
		this.getWritableDatabase().insert("carne",null, values);
	}

	public int scan(String name[], String score[])
	{
		String columns[]= {_ID,"dias","veces"};
		Cursor c = this.getReadableDatabase().query("carne", columns, null, null,null, null, null);

		int dias,veces;
		
		dias = c.getColumnIndex("dias");
		veces = c.getColumnIndex("veces");
		int count=0;
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			name[count] = Integer.toString(c.getInt(dias));
			score[count]=Integer.toString(c.getInt(veces));
			count++;
		}

		return count;
	}

	public void openDB()
	{
		this.getWritableDatabase();

	}

	public void closeDB()
	{
		this.close();
	}
	
}
