package map;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import api.ripley.Incident;
import map.incidenthandler.IncidentWrapper;

public class PinClickListener extends MouseAdapter {
	
	private IncidentWrapper[] incidents;
	
	public PinClickListener(ArrayList<Incident> incidents) {
		this.incidents = new IncidentWrapper[incidents.size()];
		int i=0;
		for (Incident incident : incidents) {
			if (incident != null) {
				this.incidents[i] = new IncidentWrapper(incident);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent E) {
		(new StateScreen(((AlienPin) E.getComponent()).getState(), incidents)).setVisible(true);
	}
}
