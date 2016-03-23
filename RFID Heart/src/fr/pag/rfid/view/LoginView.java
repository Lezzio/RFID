package fr.pag.rfid.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginView extends Scene {

	public LoginView() throws IOException {
		super(FXMLLoader.load(LoginView.class.getResource("Login-scene.fxml")));
	}

}
