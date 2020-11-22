import processing.core.*;

public class Anchor {
	
	private PApplet main;
	private PVector position;
	
	public Anchor(PApplet main, PVector position) {
		this.main = main;
		this.position = position;
	}
	
	public PVector getPosition() {
		return position;
	}
	
	public void draw() {
		main.stroke(0, 200, 0);
		main.fill(0, 200, 0);
		main.ellipse(position.x, position.y, 15, 15);
	}
	
	public void update(PVector scrollSpeed) {
		position.add(scrollSpeed);
	}
	

}
