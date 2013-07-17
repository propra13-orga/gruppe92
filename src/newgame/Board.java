package newgame;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import newgame.AudioPlayer;          // importiert den Audioplayer aus der AudioPlayer class



/**
 * Diese Klasse stellt das Hauptspiel dar
 * @author Tobias
 *
 */
public class Board extends JPanel implements ActionListener{
	
	

	/**
	 * Fuegt angegebene Variablen zum Board hinzu, darunter fallen beispielsweise die Gegner, das Mana oder das Geld
	 */
	private static final long serialVersionUID = 1L;
	Image image;
	Image img;
	private Character Jay;
	private String raum="";
	private String lr,rooms,lrs; 											//lr fuer den Namen der Raumdatei, w:wandbild , h:hintergrundsbild
	
	private ArrayList<Shot> shots;
	private ArrayList<Iceshot> iceshots;
	private ArrayList<Earthshot> earthshots;
	private ArrayList<Airshot> airshots;
	
	private ArrayList<Sword> swords;
	private ArrayList<Icesword> iceswords;
	private ArrayList<Earthsword> earthswords;
	private ArrayList<Airsword> airswords;
	
	private Timer timer;
	private int BLOCK = 50;
	private int position;
	private int ruban=0,xruban;
	private double life=3.0, xlife;
	private int magic=0;
	private int manapoints=0;
	
	private int schwertchen = 0;
	private int eisschwertchen = 0;
	private int erdschwertchen = 0;
	private int luftschwertchen = 0;
	
	private int feuerchen = 0;
	private int erdchen = 0;
	private int eischen = 0;
	private int luftchen = 0;
	
	private int k,z,posX,posY;
	
	public static int exp;
	
	boolean ingame,mana,failed,get_sword,get_icesword,get_earthsword,get_airsword,get_fireball,get_iceball, get_airball, get_earthball, test;
	
	public static boolean elementarmeister, manaleech, lifeleech;
	
	//private AudioPlayer backgroundMusic;
	private checkpoint check;
	private Raetselweg Weg;
	private Todesfeld Feld;
	private Ghost Geist;
	private Boss Monster;
	private Ball ball;
	Font smallfont = new Font("Helvetica", Font.BOLD, 17);


	/**
	 * Fuegt angegebene Images zum Board hinzu
	 */
	ImageIcon r = new ImageIcon("src/Resources/r1.png");
	ImageIcon l = new ImageIcon("src/Resources/l1.png");
	ImageIcon t = new ImageIcon("src/Resources/Character top.png");
	ImageIcon b = new ImageIcon("src/Resources/Character.png");
	
	ImageIcon fr = new ImageIcon("src/Resources/Character right with firesword.png");
	ImageIcon fl = new ImageIcon("src/Resources/Character left with firesword.png");
	ImageIcon ft = new ImageIcon("src/Resources/Character top with firesword.png");
	ImageIcon fu = new ImageIcon("src/Resources/Character with firesword.png");
	
	ImageIcon ir = new ImageIcon("src/Resources/Character right with icesword.png");
	ImageIcon il = new ImageIcon("src/Resources/Character left with icesword.png");
	ImageIcon it = new ImageIcon("src/Resources/Character top with icesword.png");
	ImageIcon iu = new ImageIcon("src/Resources/Character with icesword.png");
	
	ImageIcon er = new ImageIcon("src/Resources/Character right with earthsword.png");
	ImageIcon el = new ImageIcon("src/Resources/Character left with earthsword.png");
	ImageIcon et = new ImageIcon("src/Resources/Character top with earthsword.png");
	ImageIcon eu = new ImageIcon("src/Resources/Character with earthsword.png");
	
	ImageIcon ar = new ImageIcon("src/Resources/Character right with airsword.png");
	ImageIcon al = new ImageIcon("src/Resources/Character left with airsword.png");
	ImageIcon at = new ImageIcon("src/Resources/Character top with airsword.png");
	ImageIcon au = new ImageIcon("src/Resources/Character with airsword.png");
	
	ImageIcon tr = new ImageIcon("src/Resources/trankk.png");
	ImageIcon h1 = new ImageIcon("src/Resources/herz.png");
	
	ImageIcon dr = new ImageIcon("src/Resources/digright.png");	
	ImageIcon dl = new ImageIcon("src/Resources/digleft.png");
	ImageIcon du = new ImageIcon("src/Resources/digup.png");
	ImageIcon db = new ImageIcon("src/Resources/digb.png");
	
	ImageIcon sw = new ImageIcon("src/Resources/firesword.png");
	ImageIcon is = new ImageIcon("src/Resources/icesword.png");
	ImageIcon es = new ImageIcon("src/Resources/earthsword.png");
	ImageIcon as = new ImageIcon("src/Resources/airsword.png");
	
	ImageIcon co = new ImageIcon("src/Resources/Coin.png");
	ImageIcon h2 = new ImageIcon("src/Resources/halbherz.png");
	ImageIcon s = new ImageIcon("src/Resources/schatz.png");
	
	ImageIcon fb = new ImageIcon("src/Resources/fireball.png");
	ImageIcon ib = new ImageIcon("src/Resources/iceball.png");
	ImageIcon ab = new ImageIcon("src/Resources/airball.png");
	ImageIcon eb = new ImageIcon("src/Resources/earthball.png");

	
	
	
	
/**
 * Fuegt angegebene Arrays zum Board hinzu
 */
	java.util.List<Movement> enemys = new java.util.ArrayList<Movement>();
	java.util.List<Movement> walls = new java.util.ArrayList<Movement>();
	java.util.List<Movement> keys = new java.util.ArrayList<Movement>();
	java.util.List<Movement> wizards = new java.util.ArrayList<Movement>();
	java.util.List<Movement> coins = new java.util.ArrayList<Movement>();
	java.util.List<Movement> shopkeepers = new java.util.ArrayList<Movement>();
	java.util.List<Movement> manas = new java.util.ArrayList<Movement>();
	java.util.List<Movement> Jays = new java.util.ArrayList<Movement>();
	java.util.List<Movement> herzen = new java.util.ArrayList<Movement>();
	java.util.List<Movement> Wegs = new java.util.ArrayList<Movement>();
	java.util.List<Movement> Felds = new java.util.ArrayList<Movement>();
	java.util.List<Movement> Schilders = new java.util.ArrayList<Movement>();
	java.util.List<Movement> Rätselblocks = new java.util.ArrayList<Movement>();
	
	java.util.List<Movement> schwerter = new java.util.ArrayList<Movement>();
	java.util.List<Movement> eisschwerter = new java.util.ArrayList<Movement>();
	java.util.List<Movement> erdschwerter = new java.util.ArrayList<Movement>();
	java.util.List<Movement> luftschwerter = new java.util.ArrayList<Movement>();
	
	java.util.List<Movement> feuericons = new java.util.ArrayList<Movement>();
	java.util.List<Movement> eisicons = new java.util.ArrayList<Movement>();
	java.util.List<Movement> lufticons = new java.util.ArrayList<Movement>();
	java.util.List<Movement> erdeicons = new java.util.ArrayList<Movement>();
	
	/**
	 * Gibt den Images entsprechende Namen		
	 */
	Image image1 = image = r.getImage();
	Image image2 = image = l.getImage();
	Image image3 = image = t.getImage();	
	Image image4 = image = b.getImage();
	
	Image imagef1 = image = fr.getImage();
	Image imagef2 = image = fl.getImage();
	Image imagef3 = image = ft.getImage();
	Image imagef4 = image = fu.getImage();
	
	Image imagei1 = image = ir.getImage();
	Image imagei2 = image = il.getImage();
	Image imagei3 = image = it.getImage();
	Image imagei4 = image = iu.getImage();
	
	Image imagee1 = image = er.getImage();
	Image imagee2 = image = el.getImage();
	Image imagee3 = image = et.getImage();
	Image imagee4 = image = eu.getImage();
	
	Image imagea1 = image = ar.getImage();
	Image imagea2 = image = al.getImage();
	Image imagea3 = image = at.getImage();
	Image imagea4 = image = au.getImage();
	
	Image trank = image = tr.getImage();
	Image herz1 = image = h1.getImage();
	Image herz2 = image = h2.getImage();
	Image schatz = image = s.getImage();
	Image coin = image = co.getImage();
	
	Image image5 = image = dr.getImage();
	Image image6 = image = dl.getImage();
	Image image7 = image = du.getImage();
	Image image8 = image = db.getImage();
	
	Image sword = image = sw.getImage();
	Image icesword = image = is.getImage();
	Image earthsword = image = es.getImage();
	Image airsword = image = as.getImage();
	
	Image fireball = image = fb.getImage();
	Image airball = image = ab.getImage();
	Image iceball = image = ib.getImage();
	Image earthball = image = eb.getImage();
	
/**
 * Gibt den Status des Spiels zu Beginn an
 * Der Spieler startet in Level 1 Raum 1 ohne Manatraenke, Schwert und mit vollem Mana
 * @throws IOException
 */

	public Board() throws IOException{

		lr="l1r1";	
		addKeyListener(new Ap());
		setFocusable(true);
		setDoubleBuffered(true);
		initWorld(image4);
		ingame = true;
		mana = false;
		elementarmeister = false;
		manaleech = false;
		lifeleech = false;
		test = false;
		
		schwertchen = 0;
		eisschwertchen = 0;
		erdschwertchen = 0;
		luftschwertchen = 0;
		
		feuerchen = 0;
		eischen = 0;
		luftchen = 0;
		erdchen = 0;
		manapoints = 100;
		
	    shots = new ArrayList<Shot>();
	    airshots = new ArrayList<Airshot>();
	    iceshots = new ArrayList<Iceshot>();
	    earthshots = new ArrayList<Earthshot>();
	    
		swords = new ArrayList<Sword>();
		iceswords = new ArrayList<Icesword>();
		earthswords = new ArrayList<Earthsword>();
		airswords = new ArrayList<Airsword>();
		
	    timer = new Timer(5, this);
        timer.start();
        
        /* Spielt die ganze Zeit die Hintergrundmusik in allen 
         * Leveln
         */
        
       //backgroundMusic = new AudioPlayer("/Resources/BGMusic.mp3");
       //backgroundMusic.play();
       
       /*
        Diese eine Zeile ist die Methode, die die savedata.txt ausliest
        */
       
       //DataReader.read("src/Resources/savedata.txt");

    }

	/**
	 * Die Methode loeschen entfernt alle genannten Elemente aus dem vorigen Level
	 * Geschieht nur wenn in das nachste Level uebergewechselt wird
	 * @param b
	 */

	public void loeschen(boolean b){
		coins.clear();
		walls.clear();
		enemys.clear();
		keys.clear();
		wizards.clear();
		manas.clear();
		herzen.clear();
		shopkeepers.clear();
		Schilders.clear();
		Rätselblocks.clear();
		
		
		schwerter.clear();
		eisschwerter.clear();
		erdschwerter.clear();
		luftschwerter.clear();
		
		eisicons.clear();
		feuericons.clear();
		lufticons.clear();
		erdeicons.clear();
		
		if (b) raum="";
		if (failed) {
			if (lr==lrs){
				raum=rooms;
			}
			ruban=0;
			life=3;
			magic=3;																	
		}
	}

