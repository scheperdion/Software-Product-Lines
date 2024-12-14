package event; 
public abstract  class  AbstractEvent {
	
	String type;

	
	public abstract String iconPath();

	
	public abstract String toString();

	
	public abstract EventLocation[] getArea();


}
