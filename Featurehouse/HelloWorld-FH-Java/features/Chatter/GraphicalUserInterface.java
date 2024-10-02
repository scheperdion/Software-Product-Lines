//import forms.MainWindow;
//import messages.Message;
//import ui.UserInterface;
//
//public class GraphicalUserInterface implements UserInterface {
//    private final Client client;
//    private final MainWindow window;
//
//    public GraphicalUserInterface(Client c) {
//        this.client = c;
//        this.client.addObserver(this);
//        this.window = new MainWindow(this);
//    }
//
//    @Override
//    public void mainLoop() {
//        this.window.initialize();
//    }
//
//    @Override
//    public void sendMessage(String message) {
//        client.send(new Message(message, null));
//    }
//
//    @Override
//    public void notify(Message message) {
//        this.window.addMessage(message.getString());
//    }
//}