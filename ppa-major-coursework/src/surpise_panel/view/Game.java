package surpise_panel.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import surpise_panel.constants.Constants;
import surpise_panel.screen.GameScreen;
import api.ripley.Incident;
import api.ripley.Ripley;


public class Game extends Canvas implements Runnable, KeyListener{
	
	public static int WIDTH = 750;
	public static int HEIGHT = 550;
	public int FPS;
	boolean gameOver;
	private GameScreen game;
	private Thread thread;
	private Ripley ripley;
	
	private BufferStrategy bs;
	
	public Game(String from, String to) {		
		initialise();
	}
	
	public void initialise(){
		game = new GameScreen();
		
		addKeyListener(this);
		addKeyListener(game.getPlayer());
		gameOver = false;
		
	}
	
	public synchronized void start() {
		System.out.println("starting");
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
		
		long timer = System.currentTimeMillis();
		long lastLoopTime = System.nanoTime();
		final int IDEAL_FPS = 60;
		final int IDEAL_TIME = 1000000000 / IDEAL_FPS;
		
		int frames = 0;
		this.createBufferStrategy(3);
		bs = this.getBufferStrategy();
		
		while(!gameOver){
			
			if(game.getGameOver()) gameOver = true;
			
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
			draw(bs);
			
			try {
				Thread.sleep(((lastLoopTime - System.nanoTime()) + IDEAL_TIME) / 1000000);
			} catch(Exception e){}
		}
		
		draw(bs);
	}
	
	public void draw(BufferStrategy bs){
		do {
			do{
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Constants.SCREEN_WIDTH + 50, Constants.SCREEN_HEIGHT + 50);
				
				game.draw(g);
				
				g.dispose();
			}while(bs.contentsRestored());
			bs.show();
		}while(bs.contentsLost());
	}
	
	public void update(double delta){
		game.update(delta);
	}
	
	/**
	 * Used to start a new game when game is over;
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(gameOver){
			System.out.println("key pressed");
			int key = arg0.getKeyCode();
			if(key == KeyEvent.VK_R){
				initialise();
				start();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
