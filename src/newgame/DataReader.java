package newgame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataReader  {
    
    public static void main (String args[]) throws IOException, ClassNotFoundException{

FileInputStream fis = new FileInputStream("src/Resources/savedata.txt"); // hier befinden sich die Objekte, die geladen werden
ObjectInputStream ois = new ObjectInputStream(fis);

Character[] arr =  new Character[2]; 

System.out.println("Laden startet");

for(int i = 0; i < 2; i++) {
    arr[i] = ((Character) ois.readObject()); // Hier wird aus dem Objekt herausgelesen
    System.out.print(i);
    System.out.print(arr[i].getImage() ); // hier sollten die Bilder geladen werden
    System.out.print(arr[i].getBounds() ); // hier wird die Position des Characters geladen
    System.out.println();
}

System.out.println("Laden abgeschlossen");
/*
 * schließt die Datei
 */
ois.close();
fis.close();

}}