package map;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;

import org.apache.batik.swing.JSVGCanvas;

public class AlienIcon extends JSVGCanvas {
	
	public AlienIcon() {
		super();
		File source = new File("alien.svg");
		setBackground(new Color(0, true));
		try {
			setURI(source.toURL().toString());
		} catch (MalformedURLException e) {
			System.out.println("Could not find alien SVG.");
			e.printStackTrace();
		}
	}
}
*/

public class AlienIcon extends JLabel {
	public AlienIcon(int width) {
		super();
		ImageIcon alien = new ImageIcon("resources/alien.png");
		Image resizedAlien = alien.getImage().getScaledInstance(width,  -1, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(resizedAlien));
	}
}