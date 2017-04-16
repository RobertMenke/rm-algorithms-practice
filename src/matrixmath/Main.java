/*
 * Main.java 1.0 Aug 1, 2015
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
public class Main {

  public static void main(String[] args) {
    GenerateMatrix gen = new GenerateMatrix(4, 4);
    GenerateMatrix gen2 = new GenerateMatrix(4, 4);
    GenerateMatrix gen3 = new GenerateMatrix(4, 1);
    double[][] aMatrix1 = gen.populateArray();
    double[][] aMatrix2 = gen2.populateArray();
    double[][] aMatrix3 = gen3.populateArray();

    Matrix m = new Matrix();

    double[][] newMatrix = m.matrixProduct(aMatrix1, aMatrix2);
    double[][] rowReplace = m.rowSwitch(aMatrix1, 0, 2);

    double[][] identityMatrix = m.identityMatrix(aMatrix1);
    double[][] row1Multiplied = m.multiplyRow(aMatrix1, 0, 2);

    double[][] combinedMatrix = m.combineMatrices(aMatrix1, aMatrix3);
    double[][] forwardElimination = m.forwardElimination(combinedMatrix);

    double[][] leftMatrix = new double[3][3];

    leftMatrix[0][0] = 25.0;
    leftMatrix[0][1] = 5.0;
    leftMatrix[0][2] = 1.0;
    leftMatrix[1][0] = 64.0;
    leftMatrix[1][1] = 8.0;
    leftMatrix[1][2] = 1.0;
    leftMatrix[2][0] = 144.0;
    leftMatrix[2][1] = 12.0;
    leftMatrix[2][2] = 1.0;

    double[][] rightMatrix = new double[3][1];
    rightMatrix[0][0] = 106.8;
    rightMatrix[1][0] = 177.2;
    rightMatrix[2][0] = 279.2;

    double[] testFull = m.gaussianJordanElimination(leftMatrix, rightMatrix);

    m.printMatrix(aMatrix1, "Matrix 1");
    // m.printMatrix(aMatrix2, "Matrix 2");
    // m.printMatrix(newMatrix, "Matrix Product");
    // m.printMatrix(identityMatrix, "Identity Matrix");
    // m.printMatrix(rowReplace, "Replace Rows");
    // m.printMatrix(row1Multiplied, "Row 1 Multiplied");
    // m.printMatrix(combinedMatrix, "combined");
    // m.printMatrix(forwardElimination, "forward elimination");
    System.out.println("Backward Integration");

    for (int i = 0; i < testFull.length; i++) {
      System.out.println(testFull[i]);
    }

    Matrix khan = new Matrix();

    double[][] E = new double[][] { { 1.0, 0.0, 2.0 }, { 1.0, 1.0, 2.0 },
            { 2.0, 2.0, 1.0 } };

    double[][] identity = khan.identityMatrix(E);

    khan.gaussianJordanElimination(E, identity);
  }

}
