package surpise_panel.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import surpise_panel.timer.Timer;

public class SpriteAnimation {
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte currentSprite;
	
	private int speed;
	private double x, y;
	
	private boolean destroy = false;
	private boolean loop = false;
	private boolean play = false;
	
	private Timer timer;
	public SpriteAnimation(double x, double y, int speed){
		this.speed = speed;
		this.x = x;
		this.y = y;
		timer = new Timer();
	}
	
	public void draw(Graphics2D g){
		g.drawImage(sprites.get(currentSprite),(int) getX(),(int) getY(), null);
	}
	
	public void addSprite(BufferedImage sprite, int x, int y, int w, int h){
		sprites.add(sprite.getSubimage(x, y, w, h));
	}
	public void update(double delta){
		if(isAnimationDestroyed()) return;
		
		if(loop && play) loopAnimation();
		
		if(play && !loop) playAnimation();
	}
	
	public void stop() {
		loop = false;
		play = false;
	}
	
	public void resetSprite() {
		stop();
		currentSprite = 0;
	}
	private void loopAnimation() {
		if(timer.isTimerReady(speed) && currentSprite != sprites.size()-1){
			play = false;
		} else if(timer.timerEvent(speed) && currentSprite == sprites.size()-1){
			currentSprite++;
		}
	}

	private void playAnimation() {
		if(timer.timerEvent(speed) && currentSprite != sprites.size()-1 && !isAnimationDestroyed()){
			play = false;
			currentSprite = 0;
		} else if(timer.timerEvent(speed) && currentSprite == sprites.size()-1 && isAnimationDestroyed()){
			sprites = null;
		} else if(timer.timerEvent(speed) && currentSprite != sprites.size()-1){
			currentSprite++;
		}
	}
	
	public boolean isAnimationDestroyed(){
		if(sprites == null)
			return true;
		return false;
	}
	
	public void animate(boolean play, boolean destroy){
		this.play = play;
		this.destroy = destroy;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
}
