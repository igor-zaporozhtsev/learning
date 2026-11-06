package algoexpert.leetcode.stack.validparentheses;

import java.util.Map;
import java.util.Stack;

// 20. Valid Parentheses
public class ValidParenthesesNeetCode {
    public static void main(String[] args) {
//        System.out.println("1. true = " + isValid("()"));
//        System.out.println("2. true = " + isValid("()[]{}"));
//        System.out.println("3. false = " + isValid("(]"));
//        System.out.println("4. true = " + isValid("{[]}"));
//        System.out.println("5. false = " + isValid("{[}}"));
//        System.out.println("6. false = " + isValid("([)]"));
//        System.out.println("7. false = " + isValid("[{(}])"));
        System.out.println("8. true = " + isValid("{[()]}"));
//        System.out.println("9. false = " + isValid("]"));
//        System.out.println("10. false = " + isValid("(){}}{"));
//        System.out.println("11. false = " + isValid("({{{{}}}))"));
    }

    public static boolean isValid(String s) {
        String[] strings = s.split("");

        Stack<String> stack = new Stack<>();
        Map<String, String> closeToOpen = Map.of(")", "(", "}", "{", "]","[");

        for (String str : strings) {
            if (closeToOpen.containsKey(str)){ // If it's a closing bracket
                if (stack.empty() || !stack.pop().equals(closeToOpen.get(str))){
                    return false;
                }
            }else {
                stack.push(str);
            }

        }
        return stack.empty();
    }
}
