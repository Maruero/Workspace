package br.diastecnologia.studioisabeli.beans;

import br.diastecnologia.studioisabeli.enums.ClassStatus;
import br.diastecnologia.studioisabeli.enums.ClassType;

public class CustomerClass {

	private Integer customerID;
	private Customer customer;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	private Integer studioScheduleID;
	private Integer weekID;
	private ClassType type;
	private ClassStatus status;
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
	public Integer getWeekID() {
		return weekID;
	}
	public void setWeekID(Integer weekID) {
		this.weekID = weekID;
	}
	public ClassType getType() {
		return type;
	}
	public void setType(ClassType type) {
		this.type = type;
	}
	public ClassStatus getStatus() {
		return status;
	}
	public void setStatus(ClassStatus status) {
		this.status = status;
	}
	
}
