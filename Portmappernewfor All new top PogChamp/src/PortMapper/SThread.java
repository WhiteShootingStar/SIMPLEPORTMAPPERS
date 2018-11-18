package PortMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SThread extends Thread {
	Socket cl;
	ArrayList<serv> list;

	public SThread(Socket soc, ArrayList<serv> a) {
		cl = soc;
		list = a;
	}

	public void run() {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			PrintWriter write = new PrintWriter(cl.getOutputStream(), true);

			String line = read.readLine();
			String[] arr = line.split(" ");

			if (arr[0].equals("REGISTER")) {
				list.add(new serv(arr[1], arr[2], Integer.parseInt(arr[3])));
				write.println("Server with the name  " + arr[1] + " IP:" + arr[2] + " and port number "
						+ " has been created");
				System.out.println("Server with the name  " + arr[1] + " IP:" + arr[2] + " and port number "
						+ " has been created");
				
			}
			if (arr[0].equals("CALL")) {
				String lel ="";
				for(int i=2;i<arr.length;i++) {
					lel+=arr[i]+ " ";
				}
				System.out.println(lel);
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).name.equals(arr[1])) {
						write.println(delegate(lel, list.get(i).IP,
								list.get(i).port));

					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cl.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	String delegate(String text, String ip, int p) {
		Socket socket = null;
		PrintWriter out1 = null;
		BufferedReader in = null;
		String addres = ip;
		int port = p;

		try {
			socket = new Socket(addres, port);
			out1 = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		try {
			
			out1.println(text);
			// System.out.println(temp + " Were sent, trying");
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Pizdariki");
			return null;
		}
	}
}
