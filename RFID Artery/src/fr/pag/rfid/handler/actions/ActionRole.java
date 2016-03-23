package fr.pag.rfid.handler.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;
import fr.pag.rfid.utils.Debugger;

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
		BoardRole role = BoardRole.getById(Integer.valueOf(data));
		Debugger.log(holder.getName() + " role detected -> " + role);
		holder.setRole(role);
	}

	@Override
	public boolean execute(Board holder, int indication) {
		
		//Wait for reset
		try {
			Thread.sleep(2000); // Block (asynchronously)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		final OutputStream outStream = holder.getSerialPort().getOutputStream();
		try {
			outStream.write(indication);
			outStream.flush();
			Debugger.log("Asking role...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final InputStream inStream = holder.getSerialPort().getInputStream();
		try {
			this.handle(holder, String.valueOf(inStream.read() - 48)); //Subtract 48 for ASCII conversion
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
