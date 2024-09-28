package messages;

import interfaces.IMessageColor;

import java.awt.*;

public class MessageColorPlugin implements IMessageColor {
    private static MessageColorPlugin instance;

    private IMessageColor messageColor;
    private MessageColorPlugin() {}

    public static MessageColorPlugin getInstance() {
        if(instance == null) {
            instance = new MessageColorPlugin();
            instance.setMessageColor(new MessageColorDefault());
        }
        return instance;
    }

    public void setMessageColor(IMessageColor messageColor) {
        this.messageColor = messageColor;
    }

    @Override
    public String getWithColor(String text) {
        return messageColor.getWithColor(text);
    }

    @Override
    public Color getColor(String text) {
        return messageColor.getColor(text);
    }

    @Override
    public String stripColor(String text) {
        return messageColor.stripColor(text);
    }

    @Override
    public String stripColor(String text, String colorCode) {
        return messageColor.stripColor(text, colorCode);
    }
}
