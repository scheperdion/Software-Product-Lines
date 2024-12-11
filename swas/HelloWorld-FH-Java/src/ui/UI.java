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

public  class  UI  extends Application {
	

    private ListView<HBox> messageListView;

	
    private Client client;

	

    @Override
    public void start(Stage stage) {
        messageListView = new ListView<HBox>();

        String[] messages = { "Thunderstorm!", "UV!" };

        for (String message : messages) {
            messageListView.getItems().add(createMessageBubble(message, false));
        }

        VBox root = new VBox(messageListView);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);
        messageListView.setPrefHeight(600);
        messageListView.setPrefWidth(400);

        Scene scene = new Scene(root, 400, 600);
        stage.setTitle("Swas");
        stage.setScene(scene);
        stage.show();

        UI ui = this;
		Thread t = new Thread(client);
		t.start();
        
//        startConsoleInputThread();
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                client.updateUI(ui);
//                ui.startConsoleInputThread();
            }
        });
        clientThread.start();
    }

	

    public HBox createMessageBubble(String message, boolean right) {
        Text messageText = new Text(message);
        messageText.setWrappingWidth(300);

        HBox messageBox = new HBox();
        messageBox.setAlignment(right ? Pos.BOTTOM_RIGHT : Pos.BOTTOM_LEFT);
        messageBox.setSpacing(10);

        TextFlow textFlow = new TextFlow(messageText);
        textFlow.setStyle("-fx-background-color: " + (right ? "lightblue" : "lightgray") + "; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10;");

        messageBox.getChildren().add(textFlow);
        return messageBox;
    }

	


    public void eventToUI(final AbstractEvent event, final boolean right) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageListView.getItems().add(createMessageBubble(event.toString(), right));
            }
        });
    }

	

    private void startConsoleInputThread() {
        Thread consoleInputThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Enter message: ");
                    String input = scanner.nextLine();
                    
                    eventToUI(new NoEvent("noEvent"), false);
                }
            }
        });

        consoleInputThread.setDaemon(true);
        consoleInputThread.start();
    }

	

    public static void go(String[] args) {
    	launch(args);
    }

	
    
    public UI() {
    	this.client = new Client();
    }


}
