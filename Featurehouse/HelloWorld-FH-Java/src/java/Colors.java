import interfaces.IMessageProcessor; 

import java.awt.*; 
import java.util.Map; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 


public  class  Colors  implements IMessageProcessor {
	

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

	


    private static final Pattern ANSI_PATTERN = Pattern.compile("\u001B\\[(\\d+)m");

	

    public static String stripColorCode(String input) {
        return ANSI_PATTERN.matcher(input).replaceAll("");  // Remove all ANSI codes
    }

	

    public static Color getColor(String input) {
        Matcher matcher = ANSI_PATTERN.matcher(input);
        Color color = Color.BLACK;  // Default color

        if (matcher.find()) {
            String ansiCode = matcher.group(1);  // Get the number inside the ANSI code (e.g., "31" for red)

            // Map the ANSI code to a java.awt.Color
            switch (ansiCode) {
                case "31":
                    color = Color.RED;
                    break;
                case "32":
                    color = Color.GREEN;
                    break;
                case "33":
                    color = Color.YELLOW;
                    break;
                case "34":
                    color = Color.BLUE;
                    break;
                case "35":
                    color = Color.MAGENTA;  // No "purple" in Java, using magenta
                    break;
                case "36":
                    color = Color.CYAN;
                    break;
                default:
                    color = Color.BLACK;  // Default to black if no valid color code is found
                    break;
            }
        }

        // If no ANSI code is found, return the input message and default color
        return color;
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
