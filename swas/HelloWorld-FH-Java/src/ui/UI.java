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
import javafx.scene.control.Button; 
import javafx.scene.layout.VBox; 

public   class  UI  extends Application  implements IClientCallback {
	
	private static UI instance;

	
	private ListView<HBox> messageListView;

	
	WebView map;

	

	public UI() {
		instance = this;
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
        return webView;
    }

	
    
     private VBox  addUIElements__wrappee__Extended_UI(VBox root) {
    	this.instance.map = createMap();
    	root.getChildren().addAll(map);
    	return root;
    }

	
    private VBox addUIElements(VBox root) {
    	root = addUIElements__wrappee__Extended_UI(root);
    	WebView map = this.map;
    	
        Button darkModeButton = new Button("Toggle Dark Mode");
        darkModeButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
    	    @Override
    	    public void handle(javafx.event.ActionEvent event) {
    	    	map.getEngine().executeScript("setView(1,1,1)");
    	    }
    	});
        root.getChildren().addAll(darkModeButton);

        return root;
    }

	
    
    private void positionMap() {
    }

	
    
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
        	
        positionMap();
    }

	

    public void eventToUI(final AbstractEvent event, final boolean right) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                TODO
            }
        });
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
