package com.example.pimkey.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class EventDAO extends DAOBaseEvent{
	  public EventDAO(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}


	public static final String TABLE_NAME = "event";
	  public static final String KEY = "id";
	  public static final int COL_KEY = 0;
	  public static final String DATE = "date";
	  public static final int COL_DATE = 1;
	  public static final String TITLE = "title";
	  public static final int COL_TITLE = 2;
	  public static final String PLACE = "place";
	  public static final int COL_PLACE = 3;
	  public static final String BEGIN = "begin";
	  public static final int COL_BEGIN = 4;
	  public static final String END = "end";
	  public static final int COL_END = 5;
	  public static final String NOTE = "note";
	  public static final int COL_NOTE = 6;

	  public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	  public static final String TABLE_CREATE =
	    "CREATE TABLE " + TABLE_NAME + " (" +
	      KEY + " TEXT PRIMARY KEY, " +
	      DATE + " TEXT, " +
	      TITLE + " TEXT, " +
	      PLACE + " TEXT, " +
	      BEGIN + " TEXT, " +
	      END + "TEXT, " +
	      NOTE + "TEXT);";
	  
	  /**

	   * @param e l'event à ajouter à la base

	   */

	  public void ajouter(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(EventDAO.TITLE, e.getTitle());
		  value.put(EventDAO.BEGIN,e.getBegin());
		  value.put(EventDAO.DATE,e.getDate());
		  value.put(EventDAO.END,e.getEnd());
		  value.put(EventDAO.PLACE,e.getPlace());
		  value.put(EventDAO.NOTE,e.getNote());
		  mDb.insert(EventDAO.TABLE_NAME, null, value);
	  }


	  /**

	   * @param id l'identifiant de l'event à supprimer

	   */

	  public void supprimer(long id) {
		  mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	  }


	  /**

	   * @param e l'event modifié

	   */

	  public void modifierDate(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(DATE, e.getDate());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(e.getId())});
	  }
	  public void modifierTitle(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(TITLE, e.getTitle());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(e.getId())});
	  }
	  public void modifierBegin(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(BEGIN, e.getBegin());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(e.getId())});
	  }
	  public void modifierEnd(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(END, e.getEnd());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(e.getId())});
	  }
	  public void modifierPlace(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(PLACE, e.getPlace());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(e.getId())});
	  }
	  public void modifierNote(Event e) {
		  ContentValues value = new ContentValues();
		  value.put(NOTE, e.getNote());
		  mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(e.getId())});
	  }


	  /**

	   * @param date the date of wanted event

	   */

	  public Event[] getEventwithDate(String date) {
		  Cursor c = mDb.rawQuery("select " + TITLE + " from " + TABLE_NAME + " where date == ?", new String[]{date});
		  return cursortoevents(c);
	  }
	  
	  private Event[] cursortoevents(Cursor c){
		  if (c.getCount() == 0){
			  
				return null;
		  }
		  else{
			  Event[] event = new Event[c.getCount()];
			  for (int i=0;i<c.getCount();i++){
				  
			//Sinon on se place sur le premier élément
			c.moveToNext();			
			event[i].setId(c.getLong(COL_KEY));
			event[i].setDate(c.getString(COL_DATE));
			event[i].setTitle(c.getString(COL_TITLE));
			event[i].setPlace(c.getString(COL_PLACE));
			event[i].setBegin(c.getString(COL_BEGIN));
			event[i].setEnd(c.getString(COL_END));
			event[i].setNote(c.getString(COL_NOTE));
			c.close();
			  }
			return event;
		  }
			
		}
	  }
	  
