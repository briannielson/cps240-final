package cps240final;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
//https://www.videoblocks.com/videos/motion-backgrounds

public class TitleMenu {
	public Scene titleScene, optionScene, controlScene, audScene, credScene;
	public Button start = new Button("Start Game");
	public Button options = new Button("Options");
	public Button credits = new Button("Credits");
	public Button titleMenu = new Button("Back");
	public Button controls = new Button("Controls");
	public Button audio = new Button("Audio");
	public Button fullscreen = new Button("Fullscreen");
	public Button conBack = new Button("Back");
	public Button optBack = new Button("Back");
	public Button audBack = new Button("Back");
	public Button credBack = new Button("Back");
	
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
		System.out.println(butts.getAlignment());
		butts.setAlignment(Pos.TOP_RIGHT);
		butts.setLayoutY(130);
		butts.setLayoutX(200);
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
		optButts.getChildren().addAll(controls, audio, fullscreen);
		rootO.getChildren().add(optButts);
		optButts.setLayoutY(130);
		optButts.setLayoutX(200);
		
		VBox backButt = new VBox();
		backButt.setSpacing(10);
		backButt.setPadding(new Insets(20,20,20,20));
		backButt.getChildren().addAll(optBack);
		rootO.getChildren().add(backButt);
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
		conButts.getChildren().addAll(conBack);
//		rootCon.getChildren().add(conButts);
		conButts.setLayoutY(130);
		conButts.setLayoutX(200);
		
		VBox backButt = new VBox();
		backButt.setSpacing(10);
		backButt.setPadding(new Insets(20,20,20,20));
		backButt.getChildren().addAll(conBack);
		rootCon.getChildren().add(backButt);
	}
	
	public void audMethod() {
		Group rootAud = new Group();
		audScene = new Scene(rootAud);
		Canvas canvasAud = new Canvas(512,512);
		rootAud.getChildren().add(canvasAud);
		
		GraphicsContext gcAud = canvasAud.getGraphicsContext2D();
		gcAud.setTextAlign(TextAlignment.CENTER);
		gcAud.setTextBaseline(VPos.CENTER);
		gcAud.setFill(Color.RED);
		gcAud.setStroke(Color.BLACK);
		gcAud.setLineWidth(2);
		Font theFontAud = Font.font("CHILLER", FontWeight.BOLD, 48);
		gcAud.setFont(theFontAud);
		gcAud.fillText("AUDIO", Math.round(canvasAud.getWidth()  / 2), Math.round(canvasAud.getHeight() / 6));
		gcAud.strokeText("AUDIO", Math.round(canvasAud.getWidth()  / 2), Math.round(canvasAud.getHeight() / 6));
		//options = new Button("Back");
		VBox audButts = new VBox();
		audButts.setSpacing(10);
		audButts.setPadding(new Insets(0,20,10,20));
//		audButts.getChildren().addAll(audBack);
		rootAud.getChildren().add(audButts);
		audButts.setLayoutY(130);
		audButts.setLayoutX(200);
		
		VBox backButt = new VBox();
		backButt.setSpacing(10);
		backButt.setPadding(new Insets(20,20,20,20));
		backButt.getChildren().addAll(audBack);
		rootAud.getChildren().add(backButt);
	}
	
	public void credMethod() {
		Group rootCred = new Group();
		credScene = new Scene(rootCred);
		Canvas canvasCred = new Canvas(512,512);
		rootCred.getChildren().add(canvasCred);
		
		GraphicsContext gcCred = canvasCred.getGraphicsContext2D();
		gcCred.setTextAlign(TextAlignment.CENTER);
		gcCred.setTextBaseline(VPos.CENTER);
		gcCred.setFill(Color.RED);
		gcCred.setStroke(Color.BLACK);
		gcCred.setLineWidth(2);
		Font theFontCred = Font.font("CHILLER", FontWeight.BOLD, 48);
		gcCred.setFont(theFontCred);
		gcCred.fillText("CREDITS", Math.round(canvasCred.getWidth()  / 2), Math.round(canvasCred.getHeight() / 6));
		gcCred.strokeText("CREDITS", Math.round(canvasCred.getWidth()  / 2), Math.round(canvasCred.getHeight() / 6));
		//options = new Button("Back");
		VBox backButt = new VBox();
		backButt.setSpacing(10);
		backButt.setPadding(new Insets(20,20,20,20));
		backButt.getChildren().addAll(credBack);
		rootCred.getChildren().add(backButt);
		
	}


}
