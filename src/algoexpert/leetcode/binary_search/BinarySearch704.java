package algoexpert.leetcode.binary_search;

//704. Binary Search
public class BinarySearch704 {

	public static void main(String[] args) {
		int[] arr = {-1,0,3,5,9,12};
//		int[] arr = {2,5};
		//0 + 5 /2 = 2
		System.out.println(search(arr, 5));
	}

	//target = 5
	//-1,0,3,5,9,12 - middle nums[2] = 3
	//_,_,_,5,9,12 - middle nums[4] = 9
	//_,_,_,5,_,_ - middle nums[3] = 5
	public static int search(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;

		while (l <= r) {
			int middle = (r + l) / 2;

			if (target == nums[middle]) {
				return middle;
			}

			if (nums[middle] < target) {
				l = middle + 1;
			} else if (nums[middle] > target) {
				r = middle - 1;
			}
		}

		return -1;
	}
}
