//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Server.PreprocessorChain;
import Server.AuthenticateProcess;

public class ChatServer {
    public static void main(String[] args) {
        PreprocessorChain chain = new PreprocessorChain();
        chain.addPreprocessor(new AuthenticateProcess());
        Server s = new Server(6554, chain);
        Thread t = new Thread(s);
        t.start();
        s.listen();
    }
}
