import java.util.Scanner;

public class MatrixProduct {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create matrix A
        System.out.print("Enter number of rows for matrix A: ");
        int rowsA = sc.nextInt();
        System.out.print("Enter number of columns for matrix A: ");
        int colsA = sc.nextInt();
        int[][] A = new int[rowsA][colsA];
        System.out.println("Enter elements of matrix A: ");

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // Create matrix B
        System.out.print("Enter number of rows for matrix B: ");
        int rowsB = sc.nextInt();
        System.out.print("Enter number of columns for matrix B: ");
        int colsB = sc.nextInt();
        int[][] B = new int[rowsB][colsB];
        System.out.println("Enter elements of matrix B: ");

        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        if (colsA != rowsB) {
            System.out.println("Matrices are not of legal size.");
            return;
        }

        // If multiplied matrix == null return not legal
        int[][] C = multiplyMatrices(A, B);
        if (C == null) {
            System.out.println("Matrices are not of legal size.");
            return;
        }

        // Print
        System.out.println("Matrix A: ");
        printMatrix(A);
        System.out.println("Matrix B: ");
        printMatrix(B);
        System.out.println("Product of A and B: ");
        printMatrix(C);
    }

    // MULTIPLE MATRIX
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;
        
        if (colsA != rowsB) {
            return null;
        }

        int[][] C = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return C;
    }

    // PRINT MATRIX
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

}