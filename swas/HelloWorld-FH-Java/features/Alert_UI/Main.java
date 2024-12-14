import ui.UI;
//import client.*;

public class Main {
	private static UI ui;
    public static void main(String[] args) throws Exception {
    	original(args);
        ui = new UI();
        Thread uiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ui.go(args);
            }
        });
        uiThread.setDaemon(true);
        uiThread.start();
        client.addCallback(ui);
        uiThread.join();
    }
}
