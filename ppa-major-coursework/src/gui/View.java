package gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import welcome.Controller;
import welcome.GrabListener;
import welcome.Model;
import welcome.WelcomePanel;
import api.ripley.Ripley;
import controller.LeftListener;
import controller.RightListener;
import map.MapCanvas;
import map.MapPanel;
import statistics.StatisticsGui;
import surpise_panel.view.Game;


public class View extends JFrame implements Observer {
	
	JComboBox<String> jcbFrom;
	JComboBox<String> jcbTo;
	
	JButton jbGrab;
	
	JLabel jlFrom;
	JLabel jlTo;
	
	JButton jbLeft;
	JButton jbRight;
	
	JPanel jpCombBox;
	JPanel jpBottom;
	
	JPanel jpNorth;
	JPanel jpCenter;
	
	Model model;
	
	WelcomePanel welcome;
	StatisticsGui stat;
	MapPanel map;
	Game game;

	//ArrayList<Object> panelList = new ArrayList<Object>();
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	JPanel currentPanel;
	Game currentCanvas;
	int index;
	
	String fromm;
	String too;
	
	Ripley ripley;
	
	public View() {
		
		super();
		
		ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA==", "tBgm4pVq9ArVqL46EnH7ew==");
		
		System.out.println(ripley.getAcknowledgementString());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		setMinimumSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);

		
        welcome = new WelcomePanel();
        
        stat = new StatisticsGui(ripley);
		
		model = new Model();
		model.addObserver(this);
		
		index = 0;
		map = new MapPanel(ripley);
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

		
		//Controller controller = new Controller(model);
		GrabListener grabListener = new GrabListener(model, this);
		
		LeftListener leftListener = new LeftListener(model);
		RightListener rightListener = new RightListener(model);
		jbLeft.addActionListener(leftListener);
		jbRight.addActionListener(rightListener);

		//**********************************
//	    jcbFrom.addItemListener(controller);
//		jcbTo.addItemListener(controller);
		//**********************************
		
		
		String s = (String)jcbFrom.getSelectedItem();
		System.out.println(s);
		
		
		jpCombBox = new JPanel(new FlowLayout());
		jpBottom = new JPanel(new BorderLayout());
		
		JLabel jlInfo = new JLabel(ripley.getLastUpdated(), SwingConstants.CENTER);
		
		// Populating the combo boxes
		 for (int i = ripley.getStartYear(); i <= ripley.getLatestYear(); i++) {		
				
				jcbFrom.addItem(i + "");
				jcbTo.addItem(i + "");
				
			}

		 
		jpCombBox.add(jlFrom);
		jpCombBox.add(jcbFrom);
		
		jpCombBox.add(jlTo);
		jpCombBox.add(jcbTo);
		
		
		jbGrab = new JButton("Grab Data");

		jpCombBox.add(jbGrab);
		jbGrab.addActionListener(grabListener);
		
		jpBottom.add(jbLeft, BorderLayout.WEST);
		jpBottom.add(jbRight, BorderLayout.EAST);
		jpBottom.add(jlInfo, BorderLayout.CENTER);
		
		jpNorth = new JPanel(new BorderLayout());
		jpCenter = new JPanel();
		
		//WelcomePanel welcome = new WelcomePanel();
		currentPanel = welcome;
		jpCenter.add(currentPanel);
		
		this.setLayout(new BorderLayout());
		
		jpNorth.add(jpCombBox, BorderLayout.EAST);
		
		
		JPanel test1 = new JPanel();
		JLabel labelTest1 = new JLabel("PANEL 1");
		test1.add(labelTest1);
		
		JPanel test2 = new JPanel();
		JLabel labelTest2 = new JLabel("PANEL 2");
		test2.add(labelTest2);
		
