package algoexpert.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

//739. Daily Temperatures
public class DailyTemperatures {

	public static void main(String[] args) {
//		int[] arr = {73,74,75,71,69,72,76,73};
//		System.out.println(Arrays.toString(dailyTemperatures(arr))); //Output: [1,1,4,2,1,1,0,0]
		int[] arr = {30,40,10, 30, 60};
		System.out.println(Arrays.toString(dailyTemperatures(arr))); //Output: [1,3,1,1,0]

	}

	public static int[] dailyTemperatures(int[] temperatures) { //O(n) TC; O(1) = SC
		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
				Integer index = stack.pop();
				result[index] = i - index;
			}
			stack.push(i);
		}
		return result;
	}
}
