package surpise_panel.state;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import api.ripley.Ripley;
import surpise_panel.screen.GameScreen;

public class State {
	
	private ArrayList<SuperState> states = new ArrayList<SuperState>();
	private Canvas canvas;
	private byte selectedState = 0;
	
	public State(Canvas canvas, String from, String to){
		SuperState game = new GameScreen( from, to);
		states.add(game);
		this.canvas = canvas;
	}
	
	public void draw(Graphics2D g){
		states.get(selectedState).draw(g);
	}
	
	public void update(double delta){
		states.get(selectedState).update(delta);
	}
	
	public void setState(byte i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++){
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		}
		selectedState = i;
		states.get(selectedState).init(canvas);
	}
	
	public byte getState() {
		return selectedState;
	}
	
	public SuperState getPlayer() {
		return states.get(selectedState);
	}

	public boolean getGameOver() {
		return states.get(selectedState).getGameOver();
	}
}
