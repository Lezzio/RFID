package fr.pag.rfid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import fr.pag.rfid.view.DashboardView;
import fr.pag.rfid.view.LoginView;
import fr.pag.rfid.view.ProductEditView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI extends Application {
	
	public static Stage stage;
	
	public static DashboardView dashboardView;
	public static ProductEditView productEditView;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		dashboardView = new DashboardView();
		productEditView = new ProductEditView();
		
		stage = primaryStage;

        try {
			primaryStage.getIcons().add(new Image(new FileInputStream(new File(Heart.PATH + "/assets/img/pag-logo.jpg"))));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

        primaryStage.setScene(new LoginView());
        primaryStage.setTitle("PAG Manager");
        primaryStage.show();
	}
	
	public static void setScene(Scene scene) {
		stage.setScene(scene);
	}

}
