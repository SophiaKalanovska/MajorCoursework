package welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.View;

public class GrabListener implements ActionListener{
	
	private Model model;	
	private View view;
	
	public GrabListener(Model model, View view) {
		
		this.model = model;
		this.view = view;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (check()) {
			
			model.inform();
			
		} else {
			
			model.desinform();
			
		}

		
	}
	
	public boolean check() {
		
		if (Integer.parseInt(view.getJcbFrom()) > Integer.parseInt(view.getJcbTo())) {
			
			return false;
			
		}
		
		
		return true;
	}

}
