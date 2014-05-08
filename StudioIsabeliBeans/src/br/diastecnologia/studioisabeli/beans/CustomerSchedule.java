package br.diastecnologia.studioisabeli.beans;

public class CustomerSchedule {

	private Integer customerID;
	private Integer studioScheduleID;
	private Integer alterBeginMinutes;
	private Integer alterEndMinutes;
	public Integer getAlterBeginMinutes() {
		return alterBeginMinutes;
	}
	public void setAlterBeginMinutes(Integer alterBeginMinutes) {
		this.alterBeginMinutes = alterBeginMinutes;
	}
	public Integer getAlterEndMinutes() {
		return alterEndMinutes;
	}
	public void setAlterEndMinutes(Integer alterEndMinutes) {
		this.alterEndMinutes = alterEndMinutes;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getStudioScheduleID() {
		return studioScheduleID;
	}
	public void setStudioScheduleID(Integer studioScheduleID) {
		this.studioScheduleID = studioScheduleID;
	}
	
	
	
}
