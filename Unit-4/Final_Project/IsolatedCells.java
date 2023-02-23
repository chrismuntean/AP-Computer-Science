import java.util.Random;

public class IsolatedCells {
    public static void main(String[] args) {
        // import random
        Random rand = new Random();

        // create the grid
        int[][] grid = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                //           Random int 0-100    40%
                grid[i][j] = rand.nextInt(100) < 40 ? 1 : 0;
            }
        }

        // Print the grid to the console
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(grid[i][j] == 1 ? "* " : "_ ");
            }
            System.out.println();
        }

        // Find and count isolated cells
        int isolatedCells = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 1) {

                    // Check surroundings for isolated cells
                    boolean north = i > 0 && grid[i - 1][j] == 1;
                    boolean south = i < 7 && grid[i + 1][j] == 1;
                    boolean east = j < 7 && grid[i][j + 1] == 1;
                    boolean west = j > 0 && grid[i][j - 1] == 1;
                    if (!north && !south && !east && !west) {
                        isolatedCells++;
                    }

                }
            }
        }

        // Display the number of isolated cells
        System.out.println("Number of isolated cells: " + isolatedCells);
    }
}