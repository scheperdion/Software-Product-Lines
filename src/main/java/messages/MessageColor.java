package messages;

import java.util.Map;

public class MessageColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static String getWithColor(String text) {
        Map<String, String> colorMap = Map.of(
                "RED: ", ANSI_RED,
                "GREEN: ", ANSI_GREEN,
                "YELLOW: ", ANSI_YELLOW,
                "BLUE: ", ANSI_BLUE,
                "PURPLE: ", ANSI_PURPLE,
                "CYAN: ", ANSI_CYAN
        );

        for (Map.Entry<String, String> entry : colorMap.entrySet()) {
            if (text.startsWith(entry.getKey())) {
                return entry.getValue() + text.replaceFirst(entry.getKey(), "") + ANSI_RESET;
            }
        }

        return ANSI_RESET + text;
    }
}
