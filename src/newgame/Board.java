package newgame;




import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;




public class Board extends JPanel implements ActionListener{

	Image image;
	Image img;											//Bild fuer den Hintergrund (WEG)
	private Character Jay;
	private String raum="";
	private String lr; 								//lr fuer der Name der raumdatei, w:wandbild , h:hintergrundsbild
	private ArrayList<Shot> shots;						//Array fuer die Zeichnung der Schuesse
	private Timer timer;
	private int BLOCK = 50;								// 50* 50 Pixel
	private int position;
	private int money=0;
	private int k;
	private boolean test=true;

	ImageIcon r = new ImageIcon("src/Resources/r1.png");						// fuer versch. Positionen rechts, links, oben, unten
	ImageIcon l = new ImageIcon("src/Resources/l1.png");
	ImageIcon t = new ImageIcon("src/Resources/Character top.png");
	ImageIcon b = new ImageIcon("src/Resources/Character.png");

	java.util.List<Movement> enemys = new java.util.ArrayList<Movement>();
	java.util.List<Movement> walls = new java.util.ArrayList<Movement>();		// Array fuer die Waende..
	java.util.List<Movement> keys = new java.util.ArrayList<Movement>();
	java.util.List<Movement> wizards = new java.util.ArrayList<Movement>();
	java.util.List<Movement> coins = new java.util.ArrayList<Movement>();
	
	public Board() throws IOException{
		lr="l1r1";
		addKeyListener(new Ap());
		setFocusable(true);		
		initWorld();
	    shots = new ArrayList<Shot>();
		timer = new Timer(5, this);												//zeichnet alle  5ms den Board (Schuesse)
        timer.start();
	}
	
	public void loeschen(boolean b){
		coins.clear();
		walls.clear();
		enemys.clear();
		keys.clear();
		wizards.clear();
		if (b) raum="";
	}
	
