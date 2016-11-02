package com.example.pimkey.settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.example.pimkey.Constantes;
import com.example.pimkey.MainActivity;
import com.example.pimkey.R;
import com.example.pimkey.R.layout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class SettingsFragment extends Fragment {
	private Spinner spinDomain;
	private Spinner spinProject;
	private SharedPreferences settings;
	private SharedPreferences.Editor edit;
	public SettingsFragment(){}
	private List<String> projectList= new ArrayList<String>();
	private List<String> domainList= new ArrayList<String>();
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		//SharedPreferences settings=getActivity().getSharedPreferences(Constantes.SETTINGS,0);
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        settings=PreferenceManager.getDefaultSharedPreferences(getActivity());
        edit=settings.edit();
        ImageButton delDomain= (ImageButton) rootView.findViewById(R.id.IBDeleteDomain);
        ImageButton addDomain=(ImageButton) rootView.findViewById(R.id.IBAddDomain);
        spinDomain = (Spinner) rootView.findViewById(R.id.SSettingsDomain);
        
        Set<String> domainSet=settings.getStringSet(Constantes.DOMAIN_PREF, Constantes.DOMAIN_DEF);
        domainList.addAll(domainSet);
        Collections.sort(domainList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item, 
                domainList
            );
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spinDomain.setAdapter(adapter);
		 delDomain.setOnClickListener(new OnClickListener(){
	        	
	        	public void onClick(View v) {
	        		//SharedPreferences.Editor editor = settings.edit();
	        		Set<String> temp=settings.getStringSet(Constantes.DOMAIN_PREF, Constantes.DOMAIN_DEF);
	        		Set<String> temp2=settings.getStringSet(Constantes.PROJECT_PREF,Constantes.PROJECT_DEF);
	        		if(!temp.isEmpty()){
	        			temp.remove(spinDomain.getSelectedItem().toString());
	        			domainList.clear();
	        			domainList.addAll(temp);
	        			Collections.sort(domainList);
	        			edit.clear();
	        			edit.putStringSet(Constantes.DOMAIN_PREF, temp);
	        			edit.putStringSet(Constantes.PROJECT_PREF,temp2);
	        			edit.apply();
	        			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	        					getActivity(),
	        					android.R.layout.simple_spinner_item, 
	        					domainList
	        					);
	        			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        			spinDomain.setAdapter(adapter);
	        		}
	            }
	        });
		 addDomain.setOnClickListener(new OnClickListener(){
			 public void onClick(View v){
				FragmentManager fm = getFragmentManager();
				 MyDialogFragment dFragment = new MyDialogFragment();
					dFragment.show(fm, "domain");
			 }
		 });
		 ImageButton delProject= (ImageButton) rootView.findViewById(R.id.IBDelProject);
	        ImageButton addProject=(ImageButton) rootView.findViewById(R.id.IBAddProject);
	        spinProject = (Spinner) rootView.findViewById(R.id.SSettingsProject);
	        Set<String> ProjectSet=settings.getStringSet(Constantes.PROJECT_PREF, Constantes.PROJECT_DEF);
	        projectList.addAll(ProjectSet);
	        Collections.sort(projectList);
	        adapter = new ArrayAdapter<String>(
	                getActivity(),
	                android.R.layout.simple_spinner_item, 
	                projectList
	            );
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 spinProject.setAdapter(adapter);
			 delProject.setOnClickListener(new OnClickListener(){
		        	
		        	public void onClick(View v) {
		        		//SharedPreferences.Editor editor = settings.edit();
		        		Set<String> temp=settings.getStringSet(Constantes.DOMAIN_PREF, Constantes.DOMAIN_DEF);
		        		Set<String> temp2=settings.getStringSet(Constantes.PROJECT_PREF,Constantes.PROJECT_DEF);
		        		if(!temp2.isEmpty()){
		        			temp2.remove(spinProject.getSelectedItem().toString());
		        			projectList.clear();
		        			projectList.addAll(temp2);
		        			edit.clear();
		        			edit.putStringSet(Constantes.DOMAIN_PREF, temp);
		        			edit.putStringSet(Constantes.PROJECT_PREF,temp2);
		        			edit.apply();
		        			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		        					getActivity(),
		        					android.R.layout.simple_spinner_item, 
		        					projectList
		        					);
		        			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        			spinProject.setAdapter(adapter);
		        		}
		            }
		        });
			 addProject.setOnClickListener(new OnClickListener(){
				 public void onClick(View v){
					 FragmentManager fm = getFragmentManager();
					 MyDialogFragment dFragment = new MyDialogFragment();
						dFragment.show(fm, "project");
				 }
			 });
        return rootView;
    }
	@Override
	public void onStart(){	        
	        Set<String> domainSet=settings.getStringSet(Constantes.DOMAIN_PREF, Constantes.DOMAIN_DEF);
	        domainList.clear();
	        domainList.addAll(domainSet);
	        Collections.sort(domainList);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	                getActivity(),
	                android.R.layout.simple_spinner_item, 
	                domainList
	            );
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 spinDomain.setAdapter(adapter);
			 Set<String> ProjectSet=settings.getStringSet(Constantes.PROJECT_PREF, Constantes.PROJECT_DEF);
			 projectList.clear();
		        projectList.addAll(ProjectSet);
		        Collections.sort(projectList);
		        adapter = new ArrayAdapter<String>(
		                getActivity(),
		                android.R.layout.simple_spinner_item, 
		                projectList
		            );
				 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				 spinProject.setAdapter(adapter);
		super.onStart();
	}
}
	
