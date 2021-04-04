package file.transfer;

import java.io.File;

public class ServerMain {
	public static void main(String[] args) {
		try {
			FileTransferServer server = new FileTransferServer(9999, new File("C:\\test"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}