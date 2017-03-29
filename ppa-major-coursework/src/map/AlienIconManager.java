package map;

import java.awt.Image;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import api.ripley.Ripley;
import api.ripley.Incident;


public class AlienIconManager {
	
	private Ripley ripley;
	private ImageIcon alien;
	private String[] possibleStates;
	
	private int totalIncidents;
	private ArrayList<Incident> incidents;
	
	private TreeMap<String, Integer> incidentsByState;
	
	
	
	public AlienIconManager(Ripley ripley, String from, String to) {
		this.ripley = ripley;
		initComponents(from, to);
	}
	
	//public AlienIconManager(int width) {
		//super();
		//initComponents();
		//Image resizedAlien = alien.getImage().getScaledInstance(width,  -1, Image.SCALE_SMOOTH);
		//setIcon(new ImageIcon(resizedAlien));
	//}
	
	private void initComponents(String fromYear, String toYear) {
		String from = fromYear + "-01-01 00:00:00"; //users are not going to select the time
		String to = toYear + "-12-21 23:59:59";
		alien = new ImageIcon("resources/alien.png");
		incidents = ripley.getIncidentsInRange(from, to);
		incidentsByState = new TreeMap<>();
		possibleStates = new String[55];
		getPossibleStates().toArray(possibleStates);
		for (int i=0; i<incidents.size(); i++) {
			String state = incidents.get(i).getState();
			if (incidentsByState.containsKey(state)) {
				int oldValue = incidentsByState.get(state);
				incidentsByState.replace(state, oldValue+1);
			} else {
				incidentsByState.put(state, 1);
			}
		}
	}
	
	public static int getWidth(String state, Ripley ripley) {
		//not implemented yet
		//return appropriate width based on state
		ArrayList<Incident> incidents = ripley.getIncidentsInRange(null, null);
		return 0;
	}
	
	private ArrayList<String> getPossibleStates() {
		ArrayList<String> states = new ArrayList<>();
		states.add("AZ");
		states.add("TN");
		states.add("IN");
		states.add("TX");
		states.add("WA");
		states.add("MN");
		states.add("AL");
		states.add("OR");
		states.add("WV");
		states.add("NY");
		states.add("FL");
		states.add("WI");
		states.add("ON");
		states.add("NJ");
		states.add("OK");
		states.add("MI");
		states.add("CA");
		states.add("NE");
		states.add("IL");
		states.add("ID");
		states.add("GA");
		states.add("OH");
		states.add("IN");
		states.add("AR");
		states.add("MA");
		states.add("UT");
		states.add("LA");
		states.add("NC");
		states.add("NV");
		states.add("PA");
		states.add("IA");
		states.add("MD");
		states.add("BC");
		states.add("AK");
		states.add("NH");
		states.add("WI");
		states.add("KY");
		states.add("CO");
		states.add("SC");
		states.add("MT");
		states.add("PQ");
		states.add("CT");
		states.add("MO");
		states.add("MB");
		states.add("VA");
		states.add("ND");
		states.add("KS");
		states.add("MS");
		states.add("ME");
		states.add("VT");
		states.add("SD");
		states.add("AB");
		states.add("RI");
		states.add("HI");
		states.add("DC");
		return states;
	}
	
	public JLabel[] getAllPins() {
		return null;
	}
}