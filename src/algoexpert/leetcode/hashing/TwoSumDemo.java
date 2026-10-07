package algoexpert.leetcode.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumDemo {
    public static void main(String[] args) {
//        int[] inputArray = new int[]{2,7,11,15};
//	    System.out.println(Arrays.toString(twoSum(inputArray, 9)));
        int[] inputArray = new int[]{3,2,4};
	    System.out.println(Arrays.toString(twoSum(inputArray, 6)));
    }

    private static int[] twoSum(int[] nums, int target) {
		var map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
		    int key =  target - nums[i];
			if (map.containsKey(key)){
				return new int[]{(int) map.get(key), i};
			}
		    map.put(nums[i], i);
	    }

		return new int[]{0, 0};
    }

}
