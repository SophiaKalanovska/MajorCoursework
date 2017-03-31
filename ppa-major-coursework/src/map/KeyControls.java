//ignore this file as it is unused

package map;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControls extends KeyAdapter {
	
	private MapCanvas mc;
	
	public KeyControls(MapCanvas mc) {
		this.mc = mc;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_CLOSE_BRACKET) {
			//mc.zoomIn();
		} else if (e.getKeyCode()==KeyEvent.VK_OPEN_BRACKET) {
			//mc.zoomOut();
		}
	}


}
