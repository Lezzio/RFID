package fr.pag.rfid.interact;

import fr.pag.rfid.board.BoardManager;
import fr.pag.rfid.handler.actions.ActionRelay;
import fr.pag.rfid.utils.Protocol;

public class Platform {
	
	public void openCloseGate() throws InterruptedException {
		System.out.println("Platform");
		openGate();
		Thread.sleep(3500);
		closeGate();
		Thread.sleep(3500);
		stopGate();
	}
	public void openGate() {
		BoardManager.broadcast(ActionRelay.class, Protocol.GATE_OPEN);
	}
	public void closeGate() {
		BoardManager.broadcast(ActionRelay.class, Protocol.GATE_CLOSE);
	}
	public void stopGate() {
		BoardManager.broadcast(ActionRelay.class, Protocol.GATE_STOP);
	}

}