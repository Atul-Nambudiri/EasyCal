package com.eas.easycal;

public class IndividualEvent {
	public int id;	
	public String name;
	public String day;
	public String time;
	public String location;
	
	public IndividualEvent(int tid, String tName, String tDay, String tTime, String tLocation) {
		id = tid;
		name = tName;
		day = tDay;
		time = tTime;
		location = tLocation;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDay() {
		return day;
	}

	public String getTime() {
		return time;
	}

	public String getLocation() {
		return location;
	}
	
	public String[] getArray() {
		String[] r = new String[5];
		r[0] = name;
		r[1] = day; 
		r[2] = location;
		r[3] = time;
		r[4] = Integer.toString(id);
		return r;
	}

}
