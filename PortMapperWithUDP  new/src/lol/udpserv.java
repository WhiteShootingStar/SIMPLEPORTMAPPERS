package lol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpserv {
	DatagramSocket server;

	public udpserv() throws SocketException {
		initializeServer();
	}

	private void initializeServer() throws SocketException {
		server = new DatagramSocket(12345);
		System.out.println("Server listens on: " + server.getLocalPort());
	}

	void service() throws IOException {
		byte[] buff = new byte[MaxUdp.MAX_DATAGRAM_SIZE];

		DatagramPacket pac = new DatagramPacket(buff, buff.length);

		server.receive(pac);

		new Thread(() -> {
			String lol = new String(pac.getData(), 0, pac.getLength()).trim();
			System.out.println(lol);
			String toSend = "Echo: " + lol;
			byte[] toSendBytes = toSend.getBytes();
			int clientPort = pac.getPort();

			InetAddress clientAddress = pac.getAddress();
			DatagramPacket resp = new DatagramPacket(toSendBytes, toSendBytes.length, clientAddress, clientPort);
			try {
				server.send(resp);
				System.out.println("Im here");
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}).start();
	}

	public void listen() {
		while (true) {
			try {
				service();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new udpserv().listen();
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Could not set up the server");
		}
	}
}
