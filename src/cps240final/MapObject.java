package cps240final;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MapObject extends Sprite {
	private boolean obstacle;
	private ArrayList<Effects> buffs = new ArrayList<Effects>();
	private boolean useable;
	private Color fillColor;
	private Double[] points = new Double[4];
	private boolean expire;
	private int lifeTimer;
	
	public MapObject (String i, double x1, double y1, double x2, double y2, boolean obs, 
			ArrayList<Effects> b, boolean use, boolean expiring, int timer) {
		setImage(new Image(i));
		positionX = x1;
		positionY = y1;
		points[0] = x1;
		points[1] = y1;
		points[2] = x2;
		points[3] = y2;
		obstacle = obs;
		buffs = b;
		useable = use;
		expire = expiring;
		lifeTimer = timer;
	}
	
	public boolean getExpiring() {
		return expire;
	}
	
	public void tick() {
		if (lifeTimer > 0)
			lifeTimer--;
		else
			setDeath();
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(fillColor);
		gc.fillRect(points[0], points[1], points[2], points[3]);
	}
	
	@Override
	public boolean intersects(Sprite s) {
		if (obstacle)
			return s.getBoundary().intersects(this.getBoundary());
		Main.p1.addBuff(buffs);
		if (useable)
			setDeath();
		return false;
	}
}
