package welcome;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import api.ripley.Ripley;

public class WelcomePanel extends JPanel implements Observer{
	
	Ripley ripley;
	
	public WelcomePanel() {
			
		ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		
		setLayout(new BorderLayout());
		
		firstDisplayed();		
		
		setVisible(true);
		
	}
	
	public void firstDisplayed() {
		
		String toWelcome = ("<html>Welcome to the Ripley API v" + ripley.getVersion() + 
				"<br> Please select from the dates above, in order to begin analysing UFO sighting data<html>");
		
	/*	JLabel jlWelcome = new JLabel("<html>Welcome to the Ripley API v" + ripley.getVersion() +
				"<br>Please select from the dates above, in order to begin analysing UFO sighting data<html>",
				SwingConstants.CENTER);*/
		
		JLabel jlWelcome = new JLabel();
		jlWelcome.setLayout(new BorderLayout());
		
		jlWelcome.setText(toWelcome);
		jlWelcome.setHorizontalTextPosition(SwingConstants.CENTER);
		
		add(jlWelcome, BorderLayout.CENTER);
		
	}


	public void update(Observable arg0, Object arg1) {
		
		Model model = (Model) arg0;
		
		Controller controller = new Controller(model);

		
	}

}
