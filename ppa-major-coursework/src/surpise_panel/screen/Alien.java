package surpise_panel.screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import surpise_panel.constants.Constants;

public class Alien {
	private final int speed = 1;
	
	private BufferedImage img;
	private double x;
	private double y;
	private int w, h;
	private Rectangle rect;
	
	private static boolean left = false;
	private static boolean right = true;
	
	private static boolean moveDown = false;
	private static boolean gameOver = false;
	
	public Alien(double x, double y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rect = new Rectangle((int)x, (int)y, w, h);
		
		try {
			URL url = this.getClass().getResource("/alien.jpg");
			img = ImageIO.read(url);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g){
		g.drawImage(img, (int) x, (int) y, w, h, null);
	}
	
	public void move(double delta){
		if(left) x -= speed * delta;
		else if(right) x += speed * delta;
		
		if(x + w >= Constants.SCREEN_WIDTH){
			left = true; right = false;
//			y += 50;
//			System.out.println(y);
			moveDown = true;
		}
		if(x <= 0){
			left = false; right = true;
			moveDown = true;
			//y += 50;
		}
		
		if(this.y > Constants.GAME_OVER_Y_VALUE){
			gameOver = true;
		}
	}
	
	public boolean atGameOverYValue() {
		if( y >= Constants.GAME_OVER_Y_VALUE) return true;
		else return false;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void incrementY() {
		this.y += Constants.INCREMENT_ALIEN_Y_VALUE;
	}
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public static boolean getMoveDown() {
		return moveDown;
	}
	
	public static boolean getGameOver() {
		return gameOver;
	}
	
	public static void setMoveDown(boolean bool){
		moveDown = bool;
	}
}
