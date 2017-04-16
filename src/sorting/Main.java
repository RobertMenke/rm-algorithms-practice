/*
 * Main.java 1.0 Jul 29, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package sorting;

import javax.swing.JOptionPane;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class Main {
  public static void main(String[] args) {
    String arrayNum = JOptionPane.showInputDialog(null,
            "number of array positions", "array num",
            JOptionPane.QUESTION_MESSAGE);

    int number = Integer.parseInt(arrayNum);

    GenerateArray test = new GenerateArray(number);
    int[] random = test.populateArray();

    // InsertionSort iSort = new InsertionSort();
    // int[] sortedArray = iSort.sort(random);

    MergeSort mSort = new MergeSort(random);
    mSort.sort();

    for (int i = 0; i < random.length; i++) {
      System.out.println(random[i]);
    }
  }
}
