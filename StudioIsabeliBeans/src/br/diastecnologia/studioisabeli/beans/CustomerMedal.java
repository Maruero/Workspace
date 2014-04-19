package br.diastecnologia.studioisabeli.beans;

import java.util.Date;

public class CustomerMedal {

	
	private Integer customerID;
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	private Integer medalID;
	public Integer getMedalID() {
		return medalID;
	}
	public void setMedalID(Integer medalID) {
		this.medalID = medalID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Medal getMedal() {
		return medal;
	}
	public void setMedal(Medal medal) {
		this.medal = medal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private Date date;
	private Medal medal;
	private String description;
	
	
	
}
