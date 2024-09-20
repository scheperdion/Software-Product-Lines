package forms;

import ui.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private final UserInterface ui;
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextField HIThereTextField;

    public MainWindow(UserInterface ui) {
        this.ui = ui;
    }

    public void initialize() {
        setTitle("Chatter Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
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
        this.textArea1.append(message + "\n");
    }
}
