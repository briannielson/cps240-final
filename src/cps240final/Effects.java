/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * Effects
 * 
 * Any player or enemy buffs would go here. This was made
 * to hold temporary effects ONLY. Any permanent buffs should
 * modify its' affecting class. 
 * 
 * We were planning to use this class to handle pickups, but
 * we did not implement them yet.
 */

package cps240final;

public class Effects {
	public int duration;
	public String effect;
	
	public Effects (int t, String e) {
		duration = t;
		effect = e;
	}
	
	public void parseEffect() {
		// cycle through a long condition statement to see what we do, then do it
	}
	
	public void healthPack() {
		
	}
	
	public void boots() {
		
	}
	
	public void fireRate() {
		
	}
	
	public void invincible() {
		
	}
	
	public void stickFeet() {
		
	}
}
