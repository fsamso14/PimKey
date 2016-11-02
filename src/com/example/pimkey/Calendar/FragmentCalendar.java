package com.example.pimkey.Calendar;

import com.example.pimkey.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

public class FragmentCalendar extends Fragment {
	public FragmentCalendar(){}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView calendarView=(CalendarView) rootView.findViewById(R.id.calendarView1);
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                    int dayOfMonth) {
            	Intent event=new Intent(getActivity(),FragmentEvent.class);
            	event.putExtra("year",year);
            	event.putExtra("month",month);
            	event.putExtra("day",dayOfMonth);
            	//startActivity(event);
            }
        });
        return rootView;
    }

}
