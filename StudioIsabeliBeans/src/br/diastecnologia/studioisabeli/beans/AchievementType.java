package br.diastecnologia.studioisabeli.beans;

public class AchievementType {

	private String name;
	private Integer achievementTypeID;
	
	public AchievementType(){}
	
	public AchievementType( Integer _achievementTypeID){
		achievementTypeID = _achievementTypeID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAchievementTypeID() {
		return achievementTypeID;
	}
	public void setAchievementTypeID(Integer achievementTypeID) {
		this.achievementTypeID = achievementTypeID;
	}
	
}
