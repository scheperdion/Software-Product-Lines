import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Scanner;

import interfaces.IUserInterface;
import interfaces.IMessageSender;


public class UserInterface extends JFrame implements IUserInterface{

    @Override
    public void receive(String s) {
        String m = ColorMessage.stripColorCode(s);
        Color c = ColorMessage.getColor(s);
        setColor(c);
        original(m);
    }
}
