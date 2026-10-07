package algoexpert.leetcode.slide_window;

import java.util.Map;

public class BestTimeToBuyAndSellStockDemo {

	public static void main(String[] args) {
//		int[] arr = {10,1,5,6,7,1};
//		int[] arr = {1,2};
//		int[] arr = {7,1,5,3,6,4};
		int[] arr = {1,2,4,2,5,7,2,4,9,0,9};
//		int[] arr = {5,1,5,6,7,1,10};
		int i = maxProfit(arr);
		System.out.println(i);
	}
	public static int maxProfit(int[] prices) {
		int profit = 0;
		int left = 0; //buy
		int right = 1; //sell

		while (right < prices.length){
			int result = prices[right] - prices[left];
			if (result <= 0){
				left++;
			} else {
				profit = Math.max(result, profit);
			}
			right++;
		}

		return profit;
	}
}