		JPanel test3 = new JPanel();
		JLabel labelTest3 = new JLabel("PANEL 3");
		test3.add(labelTest3);
		//jpCenter.add(test, BorderLayout.CENTER);
	
		
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpBottom, BorderLayout.SOUTH);
		

		
		panelList.add(welcome);
		panelList.add(stat);
		panelList.add(map);
		//panelList.add(game);
		
	}
	
	public String getJcbFrom() {
		
		String jcbFromValue = (String)jcbFrom.getSelectedItem();
		return jcbFromValue;
			
	}
	
	public String getJcbTo() {
		
		String jcbToValue = (String)jcbTo.getSelectedItem();
		return jcbToValue;
		
	}
	
	public void updateWelcomePanel(String from, String to) {
		
		welcome.addToDisplay(from, to); 
			
	}
	
	public void outOfBound() {
		
		welcome.outOfBound();
		
	}
	
	public void grabData(String from, String to) {
		
		System.out.println("hello");
		welcome.grabData(from, to);

		map.setCanvas(new MapCanvas(ripley, from, to));
		
		fromm = getJcbFrom();
		too = getJcbTo();

		map.setCanvas(new MapCanvas(ripley, from, to));

		stat.update(from,to);

		//game.update(from,to);

		
	}

	public void update(Observable arg0, Object arg1) {
		
		Model model = (Model) arg0;

		if (arg1.equals("Grab Data")) {
			
			updateWelcomePanel(getJcbFrom(), getJcbTo());

     		grabData(getJcbFrom(), getJcbTo());
			
			System.out.println("Update View");
			
			jbLeft.setEnabled(true);
			jbRight.setEnabled(true);
					
		}
		
		if (arg1.equals("Out of bounds")) {
			
			outOfBound();
			
		}

//######### LEFT BUTTON CLICKED #########
		if (arg1.equals("Left")) {
			removeCorrectState();
			if (index == 0) {
				index = 3;
				addGame();
			} else if (index > 0) {
				--index;

				if(index == 3) addGame();
				else addPanel();
			}
		}
		
//######### ENABLE / DISABLE LEFT + RIGHT BUTTONS ########
		if (getJcbFrom().equals(fromm) && getJcbTo().equals(too)) jbGrab.setEnabled(false);
		else jbGrab.setEnabled(true);
		
//######### RIGHT BUTTON CLICKED #########
		if (arg1.equals("Right")) {
			removeCorrectState();
			if (index == 3) {
				index = 0;
				addPanel();
			} else {
				index++;
				if(index == 3) addGame();
				else addPanel();
			}
		}	
	}
	
	private void addGame() {
		currentPanel = null;
		
		// add loading label
//		JLabel loadingLabel = new JLabel();
//		loadingLabel.setText("Loading Space Invaders...");
//		jpCenter.add(loadingLabel, BorderLayout.CENTER);
//		jpCenter.revalidate();
//		jpCenter.repaint();
//		System.out.println("should have updated center panel");
//		
//		try {
//			Thread.sleep(2000);
//		} catch(Exception e){}
//		System.out.println("not sleeping");
		game = new Game(fromm, too);
		this.remove(jpCenter);
		this.setSize(new Dimension(800, 600));
		currentCanvas = game;
		this.add(currentCanvas, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		setLocationRelativeTo(null); //just centring the frame when it resizes
		game.start();
	}
	
	private void addPanel(){
		currentPanel = panelList.get(index);
		jpCenter.add(currentPanel, BorderLayout.CENTER);
		jpCenter.revalidate();
		jpCenter.repaint();
		if (index == 2) {
			//this.setSize(new Dimension(930, 695));
			pack();
		}else {
			this.setSize(new Dimension(800, 600));
		}
		setLocationRelativeTo(null); //centre
	}
	
	private void removeCorrectState() {
		if(currentPanel != null){
			jpCenter.remove(currentPanel);
			System.out.println("removed current panel");
		} else {
			currentCanvas.stop();
			this.remove(currentCanvas);
			currentCanvas = null;
			this.add(jpCenter, BorderLayout.CENTER);
		}
	}
}
