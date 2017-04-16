/*
 * Correlation.java 1.0 Aug 3, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package stats;

import java.util.ArrayList;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class Correlation {

  private ArrayList<Double> X;
  private ArrayList<Double> Y;
  private int arrayLength;

  public Correlation() {
  }

  /**
   * 
   * Constructor that takes 2 arraylists of doubles as argument to do various
   * correlation calculations with those lists
   *
   * @param x
   * @param y
   */
  public Correlation(ArrayList<Double> x, ArrayList<Double> y) {
    arrayLength = x.size();
    X = new ArrayList<Double>(x);
    Y = new ArrayList<Double>(y);
  }

  /**
   * 
   * Get the average of a list
   *
   * @param aList
   * @return average
   */
  public static double avg(ArrayList<Double> aList) {

    double sum = 0;
    for (int i = 0; i < aList.size(); i++) {
      sum += aList.get(i);
    }

    return sum / aList.size();
  }

  /**
   * 
   * Raise a value to a power
   *
   * @param degree
   * @param value
   * @return result
   */
  public static double exponentiate(int degree, double value) {
    if (degree < 1) {
      throw new IllegalArgumentException("only accepts positive values");
    }

    double newValue = value;
    for (int i = 1; i < degree; i++) {
      newValue = newValue * value;
    }

    return newValue;
  }

  /**
   * 
   * The coefficient of determination measures the degree to which a linear
   * regression model's independent variable can predict the dependent variable
   *
   * @return coefficient of determination
   */
  public double coefficientOfDetermination() {
    return exponentiate(2, correlationCoefficient());
  }

  public double coefficientOfVariation(ArrayList<Double> aList) {
    return stdv(aList) / avg(aList);
  }

  /**
   * 
   * Gets the correlation coefficient of 2 lists
   *
   * @return coefficient of correlation
   */
  public double correlationCoefficient() {
    return diffFromAvg() / (Math.sqrt(diffFromAvgSqrd(X) * diffFromAvgSqrd(Y)));
  }

  /**
   * 
   * Gives the predicted value of the dependent variable based on the
   * independent variable. The equation is in the form y = mx + b
   *
   * @param independentVariable
   * @return dependent variable value
   */
  public double linearRegression(double independentVariable) {
    return b1() * independentVariable + b0();
  }

  /**
   * 
   * Get the standard deviation of a list of numbers
   *
   * @param aList
   * @return standard deviation
   */
  public double stdv(ArrayList<Double> aList) {
    return Math.sqrt(diffFromAvgSqrd(aList) / (arrayLength - 1));
  }

  /**
   * 
   * The B part of the regression equation -> y = mx + B
   *
   * @return B
   */
  private double b0() {
    return avg(Y) - b1() * avg(X);
  }

  /**
   * 
   * This is the M part of the regression equation -> y = Mx + b
   *
   * @return M
   */
  private double b1() {
    return diffFromAvg() / diffFromAvgSqrd(X);
  }

  /**
   * 
   * gets the sum of (Xi - Mx)(Yi - My)
   *
   * @return SUM((Xi - Mx)(Yi - My))
   */
  private double diffFromAvg() {
    double sum = 0;
    for (int i = 0; i < arrayLength; i++) {
      sum += (X.get(i) - avg(X)) * (Y.get(i) - avg(Y));
    }

    return sum;
  }

  /**
   * 
   * Returns the sum of (Xi - Mx)^2
   *
   * @param aList
   * @return sum((Xi - Mx)^2)
   */
  private double diffFromAvgSqrd(ArrayList<Double> aList) {
    double sum = 0;
    for (int i = 0; i < aList.size(); i++) {
      sum += exponentiate(2, (aList.get(i) - avg(aList)));
    }

    return sum;
  }

  /**
   * 
   * Gets the sum of a list
   *
   * @param aList
   * @return sum
   */
  private double sumList(ArrayList<Double> aList) {
    double sum = 0.0;

    for (int i = 0; i < aList.size(); i++) {
      sum += aList.get(i);
    }

    return sum;
  }

  /**
   * 
   * sum of each list item squared
   *
   * @param aList
   * @return sum(Xi^2)
   */
  private double sumSquares(ArrayList<Double> aList) {

    double sum = 0.0;

    for (int i = 0; i < aList.size(); i++) {
      sum += aList.get(i) * aList.get(i);
    }

    return sum;
  }

  /**
   * 
   * Sum of all x*y
   *
   * @return sum(Xi * Yi)
   */
  private double sumXTimesY() {

    double sum = 0.0;

    for (int i = 0; i < X.size(); i++) {
      sum += (X.get(i) * Y.get(i));
    }

    return sum;
  }

}
