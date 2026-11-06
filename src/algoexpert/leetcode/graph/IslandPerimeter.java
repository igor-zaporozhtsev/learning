package algoexpert.leetcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//463. Island Perimeter
public class IslandPerimeter {
    public static void main(String[] args) {
        int[][] arr = { {0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        System.out.println(islandPerimeter2(arr));
    }

    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;


                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }

                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }

                }
            }
        }
        return perimeter;
    }


    public static int islandPerimeter2(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1){
                    return dfs(i, j, grid);
                }
            }
        }
        return 0;
    }

    private static int dfs(int i, int j, int[][] grid){
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || grid[i][j] == 0) {
            return 1;
        }
        // Check if the cell is already visited
        if (grid[i][j] == -1) {
            return 0;
        }
        // Mark the cell as visited
        grid[i][j] = -1;
        // Sum up the perimeters from all four directions
        int perimeter = dfs(i, j + 1, grid);
        perimeter += dfs(i + 1, j, grid);
        perimeter += dfs(i - 1, j, grid);
        perimeter += dfs(i, j - 1, grid);
        return perimeter;
    }
}
