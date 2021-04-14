package Server_Client_Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class B {
	String a = null;
	String b = null;

	public void setClientData(String a, String b) {
		this.a = a;
		this.b = b;
	}
	public String setClientData1(String a) {
		this.a = a;
		return this.a;
	}
	
	public static void main(String[] args) {
		B g = new B();
		g.setClientData(A.getInstance().a, A.getInstance().b);
		System.out.println(g.setClientData1(A.getInstance().a));
		System.out.println(g.a);
		System.out.println(g.b);
	}
}
