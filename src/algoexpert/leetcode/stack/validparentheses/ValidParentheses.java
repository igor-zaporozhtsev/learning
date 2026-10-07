package algoexpert.leetcode.stack.validparentheses;

import java.util.HashMap;

public class ValidParentheses {
    public static void main(String[] args) {
//        System.out.println("1. true = " + isValid("()"));
//        System.out.println("2. true = " + isValid("()[]{}"));
//        System.out.println("3. false = " + isValid("(]"));
//        System.out.println("4. true = " + isValid("{[]}"));
//        System.out.println("5. false = " + isValid("{[}}"));
//        System.out.println("6. false = " + isValid("([)]"));
//        System.out.println("7. false = " + isValid("[{(}])"));
//        System.out.println("8. true = " + isValid("{[()]}"));
//        System.out.println("9. false = " + isValid("]"));
//        System.out.println("10. false = " + isValid("(){}}{"));
        System.out.println("11. false = " + isValid("({{{{}}}))"));
    }

    public static boolean isValid(String s) {
        var map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        char[] chars = s.toCharArray();

        if (!map.containsValue(chars[chars.length - 1])) {
            return false;
        }

        if (chars.length == 1) {
            return false;
        }


        for (int i = 0; i < chars.length; i++) {
            Character closeSign = map.get(chars[i]);

            int count = 0;
            if (closeSign == null) continue;


            //last character can be only first
            if (i != 0 && i != chars.length - 2 && closeSign == chars[chars.length - 1]) {
                return false;
            }

            for (int j = 1; j < chars.length; j++) {
                if (closeSign == chars[j]) {
                    count++;
                }
            }
            if (count == 0) return false;
        }
        return true;
    }
}
