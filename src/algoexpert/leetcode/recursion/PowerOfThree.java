package algoexpert.leetcode.recursion;

public class PowerOfThree {

	public static void main(String[] args) {
		//boolean isPower1 = isPowerOfThree(27);
//		boolean isPower2 = isPowerOfThree(45);
		boolean isPower2 = isPowerOfThree(51);
//		System.out.println(isPower1);
		System.out.println(isPower2);
	}

	public static boolean isPowerOfThree(int n) {
		if(n == 0) {
			return false;
		}
		if(n == 1){
			return true;
		}

		//51 -> 17 -> 5.6
		//рекурсією дійшли до самого маленькогтчисла і вже з нього перевіяємо степень числа
		boolean powerOfThree = isPowerOfThree(n / 3);

		if (n % 3 == 0){
			return powerOfThree;
		} else {
			return false;
		}
	}
}
