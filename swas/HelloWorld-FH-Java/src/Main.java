import ui.UI; 

public  class  Main {
	
    public static void main(String[] args) throws Exception {
        System.out.println("Running Client");
        Client c = new Client();
        UI ui = new UI();
		Thread t = new Thread(c);
		t.start();
		
        Thread uiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ui.go(args);
            }
        });
        uiThread.setDaemon(true);
        uiThread.start();

        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                c.updateUI(ui);
            }
        });
        clientThread.start();

        clientThread.join();
        uiThread.join();
        t.join();
    }


}
