
public class Main {
	public static void main(String[] args) throws Exception{
		System.out.println("Running Server");
		Server s = new Server();
		Thread t = new Thread(s);
		t.start();
		s.startDataMock();
		t.join();
	}
}