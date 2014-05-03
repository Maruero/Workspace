package br.diastecnologia.studioisabeli.beans;

public class Achievement {

	private AchievementType achievementType;
	private Customer customer;
	private Double value;
	private Integer achievementNumber;
	public AchievementType getAchievementType() {
		return achievementType;
	}
	public void setAchievementType(AchievementType achievementType) {
		this.achievementType = achievementType;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Integer getAchievementNumber() {
		return achievementNumber;
	}
	public void setAchievementNumber(Integer achievementNumber) {
		this.achievementNumber = achievementNumber;
	}
	
}
