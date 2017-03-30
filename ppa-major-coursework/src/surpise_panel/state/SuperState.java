package surpise_panel.state;

import java.awt.Canvas;
import java.awt.Graphics2D;

import surpise_panel.screen.Player;

public interface SuperState {
	
	public void update(double delta);
	public void draw(Graphics2D g);
	public void init(Canvas canvas);
	public Player getPlayer();
	public boolean getGameOver();
}
