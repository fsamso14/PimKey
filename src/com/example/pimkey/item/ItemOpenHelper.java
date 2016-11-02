package com.example.pimkey.item;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemOpenHelper extends SQLiteOpenHelper{
	public ItemOpenHelper(Context context,CursorFactory factory) {
		super(context,ITEM_BASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

		// Version de la base de données
		private static final int DATABASE_VERSION = 7;
		
		// Nom de la base
		private static final String ITEM_BASE_NAME = "item.db";
		
		// Nom de la table
		public static final String ITEM_TABLE_NAME = "Item";
		
		//Description des colonnes
		public static final String COLUMN_ID = "ID";
		public static final int NUM_COLUMN_ID = 0;
		public static final String COLUMN_TITLE = "TITLE";
		public static final int NUM_COLUMN_TITLE = 1;
		public static final String COLUMN_COMMENTS = "COMMENTS";
		public static final int NUM_COLUMN_COMMENTS = 2;
		public static final String COLUMN_DATES = "DATES";
		public static final int NUM_COLUMN_DATES = 3;
		public static final String COLUMN_DOMAIN = "DOMAIN";
		public static final int NUM_COLUMN_DOMAIN = 4;
		public static final String COLUMN_KEYWORDS = "KEYWORDS";
		public static final int NUM_COLUMN_KEYWORDS = 5;
		public static final String COLUMN_NOTE = "NOTE";
		public static final int NUM_COLUMN_NOTE = 6;
		public static final String COLUMN_TYPE = "TYPE";
		public static final int NUM_COLUMN_TYPE = 7;
		public static final String COLUMN_PROJECT = "PROJECT";
		public static final int NUM_COLUMN_PROJECT = 8;
		public static final String COLUMN_NAME = "NAME";
		public static final int NUM_COLUMN_NAME = 9;
		public static final String COLUMN_COMPANY = "COMPANY";
		public static final int NUM_COLUMN_COMPANY = 10;
		public static final String COLUMN_SERVICE = "SERVICE";
		public static final int NUM_COLUMN_SERVICE = 11;
		public static final String COLUMN_MOBILEPHONE = "MOBILE_PHONE";
		public static final int NUM_COLUMN_MOBILEPHONE = 12;
		public static final String COLUMN_EMAIL = "EMAIL";
		public static final int NUM_COLUMN_EMAIL = 13;
		public static final String COLUMN_OFFICETEL = "OFFICE_TEL";
		public static final int NUM_COLUMN_OFFICETEL = 14;
		public static final String COLUMN_OFFICEADDRESS = "OFFICE_ADDRESS";
		public static final int NUM_COLUMN_OFFICEADDRESS = 15;
		public static final String COLUMN_WEBSITE = "WEBSITE";
		public static final int NUM_COLUMN_WEBSITE = 16;
		public static final String COLUMN_LANGUAGE = "LANGUAGE";
		public static final int NUM_COLUMN_LANGUAGE = 17;
		public static final String COLUMN_SPOUSE = "SPOUSE";
		public static final int NUM_COLUMN_SPOUSE = 18;
		public static final String COLUMN_AUTHOR = "AUTHOR";
		public static final int NUM_COLUMN_AUTHOR = 19;
		public static final String COLUMN_TOREAD = "TOREAD";
		public static final int NUM_COLUMN_TOREAD = 20;
		public static final String COLUMN_INTEREST = "INTEREST";
		public static final int NUM_COLUMN_INTEREST = 21;
		public static final String COLUMN_CONTENTQUALITY = "CONTENT_QUALITY";
		public static final int NUM_COLUMN_CONTENTQUALITY = 22;
		public static final String COLUMN_WEBSITEQUALITY = "WEBSITE_QUALITY";
		public static final int NUM_COLUMN_WEBSITEQUALITY = 23;
		public static final String COLUMN_LOCATION = "LOCATION";
		public static final int NUM_COLUMN_LOCATION = 24;
		public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
		public static final int NUM_COLUMN_DESCRIPTION = 25;
		public static final String COLUMN_AUTHORTYPE = "AUTHOR_TYPE";
		public static final int NUM_COLUMN_AUTHORTYPE = 26;
		public static final String COLUMN_AUTHORNAME = "AUTHOR_NAME";
		public static final int NUM_COLUMN_AUTHORNAME = 27;
		public static final String COLUMN_AUTHORLOCATION = "AUTHOR_LOCATION";
		public static final int NUM_COLUMN_AUTHORLOCATION = 28;
		public static final String COLUMN_PUBLICTYPE = "PUBLIC_TYPE";
		public static final int NUM_COLUMN_PUBLICTYPE = 29;
		public static final String COLUMN_TRANSCRIPT="TRANSCRIPT";
		public static final int NUM_COLUMN_TRANSCRIPT = 30;
		
		// Requête SQL pour la création da la base
		private static final String REQUETE_CREATION_BDD = "CREATE TABLE "
				+ ITEM_TABLE_NAME + " (" + COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE
				+ " TEXT NOT NULL, " + COLUMN_COMMENTS + " TEXT, "
				+ COLUMN_DATES + " INTEGER NOT NULL, "
				+ COLUMN_DOMAIN + " TEXT, "
				+ COLUMN_KEYWORDS + " TEXT, "
				+ COLUMN_NOTE + " TEXT, "
				+ COLUMN_TYPE + " INTEGER NOT NULL, "
				+ COLUMN_PROJECT + " TEXT, "
				+ COLUMN_NAME + " TEXT, "
				+ COLUMN_COMPANY + " TEXT, "
				+ COLUMN_SERVICE + " TEXT, "
				+ COLUMN_MOBILEPHONE + " TEXT, "
				+ COLUMN_EMAIL + " TEXT, "
				+ COLUMN_OFFICETEL + " TEXT, "
				+ COLUMN_OFFICEADDRESS + " TEXT, "
				+ COLUMN_WEBSITE + " TEXT, "
				+ COLUMN_LANGUAGE + " TEXT, "
				+ COLUMN_SPOUSE + " TEXT, "
				+ COLUMN_AUTHOR + " TEXT, "
				+ COLUMN_TOREAD + " TEXT, "
				+ COLUMN_INTEREST + " TEXT, "
				+ COLUMN_CONTENTQUALITY + " TEXT, "
				+ COLUMN_WEBSITEQUALITY + " TEXT, "
				+ COLUMN_LOCATION + " TEXT, "
				+ COLUMN_DESCRIPTION + " TEXT, "
				+ COLUMN_AUTHORTYPE + " TEXT, "
				+ COLUMN_AUTHORNAME + " TEXT, "
				+ COLUMN_AUTHORLOCATION + " TEXT, "
				+ COLUMN_PUBLICTYPE + " TEXT, "
				+ COLUMN_TRANSCRIPT + " TEXT);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BDD);

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > DATABASE_VERSION) {
			db.execSQL("DROP TABLE " + ITEM_TABLE_NAME + ";");
			onCreate(db);
		}
		
	}

}
