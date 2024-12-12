import ui.UI; 
//import client.*;

public  class  Main {
	
    public static void main(String[] args) throws Exception {
        System.out.println("Running Client");
        UI ui = new UI();

		
        Thread uiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ui.go(args);
            }
        });
        uiThread.setDaemon(true);
        uiThread.start();
        uiThread.join();
        

    }


}
