package newgame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Chat_Server {

	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<String> CurrentUsers = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException
	{
		try
		{
			final int PORT = 323;
			ServerSocket SERVER = new ServerSocket(PORT);
			System.out.println("Auf Clients warten...");
			
			while(true)
			{
				Socket SOCK = SERVER.accept();
				ConnectionArray.add(SOCK);
				
				System.out.println("Client verbunden von: " + SOCK.getLocalAddress().getHostName());
				
				AddUserName(SOCK);
				
				Chat_Server_Return CHAT = new Chat_Server_Return(SOCK);
				Thread X = new Thread(CHAT);
				X.start();
			}
		}
		
		catch(Exception X) {System.out.print(X);}
	}
	
	public static void AddUserName(Socket X) throws IOException
	{
		Scanner INPUT = new Scanner(X.getInputStream());
		String UserName = INPUT.nextLine();
		CurrentUsers.add(UserName);
		
		for(int i = 1; i <= Chat_Server.ConnectionArray.size(); i++)
		{
			Socket TEMP_SOCK = (Socket) Chat_Server.ConnectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter (TEMP_SOCK.getOutputStream());
			OUT.println("#?!" + CurrentUsers);
			OUT.flush();
		}
	}
}
