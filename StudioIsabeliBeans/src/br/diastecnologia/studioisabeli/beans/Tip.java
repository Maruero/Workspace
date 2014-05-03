package br.diastecnologia.studioisabeli.beans;

import java.util.Date;

public class Tip {

	private Integer tipID;
	private String title;
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	private String text;
	private Date date;
	private Integer nextTipID;
	private Integer previousTipID;
	
	public Tip(){
		
	}
	
	public Tip(Integer tipID, String text, Date date) {
		super();
		this.tipID = tipID;
		this.text = text;
		this.date = date;
	}
	
	public Tip(Integer tipID, String text, Date date, Integer nextTipID, Integer previousTipID) {
		super();
		this.tipID = tipID;
		this.text = text;
		this.date = date;
		this.nextTipID = nextTipID;
		this.previousTipID = previousTipID;
	}
	
	public Integer getTipID() {
		return tipID;
	}
	public void setTipID(Integer tipID) {
		this.tipID = tipID;
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
	public Integer getNextTipID() {
		return nextTipID;
	}
	public void setNextTipID(Integer nextTipID) {
		this.nextTipID = nextTipID;
	}
	public Integer getPreviousTipID() {
		return previousTipID;
	}
	public void setPreviousTipID(Integer previousTipID) {
		this.previousTipID = previousTipID;
	}
	
	
}
