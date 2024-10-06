import interfaces.IUserInterface; 

public  class  ChatClient {
	
    public static void main(String[] args) throws InterruptedException {
//        PluginLoader pl = new PluginLoader();

//        MessageProcessors processors = MessageProcessors.getInstance();
//        IMessageProcessor rot13 = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Rot13.class");
//        IMessageProcessor vigenere = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Vigenere.class");
//        IMessageProcessor colors = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Colors.class");
//        IMessageProcessor authentication = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Authentication.class");
//        IMessageProcessor logging = pl.loadMessageProcessor(System.getProperty("user.dir") + "/Plugins/target/classes/Logging.class");
//        processors.addMessageProcessor(authentication);
//        processors.addMessageProcessor(logging);
//        processors.addMessageProcessor(colors);
//        processors.addMessageProcessor(vigenere);
//        processors.addMessageProcessor(rot13);


        Client c = new Client(0);
        IUserInterface ui = new UserInterface();
        c.addObserver(ui);
//        ui.addMessageColor(color);
        ui.addMessageSender(c);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        ui.mainLoop();

    }


}
