package br.diastecnologia.studioisabeli.enums;

public enum ClassStatus {

	SCHEDULED, DONE, NOT_DONE;
	
	public static Integer getIndex( ClassStatus status ){
		for( int i=0 ; i<ClassStatus.values().length ; i++ ){
			if( ClassStatus.values()[i] == status ){
				return i;
			}
		}
		return -1;
	}
}
