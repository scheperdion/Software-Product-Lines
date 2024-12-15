package ui;

import java.util.Locale;

import event.AbstractEvent;
import event.EventLocation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class UI extends Application {
	public void receivedEvent(AbstractEvent event) {
		
		original(event);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("received event");
				for(EventLocation location : event.getArea())
				{
					String cmd = String.format(Locale.US, "recolorLastCircle(\'%s\')",event.getSeverity());
					System.out.println(cmd);
					instance.map.getEngine().executeScript(cmd);
				}
			}
		});
	}
}
