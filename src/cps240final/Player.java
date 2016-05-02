/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * Player
 * 
 * The protagonist lives here. The player handles spawning projectiles,
 * input, movement speed, and how fast they can shoot. Also, Player is
 * the only Sprite with the unDeath() method (Who is the real zombie???)
 */

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
	private int gunCooldown = 10;
	private int currentGunCd = 0;
	private int numLives;
	private int invincFrames;
	//private ArrayList<String> currentEffects = new ArrayList<String>();

	public Player() {
		setImage(new Image("/cps240final/sprites/survivor-idle_handgun.png"));
		setupInputDefaults();
		velocity = 2;
		
		// Set inital lives and health
		setHealth(100);
		numLives = 3;

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
				//System.out.println("Hi");
				input.remove(code);
			}
		});
	}
	
	public void improveGun() {
		gunCooldown--;
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
		if (input.contains(controls.get("ctrl_right")))
			x += velocity;
		if (input.contains(controls.get("ctrl_left")))
			x -= velocity;
		if (input.contains(controls.get("ctrl_up")))
			y -= velocity;
		if (input.contains(controls.get("ctrl_down")))
			y += velocity;
		update(x, y);

		// guns
		if (currentGunCd == 0) {
			if (input.contains(controls.get("ctrl_shoot_left")) && input.contains(controls.get("ctrl_shoot_up"))) {
				bullets.add(new Bullet(positionX, positionY, 7));
				currentGunCd = gunCooldown;
				rotation = 225;
			} else if (input.contains(controls.get("ctrl_shoot_left"))
					&& input.contains(controls.get("ctrl_shoot_down"))) {
				bullets.add(new Bullet(positionX, positionY, 5));
				currentGunCd = gunCooldown;
				rotation = 135;
			}

			else if (input.contains(controls.get("ctrl_shoot_right"))
					&& input.contains(controls.get("ctrl_shoot_up"))) {
				bullets.add(new Bullet(positionX, positionY, 1));
				currentGunCd = gunCooldown;
				rotation = 315;
			}

			else if (input.contains(controls.get("ctrl_shoot_right"))
					&& input.contains(controls.get("ctrl_shoot_down"))) {
				bullets.add(new Bullet(positionX, positionY, 3));
				currentGunCd = gunCooldown;
				rotation = 45;
			}

			else if (input.contains(controls.get("ctrl_shoot_left"))) {
				bullets.add(new Bullet(positionX, positionY, 6));
				currentGunCd = gunCooldown;
				rotation = 180;
			}

			else if (input.contains(controls.get("ctrl_shoot_up"))) {
				bullets.add(new Bullet(positionX, positionY, 0));
				currentGunCd = gunCooldown;
				rotation = 270;
			}

			else if (input.contains(controls.get("ctrl_shoot_right"))) {
				bullets.add(new Bullet(positionX, positionY, 2));
				currentGunCd = gunCooldown;
				rotation = 0;
			}

			else if (input.contains(controls.get("ctrl_shoot_down"))) {
				bullets.add(new Bullet(positionX, positionY, 4));
				currentGunCd = gunCooldown;
				rotation = 90;
			}
		} else {
			currentGunCd--;
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
	
	public boolean tickInvincFrames() {
		if (invincFrames > 0) {
			invincFrames--;
			return true;
		} return false;
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
	
	@Override
	public void update(double x, double y) {
		
		// check for intersection to outside of window AND for map objects
		positionX += x;
		if (positionX < Main.windowSizeX - getWidth() && positionX > 0)
			for (MapObject m : Main.map) {
				if (m.intersects(this)) {
					// System.out.println("X Intersection found");
					positionX -= x;
					break;
				}
			}
		else if (positionX < Main.windowSizeX - getWidth())
			positionX = 0;
		else
			positionX = Main.windowSizeX - getWidth();
		
		positionY += y;
		if (positionY < Main.windowSizeY - getHeight() && positionY > 0)
			for (MapObject m : Main.map) {
				// System.out.println(Main.map.indexOf(m));
				if (m.intersects(this)) {
					// System.out.println("Y Intersection found");
					positionY -= y;
					break;
				}
			}
		else if (positionY < Main.windowSizeY - getHeight())
			positionY = 0;
		else
			positionY = Main.windowSizeY - getHeight();
	}
	
	public void hit(int healthLost) {
		setHealth(getHealth() - healthLost);
		if (getHealth() <= 0)
			setDeath();
		invincFrames += 90;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if (tickInvincFrames() && invincFrames%5 < 2)
			return;
		super.render(gc);
	}
	
	public void unDeath() {
		dead = false;
	}

	private void updateProjectiles() {
		if (bullets.size() > 0)
			for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
				Bullet bill = iterator.next();
				bill.updatePosition();
				if (bill.offScreen() || bill.getDeath()) {
					iterator.remove();
					continue;
				}
				if (Main.mobs.size() > 0)
					for (Enemy mob : Main.mobs)
						if (mob.intersects(bill)) {
							mob.setDeath();
							bill.setDeath();
							break;
						}
			}
	}

	public void addBuff(ArrayList<Effects> buffs) {
		
	}
}
