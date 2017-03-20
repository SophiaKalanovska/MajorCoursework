package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Model.Model;

public class Controller implements ItemListener {
	
	Model model = new Model();
	
	public Controller(Model model) {
		
		this.model = model;
		
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		
		if (event.getStateChange() == ItemEvent.SELECTED) {
			
	          Object item = event.getItem();
	          System.out.println(event);
	          model.inform();
	          // do something with object
	       }
	}       

}
