package algoexpert.leetcode.queue;

import java.util.Stack;

//232. Implement Queue using Stacks
public class ImplementQueueUsingStacks {

	/*FIFO

	only two stacks
	Input
	["MyQueue", "push", "push", "peek", "pop", "empty"]
	[[], [1], [2], [], [], []]
	Output
	[null, null, null, 1, 1, false]

	*/

	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		queue.push(1);
		queue.push(2);
		System.out.println(queue.pop());
		queue.push(3);
		System.out.println(queue.pop());
		System.out.println("peek " + queue.peek());
		System.out.println(queue.pop());
//		System.out.println(queue.empty());
	}


}


class MyQueue {

	Stack<Integer> income;
	Stack<Integer> outcome;

	public MyQueue() {
		income = new Stack<>();
		outcome = new Stack<>();
	}

	public void push(int x) {
		income.push(x);
	}

	public int pop() {
		if (!outcome.isEmpty()){
			return outcome.pop();
		} else {
			while (!income.isEmpty()){
				outcome.push(income.pop());
			}
		}
		return outcome.pop();
	}

	public int peek() {
		if (outcome.isEmpty()){
			while (!income.isEmpty()){
				outcome.push(income.pop());
			}
		}
		return outcome.peek();
	}

	public boolean empty() {
		return outcome.isEmpty() && income.empty();
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
