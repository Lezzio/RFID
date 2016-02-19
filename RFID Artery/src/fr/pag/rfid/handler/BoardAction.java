package fr.pag.rfid.handler;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;

public interface BoardAction {
	
	public void handle(Board holder, String data);
	public boolean execute(Board holder, int indication);
	public BoardRole getNeededRole();
	public boolean isAsync();

}
