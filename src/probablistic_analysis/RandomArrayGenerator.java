/*

 * RandomArrayGenerator.java 1.0 Aug 2, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package probablistic_analysis;

import java.util.ArrayList;
import java.util.Random;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class RandomArrayGenerator {

  private int arrayLength;
  private ArrayList<Integer> newOrder = new ArrayList<Integer>();

  /**
   * 
   * GIVES THE OBJECT AN ARRAY LENGTH SO THAT THE OBJECT CAN RETURN RANDOMIZED
   * INDICES
   *
   * @param anyArrayLength
   */
  public RandomArrayGenerator(int anyArrayLength) {
    arrayLength = anyArrayLength;
  }

  /**
   * 
   * TAKES A MAXIMUM AND MINIMUM INTEGER VALUE AND RETURNS A RANDOMIZED
   * ARRAYLIST OF ARRAY INDICES.
   * 
   * @param usedSoFar
   * @return
   */
  public ArrayList<Integer> randInt() {

    ArrayList<Integer> usedSoFar = new ArrayList<Integer>();

    Random rand = new Random();

    for (int i = 0; i < arrayLength; i++) {

      Integer randomNum = rand.nextInt(arrayLength);

      if (usedSoFar.contains(randomNum)) {
        i--;
      } else {
        newOrder.add(randomNum);
        usedSoFar.add(randomNum);
      }
    }
    return newOrder;
  }

}
