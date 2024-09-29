package messages;

import interfaces.IMessageColor;

import java.awt.*;

public class MessageColorDefault implements IMessageColor {

    @Override
    public String getWithColor(String text) {
        return text;
    }

    @Override
    public Color getColor(String text) {
        return Color.GREEN;
    }

    @Override
    public String stripColor(String text) {
        return text;
    }

    @Override
    public String stripColor(String text, String colorCode) {
        return text;
    }
}
