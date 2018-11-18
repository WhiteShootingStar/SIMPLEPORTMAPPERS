package UdPPortMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class portMapper {
	DatagramSocket serv;

	public portMapper() throws SocketException {
		serv = new DatagramSocket(11111);
		System.out.println("Listen on " + serv.getLocalPort());
	}

	public void listen() {

		ArrayList<serv> list = new ArrayList<>();

		while (true) {
			byte[] buff = new byte[MaxUdp.MAX_DATAGRAM_SIZE];

			DatagramPacket pac = new DatagramPacket(buff, buff.length);
			try {
				serv.receive(pac);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			(new SThread(pac, list, serv)).start();

		}
	}
}
