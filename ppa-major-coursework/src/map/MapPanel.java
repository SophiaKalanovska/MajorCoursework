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

	public MapPanel(Ripley ripley, String fromYear, String toYear) {
		super();
		this.ripley = ripley;
		setPreferredSize(new Dimension(930, 595));
		setLayout(new OverlayLayout(this));
		//setFocusable(true);
		//requestFocusInWindow();
		myCanvas = new MapCanvas(ripley, fromYear, toYear);
		add(myCanvas, BorderLayout.CENTER);
		//addKeyListener(new KeyControls(myCanvas));
	}
	
	public void setCanvas(MapCanvas canvas) {
		if (myCanvas != null) {
			this.remove(myCanvas);
		}
		myCanvas = canvas;
		add(myCanvas, BorderLayout.CENTER);
		repaint();
	}
	
	public MapPanel(Ripley ripley) {
		super();
		this.ripley = ripley;
		setPreferredSize(new Dimension(930, 595));
		setLayout(new OverlayLayout(this));
		myCanvas = new MapCanvas(ripley);
	}
	
	public JSVGCanvas getCanvas() {
		return myCanvas;
	}

	public static void main(String[] args) {
		JFrame newFrame = new JFrame("Map");
		Ripley ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		MapPanel mp = new MapPanel(ripley, "1960", "1990");
		newFrame.add(mp);
		newFrame.pack();
		newFrame.setLocationRelativeTo(null);
		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Incident> incidents = ripley.getIncidentsInRange("1960-01-01 00:00:00", "1990-12-31 23:59:59");
		for (int i=0; i<incidents.size(); i++) {
			System.out.println(incidents.get(i).getState());
		}
	}

}