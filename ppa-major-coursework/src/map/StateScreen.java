package map;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import map.incidenthandler.IncidentOperations;
import map.incidenthandler.IncidentWrapper;

public class StateScreen extends JFrame {

	private String stateShort;
	private String stateFull;
	private IncidentWrapper[] localIncidents;
	
	private JList dispIncidents;
	private JScrollPane scroll;
	private JComboBox selectAttribute;

	public StateScreen(String state, IncidentWrapper[] incidents) {
		super();
		stateShort = state;
		stateFull = IncidentOperations.shortToFull(stateShort);
		localIncidents = IncidentOperations.localIncidents(incidents, state);
		initFrameComponents();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void initFrameComponents() {
		setTitle(stateFull + " (" + stateShort + ")");
		setPreferredSize(new Dimension(500,300));
		
		dispIncidents = new JList<IncidentWrapper>(localIncidents);
		
		JPanel dropDownPanel = new JPanel(new BorderLayout());
		dropDownPanel.setMinimumSize(new Dimension(400, 100));
		
		String[] options = {"-", "Date", "City", "Shape", "Duration", "Posted"};
		selectAttribute = new JComboBox(options);
		
		dropDownPanel.add(selectAttribute, BorderLayout.CENTER);
		
		scroll = new JScrollPane(dispIncidents);
		
		scroll.setMinimumSize(new Dimension(400, 200));
		
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(dropDownPanel, BorderLayout.NORTH);
	}
}
