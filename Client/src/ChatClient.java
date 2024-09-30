import interfaces.IMessageColor;
import messages.MessageColorPlugin;
//import ui.UserInterface;

import interfaces.IUserInterface;
import crypto.MessageProcessors;
import interfaces.IMessageProcessor;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        PluginLoader pl = new PluginLoader();

        MessageProcessors processors = MessageProcessors.getInstance();
        IMessageProcessor rot13 = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Rot13.class");
        IMessageProcessor vigenere = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Vigenere.class");
        IMessageProcessor colors = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Colors.class");
        IMessageProcessor authentication = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Authentication.class");
        IMessageProcessor logging = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Logging.class");
        processors.addMessageProcessor(authentication);
        processors.addMessageProcessor(logging);
//        processors.addMessageProcessor(vigenere);
        processors.addMessageProcessor(rot13);
        processors.addMessageProcessor(colors);


        Client c = new Client(0);
        UserInterface ui = new CommandLineInterface(c); //new CommandLineInterface(c);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        ui.mainLoop();

    }
}