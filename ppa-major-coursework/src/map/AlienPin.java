package map;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AlienPin extends JLabel {
	
	private String state;
	
	public AlienPin(String state) {
		super();
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
}
