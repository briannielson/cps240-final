package cps240final;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//https://www.videoblocks.com/videos/motion-backgrounds

public class TitleMenu {
	public Scene title, theScene;
	public Image image;
	public double width, height;
	public boolean check = false;
	Button start = new Button("Start Game");
	Button options = new Button("Options");
	Button credits = new Button("Credits");
	
	public TitleMenu(Scene theScene) {
		this.theScene = theScene;
		Group root = new Group();
		title = new Scene(root);
		Canvas canvas = new Canvas (512,512);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font theFont = Font.font("CHILLER", FontWeight.BOLD, 48);
		gc.setFont(theFont);
		gc.fillText("ROCK N' ROLLFARE", 5, 50);
		gc.strokeText("ROCK N' ROLLFARE", 5, 50);

		start.setMaxWidth(Double.MAX_VALUE);
		options.setMaxWidth(Double.MAX_VALUE);
		credits.setMaxWidth(Double.MAX_VALUE);
		
		VBox butts = new VBox();
		butts.setSpacing(10);
		butts.setPadding(new Insets(0,20,10,20));
		butts.getChildren().addAll(start,options,credits);
		root.getChildren().add(butts);
		butts.setLayoutY(100);
		

	}
	

}
