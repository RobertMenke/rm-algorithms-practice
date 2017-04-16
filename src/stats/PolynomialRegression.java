/*
 * Polynomial.java 1.0 Aug 5, 2015
 *
 */
package stats;

import matrixmath.Matrix;

import java.util.ArrayList;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 * 
 */
public class PolynomialRegression {

  // private objects
  private Matrix matrix;

  // private class data
  private ArrayList<DataPoint> data;
  private int degree;
  private double[][] leftMatrix;
  private double[][] rightMatrix;

  /**
   * 
   * Creates a new PolynomialRegression object for the purpose of, you guessed
   * it, polynomial regression
   *
   * @param theData
   * @param degrees
   */
  public PolynomialRegression(ArrayList<DataPoint> theData, int degrees) {
    this.data   = new ArrayList<DataPoint>(theData);
    this.degree = degrees;
    matrix = new Matrix();

    leftMatrix = new double[degree + 1][degree + 1];
    rightMatrix = new double[degree + 1][degree + 1];

    generateLeftMatrix();
    generateRightMatrix();
  }

  /**
   * 
   * Tries to select the correct polynomial degree for analysis based on the
   * coefficient of correlation
   *
   * @param correlationCoefficient
   * @return int containing the degree the polynomial should be raised to
   */
  public static int selectPolynomialDegree(double correlationCoefficient) {
    if (correlationCoefficient > -.15 && correlationCoefficient < .15) {
      return 3;
    } else if (correlationCoefficient > -.5 && correlationCoefficient <= -.15
            || correlationCoefficient < .5 && correlationCoefficient >= .15) {
      return 2;
    } else {
      return 1;
    }
  }

  /**
   * 
   * Get the terms needed to predict the Y value based on an X value
   *
   * @return double[] of terms
   */
  public double[] getTerms() {

    return matrix.gaussianJordanElimination(leftMatrix, rightMatrix);
  }

  /**
   * 
   * Predict the Y value based on the terms and an x value
   *
   * @param terms
   * @param x
   * @return double predicted Y value
   */
  public double predictY(double[] terms, double x) {

    double result = 0.0;
    for (int i = terms.length - 1; i >= 0; i--) {
      if (i == 0) {
        result += terms[i];
      } else {
        result += terms[i] * Math.pow(x, i);
      }
    }
    return result;
  }

  /**
   * 
   * Generate the matrix on the left side of the equation
   *
   */
  private void generateLeftMatrix() {

    for (int i = 0; i <= degree; i++) {
      for (int j = 0; j <= degree; j++) {
        if (i == 0 && j == 0) {
          leftMatrix[i][j] = data.size();
        } else {
          leftMatrix[i][j] = sumX(data, i + j);
        }
      }
    }
  }

  /**
   * 
   * Generate the matrix on the right side of the equation
   *
   */
  private void generateRightMatrix() {

    for (int i = 0; i <= degree; i++) {
      if (i == 0) {
        rightMatrix[i][0] = sumY(data, 1);
      } else {
        rightMatrix[i][0] = sumXTimesY(data, i);
      }
    }

  }

  /**
   * 
   * Sum up x values in an ArrayList of DataPoint objects
   *
   * @param anyData
   * @param power - the power each Xi will be raised to
   * @return the sum of x values in ArrayList<DataPoint>
   */
  private double sumX(ArrayList<DataPoint> anyData, int power) {
    double sum = 0.0;
    for (int i = 0; i < anyData.size(); i++) {
      sum += Math.pow(anyData.get(i).getX(), power);
    }
    return sum;
  }

  /**
   * 
   * Sum of x times y
   *
   * @param anyData
   * @param power - power that X will be raised to
   * @return double of sum(Xi ^ power * Yi)
   */
  private double sumXTimesY(ArrayList<DataPoint> anyData, int power) {

    double sum = 0.0;
    for (int i = 0; i < anyData.size(); i++) {
      sum += Math.pow(anyData.get(i).getX(), power) * anyData.get(i).getY();
    }
    return sum;
  }

  /**
   * 
   * Sum all of the Y values in an ArrayList<DataPoint>
   *
   * @param anyData
   * @param power - power each Yi will be raised to
   * @return double sum(Yi ^ power)
   */
  private double sumY(ArrayList<DataPoint> anyData, int power) {
    double sum = 0.0;
    for (int i = 0; i < anyData.size(); i++) {
      sum += Math.pow(anyData.get(i).getY(), power);
    }
    return sum;
  }

}
