package algoexpert.leetcode.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//49. Group Anagrams
public class GroupAnagrams {

	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		System.out.println(groupAnagrams2(strs));
	}

	//with sorting
	public static List<List<String>> groupAnagrams(String[] strs) {
		var map = new HashMap<String, List<String>>();

		for (String word : strs) {
			char[] charArray = word.toCharArray();
			Arrays.sort(charArray);
			String sortedByLetter = String.valueOf(charArray);

			if (!map.containsKey(sortedByLetter)){
				LinkedList<String> strings = new LinkedList<>();
				strings.add(word);
				map.put(sortedByLetter, strings);
			} else {
				List<String> strings = map.get(sortedByLetter);
				strings.add(word);
			}
		}

		return map.values().stream()
			.toList();
	}

	//without sorting
	public static List<List<String>> groupAnagrams2(String[] strs) {

		var map = new HashMap<String, List<String>>();

		for (String word : strs) {
			int[] count = new int[26];

			for (char c : word.toCharArray()) {
				count[c - 'a']++;
			}

			StringBuilder builder = new StringBuilder();

			for (int c : count) {
				builder.append(c).append('#');
			}

			// додати word у map
			map.computeIfAbsent(builder.toString(), k-> new ArrayList<>()).add(word);

		}

		return map.values().stream()
			.toList();
	}
}
