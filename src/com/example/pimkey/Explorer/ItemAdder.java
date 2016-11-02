package com.example.pimkey.Explorer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.example.pimkey.Constantes;
import com.example.pimkey.MainActivity;
import com.example.pimkey.NavigationDrawerFragment;
import com.example.pimkey.OnSwipeTouchListener;
import com.example.pimkey.R;
import com.example.pimkey.item.Item;
import com.example.pimkey.item.ItemRepository;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ItemAdder extends Fragment {
	private Button bNote;
	private Button bProp;
	private Button bComments;
	private Button bTranscript;
	private LinearLayout llprop;
	private LinearLayout llnote;
	private LinearLayout llcomments;
	private LinearLayout lltranscript;
	private int selection=4;
	private boolean update;
	private boolean shared;
	private int type;
	private EditText title;
	private EditText comments;
	private String dates;
	private Spinner  spinDomain;
	private EditText keywords;
	private EditText note;
	private int id;
	private Spinner spinProject;
	private EditText name;
	private EditText company;
	private EditText service;
	private EditText mobilePhone;
	private EditText eMail;
	private EditText officeTel;
	private EditText officeAddress;
	private EditText homeTel;
	private EditText homeAddress;
	private EditText website;
	private EditText spouse;
	private EditText author;
	private EditText toRead;
	private EditText interest;
	private EditText contentQuality;
	private EditText websiteQuality;
	private EditText language;
	private EditText location;
	private EditText description;
	private EditText authorType;
	private EditText authorName;
	private EditText authorLocation;
	private EditText publicType;
	private EditText transcript;
	private ItemRepository db;
	private Calendar rightNow = Calendar.getInstance();
	private Item item;
	private LinearLayout llgeneral;
	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	public ItemAdder(int type){
		this.type=type;
		this.update=false;
		this.shared=false;
	}
	public ItemAdder(boolean update,Item item){
		this.update=update;
		this.item=item;
		this.shared=false;
		this.type=item.getType();
	}
	public ItemAdder(boolean update, boolean shared,Item item){
		this.update=update;
		this.item=item;
		this.shared=shared;
		this.type=item.getType();
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Do you want to delete ?");
		builder.setTitle("Confirm");
		selection=4;
		db = new ItemRepository(getActivity());  
        SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(getActivity());
        View rootView = inflater.inflate(R.layout.itemadder_layout, container, false);
        
        //Partie commune à tous les éléments
        title=(EditText) rootView.findViewById(R.id.ETTitle);
        comments=(EditText) rootView.findViewById(R.id.ETComments);
        
        spinDomain=(Spinner) rootView.findViewById(R.id.SDomain);	
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
		if (item.getDomain()==null){
			spinDomain.setSelection(0);
		}
		else{
			boolean temp=false;
			for(int i=0;i<spinDomain.getCount() && !temp;i++){
				if(((String)spinDomain.getItemAtPosition(i)).contains(item.getDomain())){
					temp=true;
					spinDomain.setSelection(i);
					//Log.v("Test spindomain", item.getDomain()+"//"+spinDomain.getItemAtPosition(i));
				}
			}
		}
		 keywords=(EditText) rootView.findViewById(R.id.ETKeywords);
		 note=(EditText) rootView.findViewById(R.id.ETNote); 
		 transcript =(EditText) rootView.findViewById(R.id.ETTranscript);
		 spinProject=(Spinner) rootView.findViewById(R.id.SProjet);
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
				 if (item.getproject()==null){
						spinProject.setSelection(0);
					}
					else{
						boolean temp=false;
						for(int i=0;i<spinProject.getCount() && !temp;i++){
							if(((String)spinProject.getItemAtPosition(i)).contains(item.getproject())){
								//Log.v("Test spinproject", item.getproject()+"//"+spinProject.getItemAtPosition(i));
								temp=true;
								spinProject.setSelection(i);
							}
						}
					}
		Button save=(Button) rootView.findViewById(R.id.BConfirm);
		llprop=(LinearLayout) rootView.findViewById(R.id.LLProp);
		llnote=(LinearLayout) rootView.findViewById(R.id.LLNote);
		llcomments=(LinearLayout) rootView.findViewById(R.id.LLComents);
		lltranscript=(LinearLayout) rootView.findViewById(R.id.LLTranscript);
		llnote.setVisibility(View.GONE);
		llcomments.setVisibility(View.GONE);
		lltranscript.setVisibility(View.GONE);
		bNote =(Button) rootView.findViewById(R.id.BNote);
		bProp =(Button) rootView.findViewById(R.id.BProp);
		bComments=(Button) rootView.findViewById(R.id.BComments);
		bTranscript = (Button) rootView.findViewById(R.id.BTranscript);
		bNote.setBackgroundColor(Color.parseColor("#808080"));
		bComments.setBackgroundColor(Color.parseColor("#808080"));
		bTranscript.setBackgroundColor(Color.parseColor("#808080"));
		
		bProp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!(selection%4==0)){
					bProp.setBackgroundResource(android.R.drawable.btn_default);
					bNote.setBackgroundColor(Color.parseColor("#808080"));
					bComments.setBackgroundColor(Color.parseColor("#808080"));
					bTranscript.setBackgroundColor(Color.parseColor("#808080"));
					selection=4;
					llprop.setVisibility(View.VISIBLE);
					llnote.setVisibility(View.GONE);
					llcomments.setVisibility(View.GONE);
					lltranscript.setVisibility(View.GONE);
				}
				
			}
			
		});
		
		bComments.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!(selection%4==2)){
					bComments.setBackgroundResource(android.R.drawable.btn_default);
					bNote.setBackgroundColor(Color.parseColor("#808080"));
					bProp.setBackgroundColor(Color.parseColor("#808080"));
					bTranscript.setBackgroundColor(Color.parseColor("#808080"));
					selection=2;
					llprop.setVisibility(View.GONE);
					llnote.setVisibility(View.GONE);
					llcomments.setVisibility(View.VISIBLE);
					lltranscript.setVisibility(View.GONE);
				}
				
			}
			
		});
		
		bNote.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!(selection%4==1)){
					bNote.setBackgroundResource(android.R.drawable.btn_default);
					bProp.setBackgroundColor(Color.parseColor("#808080"));
					bComments.setBackgroundColor(Color.parseColor("#808080"));
					bTranscript.setBackgroundColor(Color.parseColor("#808080"));
					selection=1;
					llprop.setVisibility(View.GONE);
					llnote.setVisibility(View.VISIBLE);
					llcomments.setVisibility(View.GONE);
					lltranscript.setVisibility(View.GONE);
				}
				
			}
			
		});
		
		bTranscript.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!(selection%4==3)){
					bTranscript.setBackgroundResource(android.R.drawable.btn_default);
					bNote.setBackgroundColor(Color.parseColor("#808080"));
					bComments.setBackgroundColor(Color.parseColor("#808080"));
					bProp.setBackgroundColor(Color.parseColor("#808080"));
					selection=3;
					llprop.setVisibility(View.GONE);
					llnote.setVisibility(View.GONE);
					llcomments.setVisibility(View.GONE);
					lltranscript.setVisibility(View.VISIBLE);
				}
				
			}
			
		});
		
		llgeneral=(LinearLayout) rootView.findViewById(R.id.LLGeneral);
		llgeneral.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
			 public void onSwipeTop() {
			    }
			    public void onSwipeRight() {
			       selection=selection-1;
			       if(selection%4==0){
			    	   bProp.setBackgroundResource(android.R.drawable.btn_default);
						bNote.setBackgroundColor(Color.parseColor("#808080"));
						bComments.setBackgroundColor(Color.parseColor("#808080"));
						bTranscript.setBackgroundColor(Color.parseColor("#808080"));
						selection=4;
						llprop.setVisibility(View.VISIBLE);
						llnote.setVisibility(View.GONE);
						llcomments.setVisibility(View.GONE);
						lltranscript.setVisibility(View.GONE);
			       }
			       else if(selection%4==1){
			    	   bNote.setBackgroundResource(android.R.drawable.btn_default);
						bProp.setBackgroundColor(Color.parseColor("#808080"));
						bComments.setBackgroundColor(Color.parseColor("#808080"));
						bTranscript.setBackgroundColor(Color.parseColor("#808080"));
						selection=1;
						llprop.setVisibility(View.GONE);
						llnote.setVisibility(View.VISIBLE);
						llcomments.setVisibility(View.GONE);
						lltranscript.setVisibility(View.GONE);
			       }
			       else if(selection%4==2){
			    	   bComments.setBackgroundResource(android.R.drawable.btn_default);
						bNote.setBackgroundColor(Color.parseColor("#808080"));
						bProp.setBackgroundColor(Color.parseColor("#808080"));
						bTranscript.setBackgroundColor(Color.parseColor("#808080"));
						selection=2;
						llprop.setVisibility(View.GONE);
						llnote.setVisibility(View.GONE);
						llcomments.setVisibility(View.VISIBLE);
						lltranscript.setVisibility(View.GONE);
			       }
			       else if(selection%4==3){
			    	   bTranscript.setBackgroundResource(android.R.drawable.btn_default);
						bNote.setBackgroundColor(Color.parseColor("#808080"));
						bComments.setBackgroundColor(Color.parseColor("#808080"));
						bProp.setBackgroundColor(Color.parseColor("#808080"));
						selection=3;
						llprop.setVisibility(View.GONE);
						llnote.setVisibility(View.GONE);
						llcomments.setVisibility(View.GONE);
						lltranscript.setVisibility(View.VISIBLE);
			       }
			       
			    }
			    public void onSwipeLeft() {
			    	selection=selection+1;
				       if(selection%4==0){
				    	   bProp.setBackgroundResource(android.R.drawable.btn_default);
							bNote.setBackgroundColor(Color.parseColor("#808080"));
							bComments.setBackgroundColor(Color.parseColor("#808080"));
							bTranscript.setBackgroundColor(Color.parseColor("#808080"));
							selection=4;
							llprop.setVisibility(View.VISIBLE);
							llnote.setVisibility(View.GONE);
							llcomments.setVisibility(View.GONE);
							lltranscript.setVisibility(View.GONE);
				       }
				       else if(selection%4==1){
				    	   bNote.setBackgroundResource(android.R.drawable.btn_default);
							bProp.setBackgroundColor(Color.parseColor("#808080"));
							bComments.setBackgroundColor(Color.parseColor("#808080"));
							bTranscript.setBackgroundColor(Color.parseColor("#808080"));
							selection=1;
							llprop.setVisibility(View.GONE);
							llnote.setVisibility(View.VISIBLE);
							llcomments.setVisibility(View.GONE);
							lltranscript.setVisibility(View.GONE);
				       }
				       else if(selection%4==2){
				    	   bComments.setBackgroundResource(android.R.drawable.btn_default);
							bNote.setBackgroundColor(Color.parseColor("#808080"));
							bProp.setBackgroundColor(Color.parseColor("#808080"));
							bTranscript.setBackgroundColor(Color.parseColor("#808080"));
							selection=2;
							llprop.setVisibility(View.GONE);
							llnote.setVisibility(View.GONE);
							llcomments.setVisibility(View.VISIBLE);
							lltranscript.setVisibility(View.GONE);
				       }
				       else if(selection%4==3){
				    	   bTranscript.setBackgroundResource(android.R.drawable.btn_default);
							bNote.setBackgroundColor(Color.parseColor("#808080"));
							bComments.setBackgroundColor(Color.parseColor("#808080"));
							bProp.setBackgroundColor(Color.parseColor("#808080"));
							selection=3;
							llprop.setVisibility(View.GONE);
							llnote.setVisibility(View.GONE);
							llcomments.setVisibility(View.GONE);
							lltranscript.setVisibility(View.VISIBLE);
				       }
			    }
			    public void onSwipeBottom() {
			    }
			
		});
				 
		// Si on a une TextNote
		
				 if(type==Item.typeTextnote){	
					 TextView type=(TextView) rootView.findViewById(R.id.TVAddType);
					 type.setText("Text Note");
					 author=(EditText) rootView.findViewById(R.id.ETAuthor);
					 interest=(EditText) rootView.findViewById(R.id.ETInterest);
					 save.setText("Add");
					 if(this.update || this.shared){
						 title.setText(item.getTitle());
						 comments.setText(item.getComments());
						
						keywords.setText(item.getKeywords());
						note.setText(item.getNote());
						transcript.setText(item.getTranscript());
						
						author.setText(item.getAuthor());
						interest.setText(item.getInterest());
						if(this.update){
							save.setText("Save");
							Button delete=(Button) rootView.findViewById(R.id.BDelete);
							delete.setVisibility(0);
							delete.setOnClickListener(new OnClickListener(){
								public void onClick(View v){
									builder.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											db.Open();
											db.Delete(item.getId());
											db.Close();
											FragmentManager fm = getFragmentManager();
											fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
											clearBackStack(fm);
							        		FragmentExplorer dFragment = new FragmentExplorer();
											 fm.beginTransaction()
							                    .replace(R.id.container,dFragment).commit();
										}
									});
									builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});
									dialog = builder.create();
									dialog.show();
								}});
						}
						
					 }
					 LinearLayout llauthor=(LinearLayout) rootView.findViewById(R.id.LLAuthor);
					 	llauthor.setVisibility(0);
					 LinearLayout llinterest=(LinearLayout) rootView.findViewById(R.id.LLInterest);
					 	llinterest.setVisibility(0);
					 	save.setOnClickListener(new OnClickListener(){
							public void onClick(View v){
								Item toAdd=new Item(title.getText().toString(),comments.getText().toString(),rightNow.get(Calendar.DAY_OF_MONTH)+"/"+(rightNow.get(Calendar.MONTH)+1)+"/"+rightNow.get(Calendar.YEAR),spinDomain.getSelectedItem().toString(),
										keywords.getText().toString(),note.getText().toString(),Item.typeTextnote, spinProject.getSelectedItem().toString(),
										null,null,null,null,null,null,null,
										null,null, null,null,
										author.getText().toString(),null,null,
										null,null,null,null,null,null,null,null,null,transcript.getText().toString());
								if(!update){
									db.Open();
									db.Save(toAdd);
									db.Close();
								}
								else{
									db.Open();
									toAdd.setId(item.getId());
									db.Update(toAdd);
									db.Close();
								}
								FragmentManager fm = getFragmentManager();
								fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
								clearBackStack(fm);
				        		FragmentExplorer dFragment = new FragmentExplorer();
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).commit();
							}
						});
				 }
				 
				 // Si on a un Website
				 
				 if(type==Item.typeWebsite){	
					 save.setText("Add");
					 TextView type=(TextView) rootView.findViewById(R.id.TVAddType);
					 type.setText("Website");
					 website=(EditText) rootView.findViewById(R.id.ETWebsite);
					 description=(EditText) rootView.findViewById(R.id.ETDescription);
					 contentQuality=(EditText) rootView.findViewById(R.id.ETContentQuality);
					 websiteQuality=(EditText) rootView.findViewById(R.id.ETWebsiteQuality);
					 language=(EditText) rootView.findViewById(R.id.ETLanguage);
					 authorLocation=(EditText) rootView.findViewById(R.id.ETAuthorLocation);
					 authorType=(EditText) rootView.findViewById(R.id.ETAuthorType);
					 authorName=(EditText) rootView.findViewById(R.id.ETAuthorName);
					 publicType=(EditText) rootView.findViewById(R.id.ETPublicType);
					 if(this.update || this.shared){
						 title.setText(item.getTitle());
						 comments.setText(item.getComments());
						 
						keywords.setText(item.getKeywords());
						note.setText(item.getNote());
						transcript.setText(item.getTranscript());
						
						website.setText(item.getWebsite());
						description.setText(item.getDescription());
						contentQuality.setText(item.getContentQuality());
						 websiteQuality.setText(item.getWebsiteQuality());
						 language.setText(item.getLanguage());
						 authorLocation.setText(item.getAuthorLocation());
						 authorType.setText(item.getAuthorType());
						 authorName.setText(item.getAuthorName());
						 publicType.setText(item.getPublicType());
						 if(this.update){
							 save.setText("Save");
							 Button delete=(Button) rootView.findViewById(R.id.BDelete);
								delete.setVisibility(0);
								delete.setOnClickListener(new OnClickListener(){
									public void onClick(View v){
										builder.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												db.Open();
												db.Delete(item.getId());
												db.Close();
												FragmentManager fm = getFragmentManager();
												fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
												clearBackStack(fm);
								        		FragmentExplorer dFragment = new FragmentExplorer();
												 fm.beginTransaction()
								                    .replace(R.id.container,dFragment).commit();
											}
										});
										builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
											}
										});
										dialog = builder.create();
										dialog.show();
									}});
								Button open=(Button) rootView.findViewById(R.id.BOpen);
								open.setVisibility(0);
								open.setOnClickListener(new OnClickListener(){
									public void onClick(View v){
										if(website.getText().toString().isEmpty()){
											Toast.makeText(getActivity(), "No URL provided !", Toast.LENGTH_SHORT).show();
										}
										else{
											try {
												 Intent view = new Intent(Intent.ACTION_VIEW);
												 view.setData(Uri.parse(website.getText().toString()));
												 startActivity(view);
											} catch (ActivityNotFoundException e) {
											    e.printStackTrace();
											    Toast.makeText(getActivity(), "Impossible to open the link !", Toast.LENGTH_SHORT).show();
											}
											
										}
									}});
						 }
						
					 }
					 LinearLayout llwebsite=(LinearLayout) rootView.findViewById(R.id.LLWebsite);
					 	llwebsite.setVisibility(0);
					 LinearLayout lldescription=(LinearLayout) rootView.findViewById(R.id.LLDescription);
					 	lldescription.setVisibility(0);
					 LinearLayout llcontentQuality=(LinearLayout) rootView.findViewById(R.id.LLcontentQuality);
					 	llcontentQuality.setVisibility(0);
					 LinearLayout llWebsiteQuality=(LinearLayout) rootView.findViewById(R.id.LLWebsiteQuality);
					 	llWebsiteQuality.setVisibility(0);
					 LinearLayout llLanguage=(LinearLayout) rootView.findViewById(R.id.LLLanguage);
					 	llLanguage.setVisibility(0);
					 LinearLayout llAuthorType=(LinearLayout) rootView.findViewById(R.id.LLAuthorType);
					 	llAuthorType.setVisibility(0);
					 LinearLayout llAuthorName=(LinearLayout) rootView.findViewById(R.id.LLAuthorName);
					 	llAuthorName.setVisibility(0);
					 LinearLayout llAuthorLocation=(LinearLayout) rootView.findViewById(R.id.LLAuthorLocation);
					 	llAuthorLocation.setVisibility(0);
					 LinearLayout llPublicType=(LinearLayout) rootView.findViewById(R.id.PublicType);
					 	llPublicType.setVisibility(0);
					 
					 	save.setOnClickListener(new OnClickListener(){
							public void onClick(View v){
								Item toAdd=new Item(title.getText().toString(),comments.getText().toString(),rightNow.get(Calendar.DAY_OF_MONTH)+"/"+(rightNow.get(Calendar.MONTH)+1)+"/"+rightNow.get(Calendar.YEAR),spinDomain.getSelectedItem().toString(),
										keywords.getText().toString(),note.getText().toString(),Item.typeWebsite, spinProject.getSelectedItem().toString(),
										null, null,null, null,
										null, null,null,
										null, null,website.getText().toString(), null,
										null, null, null,
										contentQuality.getText().toString(),websiteQuality.getText().toString(),language.getText().toString(),
										null,description.getText().toString(),authorType.getText().toString(),
										authorName.getText().toString(),authorLocation.getText().toString(),publicType.getText().toString(),transcript.getText().toString());
								if(!update){
									db.Open();
									db.Save(toAdd);
									db.Close();
								}
								else{
									db.Open();
									toAdd.setId(item.getId());
									db.Update(toAdd);
									db.Close();
								}
								FragmentManager fm = getFragmentManager();
								fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
								clearBackStack(fm);
				        		FragmentExplorer dFragment = new FragmentExplorer();
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).commit();
							}
						});
				 }
				 
				 // Si on a un document
				 
				 if(type==Item.typeDoc){	
					 save.setText("Add");
					 TextView type=(TextView) rootView.findViewById(R.id.TVAddType);
					 type.setText("Document");
					 author=(EditText) rootView.findViewById(R.id.ETAuthor);
					 toRead=(EditText) rootView.findViewById(R.id.ETToRead);
					 contentQuality=(EditText) rootView.findViewById(R.id.ETContentQuality);
					 websiteQuality=(EditText) rootView.findViewById(R.id.ETWebsiteQuality);
					 language=(EditText) rootView.findViewById(R.id.ETLanguage);
					 location=(EditText) rootView.findViewById(R.id.ETLocation);
					 interest=(EditText) rootView.findViewById(R.id.ETInterest);
					 if(this.update || this.shared){
						 title.setText(item.getTitle());
						 comments.setText(item.getComments());
						
						keywords.setText(item.getKeywords());
						note.setText(item.getNote());
						transcript.setText(item.getTranscript());
						
						author.setText(item.getAuthor());
						toRead.setText(item.getToRead());
						contentQuality.setText(item.getContentQuality());
						 websiteQuality.setText(item.getWebsiteQuality());
						 language.setText(item.getLanguage());
						 location.setText(item.getLocation());
						 interest.setText(item.getInterest());
						 if(this.update){
							 save.setText("Save");
							 Button delete=(Button) rootView.findViewById(R.id.BDelete);
								delete.setVisibility(0);
								delete.setOnClickListener(new OnClickListener(){
									public void onClick(View v){
										builder.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												db.Open();
												db.Delete(item.getId());
												db.Close();
												FragmentManager fm = getFragmentManager();
											fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
												clearBackStack(fm);
								        		FragmentExplorer dFragment = new FragmentExplorer();
												 fm.beginTransaction()
								                    .replace(R.id.container,dFragment).commit();
											}
										});
										builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
											}
										});
										dialog = builder.create();
										dialog.show();
									}});
							Button open=(Button) rootView.findViewById(R.id.BOpen);
							open.setVisibility(0);
							open.setOnClickListener(new OnClickListener(){
								public void onClick(View v){
									if(location.getText().toString().isEmpty()){
										Toast.makeText(getActivity(), "No location provided !", Toast.LENGTH_SHORT).show();
									}
									else{
										try {
											Uri doc = Uri.parse(location.getText().toString());
											 Intent view = new Intent(Intent.ACTION_VIEW,doc);
											 startActivity(view);
										} catch (ActivityNotFoundException e) {
										    e.printStackTrace();
										    Toast.makeText(getActivity(), "Impossible to open this file !", Toast.LENGTH_SHORT).show();
										}
										
									}
								}});
						 }
						
					 }
					 LinearLayout llAuthor=(LinearLayout) rootView.findViewById(R.id.LLAuthor);
					 	llAuthor.setVisibility(0);
					 LinearLayout llToRead=(LinearLayout) rootView.findViewById(R.id.LLToRead);
					 	llToRead.setVisibility(0);
					 LinearLayout llcontentQuality=(LinearLayout) rootView.findViewById(R.id.LLcontentQuality);
					 	llcontentQuality.setVisibility(0);
					 LinearLayout llWebsiteQuality=(LinearLayout) rootView.findViewById(R.id.LLWebsiteQuality);
					 	llWebsiteQuality.setVisibility(0);
					 LinearLayout llLanguage=(LinearLayout) rootView.findViewById(R.id.LLLanguage);
					 	llLanguage.setVisibility(0);
					 LinearLayout llLocation=(LinearLayout) rootView.findViewById(R.id.LLLocation);
					 	llLocation.setVisibility(0);
					 LinearLayout llInterest=(LinearLayout) rootView.findViewById(R.id.LLInterest);
					 	llInterest.setVisibility(0);
					 	save.setOnClickListener(new OnClickListener(){
							public void onClick(View v){
								Item toAdd=new Item(title.getText().toString(),comments.getText().toString(),rightNow.get(Calendar.DAY_OF_MONTH)+"/"+(rightNow.get(Calendar.MONTH)+1)+"/"+rightNow.get(Calendar.YEAR),spinDomain.getSelectedItem().toString(),
										keywords.getText().toString(),note.getText().toString(),Item.typeDoc, spinProject.getSelectedItem().toString(),
										null, null,null,null,
										null,null,null,
										null,null,null,null,
										author.getText().toString(),toRead.getText().toString(),interest.getText().toString(),
										contentQuality.getText().toString(),websiteQuality.getText().toString(),language.getText().toString(),
										location.getText().toString(),null, null,
										null,null,null,transcript.getText().toString());
								if(!update){
									db.Open();
									db.Save(toAdd);
									db.Close();
								}
								else{
									db.Open();
									toAdd.setId(item.getId());
									db.Update(toAdd);
									db.Close();
								}
								FragmentManager fm = getFragmentManager();
								fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
								clearBackStack(fm);
				        		FragmentExplorer dFragment = new FragmentExplorer();
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).commit();
							}
						});
				 }
				 
				 // Si on a un contact
				 
				 if(type==Item.typeContact){	
					 TextView type=(TextView) rootView.findViewById(R.id.TVAddType);
					 type.setText("Contact");
					 name=(EditText) rootView.findViewById(R.id.ETName);
					 company=(EditText) rootView.findViewById(R.id.ETCompany);
					 service=(EditText) rootView.findViewById(R.id.ETService);
					 mobilePhone=(EditText) rootView.findViewById(R.id.ETMobilePhone);
					 eMail=(EditText) rootView.findViewById(R.id.ETEmail);
					 officeTel=(EditText) rootView.findViewById(R.id.ETOfficeTel);
					 officeAddress=(EditText) rootView.findViewById(R.id.ETOfficeAddress);
					 website=(EditText) rootView.findViewById(R.id.ETWebsite);
					 language=(EditText) rootView.findViewById(R.id.ETLanguage);
					 spouse=(EditText) rootView.findViewById(R.id.ETSpouse);
					 save.setText("Add");
					 if(this.update || this.shared){
						 title.setText(item.getTitle());
						 comments.setText(item.getComments());
						
						keywords.setText(item.getKeywords());
						note.setText(item.getNote());
						transcript.setText(item.getTranscript());
					
						name.setText(item.getName());
						company.setText(item.getCompany());
						service.setText(item.getService());
						mobilePhone.setText(item.getMobilePhone());
						eMail.setText(item.geteMail());
						officeTel.setText(item.getOfficeTel());
						officeAddress.setText(item.getOfficeAddress());
						website.setText(item.getWebsite());
						language.setText(item.getLanguage());
						spouse.setText(item.getSpouse());
						if(this.update){
							save.setText("Save");
							Button delete=(Button) rootView.findViewById(R.id.BDelete);
							delete.setVisibility(0);
							delete.setOnClickListener(new OnClickListener(){
								public void onClick(View v){
									builder.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											db.Open();
											db.Delete(item.getId());
											db.Close();
											FragmentManager fm = getFragmentManager();
											fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
											clearBackStack(fm);
							        		FragmentExplorer dFragment = new FragmentExplorer();
											 fm.beginTransaction()
							                    .replace(R.id.container,dFragment).commit();
										}
									});
									builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});
									dialog = builder.create();
									dialog.show();
								}});
						}
						
					 }
					 LinearLayout llName=(LinearLayout) rootView.findViewById(R.id.LLName);
					 	llName.setVisibility(0);
					 LinearLayout llCompany=(LinearLayout) rootView.findViewById(R.id.LLCompany);
					 	llCompany.setVisibility(0);
					 LinearLayout llService=(LinearLayout) rootView.findViewById(R.id.LLService);
						 	llService.setVisibility(0);
					 LinearLayout llMobilePhone=(LinearLayout) rootView.findViewById(R.id.LLMobilePhone);
							 	llMobilePhone.setVisibility(0);
					 LinearLayout llEMail=(LinearLayout) rootView.findViewById(R.id.LLEmail);
								 	llEMail.setVisibility(0);
					 LinearLayout llOfficeTel=(LinearLayout) rootView.findViewById(R.id.LLOfficeTel);
									 	llOfficeTel.setVisibility(0);
					 LinearLayout llOfficeAddress=(LinearLayout) rootView.findViewById(R.id.LLOfficeAddress);
										 	llOfficeAddress.setVisibility(0);
					 LinearLayout llWebsite=(LinearLayout) rootView.findViewById(R.id.LLWebsite);
											 	llWebsite.setVisibility(0);
					 LinearLayout llLanguage=(LinearLayout) rootView.findViewById(R.id.LLLanguage);
												 	llLanguage.setVisibility(0);
					 LinearLayout llSpouse=(LinearLayout) rootView.findViewById(R.id.LLSpouse);
													 	llSpouse.setVisibility(0);
													 	
					 	save.setOnClickListener(new OnClickListener(){
							public void onClick(View v){
								Item toAdd=new Item(title.getText().toString(),comments.getText().toString(),rightNow.get(Calendar.DAY_OF_MONTH)+"/"+(rightNow.get(Calendar.MONTH)+1)+"/"+rightNow.get(Calendar.YEAR),spinDomain.getSelectedItem().toString(),
										keywords.getText().toString(),note.getText().toString(),Item.typeContact, spinProject.getSelectedItem().toString(),
										name.getText().toString(),company.getText().toString(),service.getText().toString(),mobilePhone.getText().toString(),
										eMail.getText().toString(),officeTel.getText().toString(),officeAddress.getText().toString(),
										null,null, website.getText().toString(),spouse.getText().toString(),
										null,null,null,null,null,language.getText().toString(),
										null,null,null,null,null,null,transcript.getText().toString());
								if(!update){
									db.Open();
									db.Save(toAdd);
									db.Close();
								}
								else{
									db.Open();
									toAdd.setId(item.getId());
									db.Update(toAdd);
									db.Close();
								}
								FragmentManager fm = getFragmentManager();
								fm.popBackStack("explorer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
								
				        		FragmentExplorer dFragment = new FragmentExplorer();
				        		clearBackStack(fm);
								 fm.beginTransaction()
				                    .replace(R.id.container,dFragment).commit();
							}
						});
				 }
        
        return rootView;
	}
	private void clearBackStack(FragmentManager manager) {
		for(int i=manager.getBackStackEntryCount();i>0;i--){
			FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
	         manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}
}