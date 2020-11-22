import processing.core.*;

public class Player {

	public final PVector GRAVITY = new PVector(0, 1f);
	public final float DRAG = 0.995f;
	public final double VERTICAL = Math.atan2(100, 0);
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
	
	public void scrollPlayer(PVector dist) {
		position.add(dist);
//		swingPoint.add(dist);
	}


	public void setPosition(PVector position) {
		this.position = position;
	}
	
	public boolean isSwinging() {
		return swinging;
	}

	public void setSwinging(PVector swingPoint) {
		swinging = true;
		this.swingPoint = swingPoint;
		radius = Math.sqrt((swingPoint.x - position.x) * (swingPoint.x - position.x) + (swingPoint.y - position.y) * (swingPoint.y - position.y));
		angle = Math.atan2(position.y - swingPoint.y, position.x - swingPoint.x);
		pforce = (Math.sin(velocity.y / radius) + Math.cos(velocity.x / radius)) * 5;
	}
	
	public void setNotSwinging() {
		swinging = false;
		double x = pforce * Math.sin(angle - VERTICAL);
		double y = pforce *  Math.cos(angle - VERTICAL);
		velocity.x = (float) -(x * 3 + 3);
		velocity.y = (float) -(y * 1.5 + 8);
	}
	
	public void draw() {
		if (swinging) {
			main.stroke(100);
			main.line(swingPoint.x, swingPoint.y, position.x, position.y);
		}
		main.stroke(30, 30, 220);
		main.fill(30, 30, 220);
		main.rect(position.x, position.y, 24, 24); // filler values
	}
	
	public void updatePosition() {
		if (swinging) {
			double test = GRAVITY.y * Math.sin(angle - VERTICAL);
			double theta = Math.atan(pforce/radius);
			angle -= theta;
			pforce *= DRAG;
			pforce += test;
			int x = (int) (swingPoint.x + (radius * Math.cos(angle))); // calculate new x coordinate on circle
			int y = (int) (swingPoint.y + (radius * Math.sin(angle))); // calculate new y coordinate on circle
			position = new PVector(x,y); // move to new coordinates
		} else {
			velocity.add(GRAVITY);
			position.add(velocity);	
		}
		
	}
	
	
}
