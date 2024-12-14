import ui.UI; 
import client.*; 
//import client.*;

public   class  Main {
	
	private static Client client;

	
     private static void  main__wrappee__Client(String[] args) throws Exception {
        System.out.println("Running Client");
        client = new Client();
		Thread clientThread = new Thread(client);
		clientThread.start();
    }

	
    public static void main(String[] args) throws Exception {
    	main__wrappee__Client(args);
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

	
	private static UI ui;


}
