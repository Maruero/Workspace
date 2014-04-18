package br.diastecnologia.studioisabeli.beans;

public class StudioSchedule {

	private Integer StudioScheduleID;
	private Integer weekDay;
	private Integer beginHour;
	private Integer EndHour;
	private Integer CustomersMaxCount;
	
	public Integer getStudioScheduleID() {
		return StudioScheduleID;
	}
	public void setStudioScheduleID(Integer studioScheduleID) {
		StudioScheduleID = studioScheduleID;
	}
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}
	public Integer getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(Integer beginHour) {
		this.beginHour = beginHour;
	}
	public Integer getEndHour() {
		return EndHour;
	}
	public void setEndHour(Integer endHour) {
		EndHour = endHour;
	}
	public Integer getCustomersMaxCount() {
		return CustomersMaxCount;
	}
	public void setCustomersMaxCount(Integer customersMaxCount) {
		CustomersMaxCount = customersMaxCount;
	}
	
}
