package newgame;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



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
	private String lr,rooms,lrs; 														//lr fuer den Namen der Raumdatei, w:wandbild , h:hintergrundsbild
	private ArrayList<Shot> shots;
	private ArrayList<Sword> swords;
	private Timer timer;
	private int BLOCK = 50;
	private int position;
	private int need_life;
	private int ruban=0,xruban;
	private double life=3.0, xlife;
	private int magic=0;
	private int manapoints=0;
	private int schwertchen = 0;
	private int k,z,posX,posY;
	boolean ingame,mana,failed,get_sword;
	private checkpoint check;
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
	ImageIcon tr = new ImageIcon("src/Resources/trankk.png");
	ImageIcon h1 = new ImageIcon("src/Resources/herz.png");
	ImageIcon h2 = new ImageIcon("src/Resources/halbherz.png");
	ImageIcon s = new ImageIcon("src/Resources/schatz.png");
	ImageIcon dr = new ImageIcon("src/Resources/digright.png");	
	ImageIcon dl = new ImageIcon("src/Resources/digleft.png");
	ImageIcon du = new ImageIcon("src/Resources/digup.png");
	ImageIcon db = new ImageIcon("src/Resources/digb.png");
	ImageIcon sw = new ImageIcon("src/Resources/sword.png");
	ImageIcon co = new ImageIcon("src/Resources/Coin.png");
	ImageIcon sr = new ImageIcon("src/Resources/missile1.png");
	ImageIcon sl = new ImageIcon("src/Resources/missile1l.png");
	ImageIcon su = new ImageIcon("src/Resources/missile1t.png");
	ImageIcon sd = new ImageIcon("src/Resources/missile1d.png");
	
	
	
	
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
	java.util.List<Movement> schwerter = new java.util.ArrayList<Movement>();
	
	/**
	 * Gibt den Images entsprechende Namen		
	 */
	Image image1 = image = r.getImage();
	Image image2 = image = l.getImage();
	Image image3 = image = t.getImage();	
	Image image4 = image = b.getImage();
	Image trank = image = tr.getImage();
	Image herz1 = image = h1.getImage();
	Image herz2 = image = h2.getImage();
	Image schatz = image = s.getImage();
	Image image5 = image = dr.getImage();
	Image image6 = image = dl.getImage();
	Image image7 = image = du.getImage();
	Image image8 = image = db.getImage();
	Image image9 = image = sr.getImage();
	Image image10 = image = sl.getImage();
	Image image11 = image = su.getImage();
	Image image12 = image = sd.getImage();
	Image sword = image = sw.getImage();
	Image coin = image = co.getImage();
	
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
		schwertchen = 0;
		manapoints = 100;
	    shots = new ArrayList<Shot>();
		swords = new ArrayList<Sword>();
	    timer = new Timer(5, this);
        timer.start();
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
		schwerter.clear();
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
			
		if ((raum.charAt(yy*20+xx)!='#')&&(raum.charAt(yy*20+xx)!='~')&&(raum.charAt(yy*20+xx)!='s')&&(xx>=0)||(Jay.getY()<0))
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
		/**
		 * Nimmt bei Kollision das Schwert auf
		 */
		if (raum.charAt(yy*20+xx)=='o'){
			schwertchen = 5;
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

		//ImageIcon ii= new ImageIcon ("src/Resources/back"+lr.charAt(1)+".png");
		//img=ii.getImage();		//Image importieren.	
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
		Swordicon schwert;
		

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
			else if (obj=='a'){
					coin=new Coin(x,y);
					coins.add(coin);
					x=x+BLOCK;
			}
			else if(obj == '~'){
					wizard = new Wizard(x,y);
					wizards.add(wizard);
					x = x + BLOCK;
			}
			else if(obj == 'o'){
				schwert = new Swordicon(x,y);
				schwerter.add(schwert);
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

		g.drawImage(img, 0, 0, null);
		ArrayList<Movement> world = new ArrayList<Movement>();

		if (raum.contains("b")==true)world.add(check);
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
		world.addAll(schwerter);

		for(int i = 0; i < world.size(); i++){														// Array world durchgehen um objekte zu zeichnen.

			Movement obj = (Movement) world.get(i);
			g.drawImage(obj.getImage(), obj.getX(), obj.getY(), this);								// g.drawImage fuer die Grafische Zeichnung
		}
		
	       ArrayList<Shot> shots = getShots();
	       		for (int j = 0; j < shots.size(); j++){
	       			Shot m = (Shot) shots.get(j);
	       			g.drawImage(m.getImage(), m.getX(), m.getY(), this);
                }
	       		
	       
	      ArrayList<Sword> swords = getSwords();
	       		for (int i = 0; i < swords.size(); i++){
	       			Sword s = (Sword) swords.get(i);
	       			g.drawImage(s.getImage(), s.getX(), s.getY(), this);
               }

	       			for (int i = 0; i < enemys.size(); i++){										// Gegner soll nur zu sehen sein, wenn er nicht besiegt wurde 
	       				Enemy e = (Enemy) enemys.get(i);
	       				if (e.isVisible())
	       				g.drawImage(e.getImage(), e.getX(), e.getY(), this);
	       			}

	       			for (int i = 0; i < walls.size(); i++){											//  NICHT LOESCHEN FUER DAS TESTEN DER WAENDE 

	       				Wall w = (Wall) walls.get(i);
	       				if (w.isVisible())
	       				g.drawImage(w.getImage(), w.getX(), w.getY(), this);
	       			}

	       			for (int i = 0; i < coins.size(); i++){											// Muenzen sollen gezeichnet werden bei Tod

	       				Coin c = (Coin) coins.get(i);
	       				if (c.isVisible())
	       				g.drawImage(c.getImage(), c.getX(), c.getY(), this);
	       			}
	       			 			
	}
    

	/**
	 * Zeichnet die Inventaranzeige
	 */
    public void paint(Graphics g){
	super.paint(g);
	
	if(ingame){	
		buildWorld(g);
		if (raum.contains("k")){
			if (lr.charAt(1)=='3') Monster_speed=1;
			else Monster_speed=1;
			movemonster();
		}
		if (raum.contains("w")){
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
	        String s,w,l,k,p;

	        g.setFont(smallfont);															// Geldanzeige
	        g.setColor(new Color(98,150,255));
	        s = "Money: " + (countsmoney);
	        g.drawString(s,970,160);
	    	g.drawImage(schatz,970,180,this);												// zeichnet Welt mit Punktestand..
		
	    	double lifebar= life;
	    	
			String t;
																							// Lebensanzeige
			t = "Leben: " + (lifebar);
			g.drawString(t,970,40);
			
			String m;
			
			m = "Mana: " + (manapoints);
			g.drawString(m,970,280);
			
			if(life > 3){
				life = 3;
			}
			
			if (schwertchen == 5){    														//startet bei Kollision den Dialog des Ladenbesitzers
	    		g.drawImage(sword,1020, 480, this);
	    		p = "Du hast ein Schwert!";
	    		g.drawString(p,970,450);
	    		get_sword=true;
			}
			
			if(life==3.0){
			g.drawImage(herz1,970,60,this);
			g.drawImage(herz1,1020, 60, this);
			g.drawImage(herz1,1070, 60, this);
			}
			if(life==2.5){
				g.drawImage(herz1,970,60,this);
				g.drawImage(herz1,1020, 60, this);
				g.drawImage(herz2,1070, 60, this);}
			
			if(life==2.0){						
				g.drawImage(herz1,970,60,this);
				g.drawImage(herz1,1020, 60, this);
			}
			if(life==1.5){
				g.drawImage(herz1,970,60,this);
				g.drawImage(herz2,1020, 60, this);}
			
			if(life==1.0){
				g.drawImage(herz1,970,60,this);
			}
			if(life==0.5){
				g.drawImage(herz2,970,60,this);}
			
		
			   String mes;
   		        g.setFont(smallfont);																// Manaanzeige
   		        g.setColor(new Color(98,150,255));
   		        mes = "Manatraenke: " + (magic);
   		        g.drawString(mes,970,350);
   		 
	        	if(mana==true){
	        		
	        		if(magic==1){																	
	        			g.drawImage(trank, 970, 370, this);
	        		}
	        		if(magic==2){																
	        			g.drawImage(trank,970,370,this);
	        			g.drawImage(trank,1020,370, this);	
	        		}
	        	
	        		if(magic==3){																	
	        			g.drawImage(trank,970,370,this);
	        			g.drawImage(trank,1020, 370, this);
	        			g.drawImage(trank,1070, 370, this);				  			
	        		}
	        	 
	        		if (magic > 3){			
	        			magic = 3;
	        		}
	        	}
	}else{
	}    
    Toolkit.getDefaultToolkit().sync();
    g.dispose();
    }



    /**
     * Giebt die Schuesse wieder
     * @return Anzeige der Schuesse
     */
    public ArrayList<Shot> getShots() {
	        return shots;
	    }
   

    /**
     * Gibt das Schwert wieder
     * @return Anzeige des Schwertes
     */
    public ArrayList<Sword> getSwords() {																
	     return swords;
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
     * @author Tobias
     *
     */
	private class Ap extends KeyAdapter{


		public  void keyPressed(KeyEvent e){

			int key = e.getKeyCode();

			if(key == KeyEvent.VK_RIGHT){		

				Image image1 = image = r.getImage();
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0, image1);

			}else if(key == KeyEvent.VK_LEFT){

				Image image2 = image = l.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0, image2);

			}
			else if(key == KeyEvent.VK_UP){

				Image image3= image = t.getImage() ;
				Jay.setImage(image);
				position = 3;
				collision(0,-BLOCK,image3);

			}else if(key == KeyEvent.VK_DOWN){

				Image image4 = image = b.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0,BLOCK, image4);

			}
			else if(key == KeyEvent.VK_B){

				if (magic == 3){
					magic = 2;
					manapoints = 100;
				}
				if (magic == 2){
					magic = 1;
					manapoints = 100;
				}
				if (magic == 1){
					magic = 0;
					manapoints = 100;
				}

			}else if (key == KeyEvent.VK_SPACE) {	
				if (manapoints >= 5){																// Taste -Space ruft die Funktion fire auf
				fire();
				manapoints = manapoints - 5;
				}
			}else if (key == KeyEvent.VK_V && get_sword==true) {									// 2 te Waffe = Schwertkampf in versch Richtungen
				sword_play();
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
	 
	 public void sword_play(){													// Schwertkampf mit 4 Richtungen zum schiessen				
	 
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
			if (raum.contains("k")){
				check_shot_vs_boss();
			}
	}
			 ArrayList<Sword> swords = getSwords()
					 ;
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
			 repaint();	
	}
		 
		public Rectangle getBounds(){
			return new Rectangle(Jay.getX(),Jay.getY(),50,50);				
		}

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

		/**
		 * Kollisionsabfrage: Schuss vs Gold
		 */
		public void check_shot_vs_coin() {

			ArrayList<Shot> shots = getShots();

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
		
		/**
		 * Kollisionsabfrage: Schwert vs Gold
		 */
		public void check_sword_vs_coin() {										

			 ArrayList<Sword> swords = getSwords();
			 
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
			}
		
		/**
		 * Kollisionsabfrage: Schwert vs Mauer
		 */
		public void check_sword_vs_wall() {																

			 ArrayList<Sword> swords = getSwords();
			 
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
			}

		
		private int boss_leben;
		
		/**
		 * Kollisionsabfrage: Schuss vs Boss
		 */
		public void check_shot_vs_boss() {																
			ArrayList<Shot> shots = getShots();

		    for (int i = 0; i < shots.size(); i++) {
		        Shot m = (Shot) shots.get(i);
		        Rectangle r1 = m.getBounds();
		        
		        if ((Math.abs(r1.x-Monster.getX())<50)&&(Math.abs(r1.y-Monster.getY())<50)) {													 		
		        		boss_leben=boss_leben-1;
		        		if (boss_leben==0){
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
		
		/**
		 * Kollisionsabfrage: Schuss vs Mauer
		 */
		public void check_shot_vs_wall() {

			ArrayList<Shot> shots = getShots();

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

		/**
		 * Kollisionsabfrage: Schuss vs Gegner
		 */
		public void check_shot_vs_enemy() {								

			for (int i = 0; i < shots.size(); i++) {
		        Shot m = (Shot) shots.get(i);
		        Rectangle r1 = m.getBounds();
		  
		   	      	int xx = (int) ((r1.getX())/BLOCK);																	
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {										// Ersetzt in der .txt datei enemy mit ' '																							
		        		int xxx = ((Jay.getX())/BLOCK);																	
		        		int yyy=(Jay.getY())/BLOCK;	

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
		
		/**
		 * Kollisionsabfrage: Schwert vs Gegner
		 */
		public void check_sword_vs_enemy() {	
			 ArrayList<Sword> swords = getSwords();
			

			for (int i = 0; i < swords.size(); i++) {
		        Sword s = (Sword) swords.get(i);
		        Rectangle r1 = s.getBounds();
	
		            int xx = (int) ((r1.getX())/BLOCK);
	        		int yy=(int)(r1.getY())/BLOCK;

	        		if (raum.charAt(yy*20+xx)=='*') {													
		        		int xxx = ((Jay.getX())/BLOCK);
		        		int yyy=(Jay.getY())/BLOCK;	

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
		 * Ersetzt Herzen durch freie Felder
		 * Nur im Shop
		 */
		public void spend_herzen(){

			int xx = (int) ((Jay.getX())/BLOCK);
	        int yy=(int)(Jay.getY())/BLOCK;

	        	if (raum.charAt(yy*20+xx)=='h'||(raum.charAt(yy*20+xx)=='o')) {														
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

}

 
