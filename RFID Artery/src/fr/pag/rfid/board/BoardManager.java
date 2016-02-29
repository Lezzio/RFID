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

}
