/*
 * MultiplyMatrix.java 1.0 Aug 1, 2015
 *
 * Copyright (c) 2015 David J. Powell
 * Campus Box 2320, Elon University, Elon, NC 27244
 */
package matrixmath;

import java.util.Arrays;

/**
 * Models ...
 *
 * @author rbmenke
 * @version 1.0
 * @since 1.0
 */
public class Matrix {

    double[][] A;
    double[][] B;

    /**
     * Now that the matrix is in an upper right triangle, we can recursively solve
     * for each coefficient, using the result of the previously discovered
     * coefficient solve for the polynomial at k - 1 where k is used to measure
     * polynomial degrees
     *
     * @param anyMatrix
     * @param array
     * @param row
     * @param col
     * @return an array of terms that a will be plugged into the equation for
     * polynomial regression
     */
    public double[] backwardsIntegration(double[][] anyMatrix, double[] array,
            int row, int col) {
        this.printMatrix(anyMatrix, "any matrix");

        if (row < 0 || col < 0) {

            return array;
        }
        else {

            int    rows    = anyMatrix.length;
            int    cols    = anyMatrix[0].length - 1;
            double current = 0.0;
            int    counter = 0;

            for (int i = cols - 1; i >= col; i--) {

                if (i == col) {
                    // If this is the first iteration through, divide the result by the
                    // final entry in the matrix
                    current = anyMatrix[row][cols] / anyMatrix[row][i];
                }
                else {
                    // Otherwise, subtract the result by the current matrix value
                    anyMatrix[row][cols] -= anyMatrix[row][i] * array[rows - 1 - counter];
                    System.out.println("calc: " + anyMatrix[row][i] * array[rows - 1 - counter] +  " arr: " + array[rows - 1 - counter] + " mat: " + anyMatrix[row][i] + " pos: " + (rows - 1 - counter));
                    counter++;

                }
            }
            array[row] = current;
            return backwardsIntegration(anyMatrix, array, row - 1, col - 1);
        }

    }

