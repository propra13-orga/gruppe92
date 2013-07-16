package newgame;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import java.io.IOException;

/**
 * Erstellt einen Text für das Schild
 * Im Frame existieren Text und Buttons
 * @author Tobias
 *
 */
public class Dialogueschild extends JFrame implements ActionListener{
  
	private JButton weiter;																										//definiert 3 JButtons und 1 JLabel
	private JButton schliessen;
	private JLabel text;
	private JButton weiter2;

/**
 * Bedingungen und Positionen der Bestandteile
 * @param Title Legt den Titeltext fest
 */
public Dialogueschild(String Title){
	super(Title);
	
	text = new JLabel("                      !STOP! TODESFELD !STOP! Ein falscher Schritt fuehrt zum Tod ");			//legt den Text des Schild fest
	text.setFont(new Font("Serif", Font.PLAIN, 14));																		//Legt Schriftgroesse und Font fest
	getContentPane().add(text);

	weiter = new JButton("Weiter");																							//definiert Position und Gr��e f�r die Buttons
	weiter.setBounds(80,200,90,30);
	weiter.addActionListener(this);
	
	weiter2 = new JButton("Weiter2");																						//definiert Position und Gr��e f�r die Buttons
	weiter2.setBounds(80,200,90,30);
	weiter2.addActionListener(this);

	schliessen = new JButton("Schliessen");																					//definiert Position und Gr��e f�r die Buttons
	schliessen.setBounds(400,200,100,30);
	schliessen.addActionListener(this);

	add(weiter);
	add(schliessen);																										//fuegt die Buttons und den Text im ersten Label zu
	add(text);
}

@Override
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub

if (e.getSource()==schliessen)																								//schliesst das Fenster auf Druck
	setVisible(false);

if (e.getSource()==weiter)
	text.setText("                   * nach Vorne # nach Rechts * nach Vorne + nach Links * nach Vorne ");				//aendert den Text auf Druck
	remove(weiter);
	add(weiter2);
	
if (e.getSource()==weiter2)
	text.setText("");
	remove(weiter2);
}
}
