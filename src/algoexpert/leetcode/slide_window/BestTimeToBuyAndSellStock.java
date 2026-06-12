package algoexpert.leetcode.slide_window;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock {

	public static void main(String[] args) {
		int[] arr = {10,1,5,6,7,1};
//		int[] arr = {2,1,2,1,0,1,2};
//		int[] arr = {5,1,5,6,7,1,10};
		int i = maxProfit(arr);
		System.out.println(i);
	}
	public static int maxProfit(int[] prices) {
		int profit = 0;

		for (int left = 0, right = 1; right < prices.length; right++) {
			if (prices[right] - prices[left] <= 0){
				left++; //left = right;?
			} else {
				profit = Math.max(profit, (prices[right] - prices[left]));
			}
		}
		return profit;
	}
}
