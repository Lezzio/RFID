package fr.pag.rfid.board;

import java.util.ArrayList;

public class BoardManager {
	
	private final static ArrayList<Board> boards = new ArrayList<Board>();

	public static ArrayList<Board> getBoards() {
		return boards;
	}
	public static void addBoard(Board board) {
		boards.add(board);
	}
	public static void removeBoard(Board board) {
		boards.remove(board);
	}
	
	/**
	 * Broadcast execution to all connected boards regarding specified parameters
	 * @param container Class Holder
	 * @param indication Useful for following protocol
	 */
	public static void broadcast(Class<?> container, int indication) {
		for(Board board : boards) {
			board.execute(container, indication);
		}
	}

}
