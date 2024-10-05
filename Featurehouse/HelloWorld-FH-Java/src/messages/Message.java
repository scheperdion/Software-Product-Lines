package messages; 

import network.ChatSocket; 

public  class  Message {
	
    private String str;

	
    private final ChatSocket skt;

	

    public Message(String str, ChatSocket skt) {
        this.str = str;
        this.skt = skt;
    }

	

    public String getString() {
        return str;
    }

	

    public void setString(String s) { this.str = s; }

	;

	

    public ChatSocket getSocket() {
        return skt;
    }


}
