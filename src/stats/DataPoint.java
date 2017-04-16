/*
 * DataPoint.java 1.0 Aug 5, 2015
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
public class DataPoint {

  private double X;
  private double Y;

  public DataPoint(double x, double y) {
    this.X = x;
    this.Y = y;
  }

  /**
   * 
   * Changes two ArrayLists of Doubles into an ArrayList<DataPoint>
   *
   * @param x
   * @param y
   * @return ArrayList<DataPoint>
   */
  public static ArrayList<DataPoint> createArrayList(ArrayList<Double> x,
          ArrayList<Double> y) {
    assert x.size() != y.size();

    ArrayList<DataPoint> result = new ArrayList<DataPoint>();
    for (int i = 0; i < x.size(); i++) {
      result.add(new DataPoint(x.get(i).doubleValue(), y.get(i).doubleValue()));
    }
    return result;
  }

  /**
   * 
   * Change two arrays of doubles into an ArrayList of DataPoint objects
   *
   * @param x
   * @param y
   * @return ArrayList<DataPoint>
   */
  public static ArrayList<DataPoint> createArrayList(double[] x, double[] y) {
    assert x.length != y.length;

    ArrayList<DataPoint> result = new ArrayList<DataPoint>();
    for (int i = 0; i < x.length; i++) {
      result.add(new DataPoint(x[i], y[i]));
    }
    return result;
  }

  /**
   * 
   * Unpacks an ArrayList of DataPoint objects into a single arraylist of
   * doubles for either the x or the y values
   *
   * @param data
   * @param isX
   * @return ArrayList<Double>
   */
  public static ArrayList<Double> unpackDataPoint(ArrayList<DataPoint> data,
          boolean isX) {

    ArrayList<Double> list = new ArrayList<Double>();
    for (int i = 0; i < data.size(); i++) {
      if (isX) {
        list.add(data.get(i).getX());
      } else {
        list.add(data.get(i).getY());
      }
    }
    return list;
  }

  /**
   * Gets ...
   *
   * @return the x
   */
  public double getX() {
    return X;
  }

  /**
   * Gets ...
   *
   * @return the y
   */
  public double getY() {
    return Y;
  }

  /**
   * Sets ...
   *
   * @param aX the x to set
   */
  public void setX(double aX) {
    X = aX;
  }

  /**
   * Sets ...
   *
   * @param aY the y to set
   */
  public void setY(double aY) {
    Y = aY;
  }
}
