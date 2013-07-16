package newgame;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import java.io.IOException;

/**
 * Erstellt einen Dialog fuer den NPC
 * Im Frame existieren Text und Buttons
 * @author Tobias
 *
 */
public class Newframe extends JFrame implements ActionListener{
	

private JButton host;																										//definiert 3 JButtons und 1 JLabel
private JButton client;
private JLabel text;
private boolean test = false;



/**
 * Bedingungen und Positionen der Bestandteile
 * @param Title Legt den Titeltext fest
 */
public Newframe(String Title){
	super(Title);
	

	
	text = new JLabel("                     Waehlen Sie aus ob sie ein Spiel hosten oder einem Spiel beitreten moechten");		//Legt den ersten Text im Label fest
	text.setFont(new Font("Serif", Font.PLAIN, 14));																		//Legt Schriftgroesse und Font fest
	getContentPane().add(text);

	host = new JButton("Server hosten");																							//definiert Position und Größe für die Buttons
	host.setBounds(190,170,200,80);
	host.addActionListener(this);
	
	client = new JButton("Spiel beitreten");																						//definiert Position und Größe für die Buttons
	client.setBounds(190,400,200,80);
	client.addActionListener(this);

	if(test == true){
		try {
			chatserver();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	add(host);
	add(client);																										//fuegt die Buttons und den Text im ersten Label zu
	add(text);
}

@Override
public void actionPerformed(ActionEvent e) {

if (e.getSource()==host)																									//schliesst das Fenster auf Druck
	test = true;
	//setVisible(false);

	


if (e.getSource()==client)
	try {
		chatgui();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	
}

public static void chatserver() throws IOException{
	Chat_Server chatserver = new Chat_Server();										//startet den Server, unabhängig vom Client
	try {
		Chat_Server.main(null);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public static void chatgui() throws IOException{
	Chat_Server_GUI chatgui = new Chat_Server_GUI();										//startet den Server, unabhängig vom Client
	try {
		Chat_Server_GUI.main(null);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
