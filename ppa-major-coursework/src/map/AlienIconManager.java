package map;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
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
		totalIncidents = incidents.size();
		incidentsByState = new TreeMap<>();
		possibleStates = getPossibleStates().toArray(new String[50]);
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
	
	public ArrayList<Incident> getIncidents() {
		return incidents;
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
		//states.add("ON"); canada?
		states.add("NJ");
		states.add("OK");
		states.add("MI");
		states.add("CA");
		states.add("NE");
		states.add("IL");
		states.add("ID");
		states.add("GA");
		states.add("OH");
		states.add("AR");
		states.add("MA");
		states.add("UT");
		states.add("LA");
		states.add("NC");
		states.add("NV");
		states.add("PA");
		states.add("IA");
		states.add("MD");
		//states.add("BC");
		states.add("AK");
		states.add("NH");
		states.add("KY");
		states.add("CO");
		states.add("SC");
		states.add("MT");
		//states.add("PQ"); canada?
		states.add("CT");
		states.add("MO");
		//states.add("MB"); canada?
		states.add("VA");
		states.add("ND");
		states.add("KS");
		states.add("MS");
		states.add("ME");
		states.add("VT");
		states.add("SD");
		//states.add("AB"); canada?
		states.add("RI");
		states.add("HI");
		//states.add("DC"); DC should not be separate from the states
		states.add("DE");
		states.add("NM"); //forgot state of new mexico
		states.add("WY"); //forgot wyoming
		return states;
	}
	
	public AlienPin[] getAllPins() {
		AlienPin[] icons = new AlienPin[incidentsByState.entrySet().size()];
		Iterator<Entry<String,Integer>> allEntries = incidentsByState.entrySet().iterator();
		int i=0;
		while (allEntries.hasNext()) {
			Entry<String, Integer> current = allEntries.next();
			String state = current.getKey();
			double relSize = (current.getValue()*1.0) / totalIncidents; //force double
			if (relSize > 0.1) {
				relSize = 0.1;
			}
			boolean includeOnMap = isPossibleState(state);
			if (((int) (relSize*300.0) != 0) && includeOnMap) {
				icons[i] = new AlienPin(state);
				setSizeLocation(icons[i], state, relSize);
				i++;
			}
		}
		return icons;
	}
	
	public void setSizeLocation(JLabel icon, String state, double relSize) {
		Image correctlySized = alien.getImage().getScaledInstance((int)(300.0*relSize+10), -1, Image.SCALE_SMOOTH);
		icon.setIcon(new ImageIcon(correctlySized));
		int leftGap = 0;
		int topGap = 0;
		switch (state) { //longest part of this assignment probably
			case "AZ":
				leftGap = 200;
				topGap = 330;
				break;
			case "TN":
				leftGap = 655;
				topGap = 335;
				break;
			case "IN":
				leftGap = 630;
				topGap = 240;
				break;
			case "TX":
				leftGap = 395;
				topGap = 430;
				break;
			case "WA":
				leftGap = 105;
				topGap = 25;
				break;
			case "MN":
				leftGap = 490;
				topGap = 100;
				break;
			case "AL":
				leftGap = 635;
				topGap = 385;
				break;
			case "OR":
				leftGap = 80;
				topGap = 105;
				break;
			case "WV":
				leftGap = 732;
				topGap = 260;
				break;
			case "NY":
				leftGap = 805;
				topGap = 145;
				break;
			case "FL":
				leftGap = 748;
				topGap = 480;
				break;
			case "WI":
				leftGap = 560;
				topGap = 135;
				break;
			case "NJ":
				leftGap = 832;
				topGap = 215;
				break;
			case "OK":
				leftGap = 440;
				topGap = 340;
				break;
			case "MI":
				leftGap = 650;
				topGap = 150;
				break;
			case "CA":
				leftGap = 60;
				topGap = 260;
				break;
			case "NE":
				leftGap = 410;
				topGap = 210;
				break;
			case "IL":
				leftGap = 580;
				topGap = 235;
				break;
			case "ID":
				leftGap = 175;
				topGap = 130;
				break;
			case "GA":
				leftGap = 700;
				topGap = 390;
				break;
			case "OH":
				leftGap = 682;
				topGap = 225;
				break;
			case "AR":
				leftGap = 530;
				topGap = 350;
				break;
			case "MA":
				leftGap = 860;
				topGap = 150;
				break;
			case "UT":
				leftGap = 200;
				topGap = 240;
				break;
			case "LA":
				leftGap = 530;
				topGap = 445;
				break;
			case "NC":
				leftGap = 770;
				topGap = 320;
				break;
			case "NV":
				leftGap = 115;
				topGap = 210;
				break;
			case "PA":
				leftGap = 765;
				topGap = 200;
				break;
			case "IA":
				leftGap = 500;
				topGap = 200;
				break;
			case "MD":
				leftGap = 795;
				topGap = 240;
				break;
			case "AK":
				leftGap = 110;
				topGap = 480;
				break;
			case "NH":
				leftGap = 862;
				topGap = 110;
				break;
			case "KY":
				leftGap = 665;
				topGap = 295;
				break;
			case "CO":
				leftGap = 295;
				topGap = 250;
				break;
			case "SC":
				leftGap = 740;
				topGap = 360;
				break;
			case "MT":
				leftGap = 255;
				topGap = 70;
				break;
			case "CT":
				leftGap = 851;
				topGap = 174;
				break;
			case "MO":
				leftGap = 520;
				topGap = 275;
				break;
			case "VA":
				leftGap = 775;
				topGap = 275;
				break;
			case "ND":
				leftGap = 400;
				topGap = 80;
				break;
			case "KS":
				leftGap = 425;
				topGap = 275;
				break;
			case "MS":
				leftGap = 585;
				topGap = 400;
				break;
			case "ME":
				leftGap = 880;
				topGap = 63;
				break;
			case "VT":
				leftGap = 842;
				topGap = 110;
				break;
			case "SD":
				leftGap = 395;
				topGap = 140;
				break;
			case "RI":
				leftGap = 875;
				topGap = 169;
				break;
			case "HI":
				leftGap = 295;
				topGap = 525;
				break;
			case "DE":
				leftGap = 826;
				topGap = 244;
				break;
			case "NM":
				leftGap = 285;
				topGap = 350;
				break;
			case "WY":
				leftGap = 275;
				topGap = 160;
				break;
		}
		icon.setBounds(leftGap, topGap, (int)(300.0*relSize+10), (int) ((300.0*relSize+10)*1.353638)); //decimal is height as per img aspect ratio
	}
	
	private boolean isPossibleState(String state) {
		for (int i=0; i<possibleStates.length; i++) {
			if (possibleStates[i].equals(state)) {
				return true;
			}
		}
		return false;
	}
}