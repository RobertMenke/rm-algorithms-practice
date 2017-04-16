/*
 * MaxSubArray.java 1.0 Jul 31, 2015
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
public class MaxSubArray {

  public int arrayStats(int[] anArray) {
    int sum = 0;

    for (int i = 0; i < anArray.length; i++) {
      sum += anArray[i];
    }
    return sum;
  }

  /**
   * recursively find the maximum sub array Verb ...
   *
   * @param anArray
   * @param lowIndex
   * @param highIndex
   * @return int[] max sub array
   */
  public int[] findMaxSubarray(int[] anArray, int lowIndex, int highIndex) {
    // printArray(anArray);

    if (1 > highIndex) {
      return new int[] { 0, 0, 0 };
    }

    if (highIndex == lowIndex) {
      // System.out.println("triggered" + anArray[lowIndex]);
      return new int[] { lowIndex, highIndex, Math.max(0, anArray[lowIndex]) };

    }

    int midIndex = (int) Math.floor((double) (lowIndex + highIndex) / 2);

    /*
     * int[] leftResult = maxCrossingSubarray(leftSide); int[] leftArray =
     * findMaxSubarray(leftSide, 0, leftSide.length - 1);
     */

    // FIND MAX CROSSING TO THE LEFT
    int[] leftData = new int[2];
    // int[] leftSide = Arrays.copyOfRange(anArray, 0, midIndex + 1);
    int leftMax = 0;
    int sum = 0;
    int leftIndex = 1000;

    for (int i = midIndex; i >= 0; i--) {
      sum += anArray[i];
      if (sum >= leftMax) {
        leftMax = sum;
        leftIndex = i;
      }
    }

    leftData[0] = leftIndex;
    leftData[1] = sum;

    // FIND MAX CROSSING TO THE RIGHT

    /*
     * int[] rightSide = Arrays.copyOfRange(anArray, midIndex, highIndex);
     * System.out.println("Look at right half"); printArray(rightSide); int[]
     * rightResult = maxCrossingSubarray(rightSide); int[] rightArray =
     * findMaxSubarray(rightSide, 0, rightSide.length - 1);
     */
    int[] rightData = new int[2];
    int rightMax = 0;
    int rightIndex = 1000;
    sum = 0;

    for (int x = midIndex + 1; x <= highIndex; x++) {
      sum += anArray[x];
      if (sum >= rightMax) {
        rightMax = sum;
        rightIndex = x;
      }
    }

    rightData[0] = rightIndex;
    rightData[1] = sum;

    // CALL FUNCTION FOR MID ARRAY
    int[] midArray = maxCrossingSubarray(anArray);
    int midMax = midArray[2];

    findMaxSubarray(anArray, 0, highIndex - 1);
    /*
     * 
     * int leftMax = arrayStats(leftArray);
     * 
     * int rightMax = arrayStats(rightArray);
     */

    if (leftData[1] >= rightData[1] && leftData[1] >= midMax) {
      System.out.println("left");
      return new int[] { leftData[0], midIndex, sum };
    } else if (rightData[1] >= leftData[1] && rightData[1] >= midMax) {
      System.out.println("right");
      return new int[] { rightData[1], highIndex, sum };
    } else {
      System.out.println("mid");
      return midArray;
    }

  }

  /**
   * 
   * This method finds the maximum subarray that crosses the midpoint of an
   * array
   *
   * @param anyArray
   * @return
   */
  public int[] maxCrossingSubarray(int[] anyArray) {

    int leftSum = -10000;
    int maxLeftIndex = -1;
    int sum = 0;
    int maxIndex = anyArray.length - 1;
    int midIndex = (int) Math.floor((double) (anyArray.length - 1) / 2);

    for (int i = midIndex; i >= 0; i--) {
      sum = sum + anyArray[i];
      if (sum > leftSum) {
        leftSum = sum;
        maxLeftIndex = i;
      }
    }

    int maxRightIndex = -1;
    int rightSum = -10000;
    sum = 0;

    for (int j = midIndex + 1; j < anyArray.length; j++) {
      sum = sum + anyArray[j];

      if (sum > rightSum) {
        rightSum = sum;
        maxRightIndex = j;
      }
    }

    return new int[] { maxLeftIndex, maxRightIndex + 1, leftSum + rightSum };
  }

  /**
   * 
   * Prints an array on one line
   *
   * @param anyArray
   * @param arrayText - the text label for the array
   */
  public void printArray(int[] anyArray, String arrayText) {
    System.out.print(arrayText + " [");
    for (int i = 0; i < anyArray.length; i++) {
      if (i == anyArray.length - 1) {
        System.out.print(" " + anyArray[i]);
      } else {
        System.out.print(" " + anyArray[i] + ", ");
      }

    }
    System.out.println("]");
  }

  /**
   * 
   * Scans the array with multiple starting/ending points to determine the
   * maximum subarray
   *
   * @param array
   * @return int[] with the maximum possible subarray
   */
  public int[] scanArray(int[] array) {
    if (array == null || array.length == 0) {
      throw new IllegalArgumentException(
              "Array cannot be null and must contain at least one element");
    }

    int maxStartingIndex = 0;
    int maxEndingIndex = 0;
    int max = array[0];

    int startLoop = 0;
    outer: while (startLoop < array.length) {
      int currentSum = 0;
      for (int innerLoop = startLoop; innerLoop < array.length; innerLoop++) {
        currentSum += array[innerLoop];

        // Check for a new record
        if (currentSum > max) {
          max = currentSum;
          maxStartingIndex = startLoop;
          maxEndingIndex = innerLoop;
        }

        // If we're below zero than there's no need to continue on this path.
        if (currentSum <= 0) {
          startLoop = innerLoop + 1;
          continue outer;
        }

      }
      break outer;
    }

    return Arrays.copyOfRange(array, maxStartingIndex, maxEndingIndex + 1);
  }

}
