package messages;

import config.Config;

public class MessageColorProcessor extends MessageProcessor {

    @Override
    protected String formatMessage(Message message) {
        Config config = Config.getConfig();
        boolean colorEnabled = Boolean.parseBoolean(config.getProperty("COLOR_ENABLED"));

        if (colorEnabled) {
            return MessageColor.getWithColor(message.getString());
        } else {
            return message.getString();
        }
    }

    @Override
    protected void displayMessage(String message) {
        System.out.println(message);
    }
}