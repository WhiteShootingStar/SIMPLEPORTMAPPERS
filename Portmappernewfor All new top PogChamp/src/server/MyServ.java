package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServ {
	public void listen() {

		ServerSocket serv = null;
		Socket soc = null;

		try {
			serv = new ServerSocket(12345);
		} catch (IOException e) {
			e.printStackTrace();

			

			System.exit(-1);
		}

		System.out.println("Listening on " + serv.getLocalPort());

		while (true) {
			try {
				soc = serv.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			
			(new ServerThread(soc)).start();
		}
		
	
}}