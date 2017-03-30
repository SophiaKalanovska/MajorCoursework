package surpise_panel.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import surpise_panel.constants.Constants;
import surpise_panel.state.State;
import api.ripley.Incident;
import api.ripley.Ripley;


public class Game extends Canvas implements Runnable{
	
	public static int WIDTH = 750;
	public static int HEIGHT = 550;
	//JFrame jframe;
	public int FPS;
	boolean gameOver = false;
	boolean pause = true;
	public static State state;	
	private Thread thread;
	private Ripley ripley;
	
	public Game(String from, String to) {
		//this.ripley = ripley;
		
		state = new State(this, from, to);
		state.setState((byte)0);
		
		addKeyListener(state.getPlayer().getPlayer());
	}
	
	public void update(String from, String to){

		state = new State(this, from,to);
		state.setState((byte)0);
		
		addKeyListener(state.getPlayer().getPlayer());
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		gameOver = true;
		try {
			thread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void run() {
		
		System.out.println("running");
		long timer = System.currentTimeMillis();
		long lastLoopTime = System.nanoTime();
		final int IDEAL_FPS = 60;
		final int IDEAL_TIME = 1000000000 / IDEAL_FPS;
		
		int frames = 0;
		this.createBufferStrategy(3);
		BufferStrategy bullshit = this.getBufferStrategy();
		
		while(!gameOver){
			
			if(state.getGameOver()) break;
			
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) IDEAL_TIME);
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				FPS = frames;
				frames = 0;
			}
			update(delta);
			draw(bullshit);
			
			try {
				Thread.sleep(((lastLoopTime - System.nanoTime()) + IDEAL_TIME) / 1000000);
			} catch(Exception e){}
		}
		
		draw(bullshit);
		
	}
	
	public void draw(BufferStrategy bs){
		do {
			do{
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Constants.SCREEN_WIDTH + 50, Constants.SCREEN_HEIGHT + 50);
				
				state.draw(g);
				
				g.dispose();
			}while(bs.contentsRestored());
			bs.show();
		}while(bs.contentsLost());
	}
	
	public void update(double delta){
		state.update(delta);
	}
	
	public void pauseGame(boolean pause){
		this.pause = pause;
	}
}
