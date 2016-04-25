package cps240final;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	private Image image;
	protected double positionX;
	protected double positionY;
	private double width;
	private double height;
	private boolean dead = false;

	public Sprite() {
		positionX = 0;
		positionY = 0;
	}

	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public void update(double x, double y) {
		if (Main.pauseState)
			return;
		// resulting from input
		positionX += x;
		positionY += y;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
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
}
