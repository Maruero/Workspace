package br.diastecnologia.studioisabeli.enums;

public enum MedalType{

	GOLD, SILVER, BRONZE;
	
	public static Integer getIndex( MedalType type ){
		for( int i=0 ; i<MedalType.values().length ; i++ ){
			if( MedalType.values()[i] == type ){
				return i;
			}
		}
		return -1;
	}
	
}
