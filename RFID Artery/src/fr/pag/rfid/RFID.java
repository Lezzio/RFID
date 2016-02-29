package fr.pag.rfid;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.pag.rfid.bluetooth.BluetoothManager;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardBuilder;
import fr.pag.rfid.database.IDatabase;
import fr.pag.rfid.database.MongoAdapter;
import fr.pag.rfid.database.SQLAdapter;
import fr.pag.rfid.handler.actions.ActionReader;
import fr.pag.rfid.handler.actions.ActionRole;
import fr.pag.rfid.socket.SocketManager;

public class RFID {
	
	public static final int BAUD_RATE = 115200;
	public static final List<String> PORTS = Arrays.asList("COM3", "COM6");
	
	public static PrintWriter printWriter;
	
	//Hexagonal stuff
	public static IDatabase sqlDatabase = new SQLAdapter();
	public static IDatabase mongoDatabase = new MongoAdapter();
	
	
	public static void main(String ... args) {
		
		//Uncomment for local debugging
		printWriter = new PrintWriter(System.out, true);

		//Socket
		Debugger.log("Looking for receivers...");
		SocketManager.findReceivers(SocketManager.getLocalAddress().getHostAddress(), Protocol.PORT_HEART, 40);
		if(SocketManager.getReceivers().isEmpty()) {
			Debugger.log("WARNING: Socket not found!");
		}
		
		//Bluetooth
		try {
			Debugger.log("Starting bluetooth service...");
			BluetoothManager.initServer();
			Debugger.log("Bluetooth = " + BluetoothManager.getState());
		} catch (IOException e) {
			Debugger.log("WARNING: Bluetooth service is not available!");
		}
		
		//Board
		for(Board board : searchBoards(1)) {
			
			 //Let the board listening
			board.setListening(true);
			board.execute(ActionRole.class, Protocol.ASK_ROLE);
			
		}
		
	}
	
	public static List<Board> searchBoards(int amount) {

		List<Board> boards = new ArrayList<Board>();
		
		for(int i = 0 ; i < amount ; i++) {

			//Run board
			Board board = new BoardBuilder()
					.setName("Arduino " + i)
					.seekPort("Arduino", true)
					.setBaudRate(BAUD_RATE)
					.addAction(new ActionRole())
					.addAction(new ActionReader("#"))
					.build();
			
			boards.add(board);
			
		}
		
		return boards;
	}
	

}
