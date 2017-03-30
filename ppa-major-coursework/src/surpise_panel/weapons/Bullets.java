package surpise_panel.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullets {
	private Rectangle rect;
	
	private double x, y; 
	private int w, h;
	
	public Bullets(double x, double y){
		this.x = x;
		this.y = y;
		w = 5; h = 10;
		rect = new Rectangle((int) x,(int) y, w, h);
		
	}
	
	public void draw(Graphics2D g){
		y -= 5;
		g.setColor(Color.RED);
		g.fillRect((int) x - w/2, (int) y, w, h);
		g.drawRect((int)x - w/2,(int) y, w, h);
	}
	
	public boolean destroyBullet() {
		if(y < 0) return true;
		return false;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight(){
		return h;
	}
}
