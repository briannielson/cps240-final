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
		if (this.intersects(Main.p1) && !Main.p1.tickInvincFrames())
			Main.p1.hit(25);
		update(x,y);
	}
	
	@Override
	public void update(double x, double y) {
		
		// check for intersection to outside of window AND for map objects
		positionX += x;
		if (positionX < Main.windowSizeX - getWidth() && positionX > 0) {
			for (MapObject m : Main.map) {
				if (m.intersects(this)) {
					// System.out.println("X Intersection found");
					positionX -= x;
					break;
				}
			} for (Enemy e : Main.mobs) {
				if (e.intersects(this) && !e.equals(this)) {
					positionX -= x;
					// If enemy spawns in another enemy, go ahead and let it move
					if (e.intersects(this))
						positionX += x;
					break;
				}
			}
		}
		else if (positionX < Main.windowSizeX - getWidth())
			positionX = 0;
		else
			positionX = Main.windowSizeX - getWidth();
		
		positionY += y;
		if (positionY < Main.windowSizeY - getHeight() && positionY > 0) {
			for (MapObject m : Main.map) {
				// System.out.println(Main.map.indexOf(m));
				if (m.intersects(this)) {
					// System.out.println("Y Intersection found");
					positionY -= y;
					break;
				}
			} for (Enemy e : Main.mobs) {
				if (e.intersects(this) && !e.equals(this)) {
					positionY -= y;
					// If enemy spawns in another enemy, go ahead and let it move
					if (e.intersects(this))
						positionY += y;
					break;
				}
			}
		}
		else if (positionY < Main.windowSizeY - getHeight())
			positionY = 0;
		else
			positionY = Main.windowSizeY - getHeight();
	}
}
