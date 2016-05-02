/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * Sprite
 * 
 * Parent class for Enemy, Player, Bullet, and MapObject
 * This class is how we implement unit detection as well as
 * rendering onto the GraphicsContext
 */

package cps240final;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

abstract class Sprite {
	protected Image image;
	protected double positionX;
	protected double positionY;
	protected int rotation;
	private double width;
	private double height;
	protected boolean dead = false;
	protected double velocity;
	private int health;

	public Sprite() {
		positionX = 0;
		positionY = 0;
		rotation = 90;
	}

	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}
	
	public void setRotation(int theta) {
		rotation = theta;
	}

	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	abstract void update(double x, double y);
	
	public double getWidth() {
		return width;
	}
	
	public void setWidthHeight(double x, double y) {
		// System.out.println(x + " " + y);
		width = x;
		height = y;
	}
	
	public double getHeight() {
		return height;
	}

	public void render(GraphicsContext gc) {
		drawRotatedImage(gc);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	public void setDeath() {
		dead = true;
	}
	
	public boolean getDeath() {
		return dead;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int x) {
		health = x;
	}
	
	protected void rotate(GraphicsContext gc, double px, double py) {
		Rotate r = new Rotate(rotation, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}
	
	protected void drawRotatedImage(GraphicsContext gc) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, positionX + width / 2, positionY + height / 2);
        gc.drawImage(image, positionX, positionY);
        gc.restore(); // back to original state (before rotation)
    }
}
