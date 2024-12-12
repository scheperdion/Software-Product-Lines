package event; 

public  class  ThunderstormEvent  extends AbstractEvent {
	
	private String severity;

	

	public ThunderstormEvent(String text) {
		this.severity = text;
		this.type = "thunderstorm";
	}

	

	public String toString() {
		return "Thunderstorm ALERT" + " " + this.severity;
	}


}
