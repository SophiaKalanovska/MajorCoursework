package surpise_panel.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import surpise_panel.constants.Constants;

public class Bullets {
	private Rectangle rect;
	
	private double x, y; 
	private int w, h;
	
	private int bulletSpeed = 5;
	
	private boolean isThisAnAlienBullet;
	
	public Bullets(double x, double y, boolean isThisAnAlienBullet){
		this.isThisAnAlienBullet = isThisAnAlienBullet;
		this.x = x;
		this.y = y;
		w = 5; h = 10;
		rect = new Rectangle((int) x,(int) y, w, h);
	}
	
	public void draw(Graphics2D g){
		if(isThisAnAlienBullet) y += bulletSpeed;
		else y -= bulletSpeed;
		g.setColor(Color.RED);
		g.fillRect((int) x - w/2, (int) y, w, h);
		g.drawRect((int)x - w/2,(int) y, w, h);
	}
	
	public boolean destroyBullet() {
		if(isThisAnAlienBullet) if(y > Constants.SCREEN_HEIGHT) return true;
		else if(y < 0) return true;
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
