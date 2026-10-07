package algoexpert.leetcode.binary_search;

import java.util.Arrays;

//74. Search a 2D Matrix
public class Search2DMatrix {

	public static void main(String[] args) {
		int [][] arr = {
			{1,3,5,7},
			{10,11,16,20},
			{23,30,34,60}
		};

		System.out.println(searchMatrix(arr,3));

	}

	public static boolean searchMatrix(int[][] matrix, int target) {
			int m = matrix.length, n = matrix[0].length;
			int left = 0, right = m * n - 1;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				int value = matrix[mid / n][mid % n];
				if (value == target) {
					return true;
				} else if (value < target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			return false;
		}
}
