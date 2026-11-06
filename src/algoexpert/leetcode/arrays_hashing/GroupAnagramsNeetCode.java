package algoexpert.leetcode.arrays_hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagramsNeetCode {

	void main() {

		//can I solve with groupBy?
//		String[] arr = {"act","pots","tops","cat","stop","hat"};
//		String[] arr = {"actt","cat"};
		String[] arr = {"bdddddddddd","bbbbbbbbbbc"};
		System.out.println(groupAnagrams(arr));
	}
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> tokenToAnagram = new HashMap<>();

		for (String str : strs) {
			StringBuilder builder = new StringBuilder();
			int[] token = new int[26];
			char[] charArray = str.toCharArray();
			for (char c : charArray) {
				int num = c - 'a';
				//token[num] = 1; wrong
				token[num]++; //cover this case {"actt","cat"};
			}

			for (int i : token) {
				builder.append('#'); //in case in token we have 0100 occurance and we can distinct from 0100 one occurance 0100 vs 0100 [but token is 10 vs 1]
				//so we add hashtag 0#10#0 vs 0#1#0#0 then #10 vs #1
				builder.append(i);
			}

			tokenToAnagram.computeIfPresent(
				builder.toString(), (tokenKey, anagramList) -> {
					anagramList.add(str);
					return anagramList;
				}
			);
			tokenToAnagram.computeIfAbsent(builder.toString(),key -> {
				List<String> list =  new ArrayList<>();
				list.add(str);
				return list;
			});
		}

		return tokenToAnagram.values().stream().toList();
	}

	//optimal solution with refactoring
	public List<List<String>> groupAnagrams2(String[] strs) {

		Map<String, List<String>> map = new HashMap<>();

		for (String str : strs) {

			int[] count = new int[26];

			for (int i = 0; i < str.length(); i++) {
				count[str.charAt(i) - 'a']++;
			}

			StringBuilder key = new StringBuilder();

			for (int frequency : count) {
				key.append('#').append(frequency);
			}

			map.computeIfAbsent( //only one computeIfAbsent without computeIfPresent
				key.toString(),
				k -> new ArrayList<>()
			).add(str);
		}

		return new ArrayList<>(map.values());
	}

}
