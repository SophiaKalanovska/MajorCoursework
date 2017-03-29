package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import welcome.Model;

public class LeftListener implements ActionListener {
	
	private Model model;
	
	public LeftListener(Model model) {
		
		this.model = model;
		
	}

	public void actionPerformed(ActionEvent e) {
		
		model.left();
		
	}
	
	

}
