package newgame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

/**
 * Erstellt einen Client inklusive Socket und Reader
 * @author Tobias
 *
 */
public class Diggy_1_Client {

/**
 * Main Methode, die einen neuen Client erstellt und seine Funktionen ausführt
 * @param args
 * @throws Exception
 */
	public static void main(String[] args) throws Exception
	{
		Diggy_1_Client Client = new Diggy_1_Client();
		Client.run();
	}
	
	public void run() throws Exception
	{
		Socket Sock = new Socket("localhost",333);											// erstellt einen Socket fuer den Client
		PrintStream PS = new PrintStream(Sock.getOutputStream());							// erstellt eine Nachricht an den Server
		PS.println("Hallo an den Server vom Client, Starte das Spiel");
		
		InputStreamReader ISR = new InputStreamReader(Sock.getInputStream());				//liest Nachrichten ein
		BufferedReader BR = new BufferedReader(ISR);
		
		String Message = BR.readLine();
		System.out.println(Message);
		
		if (Message != null)
		{
			PrintStream PSS = new PrintStream(Sock.getOutputStream());						//Startet das Spiel, wenn der Server antwortet
			PSS.println("Message erhalten, Starte Spiel");
			game();
			
		}
	}
	
	public static void game() throws IOException{											// oeffnet das Spiel für den Multiplayermodus
		JFrame game = new JFrame("PLAY DUNGEON CRAWLER");
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		Dimension aufloesung = Toolkit.getDefaultToolkit().getScreenSize();
	    game.setSize(aufloesung);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
		game.add(new Board());																
		}
}
