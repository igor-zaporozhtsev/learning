package algoexpert.leetcode.binary_search;

//33. Search in Rotated Sorted Array
public class SearchRotatedSortedArray {

	public static void main(String[] args) {
//		int[] nums = {4,5,6,7,0,1,2};
		int[] nums = {6,7,0,1,2,4,5};
		int target  = 7;
		System.out.println(search(nums, target));
	}

	public static int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right){
			int mid = (left + (right - left) / 2);

			if (nums[mid] == target) {
				return mid;
			}

			if (nums[right] > nums[mid]) { //?
				if (nums[right] >= target && nums[mid] <= target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if (nums[left] <= target && nums[mid] >= target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}

		return -1;
	}
}
