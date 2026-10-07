package algoexpert.leetcode.two_pointers;

import java.util.Arrays;

public class TwoSum3 {

	public static void main(String[] args) {
		int[] array = {2,7,11,15};
		int targetSum = 9;
//		int[] array = {-1,-2,-3,-4,-5};
//		int targetSum = -8;

//		int[] array = {3,2,4};
//		int targetSum = 6;

//		int[] array = {3,3};
//		int targetSum = 6;

//		int[] array = {2,5,5,11};
//		int targetSum = 10;

//		int[] array = {3, 2, 4};
//		int targetSum = 6;

		int[] ints = twoSum(array, targetSum);
		System.out.println(Arrays.toString(ints));
	}

	public static int[] twoSum(
		int[] nums,
		int target
	) {
		int[] res = new int[2];
		int left = 0;
		int right = nums.length - 1;

		while (nums[left] + nums[right] != target){
			if(nums[left] + nums[right] > target){
				right--;
			} else {
				left++;
			}
		}

		res[0] = left + 1;
		res[1] = right + 1;
		return res;
	}
}
