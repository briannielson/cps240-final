package cps240final;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Scene currentScene;
	public static double windowSizeX;
	public static double windowSizeY;
	public static boolean pauseState = false;
	public static Player p1;

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
	    
	    TitleMenu tm = new TitleMenu(theScene);
	    theStage.setScene(tm.title); //shows the title screen first
		tm.start.setOnAction(new EventHandler<ActionEvent>() { //creates action for the start button, which starts the game
			@Override
			public void handle(ActionEvent event) {
				tm.check = true;
				System.out.println(tm.check);
				theStage.setScene( theScene ); //unfortunately, the zombies position is still changing
			}
		});
		
	    currentScene = theScene; // currentScene is used across other classes so that we know what to paint to...
	    
	    Canvas canvas = new Canvas( windowSizeX, windowSizeY );
	    root.getChildren().add( canvas );

	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    p1 = new Player();
	    Enemy zombie = new Enemy(200,200,"zombie");
		
		new AnimationTimer()
	    {			
	        public void handle(long currentNanoTime) // main timer for game
	        {
	        	gc.clearRect(0, 0, 512,512);
	        	
	        	zombie.updatePosition();
	        	zombie.render(gc);
	        	
	        	p1.handleInput();
	        	p1.render(gc);
	        	p1.renderProjectiles(gc);
	        }
	    }.start();
	    
	    theStage.show();
	}
}
