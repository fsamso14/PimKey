package com.example.pimkey.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.pimkey.Constantes;
import com.example.pimkey.R;
import com.example.pimkey.R.id;
import com.example.pimkey.R.layout;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MyDialogFragment extends DialogFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dialog_layout, container,
				false);
		
		getDialog().setTitle("Enter the new filter");		
		Button add= (Button) rootView.findViewById(R.id.BConfirm);
		 add.setOnClickListener(new OnClickListener(){      	
	        	public void onClick(View v) {
	        		EditText editText = (EditText) getDialog().findViewById(R.id.ETFilter);
	        		String filterType=MyDialogFragment.this.getTag();
	        		SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(getActivity());
	        		SharedPreferences.Editor editor=settings.edit();
	        		if(filterType=="domain"){
	        			
	        			Set<String> temp=settings.getStringSet(Constantes.DOMAIN_PREF,Constantes.DOMAIN_DEF);
	        			Set<String> temp2=settings.getStringSet(Constantes.PROJECT_PREF,Constantes.PROJECT_DEF);
	        			editor.clear();
	        			temp.add(editText.getText().toString());
	        			editor.putStringSet(Constantes.DOMAIN_PREF,temp);
	        			editor.putStringSet(Constantes.PROJECT_PREF,temp2);
	        			editor.apply();
	        		}
	        		else{
	        			Set<String> temp=settings.getStringSet(Constantes.DOMAIN_PREF,Constantes.DOMAIN_DEF);
	        			Set<String> temp2=settings.getStringSet(Constantes.PROJECT_PREF,Constantes.PROJECT_DEF);
	        			editor.clear();
	        			temp2.add(editText.getText().toString());
	        			editor.putStringSet(Constantes.DOMAIN_PREF,temp);
	        			editor.putStringSet(Constantes.PROJECT_PREF,temp2);
	        			editor.apply();
	        		}	        		
	        		FragmentManager fm = getFragmentManager();
					 SettingsFragment dFragment = new SettingsFragment();
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).commit();
					 MyDialogFragment.this.dismiss();
	        	}
		 });
		 Button cancel= (Button) rootView.findViewById(R.id.BCancel);
		 cancel.setOnClickListener(new OnClickListener(){      	
	        	public void onClick(View v) {
	        		FragmentManager fm = getFragmentManager();
					 SettingsFragment dFragment = new SettingsFragment();
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).commit();
					 MyDialogFragment.this.dismiss();
	        	}
		 });
		return rootView;
	}
}
