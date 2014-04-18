package br.diastecnologia.studioisabeli.beans;

public class CustomerToken {

	private String token;
	private Integer customerID;
	public CustomerToken(String token, Integer customerID) {
		super();
		this.token = token;
		this.customerID = customerID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	
	
}
