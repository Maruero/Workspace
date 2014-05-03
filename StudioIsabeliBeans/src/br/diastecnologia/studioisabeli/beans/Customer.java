package br.diastecnologia.studioisabeli.beans;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1082224357678131187L;
	
	private Integer customerID;
	private String name;
	private boolean visible;
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Customer(){}
	
	public Customer(Integer customerID, String name) {
		super();
		this.customerID = customerID;
		this.name = name;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
