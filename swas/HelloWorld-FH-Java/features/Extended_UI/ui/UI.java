package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.web.WebView;
import java.util.Scanner;
import java.io.File;

import client.*;
import event.*;

public class UI extends Application {

    private ListView<HBox> messageListView;
    private Client client;
    private WebView map;

    public WebView createMap() {
        WebView webView = new WebView();
        File htmlFile = new File("features/Extended_UI/ui/map.html");
        if (htmlFile.exists()) {
            webView.getEngine().load(htmlFile.toURI().toString());
        } else {
            System.out.println("Map file not found: " + htmlFile.getAbsolutePath());
        }
        webView.setPrefSize(400, 400);
        return webView;
    }
    
    private VBox addUIElements(VBox root) {
    	this.map = createMap();
    	root.getChildren().addAll(map);
    	return root;
    }
    
//	Button callFunctionButton = new Button("Add Custom Pin");
//	callFunctionButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
//	    @Override
//	    public void handle(javafx.event.ActionEvent event) {
//	        // Example data
//
//	        double lat = 52.371807d; // Latitude
//	        double lng = 4.896029d; // Longitude
//	        String message = "\"Hello from San Francisco!\"";
//
//	        // Call the JavaScript function 'addCustomPin'
//	        String cmd = "addCustomPin("+Double.toString(lat)+","+Double.toString(lng) +","+message+")";
//	        System.out.println(cmd);
//	        map.getEngine().executeScript(
//	            cmd
//	        );
//	    }
//	});
    
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        addUIElements(root);
//        root.getChildren().addAll(map, callFunctionButton, darkModeButton);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Swas - Severe Weather Alert System");
        stage.setScene(scene);
        stage.show();

        final UI ui = this;
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                client.updateUI(ui);
            }
        });
        clientThread.start();
    }

    public void eventToUI(final AbstractEvent event, final boolean right) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                TODO
            }
        });
    }
    
    public static void go(String[] args) {
        launch(args);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public UI() {
    }
}