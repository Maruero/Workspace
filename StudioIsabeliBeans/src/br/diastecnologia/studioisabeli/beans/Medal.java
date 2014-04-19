package br.diastecnologia.studioisabeli.beans;

import br.diastecnologia.studioisabeli.enums.MedalType;

public class Medal {

	private MedalType type;
	private Integer points;
	public MedalType getType() {
		return type;
	}
	public void setType(MedalType type) {
		this.type = type;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
}
