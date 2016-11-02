package com.example.pimkey.Explorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.pimkey.R;
import com.example.pimkey.item.Item;
import com.example.pimkey.item.ItemRepository;
import com.example.pimkey.settings.MyDialogFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

public class FragmentExplorer extends Fragment {
	private boolean search;
	private List<Item> listI;
	private Spinner spin;
	private ItemRepository db;
	private ListView list;
	public FragmentExplorer(){
		this.search=false;
	}
	public FragmentExplorer(List<Item> listI,boolean search){
		this.search=search;
		this.listI=listI;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		db = new ItemRepository(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_explorer, container, false);
        list = (ListView) rootView.findViewById(R.id.LVItem);
        ImageButton add = (ImageButton) rootView.findViewById(R.id.IBAddItem);
        spin = (Spinner) rootView.findViewById(R.id.spinner1);
		List<String> selectionList = new ArrayList<String>();
		selectionList.add("Date");
		selectionList.add("Name");
		selectionList.add("Type");
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                 getActivity(),
                 android.R.layout.simple_spinner_item, 
                 selectionList
             );
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spin.setAdapter(adapter);
		 spin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position){
				case 0:
					ItemAdapter adapter3 = new ItemAdapter(getActivity(), listI);
				    
				    list.setAdapter(adapter3);
				    list.setOnItemClickListener(new OnItemClickListener() {
				    	@Override
				    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				    		Item test=(Item) parent.getItemAtPosition(position);
				    		int id2=test.getId();
				    		Log.d("Error test",""+id2);
				    		FragmentManager fm = getFragmentManager();
				        		ItemAdder dFragment = new ItemAdder(true,test);
				        		clearBackStack(fm);
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).addToBackStack(null).commit();
			 
			            }
				    });
					break;
					
				case 1:
					List<Item> listI2=trierparnom(listI);
					ItemAdapter adapter2 = new ItemAdapter(getActivity(), listI2);
				    
				    list.setAdapter(adapter2);
				    list.setOnItemClickListener(new OnItemClickListener() {
				    	@Override
				    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				    		Item test=(Item) parent.getItemAtPosition(position);
				    		int id2=test.getId();
				    		Log.d("Error test",""+id2);
				    		FragmentManager fm = getFragmentManager();
				        		ItemAdder dFragment = new ItemAdder(true,test);
				        		clearBackStack(fm);
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).addToBackStack("explorer").commit();
			 
			            }
				    });
					break;
					
				case 2:
					List<Item> listI3=trierpartype(listI);
					ItemAdapter adapter4 = new ItemAdapter(getActivity(), listI3);
				    
				    list.setAdapter(adapter4);
				    list.setOnItemClickListener(new OnItemClickListener() {
				    	@Override
				    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				    		Item test=(Item) parent.getItemAtPosition(position);
				    		int id2=test.getId();
				    		Log.d("Error test",""+id2);
				    		FragmentManager fm = getFragmentManager();
				        		ItemAdder dFragment = new ItemAdder(true,test);
				        		clearBackStack(fm);
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).addToBackStack("explorer").commit();
			 
			            }
				    });
					break;
				}
					
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		 add.setOnClickListener(new OnClickListener(){
			 public void onClick(View v){
				 FragmentManager fm = getFragmentManager();
				 DialogFragmentForItems dFragment = new DialogFragmentForItems();
				dFragment.show(fm, "add");
			 }
		 });
		 
		 if(!search){
			  db.Open();
			  listI = db.GetAll();
			  db.Close();
		 }		
		
		    ItemAdapter adapter2 = new ItemAdapter(getActivity(), listI);
		    
		    list.setAdapter(adapter2);
		    list.setOnItemClickListener(new OnItemClickListener() {
		    	@Override
		    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    		Item test=(Item) parent.getItemAtPosition(position);
		    		int id2=test.getId();
		    		Log.d("Error test",""+id2);
		    		FragmentManager fm = getFragmentManager();
		        		ItemAdder dFragment = new ItemAdder(true,test);
		        		clearBackStack(fm);
						 fm.beginTransaction()
		                    .replace(R.id.container,dFragment).addToBackStack("explorer").commit();
	 
	            }
		    });
          
        return rootView;
    }
	private void clearBackStack(FragmentManager manager) {
		for(int i=manager.getBackStackEntryCount();i>0;i--){
			FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
	         manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}
	private List<Item> trierparnom(List<Item> atrier){
		List<Item> aRet=new ArrayList<Item>();
		List<String> noms=new ArrayList<String>();
		for(int i=0;i< atrier.size();i++){
			noms.add(i,atrier.get(i).getTitle().toLowerCase());
			Log.v("test méthode",atrier.get(i).getTitle()+"//////"+i);
		}
		Collections.sort(noms);
		for(int i=0;i< atrier.size();i++){
			boolean test=true;
			Log.v("test méthode",noms.get(i)+"//////"+i);
			for(int j=0;j<atrier.size() && test;j++){
				if(atrier.get(j).getTitle().toLowerCase().equals(noms.get(i))){
					test=false;
					aRet.add(i,atrier.get(j));
					Log.v("test du if",aRet.get(i).getTitle()+"//////"+j);
				}
			}
		}
		Log.v("test méthode","nom");
		return aRet;
	}
	private List<Item> trierpartype(List<Item> atrier){
		List<Item> contact=db.getFromType(atrier,"Contact");
		List<Item> textnote=db.getFromType(atrier,"TextNote");
		List<Item> document=db.getFromType(atrier,"Document");
		List<Item> website=db.getFromType(atrier,"Website");
		List<Item> aRet=new ArrayList<Item>();
		 Log.v("test méthode","contact : "+contact.size()+" / document : "+document.size()+" / textnote : "+textnote.size()+" / website : "+website.size());
		 aRet.addAll(contact);
		 aRet.addAll(document);
		 aRet.addAll(textnote);
		 aRet.addAll(website);
		return aRet;
	}
	

}
