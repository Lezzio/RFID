package fr.pag.rfid.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DashboardView extends Scene {

	public DashboardView() throws IOException {
		super(FXMLLoader.load(DashboardView.class.getResource("Dashboard-scene.fxml")));
	}

}