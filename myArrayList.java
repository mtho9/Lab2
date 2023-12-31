package lab1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class myArrayList {
    private static final int INITIAL_CAPACITY = 50;
    private static final double GROWTH_RATE = 0.2;
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
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i).toString() + ", ");
        }
        System.out.println();

        // Test the remove method
        Fraction removedFraction = myList.remove(new Fraction(1, 2));
        if (removedFraction != null) {
            System.out.println("Removed Fraction: " + removedFraction.toString());
        } else {
            System.out.println("Fraction not found.");
        }
        System.out.println("Modified list: ");
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i).toString() + ", ");
        }

        // Test the contains method
        boolean containsFraction = myList.contains(new Fraction(3, 4));
        System.out.println("\nContains Fraction (3/4): " + containsFraction);
        containsFraction = myList.contains(new Fraction(1, 2));
        System.out.println("Contains Fraction (1/2): " + containsFraction);

        // Lab 2 Testing
        myArrayList fractionList = new myArrayList();
        fractionList.add(new Fraction(1, 2));
        fractionList.add(new Fraction(3, 4));
        fractionList.add(new Fraction(5, 6));

        myArrayListIterator iterator = fractionList.new myArrayListIterator();

        // hasNext and next
        System.out.println("Forward Iteration:");
        while (iterator.hasNext()) {
            Fraction nextFraction = iterator.next();
            System.out.println("Next Fraction: " + nextFraction);
        }

        // hasPrevious and previous
        System.out.println("Backward Iteration:");
        while (iterator.hasPrevious()) {
            Fraction previousFraction = iterator.previous();
            System.out.println("Previous Fraction: " + previousFraction);
        }

        // add, remove, and set
        System.out.println("Testing add, remove, and set methods:");
        iterator = fractionList.new myArrayListIterator();
        while (iterator.hasNext()) {
            Fraction currentFraction = iterator.next();
            if (currentFraction.equals(new Fraction(3, 4))) {
                // remove and set
                iterator.remove();
                iterator.set(new Fraction(7, 8));
            }
            System.out.println("Current Fraction: " + currentFraction);
        }

        System.out.println("Modified List:");
        for (Fraction fraction : fractionList) {
            System.out.println(fraction);
        }
    }


    /** Coby Andersen
     * Adds a Fraction object to the myArrayList.
     * 
     * @param input The Fraction object to be added to the list.
     * @return true if the addition was successful, false otherwise.
     */
    public boolean add(Fraction input) {
        if (size >= capacity) {
            reconfigure();
        }
        list[size] = input;
        size++;
        return true;
    }

    /**
     * Nathaniel Serrano remove() - Takes a Fraction object as input and returns
     * input if the object is successfully removed from the list; Otherwise returns
     * null
     * 
     * @param input - Fraction object
     * @return - Returns input if object is successfully removed from list;
     *         otherwise returns null if input is not found in list.
     */
    public Fraction remove(Fraction input) {
        int indexOfInput = 0;
        // Check if the item to remove exists in the array
        boolean found = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i].hasSameValue(input)) {
                found = true;
                indexOfInput = i;
                break;
            }
        }
        if (!found) {
            return null;
        }
        // Create a new array to hold the result with one less element
        int numMoved = size - indexOfInput - 1;
        if (numMoved > 0)
            System.arraycopy(list, indexOfInput + 1, list, indexOfInput, numMoved);
        list[--size] = null;

        return input;
    }

    /**
     * Mandy Ho Checks if the given Fraction object exists in the array.
     *
     * @param input The Fraction object to check for existence.
     * @return true if the Fraction is found in the array, false otherwise.
     */
    public boolean contains(Fraction input) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null)
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
        for (int i = 0; i < list.length; i++) {
            if (i == index)
                return list[i];
        }
        return null;
    }

    /**
     * Ethan Gilles whenever the array is full, call reconfigure. This will copy the
     * current array into a bigger one and set the internal array to it
     */
    public void reconfigure() {

        // changes the capacity of the array
        capacity += capacity * GROWTH_RATE;

        // new fraction array of correct size
        Fraction[] bigArray = new Fraction[capacity];

        // set values equal
        for (int i = 0; i < size; i++) {
            bigArray[i] = list[i];
        }

        // array that is instanced with the arrayList is turned into the big array.
        list = bigArray;
    }

    private class myArrayListIterator implements Iterator<Fraction> {
        Fraction lastReturned = null; // last returned fraction
        int index = 0; // index where the iterator is
        boolean nextCalled = false;

        public myArrayListIterator() {

        }

        /**
         * Coby Andersen Adds a fraction object to the myArrayList frac the Fraction
         * object to be added to the ist
         */
        public void add(Fraction frac) {
            int index = currentIndex + 1;
            myArrayList.this.add(index, frc);
            currentIndex++;
            lastReturnedIndex = -1;
        }

        /**
         * Coby Andersen returns true if there is another token in its input
         * 
         * @param true if there is a next element, false otherwise
         */
        public boolean hasNext() {
            return currentIndex < size - 1;

        }

        /**
         * Coby Andersen checks the reverse direction to see if there are more elements
         * and changes the iterator Automatically throws exceptions Returns true if
         * there is a previous element, false otherwise
         */
        public boolean hasPrevious() {
            return currentIndex > 0;

        }

        /**
         * Coby Andersen Adds a fraction object to the myArrayList
         * 
         * frac the Fraction object to be added to the list
         */
        public void add(Fraction frac) {
            index++;
            myArrayList.this.add(index, frc);
            index++;
            lastReturned = null;
        }

        /**
         * Coby Andersen returns true if there is another token in its input
         * 
         * @param true if there is a next element, false otherwise
         */
        public boolean hasNext() {
            return index < size - 1;

        }

        /**
         * Coby Andersen checks the reverse direction to see if there are more elements
         * and changes the iterator
         *
         * Returns true if there is a previous element, false otherwise
         */
        public boolean hasPrevious() {
            return index > 0;

        }

        /**
         * Mandy Ho Returns the next element in the iteration and advances the iterator
         * position.
         *
         * @return The next Fraction element in the iteration.
         * @throws NoSuchElementException if there are no more elements to iterate over.
         */
        public Fraction next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = index; // Save the index of the last element returned
            return list[index++];
        }

        /**
         * Mandy Ho Returns the index of the next element
         *
         * @return The index of the next element
         */
        public int nextIndex() {
            return index++;
        }

        /**
         * Ethan Gilles sets last returned as the previous item reduces index by one
         * then returns the actual fraction that is set to last returned
         */
        public Fraction previous() {
            lastReturned = list[index - 1];
            index--;
            nextCalled = true;
            return list[index - 1];
        }

        /**
         * Ethan Gilles if index is 0, throws and exception else it returns the index
         * before where the iterator is
         */
        public int previousIndex() {
            if (index == 0) {
                return -1;
            }
            return index - 1;
        }

        /**
         * Nathaniel Serrano remove() - Removes the lastReturned value in iterator
         */
        public void remove() {
            if (!nextCalled)
                throw new UnsupportedOperationException("remove");
            myArrayList.remove(lastReturned);
            nextCalled = false;
        }

        /**
         * Nathaniel Serrano set() - Sets the lastReturned value in iterator to be the
         * inputted Fraction object.
         * 
         * @param frac - A Fraction object
         */
        public void set(Fraction frac) {
            if (!nextCalled)
                throw new UnsupportedOperationException("set");
            lastReturned = frac;
            nextCalled = false;

        }
    }

}
