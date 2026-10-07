package algoexpert.leetcode.other;

import java.util.Arrays;

public class PalindromeString {

	public static void main(String[] args) {
		String s = "level";
		System.out.println(isPalindrome(s));
	}

	private static boolean isPalindrome(String s) {
		char[] charArray = s.toCharArray();

		int left = 0;
		int right = charArray.length - 1;
		while (left < right){
			if (charArray[left] != charArray[right]){
				return false;
			}
			right--;
			left++;
		}

		return true;
	}

}
