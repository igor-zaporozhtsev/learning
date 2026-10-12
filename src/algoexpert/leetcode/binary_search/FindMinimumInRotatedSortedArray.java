package algoexpert.leetcode.binary_search;

public class FindMinimumInRotatedSortedArray {

	public static void main(String[] args) {
//		int[] nums = { 3,4,5,1,2};
//		int[] nums = { 4,5,6,7,0,1,2};
		int[] nums = {2,1};
		System.out.println(findMin(nums));
	}

	public static int findMin(int[] nums) {

		if (nums.length == 1) return nums[0];

		int left = 0;
		int right = nums.length - 1;

		while (left < right){
			int mid = (left + (right - left) / 2);
			if (nums[mid] > nums[right]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return nums[left];
	}
}
