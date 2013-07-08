package newgame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;


/**
 * Server Class
 * Erstellt einen Serversocket und einen Socket und stellt einen Port bereit
 * @author Tobias
 *
 */
public class Diggy_1_Server {

	public static boolean test1 = false;
	
	/**
	 * Main Methode, die einen neuen Server erstellt und dessen Funktionen ausführt
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Diggy_1_Server Server = new Diggy_1_Server();
		Server.run();
		test1 = true;
	}
	
	public void run() throws Exception
	{
		ServerSocket Diggysocket = new ServerSocket(333);											//Erstellt einen Serversocket mit einem Port
		Socket Sock = Diggysocket.accept();															
		InputStreamReader ISR = new InputStreamReader(Sock.getInputStream());						//Liest den Input
		BufferedReader BR = new BufferedReader(ISR);
		
		String Message = BR.readLine();
		System.out.println(Message);
		
		if (Message != null)
		{
			PrintStream PS = new PrintStream(Sock.getOutputStream());								//wenn der Server eine Nachricht erhält soll er antworten
			PS.println("Message erhalten, Starte Spiel");
			
		}
	}
	
}
