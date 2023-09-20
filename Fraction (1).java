import java.util.*;

public class Fraction implements INumber<Fraction>  {
  int numerator;
  int denominator;
  
  
  
  public Fraction(int n, int d) {
	  numerator = n;
	  denominator = d;
  }
  
 
/**Ethan Gilles
 * Prints a fraction object, by printing the numerator and denominator separated by a slash.
 * @param input - none
 * @return result - no return but prints the fraction.
 */
 public void print() {
	 System.out.print(this.numerator + "/" + this.denominator);
}


/**Ethan Gilles
 * Takes a fraction object and adds another fraction object, returning a new fraction object representing the sum
 * @param input - A fraction object to add to the selected fraction
 * @return result - A simplified fraction object that represents the two fractions added together
 */
public Fraction plus(Fraction input) {
	int commonDenominator = lcm(this.denominator, input.denominator);
	
	//finds the sum of the numerator using the common denominator
    int sumNumerator = (this.numerator * (commonDenominator / this.denominator)) + (input.numerator * (commonDenominator / input.denominator));
    
    //simplifies using GCD
    int gcd = gcd(sumNumerator, commonDenominator);
    int sNumerator = sumNumerator / gcd;
    int sDenominator = commonDenominator / gcd;

    return new Fraction(sNumerator, sDenominator);
	
}


//Coby Andersen
	/**
     * Subtracts another fraction from this fraction and returns the result as a new fraction.
     * @param input The fraction to subtract from this fraction.
     * @return A new fraction representing the result of the subtraction.
     */
public Fraction minus(Fraction input) {
	int commonDenominator = this.denominator * input.denominator;
        int newNumerator = (this.numerator * input.denominator) - (input.numerator * this.denominator);
        return new Fraction(newNumerator, commonDenominator);
    }



   /**
     * Mandy Ho
     * Divides this fraction by another fraction and returns the result as a new fraction.
     *
     * @param input The fraction to divide this fraction by.
     * @return A new fraction representing the result of the division.
     * @throws ArithmeticException If the input fraction has a numerator of zero (division by zero)
     */
    public Fraction divide(Fraction input) {
            if (input.numerator == 0) {
        throw new ArithmeticException("Division by zero is not allowed.");
    }
        int newNumerator = this.numerator * input.denominator;
        int newDenominator = this.denominator * input.numerator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }


/**
 * Nathaniel Serrano
 * Implements multiply() method:
 * Multiplies Fractions by multiplying numerators together to
 * create a new numerator and muliplying denominators together 
 * to create a new denominator and creates a new fraction
 * with the new numerator and denominator.
 * @param input - a Fraction object
 * @return result - a Fraction object equal to the product
 * of the input and the instantiated Fraction.
 */
public Fraction multiply(Fraction input) {
	int newNumerator = this.numerator * input.numerator;
	int newDenominator = this.denominator * input.denominator;
	Fraction result = new Fraction(newNumerator, newDenominator);
	return result;
}



/**Ethan Gilles
 * Gives the greatest common divisor for two integers
 * @param input - two integers
 * @return result - an integer that represents the greatest common divisor of the two inputs.
 */
public static int gcd(int a, int b) {
    if (b == 0) {
        return a;
    } else {
        return gcd(b, a % b);
    }
}


/** Ethan Gilles
 * Gives the least common multiple of two integers
 * @param input - two integers
 * @return result - an integer that represents the least common multiple of two integers
 */
public static int lcm(int a, int b) {
    return (a * b) / gcd(a, b);
}

/** Ethan Gilles
 * returns if two fractions are the same or not
 * @param input - A fraction being compared to the selected fraction
 * @return - true if the fractions are equal, false if otherwise.
 */
	public boolean hasSameValue(Fraction input) {
		// cross multiply
        int cP1 = this.numerator * input.denominator;
        int cP2 = input.numerator * this.denominator;

        // fractions are equal if their cross products are the same
        return cP1 == cP2;
	}


