package ui; 

import javafx.application.Application; 
import javafx.application.Platform; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.ListView; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.scene.text.Text; 
import javafx.scene.text.TextFlow; 
import javafx.stage.Stage; 

import java.util.Scanner; 

import client.*; 
import event.*; 
import client.IClientCallback; 

public  class  UI  extends Application  implements IClientCallback {
	
	private static UI instance;

	
	private ListView<HBox> messageListView;

	
	
	public UI() {
		instance = this;
	}

	
	
	public static UI getInstance() {
		return instance;
	}

	

	@Override
	public void start(Stage stage) {
		messageListView = new ListView<HBox>();
		VBox root = new VBox(messageListView);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		messageListView.setPrefHeight(600);
		messageListView.setPrefWidth(400);

		Scene scene = new Scene(root, 400, 600);
		stage.setTitle("Swas");
		stage.setScene(scene);
		stage.show();
	}

	

	public HBox createMessageBubble(String message, boolean right) {
		Text messageText = new Text(message);
		messageText.setWrappingWidth(300);

		HBox messageBox = new HBox();
		messageBox.setAlignment(right ? Pos.BOTTOM_RIGHT : Pos.BOTTOM_LEFT);
		messageBox.setSpacing(10);

		TextFlow textFlow = new TextFlow(messageText);
		textFlow.setStyle("-fx-background-color: " + (right ? "lightblue" : "lightgray") + "; "
				+ "-fx-background-radius: 15; " + "-fx-padding: 10;");

		messageBox.getChildren().add(textFlow);
		return messageBox;
	}

	
	
	@Override
	public void receivedEvent(AbstractEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				instance.messageListView.getItems().add(createMessageBubble(event.toString(), false));
			}
		});
	}

	

	public static void go(String[] args) {
		launch(args);
	}


}
