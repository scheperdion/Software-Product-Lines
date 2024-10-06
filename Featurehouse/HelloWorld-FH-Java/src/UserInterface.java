//import interfaces.IUserInterface;
////import interfaces.IMessageColor;
//import interfaces.IMessageSender;

import java.util.Scanner; 

public  class  UserInterface  implements IMessageReceiver {
	
    private IMessageSender _messageSender;

	
//    private IMessageColor _messageColor;

    public UserInterface(){
    }

	

    public void mainLoop() {
        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            _messageSender.send(scanner.nextLine());
        }
    }

	

    public void addMessageSender(IMessageSender ms) {
        _messageSender = ms;
    }

	


    @Override
    public void receive(String m) {
        System.out.println(m);
//        System.out.println(_messageColor.getWithColor(m));
    }


}
