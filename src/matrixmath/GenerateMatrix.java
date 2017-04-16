/*
 * GenerateArray.java 1.0 Jul 29, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package matrixmath;

import java.util.Random;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class GenerateMatrix {
  public double[][] randomArray;
  public int rowPositions;
  public int columnPositions;

  public GenerateMatrix(int arowPositions, int acolumnPositions) {
    randomArray     = new double[arowPositions][acolumnPositions];
    rowPositions    = arowPositions;
    columnPositions = acolumnPositions;
  }

  public static double randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    double randomNum = rand.nextInt((max - min + 1) + min);

    return randomNum;
  }

  public double[][] populateArray() {
    for (int i = 0; i < rowPositions; i++) {
      for (int x = 0; x < columnPositions; x++) {
        randomArray[i][x] = randInt(0, 5);
      }
    }
    return randomArray;
  }
}
