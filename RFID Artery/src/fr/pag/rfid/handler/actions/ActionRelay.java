package fr.pag.rfid.handler.actions;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;

public class ActionRelay implements BoardAction {

	@Override
	public void handle(Board holder, String data) {
		
	}

	@Override
	public BoardRole getNeededRole() {
		return BoardRole.CONTROLLER;
	}
	
	@Override
	public boolean isAsync() {
		return true;
	}
	
	

}
