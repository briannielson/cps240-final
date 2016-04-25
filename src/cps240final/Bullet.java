package cps240final;

import javafx.scene.image.Image;

public class Bullet extends Sprite {
	private final double velocity = 20;
	private final double diagScale = .707;
	private double velocityX, velocityY;

	public Bullet(double startX, double startY, int direction) {
		setImage(new Image( "/cps240final/sprites/bennyhill.jpg" ));
		setPosition(startX, startY);
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
}
