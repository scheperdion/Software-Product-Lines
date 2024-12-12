package event; 

public  class  TsunamiEvent  extends AbstractEvent {
	
	private String severity;

	

	public TsunamiEvent(String text) {
		this.severity = text;
		this.type = "tsunami";
	}

	

	public String toString() {
		return "Tsunami ALERT" + " " + this.severity;
	}


}
