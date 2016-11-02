package com.example.pimkey.Calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseEvent extends SQLiteOpenHelper {
  public static final String EVENT_KEY = "id";
  public static final String EVENT_DATE = "date";
  public static final String EVENT_TITLE = "title";
  public static final String EVENT_PLACE = "place";
  public static final String EVENT_BEGIN = "begin";
  public static final String EVENT_END = "end";
  public static final String EVENT_NOTE = "note";
  
   
  public static final String EVENT_TABLE_NAME = "event";
  public static final String EVENT_TABLE_DROP = "DROP TABLE IF EXISTS " + EVENT_TABLE_NAME + ";";
  public static final String EVENT_TABLE_CREATE =
    "CREATE TABLE " + EVENT_TABLE_NAME + " (" +
      EVENT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
      EVENT_DATE + " TEXT, " +
      EVENT_TITLE + " TEXT, " +
      EVENT_PLACE + " TEXT, " +
      EVENT_BEGIN + " TEXT, " +
      EVENT_END + "TEXT, " +
      EVENT_NOTE + "TEXT);";

  public DatabaseEvent(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(EVENT_TABLE_CREATE);
  }
 

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(EVENT_TABLE_DROP);
    onCreate(db);
  }
}