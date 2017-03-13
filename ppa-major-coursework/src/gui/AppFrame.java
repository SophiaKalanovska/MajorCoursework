package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class AppFrame extends JFrame {

	public AppFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//need to set some sort of layout manager
		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		initWidgets();
	}
	
	public void display() {
		setVisible(true);
	}
	
	public void initWidgets() {
		//add assignments (and adding) of JPanels here
	}
	
	public static void main (String[] args) {
		AppFrame myFrame = new AppFrame();
		myFrame.display();
	}
	
}
