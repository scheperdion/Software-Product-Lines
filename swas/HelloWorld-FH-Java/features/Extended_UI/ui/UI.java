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

import java.util.Locale;
import java.util.Scanner;
import java.io.File;

import client.*;
import event.*;

import location.Location;

public class UI extends Application implements IClientCallback {
	private static UI instance;
	private ListView<HBox> messageListView;
	WebView map;

	public UI() {
		instance = this;
	}
	
	private void setupMapView(final WebView webView) {
	    webView.getEngine().getLoadWorker().stateProperty().addListener(
	        new javafx.beans.value.ChangeListener<javafx.concurrent.Worker.State>() {
	            @Override
	            public void changed(javafx.beans.value.ObservableValue<? extends javafx.concurrent.Worker.State> observable, 
	                                javafx.concurrent.Worker.State oldValue, 
	                                javafx.concurrent.Worker.State newValue) {
	                if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {
	                    // Execute JavaScript once the map is fully loaded
	                    Platform.runLater(new Runnable() {
	                        @Override
	                        public void run() {
	                        	String cmd = String.format("setView(%s)", Location.getString());
	                            webView.getEngine().executeScript(cmd);
	                        }
	                    });
	                }
	            }
	        }
	    );
	}
	
    public WebView createMap() {
        WebView webView = new WebView();
        File htmlFile = new File("features/Extended_UI/ui/map.html");
        if (htmlFile.exists()) {
            webView.getEngine().load(htmlFile.toURI().toString());
        } else {
            System.out.println("Map file not found: " + htmlFile.getAbsolutePath());
        }
        webView.setPrefSize(400, 400);
        setupMapView(webView);
        return webView;
    }
    
    private VBox addUIElements(VBox root) {
    	this.instance.map = createMap();
    	root.getChildren().addAll(map);
    	return root;
    }
    
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        addUIElements(root);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle(Translation.swas_title());
        stage.setScene(scene);
        stage.show();
    }
    
	@Override
	public void receivedEvent(AbstractEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("received event");
				for(EventLocation location : event.getArea())
				{
					String cmd = String.format(Locale.US, "addCustomMarker(%f, %f, %f, \'%s\')", location.latitude, location.longitude, location.radius,event.iconPath());
					System.out.println(cmd);
					instance.map.getEngine().executeScript(cmd);
				}
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
}
