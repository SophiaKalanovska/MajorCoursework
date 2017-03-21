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
import welcome.WelcomePanel;
import api.ripley.Ripley;


public class View extends JFrame implements Observer {
	
	private static JComboBox<String> jcbFrom;
	private static JComboBox<String> jcbTo;
	private static JLabel jlFrom;
	JLabel jlTo;
	JButton jbLeft;
	JButton jbRight;
	JPanel jpCombBox;
	JPanel jpBottom;
	JLabel jlInfo;
	JPanel jpNorth;
	JPanel jpCenter;
	
	
	Ripley ripley;
	
	Model model = new Model();

	public View() {
		
		super();
		
		ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		
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
		
		jcbFrom.setSelectedItem("--");
		jcbTo.setSelectedItem("--");
		
		jcbFrom.addItemListener(new Controller(model));
		
		String s = (String)jcbFrom.getSelectedItem();
		System.out.println(s);
		
		jpCombBox = new JPanel(new FlowLayout());
		jpBottom = new JPanel(new BorderLayout());
		
		jlInfo = new JLabel(ripley.getLastUpdated(), SwingConstants.CENTER);
		
		 for (int i = ripley.getStartYear(); i < ripley.getLatestYear(); i++) {		
				
				jcbFrom.addItem(i + "");
				jcbTo.addItem(i + "");
				
			}
		
		
		
		jpCombBox.add(jlFrom);
		jpCombBox.add(jcbFrom);
		jpCombBox.add(jlTo);
		jpCombBox.add(jcbTo);
		
		jpBottom.add(jbLeft, BorderLayout.WEST);
		jpBottom.add(jbRight, BorderLayout.EAST);
		jpBottom.add(jlInfo, BorderLayout.CENTER);
		
		
		jpNorth = new JPanel(new BorderLayout());
		jpCenter = new JPanel(new BorderLayout());
		
		
		JLabel jlWelcome = new JLabel("<html>Welcome to the Ripley API v" + ripley.getVersion() +
				"<br>Please select from the dates above, in order to begin analysing UFO sighting data<html>",
				SwingConstants.CENTER);		
		
		JPanel welcome = new WelcomePanel();
		
		jpCenter.add(welcome, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		
		jpNorth.add(jpCombBox, BorderLayout.EAST);
		
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpBottom, BorderLayout.SOUTH);
		
	}
	
	public void update(Observable arg0, Object arg1) {
		
		Model model = (Model) arg0;
		
		Controller controller = new Controller(model);
		
		if (arg1.equals("Test")) {

			System.out.println("ca");
			
		}
	}
	
	public static void main (String[] args) {
		
		View view = new View();
		
		JPanel welcome = new WelcomePanel();
		
		Model model = new Model();
		
		
		Controller controller = new Controller(model);
		
		view.display();
		
		model.addObserver(view);
		model.addObserver((Observer) welcome);
		
	}

	
}
