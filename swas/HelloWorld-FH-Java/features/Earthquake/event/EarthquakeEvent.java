package event;

import java.time.LocalDateTime;

public class EarthquakeEvent extends AbstractEvent {
	private String severity;
	private String country;
	private String[] provinces;
	private EventLocation location;

	public EarthquakeEvent(String severity, String country, String[] provinces, EventLocation location) {
		this.type = "earthquake";

		this.severity = severity;
		this.country = country;
		this.provinces = provinces;
		this.location = location;
	}

	public String toString() {
		return "Earthquake ALERT" + " " + this.severity + " severity at " + location.latitude + "lat and " + location.longitude + "long";
	}
	
	public EventLocation getLocation() {
		return this.location;
	}
}
