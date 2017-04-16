/*
 * Main.java 1.0 Jul 31, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package maximumsubarray;

import java.util.Arrays;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class Main {

  /**
   * Verb ...
   *
   * @param args
   */
  public static void main(String[] args) {
    GenerateArray gen = new GenerateArray(10);
    int[] myArray = gen.populateArray();

    MaxSubArray algorithm = new MaxSubArray();

    int[] subArray = algorithm.findMaxSubarray(myArray, 0, myArray.length - 1);

    int[] theSubArray = Arrays.copyOfRange(myArray, subArray[0], subArray[1]);

    algorithm.printArray(myArray, "Original Array");
    algorithm.printArray(theSubArray, "Divide and conquer");
    algorithm.printArray(algorithm.scanArray(myArray),
            "O(n) variation of Kadane's algorithm");
    // System.out.println("Sum of max sub array: " + subArray[2]);
  }
}
