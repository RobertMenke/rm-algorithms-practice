/*
 * GenerateArray.java 1.0 Jul 29, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package maximumsubarray;

import java.util.Random;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class GenerateArray {
  public int[] randomArray;
  public int numPositions;

  public GenerateArray(int aNumber) {
    randomArray = new int[aNumber];
    numPositions = aNumber;
  }

  public static int randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }

  public int[] populateArray() {
    for (int i = 0; i < numPositions; i++) {
      randomArray[i] = randInt(-100, 100);
    }
    return randomArray;
  }
}
