package algoexpert.leetcode.arrays_hashing;

import java.util.Arrays;
import java.util.HashSet;

public class QuickSortAlgorithms {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 2, 4, 7, 1, 3, 9, 6, 5};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    private static void quickSort(int[] array, int start, int end){

    if (end <= start) return;

    int pivot = partition(array, start, end);
    quickSort(array, start, pivot - 1);
    quickSort(array, pivot + 1, end);


    }

    private static int partition(int[] array, int start, int end){
        int pivot = array[end];
        int i = start - 1;

        for (int j = start; j <= end - 1; j++) {
            if (array[j] < pivot){
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++;
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;
        return i;
    }

	//128. Longest Consecutive Sequence
	public static class LongestConsecutiveSequence {

		public static void main(String[] args) {
	//		int[] arr = {1,2,4,5,6,8, 9}; //3
	//		int[] arr = {100,4,200,1,3,2}; //4
	//		int[] arr = {0,3,7,2,5,8,4,6,0,1}; //9
			int[] arr = {0,-1};

			System.out.println(longestConsecutive(arr));
		}

		public static int longestConsecutive(int[] nums) {
			HashSet<Integer> numbers = new HashSet<>();
			for (int num : nums) {
				numbers.add(num);
			}

			int contMaxLength = 0;
			for (Integer number : numbers) {
				if (!numbers.contains(number - 1)) { //identify start of sequence
					int currentSeq = 1;

					//number = 100
					//number++ = 101
					//number++ = 102
					//number++ = 103
					//number++ = 104
					while (numbers.contains(number + 1)) {
						number++;
						currentSeq++;
					}
					contMaxLength = Math.max(contMaxLength, currentSeq);
				}
			}

			return contMaxLength;

		}
	}
}
