package fr.pag.rfid.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ArteryController {
	
	@FXML
	private static TextFlow notifications;
	
	
	public static void sendNotifications(String msg) {
		notifications.getChildren().add(new Text(msg + "\n"));
	}
	
	

}
