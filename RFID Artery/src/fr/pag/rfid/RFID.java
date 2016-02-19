package fr.pag.rfid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.pag.rfid.async.ThreadPool;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardBuilder;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.actions.ActionReader;
import fr.pag.rfid.handler.actions.ActionRole;

public class RFID {
	
	public static final int BAUD_RATE = 115200;
	public static final List<String> PORTS = Arrays.asList("COM3", "COM6");
	
	public static void main(String ... args) {
		
		for(Board board : searchBoards(1)) {
			
			 //Let the board listening
			board.setListening(true);
			board.setRole(BoardRole.READER);
			
		}
		
	}
	
	public static List<Board> searchBoards(int amount) {

		List<Board> boards = new ArrayList<Board>();
		
		for(int i = 0 ; i < amount ; i++) {

			//Run board
			Board board = new BoardBuilder()
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
