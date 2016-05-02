/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * MapObject
 * 
 * Class built to hold any object on the map not meant to move, but
 * can have an expiration timer (Walls, player buffs, enemy buffs)
 * Currently only used to make walls (currently invisible walls)
 */

package cps240final;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapObject extends Sprite {
	private boolean obstacle;
	private ArrayList<Effects> buffs = new ArrayList<Effects>();
	private boolean useable;
	private Color fillColor;
	private Double[] points = new Double[4];
	private boolean expire;
	private int lifeTimer;
	private boolean isShape;
	private boolean visible;

	public MapObject(double x1, double y1, boolean obs, ArrayList<Effects> b, boolean use, boolean expiring,
			int timer, boolean shape, boolean visibility) {
		positionX = x1;
		positionY = y1;
		obstacle = obs;
		buffs = b;
		useable = use;
		expire = expiring;
		lifeTimer = timer;
		isShape = shape;
		visible = visibility;
	}

	public void setShapeAttr(double x1, double y1, double x2, double y2, Color c) {
		positionX = x1;
		positionY = y1;
		points[0] = x1;
		points[1] = y1;
		points[2] = x2 - x1;
		points[3] = y2 - y1;
		fillColor = c;
		setWidthHeight(x2 - x1, y2 - y1);
		// System.out.println(x2 + " " + x1 + ", " + y2  + " " + y1);
	}

	public boolean getExpiring() {
		return expire;
	}

	public void tick() {
		if (lifeTimer > 0)
			lifeTimer--;
		else if (expire)
			setDeath();
	}

	@Override
	public void render(GraphicsContext gc) {
		if (!visible)
			return;
		if (isShape)
			renderShape(gc);
		else
			super.render(gc);
	}

	public void renderShape(GraphicsContext gc) {
		gc.setFill(fillColor);
		gc.fillRect(points[0], points[1], points[2], points[3]);
	}

	@Override
	// This is returning true if the object checking shouldn't be going through the obstacle
	public boolean intersects(Sprite s) {
		// System.out.println(super.getBoundary());
		if (getBoundary().intersects(s.getBoundary())) {
			if (!buffs.isEmpty())
				Main.p1.addBuff(buffs);
			if (useable)
				setDeath();
			if (obstacle) {
				// System.out.println("Obstacle is being hit!");
				return true;
			}
		}
		return false;
	}

	@Override
	void update(double x, double y) {
		// TODO Auto-generated method stub
		
	}
}
