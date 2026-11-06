package algoexpert.leetcode.arrays_hashing;

import java.util.Arrays;

public class ConcatenationOfArray {
    //9129
    //https://leetcode.com/problems/concatenation-of-array/description/

    public static void main(String[] args) {
        int[] arr = {1,3,2,1};
        System.out.println(Arrays.toString(getConcatenation(arr)));
    }

    public static int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];

        for (int i = 0; i < ans.length - nums.length; i++) {
            ans[i + nums.length] = nums[i];
            ans[i] = nums[i];
        }
        return ans;
    }
}
