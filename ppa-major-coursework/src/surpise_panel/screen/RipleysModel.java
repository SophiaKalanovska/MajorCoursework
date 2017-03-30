package surpise_panel.screen;

import java.util.ArrayList;

import api.ripley.Incident;
import api.ripley.Ripley;

public class RipleysModel {
	private Ripley ripleys;
	
	private ArrayList<Incident> allIncidentsFromSelectedRange;
	private int numberOfSightings;
	
	public RipleysModel(Ripley ripleys, String from, String to) {
		this.ripleys = ripleys;
		allIncidentsFromSelectedRange = ripleys.getIncidentsInRange(from, to);
		numberOfSightings = allIncidentsFromSelectedRange.size();
	}
	
	public void getNumberOfIncidents(String from, String to){
		allIncidentsFromSelectedRange = ripleys.getIncidentsInRange(from, to);
		numberOfSightings = allIncidentsFromSelectedRange.size();
	}
	
	public int getNumOfSightings() {
		return numberOfSightings;
	}
	
	
}
