package fr.pag.rfid.handler.actions;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.instrument.Instrumentation;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;

/*
 * BoardAction which is used to assign role
 */
public class ActionRole implements BoardAction {
	
	public static final Integer ASK_ROLE = 3;
	
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
		//Send each time until a role has been found
		this.execute(holder, 0);
		holder.setRole(BoardRole.getById(Integer.valueOf(data)));
	}

	@Override
	public boolean execute(Board holder, int indication) {
		final OutputStream outStream = holder.getSerialPort().getOutputStream();
		try {
			outStream.write(ASK_ROLE);
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