	public void collision(int movx,int movy){
		int xx = ((Jay.getX()+movx)/BLOCK);																	//xx und yy sind die imaginaere Koordinaten innerhalb des Strings Variable (level).
		int yy=(Jay.getY()+movy)/BLOCK;																		//xx und yy werden dafuer gerechnet um zu erkennen, ob an der Stelle wohin sich die Spielfigur bewegen will, kein # im variable level bzw kein Stueck Mauer im Spielfeld gibt
		if ((raum.charAt(yy*20+xx)!='#')&&(raum.charAt(yy*20+xx)!='~')||(xx*yy<0))					//yy wird mal 20 multipliziert da es in jeder linie des Spielfelds 20 Bloecke gibt(also in jeder linie des strings level gibt es 20 zeichen)
		{																							//Wandkollision:
			Jay.move(movx,movy);																		//erst wenn es kein Stueck Mauer, keinen NPC oder einen Ein-Ausgang gibt(entweder xx oder yy <0 ist) darf/kann sich die Spielfigur bewegen
			if (raum.charAt(yy*20+xx)=='a'){
				money=money+10;
				System.out.println(money);
				if (raum.contains("@") )
				{	int c =raum.lastIndexOf("@");						
					raum=raum.substring(0,c)+' '+raum.substring(c+1);
					raum=raum.substring(0,yy*20+xx)+'@'+raum.substring(yy*20+xx+1);
					try {
						restartLevel(false);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
		}
		if (raum.charAt(yy*20+xx)=='*'){    														// Kollision mit dem Gegner, Neustart des Spiels
			//Game_over();
			try {
				restartLevel(true);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		if (raum.charAt(yy*20+xx)=='~'){    														
			Dialogue();
		}
				
		if (raum.charAt(yy*20+xx)=='$')									//schluessel gefunden!
		{	if (lr.charAt(1)=='1') lr="l2r1";
			else if (lr.charAt(1)=='2')lr="l3r1"; 
			else if (lr.charAt(1)=='3')lr="l4r1";
			loeschen(true);
			try {
				initWorld();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}


	}


	public Image getImage(){
		return image;

	}
	
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

	public final void initWorld() throws IOException{											// zeichnet das Level mit Walls, Character, dem Schluessel und Gegner.
		ImageIcon ii= new ImageIcon ("src/Resources/back"+lr.charAt(1)+".png");					// Den Pfad fuers Hintergrundbild angeben.
		img=ii.getImage();		//Image importieren.		
		if (raum=="") raum=raumeinlesen();
		int x = 0;
		int y = 0;
		Wall wall;
		Coin coin;
		Enemy enemy;
		Key key;
		Wizard wizard;

		for(int i = 0; i < raum.length(); i++){									// level variable Buchstabe fuer Buchstabe durchgehen.

			char obj = raum.charAt(i);										

			if(obj == '\n'){													//y erhoeht sich um ein BLOCK wenn man ein /n im String Level findet.
				y = y + BLOCK;
				x = 0;
			}else if(obj == '#'){												// # bezeichnet ein Stueck Mauer. eine Mauer im array walls an seine Position speichern.
				wall = new Wall(x,y, "wand"+ lr.charAt(1));
				walls.add(wall);
				x = x + BLOCK;
			}else if(obj == '@'){												// Legt die Position des Charakters beim Levelstart fest
				if (lr!="l3r4"){
				Jay = new Character(x,y);
				x = x + BLOCK;}
			}
			else if(obj == ' '){												//x erhoeht sich um einen Block(' ':Bereich wo sich der Spieler bewegen kann)
				x = x + BLOCK;
			}
			else if(obj == '*'){                								// stellt den Enemy in den Levels als * dar
				enemy = new Enemy(x,y);
				enemys.add(enemy);
				x = x + BLOCK;
				}
			else if(obj == '$'){                								// stellt den Schluessel in den Levels als $ dar
				key = new Key(x,y);	
				keys.add(key);
				x = x + BLOCK;
			}
			else if (obj=='a'){
				coin=new Coin(x,y);
				coins.add(coin);
				x=x+BLOCK;
			}
			else if(obj == '~'){												//stellt den NPC in den Levels als ein ~ dar
				wizard = new Wizard(x,y);
				wizards.add(wizard);
				x = x + BLOCK;
			}
		}
	}

	public void buildWorld(Graphics g){

		g.drawImage(img, 0, 0, null);											//Background Image zeichnen

		ArrayList<Movement> world = new ArrayList<Movement>();

		world.addAll(walls);													//Alle Objekte in einem Array world speichern				//im levelend soll es kein Spielfigur geben
		world.add(Jay);
		world.addAll(enemys);
		world.addAll(keys);
		world.addAll(wizards);
		world.addAll(coins);


		for(int i = 0; i < world.size(); i++){									// Array world durchgehen um objekte zu zeichnen.
			Movement obj = (Movement) world.get(i);
			g.drawImage(obj.getImage(), obj.getX(), obj.getY(), this);			// g.drawImage fuer die Grafische Zeichnung
			
		}

	}
	public Graphics g;
	public void paint(Graphics g){
		super.paint(g);
		buildWorld(g);
		  ArrayList shots = getShots();											// fuer die grafische Zeichnung der Schuesse

	        for (int i = 0; i < shots.size(); i++ ) {
	            Shot m = (Shot) shots.get(i);
	            g.drawImage(m.getImage(), m.getX(), m.getY(), this);
	        }
	}


	 public ArrayList getShots() {												// gibt die Schuesse der Positionen wieder
	        return shots;
	    }

	private class Ap extends KeyAdapter{										// fuer rechts: holt das Bild mit Position rechts
																				// durch die class Character bewegt sich Diggy in die entsprechende Richtung
		public  void keyPressed(KeyEvent e){

			int key = e.getKeyCode();

			if(key == KeyEvent.VK_RIGHT){		

				image = r.getImage();																		//Image vom Spieler der nach rechts laeuft
				Jay.setImage(image);
				position = 1;
				collision(BLOCK,0);
			}

			else if(key == KeyEvent.VK_LEFT){
				image = l.getImage();
				Jay.setImage(image);
				position = 2;
				collision(-BLOCK,0);
			}


			else if(key == KeyEvent.VK_UP){
				image = t.getImage();
				Jay.setImage(image);
				position = 3;
				collision(0,-BLOCK);
			}
			
			else if(key == KeyEvent.VK_DOWN){
				image = b.getImage();
				Jay.setImage(image);
				position = 4;
				collision(0,BLOCK);
			}
			else if (key == KeyEvent.VK_SPACE) {							// Taste -Space ruft die Funktion fire auf
	            fire();
	        }


			repaint();
			

			if ((Jay.getY()==-BLOCK)||(Jay.getY()==0))  {										//Wenn der Spieler am Ausgang des 1. Raums ist dann 
				if (lr.length()==4){
				if (lr.charAt(3)=='1') lr=lr.substring(0,3)+"2";
				else if (lr.charAt(3)=='2') lr=lr.substring(0,3)+'3';
				}
				else lr=lr.substring(0,4);
				loeschen(true);
				try {
					initWorld();
				} catch (IOException e1) {
					e1.printStackTrace();
				}															//world initialisieren 

			}
			if (Jay.getX() ==950 ){											//Bedingung erfuellt nur am Ausgang des 2. Raums
				if (lr.length()==4){
					if (lr.charAt(3)=='1') lr=lr.substring(0,3)+"2";
					else if (lr.charAt(3)=='2') lr=lr.substring(0,3)+'3';
				}
				else lr=lr.substring(0,4);
				loeschen(true);
				try {
					initWorld();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (Jay.getX() == -BLOCK){												//wenn x=-BLOCK ist, befindet sich der Spieler am eingang von Raum 2 oder 3														//und wenn er dadurch geht dann kehrt er zu einem vorherigen Raum (Raum3-->Raum2 oder Raum2-->Raum1) zur�ck
				if (lr.charAt(3)!='1') lr=lr+'a';	
				loeschen(true);
				try {
					initWorld();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}

			
	}
	public void restartLevel(boolean test) throws IOException {			
		if (lr.length()==5){
			if (lr.charAt(3)=='2') lr=lr.substring(0, 3)+'1';
			else if (lr.charAt(3)=='3') lr=lr.substring(0, 3)+'2';
		}
		loeschen(test);
		initWorld();
		
	}
																					
	 public void fire() {
		 	if(position==1){
		 		shots.add(new Shot(Jay.getX() + BLOCK, Jay.getY()));			// Posistion der Schussrichtung,je in welche Richtung Diggy guckt
		 		k = 00;
		 	}
		 	if(position==2){													// der Schuss soll nicht ueber Diggy gehen 
		 		shots.add(new Shot(Jay.getX() - BLOCK, Jay.getY()));			// k als Flag
		 		k = 01;
		 	}
		
		 	if(position==3){
		 		shots.add(new Shot(Jay.getX(), Jay.getY() - BLOCK));
		 		k = 10;
		 	}
		
		 	if(position==4){
		 		shots.add(new Shot(Jay.getX(), Jay.getY() + BLOCK));	
		 	    k = 11;
		 	}
		 	
	}
	

	 @Override
	 public void actionPerformed(ActionEvent e) {							// zeichnet die Schuesse 
		 ArrayList shots = getShots();										
			
		 	for (int i = 0; i < shots.size(); i++) {
		 		Shot m = (Shot) shots.get(i);
		 
		 	
		 		
		 		if(m.getVisible()){	 										// falss limit des Boards nicht �berschritten
		 																	// wird je nach Blickrichtung in die richtgige
		 																	// Richtung geschossen
		 		if(k==00) m.move_r();
		 		if(k==01) m.move_l();
		 		if(k==10) m.move_u();
		 		if(k==11) m.move_d();
		 			 
		 			}else shots.remove(i);
		 	repaint();														// alle 5 ms werden die Schuss-Bewegungen gezeichnet
		 	}
	 }
	 
		public Rectangle getBounds(){
			return new Rectangle(Jay.getX(),Jay.getY(),50,50);
		}
		
		
		public void Dialogue(){
			
			JFrame Dialogue = new Dialogue("Weiser Zauberer");
			
			Dialogue.setSize(400,200);
			Dialogue.setLocationRelativeTo(null);
			Dialogue.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			Dialogue.setVisible(true);
			Dialogue.setFocusable(true);
			Dialogue.setLayout(new BorderLayout());
			Dialogue.setLayout(null);
			Dialogue.add(new Dialogue("Weiser Zauberer"));
		}
}



/**public void Game_over(){
	 

		
	 JFrame Game_over = new JFrame();
	 
	 
	 Game_over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 Game_over.setSize(500,500);
	 Game_over.setVisible(true);
	 Game_over.setFocusable(true);
	 Game_over.setLocationRelativeTo(null);   // Fenster in der MItte 
	 Game_over.add(new Game_over());
	

			
		}
*/
