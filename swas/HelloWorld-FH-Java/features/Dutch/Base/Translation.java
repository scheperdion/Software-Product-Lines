package Base;

public class Translation {
	public static String swas_title() { return "Weersextremen Waarschuwer"; };
	public static String earthquake_event_message(String severity, double lat, double lon) { 
		return String.format("Aardbeving ATTENTIE %s ernstigheid bij %f lat en %f long", severity, lat, lon);
	};
	public static String thunderstorm_event_message(String severity, double lat, double lon) { 
		return String.format("Onweer ATTENTIE %s ernstigheid bij %f lat en %f long", severity, lat, lon);
	};
	public static String flooding_event_message(String severity, double lat, double lon) { 
		return String.format("Overstroming ATTENTIE %s ernstigheid bij %f lat en %f long", severity, lat, lon);
	};
	public static String toggle_dark_mode() { return "Donkere Modus"; };
}