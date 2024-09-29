import java.awt.*;
import java.util.Map;

public class MessageColor implements interfaces.IMessageColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private MessageColor instance;

    @Override
    public String getWithColor(String text) {
        Map<String, String> colorMap = Map.of(
                "RED: ", MessageColor.ANSI_RED,
                "GREEN: ", MessageColor.ANSI_GREEN,
                "YELLOW: ", MessageColor.ANSI_YELLOW,
                "BLUE: ", MessageColor.ANSI_BLUE,
                "PURPLE: ", MessageColor.ANSI_PURPLE,
                "CYAN: ", MessageColor.ANSI_CYAN
        );

        for (Map.Entry<String, String> entry : colorMap.entrySet()) {
            if (text.startsWith(entry.getKey())) {
                return entry.getValue() + stripColor(text, entry.getKey()) + MessageColor.ANSI_RESET;
            }
        }

        return MessageColor.ANSI_RESET + text;
    }

    @Override
    public Color getColor(String text) {
        Map<String, Color> colorMap = Map.of(
                "RED: ", Color.RED,
                "GREEN: ", Color.GREEN,
                "YELLOW: ", Color.YELLOW,
                "BLUE: ", Color.BLUE,
                "PINK: ", Color.PINK,
                "CYAN: ", Color.CYAN
        );
        for (Map.Entry<String, Color> entry : colorMap.entrySet()) {
            if (text.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return Color.GREEN;
    }

    @Override
    public String stripColor(String text) {
        Map<String, Color> colorMap = Map.of(
                "RED: ", Color.RED,
                "GREEN: ", Color.GREEN,
                "YELLOW: ", Color.YELLOW,
                "BLUE: ", Color.BLUE,
                "PINK: ", Color.PINK,
                "CYAN: ", Color.CYAN
        );
        for (Map.Entry<String, Color> entry : colorMap.entrySet()) {
            if (text.startsWith(entry.getKey())) {
                return stripColor(text, entry.getKey());
            }
        }
        return text;
    }

    @Override
    public String stripColor(String text, String colorCode) {
        return text.replaceFirst(colorCode, "");
    }
}
