package newgame;
 
import java.io.*;
 
public class SaveObject  {
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        
        /*
         * Schreibt die Objekte in die abgegebene Datei
         */
        FileOutputStream fos = new FileOutputStream("src/Resources/savedata.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        Character one = new Character(1, '1'); // Die Elemente, die auf den constructor in Character zugreiffen
        Character two = new Character(2, '2');
       // Character three = new Character(3, '3');
        
        System.out.println("Speichern startet"); 
        
        oos.writeObject(one); //Hier werde die Objekte gespeichert
        oos.writeObject(two);
    
        
        System.out.println("Speichern beendet"); 
        
        /*
         * Schlieﬂt die Datei
         */
        
        oos.close();
        fos.close(); 
        
    }
    }
        
        
     