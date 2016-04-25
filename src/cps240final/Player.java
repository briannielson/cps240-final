package cps240final;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Player extends Sprite {
	private ArrayList<String> input;
	private String ctrl_up, ctrl_down, ctrl_left, ctrl_right, ctrl_pause, ctrl_shoot_up, ctrl_shoot_down, ctrl_shoot_left, ctrl_shoot_right;

	public Player() {
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
				input.remove(code);
			}
		});
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
	
	public void updatePosition() {
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
	}
}
