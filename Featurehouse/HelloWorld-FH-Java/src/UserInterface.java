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


public  class  UserInterface  extends JFrame  implements IUserInterface {
	
    private IMessageSender _messageSender;

	
//    private IMessageColor _messageColor;

    private JTextPane textPane;

	
    private JPanel panel1;

	
    private JTextField HIThereTextField;

	
    private Style textPaneStyle;

	

    
    public UserInterface(){
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());  // Or any other layout manager you prefer

        textPane = new JTextPane();
        HIThereTextField = new JTextField(20);

        // Add components to the panel1 layout
        panel1.add(new JScrollPane(textPane), BorderLayout.CENTER); // Scrollable text area
        panel1.add(HIThereTextField, BorderLayout.SOUTH); // Input field at the bottom
    }

	

    public void mainLoop() {
        setTitle("Chatter Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
        textPaneStyle = textPane.addStyle("textPaneStyle", null);
        HIThereTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = HIThereTextField.getText();
                if (!text.isEmpty()) {
                    _messageSender.send(text);
                    HIThereTextField.setText("");
                }
            }
        });
        setVisible(true);
    }

	

    public void addMessageSender(IMessageSender ms) {
        _messageSender = ms;
    }

	


    @Override
    public void receive(String s) {
        try {
            String m = ColorMessage.stripColorCode(s);
            Color c = ColorMessage.getColor(s);
            StyleConstants.setForeground(textPaneStyle, c);
            StyleConstants.setForeground(textPaneStyle, c);
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), m + "\n",textPaneStyle);
        }
        catch (Exception e){}
    }


}
