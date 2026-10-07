package algoexpert.leetcode.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SequencedCollection;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

//347. Top K Frequent Elements
//Bucket Sort
public class TopKFrequentElements {

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		int k = 2;
		System.out.println(Arrays.toString(topKFrequent2(nums, k)));
	}

	public static int[] topKFrequent2(int[] nums, int k) {
		//k -> count
		//v - values
		Map<Integer, List<Integer>> map = new HashMap<>(nums.length);
		Map<Integer, Integer> frequencyMap = new HashMap<>(nums.length);

		for (int i = 1; i < nums.length + 1; i++) {
			map.put(i, new ArrayList<>());
		}

		for (int num : nums) {
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}

		for (Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();

			List<Integer> listIns = map.get(value);
			listIns.add(key);
		}

		List<Map.Entry<Integer, List<Integer>>> list = new ArrayList<>(map.entrySet());

		List<Integer> res = new ArrayList<>(k);

		for (int i = nums.length; i >= 1 && res.size() < k; i--) {
			for (int value : map.get(i)) {
				res.add(value);

				if (res.size() == k) {
					break;
				}
			}
		}


		return res.stream().mapToInt(n->n).toArray();
	}
	public static int[] topKFrequent1(int[] nums, int k) {
			Map<Integer, Integer> map = new HashMap<>();

			// Count frequencies
			for (int num : nums) {
				map.put(num, map.getOrDefault(num, 0) + 1);
			}

			// Put entries into a list
			List<Map.Entry<Integer, Integer>> entries =
				new ArrayList<>(map.entrySet());

			// Sort by frequency, highest first
			entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); //comparingByValue

			// Take the first k elements
			int[] result = new int[k];

			for (int i = 0; i < k; i++) {
				result[i] = entries.get(i).getKey();
			}

			return result;
		}

}
