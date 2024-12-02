
public class NoEvent extends AbstractEvent {
	private String somefield;
	
	public NoEvent(String text) {
		this.somefield = text;
		this.type = "noevent";
	}
}
