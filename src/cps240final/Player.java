package cps240final;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
	private ArrayList<String> input;
	private HashMap<String, String> controls = new HashMap<String, String>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int gunCooldown = 0;
	private int numLives;

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
				if (code.equals(controls.get("ctrl_pause")))
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
		controls.put("ctrl_up", "W");
		controls.put("ctrl_down", "S");
		controls.put("ctrl_left", "A");
		controls.put("ctrl_right", "D");
		controls.put("ctrl_pause", "ESCAPE");
		controls.put("ctrl_shoot_up", "UP");
		controls.put("ctrl_shoot_down", "DOWN");
		controls.put("ctrl_shoot_left", "LEFT");
		controls.put("ctrl_shoot_right", "RIGHT");
	}

	public void handleInput() {
		// movement
		double x = 0, y = 0;
		if (positionX < Main.windowSizeX - getWidth() && input.contains(controls.get("ctrl_right")))
			x += velocity;
		if (positionX > 0 && input.contains(controls.get("ctrl_left")))
			x -= velocity;
		if (positionY > 0 && input.contains(controls.get("ctrl_up")))
			y -= velocity;
		if (positionY < Main.windowSizeY - getHeight() && input.contains(controls.get("ctrl_down")))
			y += velocity;
		update(x, y);

		// guns
		if (gunCooldown == 0) {
			if (input.contains(controls.get("ctrl_shoot_left")) && input.contains(controls.get("ctrl_shoot_up"))) {
				bullets.add(new Bullet(positionX, positionY, 7));
				gunCooldown = 30;
			} else if (input.contains(controls.get("ctrl_shoot_left"))
					&& input.contains(controls.get("ctrl_shoot_down"))) {
				bullets.add(new Bullet(positionX, positionY, 5));
				gunCooldown = 30;
			}

			else if (input.contains(controls.get("ctrl_shoot_right"))
					&& input.contains(controls.get("ctrl_shoot_up"))) {
				bullets.add(new Bullet(positionX, positionY, 1));
				gunCooldown = 30;
			}

			else if (input.contains(controls.get("ctrl_shoot_right"))
					&& input.contains(controls.get("ctrl_shoot_down"))) {
				bullets.add(new Bullet(positionX, positionY, 3));
				gunCooldown = 30;
			}

			else if (input.contains(controls.get("ctrl_shoot_left"))) {
				bullets.add(new Bullet(positionX, positionY, 6));
				gunCooldown = 30;
			}

			else if (input.contains(controls.get("ctrl_shoot_up"))) {
				bullets.add(new Bullet(positionX, positionY, 0));
				gunCooldown = 30;
			}

			else if (input.contains(controls.get("ctrl_shoot_right"))) {
				bullets.add(new Bullet(positionX, positionY, 2));
				gunCooldown = 30;
			}

			else if (input.contains(controls.get("ctrl_shoot_down"))) {
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

	public int getNumLives() {
		return numLives;
	}

	public void setNumLives(int x) {
		numLives = x;
	}
	
	public void loadControls() throws IOException {
		try {
			File f = new File("src/cps240final/controls");
			Scanner s = new Scanner(f);
			String in;
			String[] keymap = new String[2];
			
			while (s.hasNext()) {
				in = s.nextLine();
				keymap = in.split(":");
				setControlSetting(keymap[0], keymap[1]);
			}
			
			s.close();
		} catch (Exception e) {
			System.err.println("Control config file either not found, or not formatted correctly.");
			System.err.println(e);
		}
	}

	public String getControlSetting(String s) {
		if (controls.get(s) != null)
			return controls.get(s);
		return "No key named " + s;
	}
	
	public void setControlSetting(String keyname, String keymap) {
		if (controls.get(keyname) != null)
			controls.put(keyname, keymap);
		else
			System.err.println("No such key named " + keyname);
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
							break;
						}
				if (bill.offScreen() || bill.getDeath())
					iterator.remove();
			}
	}
}
