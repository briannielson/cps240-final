/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * LevelOne
 * 
 * Convenience class to hold the walls for levelOne, as well as
 * a means of progression by lowering the threshhold that 
 * zombies are able to spawn in by. This class will also handle
 * spawning zombies, since they spawn at specified coordinates
 * per each level (when there will be more than one level).
 */

package cps240final;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class LevelOne {
	private ArrayList<MapObject> map = new ArrayList<MapObject>();
	private int threshhold;
	private int timer = 10;
	private Random randoCalrisian = new Random();
	private ArrayList<Integer[]> zSpawnPoints = new ArrayList<Integer[]>();
	
	public LevelOne () {
		ArrayList<Effects> wallEffects = new ArrayList<Effects>();
		// Walls -- outer
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(112, 143, 153, 417, Color.BLACK);
		//map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		//map.get(map.size() - 1).setShapeAttr(94, 97, 137, 161, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(0, 345, 158, 420, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(0, 345, 108, 450, Color.BLACK);
		//map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		//map.get(map.size() - 1).setShapeAttr(153, 331, 197, 413, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(187, 0, 296, 71, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(297, 0, 512, 97, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(443, 0, 512, 123, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true, false) );
		map.get(map.size() - 1).setShapeAttr(478, 200, 512, 283, Color.BLACK);
		
		
		// Initialize Level
		Main.map = this.map;
		
		// Set spawning threshhold
		threshhold = 8;
		
		// Set spawn points for zombie spawner
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 0;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 230;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 0;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 0;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 390;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 118;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 130;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 0;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 400;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 465;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 460;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 465;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 420;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 160;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 0;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 460;
		
		// Setting values for our pathfinding
		PathFinding.setBlocked();
		
	}
	
	public void incrementThreshhold() {
		threshhold--;
	}
	
	public void weightedZSpawner () {
		timer--;
		int i,x,y;
		if (randoCalrisian.nextInt(10) > threshhold && timer <= 0) {
			i = randoCalrisian.nextInt(zSpawnPoints.size());
			x = zSpawnPoints.get(i)[0];
			y = zSpawnPoints.get(i)[1];
			Enemy newE = new Enemy(x,y,"zombie");
			for (Enemy e : Main.mobs) {
				if (e.intersects(newE)) {
					return;
				}
			}
			Main.mobs.add(newE);
		}
		
		timer = timer < 0 ? 50 : timer;
	}
}
