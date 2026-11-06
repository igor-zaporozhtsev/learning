package algoexpert.leetcode.graph;

//200. Number of Islands
public class NumberOfIslands_200 {

	void main(String[] args) {
		char[][] arr = {
			{'1', '1', '0'},
			{'1', '0', '1'}
		};
//		char [][] arr = {
//			{'1','1','0','0','0'},
//			{'1','1','0','0','0'},
//			{'0','0','1','0','0'},
//			{'0','0','0','1','1'}
//		};
		//output 3
		System.out.println(numIslands(arr));
	}

	//DFS
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] == '1') {   // found land nobody has visited
					count++;                   // so it is a new island
					sinkIsland(grid, row, col);
				}
			}
		}
		return count;
	}

	// Turns this land cell and all connected land cells into water
	private static void sinkIsland(
		char[][] grid,
		int row,
		int col
	) {
		// Stop if we left the map or this is not land
		if (row < 0 || row >= grid.length) {
			return;
		}
		if (col < 0 || col >= grid[row].length) {
			return;
		}
		if (grid[row][col] != '1') {
			return;
		}

		grid[row][col] = '0';              // mark as visited by sinking it

		sinkIsland(grid, row - 1, col);    // up
		sinkIsland(grid, row + 1, col);    // down
		sinkIsland(grid, row, col - 1);    // left
		sinkIsland(grid, row, col + 1);    // right
	}
}
