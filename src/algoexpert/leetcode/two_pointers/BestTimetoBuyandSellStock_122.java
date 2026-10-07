package algoexpert.leetcode.two_pointers;

public class BestTimetoBuyandSellStock_122 {
    //122. Best Time to Buy and Sell Stock II
    public static void main(String[] args) {
        int[] arr1 = {2,1,2,1,0,1,2};
        int[] arr2 = {7,1,5,3,6,4};
        int res1 = maxProfit(arr2);
        System.out.println(res1);
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]){
                profit += (prices[i] - prices[i -1]);
            }
        }

        return profit;
    }


}
