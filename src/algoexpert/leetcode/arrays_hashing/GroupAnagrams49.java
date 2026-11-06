package algoexpert.leetcode.arrays_hashing;

import java.util.*;


public class GroupAnagrams49 {
    public static void main(String[] args) {

        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagramsMap(strs));
        //output [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]];
    }

    public static List<List<String>> groupAnagramsMap(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            char[] notSortedChars = chars.clone();
            Arrays.sort(chars); //O(lon n)
            map.computeIfAbsent(Arrays.toString(chars), k -> new ArrayList<>());
            map.get(Arrays.toString(chars)).add(new String(notSortedChars));

        }
        return new ArrayList<>(map.values());
    }
}
