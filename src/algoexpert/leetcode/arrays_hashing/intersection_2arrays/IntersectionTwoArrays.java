package algoexpert.leetcode.arrays_hashing.intersection_2arrays;

public class IntersectionTwoArrays {

    //350. Intersection of Two Arrays II
//    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//    Output: [4,9]
//    Explanation: [9,4] is also accepted.

    public static int[] intersect(int[] nums1, int[] nums2) {

        int p1 = 0;
        int p2 = 0;
        int i = 0;
        int count = 0;
        int n = 0;
        while (n < nums1.length){
            if (nums1[p1] == nums2[p2]){
                nums1[p1++] = nums2[p2++];
                count++;
            } else {
                p2++;
            }

            n++;
        }


        return null;
    }

    public static void main(String[] args) {
        intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4});


    }
}
