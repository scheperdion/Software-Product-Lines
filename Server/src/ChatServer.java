import crypto.MessageProcessors;
import interfaces.IMessageProcessor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ChatServer {
    public static void main(String[] args) {
        PluginLoader pl = new PluginLoader();
        MessageProcessors processors = MessageProcessors.getInstance();
        IMessageProcessor rot13 = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Rot13.class");
        IMessageProcessor vigenere = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Vigenere.class");
        IMessageProcessor logging = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Logging.class");
//        processors.addMessageProcessor(rot13);
//        processors.addMessageProcessor(vigenere);
        processors.addMessageProcessor(logging);


        Server s = new Server(6554);
        Thread t = new Thread(s);
        t.start();
        s.listen();
    }
}