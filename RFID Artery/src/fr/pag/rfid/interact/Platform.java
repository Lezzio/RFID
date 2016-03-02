package fr.pag.rfid.interact;

import fr.pag.rfid.Protocol;
import fr.pag.rfid.board.BoardManager;
import fr.pag.rfid.handler.actions.ActionRelay;

public class Platform {
	
	public void openCloseGate() throws InterruptedException {
		System.out.println("Platform");
		openGate();
		Thread.sleep(5000);
		closeGate();
	}
	public void openGate() {
		BoardManager.broadcast(ActionRelay.class, Protocol.GATE_OPEN);
	}
	public void closeGate(){
		BoardManager.broadcast(ActionRelay.class, Protocol.GATE_CLOSE);
	}

}