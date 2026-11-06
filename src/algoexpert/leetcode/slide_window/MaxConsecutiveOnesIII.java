package algoexpert.leetcode.slide_window;

//1004. Max Consecutive Ones III
public class MaxConsecutiveOnesIII {

	public static void main(String[] args) {

		//Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
		//Output: 10
		int[] arr = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}; int k = 3;
//		int[] arr = {1,1,1,0,0,0,1,1,1,1,0}; int k = 2;
		System.out.println(longestOnes(arr, k));
	}

	public static int longestOnes(int[] nums, int k) {
		int l = 0;
		int r = 0;

		int size = 0;
		int zeroCounter = 0;

		while (r <= nums.length - 1){
			if (nums[r] == 0){
				zeroCounter++;
			}
			while (zeroCounter > k){
				if (nums[l] == 0){
					zeroCounter--;
				}
				l++;
			}

			size = Math.max(size, r - l + 1);
			r++;
		}

		return size;
	}
}
