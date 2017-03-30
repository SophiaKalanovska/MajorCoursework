package map;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AlienIcon extends JLabel {
	
	private static ImageIcon icon = new ImageIcon("resources/alien.png"); //not in constructor as only needs to run once
	
	public AlienIcon(ImageIcon icon, int width) {
		super();
		AlienIcon.icon = icon;
	}
	
}
