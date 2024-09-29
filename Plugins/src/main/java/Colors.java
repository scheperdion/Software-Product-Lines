import interfaces.IMessageProcessor;

import java.awt.*;
import java.util.Map;

public class Colors implements IMessageProcessor {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    @Override
    public String processIncomingMessage(String message) {
        return getTextWithColor(message);
    }

    @Override
    public String processOutgoingMessage(String message) {
        return message;
    }

    private String getTextWithColor(String message) {
        Map<String, String> colorMap = Map.of(
                "RED: ", Colors.ANSI_RED,
                "GREEN: ", Colors.ANSI_GREEN,
                "YELLOW: ", Colors.ANSI_YELLOW,
                "BLUE: ", Colors.ANSI_BLUE,
                "PURPLE: ", Colors.ANSI_PURPLE,
                "CYAN: ", Colors.ANSI_CYAN
        );
        for (Map.Entry<String, String> entry : colorMap.entrySet()) {
            if (message.startsWith(entry.getKey())) {
                return entry.getValue() + stripColor(message, entry.getKey()) + Colors.ANSI_RESET;
            }
        }

        return Colors.ANSI_RESET + message;
    }

    private String stripColor(String text) {
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

    private String stripColor(String text, String colorCode) {
        return text.replaceFirst(colorCode, "");
    }


    @Override
    public String processMessageOnServer(String message) {
        return message;
    }
}
