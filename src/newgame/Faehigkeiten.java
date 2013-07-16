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
public class Faehigkeiten extends JFrame implements ActionListener{
	

private JButton nummer1;																										//definiert 3 JButtons und 1 JLabel
private JButton nummer2;
private JLabel text;
private JButton nummer3;


/**
 * Bedingungen und Positionen der Bestandteile
 * @param Title Legt den Titeltext fest
 */
public Faehigkeiten(String Title){
	super(Title);
	

	
	text = new JLabel("                     Manaentzug: 5XP  Lebensentzug: 10XP  Elementarmeister: 15 XP");		//Legt den ersten Text im Label fest
	text.setFont(new Font("Serif", Font.PLAIN, 14));																		//Legt Schriftgroesse und Font fest
	getContentPane().add(text);

	nummer1 = new JButton("Manaentzug");																							//definiert Position und Größe für die Buttons
	nummer1.setBounds(50,170,200,80);
	nummer1.addActionListener(this);
	
	nummer2 = new JButton("Elementarmeister");																						//definiert Position und Größe für die Buttons
	nummer2.setBounds(190,400,200,80);
	nummer2.addActionListener(this);

	nummer3 = new JButton("Lebensentzug");																					//definiert Position und Größe für die Buttons
	nummer3.setBounds(330,170,200,80);
	nummer3.addActionListener(this);

	if(Board.manaleech ==false){
		add(nummer1);
	}
	if(Board.elementarmeister == false){
		add(nummer2);																										//fuegt die Buttons und den Text im ersten Label zu
	}
	if(Board.lifeleech == false){
		add(nummer3);
	}
	add(text);
}

@Override
public void actionPerformed(ActionEvent e) {

if (e.getSource()==nummer1)																									//schliesst das Fenster auf Druck
	if(Board.exp >= 5){
		Board.exp = Board.exp -5;
		Board.manaleech = true;
		setVisible(false);
	}


if (e.getSource()==nummer2)
	if(Board.exp >=15){
	Board.exp = Board.exp - 15;
	Board.elementarmeister = true;
	setVisible(false);
	}
	
if (e.getSource()==nummer3)
	if(Board.exp >=10){
		Board.exp = Board.exp -10;
		Board.lifeleech = true;
		setVisible(false);
		}
}
}
