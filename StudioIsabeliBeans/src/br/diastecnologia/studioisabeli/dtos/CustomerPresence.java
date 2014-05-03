package br.diastecnologia.studioisabeli.dtos;

import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.enums.ClassStatus;

public class CustomerPresence {

	private Customer customer;
	private Boolean presence;
	private Integer weekID;
	private Integer studioScheduleID;
	private ClassStatus status;
	
	public ClassStatus getStatus() {
		return status;
	}

	public void setStatus(ClassStatus status) {
		this.status = status;
	}

	public Integer getStudioScheduleID() {
		return studioScheduleID;
	}

	public void setStudioScheduleID(Integer studioScheduleID) {
		this.studioScheduleID = studioScheduleID;
	}

	public CustomerPresence(){
		
	}
	
	public CustomerPresence(Customer _customer, Integer _weekID, ClassStatus _status){
		this.customer = _customer;
		this.weekID = _weekID;
		this.status = _status;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Boolean getPresence() {
		return presence;
	}
	public void setPresence(Boolean presence) {
		this.presence = presence;
	}
	public Integer getWeekID() {
		return weekID;
	}
	public void setWeekID(Integer weekID) {
		this.weekID = weekID;
	}
	
}
