package surpise_panel.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import api.ripley.Incident;
import api.ripley.Ripley;
import surpise_panel.constants.Constants;

public class GameScreen{
	
	private Player player;
	private ArrayList<ArrayList<Alien>> alienBlock = new ArrayList<ArrayList<Alien>>();
	private ArrayList<Incident> allIncidentsFromSelectedRange;
	
	private boolean gameOver = false;
	private boolean gameWon = false;
	
	long lastShootTime = 0;
	
	private int score;
	
	public GameScreen() {
		score = 0;
		player = new Player(350, 450, 50, 50);

// ######## create aliens ###########
		int rowHeight = Constants.SCREEN_HEIGHT/2;
		int leftCoord = 10;
		
		for(int j = 0; j < 5; j++){
			rowHeight = addAlienRows(rowHeight, 5, false);
			rowHeight = addAlienRows(rowHeight, 2, true);
			addAlienRows(rowHeight, 5, false);
			leftCoord += 40;
		}
	}
	
	private int addAlienRows(int rowHeight, int numberOfRows, boolean addNull) {
		int arraySize = alienBlock.size();
		int height = rowHeight;
		for(int k = 0; k < numberOfRows; k++){
			int leftCoord = 10;
			if(!addNull){
				alienBlock.add(new ArrayList<Alien>());
				for(int i = 0; i < 10; i++){
					alienBlock.get(arraySize + k).add(new Alien(leftCoord, height, Constants.ALIEN_WIDTH, Constants.ALIEN_WIDTH));
					leftCoord += 40;
				}
			} 
			height  -= 40; 
		}
		return height;
	}

	
	public void update(double delta) {
		player.update(delta);
		
		for(int i = 0; i < alienBlock.size(); i++){
			for(int j = 0; j < alienBlock.get(i).size(); j++){
				alienBlock.get(i).get(j).move(delta);
			}			
		}
		
		//for some reason if this is put into the first nested for loop the first row 
		//doesnt move down, so the move down function is now in its own nested for loop
		if(Alien.getMoveDown()){
			for(int i = 0; i < alienBlock.size(); i++){
				for(int j = 0; j < alienBlock.get(i).size(); j++){
					alienBlock.get(i).get(j).incrementY();
				}
			}
		}
		
		//randomly choose alien to shoot
		if(System.currentTimeMillis() - lastShootTime > 500){
			if(alienBlock.size() > 0){
				int getRandomRow = (int)(Math.random() * (alienBlock.size()-1) + 0);
				int getRandomAlien = (int)(Math.random() * (alienBlock.get(getRandomRow).size() - 1) + 0);
				
				alienBlock.get(getRandomRow).get(getRandomAlien).addBullet();
				lastShootTime = System.currentTimeMillis();
			}
		}
		
		Alien.setMoveDown(false);
		
		collision();
		checkGameOver();
	}

	
	public void draw(Graphics2D g) {
		player.draw(g);

		for(int i = 0; i < alienBlock.size(); i++){
			for(int j = 0; j < alienBlock.get(i).size(); j++){
				alienBlock.get(i).get(j).draw(g);
			}
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.PLAIN, 20));
		g.drawString("Score: " + score, 50, 50);
		
		if(gameOver) drawEndGameMessage(g, "Game Over!");
		else if(gameWon) drawEndGameMessage(g, "You Won!!");
	}
	
	private void drawEndGameMessage(Graphics2D g, String text){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.PLAIN, 100));
		g.drawString(text, 130, 250);
		g.setFont(new Font("Impact", Font.PLAIN, 50));
		g.drawString("press r to restart", 150, 320);
	}
	
	public void collision() {
		for(int i = 0; i < alienBlock.size(); i++){
			for(int j = 0; j < alienBlock.get(i).size(); j++){
				
				Alien a = alienBlock.get(i).get(j);
				
				//player's bullet collision with alien
				if(player.checkBulletCollision(a.getX(), a.getY(), a.getWidth(), a.getHeight())){
					alienBlock.get(i).remove(j);
					if(alienBlock.get(i).size() == 0) alienBlock.remove(i);
					score++;
					break;
				}
				
				//alien bullet collision with player
				if(alienBlock.get(i).get(j).checkBulletCollision((int)player.getX(), (int)player.getY())){
					gameOver = true;
					break;
				}
			}
		}
	}
	
	public void checkGameOver(){
		if(Alien.getGameOver()){
			gameOver = true;
		}
		if(alienBlock.size() == 0) gameWon = true;
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean getGameOver() {
		if(gameOver || gameWon) return true;
		else return false;
	}
	
	public int getScore(){
		return score;
	}
}
