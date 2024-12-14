package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UI extends Application {
    private VBox addUIElements(VBox root) {
    	root = original(root);
    	WebView map = this.map;
    	
        Button darkModeButton = new Button("Toggle Dark Mode");
        darkModeButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
    	    @Override
    	    public void handle(javafx.event.ActionEvent event) {
    	    	map.getEngine().executeScript("toggleDarkMode()");
    	    }
    	});
        root.getChildren().addAll(darkModeButton);
        
        return root;
    }

}
