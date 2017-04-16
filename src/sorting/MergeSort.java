/*
 * MergeSort.java 1.0 Jul 29, 2015
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
public class MergeSort {

  private int[] anArray;

  /**
   * Creates ...
   *
   * @param aRandom
   */
  public MergeSort(int[] aRandom) {
    anArray = aRandom;
  }

  public void merge(int[] arr1, int[] arr2) {
    int indexArr1 = 0;
    int indexArr2 = 0;

    int indexNewArr = 0;

    while (indexArr1 < arr1.length && indexArr2 < arr2.length) {
      if (arr1[indexArr1] < arr2[indexArr2]) {
        anArray[indexNewArr] = arr1[indexArr1];
        indexArr1++;
        indexNewArr++;
      } else {
        anArray[indexNewArr] = arr2[indexArr2];
        indexArr2++;
        indexNewArr++;
      }
    }

    while (indexArr1 < arr1.length) {
      anArray[indexNewArr] = arr1[indexArr1];
      indexArr1++;
      indexNewArr++;
    }

    while (indexArr2 < arr2.length) {
      anArray[indexNewArr] = arr2[indexArr2];
      indexArr2++;
      indexNewArr++;
    }
  }

  public void sort() {

    if (anArray.length <= 1) {
      return;
    }

    int[] first = new int[anArray.length / 2];
    int[] second = new int[anArray.length - first.length];
    // Copy the first half of a into first, the second half into
    // second
    for (int i = 0; i < first.length; i++) {
      first[i] = anArray[i];
    }
    for (int i = 0; i < second.length; i++) {
      second[i] = anArray[first.length + i];
    }

    MergeSort newLeft = new MergeSort(first);
    MergeSort newRight = new MergeSort(second);

    newLeft.sort();
    newRight.sort();

    merge(first, second);
  }

}
