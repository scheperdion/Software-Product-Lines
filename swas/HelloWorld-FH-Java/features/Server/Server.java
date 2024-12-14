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
		watchFile();
	}
	
	public void watchFile() {
		try {
			Path path = Paths.get("./server_messages.json");
			System.out.println(path.toAbsolutePath().normalize());
	        WatchService watchService = FileSystems.getDefault().newWatchService();

	        path.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
	        
	        while (true) {
	            WatchKey key = watchService.take();
	            for (WatchEvent<?> event : key.pollEvents()) {
	            	Path changedPath = (Path) event.context();
	                if (changedPath.equals(path.getFileName())) {
	                	System.out.println("File updated!");
	    	            for(Socket skt : this.sockets) {
	    	            	try {
	    		            	PrintWriter output = new PrintWriter(skt.getOutputStream(), true);
	    		            	String filePath = "./server_messages.json";
	    		            	BufferedReader br = new BufferedReader(new FileReader(filePath));
	    		            	try {
	    		                    String line;
	    		                    while ((line = br.readLine()) != null) {
	    	    		            	output.println(line);
	    		                    }
	    		                } catch (IOException e) {
	    		                    System.err.println("An error occurred while reading the file: " + e.getMessage());
	    		                }
	    	            	} catch(IOException e) {
	    	            		e.printStackTrace();
	    	            	}
	    	            }
	                }
	            }

	            // Reset the key to continue monitoring
	            boolean valid = key.reset();
	            if (!valid) {
	                System.out.println("Watch service is no longer valid. Exiting...");
	                break;
	            }
	        }
		} catch(IOException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
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
