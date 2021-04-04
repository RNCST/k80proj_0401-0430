package file.transfer;
//자동 정렬 Ctrl+Shift+F
import java.io.IOException;

public interface FileTransferServerListener {
	public void clientConnection(FileTransferClient client);
	public void connectionError(IOException e);
	public void connectionClose();
}
