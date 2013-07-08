package newgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

	public static void read(String where) {
		File save = new File(where);
		
		/*
		 * Hier gibt er eine Fehlermeldung aus, falls etwas nicht stimmt.
		 * Dies macht er damit das System nicht kompeltt abstürzt.
		 */
		
		if(!save.exists()){
			System.err.println("Sorry but " + where + "is not found in " + save.getParent());
		return;
		}
		if(!(save.toString().contains("txt"))){
			System.err.println("Sorry but " + where + "does not have" + "file extenstion of .txt");
		return;
		}
		
		/*
		 *Ab hier wird aus der Datei gelesen und zwar sucht er sich den Schlüssel
		 *aus, der in der Datei (savedata.txt) vor dem = Zeichen steht.
		 */
		
		try{
		FileReader reader = new FileReader(save);
		BufferedReader bufr = new BufferedReader(reader);
		
		String name = translateKey(bufr.readLine(), "name");
		String Leben = translateKey(bufr.readLine(), "Leben");
		String mana = translateKey(bufr.readLine(), "mana");
		//String cl = translateKey(bufr.readLine(), "class");
		
		int actLeben = Integer.parseInt(Leben);
		int actMana = Integer.parseInt(mana);
		
		/*
		 * Hier werden die Daten die hinter dem = Zeichen stehen
		 * in der Konsole angezeigt.
		 */
		
		System.out.println("Das ist das " + name + "\n" +
				"HP: " + actLeben + "\n" + "Mana: " + actMana);
		
	} catch (FileNotFoundException e) {
		System.err.println("Sorry but " + where + "is not found in " + save.getParent());

	} catch (IOException e){
		System.err.println("An IOException has occured!");
	}
		
}	
	public static String translateKey(String input, String key){
		return input.substring(key.length() + 1);
	}
}
