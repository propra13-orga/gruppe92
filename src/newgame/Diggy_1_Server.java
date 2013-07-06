package newgame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;



public class Diggy_1_Server {

	public static boolean test1 = false;
	
	public static void main(String[] args) throws Exception
	{
		Diggy_1_Server Server = new Diggy_1_Server();
		Server.run();
		test1 = true;
	}
	
	public void run() throws Exception
	{
		ServerSocket Diggysocket = new ServerSocket(333);
		Socket Sock = Diggysocket.accept();
		InputStreamReader ISR = new InputStreamReader(Sock.getInputStream());
		BufferedReader BR = new BufferedReader(ISR);
		
		String Message = BR.readLine();
		System.out.println(Message);
		
		if (Message != null)
		{
			PrintStream PS = new PrintStream(Sock.getOutputStream());
			PS.println("Message erhalten, Starte Spiel");
			
		}
	}
	
}
