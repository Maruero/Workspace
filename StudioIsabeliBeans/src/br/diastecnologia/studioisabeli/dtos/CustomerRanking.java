package br.diastecnologia.studioisabeli.dtos;

import br.diastecnologia.studioisabeli.beans.Customer;

public class CustomerRanking {

	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	private Integer goldCount;
	private Integer silverCount;
	private Integer bronzeCount;
	private Integer points;

	public Integer getGoldCount() {
		return goldCount;
	}
	public void setGoldCount(Integer goldCount) {
		this.goldCount = goldCount;
	}
	public Integer getSilverCount() {
		return silverCount;
	}
	public void setSilverCount(Integer silverCount) {
		this.silverCount = silverCount;
	}
	public Integer getBronzeCount() {
		return bronzeCount;
	}
	public void setBronzeCount(Integer bronzeCount) {
		this.bronzeCount = bronzeCount;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
