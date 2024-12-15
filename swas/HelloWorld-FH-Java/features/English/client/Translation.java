package client;

public class Translation {
	public static String swas_title() { return "Severe Weather Alerting System"; };
	public static String earthquake_event_message(String severity, double lat, double lon) { 
		return String.format("Earthquake ALERT %s severity at %f lat and %f long", severity, lat, lon);
	};
	public static String toggle_dark_mode() { return "Toggle Dark Mode"; };
}