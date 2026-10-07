package algoexpert.leetcode.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

//128. Longest Consecutive Sequence
public class LongestConsecutiveSequence {

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
