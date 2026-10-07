package algoexpert.leetcode.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeSum_10_08_2026 {

	// не вирішено
	public static void main(String[] args) {
		int[] array = {-1,0,1,2,-1,-4}; //Output: [[-1,-1,2],[-1,0,1]]
		List<List<Integer>> ints = threeSum(array);
		System.out.println(ints);
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		int base = 0;
		int left = 1;
		int right = nums.length - 1;

		if (nums.length < 3) {
			 return new ArrayList<>();
		}

		List<List<Integer>> result = new ArrayList<>();

		while(base < nums.length - 2) {
			while (left < right){
				int sum = nums[base] + nums[left] + nums[right];
				if (sum < 0){
					left++;
				} else if (sum > 0){
					right--;
				} else {
					result.add(List.of(nums[base], nums[left], nums[right]));
					left++;
					right--;
				}
			}
			base++;
			left = base + 1;
			right = nums.length - 1;
		}
		return result;
	}
}
