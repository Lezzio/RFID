package fr.pag.rfid.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	
	JPanel mainContainer = new JPanel();
	
	public GUI() {
		build();
	}
	
	public void build() {
		this.setTitle("PAG Market Manager");
		this.setSize(600, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setContentPane(mainContainer);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mainContainer.setBackground(new Color(72, 143, 255));
	}

}
