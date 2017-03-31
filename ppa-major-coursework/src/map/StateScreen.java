package map;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;

import map.incidenthandler.IncidentOperations;
import map.incidenthandler.IncidentWrapper;

public class StateScreen extends JFrame {

	private String stateShort;
	private String stateFull;
	private IncidentWrapper[] localIncidents;
	private JList dispIncidents;

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
		add(dispIncidents, BorderLayout.CENTER);
	}
}
