package cps240final;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//https://www.videoblocks.com/videos/motion-backgrounds
import javafx.stage.Stage;

public class TitleMenu {

	public void start(Stage theStage) {
		theStage.setTitle("Rock n' Rollfare");
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		Canvas canvas = new Canvas (400,400);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
		gc.fillText("ROCK N' ROLLFARE", 60, 50);
		gc.strokeText("ROCK N' ROLLFARE", 60, 50);
		
		Image bg = new Image("bg.jpg");
		gc.drawImage(bg, 400, 400);
		
		theStage.show();
	}
}
