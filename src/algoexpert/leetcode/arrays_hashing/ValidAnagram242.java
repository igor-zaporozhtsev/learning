package algoexpert.leetcode.arrays_hashing;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram242 {
    public static void main(String[] args) {
        //System.out.println(isAnagram("aacc", "ccac"));
        System.out.println(isAnagramV2("aacc", "ccac"));
    }

    public static boolean isAnagramV2(String s, String t){
        if (s.length() != t.length()) return false;

        Map<Character, Integer> sTable = new HashMap<>();
        Map<Character, Integer> tTable = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            if (sTable.get(s.charAt(i)) != null){
                Integer sValue = sTable.get(s.charAt(i));
                sTable.put(s.charAt(i), sValue + 1);
            } else {
                sTable.put(s.charAt(i), 1);

            }
        }

        for(int i = 0; i < t.length(); i++){
            if (tTable.get(t.charAt(i)) != null){
                Integer tValue = tTable.get(t.charAt(i));
                tTable.put(t.charAt(i), tValue + 1);
            } else {
                tTable.put(t.charAt(i), 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (!sTable.get(s.charAt(i)).equals(tTable.get(s.charAt(i))))
                return false;
        }

        return true;
    }

    public static boolean isAnagram(String s, String t){
        if (s.length() != t.length()) return false;

        char[] firstStringChars = s.toCharArray();
        char[] secStringChars = t.toCharArray();
        insertionSort(firstStringChars);
        insertionSort(secStringChars);


        boolean eq = false;
        for (int i = 0; i < firstStringChars.length; i++) {
            for (int j = i; j < secStringChars.length; j++) {
                if (firstStringChars[i] == secStringChars[j]){
                    eq = true;
                    break;
                }else {
                    return false;
                }
            }

        }

        return eq;
    }

    public static void insertionSort(char[] inputArray){
        for (int i = 1; i < inputArray.length; i++) {
            var temp = inputArray[i];
            var j = i - 1;

            while (j >= 0 && temp < inputArray[j]){
                inputArray[j + 1] = inputArray[j];
                j--;
            }
            inputArray[j + 1] = temp;
        }
    }


}
