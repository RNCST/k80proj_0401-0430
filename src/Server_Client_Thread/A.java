package Server_Client_Thread;

public class A implements Runnable {
	String a = null;
	String b = null;

	private static A c = new A();

	public static synchronized A getInstance() {
		if (c == null) {
			c = new A();
		}
		return c;
	}

	public static void main(String[] args) {
		c.run();
		System.out.println(c.a);
		System.out.println(c.b);
	}

	@Override
	public void run() {
		a = "aa";
		b = "bb";
	}

}