   /**
     * Mandy Ho
     * Sorts an array of fractions in ascending order using the insertion sort algorithm.
     *
     * @param input An array of fractions to be sorted.
     */
    public static void insertionSort(Fraction[] input) {
        for (int i = 1; i < input.length; i++) {
            Fraction current = input[i];
            int j = i - 1;
            // Compare fractions based on their values using hasSameValue method
            while (j >= 0 && !input[j].hasSameValue(current)) {
                if (input[j].numerator * current.denominator > current.numerator * input[j].denominator) {
                    input[j + 1] = input[j];
                    j--;
                } else {
                    break;
                }
            }
            input[j + 1] = current;
        }
    }
// Coby Andersen
	/**
     * Finds the most frequently occurring fraction in an array of fractions.
     *
     * @param input An array of fractions to search for the most frequent fraction.
     * @return The most frequent fraction in the array. If multiple fractions have the same highest frequency,
     *         the first one encountered in the array is returned.
     */
public static Fraction findFrequent(Fraction[] input) {
        Map<Fraction, Integer> frequencyMap = new HashMap<>();
        Fraction mostFrequent = null;
        int maxFrequency = 0;

        for (Fraction fraction : input) {
            int frequency = frequencyMap.getOrDefault(fraction, 0) + 1;
            frequencyMap.put(fraction, frequency);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequent = fraction;
            }
        }

        return mostFrequent;
}
/**
 * Nathaniel Serrano
 * Implements printReverse method:
 * Prints last Fraction in array then creates a 
 * new array that is a copy of the input minus the 
 * last item. This new array is then used as the input
 * for the method's recursive call on itself.
 * @param inputArray - An array of Fractions
 */
public static void printReverse(Fraction[] inputArray) {
	int indexOfLast = inputArray.length-1;
	if (indexOfLast<0)
		return;
	inputArray[indexOfLast].print();
	System.out.print(", ");
	Fraction[] newArray = new Fraction[inputArray.length-1];
	for (int i=0; i<inputArray.length-1;i++) {
		newArray[i] = inputArray[i];
	}	
	printReverse(newArray);
}


