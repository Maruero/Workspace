package br.diastecnologia.studioisabeli.dtos;

public class CustomerRanking {

	private String name;
	private Integer goldCount;
	private Integer silverCount;
	private Integer bronzeCount;
	private Integer points;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
