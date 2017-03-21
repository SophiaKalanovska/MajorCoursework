package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import welcome.Controller;
import welcome.Model;

import api.ripley.Ripley;


public class View extends JFrame implements Observer {
	
	//Model model = new Model();

	public View() {
		
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//need to set some sort of layout manager
		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		initWidgets();
		
	}
	
	public void display() {
		setVisible(true);
	}
	
	public void initWidgets() {
		//add assignments (and adding) of JPanels here
		//String[] dates = {"2016", "2017"};
		
		JComboBox<String> jcbFrom = new JComboBox();
		JComboBox<String> jcbTo = new JComboBox();
		JLabel jlFrom = new JLabel("From:");
		JLabel jlTo = new JLabel("To:");
		
		JButton jbLeft = new JButton("<");
		JButton jbRight = new JButton(">");
		jbLeft.setEnabled(false);
		jbRight.setEnabled(false);
		
		jcbFrom.addItem("--");
		jcbTo.addItem("--");
		
		jcbFrom.setSelectedItem("--");
		jcbTo.setSelectedItem("--");
		
		//jcbFrom.addItemListener(new Controller(model));
		
		String s = (String)jcbFrom.getSelectedItem();
		System.out.println(s);
		
		JPanel jpCombBox = new JPanel(new FlowLayout());
		JPanel jpBottom = new JPanel(new BorderLayout());
		
		Ripley ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		JLabel jlInfo = new JLabel(ripley.getLastUpdated(), SwingConstants.CENTER);
		
		 for (int i = ripley.getStartYear(); i < ripley.getLatestYear(); i++) {		
				
				jcbFrom.addItem(i + "");
				jcbTo.addItem(i + "");
				
			}
		
		JLabel jlWelcome = new JLabel("<html>Welcome to the Ripley API v" + ripley.getVersion() +
				"<br>Please select from the dates above, in order to begin analysing UFO sighting data<html>",
				SwingConstants.CENTER);
		
		
		jpCombBox.add(jlFrom);
		jpCombBox.add(jcbFrom);
		jpCombBox.add(jlTo);
		jpCombBox.add(jcbTo);
		
		jpBottom.add(jbLeft, BorderLayout.WEST);
		jpBottom.add(jbRight, BorderLayout.EAST);
		jpBottom.add(jlInfo, BorderLayout.CENTER);
		
		
		JPanel jpNorth = new JPanel(new BorderLayout());
		JPanel jpCenter = new JPanel();
		
		jpCenter.add(jlWelcome);
		
		this.setLayout(new BorderLayout());
		
		jpNorth.add(jpCombBox, BorderLayout.EAST);
		
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpBottom, BorderLayout.SOUTH);
		
	}
	
	public void update(Observable arg0, Object arg1) {
		
	/*	Model model = (Model) arg0;
		
		Controller controller = new Controller(model);
		
		if (arg1.equals("Hello")) {
			
			
			System.out.println("ca");
			
		}*/
	}
	
	public static void main (String[] args) {
		
		View view = new View();
		
		//Model model = new Model();
		
		//Controller controller = new Controller(model);
		
		view.display();
		
		//model.addObserver(view);
		
		//Ripley ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		//System.out.println(ripley.getLastUpdated());
	}

	
}
