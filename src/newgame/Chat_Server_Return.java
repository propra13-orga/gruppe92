package newgame;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Chat_Server_Return implements Runnable {

	Socket SOCK;
	private Scanner INPUT;
	private PrintWriter OUT;
	String MESSAGE = "";
	
	public Chat_Server_Return(Socket X)
	{
		this.SOCK = X;
	}
	
	public void CheckConnection() throws IOException
	{
		if(!SOCK.isConnected())
		{
			for(int i = 1; i <= Chat_Server.ConnectionArray.size(); i++)
			{
				if(Chat_Server.ConnectionArray.get(i) == SOCK)
				{
					Chat_Server.ConnectionArray.remove(i);
				}
			}
			
			for(int i = 1; i <= Chat_Server.ConnectionArray.size(); i++)
			{
				Socket TEMP_SOCK = (Socket) Chat_Server.ConnectionArray.get(i-1);
				PrintWriter TEMP_OUT = new PrintWriter (TEMP_SOCK.getOutputStream());
				TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + " disconnected!");
				TEMP_OUT.flush();
				//Zeigt den Disconnect vom Server
				System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + " disconnected!");
				
			}
		}
	}
	
	public void run()
	{
		try
		{
			try
			{
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				
				while(true)
				{
					CheckConnection();
					
					if(!INPUT.hasNext())
					{return;}
					
					MESSAGE = INPUT.nextLine();
					
					System.out.println("Client schreibt: " + MESSAGE);
					
					for(int i = 1; i <= Chat_Server.ConnectionArray.size(); i++)
					{
						Socket TEMP_SOCK = (Socket) Chat_Server.ConnectionArray.get(i-1);
						PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
						TEMP_OUT.println(MESSAGE);
						TEMP_OUT.flush();
						System.out.println("Gesendet an: " + TEMP_SOCK.getLocalAddress().getHostName());
					}//schliesst for-Schleife
				}//schliesst while-Schleife
			}//schliesst inneres try
			finally
			{
				SOCK.close();
				
			}
		}//schliesst aeusseres try
		catch(Exception X) {System.out.println(X);}
	}
}
