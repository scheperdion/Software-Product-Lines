

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorMessage{

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private static final Pattern ANSI_PATTERN = Pattern.compile("\u001B\\[(\\d+)m");

 
    public static HashMap<String, ColorTuple> getColorMapping() {
    	HashMap<String, ColorTuple> colorMap = new HashMap<String, ColorTuple>();
        colorMap.put("RED: ", new ColorTuple(Color.RED, "\u001B[31m"));
        colorMap.put("GREEN: ", new ColorTuple(Color.GREEN, "\u001B[32m"));
        colorMap.put("YELLOW: ", new ColorTuple(Color.YELLOW, "\u001B[33m"));
        colorMap.put("BLUE: ", new ColorTuple(Color.BLUE, "\u001B[34m"));
        colorMap.put("PINK: ", new ColorTuple(Color.PINK, "\u001B[35m"));
        colorMap.put("CYAN: ", new ColorTuple(Color.CYAN, "\u001B[36m"));
        return colorMap;
    }
    
    public static String stripColorCode(String input) {
        return ANSI_PATTERN.matcher(input).replaceAll("");  // Remove all ANSI codes
    }

    public static Color getColor(String input) {
        Matcher matcher = ANSI_PATTERN.matcher(input);
        Color color = Color.BLACK;  // Default color

        if (matcher.find()) {
        	int ansiCode = -1;
        	try {
        		ansiCode = Integer.parseInt(matcher.group(1));  
        	} catch(NumberFormatException e)
        	{
        	}
            
            
            switch (ansiCode) {
                case 31:
                    color = Color.RED;
                    break;
                case 32:
                    color = Color.GREEN;
                    break;
                case 33:
                    color = Color.YELLOW;
                    break;
                case 34:
                    color = Color.BLUE;
                    break;
                case 35:
                    color = Color.MAGENTA;
                    break;
                case 36:
                    color = Color.CYAN;
                    break;
                default:
                    color = Color.BLACK; 
                    break;
            }
        }

        return color;
    }


    private static String formatColoredMessage(String message, Color color, String colorKey) {
        String strippedMessage = stripColor(message, colorKey);

        return color + strippedMessage + ANSI_RESET;
    }
    
    
    public static String getTextWithColor(String message) {
    	HashMap<String, ColorTuple> colorMap = getColorMapping();

        if (message == null || message.isEmpty()) {
            return ANSI_RESET;
        }

        for (Map.Entry<String, ColorTuple> entry : colorMap.entrySet()) {
            String colorKey = entry.getKey();
            if (message.startsWith(colorKey)) {
                String ansiColor = entry.getValue().getAnsiCode();
                if (ansiColor != null) {
                    return ansiColor + stripColor(message, colorKey) + ANSI_RESET;
                }
            }
        }


        return ANSI_RESET + message;
    }

    public static String stripColor(String text) {
    	HashMap<String, ColorTuple> colorMap = getColorMapping();
        for (HashMap.Entry<String, ColorTuple> entry : colorMap.entrySet()) {
            if (text.startsWith(entry.getKey())) {
                return stripColor(text, entry.getKey());
            }
        }
        return text;
    }

    public static String stripColor(String text, String colorCode) {
        return text.replaceFirst(colorCode, "");
    }

}
