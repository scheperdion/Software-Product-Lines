//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ChatServer {
    public static void main(String[] args) {
        AuthenticationServer s = new AuthenticationServer(6554);
        Thread t = new Thread(s);
        t.start();
        s.listen();
    }
}