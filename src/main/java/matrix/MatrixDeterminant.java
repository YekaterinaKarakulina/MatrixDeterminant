package matrix;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MatrixDeterminant {

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("The program find a matrix determinant.");
                System.out.println("Type matrix size: amount of lines, amount of columns (without spaces). Matrix will created automatically.");
                Scanner scanner = new Scanner(System.in);
                String matrixSize = scanner.nextLine();
                String[] matrixSizeValues;
                matrixSizeValues = matrixSize.split(",");
                int amountOfLines = Integer.parseInt(matrixSizeValues[0]);
                int amountOfColumns = Integer.parseInt(matrixSizeValues[1]);

                int[][] matrix;
                matrix = new int[amountOfLines][amountOfColumns];

                createAndPrintMatrix(matrix);

                if (amountOfColumns == amountOfLines) {
                    System.out.println("Determinant " + calculateDeterminant(matrix));
                } else {
                    System.out.println("It is not possible to calculate Determinant. \nYou need to use a square matrix");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException. Restart program and type correct numbers");
        }
    }


    public static void createAndPrintMatrix(int[][] matrix) throws NumberFormatException {
        Random random = new Random();
        System.out.println("Matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(20) - 10;
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int calculateDeterminant(int[][] matrix) {

        ArrayList<Integer> listOfColumnsToCreateChildMatrix = new ArrayList<Integer>();

        int Determinant = 0;
        int flag = 1;
        int[][] matrixChild;

        if (matrix.length > 2) {
            for (int amountOfColumnsToCreateChildMatrix = 0; amountOfColumnsToCreateChildMatrix < matrix.length; amountOfColumnsToCreateChildMatrix++) {
                for (int n = 0; n < matrix.length; n++) {
                    listOfColumnsToCreateChildMatrix.add(n);
                }
                listOfColumnsToCreateChildMatrix.remove(amountOfColumnsToCreateChildMatrix);

                matrixChild = new int[matrix.length - 1][matrix.length - 1];

                for (int row = 0; row < matrix.length - 1; row++) {
                    for (int column = 0; column < matrix.length - 1; column++) {
                        matrixChild[row][column] = matrix[row + 1][listOfColumnsToCreateChildMatrix.get(column)];
                    }
                }
                Determinant = Determinant + (matrix[0][amountOfColumnsToCreateChildMatrix] * flag * calculateDeterminant(matrixChild));
                listOfColumnsToCreateChildMatrix.clear();
                flag = flag * (-1);
            }
        }
        if (matrix.length == 2) {
            Determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        return Determinant;
    }
}
