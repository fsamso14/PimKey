package com.example.pimkey;

import com.example.pimkey.About.FragmentAbout;
import com.example.pimkey.Calendar.FragmentCalendar;
import com.example.pimkey.Explorer.FragmentExplorer;
import com.example.pimkey.Explorer.ItemAdder;
import com.example.pimkey.item.Item;
import com.example.pimkey.settings.SettingsFragment;
import com.example.pimkey.NavigationDrawerFragment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Spinner;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks{
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private static CharSequence mTitle;
	Spinner spin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));		
		Intent intent = getIntent();
		if (Intent.ACTION_SEND.equals(intent.getAction())){
			Fragment temp=new SettingsFragment();
			 FragmentManager fragmentManager = getFragmentManager();
	            fragmentManager.beginTransaction()
	                    .replace(R.id.container,temp).commit();
		}
	}
	@Override
	protected void onStart(){
		super.onStart();
		Intent intent = getIntent();
		Log.v("test",""+Intent.ACTION_SEND.equals(intent.getAction()));
		
		if (Intent.ACTION_SEND.equals(intent.getAction())){
		
		Bundle temp2=intent.getExtras();
		/* Log.v("test type text",""+temp2.size());
		for (String key : temp2.keySet()) {
		    Object value = temp2.get(key);
		    Log.v("TEST KEY BUNDLE", String.format("%s %s (%s)", key,  
		        value.toString(), value.getClass().getName()));
		}*/
			Item test=new Item();
			if(intent.getType().contains("text/plain")){
				test.setType(Item.typeWebsite);
				test.setWebsite(intent.getStringExtra(Intent.EXTRA_TEXT));
				test.setTitle(temp2.get(Intent.EXTRA_SUBJECT).toString());
				Fragment temp=new ItemAdder(false,true,test);
				 FragmentManager fragmentManager = getFragmentManager();
		            fragmentManager.beginTransaction()
		                    .replace(R.id.container,temp).commit();
			}
			else{
				if(intent.getType().contains("text/x-vcard")){
					test.setType(Item.typeContact);
					Fragment temp=new ItemAdder(false,true,test);
					 FragmentManager fragmentManager = getFragmentManager();
			            fragmentManager.beginTransaction()
			                    .replace(R.id.container,temp).commit();
				}
				else{
					Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
					test.setType(Item.typeDoc);
					test.setLocation(uri.toString());
					String title=uri.toString();
					test.setTitle(separateur(title).replace("%20"," "));
					Fragment temp=new ItemAdder(false,true,test);
					 FragmentManager fragmentManager = getFragmentManager();
			            fragmentManager.beginTransaction()
			                    .replace(R.id.container,temp).commit();
				}
			}			
		}
	}
	

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		 clearBackStack(fragmentManager);
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		Fragment test = null;
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			test = new FragmentExplorer();
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			test= new FragmentCalendar();
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			test= new FragmentAbout();
			break;
		}
		 if (test != null) {
	            FragmentManager fragmentManager = getFragmentManager();
	            clearBackStack(fragmentManager);
	            fragmentManager.beginTransaction()
	                    .replace(R.id.container,test).commit();
	            clearBackStack(fragmentManager);
	        } else {
	            // error in creating fragment
	            Log.e("MainActivity", "Error in creating fragment");
	        }
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Fragment temp=new SettingsFragment();
			 FragmentManager fragmentManager = getFragmentManager();
			 clearBackStack(fragmentManager);
	            fragmentManager.beginTransaction()
	                    .replace(R.id.container,temp).addToBackStack(null).commit();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
		

		

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}
	public String separateur(String aseparer){
		String aret="";
		String[] temp= aseparer.split("/");
		aret=temp[temp.length-1];
		if(aret.contains(".")){
			int temp2=aret.indexOf(".");
			aret=aret.substring(0,temp2);
		}
		return aret;
	}
	private void clearBackStack(FragmentManager manager) {
		for(int i=manager.getBackStackEntryCount();i>0;i--){
			FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
	         manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}
}

