package algoexpert.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

//739. Daily Temperatures
public class DailyTemperaturesNeetCode {

	void main(String[] args) {
//		Input: temperatures = [30,38,30,36,35,40,28]
//		Output: [1,4,1,2,1,0,0]
		int[] arr = {30,38,30,36,35,40,28};
		System.out.println(Arrays.toString(dailyTemperatures(arr))); //Output: [1,4,1,2,1,0,0]

		//30

	}

	public int[] dailyTemperatures(int[] temperatures) { //O(n) TC; O(1) = SC
		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>();


		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
				Integer index = stack.pop();
				result[index] =  i - index;
			}
			stack.push(i);

		}

		return result;
	}
}
