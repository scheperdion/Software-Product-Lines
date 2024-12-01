import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory; 

public   class   EventDeserializer {
	private RuntimeTypeAdapterFactory<AbstractEvent> runtimeTypeAdapterFactory;

	public EventDeserializer() {
		this.runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(AbstractEvent.class, "type");
		this.runtimeTypeAdapterFactory.registerSubtype(NoEvent.class, "noevent");
	}

	public Gson getGSON() {
		return new GsonBuilder()
		        .registerTypeAdapterFactory(this.runtimeTypeAdapterFactory).create();
	}
}
