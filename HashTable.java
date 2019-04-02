import java.util.ArrayList;


/**
 * 
 * @author msilveus
 *
 * @param <K>
 */
class HashTable<K> {
	private ArrayList<CommandObject>[] hashTable;
	private int capacity;
	private static ArrayList<CommandObject> dataStore;
	private double loadFactor;
	private double loadFactorThreshold;
	/**
	 * 
	 */
	public HashTable() {
		//Sets an initial capacity for the different combinations
		this.capacity=11;
		//Sets the loadFactorThreshold to .75 Source:
		loadFactorThreshold=.75;
		//Initializes the hash table
		this.hashTable = new ArrayList[capacity];
		//Takes the static command and inserts 
		
		for(int i=0;i<dataStore.size();i++) {
			insertStaticCommands(dataStore.get(i));
		}
	}
	/**
	 * 
	 * @param k
	 */
	private void insertStaticCommands(CommandObject k) {
		//This finds the appropriate hash index for preset commands
		int hashIndex =  k.getCommandSegementHash()%capacity;
		//Adds the command to the hash table at the appropriate hashIndex
		hashTable[hashIndex].add(k);
		
	}
	public void insert() {
		//TODO: Figure out parameters for this 
	}
	public void addCommand(String Command, String Segment, String Code) {
		CommandObject
	}
}
