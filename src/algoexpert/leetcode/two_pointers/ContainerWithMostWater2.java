package algoexpert.leetcode.two_pointers;

import java.util.Map;

public class ContainerWithMostWater2 {

	public static void main(String[] args) {
		int[] arr = {1,8,6,2,5,4,8,3,7}; //output 49
		System.out.println(maxArea(arr));
	}

	public static int maxArea(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int res = 0;
		int size;

		while (left < right) {
			size = (right - left) * Math.min(height[left], height[right]);
			if (height[left] > height[right]) {
				if (res < size) {
					res = size;
				}
				right--;

			} else {
				if (res < size) {
					res = size;
				}
				left++;
			}
		}
		return res;
	}
}
