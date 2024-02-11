import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

	private List<List<Entry<K,V>>> 	table;
	private int	count;
	private int  tableSize;

	// For Part III
	private long getLoops;
	private long putLoops;
	

	public HashMap() {
		tableSize = 1000003; // prime number
		table = new ArrayList<List<Entry<K,V>>>(tableSize);

		// initializes table as a list of empty lists
		for (int i = 0; i < tableSize; i++) {
			table.add(new LinkedList<Entry<K,V>>());
		}

		count = 0;

		// For Part III:
		resetGetLoops();
		resetPutLoops();
	}

	// For Part III
	public long getGetLoopCount() {
		return getLoops;
	}

	// For Part III
	public long getPutLoopCount() {
		return putLoops;
	}

	// For Part III
	public void resetGetLoops() {
		getLoops = 0;
	}
	
	// For Part III
	public void resetPutLoops() {
		putLoops = 0;
	}

	public boolean containsKey(K key) {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;
		List<Entry<K,V>> list = table.get(index);
		Iterator<Entry<K,V>> iter = list.iterator();
		while(iter.hasNext()){
			Entry<K,V> entry = iter.next();
			if(entry.getKey().equals(key)){
				return true;
			}
		}
		return false; // so it compiles
	}

	public V get (K key) throws KeyNotFoundException {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;
		List<Entry<K,V>> list = table.get(index);
		Iterator<Entry<K,V>> iter = list.iterator();
		while(iter.hasNext()){
			Entry<K,V> entry = iter.next();
			if(entry.getKey().equals(key)){
				return entry.getValue();
			}
		}
		throw new KeyNotFoundException();
	}


	public List<Entry<K,V> > entryList() {
		List <Entry<K,V>> resultList = new LinkedList<Entry<K,V>>();
		// TODO: complete the rest of this method

		// Tip: you will need to iterate through each index in the table (and each index holds a list)
		//      you will THEN need to iterate through each element in the linked list chain at a 
		//      specific index and add each element to l
		for(int index = 0; index < tableSize; index++){
			List<Entry<K,V>> list = table.get(index);
			Iterator<Entry<K,V>> iter = list.iterator();
			while(iter.hasNext()){
				resultList.add(iter.next());
			}
		}
		return resultList;
	}
	
	public void put(K key, V value){
		int index = Math.abs(key.hashCode()) % tableSize;
		Entry<K,V> to_add = new Entry<K,V>(key, value);
		List<Entry<K,V>> list = table.get(index);
		boolean keyExists = false;
	
		Iterator<Entry<K,V>> iter = list.iterator();

		while(iter.hasNext()){
			Entry<K,V> entry = iter.next();
			if(entry.getKey().equals(key)){
				entry.value = value;  // Update the value if key is found
				keyExists = true;
				break;  // Break the loop as we've found and updated the key
			}
		}	
		if (!keyExists) {
			list.add(to_add);  // Add the new entry only if the key does not exist
			count++;
		}
	}
	
	public int size() {
		return count;
	}

    public void clear() {
		for(int i = 0; i < tableSize; i++) {
			table.get(i).clear();
		}
        count = 0;
    }

}