import java.util.Scanner;

public class MagicSquare {
    public static void main(String[] args) {
        int[][] square = new int[4][4];
        Scanner input = new Scanner(System.in);
        int sum = 0;
        boolean magic = true;

        System.out.println("Enter the values for the 4x4 square:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                square[i][j] = input.nextInt();
            }
        }

        // Check rows
        for (int i = 0; i < 4; i++) {
            int rowSum = 0;
            for (int j = 0; j < 4; j++) {
                rowSum += square[i][j];
            }
            if (i == 0) {
                sum = rowSum;
            } else {
                if (rowSum != sum) {
                    magic = false;
                    break;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 4; i++) {
            int colSum = 0;
            for (int j = 0; j < 4; j++) {
                colSum += square[j][i];
            }
            if (colSum != sum) {
                magic = false;
                break;
            }
        }

        // Check diagonals
        int diag1 = square[0][0] + square[1][1] + square[2][2] + square[3][3];
        int diag2 = square[3][0] + square[2][1] + square[1][2] + square[0][3];
        if (diag1 != sum || diag2 != sum) {
            magic = false;
        }

        // Print square and result
        System.out.println("\nThe 4x4 square is:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(square[i][j] + " ");
            }
            System.out.println();
        }
        if (magic) {
            System.out.println("\nThis is a magic square with a sum of " + sum + ".");
        } else {
            System.out.println("\nThis is not a magic square.");
        }
    }

}