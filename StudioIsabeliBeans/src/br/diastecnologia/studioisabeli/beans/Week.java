package br.diastecnologia.studioisabeli.beans;

import java.sql.Date;

public class Week {

	private Integer weekID;
	private Date begin;
	private Date end;
	
	public Integer getWeekID() {
		return weekID;
	}
	public void setWeekID(Integer weekID) {
		this.weekID = weekID;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
}
