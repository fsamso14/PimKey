package com.example.pimkey.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.example.pimkey.Constantes;
import com.example.pimkey.R;
import com.example.pimkey.Explorer.FragmentExplorer;
import com.example.pimkey.item.Item;
import com.example.pimkey.item.ItemRepository;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchFragment extends Fragment {
	private List<Item> aRet;
	private ItemRepository db;
	private Spinner spinDomain;
	private Spinner spinProject;
	private Spinner spinDoc;
	private EditText ETK1;
	private EditText ETK2;
	public SearchFragment(){}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {		
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        spinDomain = (Spinner) rootView.findViewById(R.id.SDomain);
        spinProject= (Spinner) rootView.findViewById(R.id.SProject);
        spinDoc= (Spinner) rootView.findViewById(R.id.SDoc);
        ETK1=(EditText) rootView.findViewById(R.id.ETK1);
        ETK2=(EditText) rootView.findViewById(R.id.ETK2);
        SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(getActivity());	
        List<String> domainList= new ArrayList<String>();
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
		  List<String> projectList= new ArrayList<String>();
	        Set<String> projectSet=settings.getStringSet(Constantes.PROJECT_PREF, Constantes.PROJECT_DEF);
	        projectList.addAll(projectSet);
	        Collections.sort(projectList);
	        adapter = new ArrayAdapter<String>(
	                getActivity(),
	                android.R.layout.simple_spinner_item, 
	                projectList
	            );
			 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			 spinProject.setAdapter(adapter);
			  List<String> docList= new ArrayList<String>();
		        Set<String> docSet=settings.getStringSet(Constantes.DOC_PREF, Constantes.DOC_DEF);
		        docList.addAll(docSet);
		        adapter = new ArrayAdapter<String>(
		                getActivity(),
		                android.R.layout.simple_spinner_item, 
		                docList
		            );
				 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				 spinDoc.setAdapter(adapter);
				 
			db=new ItemRepository(getActivity());
			Button searchButton=(Button) rootView.findViewById(R.id.BTranscript);
			searchButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					db.Open();
					aRet=db.GetAll();
					
					aRet=db.getFromDomain(aRet,spinDomain.getSelectedItem().toString());
					
					aRet=db.getFromProject(aRet,spinProject.getSelectedItem().toString());
					
					String temp=ETK1.getText().toString()+" "+ETK2.getText().toString();
					String[] keywords=temp.split(" ");
					for(int i=0;i<keywords.length;i++){
						aRet=db.getFromKeyWords(aRet,keywords[i]);
						
					}
					aRet=db.getFromType(aRet,spinDoc.getSelectedItem().toString());
					
					db.Close();
					FragmentManager fm = getFragmentManager();
	        		FragmentExplorer dFragment = new FragmentExplorer(aRet,true);
	        		for(int i=fm.getBackStackEntryCount();i>0;i--){
	        			FragmentManager.BackStackEntry first = fm.getBackStackEntryAt(0);
	        	         fm.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
	        		}
					 fm.beginTransaction()
	                    .replace(R.id.container,dFragment).commit();
				}
			});
          
        return rootView;
    }
	

}
