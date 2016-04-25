package cps240final;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
	private ArrayList<String> input;
	private String ctrl_up, ctrl_down, ctrl_left, ctrl_right, ctrl_pause, ctrl_shoot_up, ctrl_shoot_down, ctrl_shoot_left, ctrl_shoot_right;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int gunCooldown;

	public Player() {
		setImage(new Image( "/cps240final/sprites/bennyhill.jpg" ));
		setupInputDefaults();
		
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
				if (code.equals(ctrl_pause)) Main.pauseState = Main.pauseState ? false : true; 
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
		ctrl_up 			= "W";
		ctrl_down 			= "S";
		ctrl_left 			= "A";
		ctrl_right 			= "D";
		ctrl_pause 			= "ESC";
		ctrl_shoot_up 		= "UP";
		ctrl_shoot_down		= "DOWN";
		ctrl_shoot_left		= "LEFT";
		ctrl_shoot_right 	= "RIGHT";
	}
	
	public void handleInput() {
		if (Main.pauseState)
			return;
		// movement
		double x = 0,y = 0;
		if (input.contains(ctrl_left))
        	x -= 2;
        else if (input.contains(ctrl_right))
        	x += 2;
        if (input.contains(ctrl_up))
        	y -= 2;
        else if (input.contains(ctrl_down))
        	y += 2;
        update(x,y);
        
        // guns
        if (gunCooldown == 0) {
	        if (input.contains(ctrl_shoot_left) && input.contains(ctrl_shoot_up)) {
	        	bullets.add(new Bullet(positionX, positionY, 7));
	        	gunCooldown = 30;
	        }
	        else if (input.contains(ctrl_shoot_left) && input.contains(ctrl_shoot_down)) {
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
			for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
				Bullet bill = iterator.next();
				bill.updatePosition();
				if (bill.offScreen())
					iterator.remove();
			}
	}
}
