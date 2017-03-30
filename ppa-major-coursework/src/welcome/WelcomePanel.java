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
	
		String toWelcome = ("<html> <br><br><br><br><br><br> Welcome to the Ripley API v" + ripley.getVersion() + 
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

	public void addToDisplay(String from, String to) {
		
		View view = new View();
		long start;
		long end;
		
		String more = ("<html> <br><br><br><br><br><br> Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br> Date range selected: " + from + " - " + to + "." + "<br> <br>" + 
				"Grabbing data...");
		
		jlWelcome.setText(more);

	}
	
	public void outOfBound() {
		
		
		jlWelcome.setText("<html> <br><br><br><br><br><br> Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br>"
				+ "The range you selected is incorrect. Please select a 'from' date lower thar the 'to' date<html>");
		
		
	}
	
	public void grabData(String from, String to) {
		
		long start;
		long end;
		
		start = System.currentTimeMillis();
		//yyyy-MM-dd HH:mm:ss
		ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-12-31 23:59:59");
		
	    end = System.currentTimeMillis() - start;
		
	    String time = "";
	    // To determine the time to be displayed
	    if (end < 60000) {

	    	 long seconds = end / 1000;
	    	 long ms = end - seconds;
	    	 
	    	 time = seconds + " seconds, " + ms + " milliseconds";
	    	
	    } else {
	    	
	    	long minutes = end / 60000;
	    	long seconds = (end - minutes) / 1000;
	    	long ms = end - (seconds + minutes);
	    	
	    	time = minutes + " minutes," + seconds  + " seconds," + ms + " ms";
	    	
	    }   
	    
	    
		String data = ("<html> <br><br><br><br><br><br> Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data" +
				"<br> <br> Date range selected: " + from + " - " + to + "." + "<br> <br>" + 
				"Grabbing data... <br> <br>" +
				" Data grabbed in " + time);
		
		
		System.out.println("hellooooooooo");
		
		jlWelcome.setText(data);
		
	}
	

	public void update(Observable arg0, Object arg1) {
		
		//Model model = (Model) arg0;
		
		//Controller controller = new Controller(model);
		
		//View view = new View();


		
	}

}
