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


public class MessagesWatcher {
	private String filename;
	private WatchService watchService;
	private Path path;
	
	public MessagesWatcher(String filename) throws IOException {
		path = Paths.get(filename);
        watchService = FileSystems.getDefault().newWatchService();
        path.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
	}
	
	public boolean waitOnFileChange() {
		boolean result = false;
		try {
	        while (true) {
	            WatchKey key = watchService.take();
	            for (WatchEvent<?> event : key.pollEvents()) {
	            	Path changedPath = (Path) event.context();
	                if (changedPath.equals(path.getFileName())) {
	                	result = true;
	                }
	            }

	            // Reset the key to continue monitoring
	            boolean valid = key.reset();
	            if (!valid) {
	                System.out.println("Watch service is no longer valid. Exiting...");
	                return false;
	            }
	            if (result) {
	            	return result;
	            }
	        }
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
}
