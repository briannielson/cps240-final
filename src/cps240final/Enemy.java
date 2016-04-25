package cps240final;

import javafx.scene.image.Image;

public class Enemy extends Sprite {
	private double targetX, targetY;
	public Enemy(double posX, double posY, String enemyType) {
		positionX = posX;
		positionY = posY;
		setImage(new Image( "/cps240final/sprites/" + enemyType + ".png"));
		setTarget();
	}
	
	public void setTarget() {
		targetX = Main.p1.getPosX();
		targetY = Main.p1.getPosY();
	}
	
	public void updatePosition() {
		setTarget();
		double x = 0, y = 0;
		if (targetX - positionX > 0)
			x = 1;
		else if (targetX - positionX < 0)
			x = -1;
		if (targetY - positionY > 0)
			y = 1;
		else if (targetY - positionY < 0)
			y = -1;
		update(x,y);
	}
}
