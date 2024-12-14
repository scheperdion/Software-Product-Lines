package event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory; 

import java.time.LocalDateTime;

public   class   EventDeserializer {
	private RuntimeTypeAdapterFactory<AbstractEvent> runtimeTypeAdapterFactory;

	public EventDeserializer() {
		this.runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(AbstractEvent.class, "type");
		this.runtimeTypeAdapterFactory.registerSubtype(NoEvent.class, "noevent");
	}

	public Gson getGSON() {
		return new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
		        .registerTypeAdapterFactory(this.runtimeTypeAdapterFactory).create();
	}
}
