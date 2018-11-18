package kek1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) throws IOException {
		InetAddress adress = InetAddress.getByName("192.168.10.24");
		int port = 11111;
		//String message = "REGISTER kek 192.168.10.24 12345"; byte[] arr = message.getBytes();
		 String mes1 = "CALL kek chlen232343242"; byte[] arr = mes1.getBytes();
		
		
		DatagramPacket pac = new DatagramPacket(arr, arr.length, adress, port);
		DatagramSocket socket = new DatagramSocket();
		socket.send(pac);

		byte[] buff = new byte[MaxUdp.MAX_DATAGRAM_SIZE];

		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		socket.receive(packet);
		String temp = new String(packet.getData(), 0, packet.getLength()).trim();
		System.out.println(temp);

		socket.close();

	}

}
