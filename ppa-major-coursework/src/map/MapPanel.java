package map;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import org.apache.batik.swing.JSVGCanvas;

public class MapPanel extends JPanel {

	private MapCanvas myCanvas;
	private double scaleFactor;
	//private Ripley ripley;

	public MapPanel() { //pass ripley object
		super();
		setLayout(new OverlayLayout(this));
		setFocusable(true);
		requestFocusInWindow();
		myCanvas = new MapCanvas();
		add(myCanvas, BorderLayout.CENTER);
		addKeyListener(new KeyControls(myCanvas));
		myCanvas.setLayout(new BorderLayout());
	}
	
	public JSVGCanvas getCanvas() {
		return myCanvas;
	}

	public static void main(String[] args) {
		JFrame newFrame = new JFrame("Map");
		MapPanel mp = new MapPanel();
		newFrame.add(mp);
		newFrame.pack();
		newFrame.setLocationRelativeTo(null);
		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}