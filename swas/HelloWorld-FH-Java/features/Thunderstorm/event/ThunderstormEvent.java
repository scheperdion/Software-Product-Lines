package event;

import java.time.LocalDateTime;

public class ThunderstormEvent extends AbstractEvent {
	private String severity;
	private LocalDateTime datetime;
	private String country;
	private String[] provinces;

	public ThunderstormEvent(String severity, String country, String[] provinces) {
		this.type = "thunderstorm";

		this.severity = severity;
		this.datetime = LocalDateTime.now();
		this.country = country;
		this.provinces = provinces;
	}

	public String toString() {
		return "Thunderstorm ALERT" + " " + this.severity;
	}
	
	public EventLocation[] getArea() {
		return null;
	}
	
	public String iconPath() {
		return "../../Thunderstorm/Thunderstorm.png";
	}
	
	public String getSeverity() {
		return this.severity;
	}
}
