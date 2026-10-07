package algoexpert.leetcode.stack;

import java.util.Stack;

public class MinStackCodePath {

	public static void main(String[] args) {
		MinStack5 minStack = new MinStack5();
		minStack.push(0);
		minStack.push(1);
		minStack.push(0);
		minStack.push(0);
		minStack.getMin();
		minStack.pop();
		minStack.getMin();
		minStack.pop();
		minStack.getMin();
		minStack.pop();
		minStack.push(-2);
		minStack.push(-1);
		minStack.push(-2);
		minStack.getMin();
		minStack.pop();
		minStack.top();
		minStack.getMin();
		minStack.pop();
		int min = minStack.getMin();
		minStack.pop();
		System.out.println();
	}

//["MinStack","push","push","push","getMin","pop","getMin","pop","getMin","pop","push","push","push","getMin","pop","top","getMin","pop","getMin","pop"]
//[[],[0],[1],[0],[],[],[],[],[],[],[-2],[-1],[-2],[],[],[],[],[],[],[]]


}

class MinStack5 {
	Stack<Integer> stack;
	Stack<Integer> minStack;

	public MinStack5() {
		stack = new Stack<>();
		minStack = new Stack<>();
	}

	public void push(int value) {
		stack.push(value);
		if (!minStack.isEmpty()){
			if (value < minStack.peek()){
				minStack.push(value);
			} else {
				minStack.push(minStack.peek());
			}
		} else {
			minStack.push(value);
		}
	}

	public void pop() {
		Integer value = stack.pop();
		if (minStack.peek().equals(value)){
			minStack.pop();
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
