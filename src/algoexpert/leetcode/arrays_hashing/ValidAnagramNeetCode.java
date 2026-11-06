package algoexpert.leetcode.arrays_hashing;

import java.util.Arrays;

public class ValidAnagramNeetCode {
    void main(String[] args) {
        System.out.println(isAnagram("racecar", "carrace")); //true
//        System.out.println(isAnagram("aacc", "ccac"));
    }

    public static boolean isAnagram(String s, String t){
	    if (s.length() != t.length()) {
		    return false;
	    }

		int[] arr1 = new int[26];
		int[] arr2 = new int[26];

	    for (int i = 0; i < s.length(); i++) {
		    arr1[s.charAt(i) - 'a']++;
	    }

	    for (int i = 0; i < t.length(); i++) {
		    arr2[t.charAt(i) - 'a']++;
	    }

		return Arrays.equals(arr1, arr2);
    }

	//more optimal solution with 1 array
	public boolean isAnagram2(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] count = new int[26];

		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
			count[t.charAt(i) - 'a']--;
		}

		for (int value : count) {
			if (value != 0) {
				return false;
			}
		}

		return true;
	}
}
