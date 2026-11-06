package algoexpert.leetcode.binary_search;

//875. Koko Eating Bananas
public class KokoEatingBananas {

	public static void main(String[] args) {
		int[] arr = {3,6,7,11}; int h = 8; //Output: 4
//		int[] arr = {30,11,23,4,20}; int h = 5; //Output:30
//		int[] arr = {30,11,23,4,20}; int h = 6; //Output: 23

		System.out.println(minEatingSpeed(arr, h));
	}

	public static int minEatingSpeed(int[] piles, int h) {
		int lo = 1, hi = 0;
		for (int p : piles) hi = Math.max(hi, p);

		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (hoursNeeded(piles, mid) <= h) {
				hi = mid;     // fast enough, try slower
			} else {
				lo = mid + 1;  // too slow
			}
		}
		return lo;
	}

	private static long hoursNeeded(int[] piles, int k) {
		long hours = 0;
		for (int p : piles) {
			hours = hours +  (p + k - 1) / k; // ceil(p / k)
		}
		return hours;
	}
}
