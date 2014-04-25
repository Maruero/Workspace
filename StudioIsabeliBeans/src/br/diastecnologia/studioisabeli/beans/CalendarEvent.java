package br.diastecnologia.studioisabeli.beans;

public class CalendarEvent {

	private String title;
	private String start;
	private String end;
	private CalendarEventColor color;
	private boolean allDay;
	
	private Integer studioScheduleID;
	private Integer customersMaxCount;
	
	public Integer getStudioScheduleID() {
		return studioScheduleID;
	}
	public void setStudioScheduleID(Integer studioScheduleID) {
		this.studioScheduleID = studioScheduleID;
	}
	public Integer getCustomersMaxCount() {
		return customersMaxCount;
	}
	public void setCustomersMaxCount(Integer customersMaxCount) {
		this.customersMaxCount = customersMaxCount;
	}
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public CalendarEventColor getColor() {
		return color;
	}
	public void setColor(CalendarEventColor color) {
		this.color = color;
	}
	
}
