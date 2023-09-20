package lab1;

public class myArrayList {
	private static final int INITIAL_CAPACITY = 50;
	private static final double GROWTH_RATE= 0.2;
	private Fraction[] list = null;
	private int capacity = INITIAL_CAPACITY;
	private int size = 0;
	
	
	
	public myArrayList() {
		list = new Fraction[INITIAL_CAPACITY];
    }

	public myArrayList(int n) {
		list = new Fraction[n];
	}
	
	public static void main(String[] args) {
		 myArrayList myList = new myArrayList();

	     // Test the add method
		 Fraction frac1 = new Fraction(1, 2);
		 Fraction frac2 = new Fraction(3, 4);
		 Fraction frac3 = new Fraction(5, 6);
	     myList.add(frac1);
	     myList.add(frac2);
	     myList.add(frac3);
	     System.out.println("Original list: ");
	     for (int i=0;i<myList.size(); i++) {
	    	 System.out.print(myList.get(i).toString()+", ");
	     }
	     System.out.println();

	     // Test the remove method
	     Fraction removedFraction = myList.remove(new Fraction(1, 2));
	     if (removedFraction != null) {
	         System.out.println("Removed Fraction: "+ removedFraction.toString());
	     } else {
	         System.out.println("Fraction not found.");
	     }
	     System.out.println("Modified list: ");
	     for (int i=0;i<myList.size(); i++) {
    	 System.out.print(myList.get(i).toString() + ", ");
     }

	     // Test the contains method
	     boolean containsFraction = myList.contains(new Fraction(3, 4));
	     System.out.println("\nContains Fraction (3/4): " + containsFraction);
	     containsFraction = myList.contains(new Fraction(1,2));
	     System.out.println("Contains Fraction (1/2): "+ containsFraction);
	     
	 }

	//Coby Andersen
	/**
 * Adds a Fraction object to the myArrayList.
 * @param input The Fraction object to be added to the list.
 * @return true if the addition was successful, false otherwise.
 */
	  public boolean add(Fraction input) {
		if(size >= capacity) {
			reconfigure();
		}
		  list[size] = input;
		  size++;
		  return true;
    }

	/**
	 * Nathaniel Serrano
	 * remove() - Takes a Fraction object as input and returns input if the 
	 * object is successfully removed from the list; Otherwise returns null
	 * @param input - Fraction object
	 * @return - Returns input if object is successfully removed from list;
	 * otherwise returns null if input is not found in list.
	 */
	public Fraction remove(Fraction input) {
		int indexOfInput=0;
		      // Check if the item to remove exists in the array
        boolean found = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i].hasSameValue(input)) {
                found = true;
                indexOfInput=i;
                break;
            }
        }
        if (!found) {
            return null;
        }
        // Create a new array to hold the result with one less element
        int numMoved = size - indexOfInput - 1;
        if (numMoved > 0)
        System.arraycopy(list, indexOfInput+1, list, indexOfInput, numMoved);
        list[--size] = null;
        
        return input;
    }
		
	
	
/**
 * Mandy Ho
 * Checks if the given Fraction object exists in the array.
 *
 * @param input The Fraction object to check for existence.
 * @return true if the Fraction is found in the array, false otherwise.
 */
public boolean contains(Fraction input) {
    for (int i = 0; i < list.length; i++) {
    	if (list[i]==null)
    		return false;
        if (list[i].hasSameValue(input))
            return true; // Found a matching fraction
    }
    return false; // Fraction not found in the list 
}

	
	public int size() {
		return size;
		
	}
	
	public Fraction get(int index) {
		for (int i=0;i<list.length;i++) {
			if (i == index)
				return list[i];
		}
		return null;
	}
	
	
	/**Ethan Gilles
	 * whenever the array is full, call reconfigure. This will copy the current array into a bigger one
	 * and set the internal array to it
	 */
	public void reconfigure() {
		
		//changes the capacity of the array
		capacity += capacity*GROWTH_RATE;
		
		
		//new fraction array of correct size
		Fraction[] bigArray = new Fraction[capacity];
		
		//set values equal
		for(int i = 0; i < size; i++) {
			bigArray[i] = list[i];
		}
		
		//array that is instanced with the arrayList is turned into the big array.
		list = bigArray;
	}
	
	private class myArrayListIterator implements Iterator<Fraction>{
		Fraction lastReturned = null; // last returned fraction
		int index = 0; //index where the iterator is
		
		public myArrayListIterator() {
			
		}
		
		//Coby Andersen
		public void add(Fraction frac) {
			index++;
           	myArrayList.this.add(index, frc);
           	index++;
           	lastReturned = null;
        }

		//Coby Andersen
		public boolean hasNext() {
			return index < size -1;
			
		}
		
		//Coby Andersen
		public boolean hasPrevious() {
			return index > 0;

		}
		
		public Fraction next() {
			
			
		}
		
		public int nextIndex() {
			
			
		}
		
		/*Ethan Gilles
		 * sets last returned as the previous item
		 * reduces index by one
		 * then returns the actual fraction that is set to last returned
		 */
		public Fraction previous() {
			lastReturned = list[i-1];
			index--;
			return list[i-1];
		}
		
		/* Ethan Gilles
		 * if index is 0, throws and exception
		 * else it returns the index before where the iterator is
		 */
		public int previousIndex() {
			if(index == 0 ) {
				return -1;
			}
			return index-1;
		}
		
		public void remove() {
			
		}
		
		public void set(Fraction frac) {
			
		}
	}
	
}


