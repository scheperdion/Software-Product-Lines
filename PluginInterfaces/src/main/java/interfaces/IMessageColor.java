package interfaces;

import java.awt.*;

public interface IMessageColor {
    String getWithColor(String text);

    Color getColor(String text);

    String stripColor(String text);

    String stripColor(String text, String colorCode);
}
