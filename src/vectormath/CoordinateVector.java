/*
 * CoordinateVector.java 1.0 Oct 8, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package vectormath;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class CoordinateVector {

  double x = 0;
  double y = 0;

  /**
   * 
   * Gives the object 2 coordinates at construction
   *
   * @param X
   * @param Y
   */
  public CoordinateVector(double X, double Y) {

    this.x = X;
    this.y = Y;
  }

  /**
   * 
   * adds 2 vectors
   *
   * @param one
   * @param two
   * @return
   */
  public CoordinateVector addVectors(CoordinateVector one, CoordinateVector two) {

    double i = one.x + two.x;
    double j = two.y + one.y;

    return new CoordinateVector(i, j);
  }

  /**
   * 
   * Determines the magnitude of a vector
   *
   * @param c
   * @return
   */
  public double determineMagnitude(CoordinateVector c) {

    return Math.sqrt(Math.pow(c.x, 2) + Math.pow(c.y, 2));
  }

  /**
   * 
   * Should check the rules again on this function, but the idea is to get the
   * direction of a vector in radians.
   *
   * @param c
   * @return
   */
  public double getDirection(CoordinateVector c) {

    double arcTan = Math.atan(c.y / c.x);
    if (c.x * -1 > 0 && c.y * -1 > 0) { // If we're in quandrant 3, add PI to
                                        // the result (arctan of - / - is
                                        // positive and gives quandrant 1
                                        // result)

      arcTan += Math.PI;

    } else if (c.x > 0 && c.y * -1 > 0) { // If we're in quandrant 4, add 2PI to
                                          // the result

      arcTan += Math.PI;
    }

    return arcTan;
  }

  public CoordinateVector neutralizeVector(CoordinateVector c1,
          CoordinateVector c2) {

    return new CoordinateVector(c1.x + c2.x, c1.y + c2.y);
  }

  public double neutralMagnitude(CoordinateVector c1, CoordinateVector c2) {

    CoordinateVector neutralVector = this.neutralizeVector(c1, c2);
    return Math.sqrt(Math.pow(neutralVector.x, 2)
            + Math.pow(neutralVector.y, 2));
  }

  /**
   * 
   * Substracts 2 vectors
   *
   * @param one
   * @param two
   * @return
   */
  public CoordinateVector subtractVectors(CoordinateVector one,
          CoordinateVector two) {

    double i = one.x - two.x;
    double j = two.y - one.y;

    return new CoordinateVector(i, j);
  }

  /**
   * 
   * Converts a coordinateVector into 2 unit vectors and returns them in an
   * array of i and j
   *
   * @param c
   * @return
   */
  public CoordinateVector[] toUnitVectorNotation(CoordinateVector c) {

    double magnitude = this.determineMagnitude(c);
    CoordinateVector i = new CoordinateVector(c.x / magnitude, 0);
    CoordinateVector j = new CoordinateVector(0, c.y / magnitude);
    CoordinateVector[] ret = new CoordinateVector[] { i, j };
    return ret;
  }
}
