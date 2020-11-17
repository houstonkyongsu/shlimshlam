import processing.core.*;

public class Player {

	public final PVector GRAVITY = new PVector(0, 1f);
	public final float DRAG = 0.99f;
	private PApplet main;
	private PVector position;
	private PVector velocity;
	private double pforce;
	private boolean swinging = false;
	private PVector swingPoint;
	private double radius = 0;
	private double angle = 0;
	
	
	public Player(PApplet main) {
		this.main = main;
	}


	public PVector getVelocity() {
		return velocity;
	}


	public void setVelocity(PVector velocity) {
		this.velocity = velocity;
	}


	public PVector getPosition() {
		return position;
	}


	public void setPosition(PVector position) {
		this.position = position;
	}
	

	public void setSwinging(PVector swingPoint) {
		swinging = true;
		this.swingPoint = swingPoint;
		radius = Math.sqrt((swingPoint.x - position.x) * (swingPoint.x - position.x) + (swingPoint.y - position.y) * (swingPoint.y - position.y));
		angle = Math.atan2(position.y - swingPoint.y, position.x - swingPoint.x);
		pforce = 1;
	}
	
	public void setNotSwinging() {
		swinging = false;
	}
	
	public void draw() {
		if (swinging) {
			main.stroke(100);
			main.line(swingPoint.x, swingPoint.y, position.x, position.y);
		}
		main.stroke(0);
		main.fill(0);
		main.rect(position.x, position.y, 24, 24); // filler values
	}
	
	public void updatePosition() {
		if (swinging) {
//			double f = velocity.y * Math.sin(angle) + (velocity.x / Math.cos(angle)); // perpendicular force on rope
			double vertical = Math.atan2(100, 0);
			double test = GRAVITY.y * Math.sin(angle-vertical);
			double theta = Math.atan(pforce/radius);
			angle-=theta;
			pforce*=DRAG;
			pforce+=test;
			
			int x = (int) (swingPoint.x + (radius * Math.cos(angle))); // calculate new x coordinate on circle
			int y = (int) (swingPoint.y + (radius * Math.sin(angle))); // calculate new y coordinate on circle
			position = new PVector(x,y); // move to new coordinates
		} else {
			velocity.add(GRAVITY);
			position.add(velocity);	
		}
		
	}
	
	
}
