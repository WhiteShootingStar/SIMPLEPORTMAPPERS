package UdPPortMapper;

import java.net.SocketException;

public class portmapperMain {

	public static void main(String[] args) throws SocketException {
		portMapper map = new portMapper();
		map.listen();

	}

}
