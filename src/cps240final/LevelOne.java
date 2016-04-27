package cps240final;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class LevelOne {
	private ArrayList<MapObject> map = new ArrayList<MapObject>();
	private int threshhold;
	private Random randoCalrisian = new Random();
	private ArrayList<Integer[]> zSpawnPoints = new ArrayList<Integer[]>();
	
	public LevelOne () {
		ArrayList<Effects> wallEffects = new ArrayList<Effects>();
		// Walls -- outer
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(0, 0, 206, 10, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(306, 0, 512, 10, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(502, 0, 512, 206, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(502, 306, 512, 512, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(306, 502, 512, 512, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(0, 502, 206, 512, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(0, 306, 10, 512, Color.BLACK);
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(0, 0, 10, 206, Color.BLACK);
		
		// Walls -- inner
		map.add( new MapObject(0, 0, true, wallEffects, false, false, 0, true) );
		map.get(map.size() - 1).setShapeAttr(300, 400, 310, 475, Color.BLACK);
		
		
		// Initialize Level
		Main.map = this.map;
		
		// Set spawning threshhold
		threshhold = 8;
		
		// Set spawn points for zombie spawner
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 0;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 230;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 230;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 0;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 480;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 230;
		zSpawnPoints.add(new Integer[2]);
		zSpawnPoints.get(zSpawnPoints.size() - 1)[0] = 230;
		zSpawnPoints.get(zSpawnPoints.size() - 1)[1] = 460;
		
	}
	
	public void weightedZSpawner () {
		int i,x,y;
		if (randoCalrisian.nextInt(10) > threshhold && !Main.pauseState) {
			i = randoCalrisian.nextInt(4);
			x = zSpawnPoints.get(i)[0];
			y = zSpawnPoints.get(i)[1];
			Main.mobs.add(new Enemy(x,y,"zombie"));
		}
	}
}
