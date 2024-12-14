import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import event.EventDeserializer; 


public class Server implements Runnable {
	private List<Socket> sockets;
	private final String messagesfilename = "./server_messages.json";
	
	public Server() {
		this.sockets = new ArrayList<Socket>();
	}

	@Override
	public void run() {
		ServerSocket srvr;
		try {
			srvr = new ServerSocket(6665);
            while(true) {
                Socket skt = srvr.accept();
                System.out.println("Got a new connection!");
                sockets.add(skt);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startDataMock() {
		while(true) {
			try {
				watchFile();
			} catch(IOException e) { 
				
			}
	
		}
	}
	
	public void watchFile() throws IOException {
		MessagesWatcher messagesWatcher = new MessagesWatcher(this.messagesfilename);
		MessagesReader mr = new MessagesReader();
		//MessagesReader reader = new MessagesReader();
		if(!messagesWatcher.waitOnFileChange()) {
			// TODO: something went wrong, we have to fix it...
		} else {
			System.out.println("Messages updated!");
			List<String> messages = MessagesReader.getFileContents(messagesfilename);
            for(Socket skt : this.sockets) {
            	try {
	            	PrintWriter output = new PrintWriter(skt.getOutputStream(), true);
	            	for(String message : messages) {
	            		output.println(message);
	            	}
            	} catch(IOException e) {
            		e.printStackTrace();
            	}
            }
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("Running Server");
		Server s = new Server();
		Thread t = new Thread(s);
		t.start();
		s.startDataMock();
		t.join();
	}
}
