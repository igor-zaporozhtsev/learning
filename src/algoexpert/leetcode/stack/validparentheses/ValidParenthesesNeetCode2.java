package algoexpert.leetcode.stack.validparentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class ValidParenthesesNeetCode2 {

	void main() {
//		System.out.println("1. true = " + isValid("()"));
//		System.out.println("2. true = " + isValid("()[]{}"));
//		System.out.println("3. false = " + isValid("(]"));
//		System.out.println("4. true = " + isValid("{[]}"));
//		System.out.println("5. false = " + isValid("{[}}"));
//		System.out.println("6. false = " + isValid("([)]"));
//		System.out.println("7. false = " + isValid("[{(}])"));
		System.out.println("8. true = " + isValid("{[()]}"));
//		System.out.println("9. false = " + isValid("]"));
//		System.out.println("10. false = " + isValid("(){}}{"));
//		System.out.println("11. false = " + isValid("({{{{}}}))"));
	}

	public boolean isValid(String s) {
		Map<String, String> map = Map.of(
			"(", ")",
			"{", "}",
			"[", "]"
		);

		Stack<String> stack = new Stack<>();

		String[] str = s.split("");

		for (String string : str) {
			if (map.containsKey(string)){
				stack.push(string);
			} else {
				if (stack.empty() || !map.get(stack.pop()).equals(string)){
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
