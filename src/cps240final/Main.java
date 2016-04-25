package cps240final;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Scene currentScene;
	public static double windowSizeX;
	public static double windowSizeY;
	public static boolean pauseState = false;
	public static Player p1;
	public static ArrayList<Enemy> mobs = new ArrayList<Enemy>();
	
	private int timer = 0;

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
	    theStage.setScene(tm.titleScene); //shows the title screen first
	    tm.start.setOnAction(new EventHandler<ActionEvent>() { //creates action for the start button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( theScene ); //unfortunately, the zombies position is still changing
			}
		});
	    
	    tm.options.setOnAction(new EventHandler<ActionEvent>() { //creates action for the options button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				theStage.setScene( tm.optionScene );
				System.out.println("Hi");
			}
		});
		
	    currentScene = theScene; // currentScene is used across other classes so that we know what to paint to...
	    
	    Canvas canvas = new Canvas( windowSizeX, windowSizeY );
	    root.getChildren().add( canvas );

	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    p1 = new Player();
	    p1.loadControls();
		
		new AnimationTimer()
	    {			
	        public void handle(long currentNanoTime) // main timer for game
	        {
	        	gc.clearRect(0, 0, 512,512);
	        	
	        	if (timer == 0) {
	        		mobs.add(new Enemy(200,200,"zombie"));
	        		timer = 120;
	        	} else timer--;
	        	
	        	p1.renderProjectiles(gc); // has to be first in rendering because it modifies other lists
	        	
	        	for (Iterator<Enemy> iterator = mobs.iterator(); iterator.hasNext(); ) {
	        		Enemy e = iterator.next();
	        		
	        		if (e.getDeath()) {
	        			iterator.remove();
	        			continue;
	        		}
	        		e.updatePosition();
	        		e.render(gc);
	        	}
	        	
	        	p1.handleInput();
	        	p1.render(gc);
	        }
	    }.start();
	    
	    theStage.show();
	}
}
