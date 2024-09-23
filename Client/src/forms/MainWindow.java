package forms;

import messages.MessageColor;
import ui.UserInterface;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private final UserInterface ui;
    private JTextPane textPane;
    private JPanel panel1;
    private JTextField HIThereTextField;
    private Style textPaneStyle;

    public MainWindow(UserInterface ui) {
        this.ui = ui;
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());  // Or any other layout manager you prefer

        textPane = new JTextPane();
        HIThereTextField = new JTextField(20);

        // Add components to the panel1 layout
        panel1.add(new JScrollPane(textPane), BorderLayout.CENTER); // Scrollable text area
        panel1.add(HIThereTextField, BorderLayout.SOUTH); // Input field at the bottom
    }

    public void initialize() {
        setTitle("Chatter Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
        textPaneStyle = textPane.addStyle("textPaneStyle", null);
        HIThereTextField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = HIThereTextField.getText();
                if(!text.isEmpty()) {
                    ui.sendMessage(text);
                    HIThereTextField.setText("");
                }

            }
        });
        setVisible(true);
    }

    public void addMessage(String message) {
        try {
            StyleConstants.setForeground(textPaneStyle, MessageColor.getColor(message));
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), MessageColor.stripColor(message) + "\n",textPaneStyle);
        }
        catch (Exception e){}
    }
}