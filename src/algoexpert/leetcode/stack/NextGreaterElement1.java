package algoexpert.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//496. Next Greater Element I
public class NextGreaterElement1 {

	/*

	A monotonic stack keeps track of multiple values that are still waiting for an answer.!

	The stack stores elements for which we still haven't found the answer.
	When a new element arrives, it may become the answer for some elements in the stack.

	Monotonic Stack — Mental Model

	Stack = things waiting for an answer.

	When a new element comes:

	Can it answer the top element?
	Yes → pop() the top and give it the answer.
	Repeat while it can answer more elements.
	Can't answer anymore?
	push() the new element.
	The stack stays monotonic.
	* */

	public static void main(String[] args) {
		int[] arr1 = {4, 1, 2};
		int[] arr2 = {1,3,4,2};
		System.out.println(Arrays.toString(nextGreaterElement(arr1, arr2)));
//		Input: nums1 = [2,4], nums2 = [1,2,3,4]
//		Output: [3,-1]
	}

	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		//O(m+n)

		int[] result = new int[nums1.length];

		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> decreaseStack = new Stack<>();

		for (int i = 0; i < nums1.length; i++) {
			map.put(nums1[i], i);
			result[i] = -1;
		}

		for (int j = 0; j < nums2.length; j++) {
			while (!decreaseStack.isEmpty() && nums2[j] > decreaseStack.peek()){
				Integer key = decreaseStack.pop();
				if (map.containsKey(key)) {
					Integer index = map.get(key);
					result[index] = nums2[j];
				}
			}

			decreaseStack.push(nums2[j]);
		}

		return result;
	}
}
