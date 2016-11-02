package com.example.pimkey.Calendar;

import com.example.pimkey.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FragmentEvent extends Activity {
	public FragmentEvent(){}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_event);
		Intent event=getIntent();
        TextView date= (TextView) findViewById(R.id.date);
        Button add = (Button) findViewById(R.id.add);
        date.setText(event.getIntExtra("day",0)+"/"+event.getIntExtra("month",0)+"/"+event.getIntExtra("year",0));
        final Intent tr= new Intent(this,Add_Activity.class);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(tr);
            }
        });

        

    }

}