public static void main(String[] args) {
	// Test array for insertionSort
        Fraction[] testArray = new Fraction[] { new Fraction(5, 6), new Fraction(3, 4), new Fraction(1, 2),
                new Fraction(7, 8), new Fraction(1, 3) };
        
        
   // Test array for findFrequent
        Fraction[] testArray2 = new Fraction[] { new Fraction(5, 6), new Fraction(3, 4), new Fraction(1, 2),
                new Fraction(5, 6), new Fraction(1, 2), new Fraction(5, 6), new Fraction(8, 15), new Fraction(3, 4)};
	
	//the fraction array described in the lab assignment
	Fraction[] fractionArray = new Fraction[100];
		for(int i=0; i<fractionArray.length; i++) {
			fractionArray[i] = new Fraction(i, i+1);
		}
		
		System.out.println("Outputs show method functionality, these three fractions will be used in the following examples: ");
		Fraction frac1 = fractionArray[1];
		Fraction frac2 = fractionArray[4];
		Fraction frac3 = new Fraction(2,4);

		
		System.out.print("Fraction 1: ");
		frac1.print();
		System.out.println();
		System.out.print("Fraction 2: ");
		frac2.print();
		System.out.println();
		System.out.print("Fraction 3: ");
		frac3.print();
		System.out.println();
		
		/*Ethan Gilles
		 * testing the plus method
		 * outputs show that the method is functional at adding two fractions
		 */
		System.out.println("--- plus Method ---");
		
		// addition of fractions using the plus method
		System.out.print("Fraction 1 + Fraction 2: ");
		Fraction sum = frac1.plus(frac2);
		sum.print();
		System.out.println();
		System.out.print("Fraction 1 + Fraction 3: ");
		sum = frac1.plus(frac3);
		sum.print();
		System.out.println();
		/*Ethan Gilles
		 * testing the minus method
		 * output shows that the method is functional at subtracting two fractions
		 */
		
		System.out.println("--- minus Method ---");
		
		// subtracting of fractions using minus method
		System.out.print("Fraction 1 - Fraction 2: ");
		Fraction sum2 = frac1.minus(frac2);
		sum2.print();
		System.out.println();
		System.out.print("Fraction 1 - Fraction 3: ");
		sum2 = frac1.minus(frac3);
		sum2.print();
		System.out.println();
		
		/*Ethan Gilles
		 * testing the multiply method
		 * outputs show that the method is functional at multiplying two fractions
		 */
		System.out.println("--- multiply Method ---");
		
		// multiplying two fractions using the multiply method
		System.out.print("Fraction 1 * Fraction 2: ");
		Fraction prod = frac1.multiply(frac2);
		prod.print();
		System.out.println();
		System.out.print("Fraction 1 * Fraction 3: ");
		prod = frac1.multiply(frac3);
		prod.print();
		System.out.println();
		
		/*Ethan Gilles
		 * testing the divide method
		 * outputs show that the method is functional at multiplying two fractions
		 */
		System.out.println("--- divideMethod ---");
		
		// multiplying two fractions using the multiply method
		System.out.print("Fraction 1 / Fraction 2: ");
		Fraction prod2 = frac1.divide(frac2);
		prod2.print();
		System.out.println();
		System.out.print("Fraction 1 / Fraction 3: ");
		prod2 = frac1.divide(frac3);
		prod2.print();
		System.out.println();
		
		/*Ethan Gilles
		 * Testing for hasSameValue method
		 * Outputs show that the method is functional to show two fractions with the same value
		 */ 
		System.out.println("--- hasSameValue Method ---");
		
		// comparisons of fractions using the hasSameValue method 
		System.out.println("Fraction 1 compared to Fraction 2: " + frac1.hasSameValue(frac2));
		System.out.println("Fraction 1 compared to Fraction 3: " + frac1.hasSameValue(frac3));
		
		
		/*Ethan Gilles
		 * Testing for findFrequent method
		 * Outputs show that the method is functional to find the most frequent fraction in an array
		 */ 
		System.out.println("--- findFrequent Method ---");
		
		//shows the test array for the findFrequent method
				System.out.print("Test array: [");
				for (Fraction frac : testArray2) {
					frac.print();
					System.out.print(", ");
				}
				System.out.println("]");
		
		//calling the find frequent method to find the most common fraction in the array.
		Fraction mostFrequent = findFrequent(testArray2);
		
		System.out.print("The most frequent fraction was: ");
		mostFrequent.print();
		System.out.println();
		
		/*
                 * Ethan Gilles & Mandy Ho Testing for insertionSort method Outputs show that
                 * the method is functional to sort the array using the insertion sort algorithm
                 */
                System.out.println("--- insertionSort Method ---");
                // Display the array before the sort
               System.out.print("before: [");
               for (Fraction frac : testArray) {
                 frac.print();
                 System.out.print(", ");
                 }
               System.out.println("]");
             insertionSort(testArray);
        // Display the array after the sort
        System.out.print("after:  [");
        for (Fraction frac : testArray) {
            frac.print();
            System.out.print(", ");
        }
        System.out.println("]");
		
		/*Nathaniel Serrano
		 * Testing for printReverse method
		 * Outputs show that the method is functional to print a fraction array in reverse.
		 */ 
		System.out.println("--- printReverse Method ---");
		
		
		//shows the array before printing in reverse
		System.out.print("before: [");
		for (Fraction frac : fractionArray) {
			frac.print();
			System.out.print(", ");
		}
		System.out.println("]");
		
		
		System.out.print("after:  [");
		printReverse(fractionArray);
		System.out.print("]");
		
}

}
