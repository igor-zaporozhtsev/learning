package algoexpert.leetcode.arrays_hashing;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        //int[] arr = new int[]{2, 20, 4 ,10 , 3, 4, 5};
        //int[] arr = new int[]{100, 4, 200, 1, 3, 2};
        int[] arr = new int[]{0,-1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(longestConsecutive(arr));

    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 1) return 1;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            count++;
            if (nums[i + 1] - nums[i] > 1){
                break;
            }
        }
        return count;
    }

    private static void sort(int[] arr){

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > temp){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;

        }
    }
}
