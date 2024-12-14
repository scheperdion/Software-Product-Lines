package event; 

public  class  NoEvent  extends AbstractEvent {
	
	private String somefield;

	
	
	public NoEvent(String text) {
		this.somefield = text;
		this.type = "noevent";
	}

	
	
	public String toString() {
		return this.type + " " + this.somefield;
	}

	
	
	public EventLocation[] getArea() {
		return null;
	}


}
