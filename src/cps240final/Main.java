package cps240final;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Scene currentScene;

	public static void main(String[] args) {
		launch(args);
	}
	
	/*@Override
	public void init() {
		
	}*/

	@Override
	public void start(Stage theStage) throws Exception {
		// set up resources
		// Set up scenes
		
		theStage.setTitle( "CPS240 - Final" );
		 
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    
	    theStage.setScene( theScene );
	    currentScene = theScene; // currentScene is used across other classes so that we know what to paint to...
	    
	    Canvas canvas = new Canvas( 512, 512 );
	    root.getChildren().add( canvas );
	 
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    Player p1 = new Player();
	    p1.setImage(new Image( "/cps240final/sprites/bennyhill.jpg" ));
	    p1.setupInputDefaults();
		
		new AnimationTimer()
	    {			
	        public void handle(long currentNanoTime) // main timer for game
	        {
	        	gc.clearRect(0, 0, 512,512);
	        	
	        	p1.updatePosition();
	        	p1.render(gc);
	        }
	    }.start();
	    
	    theStage.show();
	}
}
