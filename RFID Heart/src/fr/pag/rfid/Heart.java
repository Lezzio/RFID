package fr.pag.rfid;

import fr.pag.rfid.core.handler.BasketManager;
import fr.pag.rfid.core.socket.SocketManager;
import fr.pag.rfid.database.IDatabase;
import fr.pag.rfid.database.MongoAdapter;
import fr.pag.rfid.database.SQLAdapter;
import javafx.application.Application;

public class Heart {

	public final static String MARKET_NAME = "Boissy D'Anglas";
	public final static String PATH = "C:/Users/" + System.getProperty("user.name") + "/Desktop/PAG Manager";

	// Hexagonal stuff
	public final static IDatabase mongoAdapter = new MongoAdapter();
	public final static IDatabase sqlAdapter = new SQLAdapter();

	public static void main(String... args) {
		SocketManager.initServer();
		BasketManager.updateCache();
		Application.launch(GUI.class, args);
	}
	
}
