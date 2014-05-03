package br.diastecnologia.studioisabeli.beans;

import java.util.Date;

public class CustomerScrap {

	private Integer customerID;
	private Integer scrapNumber;
	private Integer nextScrapNumber;
	private Integer previousScrapNumber;
	private String text;
	private Date date;
	
	public CustomerScrap(){
		
	}
	
	public CustomerScrap(Integer customerID, Integer scrapNumber, String text, Date date) {
		super();
		this.customerID = customerID;
		this.scrapNumber = scrapNumber;
		this.text = text;
		this.date = date;
	}
	
	public CustomerScrap(Integer customerID, Integer scrapNumber, Integer nextScrapNumber, Integer previousScrapNumber, String text, Date date) {
		super();
		this.customerID = customerID;
		this.scrapNumber = scrapNumber;
		this.nextScrapNumber = nextScrapNumber;
		this.previousScrapNumber = previousScrapNumber;
		this.text = text;
		this.date = date;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getScrapNumber() {
		return scrapNumber;
	}
	public void setScrapNumber(Integer scrapNumber) {
		this.scrapNumber = scrapNumber;
	}
	public Integer getNextScrapNumber() {
		return nextScrapNumber;
	}
	public void setNextScrapNumber(Integer nextScrapNumber) {
		this.nextScrapNumber = nextScrapNumber;
	}
	public Integer getPreviousScrapNumber() {
		return previousScrapNumber;
	}
	public void setPreviousScrapNumber(Integer previousScrapNumber) {
		this.previousScrapNumber = previousScrapNumber;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
