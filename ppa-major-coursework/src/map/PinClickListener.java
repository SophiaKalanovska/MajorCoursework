package map;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class PinClickListener extends MouseAdapter {
	
	@Override
	public void mouseClicked(MouseEvent E) {
		(new StateScreen(((AlienPin) E.getComponent()).getState())).setVisible(true);
	}
}
