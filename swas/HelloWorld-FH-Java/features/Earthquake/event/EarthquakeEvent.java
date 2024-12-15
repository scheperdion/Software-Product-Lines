package event;

import java.time.LocalDateTime;

import client.Translation;

public class EarthquakeEvent extends AbstractEvent {
	private String severity;
	private String country;
	private String[] provinces;
	private EventLocation[] location;

	public EarthquakeEvent(String severity, String country, String[] provinces, EventLocation[] location) {
		this.type = "earthquake";

		this.severity = severity;
		this.country = country;
		this.provinces = provinces;
		this.location = location;
	}

	public String toString() {
		double lat = location.length > 0 ? location[0].latitude : 0;
		double lon = location.length > 0 ? location[0].longitude : 0;
		return Translation.earthquake_event_message(this.severity, lat, lon);
	}
	
	public EventLocation[] getArea() {
		return this.location;
	}
	
	public String iconPath() {
		return "../../Earthquake/Earthquake.png";
	}
	
	public String getSeverity() {
		return this.severity;
	}
}
