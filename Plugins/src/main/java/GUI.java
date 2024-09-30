import interfaces.IMessageColor;
import interfaces.IUserInterface;
import interfaces.IMessageSender;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI extends JFrame implements IUserInterface {
    private IMessageSender _messageSender;
    private IMessageColor _messageColor;

    private JTextPane textPane;
    private JPanel panel1;
    private JTextField HIThereTextField;
    private Style textPaneStyle;

    public GUI() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());  // Or any other layout manager you prefer

        textPane = new JTextPane();
        HIThereTextField = new JTextField(20);

        // Add components to the panel1 layout
        panel1.add(new JScrollPane(textPane), BorderLayout.CENTER); // Scrollable text area
        panel1.add(HIThereTextField, BorderLayout.SOUTH); // Input field at the bottom
    }

    @Override
    public void mainLoop() {
        setTitle("Chatter Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
        textPaneStyle = textPane.addStyle("textPaneStyle", null);
        HIThereTextField.addActionListener(e -> {
            String text = HIThereTextField.getText();
            if (!text.isEmpty()) {
                _messageSender.send(text);
                HIThereTextField.setText("");
            }
        });
        setVisible(true);
    }

    @Override
    public void addMessageSender(IMessageSender ms) {
        _messageSender = ms;
    }

    @Override
    public void addMessageColor(IMessageColor mc) {
        _messageColor = mc;
    }

    @Override
    public void receive(String m) {
        try {
            StyleConstants.setForeground(textPaneStyle, _messageColor.getColor(m));
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), _messageColor.stripColor(m) + "\n",textPaneStyle);
        }
        catch (Exception e){}
    }
}