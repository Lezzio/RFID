package fr.pag.rfid.board;

public enum BoardRole {

	READER(), CONTROLLER(), UNKNOWN();
	
	public static BoardRole getById(int id) {
		switch(id) {
		
		case 0: return READER;
		case 1: return CONTROLLER;
		
		default: return UNKNOWN;
		
		}
	}
	
}
