package algoexpert.leetcode.arrays_hashing.binary_search;

public class BinarySearch {
    //704. Binary Search
    //https://leetcode.com/problems/binary-search/description/

    public static void main(String[] args) {
        int[] nums1 = {-1,0,3,5,9,12};
        int[] nums2 = {-1,0,5};
        System.out.println(search(nums1, 9));
    }

    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int middle = (l + r)/2;
            if (target == nums[middle]){
                return middle;
            } else if (target > nums[middle]){
                l = middle + 1;
            } else if(target < nums[middle]) {
                r = middle - 1;
            }
        }
        return -1;
    }

}