    /**
     * Combines a matrix one the left side of the equation with the product on the
     * right side for the purpose of gaussian-jordan elimination
     *
     * @param matrix1
     * @param matrix2
     * @return
     */
    public double[][] combineMatrices(double[][] matrix1, double[][] matrix2) {
        int        rows         = matrix1.length;
        int        cols         = matrix1[0].length + matrix2[0].length;
        double[][] returnMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (j > matrix1[0].length - 1) {
                    returnMatrix[i][j] = matrix2[i][j - matrix1[0].length];
                }
                else {
                    returnMatrix[i][j] = matrix1[i][j];
                }
            }
        }

        return returnMatrix;
    }

    /**
     * Method for computing the determinant of a matrix Taken from
     * http://www.sanfoundry.com/java-program-compute-determinant-matrix/
     *
     * @param A - matrix
     * @param N - number of rows/columns (must be square)
     * @return
     */
    public double determinant(double A[][], int N) {
        double det = 0;
        if (N == 1) {
            det = A[0][0];
        }
        else if (N == 2) {
            det = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        }
        else {
            det = 0;
            for (int j1 = 0; j1 < N; j1++) {

                double[][] m = new double[N - 1][];
                for (int k = 0; k < (N - 1); k++) {

                    m[k] = new double[N - 1];
                }
                for (int i = 1; i < N; i++) {

                    int j2 = 0;
                    for (int j = 0; j < N; j++) {

                        if (j == j1) {
                            continue;
                        }
                        m[i - 1][j2] = A[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                        * determinant(m, N - 1);
            }
        }
        return det;
    }

    /**
     * Conducts forward Elimination
     *
     * @param {double[][]} anyMatrix
     * @return a matrix in the form of an upper right triangle matrix
     */
    public double[][] forwardElimination(double[][] anyMatrix) {
        int        rows         = anyMatrix.length;
        int        cols         = anyMatrix[0].length;
        double[][] returnMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                returnMatrix[i][j] = anyMatrix[i][j];
            }
        }

        for (int x = 0; x < rows - 1; x++) {

            for (int z = x; z < rows - 1; z++) {

                double numerator   = returnMatrix[z + 1][x];
                double denominator = returnMatrix[x][x];
                double result      = numerator / denominator;

                for (int i = 0; i < cols; i++) {
                    returnMatrix[z + 1][i] = returnMatrix[z + 1][i]
                            - (result * returnMatrix[x][i]);
                }
            }
        }

        printMatrix(returnMatrix, "Test fwd");
        return returnMatrix;

    }

    /**
     * This method acts like a controller and returns the terms from a matrix
     *
     * @param leftMatrix
     * @param rightMatrix
     * @return terms
     */
    public double[] gaussianJordanElimination(double[][] leftMatrix, double[][] rightMatrix) {

        double[][] combined = this.combineMatrices(leftMatrix, rightMatrix);

        this.printMatrix(combined, "combined");

        double[][] fwdIntegration = this.forwardElimination(combined);
        this.printMatrix(fwdIntegration, "fwd");

        double[] backwardSubstitution = this.backwardsIntegration(fwdIntegration,
                new double[fwdIntegration.length], fwdIntegration.length - 1,
                fwdIntegration[0].length - 2);

        for (int i = 0; i < backwardSubstitution.length; i++) {
            System.out.println("Hello: " + backwardSubstitution[i]);
        }
        return backwardSubstitution;
    }

  /*
   * public double[][] gaussianJordanElimination(double[][] anyMatrix) {
   * 
   * }
   */

    /**
     * GETS THE IDENTITY MATRIX FOR AN ARRAY OF doubleS
     *
     * @param anyMatrix
     * @return identityMatrix
     */
    public double[][] identityMatrix(double[][] anyMatrix) {
        int        rows           = anyMatrix.length;
        int        cols           = anyMatrix[0].length;
        double[][] identityMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == i) {
                    identityMatrix[i][j] = 1;
                }
                else {
                    identityMatrix[i][j] = 0;
                }
            }
        }
        return identityMatrix;
    }

    /**
     * multiplies 2 matrices
     *
     * @param anyMatrix1
     * @param anyMatrix2
     * @return matrix product
     */
    public double[][] matrixProduct(double[][] anyMatrix1, double[][] anyMatrix2) {

        int numCols1 = anyMatrix1.length;

        int numRows2 = anyMatrix2[0].length;

        if (numCols1 != numRows2) {
            throw new IllegalArgumentException(
                    "Number of columns in 1 has to equal number of rows in 2");
        }

        double[][] product = new double[numRows2][numCols1];

        for (int rows = 0; rows < numRows2; rows++) {
            for (int cols = 0; cols < numCols1; cols++) {
                product[rows][cols] = doMultiplication(anyMatrix1, anyMatrix2, rows,
                        cols, numCols1);
            }
        }
        return product;
    }

    /**
     * multiplies values of a matrix row
     *
     * @param anyMatrix
     * @param rowNum
     * @param multiplier
     * @return anyMatrix with specified row multiplied by specified multiplier
     */
    public double[][] multiplyRow(double[][] anyMatrix, int rowNum,
            double multiplier) {

        int        rows    = anyMatrix[0].length;
        int        cols    = anyMatrix.length;
        double[][] mMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == rowNum) {
                    mMatrix[i][j] = anyMatrix[i][j] * multiplier;
                }
                else {
                    mMatrix[i][j] = anyMatrix[i][j];
                }

            }

        }

        return mMatrix;
    }

    /**
     * prdoubles a matrix in an easy-to-read format
     *
     * @param anyMatrix
     * @param printText
     */
    public void printMatrix(double[][] anyMatrix, String printText) {

        System.out.println(printText);
        System.out.println("");
        for (int i = 0; i < anyMatrix.length; i++) {
            for (int j = 0; j < anyMatrix[0].length; j++) {
                System.out.print(anyMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("");
    }

    /**
     * Switches around rows of a matrix
     *
     * @param anyMatrix
     * @param replaceWith - the row integer that needs to be replaced
     * @param replace     - the row integer that will be swapped with replaceWith
     * @return
     */
    public double[][] rowSwitch(double[][] anyMatrix, int replaceWith, int replace) {
        int        rows         = anyMatrix[0].length;
        int        cols         = anyMatrix.length;
        double[][] newMatrix    = new double[rows][cols];
        double[]   cloneRow     = Arrays.copyOf(anyMatrix[replaceWith], anyMatrix.length);
        double[]   cloneReplace = Arrays.copyOf(anyMatrix[replace], anyMatrix.length);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrix[i][j] = anyMatrix[i][j];
            }
        }
        for (int i = 0; i < cloneRow.length; i++) {
            newMatrix[replace][i] = cloneRow[i];
            newMatrix[replaceWith][i] = cloneReplace[i];
        }

        return newMatrix;
    }

    /**
     * multiplies rows & columns of matrices to get a single double result
     *
     * @param anyMatrix1
     * @param anyMatrix2
     * @param row
     * @param column
     * @param numCols
     * @return an doubleeger result for the new matrix
     */
    private double doMultiplication(double[][] anyMatrix1, double[][] anyMatrix2,
            int row, int column, int numCols) {
        int    counter = 0;
        double result  = 0;
        while (counter < numCols) {
            result += anyMatrix1[row][counter] * anyMatrix2[counter][column];
            counter++;
        }
        return result;
    }

}
