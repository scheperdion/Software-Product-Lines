package ui; 

public  interface  UserInterface  extends ui.MessageObserver {
	
    void mainLoop();

	
    void sendMessage(String message);


}
