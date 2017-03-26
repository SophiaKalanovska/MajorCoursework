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
import javax.swing.SwingConstants;

import welcome.Controller;
import welcome.Model;
import welcome.WelcomePanel;
import api.ripley.Ripley;


public class View extends JFrame implements Observer {
	
	JComboBox<String> jcbFrom;
	JComboBox<String> jcbTo;
	
	JLabel jlFrom;
	JLabel jlTo;
	
	JButton jbLeft;
	JButton jbRight;
	
	JPanel jpCombBox;
	JPanel jpBottom;
	
	JPanel jpNorth;
	JPanel jpCenter;
	
	WelcomePanel welcome;
	
	boolean isDataReady;
	
	Ripley ripley;

	//Controller controller;
	
	
	public View() {
		
		super();
		
		//this.controller = controller;
		
		ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//need to set some sort of layout manager
		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		
		isDataReady = false;
		
		initWidgets();
		
	}
	
	public void display() {
		setVisible(true);
	}
	
	public void initWidgets() {
		//add assignments (and adding) of JPanels here
		
		welcome = new WelcomePanel();
		
		Model model = new Model();
		//model.addObserver(welcome);
		model.addObserver(this);
		
		jcbFrom = new JComboBox<String>();
		jcbTo = new JComboBox<String>();
		jlFrom = new JLabel("From:");
		jlTo = new JLabel("To:");
		
		jbLeft = new JButton("<");
		jbRight = new JButton(">");
		jbLeft.setEnabled(false);
		jbRight.setEnabled(false);
		
		jcbFrom.addItem("--");
		jcbTo.addItem("--");
		
		//jcbFrom.setSelectedItem("2010");
		//jcbTo.setSelectedItem("--");
		Controller controller = new Controller(model);
		
		jcbFrom.addItemListener(controller);
		jcbTo.addItemListener(controller);
		
		String s = (String)jcbFrom.getSelectedItem();
		System.out.println(s);
		
		
		jpCombBox = new JPanel(new FlowLayout());
		jpBottom = new JPanel(new BorderLayout());
		
		JLabel jlInfo = new JLabel(ripley.getLastUpdated(), SwingConstants.CENTER);
		
		 for (int i = ripley.getStartYear(); i <= ripley.getLatestYear(); i++) {		
				
				jcbFrom.addItem(i + "");
				jcbTo.addItem(i + "");
				
			}
		
	/*	JLabel jlWelcome = new JLabel("<html>Welcome to the Ripley API v" + ripley.getVersion() +
				"<br>Please select from the dates above, in order to begin analysing UFO sighting data<html>",
				SwingConstants.CENTER);
		*/
		
		jpCombBox.add(jlFrom);
		jpCombBox.add(jcbFrom);
		jpCombBox.add(jlTo);
		jpCombBox.add(jcbTo);
		
		jpBottom.add(jbLeft, BorderLayout.WEST);
		jpBottom.add(jbRight, BorderLayout.EAST);
		jpBottom.add(jlInfo, BorderLayout.CENTER);
		
		
		jpNorth = new JPanel(new BorderLayout());
		jpCenter = new JPanel();
		
		//WelcomePanel welcome = new WelcomePanel();
		jpCenter.add(welcome);
		
		this.setLayout(new BorderLayout());
		
		jpNorth.add(jpCombBox, BorderLayout.EAST);
		
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpBottom, BorderLayout.SOUTH);
		
	}
	
	public String getJcbFrom() {
		
		String jcbFromValue = (String)jcbFrom.getSelectedItem();
		System.out.println("jcbFrom: " + jcbFromValue);
		return jcbFromValue;
			
	}
	
	public String getJcbTo() {
		
		String jcbToValue = (String)jcbTo.getSelectedItem();
		System.out.println("jcbTo: " + jcbToValue);
		return jcbToValue;
		
	}
	
	public void updateWelcomePanel(String from, String to) {
		
		welcome.addToDisplay(from, to); 
		
		
	}
	
	public void grabData(String from, String to) {
		
		System.out.println("hello");
		welcome.grabData(from, to);
		
	}
	
	public void update(Observable arg0, Object arg1) {
		
		Model model = (Model) arg0;
		
	//	Controller controller = new Controller(model);
		
		if (arg1.equals("Test 2")) {
			
			getJcbFrom();
			getJcbTo();
			updateWelcomePanel(getJcbFrom(), getJcbTo());

			
		//	grabData(getJcbFrom(), getJcbTo());
			
			System.out.println("Update View");
			isDataReady = true;
			model.dataGrabbing();
					
		}
		
		if (arg1.equals("Grab Data")) {
			
			System.out.println("pute");
			grabData(getJcbFrom(), getJcbTo());
			
		}
		
		
		
	}
	
	/*public static void main (String[] args) {	
		
		Model model = new Model();
		
		Controller controller = new Controller(model);
		
		View view = new View(controller);
		
		WelcomePanel welcome = new WelcomePanel();
		
		model.addObserver(view);
		model.addObserver((Observer) welcome); 
		
		view.display();
		
		
		//Ripley ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		//System.out.println(ripley.getLastUpdated());
	}*/

	
}
