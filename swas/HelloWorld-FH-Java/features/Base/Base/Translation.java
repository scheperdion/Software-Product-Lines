package Base;

public class Translation {
	public static String swas_title() { return "swas_title"; };
	public static String earthquake_event_message(String severity, double lat, double lon) { 
		return String.format("earthquake %s, at %f lat and %f long", severity, lat, lon);
	};
	public static String toggle_dark_mode() { return "toggle_dark_mode"; };
	
//	public static String flooding_event_message() { return "flooding_event_message"; };
//	public static String thunderstorm_event_message() { return "thunderstorm_event_message"; };
}