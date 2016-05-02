/*
 * @author: Brian Bauman
 * 
 * Bullet
 * 
 * This class holds everything about projectiles. If enemies later
 * get weapons to shoot, they should implement an updateProjectile() method
 * and set their weapon info here.
 * 
 * This class is supposed to be initialized and updated by the Player
 * or the Enemy. Ownership is required so we do not dereference ourselves...
 */

package cps240final;

import javafx.scene.image.Image;

public class Bullet extends Sprite {
	private final double diagScale = .707;
	private double velocityX, velocityY;

	public Bullet(double startX, double startY, int direction) {
		setImage(new Image( "/cps240final/sprites/bullet.png" ));
		setPosition(startX + Main.p1.getWidth() / 2, startY + Main.p1.getHeight() / 2);
		velocity = 20;
		switch (direction) {
		case 0:
			setVelocity(0, -velocity);
			break;
		case 1:
			setVelocity(diagScale * velocity, -diagScale * velocity);
			break;
		case 2:
			setVelocity(velocity, 0);
			break;
		case 3:
			setVelocity(diagScale * velocity, diagScale * velocity);
			break;
		case 4:
			setVelocity(0, velocity);
			break;
		case 5:
			setVelocity(-diagScale * velocity, diagScale * velocity);
			break;
		case 6:
			setVelocity(-velocity, 0);
			break;
		case 7:
			setVelocity(-diagScale * velocity, -diagScale * velocity);
			break;
		}
	}
	
	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}
	
	public void updatePosition() {
		update(velocityX, velocityY);
	}
	
	public boolean offScreen() {
		if (positionX > 0 && positionX < Main.windowSizeX && positionY > 0 && positionY < Main.windowSizeY)
			return false;
		return true;
	}

	@Override
	public void update(double x, double y) {
		positionX += x;
		if (positionX < Main.windowSizeX - getWidth() && positionX > 0)
			for (MapObject m : Main.map) {
				if (m.intersects(this)) {
					// System.out.println("X Intersection found");
					setDeath();
					break;
				}
			}
		
		positionY += y;
		if (positionY < Main.windowSizeY - getHeight() && positionY > 0)
			for (MapObject m : Main.map) {
				// System.out.println(Main.map.indexOf(m));
				if (m.intersects(this)) {
					// System.out.println("Y Intersection found");
					setDeath();
					break;
				}
			}
	}
}
