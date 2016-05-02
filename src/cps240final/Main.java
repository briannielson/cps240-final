/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * Main
 * 
 * Where the magic happens. Static variables are setup, Scene and canvases are setup,
 * and 100 lines of event handlers are listening. In our main game loop:
 * -- Check level status
 * -- Spawn Zombies
 * -- Check Player status
 * -- Update and render player, zombies, and projectiles
 * -- update score, health, and lives
 * Everything else is typical JavaFX custodial work.
 */

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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Scene currentScene;
	public static double windowSizeX;
	public static double windowSizeY;
	public static boolean pauseState = false;
	public static boolean pauseText = false;
	public static Player p1;
	public static ArrayList<Enemy> mobs = new ArrayList<Enemy>();
	public static ArrayList<MapObject> map = new ArrayList<MapObject>();
	private long cycle = 0;
	public static boolean fs = false;
	public static int score = 0, lives = 3, levelFlag = 0;

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
	    
	    Image myBI= new Image("/cps240final/sprites/map1.png");
	    
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
		
		//Load in score and lives
		Font theFont = Font.font("Courier New", FontWeight.BOLD, 24);
		Text stext = new Text(); //stext = score text
		stext.setFill(Color.WHITE);
		stext.setStroke(Color.BLACK);
		stext.setStrokeWidth(1.3);
		stext.setFont(theFont);
		stext.setText("Score:" + score);
		stext.setY(stext.getLayoutBounds().getHeight());
		
		Text ltext = new Text(); //ltext = lives text
		ltext.setFill(Color.WHITE);
		ltext.setStroke(Color.BLACK);
		ltext.setStrokeWidth(1.3);
		ltext.setFont(theFont);
		ltext.setText(p1.getNumLives() + " Lives");
		ltext.setY(stext.getLayoutBounds().getHeight());
		ltext.setX(windowSizeX - stext.getLayoutBounds().getWidth());
		
		Text htext = new Text(); //htext = health text
		htext.setFill(Color.WHITE);
		htext.setStroke(Color.BLACK);
		htext.setStrokeWidth(1.3);
		htext.setFont(theFont);
		htext.setText(p1.getHealth() + " Health");
		htext.setY(htext.getLayoutBounds().getHeight() + stext.getLayoutBounds().getHeight());
		htext.setX(windowSizeX - htext.getLayoutBounds().getWidth());
		
		Text lvtext = new Text(); //lvtext = level text
		lvtext.setFill(Color.WHITE);
		lvtext.setStroke(Color.BLACK);
		lvtext.setStrokeWidth(1.3);
		lvtext.setFont(theFont);
		lvtext.setText("Level " + levelFlag);
		lvtext.setY(htext.getLayoutBounds().getHeight() + stext.getLayoutBounds().getHeight() + lvtext.getLayoutBounds().getHeight());
		lvtext.setX(windowSizeX - htext.getLayoutBounds().getWidth());
		
		Text ptext = new Text(); //ptext = paused text
		Font pFont = Font.font("Courier New", FontWeight.BOLD, 36);
		ptext.setFill(Color.WHITE);
		ptext.setStroke(Color.BLACK);
		ptext.setStrokeWidth(1.3);
		ptext.setFont(pFont);
		ptext.setText("Paused");
		ptext.setY(windowSizeY / 2 - ptext.getLayoutBounds().getHeight() - 30);
		ptext.setX(windowSizeX / 2 - ptext.getLayoutBounds().getWidth() / 2);
		
		root.getChildren().addAll(stext, ltext, htext, lvtext);
	    
		// naming our class so we can call it later
		AnimationTimer mainGameLoop = new AnimationTimer()
	    {
	        public void handle(long currentNanoTime) // main timer for game
	        {
	        	if (currentNanoTime - cycle >= 3000 && !pauseState) {
	        		if (pauseText) {
	        			root.getChildren().remove(ptext);
	        			pauseText = false;
	        		}
		        	gc.clearRect(0, 0, 512,512);
		        	
		        	// levels
		        	if (levelFlag == 0 && score >= 100) {
		        		levelOne.incrementThreshhold();
		        		levelFlag = 1;
		        		p1.improveGun();
		        	}
		        	else if (levelFlag == 1 && score >= 500) {
		        		levelOne.incrementThreshhold();
		        		levelFlag = 2;
		        		p1.improveGun();
		        	} else if (levelFlag == 2 && score >= 2000) {
		        		levelOne.incrementThreshhold();
		        		levelFlag = 3;
		        		p1.improveGun();
		        	} else if (levelFlag == 3 && score >= 5000) {
		        		levelOne.incrementThreshhold();
		        		levelFlag = 4;
		        		p1.improveGun();
		        	} else if (levelFlag == 4 && score >= 10000) {
		        		levelOne.incrementThreshhold();
		        		levelFlag = 5;
		        		p1.improveGun();
		        	}
		        	
		        	
		        	gc.drawImage(myBI, 0, 0);
		        	
		        	levelOne.weightedZSpawner();
		        	
		        	if (p1.getDeath() && p1.getNumLives() <= 0) {
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
		        			score = score + 10;
		        			stext.setText("Score:" + score);
		        			continue;
		        		}
		        		e.updatePosition();
		        		e.render(gc);
		        	}
		        	
		        	ltext.setText(p1.getNumLives() + " Lives");
		        	htext.setText(p1.getHealth() + " Health");
		        	lvtext.setText("Level " + levelFlag);
		        	cycle = currentNanoTime;
	        	} else if (!pauseText && pauseState) {
	        		root.getChildren().add(ptext);
	        		pauseText = true;
	        		gc.drawImage(new Image("/cps240final/sprites/controls.png"), 0, 0);
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
