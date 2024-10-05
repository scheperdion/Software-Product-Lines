/**
 * TODO description
 */

public   class  Test {
	

	 private static void  main__wrappee__Chatter  (String[] args) {
		System.out.println("Main");
	}

	

	 private static void  main__wrappee__Client  (String[] args) {
		main__wrappee__Chatter(args);
		System.out.println("Client");
	}

	

	public static void main(String[] args) {
		main__wrappee__Client(args);
		Encryption r = new Encryption();
		r.test(args);
		System.out.println("CLI");
	}


}
