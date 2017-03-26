package welcome;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import api.ripley.Ripley;
import gui.View;

public class WelcomePanel extends JPanel implements Observer{
	
	Ripley ripley;
	
	Model model;
	
	JLabel jlWelcome;
	JLabel jlMore;
	
	//View wesh;
	
	public WelcomePanel() {
		
		model = new Model();
			
		ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		
		//wesh = new View();
		
		setLayout(new BorderLayout());
		
		firstDisplayed();		
		
		setVisible(true);
		
	}
	
	public void firstDisplayed() {
		
//		model = new Model();
	//	model.addObserver(this);
	
		String toWelcome = ("<html>Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data<html>");
		
	/*	JLabel jlWelcome = new JLabel("<html>Welcome to the Ripley API v" + ripley.getVersion() +
				"<br>Please select from the dates above, in order to begin analysing UFO sighting data<html>",
				SwingConstants.CENTER);*/
		
		jlWelcome = new JLabel();
		jlWelcome.setLayout(new BorderLayout());
		
		jlWelcome.setText(toWelcome);
		jlWelcome.setHorizontalTextPosition(SwingConstants.CENTER);
		
		add(jlWelcome, BorderLayout.CENTER);
		
	}
	
/*	public void addToDisplay() {
		
		View view = new View();
		
		String more = ("<html>Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br> Date range selected: " + view.getJcbFrom());
		System.out.println("ppp");
		
		jlWelcome.setText(more);
	//	add(jlMore, BorderLayout.CENTER);
		
	}*/
	
	public void addToDisplay(String from, String to) {
		
		View view = new View();
		long start;
		long end;
		
		String more = ("<html>Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br> Date range selected: " + from + " - " + to + "." + "<br> <br>" + 
				"Grabbing data...");
		
		jlWelcome.setText(more);
		
		//model.dataGrabbing();
		
		//grabData(from, to);
	//	System.out.println("ppp");
		
	/*	start = System.currentTimeMillis();
		//yyyy-MM-dd HH:mm:ss
		ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
		
		end = System.currentTimeMillis() - start;
		
		more = ("<html>Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br> Date range selected: " + from + " - " + to + "." + "<br> <br>" + 
				"Grabbing data... <br> <br>" +
				" Data grabbed in " + end); 
		
		jlWelcome.setText(more);*/
	//	add(jlMore, BorderLayout.CENTER);
		
	}
	
	public void grabData(String from, String to) {
		
		long start;
		long end;
		
		start = System.currentTimeMillis();
		//yyyy-MM-dd HH:mm:ss
		ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
		
	    end = System.currentTimeMillis() - start;
		
		String data = ("<html>Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br> Date range selected: " + from + " - " + to + "." + "<br> <br>" + 
				"Grabbing data... <br> <br>" +
				" Data grabbed in " + end + "ms");
		
		System.out.println("hellooooooooo");
		
		jlWelcome.setText(data);
		
	}


	public void update(Observable arg0, Object arg1) {
		
		//Model model = (Model) arg0;
		
		//Controller controller = new Controller(model);
		
		//View view = new View();
		
		if (arg1.equals("Test 2")) {
			
			System.out.println("Update Welcome Panel");
			//addToDisplay();
		//	view.getJcbFrom();
		//	view.getJcbTo();


		}

		
	}

}
