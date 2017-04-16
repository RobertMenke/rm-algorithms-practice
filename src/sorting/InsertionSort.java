/*
 * InsertionSort.java 1.0 Jul 29, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package sorting;


/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class InsertionSort {

  public int[] sort(int[] anArray) {

    for (int i = 1; i < anArray.length; i++) {
      int next = anArray[i];
      // Move all larger elements up
      int j = i;
      while (j > 0 && anArray[j - 1] > next) {
        anArray[j] = anArray[j - 1];
        j--;
      }
      // Insert the element
      anArray[j] = next;
    }
    return anArray;
  }
}
