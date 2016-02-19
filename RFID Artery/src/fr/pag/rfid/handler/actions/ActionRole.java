package fr.pag.rfid.handler.actions;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;

/*
 * BoardAction which is used to assign role
 */
public class ActionRole implements BoardAction {
	
	@Override
	public BoardRole getNeededRole() {
		return BoardRole.UNKNOWN;
	}
	
	@Override
	public boolean isAsync() {
		return true;
	}
	
	@Override
	public void handle(Board holder, String data) {
		//Send role asking each time
		
		
		holder.setRole(BoardRole.getById(Integer.valueOf(data)));
	}

}
