import ui.UI;
import client.*;

public class Main {
	private static Client client;
    public static void main(String[] args) throws Exception {
        System.out.println("Running Client");
        client = new Client();
		Thread clientThread = new Thread(client);
		clientThread.start();
    }
}
