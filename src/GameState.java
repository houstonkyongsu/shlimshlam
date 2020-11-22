import java.util.*;
import java.util.Map.Entry;

import processing.core.*;

public class GameState {
	
	private PApplet main;
	private HashMap<Integer,Player> players;
	private ArrayList<Anchor> anchors;
	
	public GameState(PApplet main) {
		this.main = main;
		players  = new HashMap<>();
		anchors = new ArrayList<>();
	}
	
	public HashMap<Integer,Player> getPlayers() {
		return players;
	}
	
	public void initialiseGame() {
		anchors.add(new Anchor(main, new PVector(500, 300)));
		anchors.add(new Anchor(main, new PVector(700, 400)));
		anchors.add(new Anchor(main, new PVector(950, 250)));
		anchors.add(new Anchor(main, new PVector(1300, 350)));
		players.put(0, new Player(main));
		players.get(0).setPosition(new PVector(0, 300));
		players.get(0).setVelocity(new PVector(10, -10));
	}
	
	public void spawnAnchors() {
		float x = anchors.get(anchors.size()-1).getPosition().x;
		if (x < main.width) {
			int newx = (int) (main.random(150) + 50 + x);
			int y = (int) main.random(500) + 80;
			anchors.add(new Anchor(main, new PVector(newx,y)));
		}
	}
	
	public void setPlayerSwinging(int id) {
		if (!players.isEmpty()) {
			Player p = players.get(id);
			if (!p.isSwinging()) {
	        	if (!anchors.isEmpty()) {
	        		p.setSwinging(nearestAnchor(p.getPosition()));
	        	}
	        }
		}
	}
	
	public float scrollObjects() {
		float diff = 0;
		if (players.size() > 0) {
			for (Player p : players.values()) {
				if (p.getPosition().x > 600) {
					diff = p.getPosition().x - 600;
					for (Player pl : players.values()) {
						pl.scrollPlayer(new PVector(-diff,0));
					}
					for (Anchor a : anchors) {
						a.update(new PVector(-diff,0));
					}
				}
			}
		}
		return diff;
	}
	
	private PVector nearestAnchor(PVector pos) {
		float distance = Float.MAX_VALUE;
		Anchor best = new Anchor(main, new PVector(0,0));
		for (Anchor a : anchors) {
			if (pos.dist(a.getPosition()) < distance) {
				distance = pos.dist(a.getPosition());
				best = a;
			}
		}
		return best.getPosition();
	}
	
	public void removeOrUpdatePlayers() {
		Iterator<Entry<Integer, Player>> it = players.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Integer,Player> pair = it.next();
	        Player p = pair.getValue();
	        if ((p.getPosition().x < -100 || p.getPosition().y > main.height + 100) && !p.isSwinging()) {
				it.remove();
			} else {
				p.updatePosition();				
			}
	    }
	}
	
	public void removeOrUpdateAnchors() {
		ListIterator<Anchor> iter = anchors.listIterator();
		while(iter.hasNext()) {
			Anchor a = iter.next();
			if (a.getPosition().x < -100 || a.getPosition().y > main.height + 100) {
				iter.remove();
			}
		}
	}
	
	public void drawObjects() {
	    for (Player player : players.values()) {
	        player.draw();
	    }
		for (Anchor a : anchors) {
			a.draw();
		}
	}

}
