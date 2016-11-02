package com.example.pimkey.item;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class ItemRepository extends Repository<Item>{
	
	public ItemRepository(Context c){
		this.sqLiteOpenHelper= new ItemOpenHelper(c,null);
	}
	
	public void DeleteProduit(int id) {
		maBDD.delete(ItemOpenHelper.ITEM_TABLE_NAME,
				ItemOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	@Override
	public List<Item> GetAll() {
		Cursor cursor = maBDD.query(ItemOpenHelper.ITEM_TABLE_NAME,
				new String[] { ItemOpenHelper.COLUMN_ID,
						ItemOpenHelper.COLUMN_TITLE,
						ItemOpenHelper.COLUMN_COMMENTS,
						ItemOpenHelper.COLUMN_DATES,
						ItemOpenHelper.COLUMN_DOMAIN,
						ItemOpenHelper.COLUMN_KEYWORDS,
						ItemOpenHelper.COLUMN_NOTE,
						ItemOpenHelper.COLUMN_TYPE,
						ItemOpenHelper.COLUMN_PROJECT,
						ItemOpenHelper.COLUMN_NAME,
						ItemOpenHelper.COLUMN_COMPANY,
						ItemOpenHelper.COLUMN_SERVICE,
						ItemOpenHelper.COLUMN_MOBILEPHONE,
						ItemOpenHelper.COLUMN_EMAIL,
						ItemOpenHelper.COLUMN_OFFICETEL,
						ItemOpenHelper.COLUMN_OFFICEADDRESS,
						ItemOpenHelper.COLUMN_WEBSITE,
						ItemOpenHelper.COLUMN_LANGUAGE,
						ItemOpenHelper.COLUMN_SPOUSE,
						ItemOpenHelper.COLUMN_AUTHOR,
						ItemOpenHelper.COLUMN_TOREAD,
						ItemOpenHelper.COLUMN_INTEREST,
						ItemOpenHelper.COLUMN_CONTENTQUALITY,
						ItemOpenHelper.COLUMN_WEBSITEQUALITY,
						ItemOpenHelper.COLUMN_LOCATION,
						ItemOpenHelper.COLUMN_DESCRIPTION,
						ItemOpenHelper.COLUMN_AUTHORTYPE,
						ItemOpenHelper.COLUMN_AUTHORNAME,
						ItemOpenHelper.COLUMN_AUTHORLOCATION,
						ItemOpenHelper.COLUMN_PUBLICTYPE,
						ItemOpenHelper.COLUMN_TRANSCRIPT
						}, null, null, null,
				null, null);
 
		return ConvertCursorToListObject(cursor);
	}

	@Override
	public Item GetById(int id) {
		Cursor cursor = maBDD.query(ItemOpenHelper.ITEM_TABLE_NAME,
				new String[] { ItemOpenHelper.COLUMN_ID,
				ItemOpenHelper.COLUMN_TITLE,
				ItemOpenHelper.COLUMN_COMMENTS,
				ItemOpenHelper.COLUMN_DATES,
				ItemOpenHelper.COLUMN_DOMAIN,
				ItemOpenHelper.COLUMN_KEYWORDS,
				ItemOpenHelper.COLUMN_NOTE,
				ItemOpenHelper.COLUMN_TYPE,
				ItemOpenHelper.COLUMN_PROJECT,
				ItemOpenHelper.COLUMN_NAME,
				ItemOpenHelper.COLUMN_COMPANY,
				ItemOpenHelper.COLUMN_SERVICE,
				ItemOpenHelper.COLUMN_MOBILEPHONE,
				ItemOpenHelper.COLUMN_EMAIL,
				ItemOpenHelper.COLUMN_OFFICETEL,
				ItemOpenHelper.COLUMN_OFFICEADDRESS,
				ItemOpenHelper.COLUMN_WEBSITE,
				ItemOpenHelper.COLUMN_LANGUAGE,
				ItemOpenHelper.COLUMN_SPOUSE,
				ItemOpenHelper.COLUMN_AUTHOR,
				ItemOpenHelper.COLUMN_TOREAD,
				ItemOpenHelper.COLUMN_INTEREST,
				ItemOpenHelper.COLUMN_CONTENTQUALITY,
				ItemOpenHelper.COLUMN_WEBSITEQUALITY,
				ItemOpenHelper.COLUMN_LOCATION,
				ItemOpenHelper.COLUMN_DESCRIPTION,
				ItemOpenHelper.COLUMN_AUTHORTYPE,
				ItemOpenHelper.COLUMN_AUTHORNAME,
				ItemOpenHelper.COLUMN_AUTHORLOCATION,
				ItemOpenHelper.COLUMN_PUBLICTYPE,
				ItemOpenHelper.COLUMN_TRANSCRIPT
				},ItemOpenHelper.COLUMN_ID +"="+id,
				null, null, null, null);
 
		return ConvertCursorToOneObject(cursor);
	}

	@Override
	public void Save(Item entite) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ItemOpenHelper.COLUMN_TITLE, entite.getTitle());
		contentValues.put(ItemOpenHelper.COLUMN_COMMENTS, entite.getComments());
		contentValues.put(ItemOpenHelper.COLUMN_DATES, entite.getDates());
		contentValues.put(ItemOpenHelper.COLUMN_DOMAIN, entite.getDomain());
		contentValues.put(ItemOpenHelper.COLUMN_KEYWORDS, entite.getKeywords());
		contentValues.put(ItemOpenHelper.COLUMN_NOTE, entite.getNote());
		contentValues.put(ItemOpenHelper.COLUMN_TYPE, entite.getType());
		contentValues.put(ItemOpenHelper.COLUMN_PROJECT, entite.getproject());
		contentValues.put(ItemOpenHelper.COLUMN_NAME, entite.getName());
		contentValues.put(ItemOpenHelper.COLUMN_COMPANY, entite.getCompany());
		contentValues.put(ItemOpenHelper.COLUMN_SERVICE, entite.getService());
		contentValues.put(ItemOpenHelper.COLUMN_MOBILEPHONE, entite.getMobilePhone());
		contentValues.put(ItemOpenHelper.COLUMN_EMAIL, entite.geteMail());
		contentValues.put(ItemOpenHelper.COLUMN_OFFICETEL, entite.getOfficeTel());
		contentValues.put(ItemOpenHelper.COLUMN_OFFICEADDRESS, entite.getOfficeAddress());
		contentValues.put(ItemOpenHelper.COLUMN_WEBSITE, entite.getWebsite());
		contentValues.put(ItemOpenHelper.COLUMN_LANGUAGE, entite.getLanguage());
		contentValues.put(ItemOpenHelper.COLUMN_SPOUSE, entite.getSpouse());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHOR, entite.getAuthor());
		contentValues.put(ItemOpenHelper.COLUMN_TOREAD, entite.getToRead());
		contentValues.put(ItemOpenHelper.COLUMN_INTEREST, entite.getInterest());
		contentValues.put(ItemOpenHelper.COLUMN_CONTENTQUALITY, entite.getContentQuality());
		contentValues.put(ItemOpenHelper.COLUMN_WEBSITEQUALITY, entite.getWebsiteQuality());
		contentValues.put(ItemOpenHelper.COLUMN_LOCATION, entite.getLocation());
		contentValues.put(ItemOpenHelper.COLUMN_DESCRIPTION, entite.getDescription());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHORTYPE, entite.getAuthorType());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHORNAME, entite.getAuthorName());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHORLOCATION, entite.getAuthorLocation());
		contentValues.put(ItemOpenHelper.COLUMN_PUBLICTYPE, entite.getPublicType());
		contentValues.put(ItemOpenHelper.COLUMN_TRANSCRIPT,entite.getTranscript());
		maBDD.insert(ItemOpenHelper.ITEM_TABLE_NAME, null, contentValues);
		
	}

	@Override
	public void Update(Item entite) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ItemOpenHelper.COLUMN_TITLE, entite.getTitle());
		contentValues.put(ItemOpenHelper.COLUMN_COMMENTS, entite.getComments());
		contentValues.put(ItemOpenHelper.COLUMN_DATES, entite.getDates());
		contentValues.put(ItemOpenHelper.COLUMN_DOMAIN, entite.getDomain());
		contentValues.put(ItemOpenHelper.COLUMN_KEYWORDS, entite.getKeywords());
		contentValues.put(ItemOpenHelper.COLUMN_NOTE, entite.getNote());
		contentValues.put(ItemOpenHelper.COLUMN_TYPE, entite.getType());
		contentValues.put(ItemOpenHelper.COLUMN_PROJECT, entite.getproject());
		contentValues.put(ItemOpenHelper.COLUMN_NAME, entite.getName());
		contentValues.put(ItemOpenHelper.COLUMN_COMPANY, entite.getCompany());
		contentValues.put(ItemOpenHelper.COLUMN_SERVICE, entite.getService());
		contentValues.put(ItemOpenHelper.COLUMN_MOBILEPHONE, entite.getMobilePhone());
		contentValues.put(ItemOpenHelper.COLUMN_EMAIL, entite.geteMail());
		contentValues.put(ItemOpenHelper.COLUMN_OFFICETEL, entite.getOfficeTel());
		contentValues.put(ItemOpenHelper.COLUMN_OFFICEADDRESS, entite.getOfficeAddress());
		contentValues.put(ItemOpenHelper.COLUMN_WEBSITE, entite.getWebsite());
		contentValues.put(ItemOpenHelper.COLUMN_LANGUAGE, entite.getLanguage());
		contentValues.put(ItemOpenHelper.COLUMN_SPOUSE, entite.getSpouse());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHOR, entite.getAuthor());
		contentValues.put(ItemOpenHelper.COLUMN_TOREAD, entite.getToRead());
		contentValues.put(ItemOpenHelper.COLUMN_INTEREST, entite.getInterest());
		contentValues.put(ItemOpenHelper.COLUMN_CONTENTQUALITY, entite.getContentQuality());
		contentValues.put(ItemOpenHelper.COLUMN_WEBSITEQUALITY, entite.getWebsiteQuality());
		contentValues.put(ItemOpenHelper.COLUMN_LOCATION, entite.getLocation());
		contentValues.put(ItemOpenHelper.COLUMN_DESCRIPTION, entite.getDescription());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHORTYPE, entite.getAuthorType());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHORNAME, entite.getAuthorName());
		contentValues.put(ItemOpenHelper.COLUMN_AUTHORLOCATION, entite.getAuthorLocation());
		contentValues.put(ItemOpenHelper.COLUMN_PUBLICTYPE, entite.getPublicType());
		contentValues.put(ItemOpenHelper.COLUMN_TRANSCRIPT,entite.getTranscript());
		maBDD.update(ItemOpenHelper.ITEM_TABLE_NAME, contentValues,
				ItemOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(entite.getId()) });
		
	}

	@Override
	public void Delete(int id) {
		maBDD.delete(ItemOpenHelper.ITEM_TABLE_NAME,
				ItemOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	@Override
	public List<Item> ConvertCursorToListObject(Cursor c) {
		List<Item> liste = new ArrayList<Item>();
		 
		// Si la liste est vide
		if (c.getCount() == 0)
			return liste;
 
		// position sur le premeir item
		c.moveToLast();
 
		// Pour chaque item
		do {
 
			Item item = ConvertCursorToObject(c);
 
			liste.add(item);
		} while (c.moveToPrevious());
 
		// Fermeture du curseur
		c.close();
 
		return liste;
	}

	@Override
	public Item ConvertCursorToObject(Cursor c) {
		Item item = new Item(c.getString(ItemOpenHelper.NUM_COLUMN_TITLE),
				c.getString(ItemOpenHelper.NUM_COLUMN_COMMENTS),
				c.getString(ItemOpenHelper.NUM_COLUMN_DATES),
				c.getString(ItemOpenHelper.NUM_COLUMN_DOMAIN),
				c.getString(ItemOpenHelper.NUM_COLUMN_KEYWORDS),
				c.getString(ItemOpenHelper.NUM_COLUMN_NOTE),
				c.getInt(ItemOpenHelper.NUM_COLUMN_TYPE),
				c.getString(ItemOpenHelper.NUM_COLUMN_PROJECT),
				c.getString(ItemOpenHelper.NUM_COLUMN_NAME),
				c.getString(ItemOpenHelper.NUM_COLUMN_COMPANY),
				c.getString(ItemOpenHelper.NUM_COLUMN_SERVICE),
				c.getString(ItemOpenHelper.NUM_COLUMN_MOBILEPHONE),
				c.getString(ItemOpenHelper.NUM_COLUMN_EMAIL),
				c.getString(ItemOpenHelper.NUM_COLUMN_OFFICETEL),
				c.getString(ItemOpenHelper.NUM_COLUMN_OFFICEADDRESS),
				null,
				null,
				c.getString(ItemOpenHelper.NUM_COLUMN_WEBSITE),
				c.getString(ItemOpenHelper.NUM_COLUMN_SPOUSE),
				c.getString(ItemOpenHelper.NUM_COLUMN_AUTHOR),
				c.getString(ItemOpenHelper.NUM_COLUMN_TOREAD),
				c.getString(ItemOpenHelper.NUM_COLUMN_INTEREST),
				c.getString(ItemOpenHelper.NUM_COLUMN_CONTENTQUALITY),
				c.getString(ItemOpenHelper.NUM_COLUMN_WEBSITEQUALITY),
				c.getString(ItemOpenHelper.NUM_COLUMN_LANGUAGE),
				c.getString(ItemOpenHelper.NUM_COLUMN_LOCATION),
				c.getString(ItemOpenHelper.NUM_COLUMN_DESCRIPTION),
				c.getString(ItemOpenHelper.NUM_COLUMN_AUTHORTYPE),
				c.getString(ItemOpenHelper.NUM_COLUMN_AUTHORNAME),
				c.getString(ItemOpenHelper.NUM_COLUMN_AUTHORLOCATION),
				c.getString(ItemOpenHelper.NUM_COLUMN_PUBLICTYPE),
				c.getString(ItemOpenHelper.NUM_COLUMN_TRANSCRIPT));
		item.setId(c.getInt(ItemOpenHelper.NUM_COLUMN_ID));
		return item;
	}

	@Override
	public Item ConvertCursorToOneObject(Cursor c) {
		Log.d("Taille du curseur",""+c.getCount());
		c.moveToFirst();
		 
		Item item = ConvertCursorToObject(c);
 
		c.close();
		return item;
	}
	public List<Item> getContact(List<Item> temp){
		List<Item> temp2 = new ArrayList<Item>();
		for(int i=0;i< temp.size();i++){
			if(temp.get(i).getType()==Item.typeContact){
				temp2.add(temp.get(i));
			}
		}
		return temp2;
	}
	public List<Item> getDoc(List<Item> temp){
		List<Item> temp2 = new ArrayList<Item>();
		for(int i=0;i< temp.size();i++){
			if(temp.get(i).getType()==Item.typeDoc){
				temp2.add(temp.get(i));
			}
		}
		return temp2;
	}
	public List<Item> getTextNote(List<Item> temp){
		List<Item> temp2 = new ArrayList<Item>();
		for(int i=0;i< temp.size();i++){
			if(temp.get(i).getType()==Item.typeTextnote){
				temp2.add(temp.get(i));
			}
		}
		return temp2;
	}
	public List<Item> getWebsite(List<Item> temp){
		List<Item> temp2 = new ArrayList<Item>();
		for(int i=0;i< temp.size();i++){
			if(temp.get(i).getType()==Item.typeWebsite){
				temp2.add(temp.get(i));
			}
		}
		return temp2;
	}
	public List<Item> getFromDomain(List<Item> temp,String domain){
		if(!domain.isEmpty()){
				List<Item> temp2 = new ArrayList<Item>();
			for(int i=0;i< temp.size();i++){
				if(temp.get(i).getDomain().contains(domain)){
					temp2.add(temp.get(i));
				}
			}
				return temp2;
		}
		else{
			return temp;
		}
				
	}
	public List<Item> getFromProject(List<Item> temp,String project){
		if(!project.isEmpty()){
			List<Item> temp2 = new ArrayList<Item>();
			for(int i=0;i< temp.size();i++){
				if(temp.get(i).getproject().contains(project)){
					temp2.add(temp.get(i));
				}
			}
			return temp2;
		}
		else{
			return temp;
		}
		
	}
	public List<Item> getFromKeyWords(List<Item> temp,String keywords){
		if(!keywords.isEmpty()){
			List<Item> temp2 = new ArrayList<Item>();
			for(int i=0;i< temp.size();i++){
				if(temp.get(i).getKeywords().contains(keywords)){
					temp2.add(temp.get(i));
				}
			}
			return temp2;
		}
		else{
			return temp;
		}
	}
	public List<Item> getFromType(List<Item> temp,String type){
		if(type.isEmpty()){
			return temp;
		}
		else{
			List<Item> temp2 = new ArrayList<Item>();
			if(type.contains("Con")){
				for(int i=0;i<temp.size();i++){
					if(temp.get(i).getType()==Item.typeContact){
						temp2.add(temp.get(i));
					}
				}
			}
			if(type.contains("Doc")){
				for(int i=0;i<temp.size();i++){
					if(temp.get(i).getType()==Item.typeDoc){
						temp2.add(temp.get(i));
					}
				}
			}
			if(type.contains("Web")){
				for(int i=0;i<temp.size();i++){
					if(temp.get(i).getType()==Item.typeWebsite){
						temp2.add(temp.get(i));
					}
				}
			}
			if(type.contains("Tex")){
				for(int i=0;i<temp.size();i++){
					if(temp.get(i).getType()==Item.typeTextnote){
						temp2.add(temp.get(i));
					}
				}
			}
			return temp2;
		}
		
	}
}
