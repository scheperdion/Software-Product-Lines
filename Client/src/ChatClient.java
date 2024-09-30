import interfaces.IMessageColor;
import messages.MessageColorPlugin;
//import ui.UserInterface;

import interfaces.IUserInterface;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        PluginLoader pl = new PluginLoader();
        IMessageColor color = pl.load(System.getProperty("user.dir") + "/Plugins/target/classes/MessageColor.class");
        MessageColorPlugin.getInstance().setMessageColor(color);

        Client c = new Client(0);
        IUserInterface ui = (IUserInterface) pl.loadPlugin(System.getProperty("user.dir") + "/Plugins/target/classes/GUI.class");
        c.addObserver(ui);
        ui.addMessageColor(color);
        ui.addMessageSender(c);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        c.authenticate();
        ui.mainLoop();

    }
}