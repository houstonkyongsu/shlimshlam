import processing.core.*;
import java.util.*;

public class ShlimShlamMain extends PApplet {

	public final int PORT = 7891;
	private PImage background;
	private int screen = 0;
	private GameState game;
	private int scrollCounter = 1;
	
	public void settings() {
//		fullScreen();
		size(1000,700);
		background = loadImage("background.png");
		game = new GameState(this);
		game.initialiseGame();
	}
	
	public void draw() {
		frameRate(30); 
		rectMode(CENTER);
		background(220);
		noCursor();
		switch (screen) {
			case 1:
				game.removeOrUpdatePlayers();
				game.spawnAnchors();
				float diff = game.scrollObjects();
				scrollBackground(diff);
				game.drawObjects();
				break;
			default:
				textSize(32);
				text("click to start", width/2-65, height/2); 
		}
	}
	
	public void mousePressed() {
		if (screen == 1) {
			if (game.getPlayers().size() > 0) {
//				for (Map.Entry<Integer, Player> entry : game.getPlayers().entrySet()) {
//				    int key = entry.getKey();
//				    Player value = entry.getValue();
//				    if (key == 0) {
//				    	
//				    }
//				}
//				game.getPlayers().get(0).setNotSwinging();
				game.setPlayerSwinging(0);
			}
		}
	}
	
	public void mouseReleased() {
		if (screen == 0) {
			screen = 1;
		} else if (screen == 1) {
			if (game.getPlayers().size() > 0) {
				game.getPlayers().get(0).setNotSwinging();
			}
		}
	}
	
	private void scrollBackground(float diff) {
		scrollCounter += (int) diff;
		int x = scrollCounter % background.width;
		for (int i = -x; i < width; i += background.width) {
			copy(background, 0, 0, background.width, height, i, 0, background.width, height);
		}
	}
	
	public static void main(String[] args) {
		String[] processingArgs = {"ShlimShlam"};
		ShlimShlamMain main = new ShlimShlamMain();
		PApplet.runSketch(processingArgs, main);

	}

}
