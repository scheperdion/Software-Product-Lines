package event;

/**
 * TODO description
 */
public class EventDeserializer {

	public EventDeserializer() {
		this.runtimeTypeAdapterFactory.registerSubtype(FloodingEvent.class, "flooding");
	}
}