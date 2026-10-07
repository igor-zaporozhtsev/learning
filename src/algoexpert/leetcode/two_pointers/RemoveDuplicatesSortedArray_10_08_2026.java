package algoexpert.leetcode.two_pointers;

public class RemoveDuplicatesSortedArray_10_08_2026 {
	//26. Remove Duplicates from Sorted Array
	public static void main(String[] args) {
//		int[] nums = {1,1,2};
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
//		int[] nums = {1,2,2,2,3,4,4,5};
		System.out.println(removeDuplicates(nums));

	}

	public static int removeDuplicates(int[] nums) {
		int left = 1;
		int right = 1;

		while (right < nums.length){
			if (nums[right] != nums[right - 1]){
				nums[left] = nums[right];
				left++;
			}
			right++;
		}
		return left;
	}

}
