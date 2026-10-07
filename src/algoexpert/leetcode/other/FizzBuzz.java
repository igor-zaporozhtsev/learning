package algoexpert.leetcode.other;

public class FizzBuzz {

	public static void main(String[] args) {
		int number = 99;
		System.out.println(fizzBuzz(number));
	}

	private static String fizzBuzz(int n){
		if (n % 3 == 0 && n % 5 == 0){
			return "FizzBuzz";
		} else if (n % 3 == 0){
			return "Fizz";
		} else if (n % 5 == 0) {
			return "Buzz";
		} else {
			return String.valueOf(n);
		}
	}
}
