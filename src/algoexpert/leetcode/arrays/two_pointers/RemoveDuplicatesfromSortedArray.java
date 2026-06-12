package algoexpert.leetcode.arrays.two_pointers;

import java.util.Arrays;

public class RemoveDuplicatesfromSortedArray {

/*
  Input: nums = [1,1,2]
  Output: 2, nums = [1,2,_]
*/

    public static void main(String[] args) {
	    int array[] = {1,2,2,2, 3,4,4,5};
        int[] array2 = {1,1,2};
        System.out.println(removeDuplicates2(array));
    }
  /*

    0,0,1,1,1,2,2,3,3,4

    0,1,1,1,1,2,2,3,3,4

    0,_,1,_,_,2,_ ,3,_,4

    0,1,2,3,4,_,_,_,_,_

 */

    public static int removeDuplicates2(int[] nums){

        int left_pointer = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]){
                nums[left_pointer] = nums[i];
                left_pointer++;
            }
        }
        return left_pointer;
    }
}
