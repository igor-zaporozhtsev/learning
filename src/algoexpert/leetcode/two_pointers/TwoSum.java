package algoexpert.leetcode.two_pointers;

import java.util.Arrays;

public class TwoSum {

	public static void main(String[] args) {
//		int[] array = {2,7,11,15};
//		int targetSum = 9;
//		int[] array = {-1,-2,-3,-4,-5};
//		int targetSum = -8;

//		int[] array = {3,2,4};
//		int targetSum = 6;

//		int[] array = {3,3};
//		int targetSum = 6;

//		int[] array = {2,5,5,11};
//		int targetSum = 10;

		int[] array = {3,2,4};
		int targetSum = 6;

		int[] ints = twoSum(array, targetSum);
		System.out.println(Arrays.toString(ints));
	}

	public static int[] twoSum(int[] nums, int target) {

		int right = nums.length - 1;
		int left = 0;

		while(left < right){
			if (nums[right] + nums[left] == target){
				return new int[]{left, right};
			}
			if (nums[right] + nums[left] > target){
				right--;
			} else if (nums[right] + nums[left] < target){
				left++;
			}

//			if (nums.length == 2 && nums[right] == nums[left]){
//				right = nums.length - 1;
//			}
		}

		return new int[]{left, right};
	}
}
