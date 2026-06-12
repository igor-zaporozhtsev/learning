package algoexpert.leetcode.slide_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
//		String intput = "zxyzxyz";//3
//		String intput = "xxxx";//1
		String intput = "pwwkew";//3
		System.out.println(lengthOfLongestSubstring(intput));
	}

	public static int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<>();
		char[] charArray = s.toCharArray();

		int left = 0;
		int right = 0;
		while (right < charArray.length) {
			char r = charArray[right];
			char l = charArray[left];
			if (!set.contains(r)) {
				right++;
				set.add(r);
			} else {
				set.remove(l);
				left++;
			}
		}

		return set.size();
	}

}