	/**
	 * Diese Methode beschreibt die Kollision des Charakters mit Elementen im Board
	 * Der Charakter kollidiert beispielsweise mit Waenden, NPC's oder Ladenbesitzern
	 * Durch den Rest kann der Charakter durchlaufen
	 * Zudem werden Methoden aufgerufen, wenn der Charakter kollidiert
	 * So wird beispielsweise ein Dialogfenster geoeffnet oder Geld ins Inventar eingelagert
	 * @param movx Bewegungsvariable auf der x-Achse
	 * @param movy Bewegungsvariable auf der y-Achse
	 * @param image Macht eine Abfrage in welche Richtung der Charakter schaut und gibt das entsprechende Bild aus
	 */
	public void collision(int movx,int movy,Image image){ 
		
		int xx = ((Jay.getX()+movx)/BLOCK);																	 							
		int yy=(Jay.getY()+movy)/BLOCK;	
			
		if ((raum.charAt(yy*20+xx)!='#')&&(raum.charAt(yy*20+xx)!='~')&&(raum.charAt(yy*20+xx)!='Y')&&(raum.charAt(yy*20+xx)!='n')&&(raum.charAt(yy*20+xx)!='s')&&(xx>=0)||(Jay.getY()<0))
		{																							        							
			Jay.move(movx,movy);
			if (raum.charAt(yy*20+xx)=='a'){
				ruban= ruban+1;
				if (raum.contains("@") )
				{	int c =raum.lastIndexOf("@");						
					raum=raum.substring(0,c)+' '+raum.substring(c+1);
					raum=raum.substring(0,yy*20+xx)+'@'+raum.substring(yy*20+xx+1);
					try {
						restartLevel(false,Jay.getImage());
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}
			}
		}
		
		/**
		 * Lebensverlust bei Gegnerkollision
		 * Neustart des Spiels bei Tod
		 */
		if (raum.charAt(yy*20+xx)=='*'){
			life=life-0.5;
			if(life==0){
				failed=true;
				try {
					
					restartLevel(true,Jay.getImage());
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
			else{
			}
	   }
	   	if (raum.charAt(yy*20+xx)=='_'){
				failed=true;
				try {
				
					restartLevel(true,Jay.getImage());
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
			}
			else{
			}
		/**
		 * Startet den Dialog des NPC
		 */
		if (raum.charAt(yy*20+xx)=='~'){
			Dialogue();
		}
		/**
		 * Startet den Dialog des Ladenbesitzers
		 */
		if (raum.charAt(yy*20+xx)=='s'){
			DialogueShop();
		}
		if (raum.charAt(yy*20+xx)=='Y'){
			Dialogueschild();
		}
		if (raum.charAt(yy*20+xx)=='n'){
			DialogueRätsel();
		}
		/**
		 * Nimmt bei Kollision das Schwert auf
		 */
		if (raum.charAt(yy*20+xx)=='5'){
			schwertchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision das Eisschwert auf
		 */
		if (raum.charAt(yy*20+xx)=='6'){
			eisschwertchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision das Erdschwert auf
		 */
		if (raum.charAt(yy*20+xx)=='7'){
			erdschwertchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision das Luftschwert auf
		 */
		if (raum.charAt(yy*20+xx)=='8'){
			luftschwertchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision den Feuerzauber auf
		 */
		if (raum.charAt(yy*20+xx)=='1'){
			feuerchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision den Eiszauber auf
		 */
		if (raum.charAt(yy*20+xx)=='2'){
			eischen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision den Luftzauber auf
		 */
		if (raum.charAt(yy*20+xx)=='3'){
			luftchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision den Erdzauber auf
		 */
		if (raum.charAt(yy*20+xx)=='4'){
			erdchen = 5;
			spend_herzen();
		}
		/**
		 * Nimmt bei Kollision ein Leben auf und gibt 30 Gold aus
		 */
		if (raum.charAt(yy*20+xx)=='h'){
				if((ruban>= 30)||(xruban>=30)){
				life = life+1;
				xruban = xruban - 30;
				mana = true;
				spend_herzen();}
				else{
				}
			}

		/**
		 * Nimmt bei Kollision im Shop einen Manatrank ins Inventar und gibt 20 Gold aus
		 */
		if (raum.charAt(yy*20+xx)=='q'){
			if ((ruban >= 20)||(xruban>=20)){
			magic = magic + 1;
			mana = true;
			spend_mana();
			xruban = xruban-20;
		}
			else{
			}
			}
		
		/**
		 * Nimmt bei Kollision einen Manatrank ins Inventar
		 */
		if (raum.charAt(yy*20+xx)=='m'){
				magic = magic  + 1;
				mana = true;
				spend_mana();


		}
		/**
		 * Sollte ein Schluessel aufgenommen werden startet das naechste Level
		 */
		else if (raum.charAt(yy*20+xx)=='$'){
			if (lr.charAt(1)=='1') lr="l2r1";
			else if (lr.charAt(1)=='2')lr="l3r1"; 
			else if (lr.charAt(1)=='3')lr="l3r6";
			loeschen(true);
			try {
				initWorld(Jay.getImage());
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		/**
		 * Speichert das Geld, die Position und den Raum, in dem sich der Charakter befindet
		 */
		else if (raum.charAt(yy*20+xx)=='b'){
			xruban=xruban+ruban;															
			ruban=0;
			posX=Jay.getX();
			posY=Jay.getY();
			rooms=raum;
			lrs=lr;
		}
	}

	/**
	 * Methode, die die Bewegung des Bosses definiert
	 */
	public void movemonster() {
		
		if (Jay.getY()<Monster.getY()){
			Monster.move(0,-Monster_speed);
		}
		else if (Jay.getY()>Monster.getY()){
			Monster.move(0,Monster_speed);
		}
		if (Jay.getX()>Monster.getX()){
			Monster.move(Monster_speed,0);
		}
		else if (Jay.getX()<Monster.getX()){
			Monster.move(-Monster_speed,0);
		}
		kollision_boss_spieler();
	}
		
	
		/**
		 * Methode, die die Bewegung des Geists definiert
		 */
		public void movegeist() {
			
		
		if (Jay.getY()<Geist.getY()){
			Geist.move(0,-Geist_speed);
		}
		else if (Jay.getY()>Geist.getY()){
			Geist.move(0,Geist_speed);
		}
		else if (Jay.getX()>Geist.getX()){
			Geist.move(Geist_speed,0);
		}
		else if (Jay.getX()<Geist.getX()){
			Geist.move(-Geist_speed,0);
		}
		
		/*
		 * Hier findet die Kollision mit Geist statt und weiter unten ist die
		 * genau Definition
		 */
		
		//kollision_ghost_spieler();
		
		//if (counter % 150==0){
			
			//Geist.setX(Geist.getX()); // Zus�tzlich selbe Kollision, wie mit dem ball vom Boss
			//Geist.setX(Geist.getX());
			//mx=0;my=0;
		}
	
		
		
		
		
	
	/**
	 * Fuegt folgende Integer-Variablen hinzu
	 */
	private int mx,my,counter,Monster_speed,Geist_speed,schuss_speed;

	/**
	 * Methode, die die Bewegung des Schusses vom Boss definiert
	 */
	public void moveBall() {
			
		if (ball.getX()<mx){
			ball.move(schuss_speed, 0);
		}
		else if (ball.getX()>mx){
			ball.move(-schuss_speed, 0);
		}
		if (ball.getY()<my){
			ball.move(0, schuss_speed);
		}
		else if (ball.getY()>my){
			ball.move(0,-schuss_speed);
		}
		
		kollision_ball_spieler();
		
		if (counter % 150==0){
			ball.setX(Monster.getX());
			ball.setY(Monster.getY());
			//Geist.setX(Geist.getX()); // Zus�tzlich selbe Kollision, wie mit dem ball vom Boss
		//	Geist.setX(Geist.getX());
			mx=0;my=0;
		}
	}
	
			
	/**
	 * Definiert die Methode, die den Spielern Leben verlieren laesst
	 * Nur bei Beruehrung des Schusses vom Boss
	 */
	private void kollision_ball_spieler() {
		if ((Math.abs(Jay.getX()-ball.getX())<50)&&(Math.abs(Jay.getY()-ball.getY())<50)){
			if(life==0){
				
				failed=true;
				try {
					restartLevel(true,Jay.getImage());
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			else{
				life=life-1;
			}
		}
	}
	
	/*private void kollision_ghost_spieler() {
		if ((Math.abs(Jay.getX()-Geist.getX())<50)&&(Math.abs(Jay.getY()-Geist.getY())<50)){
			if(life==0){
				
				failed=true;
				try {
					restartLevel(true,Jay.getImage());
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			else{
				life=life-1;
			}
		}
	}*/
	
	/**
	 * Definiert die Methode, die den Spielern Leben verlieren laesst
	 * Nur bei Beruehrung des Bosses selbst
	 */
	private void kollision_boss_spieler() {
		if ((Math.abs(Jay.getX()-Monster.getX())<50)&&(Math.abs(Jay.getY()-Monster.getY())<50)){
			if(life==0){
				
				failed=true;
				try {
					restartLevel(true,Jay.getImage());
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			else{
				life=life-1;
			}
		}
	}

	public Image getImage(){
		return image;
	}

	/**
	 * Liest die Textdateien mit den Raeumen ein
	 * @return Gibt die Zeichnung des Raums wieder
	 * @throws IOException
	 */
	public String raumeinlesen() throws IOException{
		String room="";
		FileReader fr = new FileReader("src/Resources/"+lr+".txt");
		BufferedReader br = new BufferedReader(fr);
		String zeile = br.readLine();
		while (zeile != null)
		{
			room=room+zeile+'\n';
		    zeile = br.readLine();
		}
		br.close();
		return room;
	}

	/**
	 * Diese Funktion liest alle Variablen ein und fuegt sie dem Board hinzu
	 * @param image Gibt die Pfade der Bilder an
	 * @throws IOException
	 */
	public final void initWorld(Image image) throws IOException{

		ImageIcon ii= new ImageIcon ("src/Resources/back"+lr.charAt(1)+".png");
		img=ii.getImage();		//Image importieren.	
		setBackground(Color.BLACK);
		if (raum=="") raum=raumeinlesen();
		int x = 0;
		int y = 0;
		Wall wall;
		Coin coin;
		Enemy enemy;
		Key key;
		Wizard wizard;
		Shopkeeper shopkeeper;
		Mana mana;
		Mana shopmana;
		Heiltrank heiltrank;
		Schild schild;
		Raetselblock raetselblock;
		
		
		Swordicon schwert;
		Iceswordicon eisschwert;
		Earthswordicon erdschwert;
		Airswordicon luftschwert;
		
		Eisicon eis;
		Feuericon feuer;
		Lufticon luft;
		Erdeicon erde;
	
		
		 

		
		
		for(int i = 0; i < raum.length(); i++){											// Level durchgehen

			char obj = raum.charAt(i);										

			if(obj == '\n'){															//y erhoeht sich um einen BLOCK wenn man ein /n im String Level findet
				y = y + BLOCK;
				x = 0;
			}else if(obj == '#'){	
				wall = new Wall(x,y, "wand"+ lr.charAt(1));
				walls.add(wall);
				x = x + BLOCK;
				
			}
			else if(obj == '@'){														// Legt die Position des Charakters beim Levelstart fest
				if (lr!="l3r6"){	
					if (failed==false)Jay = new Character(x,y);
					else if (failed){
						if (lr==lrs) Jay=new Character(posX,posY);
						else Jay = new Character(x,y);
						failed=false;
					}
					if (image==image1){
						image =	r.getImage();
						Jay.setImage(image);}
					if (image==image2){
						image =	l.getImage();
						Jay.setImage(image);}
					if (image==image3){
						image =	t.getImage();
						Jay.setImage(image);}
					if (image==image4){
						image =	b.getImage();
						Jay.setImage(image);}
					
					if (image==imagef1){
						image =	fr.getImage();
						Jay.setImage(image);}
					if (image==imagef2){
						image =	fl.getImage();
						Jay.setImage(image);}
					if (image==imagef3){
						image =	ft.getImage();
						Jay.setImage(image);}
					if (image==imagef4){
						image =	fu.getImage();
						Jay.setImage(image);}
					
					if (image==imagei1){
						image =	ir.getImage();
						Jay.setImage(image);}
					if (image==imagei2){
						image =	il.getImage();
						Jay.setImage(image);}
					if (image==imagei3){
						image =	it.getImage();
						Jay.setImage(image);}
					if (image==imagei4){
						image =	iu.getImage();
						Jay.setImage(image);}
					
					if (image==imagee1){
						image =	er.getImage();
						Jay.setImage(image);}
					if (image==imagee2){
						image =	el.getImage();
						Jay.setImage(image);}
					if (image==imagee3){
						image =	et.getImage();
						Jay.setImage(image);}
					if (image==imagee4){
						image =	eu.getImage();
						Jay.setImage(image);}
					
					if (image==imagea1){
						image =	ar.getImage();
						Jay.setImage(image);}
					if (image==imagea2){
						image =	al.getImage();
						Jay.setImage(image);}
					if (image==imagea3){
						image =	at.getImage();
						Jay.setImage(image);}
					if (image==imagea4){
						image =	au.getImage();
						Jay.setImage(image);}
					
					if (image==image5){
						image =	dr.getImage();
						Jay.setImage(image);}	
					if (image==image6){
						image =	dl.getImage();
						Jay.setImage(image);}
					if (image==image7){
						image =	du.getImage();
						Jay.setImage(image);}
					if (image==image8){
						image =	db.getImage();
						Jay.setImage(image);}


				x = x + BLOCK;}
			}
			else if(obj == ' '){										//x erhoeht sich um einen Block(' ':Bereich wo sich der Spieler bewegen kann)
				x = x + BLOCK;
			}
			else if(obj == '*'){
				enemy = new Enemy(x,y);
				enemys.add(enemy);
				x = x + BLOCK;
			}
			else if(obj == '$'){
				key = new Key(x,y);	
				keys.add(key);
				x = x + BLOCK;
			}
			else if(obj == 'Y'){
				schild = new Schild(x,y);
				Schilders.add(schild);
				x = x + BLOCK;
			}
			else if(obj == 'n'){
				raetselblock = new Raetselblock(x,y);
				Rätselblocks.add(raetselblock);
				x = x + BLOCK;
			}
			else if (obj=='a'){
				coin=new Coin(x,y);
				coins.add(coin);
				x=x+BLOCK;
			}
			else if(obj == '-'){
				Weg = new Raetselweg(x,y);
				Wegs.add(Weg);
				x=x+BLOCK;
			}
			else if(obj == '_'){
				Feld = new Todesfeld(x,y);
				Felds.add(Feld);
				x=x+BLOCK;
			}
			else if(obj == '~'){
				wizard = new Wizard(x,y);
				wizards.add(wizard);
				x = x + BLOCK;
			}
			else if(obj == '5'){
				schwert = new Swordicon(x,y);
				schwerter.add(schwert);
				x = x + BLOCK;
			}
			else if(obj == '6'){
				eisschwert = new Iceswordicon(x,y);
				eisschwerter.add(eisschwert);
				x = x + BLOCK;
			}
			else if(obj == '7'){
				erdschwert = new Earthswordicon(x,y);
				erdschwerter.add(erdschwert);
				x = x + BLOCK;
			}
			else if(obj == '8'){
				luftschwert = new Airswordicon(x,y);
				luftschwerter.add(luftschwert);
				x = x + BLOCK;
			}
			else if(obj == '1'){
				feuer = new Feuericon(x,y);
				feuericons.add(feuer);
				x = x + BLOCK;
			}
			else if(obj == '2'){
				eis = new Eisicon(x,y);
				eisicons.add(eis);
				x = x + BLOCK;
			}
			else if(obj == '3'){
				luft = new Lufticon(x,y);
				lufticons.add(luft);
				x = x + BLOCK;
			}
			else if(obj == '4'){
				erde = new Erdeicon(x,y);
				erdeicons.add(erde);
				x = x + BLOCK;
			}
			else if(obj == 's'){
				shopkeeper = new Shopkeeper(x,y);
				shopkeepers.add(shopkeeper);
				x = x + BLOCK;
			}
			else if(obj == 'b'){
				check = new checkpoint(x,y);
				x=x+BLOCK;
			}
			else if(obj == 'h'){
				heiltrank = new Heiltrank(x,y);
				herzen.add(heiltrank);
				x=x+BLOCK;
				
			}
			else if(obj == 'k'){
				Monster = new Boss(x,y);
				boss_leben=10;
				x=x+BLOCK;
			}
			else if(obj == 'w'){
				Geist = new Ghost(x,y);
				ghost_leben=1;  // Gibt die Leben des Geistes an, nachher bei der Kollision von Schuss mit Geist, werden diese abgezogen
				x=x+BLOCK;
			}
			else if(obj == 'r'){																	
				ball = new Ball(x,y);
				x=x+BLOCK;

			}	
			else if(obj == 'm'){
				mana = new Mana(x,y);
				manas.add(mana);
				x=x+BLOCK;
			}
			else if(obj == 'q'){
				shopmana = new Mana(x,y);
				manas.add(shopmana);
				x=x+BLOCK;
					
					
			}
		}
	}
	
	/**
	 * Zeichnet die Elemente, die der Welt hinzugefuegt wurden ins Board
	 */
	ArrayList<Movement> world = new ArrayList<Movement>();
	public void buildWorld( Graphics g){

		Graphics2D g2;
		g.drawImage(img, 0, 0, null);
		g2 = (Graphics2D)g.create();
		g2.scale(0.8, 0.8);
		ArrayList<Movement> world = new ArrayList<Movement>();

		if (raum.contains("b")==true)world.add(check);
		if (raum.contains("-")==true)world.addAll(Wegs);
		if (raum.contains("_")==true)world.addAll(Felds);
		if (lr!="l3r6")world.add(Jay);
		if (raum.contains("k")) world.add(Monster);
		if (raum.contains("r")) world.add(ball);
		if (raum.contains("w")) world.add(Geist);
		world.addAll(keys);
		world.addAll(wizards);
		world.addAll(coins);
		world.addAll(shopkeepers);
		world.addAll(manas);
		world.addAll(herzen);
		world.addAll(Schilders);
		world.addAll(Rätselblocks);
		
		world.addAll(schwerter);
		world.addAll(eisschwerter);
		world.addAll(erdschwerter);
		world.addAll(luftschwerter);
		
		world.addAll(feuericons);
		world.addAll(eisicons);
		world.addAll(lufticons);
		world.addAll(erdeicons);

		for(int i = 0; i < world.size(); i++){														// Array world durchgehen um objekte zu zeichnen.

			Movement obj = (Movement) world.get(i);
			g2.drawImage(obj.getImage(), obj.getX(), obj.getY(), this);								// g.drawImage fuer die Grafische Zeichnung
		}
		
	       ArrayList<Shot> shots = getShots();														//definiert die verschiedenen Zauber
	       		for (int j = 0; j < shots.size(); j++){
	       			Shot m = (Shot) shots.get(j);
	       			g2.drawImage(m.getImage(), m.getX(), m.getY(), this);
	       			
                }
	       		
	       	ArrayList<Airshot> airshots = getAirshots();
	       		for (int j = 0; j < airshots.size(); j++){
	       			Airshot m = (Airshot) airshots.get(j);
	       			g2.drawImage(m.getImage(), m.getX(), m.getY(), this);
	       			
                }
	       		
	       ArrayList<Iceshot> iceshots = getIceshots();
	       		for (int j = 0; j < iceshots.size(); j++){
	       			Iceshot m = (Iceshot) iceshots.get(j);
	       			g2.drawImage(m.getImage(), m.getX(), m.getY(), this);
	       			
                }
	       		
	       	ArrayList<Earthshot> earthshots = getEarthshots();
	       		for (int j = 0; j < earthshots.size(); j++){
	       			Earthshot m = (Earthshot) earthshots.get(j);
	       			g2.drawImage(m.getImage(), m.getX(), m.getY(), this);
	       			
                }
	       
	      ArrayList<Sword> swords = getSwords();													// definiert die verschiedenen Schwerter
	       		for (int i = 0; i < swords.size(); i++){
	       			Sword s = (Sword) swords.get(i);
	       			g2.drawImage(s.getImage(), s.getX(), s.getY(), this);
               }
	       
	      ArrayList<Icesword> iceswords = getIceswords();
	       		for (int i = 0; i < iceswords.size(); i++){
	       			Icesword s = (Icesword) iceswords.get(i);
	       			g2.drawImage(s.getImage(), s.getX(), s.getY(), this);
               }
	       		
	     ArrayList<Earthsword> earthswords = getEarthswords();
	       		for (int i = 0; i < earthswords.size(); i++){
	       			Earthsword s = (Earthsword) earthswords.get(i);
	       			g2.drawImage(s.getImage(), s.getX(), s.getY(), this);
               }
	       		
	     ArrayList<Airsword> airswords = getAirswords();
	       		for (int i = 0; i < airswords.size(); i++){
	       			Airsword s = (Airsword) airswords.get(i);
	       			g2.drawImage(s.getImage(), s.getX(), s.getY(), this);
               }

	       			for (int i = 0; i < enemys.size(); i++){										// Gegner soll nur zu sehen sein, wenn er nicht besiegt wurde 
	       				Enemy e = (Enemy) enemys.get(i);
	       				if (e.isVisible())
	       				g2.drawImage(e.getImage(), e.getX(), e.getY(), this);

	       			}

	       			for (int i = 0; i < walls.size(); i++){											//  NICHT LOESCHEN FUER DAS TESTEN DER WAENDE 

	       				Wall w = (Wall) walls.get(i);
	       				if (w.isVisible())
	       				g2.drawImage(w.getImage(), w.getX(), w.getY(), this);
	       			}

	       			for (int i = 0; i < coins.size(); i++){											// Muenzen sollen gezeichnet werden bei Tod

	       				Coin c = (Coin) coins.get(i);
	       				if (c.isVisible())
	       				g2.drawImage(c.getImage(), c.getX(), c.getY(), this);
	       			}
	       			 			
	}
    

	/**
	 * Zeichnet die Inventaranzeige
	 */
    public void paint(Graphics g){
	super.paint(g);
	Graphics2D g2;
	g2 = (Graphics2D)g.create();
	g2.scale(0.8, 0.8);
	
	if(ingame){	
		buildWorld(g);
		if (raum.contains("k")){
			if (lr.charAt(1)=='3') Monster_speed=1;
			else Monster_speed=1;
			movemonster();
		}
		if (raum.contains("w")&&(test==false)){
			if (lr=="l1r1") Geist_speed=1;
			else Geist_speed=1;
			movegeist();
		}
		if (raum.contains("r")){
				if (mx==0)mx=Jay.getX();
				if (my==0)my=Jay.getY();
				if (lr.charAt(1)=='3') {
					schuss_speed=4;
				}
				else if (lr.charAt(1)=='2'){
					schuss_speed=3;
					
				}
				else if (lr.charAt(1)=='1'){
					schuss_speed=2;
					
				}
				counter=counter+1;
				moveBall();
			} 
		int countsmoney= ruban + xruban;
	        String s,p,z;

	        g2.setFont(smallfont);															// Geldanzeige
	        g2.setColor(new Color(98,150,255));
	        s = "Money: " + (countsmoney);
	        g2.drawString(s,970,130);
	    	g2.drawImage(schatz,970,150,this);												// zeichnet Welt mit Punktestand..
		
	    	z = "EXP: " + exp;
	    	g2.drawString(z, 970, 270);
	    	
	    	
			String t;
			if(manaleech == true){	
				t = "Du beherrscht Manaentzug";
				g2.drawString(t,970,800);}
			
			if(lifeleech == true){	
				t = "Du beherrscht Lebensentzug";
				g2.drawString(t,970,830);}
			
			if(elementarmeister == true){	
				t = "Du beherrscht alle Elemente";
				g2.drawString(t,970,860);}
			
			String m;
			
			m = "Mana: " + (manapoints);
			g2.drawString(m,970,240);
			
			if(life > 3){
				life = 3;
			}
			
			if (schwertchen == 5){    														// zeichnet die Schwerter im Inventar
	    		g2.drawImage(sword,960, 700, this);
	    		p = "Du hast ein Feuerschwert!";
	    		g2.drawString(p,970,580);
	    		get_sword=true;
			}
			if (eisschwertchen == 5){    						
	    		g2.drawImage(icesword,1020, 700, this);
	    		p = "Du hast ein Eisschwert!";
	    		g2.drawString(p,970,610);
	    		get_icesword=true;
			}
			if (erdschwertchen == 5){    						
	    		g2.drawImage(earthsword,1080, 700, this);
	    		p = "Du hast ein Erdschwert!";
	    		g2.drawString(p,970,670);
	    		get_earthsword=true;
			}
			if (luftschwertchen == 5){    						
	    		g2.drawImage(airsword,1140, 700, this);
	    		p = "Du hast ein Luftschwert!";
	    		g2.drawString(p,970,640);
	    		get_airsword=true;
			}
			if (feuerchen == 5){    														// zeichnet die Zauber im Inventar
	    		g2.drawImage(fireball,960, 500, this);
	    		p = "Du hast die Macht des Feuers!";
	    		g2.drawString(p,970,400);
	    		get_fireball=true;
			}
			if (eischen == 5){    												
	    		g2.drawImage(iceball,1020, 500, this);
	    		p = "Du hast die Macht des Eises!";
	    		g2.drawString(p,970,430);
	    		get_iceball=true;
			}
			if (luftchen == 5){    												
	    		g2.drawImage(airball,1140, 500, this);
	    		p = "Du hast die Macht der Luft!";
	    		g2.drawString(p,970,460);
	    		get_airball=true;
			}
			if (erdchen == 5){    							
	    		g2.drawImage(earthball,1080, 500, this);
	    		p = "Du hast die Macht der Erde!";
	    		g2.drawString(p,970,490);
	    		get_earthball=true;
			}
			
			if(life==3.0){																	// zeichnet das Leben
			g2.drawImage(herz1,970,30,this);
			g2.drawImage(herz1,1020, 30, this);
			g2.drawImage(herz1,1070, 30, this);
			}
			if(life==2.5){
				g2.drawImage(herz1,970,30,this);
				g2.drawImage(herz1,1020, 30, this);
				g2.drawImage(herz2,1070, 30, this);}
			
			if(life==2.0){						
				g2.drawImage(herz1,970,30,this);
				g2.drawImage(herz1,1020, 30, this);
			}
			if(life==1.5){
				g2.drawImage(herz1,970,30,this);
				g2.drawImage(herz2,1020, 30, this);}
			
			if(life==1.0){
				g2.drawImage(herz1,970,30,this);
			}
			if(life==0.5){
				g2.drawImage(herz2,970,30,this);}
			
		
			   String mes;
   		        g2.setFont(smallfont);																// Zeihcnet die Manatraenke
   		        g2.setColor(new Color(98,150,255));
   		        mes = "Manatraenke: " + (magic);
   		        g2.drawString(mes,970,300);
   		 
	        	if(mana==true){
	        		
	        		if(magic==1){																	
	        			g2.drawImage(trank, 970, 320, this);
	        		}
	        		if(magic==2){																
	        			g2.drawImage(trank,970,320,this);
	        			g2.drawImage(trank,1020,320, this);	
	        		}
	        	
	        		if(magic==3){																	
	        			g2.drawImage(trank,970,320,this);
	        			g2.drawImage(trank,1020, 320, this);
	        			g2.drawImage(trank,1070, 320, this);				  			
	        		}
	        	 
	        		if (magic > 3){			
	        			magic = 3;
	        		}
	        	}
	}else{
	}    
    Toolkit.getDefaultToolkit().sync();
    g2.dispose();
    }



    /**
     * Gibt die Schuesse wieder
     * @return Anzeige der Schuesse
     */
    public ArrayList<Shot> getShots() {
	        return shots;
	    }
    public ArrayList<Airshot> getAirshots() {
        return airshots;
    }
    
    public ArrayList<Earthshot> getEarthshots() {
        return earthshots;
    }
    
    public ArrayList<Iceshot> getIceshots() {
        return iceshots;
    }
   

    /**
     * Gibt die Schwerter wieder
     * @return Anzeige der Schwerter
     */
    public ArrayList<Sword> getSwords() {																
	     return swords;
    }
    public ArrayList<Icesword> getIceswords() {																
	     return iceswords;
   }
    public ArrayList<Earthsword> getEarthswords() {																
	     return earthswords;
  }
    public ArrayList<Airsword> getAirswords() {																
	     return airswords;
  }

    /**
     * Fuegt Tastenbefehle hinzu
     * Bei rechtem Tastendruck bewegt sich die Figur nach rechts und gibt das entsprechende Bild aus
     * Bei linken Tastendruck bewegt sich die Figur nach rechts und gibt das entsprechende Bild aus
     * Bei oberem Tastendruck bewegt sich die Figur nach rechts und gibt das entsprechende Bild aus
     * Bei unterem Tastendruck bewegt sich die Figur nach rechts und gibt das entsprechende Bild aus
     * Bei Tastendruck 'V' greift der Spieler mit dem Schwert an und gibt das entsprechende Bild in die richtige Richtung wieder
     * Bei Tastendruck 'Leertaste' schiesst der Spieler und gibt das entsprechende Bild wieder
     * Bei Tastendruck 'B' verbraucht der Spieler einen Manatrank, sofern er einen besitzt
     * Mit Taste 'X' tauscht er seine Schwerter
     * Mit Taste 'C' tauscht er seine Zauber
     * @author Tobias
     *
     */
	private class Ap extends KeyAdapter{


		public  void keyPressed(KeyEvent e){

			int key = e.getKeyCode();
			
			/*
			 * Hier wird ein Speicherbutton im Spiel aufgerufen, durch das Klicken auf 'F1'
			 */

			if(key == KeyEvent.VK_F1){
				/*
				
		        FileOutputStream fos = null;
				try {
					fos = new FileOutputStream("src/Resources/savedata.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(fos);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        Character one = new Character(1, '1'); // Die Elemente, die auf den constructor in Character zugreiffen
		        Character two = new Character(2, '2');
		       // Character three = new Character(3, '3');
		        
		        System.out.println("Speichern startet"); 
		        
		        try {
					oos.writeObject(one);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //Hier werde die Objekte gespeichert
		        try {
					oos.writeObject(two);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    
		        
		        System.out.println("Speichern beendet"); 
		        
		        
		        
		        try {
					oos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		        */
			
				
			final JFrame frame = new JFrame("Spiel speichern"); // Hier wird die Frame erstellt
	        frame.setLayout(new BorderLayout());
	        JButton save = new JButton("Speichern"); // Ab hier wird der Button an sich angezeigt, drunter kann man die Groe�e veraendern
	        save.setBounds(61, 558, 325, 53);
	        save.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent p) {
	             
	                JPanel fenster = new JPanel(); // Ab hier kann man auf den Button klicken
	                frame.add(fenster);
	                frame.validate();
	            }
	        });
	        frame.add(save, BorderLayout.NORTH);
	        frame.setBounds(0, 0, 200, 62);   // Das regelt die Groe�e des Frames
	        frame.setVisible(true);
				}
				

			if(key == KeyEvent.VK_N){
				faehigkeiten();
			}
			
			if(key == KeyEvent.VK_RIGHT){		

			if(get_sword==false && get_icesword==false && get_earthsword == false && get_airsword==false){
				Image image1 = image = r.getImage();
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0, image1);}
			else if(schwertchen==5){
				Image imagef1 = image = fr.getImage();
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0, imagef1);
			}
			
			else if(eisschwertchen==5){
				Image imagei1 = image = ir.getImage();
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0, imagei1);
			}
			
			else if(erdschwertchen==5){
				Image imagee1 = image = er.getImage();
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0, imagee1);
			}
			
			else if(luftschwertchen==5){
				Image imagea1 = image = ar.getImage();
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0, imagea1);
			}

			}else if(key == KeyEvent.VK_LEFT){

			if(get_sword==false && get_icesword==false && get_earthsword == false && get_airsword==false){	
				Image image2 = image = l.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0, image2);}
			else if(schwertchen==5){
				Image imagef2 = image = fl.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0, imagef2);
			}
			
			else if(eisschwertchen==5){
				Image imagei2 = image = il.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0, imagei2);
			}
			
			else if(erdschwertchen==5){
				Image imagee2 = image = el.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0, imagee2);
			}
			
			else if(luftschwertchen==5){
				Image imagea2 = image = al.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0, imagea2);
			}

			}
			else if(key == KeyEvent.VK_UP){

			if(get_sword==false && get_icesword==false && get_earthsword == false && get_airsword==false){	
				Image image3= image = t.getImage() ;
				Jay.setImage(image);
				position = 3;
				collision(0,-BLOCK,image3);}
			else if(schwertchen==5){
				Image imagef3 = image = ft.getImage();
				Jay.setImage(image);
				position = 3;
				collision(0, -BLOCK, imagef3);
			}
			
			else if(eisschwertchen==5){
				Image imagei3 = image = it.getImage();
				Jay.setImage(image);
				position = 3;
				collision(0, -BLOCK, imagei3);
			}
			
			else if(erdschwertchen==5){
				Image imagee3 = image = et.getImage();
				Jay.setImage(image);
				position = 3;
				collision(0, -BLOCK, imagee3);
			}
			
			else if(luftschwertchen==5){
				Image imagea3 = image = at.getImage();
				Jay.setImage(image);
				position = 3;
				collision(0, -BLOCK, imagea3);
			}

			}else if(key == KeyEvent.VK_DOWN){

			if(get_sword==false && get_icesword==false && get_earthsword == false && get_airsword==false){	
				Image image4 = image = b.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0,BLOCK, image4);}
			else if(schwertchen==5){
				Image imagef4 = image = fu.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0, BLOCK, imagef4);
			}
			
			else if(eisschwertchen==5){
				Image imagei4 = image = iu.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0, BLOCK, imagei4);
			}
			
			else if(erdschwertchen==5){
				Image imagee4 = image = eu.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0, BLOCK, imagee4);
			}
			
			else if(luftschwertchen==5){
				Image imagea4 = image = au.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0, BLOCK, imagea4);
			}

			}
			else if(key == KeyEvent.VK_B){

				if (magic == 3){
					magic = 2;
					manapoints = 100;
				}
				else if (magic == 2){
					magic = 1;
					manapoints = 100;
				}
				else if (magic == 1){
					magic = 0;
					manapoints = 100;
				}

			}
			else if(key == KeyEvent.VK_C){								// die vielen Bedingungen geben alle M�glichkeiten wieder Zauber im Inventar zu haben

			if(get_fireball==true && get_iceball==false && get_earthball==false && get_airball==false){
				feuerchen=5;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==false && get_airball==false){
				eischen=5;
			}
			else if(get_fireball==false && get_iceball==false && get_earthball==true && get_airball==false){
				erdchen=5;
			}
			else if(get_fireball==false && get_iceball==false && get_earthball==false && get_airball==true){
				luftchen=5;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==false && get_airball==false && feuerchen==5){
				feuerchen=0;
				eischen=5;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==false && get_airball==false && eischen==5){
				feuerchen=5;
				eischen=0;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==true && get_airball==false && feuerchen==5){
				feuerchen=0;
				erdchen=5;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==true && get_airball==false && erdchen==5){
				feuerchen=5;
				erdchen=0;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==false && get_airball==true && feuerchen==5){
				feuerchen=0;
				luftchen=5;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==false && get_airball==true && luftchen==5){
				feuerchen=5;
				luftchen=0;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==true && get_airball==false && eischen==5){
				eischen=0;
				erdchen=5;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==true && get_airball==false && erdchen==5){
				eischen=5;
				erdchen=0;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==false && get_airball==true && eischen==5){
				eischen=0;
				luftchen=5;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==false && get_airball==true && luftchen==5){
				eischen=5;
				luftchen=0;
			}
			else if(get_fireball==false && get_iceball==false && get_earthball==true && get_airball==true && erdchen==5){
				erdchen=0;
				luftchen=5;
			}
			else if(get_fireball==false && get_iceball==false && get_earthball==true && get_airball==true && luftchen==5){
				erdchen=5;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==false && feuerchen==5){
				feuerchen=0;
				eischen=5;
				erdchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==false && eischen==5){
				feuerchen=0;
				eischen=0;
				erdchen=5;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==false && erdchen==5){
				feuerchen=5;
				eischen=0;
				erdchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==false && get_airball==true && feuerchen==5){
				feuerchen=0;
				eischen=5;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==false && get_airball==true && eischen==5){
				feuerchen=0;
				eischen=0;
				luftchen=5;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==false && get_airball==true && luftchen==5){
				feuerchen=5;
				eischen=0;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==true && get_airball==true && feuerchen==5){
				feuerchen=0;
				erdchen=5;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==true && get_airball==true && erdchen==5){
				feuerchen=0;
				erdchen=0;
				luftchen=5;
			}
			else if(get_fireball==true && get_iceball==false && get_earthball==true && get_airball==true && luftchen==5){
				feuerchen=5;
				erdchen=0;
				luftchen=0;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==true && get_airball==true && eischen==5){
				eischen=0;
				erdchen=5;
				luftchen=0;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==true && get_airball==true && erdchen==5){
				eischen=0;
				erdchen=0;
				luftchen=5;
			}
			else if(get_fireball==false && get_iceball==true && get_earthball==true && get_airball==true && luftchen==5){
				eischen=5;
				erdchen=0;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==true && feuerchen==5){
				feuerchen=0;
				eischen=5;
				erdchen=0;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==true && eischen==5){
				feuerchen=0;
				eischen=0;
				erdchen=5;
				luftchen=0;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==true && erdchen==5){
				feuerchen=0;
				eischen=0;
				erdchen=0;
				luftchen=5;
			}
			else if(get_fireball==true && get_iceball==true && get_earthball==true && get_airball==true && luftchen==5){
				feuerchen=5;
				eischen=0;
				erdchen=0;
				luftchen=0;
			}

			}
			
			else if(key == KeyEvent.VK_X){								// die vielen Bedingungen geben alle M�glichkeiten wieder Schwerter im Inventar zu haben

				if(get_sword==true && get_icesword==false && get_earthsword==false && get_airsword==false){
					schwertchen=5;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==false && get_airsword==false){
					eisschwertchen=5;
				}
				else if(get_sword==false && get_icesword==false && get_earthsword==true && get_airsword==false){
					erdschwertchen=5;
				}
				else if(get_sword==false && get_icesword==false && get_earthsword==false && get_airsword==true){
					luftschwertchen=5;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==false && get_airsword==false && schwertchen==5){
					schwertchen=0;
					eisschwertchen=5;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==false && get_airsword==false && eisschwertchen==5){
					schwertchen=5;
					eisschwertchen=0;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==true && get_airsword==false && schwertchen==5){
					schwertchen=0;
					erdschwertchen=5;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==true && get_airsword==false && erdschwertchen==5){
					schwertchen=5;
					erdschwertchen=0;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==false && get_airsword==true && schwertchen==5){
					schwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==false && get_airsword==true && luftschwertchen==5){
					schwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==true && get_airsword==false && eisschwertchen==5){
					eisschwertchen=0;
					erdschwertchen=5;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==true && get_airsword==false && erdschwertchen==5){
					eisschwertchen=5;
					erdschwertchen=0;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==false && get_airsword==true && eisschwertchen==5){
					eisschwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==false && get_airsword==true && luftschwertchen==5){
					eisschwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==false && get_icesword==false && get_earthsword==true && get_airsword==true && erdschwertchen==5){
					erdschwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==false && get_icesword==false && get_earthsword==true && get_airsword==true && luftschwertchen==5){
					erdschwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==false && schwertchen==5){
					schwertchen=0;
					eisschwertchen=5;
					erdschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==false && eisschwertchen==5){
					schwertchen=0;
					eisschwertchen=0;
					erdschwertchen=5;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==false && erdschwertchen==5){
					schwertchen=5;
					eisschwertchen=0;
					erdschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==false && get_airsword==true && schwertchen==5){
					schwertchen=0;
					eisschwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==false && get_airsword==true && eisschwertchen==5){
					schwertchen=0;
					eisschwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==false && get_airsword==true && luftschwertchen==5){
					schwertchen=5;
					eisschwertchen=0;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==true && get_airsword==true && schwertchen==5){
					schwertchen=0;
					erdschwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==true && get_airsword==true && erdschwertchen==5){
					schwertchen=0;
					erdschwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==true && get_icesword==false && get_earthsword==true && get_airsword==true && luftschwertchen==5){
					schwertchen=5;
					erdschwertchen=0;
					luftschwertchen=0;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==true && get_airsword==true && eisschwertchen==5){
					eisschwertchen=0;
					erdschwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==true && get_airsword==true && erdschwertchen==5){
					eisschwertchen=0;
					erdschwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==false && get_icesword==true && get_earthsword==true && get_airsword==true && luftschwertchen==5){
					eisschwertchen=5;
					erdschwertchen=0;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==true && schwertchen==5){
					schwertchen=0;
					eisschwertchen=5;
					erdschwertchen=0;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==true && eisschwertchen==5){
					schwertchen=0;
					eisschwertchen=0;
					erdschwertchen=5;
					luftschwertchen=0;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==true && erdschwertchen==5){
					schwertchen=0;
					eisschwertchen=0;
					erdschwertchen=0;
					luftschwertchen=5;
				}
				else if(get_sword==true && get_icesword==true && get_earthsword==true && get_airsword==true && luftschwertchen==5){
					schwertchen=5;
					eisschwertchen=0;
					erdschwertchen=0;
					luftschwertchen=0;
				}

				}
			
			else if (key == KeyEvent.VK_SPACE && feuerchen==5) {	
				if (manapoints >= 5){																// Taste -Space ruft die Schussfunktion auf
				fire();
				manapoints = manapoints - 5;
				}
				
			}else if (key == KeyEvent.VK_SPACE  && eischen==5) {	
				if (manapoints >= 5){															
				ice();
				manapoints = manapoints - 5;
					}

	
			}
			else if (key == KeyEvent.VK_SPACE && luftchen==5) {	
				if (manapoints >= 5){															
				air();
				manapoints = manapoints - 5;
				}
				
			}
			else if (key == KeyEvent.VK_SPACE && erdchen==5) {	
				if (manapoints >= 5){															
				earth();
				manapoints = manapoints - 5;
				}
				
			}
			
			else if (key == KeyEvent.VK_V && schwertchen==5) {										// Charakter schlaegt mit dem Schwert zu
				sword_play();
			}
			
			else if (key == KeyEvent.VK_V && eisschwertchen==5) {								
				icesword_play();
			}
			
			else if (key == KeyEvent.VK_V && erdschwertchen==5) {								
				earthsword_play();
			}
			
			else if (key == KeyEvent.VK_V && luftschwertchen==5) {								
				airsword_play();
			}

			repaint();
			
			
			/**
			 * Gibt den Wechsel in die naechsten Raeume wieder
			 */
			if ((Jay.getY()==-BLOCK)||(Jay.getY()==0))  {	
				if (lr.charAt(3)=='1') lr=lr.substring(0,3)+"2";
				else if (lr.charAt(3)=='2') lr=lr.substring(0,3)+'3';
				else if (lr.charAt(3)=='3') lr=lr.substring(0,3)+'4';
				else if (lr.charAt(3)=='4') lr=lr.substring(0,3)+'5';
				
				xruban=xruban+ruban;
				xlife=xlife+life;
				ruban=0;
				loeschen(true);
				try {
					initWorld(Jay.getImage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (Jay.getX() ==950 ){
				if (lr.charAt(3)=='1') lr=lr.substring(0,3)+"2";
				else if (lr.charAt(3)=='2') lr=lr.substring(0,3)+'3';
				else if (lr.charAt(3)=='3') lr=lr.substring(0,3)+'4';
				else if (lr.charAt(3)=='4') lr=lr.substring(0,3)+'5';
				xruban=xruban+ruban;
				xlife=xlife+life;
				ruban=0;
				loeschen(true);
				try {
					initWorld(Jay.getImage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Neustart des Levels
	 * @param test	Loescht die dagewesenen Elemente
	 * @param image2 Neue Zeichnung des Levels
	 * @throws IOException
	 */
	public void restartLevel(boolean test,Image image2) throws IOException {			
		loeschen(test);
		initWorld(Jay.getImage());
	}

	 public void fire() {
		 	if(position==1){
		 		shots.add(new Shot(Jay.getX() + BLOCK, Jay.getY()));			// Posistion der Schussrichtung, je in welche Richtung Diggy guckt
		 		k = 00;}
		 	if(position==2){													// der Schuss soll nicht ueber Diggy gehen 
		 		shots.add(new Shot(Jay.getX() - BLOCK, Jay.getY()));			// k als Flag
		 		k = 01;}
		 	if(position==3){
		 		shots.add(new Shot(Jay.getX(), Jay.getY() - BLOCK));
		 		k = 10;}
		 	if(position==4){
		 		shots.add(new Shot(Jay.getX(), Jay.getY() + BLOCK));	
		 	    k = 11;}
	}
	 
	 public void air() {
		 	if(position==1){
		 		airshots.add(new Airshot(Jay.getX() + BLOCK, Jay.getY()));			// Posistion der Schussrichtung, je in welche Richtung Diggy guckt
		 		k = 00;}
		 	if(position==2){														// der Schuss soll nicht ueber Diggy gehen 
		 		airshots.add(new Airshot(Jay.getX() - BLOCK, Jay.getY()));			// k als Flag
		 		k = 01;}
		 	if(position==3){
		 		airshots.add(new Airshot(Jay.getX(), Jay.getY() - BLOCK));
		 		k = 10;}
		 	if(position==4){
		 		airshots.add(new Airshot(Jay.getX(), Jay.getY() + BLOCK));	
		 	    k = 11;}
	}
	 
	 public void earth() {
		 	if(position==1){
		 		earthshots.add(new Earthshot(Jay.getX() + BLOCK, Jay.getY()));			// Posistion der Schussrichtung, je in welche Richtung Diggy guckt
		 		k = 00;}
		 	if(position==2){															// der Schuss soll nicht ueber Diggy gehen 
		 		earthshots.add(new Earthshot(Jay.getX() - BLOCK, Jay.getY()));			// k als Flag
		 		k = 01;}
		 	if(position==3){
		 		earthshots.add(new Earthshot(Jay.getX(), Jay.getY() - BLOCK));
		 		k = 10;}
		 	if(position==4){
		 		earthshots.add(new Earthshot(Jay.getX(), Jay.getY() + BLOCK));	
		 	    k = 11;}
	}
	 public void ice() {
		 	if(position==1){
		 		iceshots.add(new Iceshot(Jay.getX() + BLOCK, Jay.getY()));			// Posistion der Schussrichtung, je in welche Richtung Diggy guckt
		 		k = 00;}
		 	if(position==2){														// der Schuss soll nicht ueber Diggy gehen 
		 		iceshots.add(new Iceshot(Jay.getX() - BLOCK, Jay.getY()));			// k als Flag
		 		k = 01;}
		 	if(position==3){
		 		iceshots.add(new Iceshot(Jay.getX(), Jay.getY() - BLOCK));
		 		k = 10;}
		 	if(position==4){
		 		iceshots.add(new Iceshot(Jay.getX(), Jay.getY() + BLOCK));	
		 	    k = 11;}
	}
	 
	 public void sword_play(){													// Schwertkampf mit 4 Richtungen zum angreifen				
	 
	 	 	if(position==1){																		
		 		image = dr.getImage();
				Jay.setImage(image);
				swords.add(new Sword(Jay.getX() + 2, Jay.getY()));				// Posistion der Schwertrichtung, je in welche Richtung Diggy guckt
		 		z = 00;}														// tot nach 2 Entfernung mit Schwert
		 	if(position==2){			
		 		image = dl.getImage();
				Jay.setImage(image);																	
			 	swords.add(new Sword(Jay.getX(), Jay.getY()));					// z als Flag fuer die Richtungen des Angriffs
			 	z = 01;}
		 	if(position==3){
		 		image = du.getImage();
				Jay.setImage(image);
				swords.add(new Sword(Jay.getX(), Jay.getY()));
		 		z = 10;}
			if(position==4){
		 		image = db.getImage();
				Jay.setImage(image);
				swords.add(new Sword(Jay.getX(), Jay.getY() + 2));	
		 	    z = 11;}
		 	}
	 
	 public void icesword_play(){														// Schwertkampf mit 4 Richtungen angreifen				
		 
	 	 	if(position==1){																		
		 		image = dr.getImage();
				Jay.setImage(image);
				iceswords.add(new Icesword(Jay.getX() + 2, Jay.getY()));				// Posistion der Schwertrichtung, je in welche Richtung Diggy guckt
		 		z = 00;}																// tot nach 2 Entfernung mit Schwert
		 	if(position==2){			
		 		image = dl.getImage();
				Jay.setImage(image);																	
			 	iceswords.add(new Icesword(Jay.getX(), Jay.getY()));					// z als Flag fuer die Richtungen des Angriffs
			 	z = 01;}
		 	if(position==3){
		 		image = du.getImage();
				Jay.setImage(image);
				iceswords.add(new Icesword(Jay.getX(), Jay.getY()));
		 		z = 10;}
			if(position==4){
		 		image = db.getImage();
				Jay.setImage(image);
				iceswords.add(new Icesword(Jay.getX(), Jay.getY() + 2));	
		 	    z = 11;}
		 	}
	 
	 public void earthsword_play(){															// Schwertkampf mit 4 Richtungen zum angreifen			
		 
	 	 	if(position==1){																		
		 		image = dr.getImage();
				Jay.setImage(image);
				earthswords.add(new Earthsword(Jay.getX() + 2, Jay.getY()));				// Posistion der Schwertrichtung, je in welche Richtung Diggy guckt
		 		z = 00;}																	// tot nach 2 Entfernung mit Schwert
		 	if(position==2){			
		 		image = dl.getImage();
				Jay.setImage(image);																	
			 	earthswords.add(new Earthsword(Jay.getX(), Jay.getY()));					// z als Flag fuer die Richtungen des Angriffs
			 	z = 01;}
		 	if(position==3){
		 		image = du.getImage();
				Jay.setImage(image);
				earthswords.add(new Earthsword(Jay.getX(), Jay.getY()));
		 		z = 10;}
			if(position==4){
		 		image = db.getImage();
				Jay.setImage(image);
				earthswords.add(new Earthsword(Jay.getX(), Jay.getY() + 2));	
		 	    z = 11;}
		 	}
	 	
	 public void airsword_play(){														// Schwertkampf mit 4 Richtungen zum angreifen				
		 
	 	 	if(position==1){																		
		 		image = dr.getImage();
				Jay.setImage(image);
				airswords.add(new Airsword(Jay.getX() + 2, Jay.getY()));				// Posistion der Schwertrichtung, je in welche Richtung Diggy guckt
		 		z = 00;}																// tot nach 2 Entfernung mit Schwert
		 	if(position==2){			
		 		image = dl.getImage();
				Jay.setImage(image);																	
			 	airswords.add(new Airsword(Jay.getX(), Jay.getY()));					// z als Flag fuer die Richtungen des Angriffs
			 	z = 01;}
		 	if(position==3){
		 		image = du.getImage();
				Jay.setImage(image);
				airswords.add(new Airsword(Jay.getX(), Jay.getY()));
		 		z = 10;}
			if(position==4){
		 		image = db.getImage();
				Jay.setImage(image);
				airswords.add(new Airsword(Jay.getX(), Jay.getY() + 2));	
		 	    z = 11;}
		 	}

	 /**
	  * Fuehrt die Schuesse aus
	  */
	 @Override
	 public void actionPerformed(ActionEvent e) {

     ArrayList<Shot> shots = getShots();
     
     for (int i = 0; i < shots.size(); i++) {
    	 Shot m = (Shot) shots.get(i);

    	 if(m.isVisible()){	 	
		 					
			if(k==00) m.move_r();
			if(k==01) m.move_l();
			if(k==10) m.move_u();
			if(k==11) m.move_d();
			
			
		}else shots.remove(i);
    	 	check_shot_vs_wall();															// Kollisionabfrage mit Schuss
			check_shot_vs_enemy();
			check_shot_vs_coin();
			//check_shot_vs_ghost();
			if (raum.contains("k")){
				check_shot_vs_boss();
			}
	}
     
     ArrayList<Airshot> airshots = getAirshots();
     
     for (int i = 0; i < airshots.size(); i++) {
    	 Airshot m = (Airshot) airshots.get(i);

    	 if(m.isVisible()){	 	
		 					
			if(k==00) m.move_r();
			if(k==01) m.move_l();
			if(k==10) m.move_u();
			if(k==11) m.move_d();
			
			
		}else airshots.remove(i);
    	 	check_shot_vs_wall();															// Kollisionabfrage mit Schuss
			check_shot_vs_enemy();
			check_shot_vs_coin();
			//check_shot_vs_ghost();
			if (raum.contains("k")){
				check_shot_vs_boss();
			}
	}

     ArrayList<Earthshot> earthshots = getEarthshots();
     
     for (int i = 0; i < earthshots.size(); i++) {
    	 Earthshot m = (Earthshot) earthshots.get(i);

    	 if(m.isVisible()){	 	
		 					
			if(k==00) m.move_r();
			if(k==01) m.move_l();
			if(k==10) m.move_u();
			if(k==11) m.move_d();
			
			
		}else earthshots.remove(i);
    	 	check_shot_vs_wall();															// Kollisionabfrage mit Schuss
			check_shot_vs_enemy();
			check_shot_vs_coin();
			//check_shot_vs_ghost();
			if (raum.contains("k")){
				check_shot_vs_boss();
			}
	}
     
     ArrayList<Iceshot> iceshots = getIceshots();
     
     for (int x = 0; x < iceshots.size(); x++) {
    	 Iceshot d = (Iceshot) iceshots.get(x);

    	 if(d.isVisible()){	 	
		 					
			if(k==00) d.move_r();
			if(k==01) d.move_l();
			if(k==10) d.move_u();
			if(k==11) d.move_d();
			
			
		}else iceshots.remove(x);
    	 	check_shot_vs_wall();															// Kollisionabfrage mit Schuss
			check_shot_vs_enemy();
			check_shot_vs_coin();
			//check_shot_vs_ghost();
			if (raum.contains("k")){
				check_shot_vs_boss();
			}
	}
			 ArrayList<Sword> swords = getSwords();
			 ArrayList<Icesword> iceswords = getIceswords();
			 ArrayList<Earthsword> earthswords = getEarthswords();
			 ArrayList<Airsword> airswords = getAirswords();
			 
			 for (int j = 0; j < swords.size(); j++) {										// fuer den Schwertkampf mit versch Bildern und Angriffsrichtungen
				 Sword s = (Sword) swords.get(j);
				 
				 		if(s.isVisible()){
							
				 			if(z==00) s.move_r_sword();
				 			if(z==01) s.move_l_sword();
				 			if(z==10) s.move_u_sword();
				 			if(z==11) s.move_d_sword();
							
						}else swords.remove(j);
				 			check_sword_vs_wall();											// Kollision mit Schwertangriff 
				 			check_sword_vs_enemy();
				 			check_sword_vs_coin();
	 		}	
			 
			 for (int j = 0; j < iceswords.size(); j++) {									// fuer den Schwertkampf mit versch Bildern und Angriffsrichtungen
				 Icesword s = (Icesword) iceswords.get(j);
				 
				 		if(s.isVisible()){
							
				 			if(z==00) s.move_r_sword();
				 			if(z==01) s.move_l_sword();
				 			if(z==10) s.move_u_sword();
				 			if(z==11) s.move_d_sword();
							
						}else iceswords.remove(j);
				 			check_sword_vs_wall();											// Kollision mit Schwertangriff 
				 			check_sword_vs_enemy();
				 			check_sword_vs_coin();
	 		}
			 
			 for (int j = 0; j < earthswords.size(); j++) {									// fuer den Schwertkampf mit versch Bildern und Angriffsrichtungen
				 Earthsword s = (Earthsword) earthswords.get(j);
				 
				 		if(s.isVisible()){
							
				 			if(z==00) s.move_r_sword();
				 			if(z==01) s.move_l_sword();
				 			if(z==10) s.move_u_sword();
				 			if(z==11) s.move_d_sword();
							
						}else earthswords.remove(j);
				 			check_sword_vs_wall();											// Kollision mit Schwertangriff 
				 			check_sword_vs_enemy();
				 			check_sword_vs_coin();
	 		}
			 
			 for (int j = 0; j < airswords.size(); j++) {									// fuer den Schwertkampf mit versch Bildern und Angriffsrichtungen
				 Airsword s = (Airsword) airswords.get(j);
				 
				 		if(s.isVisible()){
							
				 			if(z==00) s.move_r_sword();
				 			if(z==01) s.move_l_sword();
				 			if(z==10) s.move_u_sword();
				 			if(z==11) s.move_d_sword();
							
						}else airswords.remove(j);
				 			check_sword_vs_wall();											// Kollision mit Schwertangriff 
				 			check_sword_vs_enemy();
				 			check_sword_vs_coin();
	 		}
			 repaint();	
	}
		 
		public Rectangle getBounds(){
			return new Rectangle(Jay.getX(),Jay.getY(),50,50);				
		}

		/**
		 * Definiert das Aussehen des Frames fuer den NPC-Dialog
		 */
		public void Dialogue(){

			JFrame Dialogue = new Dialogue("Weiser Zauberer");

			Dialogue.setSize(600,300);
			Dialogue.setLocationRelativeTo(null);
			Dialogue.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			Dialogue.setVisible(true);
			Dialogue.setFocusable(true);
			Dialogue.setLayout(new BorderLayout());
			Dialogue.setLayout(null);
			Dialogue.add(new Dialogue("Weiser Zauberer"));
		}
		
		public void faehigkeiten(){
			JFrame Faehigkeiten = new Faehigkeiten("Faehigkeiten");
			
			Faehigkeiten.setSize(600, 600);
			Faehigkeiten.setLocationRelativeTo(null);
			Faehigkeiten.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			Faehigkeiten.setVisible(true);
			Faehigkeiten.setFocusable(true);
			Faehigkeiten.setLayout(new BorderLayout());
			Faehigkeiten.setLayout(null);
			Faehigkeiten.add(new Dialogue("Faehigkeiten"));
		}

		/**
		 * Definiert das Aussehen des Frames fuer den ShopDialog
		 */
		public void DialogueShop(){

			JFrame DialogueShop = new DialogueShop("Ladenbesitzer");
			DialogueShop.setSize(600,300);
			DialogueShop.setLocationRelativeTo(null);
			DialogueShop.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			DialogueShop.setVisible(true);
			DialogueShop.setFocusable(true);
			DialogueShop.setLayout(new BorderLayout());     
			DialogueShop.setLayout(null);
			DialogueShop.add(new Dialogue("Ladenbesitzer"));
	    }
	    public void Dialogueschild(){

			JFrame Dialogueschild = new Dialogueschild("Schild");
			Dialogueschild.setSize(600,300);
			Dialogueschild.setLocationRelativeTo(null);
			Dialogueschild.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			Dialogueschild.setVisible(true);
			Dialogueschild.setFocusable(true);
			Dialogueschild.setLayout(new BorderLayout());     
			Dialogueschild.setLayout(null);
			Dialogueschild.add(new Dialogue("Schild"));
	    }

		public void DialogueRätsel(){

			JFrame DialogueRaetsel = new DialogueRaetsel("Raetsel");
			DialogueRaetsel.setSize(600,300);
			DialogueRaetsel.setLocationRelativeTo(null);
			DialogueRaetsel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			DialogueRaetsel.setVisible(true);
			DialogueRaetsel.setFocusable(true);
			DialogueRaetsel.setLayout(new BorderLayout());     
			DialogueRaetsel.setLayout(null);
			DialogueRaetsel.add(new Dialogue("Rätsel"));
	    }

		/**
		 * Kollisionsabfrage: Schuss vs Gold
		 */
		public void check_shot_vs_coin() {

			ArrayList<Shot> shots = getShots();
			ArrayList<Iceshot> iceshots = getIceshots();
			ArrayList<Airshot> airshots = getAirshots();
			ArrayList<Earthshot> earthshots = getEarthshots();

			if(feuerchen==5){
			    for (int i = 0; i < shots.size(); i++) {
			        Shot m = (Shot) shots.get(i);

			        Rectangle r1 = m.getBounds();

			        for (int j = 0; j < coins.size(); j++) {
				        Coin c = (Coin) coins.get(j);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                m.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
			    }
			
			if(erdchen==5){
			    for (int i = 0; i < earthshots.size(); i++) {
			        Earthshot m = (Earthshot) earthshots.get(i);

			        Rectangle r1 = m.getBounds();

			        for (int j = 0; j < coins.size(); j++) {
				        Coin c = (Coin) coins.get(j);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                m.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
			    }
			
			if(luftchen==5){
			    for (int i = 0; i < airshots.size(); i++) {
			        Airshot m = (Airshot) airshots.get(i);

			        Rectangle r1 = m.getBounds();

			        for (int j = 0; j < coins.size(); j++) {
				        Coin c = (Coin) coins.get(j);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                m.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
			    }
			    
			if(eischen==5){
			    for (int i = 0; i < iceshots.size(); i++) {
			        Iceshot m = (Iceshot) iceshots.get(i);

			        Rectangle r1 = m.getBounds();

			        for (int j = 0; j < coins.size(); j++) {
				        Coin c = (Coin) coins.get(j);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                m.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
			}
			}
		
		/**
		 * Kollisionsabfrage: Schwert vs Gold
		 */
		public void check_sword_vs_coin() {										

			 ArrayList<Sword> swords = getSwords();
			 ArrayList<Icesword> iceswords = getIceswords();
			 ArrayList<Earthsword> earthswords = getEarthswords();
			 ArrayList<Airsword> airswords = getAirswords();
			 
				for (int j = 0; j < swords.size(); j++) {										
					Sword s = (Sword) swords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< coins.size(); i++) {
				        Coin c = (Coin) coins.get(i);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
				
				for (int j = 0; j < iceswords.size(); j++) {										
					Icesword s = (Icesword) iceswords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< coins.size(); i++) {
				        Coin c = (Coin) coins.get(i);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
				
				for (int j = 0; j < earthswords.size(); j++) {										
					Earthsword s = (Earthsword) earthswords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< coins.size(); i++) {
				        Coin c = (Coin) coins.get(i);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
				
				for (int j = 0; j < airswords.size(); j++) {										
					Airsword s = (Airsword) airswords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< coins.size(); i++) {
				        Coin c = (Coin) coins.get(i);
				        Rectangle r2 = c.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                c.setVisible(true);
			            }
			        }
			    }
			}
		
		/**
		 * Kollisionsabfrage: Schwert vs Mauer
		 */
		public void check_sword_vs_wall() {																

			 ArrayList<Sword> swords = getSwords();
			 ArrayList<Icesword> iceswords = getIceswords();
			 ArrayList<Earthsword> earthswords = getEarthswords();
			 ArrayList<Airsword> airswords = getAirswords();
			 
				for (int j = 0; j < swords.size(); j++) {										
					Sword s = (Sword) swords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< walls.size(); i++) {
				        Wall w = (Wall) walls.get(i);
				        Rectangle r2 = w.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                w.setVisible(true);
			            }
			        }
			    }
				
				for (int j = 0; j < iceswords.size(); j++) {										
					Icesword s = (Icesword) iceswords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< walls.size(); i++) {
				        Wall w = (Wall) walls.get(i);
				        Rectangle r2 = w.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                w.setVisible(true);
			            }
			        }
			    }
				
				for (int j = 0; j < earthswords.size(); j++) {										
					Earthsword s = (Earthsword) earthswords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< walls.size(); i++) {
				        Wall w = (Wall) walls.get(i);
				        Rectangle r2 = w.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                w.setVisible(true);
			            }
			        }
			    }
				
				for (int j = 0; j < airswords.size(); j++) {										
					Airsword s = (Airsword) airswords.get(j);

			        Rectangle r1 = s.getBounds();

			        for (int i = 0; i< walls.size(); i++) {
				        Wall w = (Wall) walls.get(i);
				        Rectangle r2 = w.getBounds();

			            if (r1.intersects(r2)) {
			                s.setVisible(false);
			                w.setVisible(true);
			            }
			        }
			    }
			}

		
		private int boss_leben;
		private int ghost_leben;  //Ghost Kollision
		
		/**
		 * Kollisionsabfrage: Schuss vs Boss
		 */
		
		/*public void check_shot_vs_ghost() {																
			ArrayList<Shot> shots = getShots();
			//ArrayList<Iceshot> iceshots = getIceshots();
			ArrayList<Airshot> airshots = getAirshots();
			//ArrayList<Earthshot> earthshots = getEarthshots();

			if(feuerchen==5){
		    for (int i = 0; i < shots.size(); i++) {
		        Shot m = (Shot) shots.get(i);
		        Rectangle r1 = m.getBounds();
		        
		        if ((Math.abs(r1.x-Geist.getX())<50)&&(Math.abs(r1.y-Geist.getY())<50)) {													 		
		        	world.remove(Geist);
		        	ghost_leben=ghost_leben-1;
		        		if (ghost_leben==0){
		        			
		        			//exp = exp + 5;
		        			//if(manaleech == true)manapoints = manapoints + 3;
		        			//if(lifeleech == true && life <=2.5)life = life + 0.5;
		        			//if (lr.charAt(3)=='5')lr=lr.substring(0,3)+'6';
		        			//else if (lr.charAt(3)=='4')lr=lr.substring(0, 3)+'5';
		        			test = true;
		        			world.remove(Geist);
		        			
		        			
		        		}
		        }
		    }
			}	
			if(elementarmeister==true){
				if(luftchen==5){
				    for (int i = 0; i < airshots.size(); i++) {
				        Airshot m = (Airshot) airshots.get(i);
				        Rectangle r1 = m.getBounds();
				        
				        if ((Math.abs(r1.x-Geist.getX())<50)&&(Math.abs(r1.y-Geist.getY())<50)) {													 		
				        	ghost_leben=ghost_leben-1;
				        		if (ghost_leben==0){
				        			exp = exp + 20;
				        			if(manaleech == true)manapoints = manapoints + 3;
				        			if(lifeleech == true && life <=2.5)life = life + 0.5;
				        			if (lr.charAt(3)=='5')lr=lr.substring(0,3)+'6';
				        			else if (lr.charAt(3)=='4')lr=lr.substring(0, 3)+'5';
				        			loeschen(true);
				        			try {
				        				initWorld(Jay.getImage());
				        			} catch (IOException e1) {

				        				e1.printStackTrace();
				        			}
				        		}
				        }
			        }
					}}
		}*/
		
		public void check_shot_vs_boss() {																
			ArrayList<Shot> shots = getShots();
			ArrayList<Iceshot> iceshots = getIceshots();
			ArrayList<Airshot> airshots = getAirshots();
			ArrayList<Earthshot> earthshots = getEarthshots();

			if(feuerchen==5){
		    for (int i = 0; i < shots.size(); i++) {
		        Shot m = (Shot) shots.get(i);
		        Rectangle r1 = m.getBounds();
		        
		        if ((Math.abs(r1.x-Monster.getX())<50)&&(Math.abs(r1.y-Monster.getY())<50)) {													 		
		        		boss_leben=boss_leben-1;
		        		if (boss_leben==0){
		        			exp = exp + 20;
		        			if(manaleech == true)manapoints = manapoints + 3;
		        			if(lifeleech == true && life <=2.5)life = life + 0.5;
		        			if (lr.charAt(3)=='5')lr=lr.substring(0,3)+'6';
		        			else if (lr.charAt(3)=='4')lr=lr.substring(0, 3)+'5';
		        			loeschen(true);
		        			try {
		        				initWorld(Jay.getImage());
		        			} catch (IOException e1) {

		        				e1.printStackTrace();
		        			}
		        		}
		        }
	        }
			}
			
			if(elementarmeister==true){
			if(luftchen==5){
			    for (int i = 0; i < airshots.size(); i++) {
			        Airshot m = (Airshot) airshots.get(i);
			        Rectangle r1 = m.getBounds();
			        
			        if ((Math.abs(r1.x-Monster.getX())<50)&&(Math.abs(r1.y-Monster.getY())<50)) {													 		
			        		boss_leben=boss_leben-1;
			        		if (boss_leben==0){
			        			exp = exp + 20;
			        			if(manaleech == true)manapoints = manapoints + 3;
			        			if(lifeleech == true && life <=2.5)life = life + 0.5;
			        			if (lr.charAt(3)=='5')lr=lr.substring(0,3)+'6';
			        			else if (lr.charAt(3)=='4')lr=lr.substring(0, 3)+'5';
			        			loeschen(true);
			        			try {
			        				initWorld(Jay.getImage());
			        			} catch (IOException e1) {

			        				e1.printStackTrace();
			        			}
			        		}
			        }
		        }
				}}
			
			
			if(elementarmeister==true){
			if(erdchen==5){
			    for (int i = 0; i < earthshots.size(); i++) {
			        Earthshot m = (Earthshot) earthshots.get(i);
			        Rectangle r1 = m.getBounds();
			        
			        if ((Math.abs(r1.x-Monster.getX())<50)&&(Math.abs(r1.y-Monster.getY())<50)) {													 		
			        		boss_leben=boss_leben-1;
			        		if (boss_leben==0){
			        			exp = exp + 20;
			        			if(manaleech == true)manapoints = manapoints + 3;
			        			if(lifeleech == true && life <=2.5)life = life + 0.5;
			        			if (lr.charAt(3)=='5')lr=lr.substring(0,3)+'6';
			        			else if (lr.charAt(3)=='4')lr=lr.substring(0, 3)+'5';
			        			loeschen(true);
			        			try {
			        				initWorld(Jay.getImage());
			        			} catch (IOException e1) {

			        				e1.printStackTrace();
			        			}
			        		}
			        }
		        }
				}}
			
			
			if(elementarmeister==true){
			if(eischen==5){
			    for (int i = 0; i < iceshots.size(); i++) {
			        Iceshot m = (Iceshot) iceshots.get(i);
			        Rectangle r1 = m.getBounds();
			        
			        if ((Math.abs(r1.x-Monster.getX())<50)&&(Math.abs(r1.y-Monster.getY())<50)) {													 		
			        		boss_leben=boss_leben-1;
			        		if (boss_leben==0){
			        			exp = exp +20;
			        			if(manaleech == true)manapoints = manapoints + 3;
			        			if(lifeleech == true && life <=2.5)life = life + 0.5;
			        			if (lr.charAt(3)=='5')lr=lr.substring(0,3)+'6';
			        			else if (lr.charAt(3)=='4')lr=lr.substring(0, 3)+'5';
			        			loeschen(true);
			        			try {
			        				initWorld(Jay.getImage());
			        			} catch (IOException e1) {

			        				e1.printStackTrace();
			        			}
			        		}
			        }
		        }
				}}

		}
		
		/**
		 * Kollisionsabfrage: Schuss vs Mauer
		 */
		public void check_shot_vs_wall() {

			ArrayList<Shot> shots = getShots();
			ArrayList<Iceshot> iceshots = getIceshots();
			ArrayList<Airshot> airshots = getAirshots();
			ArrayList<Earthshot> earthshots = getEarthshots();

			if(feuerchen ==5){
		    for (int i = 0; i < shots.size(); i++) {
		        Shot m = (Shot) shots.get(i);

		        Rectangle r1 = m.getBounds();

		        for (int j = 0; j < walls.size(); j++) {
			        Wall w = (Wall) walls.get(j);
			        Rectangle r2 = w.getBounds();

		            if (r1.intersects(r2)) {													//Schuss ist auf der Wand nicht sichtbar
		                m.setVisible(false);
		                w.setVisible(true);

		             }
		        }
		    }
			}
			
			if(luftchen ==5){
			    for (int i = 0; i < airshots.size(); i++) {
			        Airshot m = (Airshot) airshots.get(i);

			        Rectangle r1 = m.getBounds();

			        for (int j = 0; j < walls.size(); j++) {
				        Wall w = (Wall) walls.get(j);
				        Rectangle r2 = w.getBounds();

			            if (r1.intersects(r2)) {													//Schuss ist auf der Wand nicht sichtbar
			                m.setVisible(false);
			                w.setVisible(true);

			             }
			        }
			    }
				}
			
			if(erdchen ==5){
			    for (int i = 0; i < earthshots.size(); i++) {
			        Earthshot m = (Earthshot) earthshots.get(i);

			        Rectangle r1 = m.getBounds();

			        for (int j = 0; j < walls.size(); j++) {
				        Wall w = (Wall) walls.get(j);
				        Rectangle r2 = w.getBounds();

			            if (r1.intersects(r2)) {													//Schuss ist auf der Wand nicht sichtbar
			                m.setVisible(false);
			                w.setVisible(true);

			             }
			        }
			    }
				}
			
			if (eischen == 5){
		    for (int i = 0; i < iceshots.size(); i++) {
		        Iceshot m = (Iceshot) iceshots.get(i);

		        Rectangle r1 = m.getBounds();

		        for (int j = 0; j < walls.size(); j++) {
			        Wall w = (Wall) walls.get(j);
			        Rectangle r2 = w.getBounds();

		            if (r1.intersects(r2)) {
		                m.setVisible(false);
		                w.setVisible(true);
		            }
		        }
		    }
			}
		}
	

		/**
		 * Kollisionsabfrage: Schuss vs Gegner
		 */
		public void check_shot_vs_enemy() {								

			if(elementarmeister==true){
			if(feuerchen==5){
			for (int i = 0; i < shots.size(); i++) {
		        Shot m = (Shot) shots.get(i);
		        Rectangle r1 = m.getBounds();
		  
		   	      	int xx = (int) ((r1.getX())/BLOCK);																	
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {										// Ersetzt in der .txt datei enemy mit ' '																							
		        		int xxx = ((Jay.getX())/BLOCK);																	
		        		int yyy=(Jay.getY())/BLOCK;	
		        		exp = exp +1;
		        		if(manaleech == true)manapoints = manapoints + 3;
	        			if(lifeleech == true && life <=2.5)life = life + 0.5;

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					} catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        		}
		
		        }
			}}
			
			
			if(elementarmeister==true){
			if(luftchen==5){
				for (int i = 0; i < airshots.size(); i++) {
			        Airshot m = (Airshot) airshots.get(i);
			        Rectangle r1 = m.getBounds();
			  
			   	      	int xx = (int) ((r1.getX())/BLOCK);																	
		        		int yy=(int)(r1.getY())/BLOCK;

		        		if (raum.charAt(yy*20+xx)=='*') {										// Ersetzt in der .txt datei enemy mit ' '																							
			        		int xxx = ((Jay.getX())/BLOCK);																	
			        		int yyy=(Jay.getY())/BLOCK;	
			        		exp = exp+1;
			        		if(manaleech == true)manapoints = manapoints + 3;
		        			if(lifeleech == true && life <=2.5)life = life + 0.5;

			        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
			        				int c =raum.lastIndexOf("@");						
			    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
			    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
			    					try {
			    						restartLevel(false, (Jay.getImage()));
			    					} catch (IOException e1) {
			    						e1.printStackTrace();
			    					}
		        		}
			
			        }
				}}
			
			if(elementarmeister==true){
			if(erdchen==5){
				for (int i = 0; i < earthshots.size(); i++) {
			        Earthshot m = (Earthshot) earthshots.get(i);
			        Rectangle r1 = m.getBounds();
			  
			   	      	int xx = (int) ((r1.getX())/BLOCK);																	
		        		int yy=(int)(r1.getY())/BLOCK;

		        		if (raum.charAt(yy*20+xx)=='*') {										// Ersetzt in der .txt datei enemy mit ' '																							
			        		int xxx = ((Jay.getX())/BLOCK);																	
			        		int yyy=(Jay.getY())/BLOCK;	
			        		exp = exp + 1;
			        		if(manaleech == true)manapoints = manapoints + 3;
		        			if(lifeleech == true && life <=2.5)life = life + 0.5;

			        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
			        				int c =raum.lastIndexOf("@");						
			    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
			    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
			    					try {
			    						restartLevel(false, (Jay.getImage()));
			    					} catch (IOException e1) {
			    						e1.printStackTrace();
			    					}
		        		}
			
			        }
				}}
			
			if(eischen==5){
			for (int i = 0; i < iceshots.size(); i++) {
		        Iceshot m = (Iceshot) iceshots.get(i);
		        Rectangle r1 = m.getBounds();

		   	      	int xx = (int) ((r1.getX())/BLOCK);																	
	        		int yy=(int)(r1.getY())/BLOCK;
	        		
	        		if (raum.charAt(yy*20+xx)=='*') {										// Ersetzt in der .txt datei enemy mit ' '																							
		        		int xxx = ((Jay.getX())/BLOCK);																	
		        		int yyy=(Jay.getY())/BLOCK;
		        		exp = exp +1;
		        		if(manaleech == true)manapoints = manapoints + 3;
	        			if(lifeleech == true && life <=2.5)life = life + 0.5;
		        		
		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					} catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        		}
			}
			}
		    			
		}
		
		private void setContentPane(ImageIcon imageIcon) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Kollisionsabfrage: Schwert vs Gegner
		 */
		public void check_sword_vs_enemy() {	
			 ArrayList<Sword> swords = getSwords();
			 ArrayList<Icesword> iceswords = getIceswords();
			 ArrayList<Earthsword> earthswords = getEarthswords();
			 ArrayList<Airsword> airswords = getAirswords();
			
		if(elementarmeister==true){
			for (int i = 0; i < swords.size(); i++) {
		        Sword s = (Sword) swords.get(i);
		        Rectangle r1 = s.getBounds();
	
		            int xx = (int) ((r1.getX())/BLOCK);
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {													
		        		int xxx = ((Jay.getX())/BLOCK);
		        		int yyy=(Jay.getY())/BLOCK;
		        		exp = exp +1;
		        		if(manaleech == true)manapoints = manapoints + 3;
	        			if(lifeleech == true && life <=2.5)life = life + 0.5;

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					} catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        		}
	        		
			}}
			
			for (int i = 0; i < iceswords.size(); i++) {
		        Icesword s = (Icesword) iceswords.get(i);
		        Rectangle r1 = s.getBounds();
	
		            int xx = (int) ((r1.getX())/BLOCK);
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {													
		        		int xxx = ((Jay.getX())/BLOCK);
		        		int yyy=(Jay.getY())/BLOCK;
		        		exp = exp +1;
		        		if(manaleech == true)manapoints = manapoints + 3;
	        			if(lifeleech == true && life <=2.5)life = life + 0.5;
		        		

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					} catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        		}
	        		
			}
			
			
			if(elementarmeister==true){
			for (int i = 0; i < earthswords.size(); i++) {
		        Earthsword s = (Earthsword) earthswords.get(i);
		        Rectangle r1 = s.getBounds();
	
		            int xx = (int) ((r1.getX())/BLOCK);
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {													
		        		int xxx = ((Jay.getX())/BLOCK);
		        		int yyy=(Jay.getY())/BLOCK;
		        		exp = exp +1;
		        		if(manaleech == true)manapoints = manapoints + 3;
	        			if(lifeleech == true && life <=2.5)life = life + 0.5;	

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					} catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        		}
	        		
			}}
			
			
			if(elementarmeister==true){
			for (int i = 0; i < airswords.size(); i++) {
		        Airsword s = (Airsword) airswords.get(i);
		        Rectangle r1 = s.getBounds();
	
		            int xx = (int) ((r1.getX())/BLOCK);
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {													
		        		int xxx = ((Jay.getX())/BLOCK);
		        		int yyy=(Jay.getY())/BLOCK;
		        		exp = exp +1;
		        		if(manaleech == true)manapoints = manapoints + 3;
	        			if(lifeleech == true && life <=2.5)life = life + 0.5;	

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					} catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        		}
	        		
			}}
	}
		/**
		 * Ersetzt Manatraenke durch freie Felder
		 * Egal ob im normalen Level oder im Shop
		 */
		public void spend_mana(){

			int xx = (int) ((Jay.getX())/BLOCK);
	        int yy=(int)(Jay.getY())/BLOCK;

	        	if (raum.charAt(yy*20+xx)=='m'||(raum.charAt(yy*20+xx)=='q')) {													
		        		int xxx = ((Jay.getX())/BLOCK);																
		        		int yyy=(Jay.getY())/BLOCK;	

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					}catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        	}
		}
		
		/**
		 * Ersetzt Herzen, Schwerter und Zauber durch freie Felder
		 * Nur im Shop
		 */
		public void spend_herzen(){

			int xx = (int) ((Jay.getX())/BLOCK);
	        int yy=(int)(Jay.getY())/BLOCK;

	        	if (raum.charAt(yy*20+xx)=='h'||(raum.charAt(yy*20+xx)=='5')||(raum.charAt(yy*20+xx)=='1')||(raum.charAt(yy*20+xx)=='2')||(raum.charAt(yy*20+xx)=='3')||(raum.charAt(yy*20+xx)=='4'||(raum.charAt(yy*20+xx)=='6')||(raum.charAt(yy*20+xx)=='7')||(raum.charAt(yy*20+xx)=='8'))) {														
		        		int xxx = ((Jay.getX())/BLOCK);																
		        		int yyy=(Jay.getY())/BLOCK;	

		        		raum=raum.substring(0,yy*20+xx)+' '+raum.substring(yy*20+xx+1);	
		        				int c =raum.lastIndexOf("@");						
		    					raum=raum.substring(0,c)+' '+raum.substring(c+1);
		    					raum=raum.substring(0,yyy*20+xxx)+'@'+raum.substring(yyy*20+xxx+1);
		    					try {
		    						restartLevel(false, (Jay.getImage()));
		    					}catch (IOException e1) {
		    						e1.printStackTrace();
		    					}
	        	}
		}}
		/*
		 * Hier wird ein neuer Frame ge�ffnet mit einem Speicher Button
		 * Man kann das Spiel speichern und dann weiterspielen
		*/
		//private class KlickZumSpeichern extends KeyAdapter{

		
