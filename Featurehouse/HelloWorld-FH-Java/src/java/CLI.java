import interfaces.IUserInterface; 
//import interfaces.IMessageColor;
import interfaces.IMessageSender; 

import java.util.Scanner; 

public  class  CLI  implements IUserInterface {
	
    private IMessageSender _messageSender;

	
//    private IMessageColor _messageColor;

    public CLI(){
    }

	


    @Override
    public void mainLoop() {
        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            _messageSender.send(scanner.nextLine());
        }
    }

	

    @Override
    public void addMessageSender(IMessageSender ms) {
        _messageSender = ms;
    }

	

//    @Override
//    public void addMessageColor(IMessageColor mc) {
//        _messageColor = mc;
//    }

    @Override
    public void receive(String m) {
        System.out.println(m);
//        System.out.println(_messageColor.getWithColor(m));
    }


}
