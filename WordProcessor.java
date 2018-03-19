
/*import classes for file input - scanner etc.*/
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

/*import implementing set (e.g. TreeSet)*/

public class WordProcessor {
	
	private static <E> String displaySet(Set<E> inputSet){
		
		/* implement this static method to create a String representation of set - 5 
		comma separated elements per line assume that type E has a toString method */

		String output = ""; 
		int count = 0;

		for (E element:inputSet) {
			output += element;
			count++;
			if (count%5 == 0) {				// When dividing by 5 results in no remainder, new line.
				output = output + "\n";	
			}	
		}
		
		output = output.toString();
		return output;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
		Set<String> wordSet = new TreeSet<String>();		//set of type String called wordSet
		Set<CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>(); 	//set of type CountedElement<String> called countedWordSet 

		/*for each input file (assume 3 arguments, each the name of a file)
		for each word w, if wordset doesn't contain w: add w to wordset
	    add new element to countedWordSet
		else increment numeric part of element in countedWordSet containing w */

		FileReader fReader = null;
		Scanner scanner = null;

		String word = "";
		String[] w;
		CountedElement <String> cElement;

		for (String files:args) {
			try {
				fReader = new FileReader (files);
				scanner = new Scanner (fReader);

				while (scanner.hasNext()) {
					word = scanner.next();
					w = word.split(" ");

					for (int i=0; i<w.length; i++) {
						if (!wordSet.contains(w[i])) {						// If it's not in the list, add it.
							cElement = new CountedElement<String>(w[i], 1);
							wordSet.add(w[i]);
							countedWordSet.add(cElement);
						}
						
						else {
							
							for (CountedElement<String> e: countedWordSet) {	// Else, add 1 to the count.
								if (e.getElement().equals(w[i])) {
									e.setCount(e.getCount()+1);			
								}
							}
						}
					}
				}
				fReader.close (); 
				scanner.close ();
			}

			catch (IOException IOE) { 	// Couldn't find the file.
				JOptionPane.showMessageDialog (null, "FILE NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
				IOE.printStackTrace();		
			}

			catch (InputMismatchException IME) {		// Wrong file type.
				JOptionPane.showMessageDialog (null, "INVALID FILE", "ERROR", JOptionPane.ERROR_MESSAGE);
				IME.printStackTrace();	
			}
		}
		
		System.out.println(displaySet(countedWordSet));
	}
}