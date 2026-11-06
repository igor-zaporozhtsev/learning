package algoexpert.leetcode.arrays_hashing;

import java.util.*;

public class TopKElementsinList347 {
    public static void main(String[] args) {
        int [] arr = {1, 2, 2, 2, 2, 3, 3, 3, 3};
        int k = 2;
        int [] ints = topKFrequent(arr, k);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // Step 1: Create a map to count the frequency of each element.
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a max heap (priority queue) to store elements based on their frequencies.
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(map.entrySet());

        // Step 3: Extract the top k elements from the heap.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
    }
}
