package client;

import event.AbstractEvent;

public interface IClientCallback {
	void receivedEvent(AbstractEvent event);
}
