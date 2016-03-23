package fr.pag.rfid.controller;

import java.io.IOException;
import java.sql.Connection;

import fr.pag.rfid.GUI;
import fr.pag.rfid.Heart;
import fr.pag.rfid.database.IDatabase;
import fr.pag.rfid.database.SQLAdapter;
import fr.pag.rfid.view.DashboardView;
import fr.pag.rfid.view.ProductEditView;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField userField;
	@FXML
	private TextField passwordField;
	@FXML
	private Button connectButton;
	@FXML
	private Label connectionState;
	
	@FXML
	private void connectClicked() {
		connectButton.setCursor(Cursor.WAIT);
		connectionState.setText("Connexion en cours...");
		
		String dbUser = "pag";
		String dbPassword = "131817pag&";
		String dbName = "pag1";
		
		final IDatabase sqlAdapter = Heart.sqlAdapter;
		Connection connection = (Connection) sqlAdapter.connect(SQLAdapter.address, dbUser, dbPassword, dbName);
		if(userField.getText().length() > 0 && passwordField.getText().length() > 0 && sqlAdapter.isValid(userField.getText(), passwordField.getText(), connection)) {
			GUI.setScene(GUI.dashboardView);
		} else {
			connectionState.setText("Connexion refusé!");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Credentials error");
			alert.setContentText("Credentials are not valid, please retry or contact your administrator");
			alert.show();
		}

		connectButton.setCursor(Cursor.HAND);
	}
	

}
