import java.util.LinkedList;
import java.util.Queue;
/*
Problem Statement:

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. A clear path is defined as a path from the top-left cell (0, 0) to the bottom-right cell (n-1, n-1) such that:

You can only move in one of the 8 possible directions (horizontally, vertically, or diagonally).
You can only travel through cells that contain a 0 (representing an open cell).
The path cannot travel through cells that contain a 1 (representing an obstacle).
Return the length of the shortest path. If there is no such path, return -1.
 */
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // Directions array to move in 8 possible directions
    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}, // right, down, left, up
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // diagonals
    };

    public static void main(String[] args) {
        // Example input grid
        int[][] grid = {
                {0, 1, 0},
                {1, 0, 0},
                {1, 1, 0}
        };

        // Call the method to find the shortest path
        int result = shortestPathBinaryMatrix(grid);
        if (result == -1) {
            System.out.println("No path exists.");
        } else {
            System.out.println("The shortest path length is: " + result);
        }
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Edge case: Start or end is blocked
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }

        // BFS queue, stores (x, y, distance) for each cell
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // Starting point with distance 1
        grid[0][0] = 1; // Mark the start as visited

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], distance = current[2];

            // If we reached the bottom-right corner, return the distance
            if (x == n - 1 && y == n - 1) {
                return distance;
            }

            // Explore all 8 possible directions
            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                // Check if the new cell is within bounds and is a 0 (not visited yet)
                if (newX >= 0 && newY >= 0 && newX < n && newY < n && grid[newX][newY] == 0) {
                    queue.offer(new int[]{newX, newY, distance + 1});
                    grid[newX][newY] = 1; // Mark as visited
                }
            }
        }

        // If no path was found, return -1
        return -1;
    }
}
