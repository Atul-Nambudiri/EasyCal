package com.eas.easycal;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<String[]> {
	private final Context context;
	private final String[][] events;
	
	public EventAdapter(Context c, String[][] e) {
		super(c, R.layout.event_layout, e);
		context = c;
		events = e;
	}
	
	public View getView(int position, View v, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.event_layout, parent, false);
		
		//Set text views for each item in the list
		TextView textTitle = (TextView) rowView.findViewById(R.id.title);
		TextView textLocation = (TextView) rowView.findViewById(R.id.location);
		TextView textDay = (TextView) rowView.findViewById(R.id.day);
		TextView textTime = (TextView) rowView.findViewById(R.id.time);
		TextView textID = (TextView) rowView.findViewById(R.id.id);
		
		//Sets the text to be inserted in each text view
		textTitle.setText(events[position][0]);
		textTitle.setTypeface(null,Typeface.BOLD);
		textDay.setText(events[position][1]);
		textLocation.setText(events[position][2]);
		textTime.setText(events[position][3]);		
		textID.setText(events[position][4]);
		
		
		if (position % 2 == 0) rowView.setBackgroundColor(0x88003C7D);
		else rowView.setBackgroundColor(0x88F47F24);
		
		return rowView;
	}
}
