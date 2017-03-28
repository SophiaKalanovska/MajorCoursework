package map;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import api.ripley.Ripley;


public class AlienIcon extends JLabel {
	
	private Ripley ripley;
	
	public AlienIcon(String state, Ripley ripley) {
		this(getWidth(state, ripley));
	}
	
	public AlienIcon(int width) {
		super();
		ImageIcon alien = new ImageIcon("resources/alien.png");
		Image resizedAlien = alien.getImage().getScaledInstance(width,  -1, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(resizedAlien));
	}
	
	public AlienIcon() {
		super();
		ImageIcon alien = new ImageIcon("resources/alien.png");
		setIcon(alien);
	}
	
	public static int getWidth(String state, Ripley ripley) {
		return 0;  //not implemented yet
		//return appropriate width based on state
	}
}