import crypto.MessageProcessors;
import interfaces.IMessageColor;
import interfaces.IMessageProcessor;
import messages.MessageColorPlugin;
import ui.UserInterface;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        PluginLoader pl = new PluginLoader();
        IMessageColor color = pl.load(System.getProperty("user.dir") + "/Plugins/target/classes/MessageColor.class");
        MessageColorPlugin.getInstance().setMessageColor(color);

        MessageProcessors processors = MessageProcessors.getInstance();
        IMessageProcessor rot13 = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Rot13.class");
        IMessageProcessor vigenere = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Vigenere.class");
        processors.addMessageProcessor(rot13);
        //processors.addMessageProcessor(vigenere);

        Client c = new Client(0);
        UserInterface ui = new GraphicalUserInterface(c); //new CommandLineInterface(c);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        c.authenticate();
        ui.mainLoop();

    }
}