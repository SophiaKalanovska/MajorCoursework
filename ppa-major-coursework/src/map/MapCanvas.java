package map;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JLabel;

import org.apache.batik.swing.JSVGCanvas;

import api.ripley.Ripley;

public class MapCanvas extends JSVGCanvas {
	
	private double scaleFactor;
	private Ripley ripley;
	private JSVGCanvas.LocationListener receiveMouse;

	public MapCanvas() {
		super();
		//initMouseListener();
		File sourceMap = new File("resources/map.svg"); //assign source file
		try {
			setURI(sourceMap.toURL().toString());
		} catch (MalformedURLException e) {
			System.out.println("Could not find map.");
			e.printStackTrace();
		}
		setBackground(new Color(0, true));
		setLayout(null);
	}
	
	public MapCanvas(Ripley ripley, String fromYear, String toYear) {
		this();
		this.ripley = ripley;
		AlienIconManager pinGenerator = new AlienIconManager(ripley, fromYear, toYear);
		AlienPin[] pins = pinGenerator.getAllPins();
		for (int i=0; i<pins.length; i++) {
			if (pins[i] != null) {
				add(pins[i]);
				pins[i].addMouseListener(new PinClickListener(pinGenerator.getIncidents()));
			}
		}
	}
	
	public MapCanvas(Ripley ripley) {
		this();
		this.ripley = ripley;
	}
	
	//type whatever you want
	
	/*public void initMouseListener() {
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
	}*/
	
	//to be invoked AFTER PLACEMENT
	public void initialSize() {
		
	}
}
