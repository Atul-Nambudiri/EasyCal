package com.eas.easycal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class newListFragment extends ListFragment{
private IndividualEvent testEvents[];
private String[][] testEventsS = new String[12][0];
private EventAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		testEvents = new IndividualEvent[] {
				new IndividualEvent(1, "MATH 231", "12", "1:00", "Natural History Building"), 
				new IndividualEvent(1, "EALC 250", "13", "1:00", "Main Library"),
				new IndividualEvent(1, "CS 125", "14", "1:00", "Digital Computer Laboratory"),
				new IndividualEvent(1, "CS 173", "15", "1:00", "Digital Computer Laboratory"),
				new IndividualEvent(1, "ENG 100", "16", "1:00", "Everitt Laboratory"),
				new IndividualEvent(1, "CS 100", "17", "1:00", "Natural History Building"),
				new IndividualEvent(1, "CS 398", "18", "1:00", "Everitt Laboratory"),
				new IndividualEvent(1, "PHYS 214", "19", "1:00", "Loomis Lab"),
				new IndividualEvent(1, "Lunch", "20", "1:00", "Illinois Street Residence Hall"),
				new IndividualEvent(1, "CS 225", "21", "1:00", "Siebel Center for Computer Science"),
				new IndividualEvent(1, "MATH 241", "22", "1:00", "Altgeld Hall"),
				new IndividualEvent(1, "SigMobile", "23", "1:00", "Siebel Center for Computer Science")
		};
		testEventsS = new String[12][0];
		for(int i = 0; i < testEvents.length; i++){
			testEventsS[i] = testEvents[i].getArray();
		}		
		
				
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.list_fragment, container, false);
		adapter = new EventAdapter(getActivity(), testEventsS);
		setListAdapter(adapter);
		return v;
	}
	
	public void update(String[][] t){
		testEventsS = t;		
		adapter = new EventAdapter(getActivity(), testEventsS);
		setListAdapter(adapter); 
	}
	
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
		eventDetailDialog(v);
	}
	
	public void eventDetailDialog(View t) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View v = inflater.inflate(R.layout.detail_dialog, null);
		builder.setView(v); 
		String name = ((TextView) t.findViewById(R.id.title)).getText().toString();
		String day = ((TextView) t.findViewById(R.id.day)).getText().toString();
        String time = ((TextView) t.findViewById(R.id.time)).getText().toString();
        String location = ((TextView) t.findViewById(R.id.location)).getText().toString();
        ((TextView) v.findViewById(R.id.title2)).setText(name);
        ((TextView) v.findViewById(R.id.location2)).setText(location);
        ((TextView) v.findViewById(R.id.time2)).setText(time);
        ((TextView) v.findViewById(R.id.day2)).setText(day);
		builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
		     @Override
		     public void onClick(DialogInterface dialog, int id) {
		       dialog.dismiss();
		     }
		    });
		 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int id) {
			        dialog.cancel();
			    }
		 });
		 AlertDialog dialog = builder.create();
		 dialog.show();		 
	}
}