package newgame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataReader  {
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{

FileInputStream ifstream = new FileInputStream("src/Resources/savedata.txt");
ObjectInputStream iostream = new ObjectInputStream(ifstream);

Character[] arr =  new Character[2]; //to store the objects

System.out.println("Starting reading!");

for(int i = 0; i < 2; i++) {
    arr[i] = ((Character) iostream.readObject()); //read in the object
    System.out.print("The contents of TesterObject " + i + " are: ");
    System.out.print(arr[i].getImage() + " "); //display the fields 
    System.out.print(arr[i].getBounds() + " ");
    //System.out.print(arr[i].getChar());
    System.out.println();
}

System.out.println("Done reading!");

iostream.close();
ifstream.close();

}}