package pad;

import java.util.Observer;

import gui.View;
import welcome.Controller;
import welcome.Model;
import welcome.WelcomePanel;

public class Main {
	
	public static void main (String[] args) {		
		
		WelcomePanel welcome = new WelcomePanel();
				
		Model model = new Model();
		
		View view = new View();
		
		Controller controller = new Controller(model);
		
	
		//View view = new View(controller);
		
	    //model.addObserver(view);

		view.display();
		
	}


}
