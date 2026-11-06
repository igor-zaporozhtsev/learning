package algoexpert.leetcode.slide_window;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//567. Permutation in String
public class PermutationInString {

	public static void main(String[] args) {
		//Input: s1 = "ab", s2 = "eidbaooo"
//		String s1 = "ab"; String s2 = "eidbaooo";
//		String s1 = "abbb"; String s2 = "abbbdl";
//		String s1 = "aabc";
//		String s2 = "eidbaacoo";

		String s1 = "ab";  // n1 = 2
		String s2 = "cab";
		System.out.println(checkInclusion(s1, s2));
	}

	public static boolean checkInclusion(
		String s1,
		String s2
	) {
		int n1 = s1.length();
		int n2 = s2.length();

		if (n2 < n1) {
			return false;
		}

		int[] count1 = new int[26];
		int[] count2 = new int[26];

		for (int i = 0; i < n1; i++) {
			count1[s1.charAt(i) - 'a']++;
			count2[s2.charAt(i) - 'a']++;
		}

		if (Arrays.equals(count1, count2)) {
			return true;
		}

		for (int i = n1; i < n2; i++) {
			int added = s2.charAt(i) - 'a';
			count2[added] = count2[added] + 1;

			int removed = s2.charAt(i - n1) - 'a';
			count2[removed] = count2[removed] - 1;

			if (Arrays.equals(count1, count2)) {
				return true;
			}
		}

		return false;
	}

}
