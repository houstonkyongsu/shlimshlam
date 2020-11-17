import processing.core.*;
import java.util.*;

public class ShlimShlamMain extends PApplet {

	public final int PORT = 7891;
	private PImage background;
	private int screen = 1;
	private GameState game;
	
	public void settings() {
//		fullScreen();
		size(1000,1000);
//		background = loadImage("background.png");
		game = new GameState(this);
		game.initialiseGame();
	}
	
	public void draw() {
		frameRate(20);
		rectMode(CENTER);
		background(220);
		noCursor();
		switch (screen) {
			case 1:
	//			scrollBackground();
				game.updateState();
				game.drawObjects();
				break;
			default:

		}
	}
	
	public void mousePressed() {
		
	}
	
//	private void scrollBackground() {
//		int x = frameCount % background.width;
//		for (int i = -x; i < width; i += background.width) {
//			copy(background, 0, 0, background.width, height, i, 0, background.width, height);
//		}
//	}
	
	public static void main(String[] args) {
		String[] processingArgs = {"ShlimShlam"};
		ShlimShlamMain main = new ShlimShlamMain();
		PApplet.runSketch(processingArgs, main);

	}

}
