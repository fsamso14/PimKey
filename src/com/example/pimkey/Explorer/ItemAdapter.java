package com.example.pimkey.Explorer;

import java.util.List;

import com.example.pimkey.R;
import com.example.pimkey.item.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter{
	private List<Item> mListI;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public ItemAdapter(Context context, List<Item> aListI) {
		  mContext = context;
		  mListI = aListI;
		  mInflater = LayoutInflater.from(mContext);
		}

	@Override
	public int getCount() {
		return mListI.size();
	}

	@Override
	public Object getItem(int position) {
		return mListI.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  LinearLayout layoutItem;
		  if (convertView == null) {
		  	//Initialisation de notre item à partir du  layout XML "personne_layout.xml"
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.listlayout, null);
		  } else {
		  	layoutItem = (LinearLayout) convertView;
		  }
		  
		  //(2) : Récupération des TextView de notre layout      
		  TextView tv_Title = (TextView)layoutItem.findViewById(R.id.titre);
		  TextView tv_Description = (TextView)layoutItem.findViewById(R.id.description);
		  ImageView img=(ImageView) layoutItem.findViewById(R.id.img);
		  //(3) : Renseignement des valeurs       
		  tv_Title.setText(mListI.get(position).getTitle());
		  tv_Description.setText(mListI.get(position).getKeywords());
		  if(mListI.get(position).getType()==Item.typeContact){
			  img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_contact));
		  }
		  else{
			  if(mListI.get(position).getType()==Item.typeDoc){
				  img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doc));
			  }
			  else{
				  if(mListI.get(position).getType()==Item.typeTextnote){
					  img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_textnote));
				  }
				  else{
					  img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_website));
				  }
			  }
		  }	  
		 
		  return layoutItem;
		}

}
