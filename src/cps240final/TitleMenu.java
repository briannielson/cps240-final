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
import javafx.stage.Stage;

public class TitleMenu {
	public boolean check = false;

	public void start(Stage theStage) {
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		Canvas canvas = new Canvas (400,400);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
		gc.fillText("ROCK N' ROLLFARE", 60, 50);
		gc.strokeText("ROCK N' ROLLFARE", 60, 50);
		
		Button start = new Button("Start Game");
		Button options = new Button("Options");
		Button credits = new Button("Credits");
		
		start.setMaxWidth(Double.MAX_VALUE);
		options.setMaxWidth(Double.MAX_VALUE);
		credits.setMaxWidth(Double.MAX_VALUE);
		
		VBox butts = new VBox();
		butts.setSpacing(10);
		butts.setPadding(new Insets(0,20,10,20));
		butts.getChildren().addAll(start,options,credits);
		root.getChildren().add(butts);
		butts.setLayoutY(100);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				check = true;
				System.out.println("test");
			}
		});
		
	}
}
