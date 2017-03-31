package map.incidenthandler;

import api.ripley.Incident;

public class IncidentWrapper {
	
	/* only reason this "has-an" Incident (as opposed to is-an Incident)
	 * is because Incident doesn't have a public constructor
	 * 
	 * this is how I can get away with 'technically' overriding toString
	 * 
	 * didn't need to implement all of Incident's original public methods
	 * (since you can just run on result of getIncident())
	 * but man I really like the law of Demeter can you blame me
	 * */
	
	private Incident incident;
	
	public IncidentWrapper(Incident incident) {
		this.incident = incident;
	}
	
	public void setIncident(Incident incident) {
		this.incident = incident;
	}
	
	public Incident getIncident() {
		return incident;
	}
	
	public String getCity() {
		return incident.getCity();
	}
	
	public String getIncidentID() {
		return incident.getIncidentID();
	}
	
	public String getDateAndTime() {
		return incident.getDateAndTime();
	}
	
	public String getState() {
		return incident.getState();
	}
	
	public String getShape() {
		return incident.getShape();
	}
	
	public String getPosted() {
		return incident.getPosted();
	}
	
	public String getDuration() {
		return incident.getDuration();
	}
	
	public String getSummary() {
		return incident.getSummary();
	}
	
	//override toString!!
	@Override
	public String toString() {
		return ("Time: " + getDateAndTime() + " City: " + getCity() + " Shape: " + getShape() 
		+ " Duration: " + getDuration() + " Posted: " + getPosted());
	}
	
	
}
