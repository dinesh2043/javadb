package Database;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		GUI graphicalUserInterface = new GUI();

		graphicalUserInterface.setSize(650, 350);
		graphicalUserInterface.createGUI();
		graphicalUserInterface.setVisible(true);
		graphicalUserInterface.setTitle("Welcome to Personal Database");
		graphicalUserInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
