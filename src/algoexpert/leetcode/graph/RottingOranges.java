package algoexpert.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

//994. Rotting Oranges
public class RottingOranges {

	void main() {
		// Приклад 1: усі апельсини згниють за 4 хвилини
		int[][] grid1 = {
			{2, 1, 1},
			{1, 1, 0},
			{0, 1, 1}
		};
		System.out.println(orangesRotting(grid1)); // 4

		/*// Приклад 2: апельсин у лівому нижньому куті недосяжний
		int[][] grid2 = {
			{2, 1, 1},
			{0, 1, 1},
			{1, 0, 1}
		};
		System.out.println(orangesRotting(grid2)); // -1

		// Приклад 3: свіжих апельсинів немає, час 0
		int[][] grid3 = {
			{0, 2}
		};
		System.out.println(orangesRotting(grid3)); // 0*/
	}

	//BFS
	public int orangesRotting(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;

		Queue<int[]> rotten = new LinkedList<>(); // coordinates of rotten oranges
		int fresh = 0;                            // number of fresh ones

		// 1. We go through the grid: note the rotten ones and count the fresh ones.
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 2) {
					rotten.add(new int[]{r, c});
				} else if (grid[r][c] == 1) {
					fresh++;
				}
			}
		}

		// Four directions: up, down, left, right
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int minutes = 0;

		// 2. As long as there are rotten ones capable of infecting their neighbors, and fresh ones still remain
		while (!rotten.isEmpty() && fresh > 0) {
			int size = rotten.size(); // how many spotted oranges in current minutes

			for (int i = 0; i < size; i++) {
				int[] cell = rotten.poll();

				for (int[] d : directions) {
					int newR = cell[0] + d[0];
					int newC = cell[1] + d[1];

					// We verify that the cell is within the grid and contains a fresh orange.
					if (newR >= 0 && newR < rows
						&& newC >= 0 && newC < cols
						&& grid[newR][newC] == 1
					) {

						grid[newR][newC] = 2;          // it is rotting.
						fresh--;                       // The number of fresh ones is decreasing.
						rotten.add(new int[]{newR, newC}); // infect the neighbors the very next minute
					}
				}
			}

			minutes++; // infect the neighbors the very next minute
		}

		// 3.If any fresh ones remain, they are out of reach.
		return fresh == 0 ? minutes : -1;
	}
}
