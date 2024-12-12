package event;

/**
 * TODO description
 */
public class EventDeserializer {

	public EventDeserializer() {
		this.runtimeTypeAdapterFactory.registerSubtype(EarthquakeEvent.class, "earthquake");
	}
}