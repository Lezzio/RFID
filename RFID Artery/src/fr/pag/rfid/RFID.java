package fr.pag.rfid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.pag.rfid.bluetooth.BluetoothManager;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardBuilder;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.database.IDatabase;
import fr.pag.rfid.database.MongoAdapter;
import fr.pag.rfid.database.SQLAdapter;
import fr.pag.rfid.handler.actions.ActionReader;
import fr.pag.rfid.handler.actions.ActionRole;

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
		
		try {
			BluetoothManager.initServer();
		} catch (IOException e) {
			Debugger.log("Bluetooth service is not available");
		}
		Debugger.log("Bluetooth = " + BluetoothManager.getState());
		
		for(Board board : searchBoards(1)) {
			
			 //Let the board listening
			board.setListening(true);
			board.execute(ActionRole.class, ActionRole.ASK_ROLE);
			
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
