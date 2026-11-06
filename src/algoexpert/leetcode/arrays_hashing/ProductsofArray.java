package algoexpert.leetcode.arrays_hashing;

import java.util.Arrays;

public class ProductsofArray {
    //Products of Array Discluding Self
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        int[] res = productExceptSelf(arr);
        System.out.println(Arrays.toString(res));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] arr = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[i] = prefix;
            prefix = prefix * nums[i];
        }

        int postfix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            arr[i] = postfix * arr[i];
            postfix = postfix * nums[i];

        }
        return arr;
    }
}
