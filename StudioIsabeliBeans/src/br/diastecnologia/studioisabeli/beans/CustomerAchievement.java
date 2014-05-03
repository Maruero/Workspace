package br.diastecnologia.studioisabeli.beans;

import java.util.Date;
import java.util.List;

public class CustomerAchievement {

	private Date date;
	private Customer customer;
	private Integer achievementNumber;
	private List<Achievement> achievements;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getAchievementNumber() {
		return achievementNumber;
	}
	public void setAchievementNumber(Integer achievementNumber) {
		this.achievementNumber = achievementNumber;
	}
	public List<Achievement> getAchievements() {
		return achievements;
	}
	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}
	
}
