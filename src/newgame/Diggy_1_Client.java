package newgame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

public class Diggy_1_Client {

	
	public static void main(String[] args) throws Exception
	{
		Diggy_1_Client Client = new Diggy_1_Client();
		Client.run();
	}
	
	public void run() throws Exception
	{
		Socket Sock = new Socket("localhost",333);
		PrintStream PS = new PrintStream(Sock.getOutputStream());
		PS.println("Hallo an den Server vom Client, Starte das Spiel");
		
		InputStreamReader ISR = new InputStreamReader(Sock.getInputStream());
		BufferedReader BR = new BufferedReader(ISR);
		
		String Message = BR.readLine();
		System.out.println(Message);
		
		if (Message != null)
		{
			PrintStream PSS = new PrintStream(Sock.getOutputStream());
			PSS.println("Message erhalten, Starte Spiel");
			game();
			
		}
	}
	
	public static void game() throws IOException{											// Fenster fuer's Spiel
		JFrame game = new JFrame("PLAY DUNGEON CRAWLER");
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		Dimension aufloesung = Toolkit.getDefaultToolkit().getScreenSize();
	    game.setSize(aufloesung);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
		game.add(new Board());																// oeffnet Klasse board (das eigentliche Spiel)
		}
}
