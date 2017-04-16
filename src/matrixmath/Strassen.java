/*
 * Strassen.java 1.0 Aug 1, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package matrixmath;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class Strassen {
  int[][] A;
  int[][] B;

  public void SplitArray(int[][] anyMatrix) {
    int half = (int) Math.floor(anyMatrix.length / 2);
    int numberOfElementsInArray = anyMatrix[0].length;
    A = new int[half][numberOfElementsInArray];
    B = new int[anyMatrix.length - half][numberOfElementsInArray];
    for (int i = 0; i < anyMatrix.length; i++) {
      if (i < half) {
        A[i] = anyMatrix[i];
      } else {
        B[i] = anyMatrix[i];
      }

    }
  }
  /*
   * public int[][] strassenMethod(int[][] anyMatrix1, int[][] anyMatrix2) {
   * 
   * }
   */
}
