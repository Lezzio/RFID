package fr.pag.rfid.controller;

import java.io.IOException;

import fr.pag.rfid.GUI;
import fr.pag.rfid.view.ProductEditView;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DashController {
	
	@FXML
	private TextFlow notifications;
	
	
	public void sendNotifications(String msg) {
		notifications.getChildren().add(new Text(msg + "\n"));
	}
	
	@FXML
	private void fileEditorClicked() {
		try {
			GUI.setScene(new ProductEditView());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize() {
		this.sendNotifications("Bienvenue dans PAG Manager");
	}

}