package ui;

import messages.Message;

public interface MessageObserver {
    void notify(Message message);
}