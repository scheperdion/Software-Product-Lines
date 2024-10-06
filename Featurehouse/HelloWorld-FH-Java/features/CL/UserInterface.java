
import interfaces.IUserInterface;
import interfaces.IMessageReceiver;
import interfaces.IMessageSender;

import java.awt.Color;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface implements IUserInterface{
    private IMessageSender _messageSender;
    private Color color;
    private static final String ANSI_RESET = "\u001B[0m";

    public UserInterface(){
    	this.color = Color.GREEN;
    }

    public void mainLoop() {
        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            _messageSender.send(scanner.nextLine());
        }
    }

    public void addMessageSender(IMessageSender ms) {
        _messageSender = ms;
    }

    @Override
    public void setColor(Color color) {
    	this.color = color;
    }
    
    public static HashMap<Color, String> getColorMapping() {
    	HashMap<Color, String> colorMap = new HashMap<Color, String> ();
        colorMap.put(Color.RED, "\u001B[31m");
        colorMap.put(Color.GREEN, "\u001B[32m");
        colorMap.put(Color.YELLOW, "\u001B[33m");
        colorMap.put(Color.BLUE, "\u001B[34m");
        colorMap.put(Color.PINK, "\u001B[35m");
        colorMap.put(Color.CYAN, "\u001B[36m");
        return colorMap;
    }

    @Override
    public void receive(String m) {
    	String ANSI_COLOR = getColorMapping().get(this.color) == null ? "" : getColorMapping().get(this.color);
        System.out.println(ANSI_COLOR + m + ANSI_RESET);
    }
}
