//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainServer {
    public static void main(String[] args) {
        Server s = new Server(6554);
        s.listen();
    }
}