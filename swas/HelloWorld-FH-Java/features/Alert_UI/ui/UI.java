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

import Base.Alert;

public class UI extends Application {

    private ListView<HBox> messageListView;

    @Override
    public void start(Stage stage) {
        messageListView = new ListView<HBox>();

        String[] messages = { "Thunderstorm!", "UVdfsigbsifdb!" };

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

        startConsoleInputThread();
    }

    private HBox createMessageBubble(String message, boolean isSent) {
        Text messageText = new Text(message);
        messageText.setWrappingWidth(300);

        HBox messageBox = new HBox();
        messageBox.setAlignment(isSent ? Pos.BOTTOM_RIGHT : Pos.BOTTOM_LEFT);
        messageBox.setSpacing(10);

        TextFlow textFlow = new TextFlow(messageText);
        textFlow.setStyle("-fx-background-color: " + (isSent ? "lightblue" : "lightgray") + "; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10;");

        messageBox.getChildren().add(textFlow);
        return messageBox;
    }


    public void alertToUI(final Alert alert, final boolean isSent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageListView.getItems().add(createMessageBubble(alert.toString(), isSent));
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
                    
                    alertToUI(new Alert(), false);
                }
            }
        });

        consoleInputThread.setDaemon(true);
        consoleInputThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
