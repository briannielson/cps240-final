package cps240final;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
//https://www.videoblocks.com/videos/motion-backgrounds

public class TitleMenu {
	public Scene titleScene, optionScene, controlScene, holdScene;
	public Button start = new Button("Start Game");
	public Button options = new Button("Options");
	public Button credits = new Button("Credits");
	public Button titleMenu = new Button("Back");
	public Button controls = new Button("Controls");
	public Button audio = new Button("Audio");
	public Button fullscreen = new Button("Fullscreen");
	public Button back = new Button("Back");
	
	public TitleMenu() {
		//Title Menu scene starts here
		Group rootT = new Group();
		titleScene = new Scene(rootT);
		Canvas canvasT = new Canvas (512,512);
		rootT.getChildren().add(canvasT);
		
		GraphicsContext gcT = canvasT.getGraphicsContext2D();
		gcT.setTextAlign(TextAlignment.CENTER);
		gcT.setTextBaseline(VPos.CENTER);
		gcT.setFill(Color.RED);
		gcT.setStroke(Color.BLACK);
		gcT.setLineWidth(2);
		Font theFont = Font.font("CHILLER", FontWeight.BOLD, 48);
		gcT.setFont(theFont);
		gcT.fillText("ROCK N' ROLLFARE", Math.round(canvasT.getWidth()  / 2), Math.round(canvasT.getHeight() / 6));
		gcT.strokeText("ROCK N' ROLLFARE", Math.round(canvasT.getWidth()  / 2), Math.round(canvasT.getHeight() / 6));
		
		System.out.println(gcT.getTextAlign());

		start.setMaxWidth(Double.MAX_VALUE);
		options.setMaxWidth(Double.MAX_VALUE);
		credits.setMaxWidth(Double.MAX_VALUE);
		titleMenu.setMaxWidth(Double.MAX_VALUE);
		controls.setMaxWidth(Double.MAX_VALUE);
		audio.setMaxWidth(Double.MAX_VALUE);
		fullscreen.setMaxWidth(Double.MAX_VALUE);
		
		VBox butts = new VBox();
		butts.setSpacing(10);
		butts.setPadding(new Insets(0,20,10,20));
		butts.getChildren().addAll(start, options, credits);
		rootT.getChildren().add(butts);
		butts.setLayoutY(200);
		butts.setLayoutX(200);
		//butts.setAlignment(Pos.CENTER);
		//Title Menu Scene Stops here
	}

	public void optMethod() {
		Group rootO = new Group();
		optionScene = new Scene(rootO);
		Canvas canvasO = new Canvas(512,512);
		rootO.getChildren().add(canvasO);
		
		GraphicsContext gcO = canvasO.getGraphicsContext2D();
		gcO.setTextAlign(TextAlignment.CENTER);
		gcO.setTextBaseline(VPos.CENTER);
		gcO.setFill(Color.RED);
		gcO.setStroke(Color.BLACK);
		gcO.setLineWidth(2);
		Font theFontO = Font.font("CHILLER", FontWeight.BOLD, 48);
		gcO.setFont(theFontO);
		gcO.fillText("OPTIONS", Math.round(canvasO.getWidth()  / 2), Math.round(canvasO.getHeight() / 6));
		gcO.strokeText("OPTIONS", Math.round(canvasO.getWidth()  / 2), Math.round(canvasO.getHeight() / 6));
		
		VBox optButts = new VBox();
		optButts.setSpacing(10);
		optButts.setPadding(new Insets(0,20,10,20));
		optButts.getChildren().addAll(back, controls, audio, fullscreen);
		rootO.getChildren().add(optButts);
		optButts.setLayoutY(200);
		optButts.setLayoutX(200);
	}
	
	public void conMethod() {
		Group rootCon = new Group();
		controlScene = new Scene(rootCon);
		Canvas canvasCon = new Canvas(512,512);
		rootCon.getChildren().add(canvasCon);
		
		GraphicsContext gcCon = canvasCon.getGraphicsContext2D();
		gcCon.setTextAlign(TextAlignment.CENTER);
		gcCon.setTextBaseline(VPos.CENTER);
		gcCon.setFill(Color.RED);
		gcCon.setStroke(Color.BLACK);
		gcCon.setLineWidth(2);
		Font theFontCon = Font.font("CHILLER", FontWeight.BOLD, 48);
		gcCon.setFont(theFontCon);
		gcCon.fillText("CONTROLS", Math.round(canvasCon.getWidth()  / 2), Math.round(canvasCon.getHeight() / 6));
		gcCon.strokeText("CONTROLS", Math.round(canvasCon.getWidth()  / 2), Math.round(canvasCon.getHeight() / 6));
		//options = new Button("Back");
		VBox conButts = new VBox();
		conButts.setSpacing(10);
		conButts.setPadding(new Insets(0,20,10,20));
		conButts.getChildren().addAll(back);
		rootCon.getChildren().add(conButts);
		conButts.setLayoutY(200);
		conButts.setLayoutX(200);
	}

}
