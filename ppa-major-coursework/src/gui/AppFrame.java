package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class AFrame extends JFrame {

	public AFrame() {
		super();
		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
	}
	
	public void display() {
		setVisible(true);
	}
	
	public static void main (String[] args) {
		AFrame myFrame = new AFrame();
		myFrame.display();
	}
	
}
