package newgame;
 
import java.io.*;
 
public class SaveObject  {
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        
        //writing objects to the file
        FileOutputStream fstream = new FileOutputStream("src/Resources/savedata.txt");
        ObjectOutputStream ostream = new ObjectOutputStream(fstream);
        
        Character one = new Character(1, '1'); //3 TesterObjects
        Character two = new Character(2, '2');
        Character three = new Character(3, '3');
        
        System.out.println("Starting writing!"); //opening message
        
        ostream.writeObject(one); //write the objects
        ostream.writeObject(two);
        ostream.writeObject(three);
        
        System.out.println("Done writing!"); //closing message
        
        ostream.close();
        fstream.close(); //close files
        
    }
    }
        
        
       /*
        //reading objects from the file
        FileInputStream ifstream = new FileInputStream("src/Resources/savedata.txt");
        ObjectInputStream iostream = new ObjectInputStream(ifstream);
        
        Character[] arr =  new Character[3]; //to store the objects
        
        System.out.println("Starting reading!");
        
        for(int i = 0; i < 3; i++) {
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
        
    }
}*/