package portTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class portTest {

	public static void main(String[] args) {
		 Socket socket =null;
	        int port =11111;
	        PrintWriter prWriter =null;
	        String IP = "172.21.214.5";
	        BufferedReader buffRead =null;
	        
	        try{
	            socket = new Socket(IP, port);
	            prWriter = new PrintWriter(socket.getOutputStream(),true);
	            buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        }
	        catch(UnknownHostException a){
	            a.printStackTrace();
	            System.exit(-1);
	        }
	        catch(IOException e) {
	        	e.printStackTrace();
	        	System.out.println("Bad");
	        	System.exit(-1);
	        }
	        
	        try{
	            
	       //prWriter.println("REGISTER kek 172.21.214.5 12345"); // либо эта стррочка должа быть закомменчена, либо  следущая. Сначала запустите с этой строкой, потом со следущей
	            
	        prWriter.println("CALL kek  hello 22866+322"); // First Run program with Register, then with Call
	            String line = buffRead.readLine();
	            
	            System.out.println(line );
	            
	           
	        } catch (IOException ex) {
	           ex.printStackTrace();
	           System.exit(-1);;
	        }
	        
	    }

	}


