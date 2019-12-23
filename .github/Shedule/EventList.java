package Shedule;

import java.util.Date;

public class EventList {
//private Date date;
	private String date;
	private String event;
	private String program;
//	private Date time;
	private String time;
	private String instructor;
	
	
	 public EventList(String date,String event,String program,String time, String instructor) {
		this.date=date;
		this.event=event;
		this.program=program;
		this.time=time;
		this.instructor=instructor;
		
	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	
}
