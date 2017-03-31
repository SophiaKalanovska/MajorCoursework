package map.incidenthandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class DoubleClick extends MouseAdapter {
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JOptionPane.showMessageDialog(null,((IncidentWrapper)(((JList)(e.getSource())).getSelectedValue())).getSummary());
		}
	}
	
	
}
