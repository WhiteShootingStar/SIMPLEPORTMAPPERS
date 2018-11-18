package PortMapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class portMapper {

	public void listen() {
		ServerSocket soc =null;
		Socket client =null;
		ArrayList<serv> list = new ArrayList<>();
		try {
			
			soc = new ServerSocket(11111);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("Listening on "+ soc.getLocalPort());
		
		while(true) {
			try {
				client=soc.accept();
			}
			catch(IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			(new SThread(client,list)).start();
		}
	}
	
}
