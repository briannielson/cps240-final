package cps240final;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Scene currentScene;
	public static double windowSizeX;
	public static double windowSizeY;
	public static boolean pauseState = false;
	public static Player p1;
	public static ArrayList<Enemy> mobs = new ArrayList<Enemy>();
	public static ArrayList<MapObject> map = new ArrayList<MapObject>();
	private long cycle = 0;
	public static boolean fs = false;

	public static void main(String[] args) {
		launch(args);
	}
	
	/*@Override
	public void init() {
		
	}*/

	@Override
	public void start(Stage theStage) throws Exception {
		windowSizeX = 512;
		windowSizeY = 512;
		// set up resources
		// Set up scenes
		
		theStage.setTitle( "CPS240 - Final" );
		 
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    
	    TitleMenu tm = new TitleMenu();
		
	    currentScene = theScene; // currentScene is used across other classes so that we know what to paint to...
	    
	    Canvas canvas = new Canvas( windowSizeX, windowSizeY );
	    root.getChildren().add( canvas );

	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    // Set player object and constants
	    p1 = new Player();
	    p1.loadControls();
	    p1.setPosition(windowSizeX / 2, windowSizeY / 2 );
	    
	    // Load in Map (Its a class but its actually just a way to store the map)
		LevelOne levelOne = new LevelOne();
	    
		// naming our class so we can call it later
		AnimationTimer mainGameLoop = new AnimationTimer()
	    {
	        public void handle(long currentNanoTime) // main timer for game
	        {
	        	if (currentNanoTime - cycle >= 3000 && !pauseState) {
		        	gc.clearRect(0, 0, 512,512);
		        	
		        	levelOne.weightedZSpawner();
		        	
		        	if (p1.getDeath() && p1.getNumLives() < 0) {
		        		this.stop();
		        		mobs.clear();
		        		map.clear();
		        		theStage.setScene(tm.titleScene);
		        	} else if (p1.getDeath()) {
		        		mobs.clear();
		        		map.clear();
		        		new LevelOne();
		        		p1.setHealth(100);
		        		p1.setNumLives(p1.getNumLives() - 1);
		        		p1.unDeath();
		        	}
		        	
		        	p1.handleInput();
		        	p1.renderProjectiles(gc); // has to be first in rendering because it modifies other lists
		        	p1.render(gc);
		        	
		        	for (Iterator<MapObject> iterator = map.iterator(); iterator.hasNext(); ) {
		        		MapObject e = iterator.next();
		        		
		        		if (e.getDeath()) {
		        			iterator.remove();
		        			continue;
		        		}
		        		
		        		e.tick();
		        		e.render(gc);
		        	}
		        	
		        	for (Iterator<Enemy> iterator = mobs.iterator(); iterator.hasNext(); ) {
		        		Enemy e = iterator.next();
		        		
		        		if (e.getDeath()) {
		        			iterator.remove();
		        			continue;
		        		}
		        		e.updatePosition();
		        		e.render(gc);
		        	}
		        	
		        	cycle = currentNanoTime;
	        	}
	        }
	    };
	    
	    // keep event listeners at the bottom so they are context sensitive
	    
	    theStage.setScene(tm.titleScene); //shows the title screen first
	    tm.start.setOnAction(new EventHandler<ActionEvent>() { //creates action for the start button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( theScene );
				// Set player object and constants
			    p1 = new Player();
			    try {
			    	p1.loadControls();
			    } catch (IOException e) {
			    	System.out.println("Couldn't find controls file!");
			    }
			    p1.setPosition(windowSizeX / 2, windowSizeY / 2 );
			    
			    // Load in Map (Its a class but its actually just a way to store the map)
				new LevelOne();
				
				mainGameLoop.start();
			}
		});
	    
	    tm.options.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				tm.optMethod();
				theStage.setScene( tm.optionScene );
			}
		});
	    
	    tm.titleMenu.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( tm.titleScene );
			}
		});
	    
	    tm.controls.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				tm.conMethod();
				theStage.setScene( tm.controlScene );
			}
		});
	    
	    tm.audio.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				tm.audMethod();
				theStage.setScene( tm.audScene );
			}
		});
	    
	    tm.credits.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				tm.credMethod();
				theStage.setScene( tm.credScene );
			}
		});
	    
	    tm.audBack.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( tm.optionScene );
			}
		});
	    
	    tm.conBack.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( tm.optionScene );
			}
		});
	    
	    tm.credBack.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( tm.titleScene );
			}
		});
	    
	    tm.optBack.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( tm.titleScene );
			}
		});
	    
	    tm.fullscreen.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
				if (!fs) {
			        theStage.setX(primaryScreenBounds.getMinX());
			        theStage.setY(primaryScreenBounds.getMinY());
			        theStage.setWidth(primaryScreenBounds.getWidth());
			        theStage.setHeight(primaryScreenBounds.getHeight());
			        fs = true;
			        tm.fullscreen.setText("Fullscreen");
				}
				else {
					theStage.sizeToScene();
					fs = false;
			        tm.fullscreen.setText("Fullscreen");
					
				}
			}
		});
	  
	    theStage.show();
	}
	
	public static void test() {
		
	}
}
