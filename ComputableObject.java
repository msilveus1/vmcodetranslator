import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

/*
 * This is for the computable type of VM command that can exist
 */
public class ComputableObject implements VMObjects {
	private String computable;  //This is where the unique hash gets its hashcode
	private ArrayList<String> formatedCode;  //Stores formated code VM machine 
	private ArrayList<Integer> storedIndices; //Stores the indices in formatedCode that need replaced with a label.
	/**
	 * A constructor for this method
	 * @param computable this is the term for the computation
	 * @param inputCode this is the input code for what someone want to be associated with this specific computable command
	 * 
	 */
	public ComputableObject(String computable, String inputCode ) {
		this.computable=computable;
		formatCode(inputCode);
		//Inserts the associated input code into a method to be formated
	}
	/**
	 * This is the shared function in of VMObjects and it creates a unique hash based of the string values
	 * This is more important for the command object class but still aplicable 
	 */
	@Override
	public int getUniqueHash() {
		//This a unique hash code
		return computable.hashCode();
		
	}
	/**
	 * This formats the code so that labels can be inserted fairly easily
	 * @param inputCode this is the input code
	 */
	private void formatCode(String inputCode) {
		CharacterIterator it = new StringCharacterIterator(inputCode);
		int index = 0; //Initial index for array
		//"@L" is the two characters that are being searched for  
		while(it.current()!= CharacterIterator.DONE) {
			//Checks that the particular values are found
			if (index+1>formatedCode.size()) {
				//Checks that the index is intialized and if not adds to the arrayList to prevent index out of bounds exception
				String k = "";//A place holder value;
				formatedCode.add(k);  //Adds to the array list
			}
			if ((it.current()=='@') && it.next() =='L') {
				//Checks the current and the next character to see if they match the desired charecters
				this.formatedCode.set(index, formatedCode.get(index)+it.previous());
				index++;
				//adds the index and adds the dummy value to the paticular spot
				formatedCode.add("L");
				storedIndices.add(index);
				//Adds to the indices for when insertion is required later
				index++;
				it.next();
				it.next();
				//Adds to the index so that the next spot in the array 
			}else {
				//Sets the string at the current index to itself plus the current character
				formatedCode.set(index, formatedCode.get(index)+it.current());
				it.next();
			}	
		}
	}
	/**
	 * This method retrieves the formated code with hopefully an insertion of a new label
	 */
	@Override
	public String getCodeString() throws NumberNotSetException {
		//Checks that some number has been inserted.
		if (formatedCode.get(storedIndices.get(0)).equals("L")) {
			throw new NumberNotSetException();
		}
		return formatedCode.toString();
		
	}
	/**
	 * This takes from the global counter method in the driver method and adds a unique label in the places that would have 
	 * "L" in the places signified as "@L"
	 */
	public void insertUniqueLabel(){
		for(int i=0;i<storedIndices.size();i++) {
			formatedCode.set(storedIndices.get(i), Driver.globalCounter.toString());
		}
		
	}
	
	

	
}
