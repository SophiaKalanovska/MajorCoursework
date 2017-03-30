package surpise_panel.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import api.ripley.Incident;
import api.ripley.Ripley;
import surpise_panel.constants.Constants;
import surpise_panel.state.SuperState;

public class GameScreen implements SuperState{
	
	private Player player;
	private ArrayList<ArrayList<Alien>> alienBlock = new ArrayList<ArrayList<Alien>>();
	//private ArrayList<Alien> aliens;
	private ArrayList<Incident> allIncidentsFromSelectedRange;
	private boolean gameOver = false;
	private RipleysModel model;
	
	private int score;
	public GameScreen( String from, String to) {
// ######## initialise variables ###########
		score = 0;
		player = new Player(350, 450, 50, 50);
		//model = new RipleysModel( from, to);
		//System.out.println("num of sightings from game: " + model.getNumOfSightings());
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

	@Override
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
		
		Alien.setMoveDown(false);
		
		collision();
		checkGameOver();
	}

	@Override
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
		
		if(gameOver){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Impact", Font.PLAIN, 100));
			g.drawString("Game Over!", 130, 250);
		}
	}
	
	public void collision() {
		for(int i = 0; i < alienBlock.size(); i++){
			for(int j = 0; j < alienBlock.get(i).size(); j++){
				Alien a = alienBlock.get(i).get(j);
				if(player.checkBulletCollision(a.getX(), a.getY(), a.getWidth(), a.getHeight())){
					alienBlock.get(i).remove(j);
					if(alienBlock.get(i).size() == 0) alienBlock.remove(i);
					score++;
					break;
				}
			}
		}
	}
	
	public void checkGameOver(){
		if(Alien.getGameOver()){
			gameOver = true;
		}
	}
	
	
	@Override
	public void init(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public int getScore(){
		return score;
	}

}
