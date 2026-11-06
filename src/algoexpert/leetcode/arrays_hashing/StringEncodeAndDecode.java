package algoexpert.leetcode.arrays_hashing;

import java.util.ArrayList;
import java.util.List;

public class StringEncodeAndDecode {
    public static void main(String[] args) {
        String encoded = encode(List.of("car", "bus", "boat", "subway"));
        System.out.println(encoded);
        List<String> decoded = decode(encoded);
        decoded.forEach(System.out::println);
    }

    public static String encode(List<String> strs) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s: strs) {
            stringBuilder.append(s.length()).append('#').append(s);
        }

        return stringBuilder.toString();
    }

    public static List<String> decode(String str) {

        List<String> res = new ArrayList<>();

        int i = 0;
        while (i < str.length()){
            int j = i;
            while (str.charAt(j) != '#'){
                j++;
            }
            String sub = str.substring(i, j);
            int length = Integer.parseInt(sub);

            i = j + 1 + length;

            String word = str.substring(j + 1, i);
            res.add(word);
        }
        return res;
    }
}
