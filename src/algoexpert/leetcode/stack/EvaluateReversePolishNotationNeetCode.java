package algoexpert.leetcode.stack;

import java.util.Stack;

public class EvaluateReversePolishNotationNeetCode {

	void main() {
		String[] strings = {"1", "2", "+", "3", "*", "4", "-"};
		System.out.println(evalRPN(strings));

	}

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();

		for (String token : tokens) {
			switch (token) {
				case "+" -> {
					Integer a = stack.pop();
					Integer b = stack.pop();
					stack.push(b + a);
				}
				case "-" -> {
					Integer a = stack.pop();
					Integer b = stack.pop();
					stack.push(b - a);
				}
				case "/" -> {
					Integer a = stack.pop();
					Integer b = stack.pop();
					stack.push(b /  a);
				}
				case "*" -> {
					Integer a = stack.pop();
					Integer b = stack.pop();
					stack.push(b * a);
				}
				default -> stack.push(Integer.parseInt(token));
			}
		}

		return stack.pop();

	}
}
