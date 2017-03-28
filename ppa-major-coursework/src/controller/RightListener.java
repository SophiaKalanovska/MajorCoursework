package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import welcome.Model;

public class RightListener implements ActionListener {

	private Model model;
	
	public RightListener(Model model) {
		
		this.model = model;
	
	}

	
	public void actionPerformed(ActionEvent e) {
		
		model.right();
		
	}

}
