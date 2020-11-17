import java.util.ArrayList;

import processing.core.*;

public class GameState {
	
	private PApplet main;
	private ArrayList<Player> players;
	private ArrayList<Pivot> pivots;
	
	public GameState(PApplet main) {
		this.main = main;
		players  = new ArrayList<>();
		pivots = new ArrayList<>();
	}
	
	public void initialiseGame() {
		pivots.add(new Pivot(main, new PVector(500, 500)));
		players.add(new Player(main));
		players.get(0).setPosition(new PVector(400, 600));
		players.get(0).setSwinging(pivots.get(0).getPosition());
		players.get(0).setVelocity(new PVector(3, 0.1f));
	}
	
	public void updateState() {
		for (Player p : players) {
			p.updatePosition();
		}
		
	}
	
	public void drawObjects() {
		for (Player p : players) {
			p.draw();
		}
		for (Pivot p : pivots) {
			p.draw();
		}
	}

}
