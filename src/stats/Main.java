/*
 * Main.java 1.0 Aug 3, 2015
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
public class Main {

  /**
   * Verb ...
   *
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    ArrayList<DataPoint> data = new ArrayList<>();
    data.add(new DataPoint(0.0, 1.0));
    data.add(new DataPoint(1.0, 3.0));
    data.add(new DataPoint(2.0, 6.0));
    data.add(new DataPoint(3.0, 9.0));
    data.add(new DataPoint(4.0, 12.0));
    data.add(new DataPoint(5.0, 15.0));
    data.add(new DataPoint(6.0, 18.0));

    ArrayList<Double> X = DataPoint.unpackDataPoint(data, true);
    ArrayList<Double> Y = DataPoint.unpackDataPoint(data, false);

    Correlation cor = new Correlation(X, Y);

    double cc = cor.correlationCoefficient();

    System.out.println("Coefficient of correlation is: " + cc);
    int degree = PolynomialRegression.selectPolynomialDegree(cc);

    PolynomialRegression p = new PolynomialRegression(data, 3); // change
                                                                // degree
                                                                // manually
                                                                // if
                                                                // appropriate

    double[] terms = p.getTerms();
    double prediction = p.predictY(terms, 5.0);


    for (int i = 0; i < terms.length; i++) {
      System.out.println("term" + (i + 1) + " " + terms[i]);
    }

    System.out.println("");

    System.out.println("result: " + prediction);
  }

}
