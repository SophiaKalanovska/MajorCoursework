package map;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import org.apache.batik.swing.JSVGCanvas;

import api.ripley.Incident;
import api.ripley.Ripley;

public class MapPanel extends JPanel {

	private MapCanvas myCanvas;
	private Ripley ripley;

	public MapPanel(Ripley ripley) {
		super();
		this.ripley = ripley;
		setPreferredSize(new Dimension(800, 500));
		setLayout(new OverlayLayout(this));
		//setFocusable(true);
		//requestFocusInWindow();
		myCanvas = new MapCanvas(ripley);
		add(myCanvas, BorderLayout.CENTER);
		//addKeyListener(new KeyControls(myCanvas));
	}
	
	public JSVGCanvas getCanvas() {
		return myCanvas;
	}

	public static void main(String[] args) {
		JFrame newFrame = new JFrame("Map");
		Ripley ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		MapPanel mp = new MapPanel(ripley);
		newFrame.add(mp);
		newFrame.pack();
		newFrame.setLocationRelativeTo(null);
		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Incident> incidents = ripley.getIncidentsInRange("1990-01-01 00:00:00", "2000-12-31 23:59:59");
		for (int i=0; i<incidents.size(); i++) {
			System.out.println(incidents.get(i).getState());
		}
	}

}