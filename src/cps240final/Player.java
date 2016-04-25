package cps240final;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
	private ArrayList<String> input;
	private String ctrl_up, ctrl_down, ctrl_left, ctrl_right, ctrl_pause, ctrl_shoot_up, ctrl_shoot_down,
			ctrl_shoot_left, ctrl_shoot_right;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int gunCooldown = 0;

	public Player() {
		setImage(new Image("/cps240final/sprites/bennyhill.jpg"));
		setupInputDefaults();
		velocity = 2;

		// set position to the center
		input = new ArrayList<String>();

		Main.currentScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				// only add once... prevent duplicates
				if (!input.contains(code))
					input.add(code);
			}
		});

		Main.currentScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (code.equals(ctrl_pause))
					Main.pauseState = Main.pauseState ? false : true;
				input.remove(code);
			}
		});
	}

	public double getPosX() {
		return positionX;
	}

	public double getPosY() {
		return positionY;
	}

	public void setupInputDefaults() {
		ctrl_up = "W";
		ctrl_down = "S";
		ctrl_left = "A";
		ctrl_right = "D";
		ctrl_pause = "ESCAPE";
		ctrl_shoot_up = "UP";
		ctrl_shoot_down = "DOWN";
		ctrl_shoot_left = "LEFT";
		ctrl_shoot_right = "RIGHT";
	}

	public void handleInput() {
		// movement
		double x = 0, y = 0;
		if (positionX < Main.windowSizeX - getWidth() && input.contains(ctrl_right))
			x += velocity;
		if (positionX > 0 && input.contains(ctrl_left))
			x -= velocity;
		if (positionY > 0 && input.contains(ctrl_up))
			y -= velocity;
		if (positionY < Main.windowSizeY - getHeight()&& input.contains(ctrl_down))
			y += velocity;
		update(x, y);

		// guns
		if (gunCooldown == 0) {
			if (input.contains(ctrl_shoot_left) && input.contains(ctrl_shoot_up)) {
				bullets.add(new Bullet(positionX, positionY, 7));
				gunCooldown = 30;
			} else if (input.contains(ctrl_shoot_left) && input.contains(ctrl_shoot_down)) {
				bullets.add(new Bullet(positionX, positionY, 5));
				gunCooldown = 30;
			}

			else if (input.contains(ctrl_shoot_right) && input.contains(ctrl_shoot_up)) {
				bullets.add(new Bullet(positionX, positionY, 1));
				gunCooldown = 30;
			}

			else if (input.contains(ctrl_shoot_right) && input.contains(ctrl_shoot_down)) {
				bullets.add(new Bullet(positionX, positionY, 3));
				gunCooldown = 30;
			}

			else if (input.contains(ctrl_shoot_left)) {
				bullets.add(new Bullet(positionX, positionY, 6));
				gunCooldown = 30;
			}

			else if (input.contains(ctrl_shoot_up)) {
				bullets.add(new Bullet(positionX, positionY, 0));
				gunCooldown = 30;
			}

			else if (input.contains(ctrl_shoot_right)) {
				bullets.add(new Bullet(positionX, positionY, 2));
				gunCooldown = 30;
			}

			else if (input.contains(ctrl_shoot_down)) {
				bullets.add(new Bullet(positionX, positionY, 4));
				gunCooldown = 30;
			}
		} else {
			gunCooldown--;
		}
		updateProjectiles();
	}

	public void renderProjectiles(GraphicsContext gc) {
		for (Bullet bill : bullets) {
			bill.render(gc);
		}
	}

	private void updateProjectiles() {
		if (bullets.size() > 0)
			for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
				Bullet bill = iterator.next();
				bill.updatePosition();
				if (Main.mobs.size() > 0)
					for (Enemy mob : Main.mobs)
						if (mob.intersects(bill)) {
							mob.setDeath();
							bill.setDeath();
							continue;
						}
				if (bill.offScreen() || bill.getDeath())
					iterator.remove();
			}
	}
}
