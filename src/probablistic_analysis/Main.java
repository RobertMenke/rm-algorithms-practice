/*
 * Main.java 1.0 Aug 2, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package probablistic_analysis;

import java.util.ArrayList;

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
    // TODO Auto-generated method stub

    int[] anyArray = new int[10];
    for (int counter = 0; counter < 10; counter++) {
      anyArray[counter] = counter;

    }
    RandomArrayGenerator gen = new RandomArrayGenerator(anyArray.length);
    ArrayList<Integer> result = gen.randInt();
    System.out.print(result);

  }
}
