package algoexpert.leetcode.two_pointers;

public class BestTimetoBuyandSellStock_121 {
    //121. Best Time to Buy and Sell Stock
    public static void main(String[] args) {
        int[] arr1 = {2,1,2,1,0,1,2};
        int[] arr2 = {7,1,5,3,6,4};
        int res1 = maxProfit(arr2);
        System.out.println(res1);
    }

    public static int maxProfit(int[] prices) {

        int left = 0;
        int right = 1;
        int maxProfit = 0;
        while (prices.length > right){
            if (prices[left] > prices[right]){
                left = right;
            } else {
                int profit = prices[right] - prices[left];
                right++;
                maxProfit = Math.max(profit, maxProfit);

            }
        }
        return maxProfit;
    }


}
