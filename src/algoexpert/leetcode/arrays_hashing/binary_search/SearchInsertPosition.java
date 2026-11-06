package algoexpert.leetcode.arrays_hashing.binary_search;

public class SearchInsertPosition {
    //35. Search Insert Position

    public static void main(String[] args) {
        int[] nums1 = {1,3,5,6}; int target1 = 5;
        int[] nums2 = {1,3,5,6}; int target2 = 2;
        int[] nums3 = {1,3,5,6}; int target3 = 7;


        //System.out.println(searchInsert(nums1, target1));
        //System.out.println(searchInsert(nums2, target2));
        System.out.println(searchInsert(nums3, target3));
    }


    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int middle = (left + right) / 2;
            if (nums[middle] == target){
                return middle;
            }
            if (target > nums[middle] ){
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }
}
