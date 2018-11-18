package UdPPortMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class SThread extends Thread {
	DatagramPacket cl;
	ArrayList<serv> list;
	DatagramSocket ssocket;

	public SThread(DatagramPacket soc, ArrayList<serv> a, DatagramSocket kek) {
		cl = soc;
		list = a;
		ssocket = kek;
	}

	public void run() {
		try {
			// BufferedReader read = new BufferedReader(new
			// InputStreamReader(cl.getInputStream()));
			// PrintWriter write = new PrintWriter(cl.getOutputStream(), true);
			String received = (new String(cl.getData())).trim();

			String[] arr = received.split(" ");

			if (arr[0].equals("REGISTER")) {

				list.add(new serv(arr[1], arr[2], Integer.parseInt(arr[3])));
				byte[] byteArr = new String(
						"Server with the name  " + arr[1] + " IP:" + arr[2] + " and port number " + " has been created")
								.getBytes();

				System.out.println("Server with the name  " + arr[1] + " IP:" + arr[2] + " and port number "
						+ " has been created");

				try {
					DatagramPacket resp = new DatagramPacket(byteArr, byteArr.length, cl.getAddress(), cl.getPort());
					ssocket.send(resp);

				} catch (IOException e) {
					e.printStackTrace();// TODO: handle exception
				}

			}
			if (arr[0].equals("CALL")) {
				String lel = "";
				for (int i = 2; i < arr.length; i++) {
					lel += arr[i] + " ";
				}
				System.out.println(lel);

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).name.equals(arr[1])) {
						System.out.println(delegate(lel, InetAddress.getByName(list.get(i).IP), list.get(i).port));

					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	String delegate(String text, InetAddress ip, int p) {

		byte[] toSend = text.getBytes();
		InetAddress a = ip;
		int port = p;
		
		try {
			 DatagramSocket socSend = new DatagramSocket();
			DatagramPacket toSendInfo = new DatagramPacket(toSend, toSend.length, a, port);

			socSend.send(toSendInfo);
			System.out.println("Tried to send");

			byte[] returnbuff = new byte[MaxUdp.MAX_DATAGRAM_SIZE];
			DatagramPacket pac = new DatagramPacket(returnbuff, returnbuff.length);
			System.out.println("Sended");
			
			socSend.receive(pac);
			String test = new String(pac.getData(), 0, pac.getLength()).trim();
			System.out.println("This will be send:  " + test);
			byte[] res = test.getBytes();
			DatagramPacket resPac = new DatagramPacket(res, res.length, cl.getAddress(), cl.getPort());
			socSend.send(resPac);

			

			// System.out.println(temp + " Were sent, trying");
			return "1";
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Pizdariki");
			return null;
		}
	}
}
