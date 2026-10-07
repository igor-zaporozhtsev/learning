package algoexpert.leetcode.other;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzzForLoop {
    public static void main(String[] args) {
        System.out.println(fizzBuzz(15));
    }

    private static List<String> fizzBuzz(int n) {
        var list = new ArrayList<String>();

        for (int i = 1; i <= n; i++) {

            if (i % 5 == 0 && i % 3 == 0) {
                list.add("FizzBuzz");
                continue;
            }
            if (i % 5 == 0) {
                list.add("Buzz");
                continue;
            }
            if (i % 3 == 0) {
                list.add("Fizz");
                continue;
            }
            list.add(String.valueOf(i));
        }


        return list;
    }
}
