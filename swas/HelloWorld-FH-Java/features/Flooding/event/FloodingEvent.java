package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FloodingEvent extends AbstractEvent {
	private String severity;
	private String country;
	private String[] provinces;

	public FloodingEvent(String severity, String country, String[] provinces) {
		this.type = "flooding";

		this.severity = severity;
		this.country = country;
		this.provinces = provinces;
	}

	public String toString() {
		return "Flooding ALERT" + " " + this.severity + " severity in " + Arrays.toString(this.provinces);
	}

	public EventLocation getLocation() {
		return null;
	}
	
	public String getSeverity() {
		return this.severity;
	}
}
