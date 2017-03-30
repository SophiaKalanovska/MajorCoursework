package surpise_panel.screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import surpise_panel.constants.Constants;
import surpise_panel.weapons.Bullets;

public class Player implements KeyListener{
	
	private final int speed = 5;
	
	private BufferedImage img;
	private double x, y;
	private int w, h;
	private Rectangle rect;
	
	private boolean left = false;
	private boolean right = false;
	
	private ArrayList<Bullets> bullets;
	
	private long shootInterval;
	
	public Player(double x, double y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rect = new Rectangle((int)x, (int)y, w, h);
		
		try {
			URL url = this.getClass().getResource("/player_new.png");
			img = ImageIO.read(url);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		bullets = new ArrayList<Bullets>();
		shootInterval = System.currentTimeMillis();
	}
	public void draw(Graphics2D g){
		g.drawImage(img, (int) x, (int) y, w, h, null);
		
		for(int i = 0; i < bullets.size(); i++){
			if(!bullets.get(i).destroyBullet()){
				bullets.get(i).draw(g);
			}
			else {
				bullets.remove(i); 
			}
		}
	}
	
	public void update(double delta){
		if(right && !left){
			if(!(x + w >= Constants.SCREEN_WIDTH)) x += speed * delta;
		}
		if(left && !right){
			if(!(x <= 0)) x -= speed * delta;
		}
		rect.x = (int) x;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			right = true;
		} else if(key == KeyEvent.VK_LEFT){
			left = true;
		}
		
		if(key == KeyEvent.VK_SPACE) addBullet();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			right = false;
		} else if(key == KeyEvent.VK_LEFT){
			left = false;
		}
	}
	
	private void addBullet() {
		if(System.currentTimeMillis() - shootInterval > 200){
			bullets.add(new Bullets(x + w/2, y));
			shootInterval = System.currentTimeMillis();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	
	public boolean checkBulletCollision(double x, double y, int w, int h){
		for(int i = 0; i < bullets.size(); i++){
			Bullets bullet = bullets.get(i);
			
			if(bullet.getX() + bullet.getWidth() >= x && bullet.getX() <= x + w && bullet.getY() <= y + h && bullet.getY() + bullet.getHeight() >= y){
				bullets.remove(i);
				return true;
			}
		}
		
		return false;
	}

}
