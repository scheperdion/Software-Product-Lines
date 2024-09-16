//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Server.PreprocessorFactory;

public class ChatServer {
    public static void main(String[] args) {

        Server s = new Server(6554, (new PreprocessorFactory()).createPreprocessorChain());
        Thread t = new Thread(s);
        t.start();
        s.listen();
    }
}
