import java.util.ArrayList;
import java.io.File;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
/**
 * 
 * @author msilveus
 *Source: https://www.techiedelight.com/iterate-over-characters-string-java/
 */
public class CommandObject {
	
	private ArrayList<String> code; //This is the code that goes along with this particular object  
	private ArrayList<Integer> storedIndices;
	public CommandObject(String Command,String Segment, String codeInput) {
		getProperCodeOrientation(codeInput);
	}
	public CommandObject(String Command,String Segment, File codeInput) {
		getProperCodeOrientation(codeInput);
	}

	public int getCommandSegementHash() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getCodeString() throws NumberNotSetException {
		if (code.get(storedIndices.get(0)).equals("n")){
			throw new NumberNotSetException();
		}
		String codePrint="";  // this is the intialization of the string variable that one hopes to add to and return 
		for (int i=0;i<code.size();i++) {
			//Gets all the value of the array and prints them out 
			codePrint= codePrint+code.get(i);
		}
		return codePrint;
	}
	/**
	 * Sets the numbers to the paticular numbers
	 * @param number this is the desired input into your 
	 */
	public void insertNumber(String number) {
		for(int i = 0;i<storedIndices.size();i++) {
			//Sets particular indices to the numbers that is desired.
			code.set(storedIndices.get(i), number);
		}
	}
	/**
	 * 
	 * @param codeInput
	 */
	private void getProperCodeOrientation(String codeInput) {
		CharacterIterator it = new StringCharacterIterator(codeInput);
		int index = 0; //Initial index for array
		//"@n" is the two characters that are being searched for  
		while(it.current()!= CharacterIterator.DONE) {
			//Checks that the particular values are found
			if (index+1>code.size()) {
				//Checks that the index is intialized and if not adds to the arrayList to prevent index out of bounds exception
				String k = "";//A holder value;
				code.add(k);  //Adds to the array list
			}
			if ((it.current()=='@') && it.next() =='n') {
				//Checks the current and the next character to see if they match the desired charecters
				code.set(index, code.get(index)+it.current());
				index++;
				//adds the index and adds the dummy value to the paticular spot
				code.add("n");
				storedIndices.add(index);
				//Adds to the indices for when insertion is required later
				index++;
				//Adds to the index so that the next spot in the array 
			}else {
				//Sets the string at the current index to itself plus the current charecter
				code.set(index, code.get(index)+it.current());
			}
		}
	}

}
