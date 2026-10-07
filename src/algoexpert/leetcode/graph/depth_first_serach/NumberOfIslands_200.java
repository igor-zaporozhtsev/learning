package algoexpert.leetcode.graph.depth_first_serach;

//200. Number of Islands
public class NumberOfIslands_200 {

	public static void main(String[] args) {
		char [][] arr = {
			{'1','1','0'},
			{'1','0','1'}
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

	//BFS
	public static int numIslands(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1'){
					count++;
					dfs(grid, i, j);
				}
			}
		}
		return count;
	}

	private static void dfs(char[][] grid, int i, int j) {
		if ( grid[i][j] == '0'){
			return;
		}

		grid[i][j] = '0';

		if (i > 0){
			dfs(grid, i - 1, j);
		}
		if (i < grid.length - 1){
			dfs(grid, i + 1, j);
		}
		if (j > 0){
			dfs(grid, i, j - 1);
		}
		if (j < grid.length - 1){
			dfs(grid, i, j + 1);
		}
	}

}
