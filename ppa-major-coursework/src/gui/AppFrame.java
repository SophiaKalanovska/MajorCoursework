package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import api.ripley.Ripley;

public class AppFrame extends JFrame {
	
	private Ripley ripley;
	
	private JPanel header;
	
	private JPanel footer;
	private JButton nextP;
	private JButton prevP;
	private JLabel lastReport;
	
	private JPanel dateRangeSelection;
	private JComboBox fromDate;
	private JComboBox toDate;
	

	public AppFrame() {
		super();
		ripley = new Ripley("10tLI3GUsNqyVD6ql2OMtA", "tBgm4pVq9ArVqL46EnH7ew");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		initWidgets();
	}
	
	public void display() {
		setVisible(true);
	}
	
	public void initWidgets() {
		initHeader();
		initFooter();
	}
	
	public void initHeader() {
		header = new JPanel(new BorderLayout());
		dateRangeSelection = new JPanel(new FlowLayout());
		dateRangeSelection.add(new JLabel("From:"));
		fromDate = new JComboBox();
		dateRangeSelection.add(fromDate);
		dateRangeSelection.add(new JLabel("To:"));
		toDate = new JComboBox();
		dateRangeSelection.add(toDate);
		header.add(dateRangeSelection, BorderLayout.EAST);
		add(header, BorderLayout.NORTH);
	}
	
	public void initFooter() {
		footer = new JPanel(new BorderLayout());
		prevP = new JButton("<");
		nextP = new JButton(">");
		lastReport = new JLabel("Last set of reported incidents:", SwingConstants.CENTER);
		footer.add(prevP, BorderLayout.WEST);
		footer.add(lastReport, BorderLayout.CENTER);
		footer.add(nextP, BorderLayout.EAST);
		add(footer, BorderLayout.SOUTH);
	}
	
	public static void main (String[] args) {
		AppFrame myFrame = new AppFrame();
		myFrame.display();
	}
	
}
