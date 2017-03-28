package map;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.MalformedURLException;

import org.apache.batik.swing.JSVGCanvas;

public class MapCanvas extends JSVGCanvas {
	
	private double scaleFactor;
	private JSVGCanvas.LocationListener receiveMouse;

	public MapCanvas() {
		super();
		scaleFactor = 1;
		initMouseListener();
		File sourceMap = new File("resources/map.svg");
		try {
			setURI(sourceMap.toURL().toString());
		} catch (MalformedURLException e) {
			System.out.println("Could not find map.");
			e.printStackTrace();
		}
		setBackground(new Color(0, true));
		setLayout(new GridBagLayout());
	}
	
	public void initMouseListener() {
		receiveMouse = new LocationListener();
	}
	
	public void zoomIn() {
		if (scaleFactor + 0.5 > 3) {
			scaleFactor = 2.75;
		} else {
			scaleFactor += 0.5;
			AffineTransform zoom = new AffineTransform();
			zoom.scale(scaleFactor, scaleFactor);
			setRenderingTransform(zoom, true);
		}
	}
	
	public void zoomOut() {
		if (scaleFactor - 0.5 < 0) {
			scaleFactor = 0.25;
		} else {
			scaleFactor -= 0.5;
			AffineTransform zoom = new AffineTransform();
			zoom.scale(scaleFactor, scaleFactor);
			setRenderingTransform(zoom, true);
		}
	}
	
}
