package fr.pag.rfid.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ArteryView extends Scene {

	public ArteryView() throws IOException {
		super(FXMLLoader.load(ArteryView.class.getResource("Artery-scene.fxml")));
	}

}
