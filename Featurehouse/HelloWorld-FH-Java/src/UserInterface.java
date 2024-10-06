

import interfaces.IUserInterface; 
import interfaces.IMessageReceiver; 
import interfaces.IMessageSender; 

import java.awt.Color; 
import java.util.HashMap; 
import java.util.Scanner; import javax.swing.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.text.Style; 
import javax.swing.text.StyleConstants; 
import javax.swing.text.StyledDocument; 
import java.awt.*; 


public   class  UserInterface  extends JFrame  implements IUserInterface {
	
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

	

    
     private void  receive__wrappee__CL(String m) {
    	String ANSI_COLOR = getColorMapping().get(this.color) == null ? "" : getColorMapping().get(this.color);
        System.out.println(ANSI_COLOR + m + ANSI_RESET);
    }

	

    @Override
    public void receive(String s) {
        String m = ColorMessage.stripColorCode(s);
        Color c = ColorMessage.getColor(s);
        setColor(c);
        receive__wrappee__CL(m);
    }


}
