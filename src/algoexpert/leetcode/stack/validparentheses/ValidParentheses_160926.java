package algoexpert.leetcode.stack.validparentheses;

import java.util.Map;
import java.util.Stack;

//20. Valid Parentheses
public class ValidParentheses_160926 {

	public static void main(String[] args) {
		//"()" - true
		//"()[]{}" - true
		//"(]" false
		//"([)]" - false

		System.out.println(isValid("(")); // false
		System.out.println(isValid(")")); // false
//		System.out.println(isValid("(){}}{")); // false
//		System.out.println(isValid("()")); // true
//		System.out.println(isValid("()[]{}")); // true
//		System.out.println(isValid("(]")); //false
//		System.out.println(isValid("([)]")); //false

	}

	public static boolean isValid(String s) {
		Map<String, String> map = Map.of("(", ")", "{", "}", "[", "]");
		Stack<String> stack = new Stack<>();

		String[] stringArray = s.split("");

		for (String value : stringArray) {
			if (map.containsKey(value)){
				stack.push(value);
			} else {
				if(stack.isEmpty()){
					return false;
				} else {
					String key = stack.pop();
					if (!map.get(key).equals(value)){
						return false;
					}
				}
			}
		}

		return stack.isEmpty();
	}

}
