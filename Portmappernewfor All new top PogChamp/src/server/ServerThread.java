package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket s;

	public ServerThread(Socket client) {
		
		s = client;
	}
	public void run() {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter write = new PrintWriter(s.getOutputStream(),true);
		
			
			String line = read.readLine();
			System.out.println(line);
			String[] arr = line.split(" ");
			int a =0;
			for(int i = 0;i<arr.length; i++) {
				a+= Integer.parseInt(arr[i]);
			}
			write.println(a);
			System.out.println(a);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
	
}
