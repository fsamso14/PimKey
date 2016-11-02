package com.example.pimkey.Explorer;


import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.pimkey.R;
import com.example.pimkey.item.Item;

public class DialogFragmentForItems extends DialogFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dialogadditem_layout, container,
				false);
		
		getDialog().setTitle("What do you want to add ?");		
		Button addDoc= (Button) rootView.findViewById(R.id.BAddDoc);
		 addDoc.setOnClickListener(new OnClickListener(){      	
	        	public void onClick(View v) {
	        		       		
	        		FragmentManager fm = getFragmentManager();
	        		ItemAdder dFragment = new ItemAdder(Item.typeDoc);
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).addToBackStack(null).commit();
					 DialogFragmentForItems.this.dismiss();
	        	}
		 });
		 Button addTextNote= (Button) rootView.findViewById(R.id.BAddTextNote);
		 addTextNote.setOnClickListener(new OnClickListener(){      	
	        	public void onClick(View v) {
	        		       		
	        		FragmentManager fm = getFragmentManager();
					 ItemAdder dFragment = new ItemAdder(Item.typeTextnote);
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).addToBackStack(null).commit();
					 DialogFragmentForItems.this.dismiss();
	        	}
		 });
		 Button addContact= (Button) rootView.findViewById(R.id.BAddContact);
		 addContact.setOnClickListener(new OnClickListener(){      	
	        	public void onClick(View v) {
	        		       		
	        		FragmentManager fm = getFragmentManager();
	        		ItemAdder dFragment = new ItemAdder(Item.typeContact);
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).addToBackStack(null).commit();
					 DialogFragmentForItems.this.dismiss();
	        	}
		 });
		 Button addWebsite= (Button) rootView.findViewById(R.id.BAddWebsite);
		 addWebsite.setOnClickListener(new OnClickListener(){      	
	        	public void onClick(View v) {
	        		       		
	        		FragmentManager fm = getFragmentManager();
	        		ItemAdder dFragment = new ItemAdder(Item.typeWebsite);
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).addToBackStack(null).commit();
					 DialogFragmentForItems.this.dismiss();
	        	}
		 });
		return rootView;
	}
}
