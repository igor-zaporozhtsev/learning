package algoexpert.leetcode.other;

import java.util.Collections;

public class ReverseString {

	public static void main(String[] args) {
		String s = "basket";
//		System.out.println(reverseString(s));
		System.out.println(reverseStringRecursively(s));
	}

	private static String reverseString(String s) {
		char[] charArray = s.toCharArray();
		int left = 0;
		int right = charArray.length - 1;

		while (left < right){
			char temp = charArray[right];

			charArray[right] = charArray[left];
			charArray[left] = temp;

			left++;
			right--;
		}
		return String.valueOf(charArray);
	}

	private static String reverseStringRecursively(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}

		String reversed = reverseStringRecursively(s.substring(1));
		char c = s.charAt(0);
		return reversed + c;
	}
}

// -- recursive method call --
//basket
//asket
//sket
//ket
//et
//t

// - return recursive method call with s.charAt(0)
//t + e
//te + k
//tek + s
//teks + a
//teksa + b
