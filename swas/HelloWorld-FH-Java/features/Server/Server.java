import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import event.*;

import java.io.*;


public class Server implements Runnable {
	private List<Socket> sockets;
	
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
		EventDeserializer ed = new EventDeserializer();
		Gson gson = ed.getGSON();
		while(true) {
			// send data / events to clients at random intervals
			try {
			    Thread.sleep(5000);
	            for(Socket skt : this.sockets) {
	            	try {
		            	PrintWriter output = new PrintWriter(skt.getOutputStream(), true);
		            	
		            	EventLocation location = new EventLocation(52.371807d, 4.896029d, 20d);
		            	
		            	EarthquakeEvent event = new EarthquakeEvent("high", "netherlands", new String[]{"Noord_Holland", "Utrecht"}, new EventLocation[]{location});
		            	output.println(gson.toJson(event));
		            	System.out.println(gson.toJson(event));
	            	} catch(IOException e) {
	            		e.printStackTrace();
	            	}
	            }
			} catch (InterruptedException e) {
				
			  Thread.currentThread().interrupt();
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
