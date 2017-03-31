package surpise_panel.screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import surpise_panel.constants.Constants;
import surpise_panel.weapons.Bullets;

public class Alien {
	private final int speed = 1;
	
	private BufferedImage img;
	private double x;
	private double y;
	private int w, h;
	//private Rectangle rect;
	
	private static boolean left = false;
	private static boolean right = true;
	
	private static boolean moveDown = false;
	private static boolean gameOver = false;
	
	private ArrayList<Bullets> bullets;
	private long lastTimeShotBullet = 0;
	
	public Alien(double x, double y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		bullets = new ArrayList<Bullets>();
		
		//add image
		try {
			URL url = this.getClass().getResource("/alien.jpg");
			img = ImageIO.read(url);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g){
		g.drawImage(img, (int) x, (int) y, w, h, null);
		
	//~~~ Draw bullets	
		for(int i = 0; i < bullets.size(); i++){
			if(!bullets.get(i).destroyBullet()){
				bullets.get(i).draw(g);
			}
			else {
				bullets.remove(i); 
			}
		}
	}
	
	public void move(double delta){
		if(left) x -= speed * delta;
		else if(right) x += speed * delta;
		
		if(x + w >= Constants.SCREEN_WIDTH){
			left = true; right = false;
			moveDown = true;
		}
		if(x <= 0){
			left = false; right = true;
			moveDown = true;
		}
		
		if(this.y > Constants.GAME_OVER_Y_VALUE){
			gameOver = true;
		}
	}
	
	public void addBullet() {
		if(this.y > 0){
			if(System.currentTimeMillis() - lastTimeShotBullet > 3000)
			bullets.add(new Bullets(this.x + w/2, this.y, true));
			lastTimeShotBullet = System.currentTimeMillis();
		}
	}
	
	public boolean atGameOverYValue() {
		if( y >= Constants.GAME_OVER_Y_VALUE) return true;
		else return false;
	}
	
	public boolean checkBulletCollision(int playerX, int playerY){
		for(int i = 0; i < bullets.size(); i++){
			Bullets bullet = bullets.get(i);
			if(bullet.getX() + bullet.getWidth() >= playerX && bullet.getX() <= playerX + Constants.PLAYER_WIDTH_AND_HEIGHT && bullet.getY() <= playerY + Constants.PLAYER_WIDTH_AND_HEIGHT && bullet.getY() + bullet.getHeight() >= playerY){
				return true;
			}
		}
		return false;
	}
	
	
//########## GETTERS + SETTERS ##########
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
