package file.transfer;

import java.io.File;

public class ClientMain {
	public static void main(String[] args) {
		try {
			FileTransferAddress address = new FileTransferAddress("127.0.0.1", 9999);
			File file = new File("c:\\test1");
			FileTransferClient client = new FileTransferClient(address, file);
			client.sendFile(new File("c:\\emoticon.zip"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}